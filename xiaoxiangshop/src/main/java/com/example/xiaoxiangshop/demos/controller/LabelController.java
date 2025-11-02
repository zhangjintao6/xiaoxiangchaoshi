package com.example.xiaoxiangshop.demos.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.xiaoxiangshop.demos.pojo.Label;
import com.example.xiaoxiangshop.demos.service.ILabelService;
import com.example.xiaoxiangshop.demos.util.uploadImg;
import com.example.xiaoxiangshop.demos.vo.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/label")
public class LabelController {

    @Autowired
    ILabelService labelService;

    @RequestMapping("findAllLabel")
    IPage<Label> findAllLabel(@RequestParam int page,
                              @RequestParam int limit,
                              @RequestParam String check){
        if (check != null && !check.isEmpty()){
            QueryWrapper<Label> wrapper = new QueryWrapper<>();
            wrapper.like("label_name",check);
            return labelService.page(new Page<>(page,limit),wrapper);
        }
        return labelService.page(new Page<>(page,limit));
    }

    @RequestMapping("updateLabel")
    Boolean updateLabel(@RequestBody Label label){
        UpdateWrapper<Label> wrapper = new UpdateWrapper<>();
        wrapper.eq("label_id",label.getLabel_id());
        if (label.getLabel_name() != null && !label.getLabel_name().isEmpty()){
            wrapper.set("label_name",label.getLabel_name());
        }
        if (label.getImg() != null && !label.getImg().isEmpty()){
            wrapper.set("img",label.getImg());
        }
        return labelService.update(wrapper);
    }

    @RequestMapping("upload")
    ResponseMsg upload(MultipartFile file) {
        String path = "E:/JAVA_PRO/vivo/gitee/xiaoxiangshop/vue-admin/public/labelImg/";
        return uploadImg.uploadImgs(file, path);
    }

    @RequestMapping("doAdd")
    Boolean doAdd(@RequestBody Label label){
        return labelService.save(label);
    }

    @RequestMapping("delByIds")
    Boolean delByIds(@RequestBody List<Integer> ids){
        return labelService.removeByIds(ids);
    }

}

