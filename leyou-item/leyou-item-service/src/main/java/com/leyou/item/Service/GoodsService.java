package com.leyou.item.Service;

import com.common.utils.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.item.bo.SpuBo;
import com.leyou.item.mapper.*;
import com.leyou.item.pojo.*;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsService {
    @Autowired
    private SpuDetailMapper spuDetailMapper;
    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private CategorySerivce categorySerivce;
    public PageResult<SpuBo> queryGoodsByPage(
            String key,
            Boolean saleable,
            Integer page,
            Integer rows
    ){
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(key)){
            criteria.andLike("title", "%"+key+"%");
        }
        if(saleable!=null){
            criteria.andEqualTo("saleable",saleable);
        }
        PageHelper.startPage(page,rows);
        List<Spu> spus = spuMapper.selectByExample(example);
        PageInfo pageInfo=new PageInfo(spus);
        List<SpuBo> spuBos = spus.stream().map(spusList -> {
            SpuBo spuBo = new SpuBo();
            BeanUtils.copyProperties(spusList, spuBo);

            spuBo.setBname(brandMapper.selectByPrimaryKey(spusList.getBrandId()).getName());

            spuBo.setCname(StringUtils.join(categorySerivce.queryListByIds(Arrays.asList(spusList.getCid1(), spusList.getCid2(), spusList.getCid3())), "-"));
            return spuBo;
        }).collect(Collectors.toList());
        PageResult pageResult=new PageResult(pageInfo.getTotal(),spuBos);
        return pageResult;
    }
    @Transactional
    public void saveGoods(SpuBo spuBo){
        spuBo.setId(null);
        spuBo.setSaleable(true);
        spuBo.setValid(true);
        spuBo.setCreateTime(new Date());
        spuBo.setLastUpdateTime(spuBo.getCreateTime());
        spuMapper.insert(spuBo);
        spuBo.getSpuDetail().setSpuId(spuBo.getId());
        spuDetailMapper.insert(spuBo.getSpuDetail());
//        Sku record=new Sku();
//        record.setSpuId(spuBo.getId());
//        List<Sku> skus = skuMapper.select(record);
        spuBo.getSkus().forEach(sku->{
            sku.setId(null);

            sku.setSpuId(spuBo.getId());
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(sku.getCreateTime());
            skuMapper.insert(sku);
            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            stockMapper.insert(stock);
        });


    }

    public void addSkuAndStock(SpuBo spuBo){
        spuBo.getSkus().forEach(sku->{
            sku.setId(null);

            sku.setSpuId(spuBo.getId());
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(sku.getCreateTime());
            skuMapper.insert(sku);
            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            stockMapper.insert(stock);
        });
    }
    @Transactional
    public void updateGoods(SpuBo spuBo){
        Sku record=new Sku();
        record.setSpuId(spuBo.getId());
        List<Sku> select = skuMapper.select(record);
        select.forEach(skus->{
            stockMapper.deleteByPrimaryKey(skus.getId());
        });
        skuMapper.delete(record);
        addSkuAndStock(spuBo);
        spuMapper.updateByPrimaryKeySelective(spuBo);
        spuDetailMapper.updateByPrimaryKeySelective(spuBo.getSpuDetail());
    }
    public SpuDetail queryDetail(Long sid){
       return spuDetailMapper.selectByPrimaryKey(sid);
    }
    public List<Sku> querySku(Long sid){
        Sku sku=new Sku();
        sku.setSpuId(sid);
        List<Sku> skus = skuMapper.select(sku);
        skus.forEach(skuss->{
            skuss.setStock(stockMapper.selectByPrimaryKey(skuss.getId()).getStock());

        });
        return skus;
    }
}
