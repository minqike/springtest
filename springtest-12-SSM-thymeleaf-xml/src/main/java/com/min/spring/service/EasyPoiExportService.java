package com.min.spring.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface EasyPoiExportService {

    public void exportUserUseTemplate(HttpServletResponse response) throws Exception;

    public void exportUser(HttpServletResponse response) throws IOException;

}
