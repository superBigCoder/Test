package com.leyou.item.pojo;

import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import javax.persistence.Transient;

@Table(name = "tb_spec_group")
public class SpecGroup {

    @Id
    @KeySql(useGeneratedKeys=true)
    private Long id;

    private Long cid;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SpecParam> getParams() {
        return params;
    }

    public void setParams(List<SpecParam> params) {
        this.params = params;
    }

    @Transient
    private List<SpecParam> params; // 该组下的所有规格参数集合

   // getter和setter省略
}