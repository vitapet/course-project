package com.gmail.vitaliapetsenak.shop.web.servlet.admin.news;

import com.gmail.vitaliapetsenak.shop.web.manager.NewsManager;
import com.gmail.vitaliapetsenak.shop.web.model.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminAddNewsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        uploadFile(request, response);
        NewsManager.setNewsFromRequest(request, getServletContext(), Command.ADD);
        request.getSession().setAttribute("message", "News was added successfully");
        response.sendRedirect(request.getContextPath() + "/admin/news");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/admin/admin_news_add.jsp").forward(request, response);
    }


//    private void uploadFile(HttpServletRequest request, HttpServletResponse response) {
//        Map<String, String> parameters = new HashMap<>();
//        FileItem fileItem = null;
//        FileItemFactory factory = new DiskFileItemFactory();
//        ServletFileUpload upload = new ServletFileUpload(factory);
//        ProgressUploadListener uploadListener = new ProgressUploadListener();
//        upload.setProgressListener(uploadListener);
//        try {
//            List<FileItem> parts = upload.parseRequest(request);
//            for (FileItem item : parts) {
//                if (item.isFormField()) {
//                    parameters.put(item.getFieldName(), item.getString("UTF-8").trim());
//                } else {
//                    fileItem = item;
//                }
//            }
//            NewsDTO news = NewsDTO.newBuilder()
//                    .timestamp(Timestamp.valueOf(LocalDateTime.now()))
//                    .name(parameters.get("name"))
//                    .description(parameters.get("description"))
//                    .author(parameters.get("author"))
//                    .user((UserDTO) request.getSession().getAttribute("user"))
//                    .build();
//            newsService.addNews(news);
//            news = newsService.readByName(news.getName());
//            String path = getServletContext().getRealPath("/resources/image/" + File.separator + news.getId() + ".jpg");
//            File file = new File(path);
//            fileItem.write(file);
//            news.setImage(news.getId() + ".jpg");
//            newsService.updateNews(news);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
