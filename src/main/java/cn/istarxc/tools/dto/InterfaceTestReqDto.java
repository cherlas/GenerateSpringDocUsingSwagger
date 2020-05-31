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
@ApiModel(value = "第二个参数")
public class InterfaceTestReqDto implements Serializable {
    @ApiModelProperty(value = "测试字段1")
    private String test1;

    @ApiModelProperty(value = "测试字段2")
    private String test2;

    @ApiModelProperty(value = "整型列")
    private Integer intColumn;

    @ApiModelProperty(value = "时间戳列")
    private LocalDateTime dateColumn;
}
