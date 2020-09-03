package com.mid.project.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mid.project.common.entity.Colum;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jiangshaoyue
 * @since 2020-06-08
 */
public interface ColumMapper extends BaseMapper<Colum> {
    List<Colum> testPageHelper(Colum colum);
}
