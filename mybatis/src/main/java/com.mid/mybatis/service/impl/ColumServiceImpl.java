package com.mid.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiangshaoyue
 * @since 2020-06-08
 */
@Service
public class ColumServiceImpl extends ServiceImpl<com.mid.project.mapper.ColumMapper, com.mid.project.common.entity.Colum> implements com.mid.project.service.IColumService {

    @Autowired
    private com.mid.project.mapper.ColumMapper columMapper;

    @Override
    public List<com.mid.project.common.entity.Colum> testPageHelper(com.mid.project.common.dto.ColumDTO columDTO) {
        PageHelper.startPage(columDTO.getPageNum(), columDTO.getPageSize());
        com.mid.project.common.entity.Colum colum = new com.mid.project.common.entity.Colum();
        BeanUtils.copyProperties(columDTO, colum);
        List<com.mid.project.common.entity.Colum> list = columMapper.testPageHelper(colum);
        //此list如果再处理需关注total
        PageInfo<com.mid.project.common.entity.Colum> pageInfoVO = new PageInfo<com.mid.project.common.entity.Colum>(list);
        return pageInfoVO.getList();
    }
}
