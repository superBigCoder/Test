package com.leyou.item.Service;

import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecParamService {
    @Autowired
    private SpecParamMapper specParamMapper;
public List<SpecParam> queryParamByGid(Long gid,Long cid,Boolean generice,Boolean searching){
    SpecParam specParam=new SpecParam();
    specParam.setGroupId(gid);
    specParam.setCid(cid);
    specParam.setGeneric(generice);
    specParam.setSearching(searching);
    List<SpecParam> select = specParamMapper.select(specParam);
    return select;
}
}
