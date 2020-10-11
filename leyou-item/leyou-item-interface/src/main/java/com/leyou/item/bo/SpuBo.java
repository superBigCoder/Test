package com.leyou.item.bo;

import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;

import java.util.List;

public class SpuBo extends Spu {
    private String cname;
    private String bname;
    private List<Sku> skus;
    private SpuDetail spuDetail;

    @Override
    public List<Sku> getSkus() {
        return skus;
    }

    @Override
    public void setSkus(List<Sku> skus) {
        this.skus = skus;
    }

    @Override
    public SpuDetail getSpuDetail() {
        return spuDetail;
    }

    @Override
    public void setSpuDetail(SpuDetail spuDetail) {
        this.spuDetail = spuDetail;
    }

    @Override
    public String getCname() {
        return cname;
    }

    @Override
    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String getBname() {
        return bname;
    }

    @Override
    public void setBname(String bname) {
        this.bname = bname;
    }
}
