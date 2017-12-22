package com.gmail.vitaliapetsenak.shop.web.manager;

import com.gmail.vitaliapetsenak.shop.service.CommentService;
import com.gmail.vitaliapetsenak.shop.service.NewsService;
import com.gmail.vitaliapetsenak.shop.service.model.CommentDTO;
import com.gmail.vitaliapetsenak.shop.service.model.NewsDTO;
import com.gmail.vitaliapetsenak.shop.service.model.RoleDTO;
import com.gmail.vitaliapetsenak.shop.service.model.UserDTO;
import com.gmail.vitaliapetsenak.shop.web.listener.ProgressUploadListener;
import com.gmail.vitaliapetsenak.shop.web.model.Command;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsManager {
    private static NewsService newsService = NewsService.getInstance();
    private static CommentService commentService = CommentService.getInstance();

    public static Long setNewsFromRequest(HttpServletRequest request, ServletContext context, Command command) {
        Map<String, String> parameters = new HashMap<>();
        NewsDTO news = null;
        String path;
        File file;
        try {
            FileItem fileItem = parseRequest(parameters, request);
            switch (command) {
                case ADD:
                    news = fillNews(parameters, command, request);
                    newsService.addNews(news);
                    news = newsService.readByName(news.getName());
                    path = context.getRealPath("/resources/image/" + File.separator + news.getId() + ".jpg");
                    file = new File(path);
                    fileItem.write(file);
                    news.setImage(news.getId() + ".jpg");
                    break;
                case UPDATE:
                    news = fillNews(parameters, command, request);
                    if (fileItem.getSize() != 0) {
                        path = context.getRealPath("/resources/image/" + File.separator + news.getId() + ".jpg");
                        file = new File(path);
                        file.delete();
                        file = new File(path);
                        fileItem.write(file);
                    }
                    break;
            }
            newsService.updateNews(news);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return news.getId();
    }

    public static NewsDTO putNewsToRequest(HttpServletRequest request) {
        String id = request.getParameter("id");
        NewsDTO news = newsService.getNews(Long.valueOf(id));
        request.setAttribute("news", news);
        return news;
    }

    public static void putNewsWithComments(HttpServletRequest request) {
        NewsDTO news = putNewsToRequest(request);
        List<CommentDTO> comments = commentService.getByNews(news.getId());
        request.setAttribute("comments", comments);
    }

    public static void deleteNews(HttpServletRequest request, ServletContext context) {
        String id = request.getParameter("id");
        UserDTO curUser = (UserDTO) request.getSession().getAttribute("user");
        if (id == null) {
            request.getSession().setAttribute("message", "Choose news.");
        } else {
            NewsDTO news = newsService.getNews(Long.valueOf(id));
            if (news.getUser().getRole().equals(RoleDTO.ROOT) && curUser.getRole().equals(RoleDTO.ADMIN)) {
                request.getSession().setAttribute("message", "You don't have permission to delete this news. Choose other.");
            } else {
                File file = new File(context.getRealPath("/resources/image/" + news.getId() + ".jpg"));
                file.delete();
                newsService.deleteNews(Long.valueOf(id));
                request.getSession().setAttribute("message", "News was removed successfully.");
            }
        }
    }

    public static void putListToRequest(HttpServletRequest request) {
        List<NewsDTO> newsList = newsService.getAllNews();
        request.setAttribute("newsList", newsList);
    }

    public static String deleteComment(HttpServletRequest request) {
        String id = request.getParameter("id");
        String newsId = request.getParameter("newsId");
        if (id == null) {
            request.getSession().setAttribute("message", "Choose comment.");
        } else {
            commentService.deleteComment(Long.valueOf(id));
            request.getSession().setAttribute("message", "Comment was deleted successfully");
        }
        return newsId;
    }

    public static Long addComment(HttpServletRequest request) {
        String newsId = request.getParameter("newsId");
        NewsDTO news = newsService.getNews(Long.valueOf(newsId));
        CommentDTO comment = CommentDTO.newBuilder()
                .text(request.getParameter("text"))
                .user((UserDTO) request.getSession().getAttribute("user"))
                .news(news)
                .date(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        commentService.addComment(comment);
        return news.getId();
    }

    private static NewsDTO fillNews(Map<String, String> parameters, Command command, HttpServletRequest request) {
        NewsDTO news = NewsDTO.newBuilder()
                .timestamp(Timestamp.valueOf(LocalDateTime.now()))
                .name(parameters.get("name"))
                .description(parameters.get("description"))
                .build();
        switch (command) {
            case ADD:
                news.setUser((UserDTO) request.getSession().getAttribute("user"));
                news.setAuthor(parameters.get("author"));
                break;
            case UPDATE:
                news.setId(Long.valueOf(parameters.get("id")));
                break;
        }
        return news;
    }

    private static FileItem parseRequest(Map<String, String> parameters, HttpServletRequest request) throws FileUploadException, UnsupportedEncodingException {
        FileItem fileItem = null;
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        ProgressUploadListener uploadListener = new ProgressUploadListener();
        upload.setProgressListener(uploadListener);
        List<FileItem> parts = upload.parseRequest(request);
        for (FileItem item : parts) {
            if (item.isFormField()) {
                parameters.put(item.getFieldName(), item.getString("UTF-8").trim());
            } else {
                fileItem = item;
            }
        }
        return fileItem;
    }
}
