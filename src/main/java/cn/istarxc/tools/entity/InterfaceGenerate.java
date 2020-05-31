package cn.istarxc.tools.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 接口文档生成测试
 * </p>
 *
 * @author Cherlas
 * @since 2020-05-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="StInterfaceGenerate对象", description="接口文档生成测试")
public class InterfaceGenerate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.UUID)
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
