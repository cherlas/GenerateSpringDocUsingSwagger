package cn.istarxc.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;

@SpringBootTest
class InterfaceGeneratorToolsApplicationTests {

    @Autowired
    ConfigurableEnvironment configurableEnvironment;

    @Test
    void generateDocsUsingSwagger() {
        RestTemplate template = new RestTemplate();
        String path = configurableEnvironment.getProperty("server.servlet.context-path");
        String port = configurableEnvironment.getProperty("server.port");
        System.out.println(path + " --- " + port);

        String docJson = template.getForObject(URI.create("http://127.0.0.1:" + port + path + "/v2/api-docs"), String.class);
        docJson = docJson.replaceAll("\\#\\/definitions", "/definitions").replaceAll("\\$ref", "ref");
        JSONObject swaggerDocJsonObj = JSON.parseObject(docJson);
        String basePath = swaggerDocJsonObj.getString("basePath");
        JSONObject definitionsObj = swaggerDocJsonObj.getJSONObject("definitions");
        JSONObject pathsObj = swaggerDocJsonObj.getJSONObject("paths");
        Map<String, Map<String, Property>> definitions = generateDefinitions(definitionsObj);
        List<MethodInfo> methodInfos = generatePaths(pathsObj, definitions, basePath);
        generdateDocs(methodInfos);
    }

    private void generdateDocs(List<MethodInfo> methodInfos) {

    }

    private List<MethodInfo> generatePaths(JSONObject pathsObj, Map<String, Map<String, Property>> definitions, String basePath) {
        List<MethodInfo> methodInfos = new ArrayList<>();
        pathsObj.forEach((k, v) -> {
            if (!"/error".equals(k)) {
                ((JSONObject) v).forEach((key, value) -> {
                    JSONObject methodInfoObj = (JSONObject) value;
                    String methodSummary = methodInfoObj.getString("summary");

                    Map<String, Property> reqPara = new LinkedHashMap<>();
                    JSONArray parametersObj = methodInfoObj.getJSONArray("parameters");
                    parametersObj.forEach(parameter -> {
                        JSONObject para = (JSONObject) parameter;
                        JSONObject parameterSchema = para.getJSONObject("schema");
                        if (parameterSchema != null) {
                            String ref = parameterSchema.getString("ref");
                            if (ref == null) {
                                ref = parameterSchema.getJSONObject("items").getString("ref");
                            }
                            reqPara.putAll(definitions.get(ref.substring(ref.lastIndexOf("/") + 1)));
                        } else {
                            String parameterName = para.getString("name");
                            Property property = JSON.parseObject(parameter.toString(), Property.class);
                            reqPara.put(parameterName, property);
                        }
                    });

                    Map<String, Property> rspPara = new LinkedHashMap<>();
                    JSONObject responseObj = methodInfoObj.getJSONObject("responses").getJSONObject("200");
                    JSONObject parameterSchema = responseObj.getJSONObject("schema");
                    if (parameterSchema != null) {
                        String ref = parameterSchema.getString("ref");
                        if (ref == null) {
                            ref = parameterSchema.getJSONObject("items").getString("ref");
                        }
                        rspPara.putAll(definitions.get(ref.substring(ref.lastIndexOf("/") + 1)));
                    } else {
                        String parameterName = responseObj.getString("name");
                        Property property = JSON.parseObject(responseObj.toString(), Property.class);
                        rspPara.put(parameterName, property);
                    }

                    MethodInfo methodInfo = new MethodInfo();
                    methodInfo.setPath(basePath + k);
                    methodInfo.setSummary(methodSummary);
                    methodInfo.setUsingMethod(key.toUpperCase());
                    methodInfo.setReqParameters(reqPara);
                    methodInfo.setRspParameters(rspPara);
                    methodInfos.add(methodInfo);
                });
            }
        });
        return methodInfos;
    }

    private Map<String, Map<String, Property>> generateDefinitions(JSONObject definitionObj) {
        Map<String, Map<String, Property>> definitions = new HashMap<>();
        Set<String> refDef = new HashSet<>();
        final int[] index = {0};
        definitionObj.forEach((k, v) -> {
            if (!"ModelAndView".equals(k) && !"View".equals(k)) {
                JSONObject propertiesObj = ((JSONObject) v).getJSONObject("properties");
                Map<String, Property> properties = new HashMap<>();
                propertiesObj.forEach((key, value) -> {
                    Property property = JSON.parseObject(value.toString(), Property.class);
                    if (property.type == null) {
                        refDef.add(k);
                    } else {
                        properties.put(key + "-" + index[0], property);
                    }
                });
                if (!refDef.contains(k)) {
                    definitions.put(k, properties);
                }
                index[0]++;
            }
        });

        if (!refDef.isEmpty()) {
            refDef.forEach(refDefKey -> {
                JSONObject propertiesObj = definitionObj.getJSONObject(refDefKey).getJSONObject("properties");
                Map<String, Property> combineDef = new LinkedHashMap<>();
                propertiesObj.forEach((k, v) -> {
                    String ref = ((JSONObject) v).getString("ref");
                    String type = null;
                    if (ref == null) {
                        ref = ((JSONObject) v).getJSONObject("items").getString("ref");
                        type = "array";
                    }
                    String key = ref.substring(ref.lastIndexOf('/') + 1);
                    combineDef.put(k, new Property(type, "", "以下是 " + k + " 的参数"));
                    combineDef.putAll(definitions.get(key));
                });
                definitions.put(refDefKey, combineDef);
            });
        }

        return definitions;
    }

    @Data
    private static class Property {
        @JSONField(name = "type")
        private String type;
        @JSONField(name = "format")
        private String format;
        @JSONField(name = "description")
        private String description;

        public Property() {
        }

        public Property(String type, String format, String description) {
            this.type = type;
            this.format = format;
            this.description = description;
        }
    }

    @Data
    private static class MethodInfo {
        Map<String, Property> reqParameters;
        Map<String, Property> rspParameters;
        private String usingMethod;
        private String path;
        private String summary;
    }
}
