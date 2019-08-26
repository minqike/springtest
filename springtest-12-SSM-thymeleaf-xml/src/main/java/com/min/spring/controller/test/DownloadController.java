package com.min.spring.controller.test;

import com.min.spring.util.DownloadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class DownloadController {
    @RequestMapping(value = "test/preDownload", method = RequestMethod.POST)
    @ResponseBody
    public String preDownload(
            @RequestParam(required = false) String searchString,
            HttpServletRequest request,
            HttpServletResponse response) {
        return "OK";
    }

    @RequestMapping(value = "test/postDownload", method = RequestMethod.POST)
    @ResponseBody
    public void postDownload(
            @RequestParam(required = false) String searchString,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        //下载文件名的设置 方式1:自定义文件名
        String filename = "测试post.xml";

        //下载文件名的设置 方式2:采用传递过来的参数作为文件名
        // String filename = request.getParameter("filename");

        //转换文件名,防止下载的时候文件名是乱码
        filename = DownloadUtil.filenameEncode(request, filename);
        //设置下载文件的response的格式和文件名
        response = DownloadUtil.setResponseForDownload(response, filename);

        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write("这是测试文件post\n".getBytes());
        outputStream.write(("这个是传送过来的参数:"+searchString).getBytes());
        outputStream.flush();
        outputStream.close();

    }

    @RequestMapping(value = "test/getDownload", method = RequestMethod.GET)
    @ResponseBody
    public void getDownload(
            @RequestParam(required = false) String searchString,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        //下载文件名的设置 方式1:自定义文件名
        String filename = "测试get.txt";

        //下载文件名的设置 方式2:采用传递过来的参数作为文件名
        // String filename = request.getParameter("filename");

        //转换文件名,防止下载的时候文件名是乱码
        filename = DownloadUtil.filenameEncode(request, filename);
        //设置下载文件的response的格式和文件名
        response = DownloadUtil.setResponseForDownload(response, filename);

        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write("这是测试文件get".getBytes());
        outputStream.flush();
        outputStream.close();

    }

}
