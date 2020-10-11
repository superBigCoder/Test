package com.leyou.item.controller;

import com.common.utils.PageResult;
import com.leyou.item.Service.BrandService;
import com.leyou.item.pojo.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("brand")
public class BrandController {
    @Autowired
    private BrandService brandService;
    @RequestMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value = "key",required = false) String key,
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "rows",defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy",defaultValue = "id") String sortBy,
            @RequestParam(value = "desc",defaultValue = "asc") Boolean desc
    ){
        PageResult<Brand> result = brandService.queryBrandByPage(key,page,rows,sortBy,desc);
                if(CollectionUtils.isEmpty(result.getItems())){
                    return ResponseEntity.notFound().build();
                }
                return ResponseEntity.ok(result);
    }
    @PostMapping
    public ResponseEntity<Void> saveBrand(@RequestBody Brand brand, @RequestParam("cids") List<Long> cids){
                   brandService.saveBrand(brand,cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @RequestMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandByCid(@PathVariable("cid")Long cid){
        List<Brand> brands= brandService.queryBrandByCid(cid);
        return ResponseEntity.ok(brands);
    }
}
