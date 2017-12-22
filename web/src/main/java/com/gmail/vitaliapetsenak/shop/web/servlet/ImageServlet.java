package com.gmail.vitaliapetsenak.shop.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        File f = new File(getServletContext().getRealPath("") + "/resources/image/" + File.separator + name);
        FileInputStream fin = new FileInputStream(f);
        ServletOutputStream outStream = response.getOutputStream();
        response.setContentType("image/jpeg");
        int i = 0;
        while (i != -1) {
            i = fin.read();
            outStream.write(i);
        }
        fin.close();
    }
}
