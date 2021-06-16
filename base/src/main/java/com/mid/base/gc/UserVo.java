package com.mid.base.gc;

import lombok.Data;
import lombok.experimental.Accessors;

@Data //Set GET方法
@Accessors(chain = true)
public class UserVo {
    private String userId;
    private String userName;
}
