package com.example.xiaoxiangshop.demos.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.xiaoxiangshop.demos.pojo.Img_detail;
import com.example.xiaoxiangshop.demos.service.impl.Img_detailServiceImpl;
import com.example.xiaoxiangshop.demos.util.uploadImg;
import com.example.xiaoxiangshop.demos.vo.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zjt
 * @since 2024-09-12
 */
@RestController
@RequestMapping("/img_detail")
public class Img_detailController {

    @Autowired
    Img_detailServiceImpl imgDetailService;
    @Value("${uploadPath}")
    String uploadPath;

    @RequestMapping("getImageDetail")
    List<Img_detail> getImageDetail(int pid){
        return imgDetailService.getImageDetail(pid);
    }

    @RequestMapping("upload")
    ResponseMsg upload(MultipartFile file){
        String path = "E:/JAVA_PRO/vivo/gitee/xiaoxiangshop/vue-admin/public/productImg/";
        return uploadImg.uploadImgs(file,path);
    }

    @RequestMapping("updateImg")
    Boolean updateImg(@RequestBody Img_detail detail){
        UpdateWrapper<Img_detail> wrapper = new UpdateWrapper<>();
        wrapper.eq("img_detail_id",detail.getImg_detail_id());
        wrapper.set("img",detail.getImg());
        return imgDetailService.update(wrapper);
    }

    @RequestMapping("addImg")
    Boolean addImg(@RequestBody Img_detail detail){
        return imgDetailService.save(detail);
    }

    @RequestMapping("delImg")
    Boolean delImg(@RequestBody Img_detail detail){
        return imgDetailService.removeById(detail.getImg_detail_id());
    }
}

