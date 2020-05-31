package cn.istarxc.tools.controller;


import cn.istarxc.tools.dto.CombinDto;
import cn.istarxc.tools.dto.InterfaceGenerateReqDto;
import cn.istarxc.tools.dto.InterfaceGenerateRspDto;
import cn.istarxc.tools.service.IInterfaceGenerateService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 接口文档生成测试 前端控制器
 * </p>
 *
 * @author Cherlas
 * @since 2020-05-30
 */
@RestController
@RequestMapping("/interface-generator")
public class InterfaceGenerateController {

    @Autowired
    IInterfaceGenerateService iInterfaceGenerateService;

    @PostMapping("/query-data")
    @ApiOperation("请求数据")
    public InterfaceGenerateRspDto queryData(@RequestBody InterfaceGenerateReqDto interfaceGenerateReqDto, String testVal) {
        return iInterfaceGenerateService.queryData(interfaceGenerateReqDto);
    }

    @PostMapping("/test-data")
    @ApiOperation("请求数据")
    public Boolean testData(@RequestBody CombinDto combinDto) {
        return null;
    }

    @PostMapping("/test-list")
    @ApiOperation("请求数据")
    public Integer testList(@RequestBody List<InterfaceGenerateReqDto> dtoList) {
        return null;
    }
}
