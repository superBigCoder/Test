package com.leyou.item.controller;

import com.common.utils.PageResult;
import com.leyou.item.Service.GoodsService;
import com.leyou.item.bo.SpuBo;
import com.leyou.item.mapper.SkuMapper;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.SpuDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    //分页查询spu
    @RequestMapping("spu/page")
    public ResponseEntity<PageResult<SpuBo>> queryGoodsByPage(
            @RequestParam(value = "key",required = false)String key,
            @RequestParam(value="saleable",required = false)Boolean saleable,
            @RequestParam("page")Integer page,
            @RequestParam("rows")Integer rows
     ){
      PageResult<SpuBo> result=goodsService.queryGoodsByPage(key,saleable,page,rows);
          if(CollectionUtils.isEmpty(result.getItems())){
           return ResponseEntity.notFound().build();
          }
          return ResponseEntity.ok(result);
    }
    @RequestMapping("sku/list")
    public ResponseEntity<List<Sku>> querySku(@RequestParam("id") Long id){
        List<Sku> skus = goodsService.querySku(id);
        return ResponseEntity.ok(skus);
    }
    @RequestMapping("spu/detail/{id}")
    public ResponseEntity<SpuDetail> queryDetail(@PathVariable("id")Long sid){
        SpuDetail spuDetail = goodsService.queryDetail(sid);
        return ResponseEntity.ok(spuDetail);
    }
    @PostMapping("goods")
    public ResponseEntity<Void> saveGoods(@RequestBody SpuBo spuBo){
                 goodsService.saveGoods(spuBo);
                 return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("goods")
    public ResponseEntity<Void> updateGoods(@RequestBody SpuBo spuBo){
        goodsService.updateGoods(spuBo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
