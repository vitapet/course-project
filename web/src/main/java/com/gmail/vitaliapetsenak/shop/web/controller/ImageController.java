package com.gmail.vitaliapetsenak.shop.web.controller;

import com.gmail.vitaliapetsenak.shop.service.INewsFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;

@Controller
public class ImageController {

    @Autowired
    private INewsFileService newsFileService;

    @GetMapping(value = "/image/{id}")
    public void getImage(HttpServletResponse response, @PathVariable Long id) throws IOException {
        File file = newsFileService.getFileById(id);

        if (!file.exists()) {
            String errorMessage = "Sorry. The file you are looking does not exist.";
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        }

        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        response.setContentType(mimeType);

        response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
        response.setContentLength((int) file.length());
        try (
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStream inputStream = new BufferedInputStream(fileInputStream)
        ) {
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        }
    }
}
