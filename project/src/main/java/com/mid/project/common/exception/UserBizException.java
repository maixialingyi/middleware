package com.mid.project.common.exception;

/**
 * 用户设置相关异常
 */

public class UserBizException extends BaseBizException{

    public UserBizException(BizCode bizCode) {
        super(bizCode);
    }

    public UserBizException(BizCode bizCode, String message) {
        super(bizCode, message);
    }

    public UserBizException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserBizException(BizCode bizCode, String message, Throwable cause) {
        super(bizCode, message, cause);
    }

    @Override
    public String getCode() {
        return bizCode.getCode(BizType.LECHECK_USE_CENTER);
    }
}
