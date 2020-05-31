package cn.istarxc.tools.service.impl;

import cn.istarxc.tools.dto.InterfaceGenerateReqDto;
import cn.istarxc.tools.dto.InterfaceGenerateRspDto;
import cn.istarxc.tools.entity.InterfaceGenerate;
import cn.istarxc.tools.mapper.InterfaceGenerateMapper;
import cn.istarxc.tools.service.IInterfaceGenerateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 接口文档生成测试 服务实现类
 * </p>
 *
 * @author Cherlas
 * @since 2020-05-30
 */
@Service
public class InterfaceGenerateServiceImpl extends ServiceImpl<InterfaceGenerateMapper, InterfaceGenerate> implements IInterfaceGenerateService {

    @Override
    public InterfaceGenerateRspDto queryData(InterfaceGenerateReqDto interfaceGenerateReqDto) {
        return new InterfaceGenerateRspDto();
    }
}
