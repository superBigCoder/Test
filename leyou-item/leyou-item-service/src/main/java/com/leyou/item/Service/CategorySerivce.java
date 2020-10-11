package com.leyou.item.Service;

import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategorySerivce {
    //根据父节点查询子节点
    @Autowired
    private CategoryMapper categoryMapper;
        public List<Category> queryListById(Long pid){
            Category record=new Category();
            record.setParentId(pid);
            return categoryMapper.select(record);
        }
    public List<String> queryListByIds(List<Long> ids){
        List<Category> categories = categoryMapper.selectByIdList(ids);
        List<String> collect = categories.stream().map(categorie -> {
            return categorie.getName();
        }).collect(Collectors.toList());
        return collect;
    }

}
