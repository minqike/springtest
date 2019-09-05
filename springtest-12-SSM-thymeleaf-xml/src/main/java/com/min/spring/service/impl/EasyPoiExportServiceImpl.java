package com.min.spring.service.impl;

import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.min.spring.dto.ExportUserDto;
import com.min.spring.entity.User;
import com.min.spring.service.EasyPoiExportService;
import com.min.spring.service.UserService;
import com.min.spring.util.EasyPoiUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class EasyPoiExportServiceImpl implements EasyPoiExportService {
    @Autowired
    UserService userService;

    /**
     * 根据服务器上的模板生成excel文件,下载. 不会在服务器生成文件
     * @param response
     * @throws Exception
     */
    @Override
    public void exportUserUseTemplate(HttpServletResponse response) throws Exception {
        List<User> list = new ArrayList<>();
        list = userService.findAll();


        // 获取导出excel指定模版
        TemplateExportParams params = new TemplateExportParams();
        params.setSheetName("用户");
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("list", list);

        Workbook workbook = EasyPoiUtil.getWorkbook(params, data, "template/user.xlsx");
        EasyPoiUtil.export(response, workbook, "用户信息");

    }

    /**
     * 生成excel,下载文件, 不会在服务器生成实体文件
     * @param response
     * @throws IOException
     */
    @Override
    public void exportUser(HttpServletResponse response) throws IOException {
        List<User> list = userService.findAll();
        //参数title设置为null的时候,第一行大标题就不会出现,只会有第二行列标题
        EasyPoiUtil.exportExcel(list, null, "用户信息", User.class, "用户信息", response);
    }
}
