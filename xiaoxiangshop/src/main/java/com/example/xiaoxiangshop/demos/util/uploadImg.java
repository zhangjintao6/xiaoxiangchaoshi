package com.example.xiaoxiangshop.demos.util;

import com.example.xiaoxiangshop.demos.vo.ResponseMsg;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class uploadImg {
    public static ResponseMsg uploadImgs(MultipartFile file,String path){
        String fileName = "";
        //得到服务器运行绝对路径
        File aFile = null;
        try {
            aFile = ResourceUtils.getFile("classpath:");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir(); //upload文件夹不存在就创建
        }

        if (null != file && !file.isEmpty()){
            //file.getOriginalFilename(); //得到上传文件的原始名字
            fileName = formateDate()  + file.getOriginalFilename();
            File savedFile = new File(path + fileName); //path+fileName文件的完整路径
            System.out.println(path + fileName);
            try {
                file.transferTo(savedFile); //保存文件对象到服务器本地磁盘
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResponseMsg.success(fileName);
    }
    public static String formateDate(){
        LocalDateTime now = LocalDateTime.now(); //得到当前日期
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        return formatter.format(now);
    }
}
