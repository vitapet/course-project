package com.gmail.vitaliapetsenak.shop.web.servlet.admin.news;

import com.gmail.vitaliapetsenak.shop.web.manager.NewsManager;
import com.gmail.vitaliapetsenak.shop.web.model.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminUpdateNewsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String id = uploadFile(request, response);
        Long id = NewsManager.setNewsFromRequest(request, getServletContext(), Command.UPDATE);
        request.getSession().setAttribute("message", "News was updated successfully");
        response.sendRedirect(request.getContextPath() + "/admin/news/selected?id=" + id);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NewsManager.putNewsToRequest(request);
        request.getRequestDispatcher("/WEB-INF/jsp/admin/admin_news_update.jsp").forward(request, response);
    }

//    private String uploadFile(HttpServletRequest request, HttpServletResponse response) {
//        Map<String, String> parameters = new HashMap<>();
//        FileItem fileItem = null;
//        try {
//            List<FileItem> parts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
//            for (FileItem item : parts) {
//                if (item.isFormField()) {
//                    parameters.put(item.getFieldName(), item.getString("UTF-8").trim());
//                } else {
//                    fileItem = item;
//                }
//            }
//            NewsDTO news = NewsDTO.newBuilder()
//                    .id(Long.valueOf(parameters.get("id")))
//                    .timestamp(Timestamp.valueOf(LocalDateTime.now()))
//                    .name(parameters.get("name"))
//                    .description(parameters.get("description"))
//                    .build();
//            if (fileItem.getSize() != 0) {
//                String path = getServletContext().getRealPath("/resources/image/" + File.separator + news.getId() + ".jpg");
//                File file = new File(path);
//                file.delete();
//                file = new File(path);
//                fileItem.write(file);
//            }
//            newsService.updateNews(news);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return parameters.get("id");
//    }
}
