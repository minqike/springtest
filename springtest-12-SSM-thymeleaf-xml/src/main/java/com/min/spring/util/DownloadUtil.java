package com.min.spring.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class DownloadUtil {

    //设置下载文件的response的格式和文件名
    public static HttpServletResponse setResponseForDownload(HttpServletResponse response, String filename) {
        //配置response内容
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        return response;
    }

    //转换文件名,防止下载的时候文件名是乱码
    public static String filenameEncode(HttpServletRequest request, String filename) throws UnsupportedEncodingException {
        String userAgent = request.getHeader("User-Agent");
        if (/* IE 8 至 IE 10 */
                userAgent.toUpperCase().contains("MSIE") ||
                        /* IE 11 */
                        userAgent.contains("Trident/7.0")) {
            filename = URLEncoder.encode(filename, "UTF-8");
        } else if (userAgent.toUpperCase().contains("MOZILLA") ||
                userAgent.toUpperCase().contains("CHROME")) {
            filename = new String(filename.getBytes(), "ISO-8859-1");
        } else {
            filename = URLEncoder.encode(filename, "UTF-8");
        }
        return filename;
    }
}
