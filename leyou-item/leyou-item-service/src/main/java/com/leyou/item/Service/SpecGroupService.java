package com.leyou.item.Service;

import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.pojo.SpecGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecGroupService {
    @Autowired
    private SpecGroupMapper specGroupMapper;
    public List<SpecGroup> queryGroupByCid(Long cid){
        SpecGroup record=new SpecGroup();
        record.setCid(cid);
        List<SpecGroup> select = specGroupMapper.select(record);
        return select;
    }
}
