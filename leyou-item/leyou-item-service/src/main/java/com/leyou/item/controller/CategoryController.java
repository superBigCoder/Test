package com.leyou.item.controller;

import com.leyou.item.Service.CategorySerivce;
import com.leyou.item.pojo.Category;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {
@Autowired
  private CategorySerivce categorySerivce;
    //根据父节点查询子节点
    @GetMapping("list")
    public ResponseEntity<List<Category>> queryListById(@RequestParam(value="pid",defaultValue = "0") Long pid){

              if (pid == null || pid < 0) {
//                  return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
              return ResponseEntity.badRequest().build();
              }
              List<Category> categories = this.categorySerivce.queryListById(pid);
        System.out.println(categories+"-------------------------------------------------");
              if (CollectionUtils.isEmpty(categories)) {
//                  return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                  return ResponseEntity.notFound().build();
              }
              return ResponseEntity.ok(categories);


    }
}
