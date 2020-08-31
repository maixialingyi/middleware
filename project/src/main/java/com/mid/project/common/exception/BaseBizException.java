package com.mid.project.common.exception;

/**
 * 业务异常
 */
public abstract class BaseBizException extends Exception{

    protected BizCode bizCode;

    public BaseBizException(BizCode bizCode) {
        super(bizCode.getMessage());
        this.bizCode = bizCode;
    }

    public BaseBizException(BizCode bizCode, Throwable cause) {
        super(bizCode.getMessage(),cause);
        this.bizCode = bizCode;
    }

    public BaseBizException(BizCode bizCode, String message) {
        super(message);
        this.bizCode = bizCode;
    }

    public BaseBizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseBizException(BizCode bizCode, String message, Throwable cause) {
        super(message, cause);
        this.bizCode = bizCode;
    }



    public BizCode getBizCode() {
        return bizCode;
    }

    public void setBizCode(BizCode bizCode) {
        this.bizCode = bizCode;
    }

    public abstract String getCode() ;

    public String getCodeMessage() {
        return this.bizCode.getMessage();
    }
}
