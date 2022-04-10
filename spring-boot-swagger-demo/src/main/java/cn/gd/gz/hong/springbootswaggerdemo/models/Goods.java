package cn.gd.gz.hong.springbootswaggerdemo.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 *
 */
@Setter
@Getter
@ApiModel("商品模型")
public class Goods {

    /**
     * 商品id
     */
    @ApiModelProperty("商品ID")
    Long goodsId;

    /**
     * 商品名称
     */
    @ApiModelProperty("商品名称")
    private String goodsName;

    /**
     * 商品价格
     */
    @ApiModelProperty("商品价格")
    private BigDecimal price;
}
