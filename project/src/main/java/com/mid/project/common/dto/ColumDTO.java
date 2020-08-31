package com.mid.project.common.dto;

import com.jsy.service.common.util.PageInfo;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author jiangshaoyue
 * @since 2020-06-08
 */
@Data
public class ColumDTO extends PageInfo {

    private Long id;

    private Boolean tTinyint1;

    private Integer tTinyint4;

    private Integer tInt;

    private BigDecimal tDecimal;

    private LocalDateTime tDatetime;

    private String tChar;

    private String tVarchar;

}
