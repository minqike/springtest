package com.min.spring.controller;

import com.min.spring.dto.R;
import com.min.spring.util.SnowflakeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@PropertySource("classpath:application.properties")
public class UploadController {

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Value("${file.staticAccessPath}")
    private String staticAccessPath;

    @ResponseBody
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public R upload(MultipartFile file, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        //获取上传目录
        String path = request.getSession().getServletContext().getRealPath("/");

        //目录不存在这创建
        File folder= new File(uploadFolder);
        if (!folder.exists()) {
            folder.mkdir();
        }
        //获取文件后缀
        String fileExt;
        String fileName = file.getOriginalFilename();
        String[] filesplit = fileName.split("\\.");
        if (filesplit.length>1){
            fileExt = filesplit[filesplit.length - 1];
        }else{
            return R.error(-1, "上传失败，文件名有问题");
        }
        String newfilename= SnowflakeUtils.genId() +  "." + fileExt;
        //将文件写入目标
        File newFile = new File(folder ,newfilename);
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok(staticAccessPath+newfilename);

    }
}
