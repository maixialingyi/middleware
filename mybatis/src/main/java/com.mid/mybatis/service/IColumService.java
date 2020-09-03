package com.mid.project.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mid.project.common.dto.ColumDTO;
import com.mid.project.common.entity.Colum;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiangshaoyue
 * @since 2020-06-08
 */
public interface IColumService extends IService<Colum> {

    //自定义sql 不可覆盖mybaits-plus中方法
    List<Colum> testPageHelper(ColumDTO columDTO);
}
