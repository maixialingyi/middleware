package com.mid.project.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class ExceptionDTO {

    @NotEmpty(message = "feild不能为空")
    @ApiModelProperty(value="feild",required=true)
    private String feild;

    @NotEmpty(message = "List不能为空")
    @ApiModelProperty(value="dList不能为空",required=true)
    private List<Knowledge> feildList;

    @NotEmpty(message = "对象类型属性不能为空")
    @ApiModelProperty(value="qi不能为空",required=true)
    private Qi qi;

    @Data
    public static class Knowledge {
        @ApiModelProperty(value="维度id",required=true)
        private String lkcId;
    }
    @Data
    public static class Qi {
        @ApiModelProperty(value="年级id列表",required=true)
        private List<String> gradeIdList;          
    }
}
