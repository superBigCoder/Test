package com.leyou.item.controller;

import com.leyou.item.Service.SpecGroupService;
import com.leyou.item.Service.SpecParamService;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("spec")
public class SpecGroupController {
@Autowired private SpecGroupService specGroupService;
    @Autowired private SpecParamService specParamService;
    @RequestMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupByCid(@PathVariable("cid") Long cid){
       List<SpecGroup> groups= specGroupService.queryGroupByCid(cid);
       if(CollectionUtils.isEmpty(groups)){
           return ResponseEntity.notFound().build();
       }
        return ResponseEntity.ok(groups);
    }
    @RequestMapping("params")
    public ResponseEntity<List<SpecParam>> queryParamByGid(
            @RequestParam(value="gid",required = false)Long gid,
             @RequestParam(value="cid",required = false)Long cid,
            @RequestParam(value="generice",required = false)Boolean generice,
            @RequestParam(value="searching",required = false)Boolean searching
    ){
        List<SpecParam> params= specParamService.queryParamByGid(gid,cid,generice,searching);
        if(CollectionUtils.isEmpty(params)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(params);
    }
}
