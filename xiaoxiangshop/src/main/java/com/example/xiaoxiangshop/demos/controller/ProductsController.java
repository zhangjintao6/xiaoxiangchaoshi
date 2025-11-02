package com.example.xiaoxiangshop.demos.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.xiaoxiangshop.demos.pojo.Label;
import com.example.xiaoxiangshop.demos.pojo.Products;
import com.example.xiaoxiangshop.demos.service.IProduct_typesService;
import com.example.xiaoxiangshop.demos.service.IProductsService;
import com.example.xiaoxiangshop.demos.util.uploadImg;
import com.example.xiaoxiangshop.demos.vo.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zjt
 * @since 2024-09-12
 */
@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    IProductsService productsService;
    @Autowired
    IProduct_typesService product_typesService;


    //查询的方法
    //标签，父类型，子类型，排序条件，正序倒序
    //折扣，rate=null不查，rate=0查所有折扣，rate=1，2，3...查具体的折扣率
    @RequestMapping("findProducts")
    List<Products> findProducts(@RequestBody Map<String, Object> params){
        // 解析JSON数据并使用
        Integer pt_id = (Integer) params.get("pt_id");
        Integer ct_id = (Integer) params.get("ct_id");
        List<Integer> label_ids = (List<Integer>) params.get("label_ids");
        String sort = (String) params.get("sort");
        Integer order = (Integer) params.get("order");
        Integer rate = (Integer) params.get("rate");
        return productsService.findProducts(pt_id,ct_id,label_ids,sort,order,rate);
    }

    //搜索
    //模糊查询名称，品牌，详情
    //带条件：标签，价格区间，子类型，活动
    //销量，价格排序
    @RequestMapping("searchProducts")
    List<Products> searchProducts(@RequestBody Map<String, Object> params){
        // 解析JSON数据并使用
        String keyword = (String) params.get("keyword");
        List<Integer> label_ids = (List<Integer>) params.get("label_ids");
        Integer brand_id = (Integer) params.get("brand_id");
        Double low = (Double) params.get("low");
        Double high = (Double) params.get("high");
        Integer ct_id = (Integer) params.get("ct_id");
        Integer rate = (Integer) params.get("rate");
        String sort = (String) params.get("sort");
        Integer order = (Integer) params.get("order");
        return productsService.searchProducts(keyword,label_ids,brand_id,low,high,ct_id,rate,sort,order);
    }


    @RequestMapping("/listByLabelId")/*根据传过来的标签id查出来标签商品*/
    List<Products> listByTagId(int LabelId) {
        return productsService.findProductsByLabelId(LabelId);
    }

    @RequestMapping("/search")/*模糊查询功能，可以根据用户传过来的商品名，价格，品牌*/
    public List<Products> searchProducts(@RequestParam String keyword) {
        return productsService.findProductsByNameOrBrandOrPrice(keyword);
    }

    //查询所有的商品信息包括品牌、类型等
    @RequestMapping("/findAllProducts")
    IPage<Products> findAllProducts(@RequestParam int page,
                                    @RequestParam int limit,
                                    @RequestParam String check){
        if (check != null && !check.isEmpty()){
            QueryWrapper<Products> wrapper = new QueryWrapper<>();
            wrapper.like("product_name",check);
            return productsService.findAllProductsByCheck(new Page<>(page,limit),wrapper);
        }
        return productsService.findAllProducts(new Page<>(page,limit));
    }

    @RequestMapping("upload")
    ResponseMsg upload(MultipartFile file) {
        String path = "E:/JAVA_PRO/vivo/gitee/xiaoxiangshop/vue-admin/public/productImg/";
        return uploadImg.uploadImgs(file, path);
    }

    //查询
    @RequestMapping("doUpdate")
    Boolean doUpdate(@RequestBody Products product){
        UpdateWrapper<Products> wrapper = new UpdateWrapper<>();
        wrapper.eq("product_id",product.getProduct_id());
        if (product.getBrand_id() != null && product.getBrand_id() != 0){
            wrapper.set("brand_id",product.getBrand_id());
        }
        if (product.getType_id() != null && product.getType_id() != 0){
            wrapper.set("type_id",product.getType_id());
        }
        if (product.getPrice() != null && product.getPrice() != 0){
            wrapper.set("price",product.getPrice());
        }
        if (product.getStock() != null && product.getStock() != 0){
            wrapper.set("stock",product.getStock());
        }
        if (product.getProduct_name() != null && !product.getProduct_name().isEmpty()){
            wrapper.set("product_name",product.getProduct_name());
        }
        if (product.getDescription() != null && !product.getDescription().isEmpty()){
            wrapper.set("description",product.getDescription());
        }
        if (product.getImg() != null && !product.getImg().isEmpty()){
            wrapper.set("img",product.getImg());
        }
        return productsService.update(wrapper);
    }

    @RequestMapping("doAdd")
    Boolean doAdd(@RequestBody Products product){
        product.setImg_detail(1);
        product.setSale_num(0);
        LocalDateTime now = LocalDateTime.now();
        product.setCreate_time(now);
        return productsService.save(product);
    }

    @RequestMapping("delByIds")
    Boolean delByIds(@RequestBody List<Integer> ids){
        return productsService.removeByIds(ids);
    }




}
