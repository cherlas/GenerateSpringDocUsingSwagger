package cn.istarxc.tools.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 接口文档生成测试
 * </p>
 *
 * @author Cherlas
 * @since 2020-05-30
 */
@Data
@ApiModel(value = "请求返回参数对象")
public class InterfaceGenerateRspDto implements Serializable {
    private String id;

    @ApiModelProperty(value = "字符串类型列")
    private String strColumn;

    @ApiModelProperty(value = "整型列")
    private Integer intColumn;

    @ApiModelProperty(value = "时间戳列")
    private LocalDateTime dateColumn;

    @ApiModelProperty(value = "boolean 类型列")
    private Boolean boolColumn;

    @ApiModelProperty(value = "枚举类型")
    private String enumColumn;

    @ApiModelProperty(value = "浮点型数据")
    private Float floatColumn;
}
