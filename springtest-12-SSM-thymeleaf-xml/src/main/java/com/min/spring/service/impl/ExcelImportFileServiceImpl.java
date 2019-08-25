package com.min.spring.service.impl;

import com.min.spring.constant.MyConstant;
import com.min.spring.dao.ExcelImportFileDao;
import com.min.spring.entity.ExcelImportFile;
import com.min.spring.entity.LoginUser;
import com.min.spring.service.ExcelImportFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ExcelImportFileServiceImpl implements ExcelImportFileService {

    @Autowired
    ExcelImportFileDao excelImportFileDao;





    @Override
    @Transactional
    public ExcelImportFile save(String importType, MultipartFile file, HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getSession().getAttribute(MyConstant.LOGIN_USER);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");//Date指定格式：yyyy-MM-dd HH:mm:ss:SSS
        String dateStr = simpleDateFormat.format(new Date());                         //format()方法将Date转换成指定格式的Strin

        ExcelImportFile excelImportFile = new ExcelImportFile();
        excelImportFile.setImportFileName(file.getOriginalFilename());
        excelImportFile.setImportDate(dateStr);
        excelImportFile.setImportType(importType);
        excelImportFile.setImportUser(loginUser.getUsername());
        excelImportFile.setStatus(0);

        excelImportFile.setImportCount(2);
        excelImportFile.setCreated(new Date());
        excelImportFile.setUpdated(excelImportFile.getCreated());

        ExcelImportFile importFile = excelImportFileDao.save(excelImportFile);



        return excelImportFile;
    }
}
