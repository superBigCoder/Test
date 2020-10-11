package com.leyou.item.Service;

import com.common.utils.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    private BrandMapper brandMapper;
    public PageResult<Brand> queryBrandByPage(String key,Integer page,Integer rows,String sortBy,Boolean desc){
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(key)){
            criteria.andLike("name","%"+key+"%").orEqualTo("letter",key);
        }
        PageHelper.startPage(page,rows);
        if(StringUtils.isNotBlank(sortBy)){
            example.setOrderByClause(sortBy+" "+(desc?"desc":"asc"));
        }
        List<Brand> brands = brandMapper.selectByExample(example);
        PageInfo<Brand> pageInfo=new PageInfo<>(brands);

        return new PageResult<>(pageInfo.getTotal(),pageInfo.getList());
    }
    @Transactional
    public void saveBrand(Brand brand,List<Long> cids){
        brandMapper.insertSelective(brand);

            cids.forEach(cid->{
                brandMapper.insertCategoryAndBrand(cid,brand.getId());
            });

    }
    public List<Brand> queryBrandByCid(Long cid){
        List<Brand> brands = brandMapper.selectBrandByCid(cid);
        return brands;
    }
}
