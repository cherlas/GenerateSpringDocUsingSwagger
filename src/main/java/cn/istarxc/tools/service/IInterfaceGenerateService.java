package cn.istarxc.tools.service;

import cn.istarxc.tools.dto.InterfaceGenerateReqDto;
import cn.istarxc.tools.dto.InterfaceGenerateRspDto;
import cn.istarxc.tools.entity.InterfaceGenerate;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 接口文档生成测试 服务类
 * </p>
 *
 * @author Cherlas
 * @since 2020-05-30
 */
public interface IInterfaceGenerateService extends IService<InterfaceGenerate> {
    InterfaceGenerateRspDto queryData(InterfaceGenerateReqDto interfaceGenerateReqDto);
}
