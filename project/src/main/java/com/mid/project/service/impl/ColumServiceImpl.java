package com.mid.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jsy.service.common.dto.ColumDTO;
import com.jsy.service.common.entity.Colum;
import com.jsy.service.mapper.ColumMapper;
import com.jsy.service.service.IColumService;
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
public class ColumServiceImpl extends ServiceImpl<ColumMapper, Colum> implements IColumService {

    @Autowired
    private ColumMapper columMapper;

    @Override
    public List<Colum> testPageHelper(ColumDTO columDTO) {
        PageHelper.startPage(columDTO.getPageNum(), columDTO.getPageSize());
        Colum colum = new Colum();
        BeanUtils.copyProperties(columDTO, colum);
        List<Colum> list = columMapper.testPageHelper(colum);
        //此list如果再处理需关注total
        PageInfo<Colum> pageInfoVO = new PageInfo<Colum>(list);
        return pageInfoVO.getList();
    }
}
