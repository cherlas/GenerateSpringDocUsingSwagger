package cn.istarxc.tools.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("合并dssto")
public class CombinDto {
    private InterfaceTestReqDto interfaceTestReqDto;
    private List<InterfaceGenerateReqDto> interfaceGenerateReqDtos;
}
