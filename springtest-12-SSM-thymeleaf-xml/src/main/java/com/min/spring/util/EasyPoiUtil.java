package com.min.spring.util;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class EasyPoiUtil {

    /**
     * 模板路径
     */
    private static final String TEMPLATE_PATH = "/";

    /**
     * 生成excel对象
     *
     * @param params       模板导出参数设置
     * @param data         模板导出数据
     * @param templateName 模板名称
     * @return workBook对象
     * @throws Exception 异常抛出
     */
    public static Workbook getWorkbook(TemplateExportParams params, Map<String, Object> data, String templateName) throws Exception {
        String templatePath = TEMPLATE_PATH + templateName;
        File file = getTemplateFile(templatePath);
        params.setTemplateUrl(file.getAbsolutePath());
        Workbook book = ExcelExportUtil.exportExcel(params, data);
        if (file.exists()) {
            file.delete();
        }
        return book;
    }

    /**
     * 导出excel对象
     *
     * @param response httpResponse对象
     * @param workbook workBook对象
     * @param fileName 导出文件名
     * @throws Exception 异常抛出
     */
    public static void export(HttpServletResponse response, Workbook workbook, String fileName) throws Exception {

        downLoadExcel(fileName, response, workbook);

//        response.reset();
//        response.setContentType("application/x-msdownload");
//        fileName = fileName + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//
//        response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("gb2312"), "ISO-8859-1") + ".xlsx");
//        ServletOutputStream outStream = null;
//        try {
//            outStream = response.getOutputStream();
//            workbook.write(outStream);
//        } finally {
//            workbook.close();
//            outStream.close();
//        }
    }

    /**
     * 获取模板文件--获取到的文件为临时文件，用完后需要手动删除
     * <p>由于springboot打包成jar之后，不能以绝对路径的形式读取模板文件，故此处将模板文件以临时文件的形式写到磁盘中，用完请手动删除</p>
     *
     * @param templatePath 模板路径
     * @return 模板文件
     * @throws Exception 异常抛出
     */
    public static File getTemplateFile(String templatePath) throws Exception {
        File file = File.createTempFile("temp", null);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(templatePath);
        if (resources.length == 1) {
            InputStream inputStream = resources[0].getInputStream();
            inputStreamToFile(inputStream, file);
        } else {
            System.out.println("请检查模板文件是否存在");
        }
        return file;
    }

    /**
     * InputStream 转file
     *
     * @param ins  输入流
     * @param file 目标文件
     */
    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * excel 导出
     *
     * @param list           数据
     * @param title          标题
     * @param sheetName      sheet名称
     * @param pojoClass      pojo类型
     * @param fileName       文件名称
     * @param isCreateHeader 是否创建表头
     * @param response
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, boolean isCreateHeader, HttpServletResponse response) throws IOException {
        ExportParams exportParams = new ExportParams(title, sheetName, ExcelType.XSSF);
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(list, pojoClass, fileName, response, exportParams);
    }

    /**
     * excel 导出
     *
     * @param list      数据
     * @param title     标题
     * @param sheetName sheet名称
     * @param pojoClass pojo类型
     * @param fileName  文件名称
     * @param response
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, HttpServletResponse response) throws IOException {
        ExportParams exportParams = new ExportParams(title, sheetName, ExcelType.XSSF);
        exportParams.setCreateHeadRows(true);
        defaultExport(list, pojoClass, fileName, response,exportParams );
    }

    /**
     * excel 导出
     *
     * @param list         数据
     * @param pojoClass    pojo类型
     * @param fileName     文件名称
     * @param response
     * @param exportParams 导出参数
     */
    public static void exportExcel(List<?> list, Class<?> pojoClass, String fileName, ExportParams exportParams, HttpServletResponse response) throws IOException {
        defaultExport(list, pojoClass, fileName, response, exportParams);
    }

    /**
     * excel 导出
     *
     * @param list     数据
     * @param fileName 文件名称
     * @param response
     */
    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response) throws IOException {
        defaultExport(list, fileName, response);
    }

    /**
     * 默认的 excel 导出
     *
     * @param list         数据
     * @param pojoClass    pojo类型
     * @param fileName     文件名称
     * @param response
     * @param exportParams 导出参数
     */
    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response, ExportParams exportParams) throws IOException {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
        downLoadExcel(fileName, response, workbook);
    }

    /**
     * 默认的 excel 导出
     *
     * @param list     数据
     * @param fileName 文件名称
     * @param response
     */
    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) throws IOException {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
        downLoadExcel(fileName, response, workbook);
    }

    /**
     * 下载
     *
     * @param fileName 文件名称
     * @param response
     * @param workbook excel数据
     */
    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) throws IOException {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + "." + ExcelTypeEnum.XLSX.getValue(), "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * excel 导入
     *
     * @param filePath   excel文件路径
     * @param titleRows  标题行
     * @param headerRows 表头行
     * @param pojoClass  pojo类型
     * @param <T>
     * @return
     */
    public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass) throws IOException {
        if (StringUtils.isBlank(filePath)) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        params.setNeedSave(true);
        params.setSaveUrl("/excel/");
        try {
            return ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        } catch (NoSuchElementException e) {
            throw new IOException("模板不能为空");
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * excel 导入
     *
     * @param file      excel文件
     * @param pojoClass pojo类型
     * @param <T>
     * @return
     */
    public static <T> List<T> importExcel(MultipartFile file, Class<T> pojoClass) throws IOException {
        return importExcel(file, 1, 1, pojoClass);
    }

    /**
     * excel 导入
     *
     * @param file       excel文件
     * @param titleRows  标题行
     * @param headerRows 表头行
     * @param pojoClass  pojo类型
     * @param <T>
     * @return
     */
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass) throws IOException {
        return importExcel(file, titleRows, headerRows, false, pojoClass);
    }

    /**
     * excel 导入
     *
     * @param file       上传的文件
     * @param titleRows  标题行
     * @param headerRows 表头行
     * @param needVerfiy 是否检验excel内容
     * @param pojoClass  pojo类型
     * @param <T>
     * @return
     */
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, boolean needVerfiy, Class<T> pojoClass) throws IOException {
        if (file == null) {
            return null;
        }
        try {
            return importExcel(file.getInputStream(), titleRows, headerRows, needVerfiy, pojoClass);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * excel 导入
     *
     * @param inputStream 文件输入流
     * @param titleRows   标题行
     * @param headerRows  表头行
     * @param needVerfiy  是否检验excel内容
     * @param pojoClass   pojo类型
     * @param <T>
     * @return
     */
    public static <T> List<T> importExcel(InputStream inputStream, Integer titleRows, Integer headerRows, boolean needVerfiy, Class<T> pojoClass) throws IOException {
        if (inputStream == null) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        params.setSaveUrl("/excel/");
        params.setNeedSave(true);
//        params.setNeedVerfiy(needVerfiy);
        try {
            return ExcelImportUtil.importExcel(inputStream, pojoClass, params);
        } catch (NoSuchElementException e) {
            throw new IOException("excel文件不能为空");
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * Excel 类型枚举
     */
    enum ExcelTypeEnum {
        XLS("xls"), XLSX("xlsx");
        private String value;

        ExcelTypeEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}