package cn.gd.gz.hong.springbootswaggerdemo.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 *
 */
@Getter
@Setter
@ApiModel("API通用数据")
public class CommonResponse<T> {

    /**
     * 标识代码，0表示成功，非0表示出错
     */
    @ApiModelProperty("标识代码,0表示成功，非0表示出错")
    private Integer code;

    /**
     * 描述信息，通常错时使用
     */
    @ApiModelProperty("错误描述")
    private String msg;

    /**
     * 业务数据
     */
    @ApiModelProperty("业务数据")
    private T data;

    public CommonResponse(Integer status, String msg, T data) {
        this.code = status;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功
     */
    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(0, "成功", data);
    }

    public static <T> CommonResponse<T> success(Integer code, String msg) {
        return new CommonResponse<>(code, msg, null);
    }

    /**
     * 错误
     */
    public static <T> CommonResponse<T> error(int code, String msg) {
        return new CommonResponse<>(code, msg, null);
    }
}

