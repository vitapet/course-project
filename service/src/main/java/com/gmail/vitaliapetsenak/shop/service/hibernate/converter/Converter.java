package com.gmail.vitaliapetsenak.shop.service.hibernate.converter;

import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.*;
import com.gmail.vitaliapetsenak.shop.service.hibernate.model.*;

public class Converter {
    public static void convert(UserDTO userDTO, User user) {
        if (userDTO.getLogin() != null) {
            user.setLogin(userDTO.getLogin());
        }
        if (userDTO.getPassword() != null) {
            user.setPassword(userDTO.getPassword());
        }
        if (userDTO.getStatus() != null) {
            user.setStatus(userDTO.getStatus().getStatus());
        }
        if (userDTO.getRole() != null) {
            user.setRole(userDTO.getRole().getRole());
        }
        if (userDTO.getFirstName() != null) {
            user.setFirstName(userDTO.getFirstName());
        }
        if (userDTO.getSurname() != null) {
            user.setSurname(userDTO.getSurname());
        }
        if (userDTO.getBirthDate() != null) {
            user.setBirthDate(userDTO.getBirthDate());
        }
        if (userDTO.getPhone() != null) {
            user.setPhone(userDTO.getPhone());
        }
        if (userDTO.getCountry() != null) {
            user.setCountry(userDTO.getCountry());
        }
        if (userDTO.getCity() != null) {
            user.setCity(userDTO.getCity());
        }
        if (userDTO.getStreet() != null) {
            user.setStreet(userDTO.getStreet());
        }
        if (userDTO.getHouse() != null) {
            user.setHouse(userDTO.getHouse());
        }
        if (userDTO.getBlock() != null) {
            user.setBlock(userDTO.getBlock());
        }
        if (userDTO.getApartment() != null) {
            user.setApartment(userDTO.getApartment());
        }
    }

    public static void convert(ProductDTO productDTO, Product product) {
        if (productDTO.getName() != null) {
            product.setName(productDTO.getName());
        }
        if (productDTO.getDescription() != null) {
            product.setDescription(productDTO.getDescription());
        }
        if (productDTO.getCategory() != null) {
            product.setCategory(productDTO.getCategory().getCategory());
        }
        if (productDTO.getCount() != null) {
            product.setCount(productDTO.getCount());
        }
        if (productDTO.getPrice() != null) {
            product.setPrice(productDTO.getPrice());
        }
        if (productDTO.getStatus() != null) {
            product.setStatus(productDTO.getStatus().getStatus());
        }
    }

    public static void convert(NewsDTO newsDTO, News news) {
        if (newsDTO.getUser() != null) {
            news.setUser(getUser(newsDTO.getUser()));
        }
        if (newsDTO.getAuthor() != null) {
            news.setAuthor(newsDTO.getAuthor());
        }
        if (newsDTO.getName() != null) {
            news.setName(newsDTO.getName());
        }
        if (newsDTO.getDescription() != null) {
            news.setDescription(newsDTO.getDescription());
        }
        if (newsDTO.getTimestamp() != null) {
            news.setTimestamp(newsDTO.getTimestamp());
        }
        if (newsDTO.getImage() != null) {
            news.setImage(newsDTO.getImage());
        }
    }

    private static User getUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        convert(userDTO, user);
        return user;
    }

    public static void convert(OrderDTO orderDTO, Order order) {
        if (orderDTO.getUser() != null) {
            order.setUser(getUser(orderDTO.getUser()));
        }
        if (orderDTO.getStatus() != null) {
            order.setStatus(orderDTO.getStatus().getStatus());
        }
        if (orderDTO.getTimestamp() != null) {
            order.setTimestamp(orderDTO.getTimestamp());
        }
    }

    public static void convert(OrderProductDTO orderProductDTO, OrderProduct orderProduct) {
        if (orderProductDTO.getOrder() != null) {
            orderProduct.setOrder(orderProductDTO.getOrder());
        }
        if (orderProductDTO.getProduct() != null) {
            orderProduct.setProduct(orderProductDTO.getProduct());
        }
        if (orderProductDTO.getCount() != null) {
            orderProduct.setCount(orderProductDTO.getCount());
        }
        if (orderProductDTO.getAmount() != null) {
            orderProduct.setAmount(orderProductDTO.getAmount());
        }
    }

    public static void convert(CommentDTO commentDTO, Comment comment) {
        if (commentDTO.getText() != null) {
            comment.setText(commentDTO.getText());
        }
        if (commentDTO.getUser() != null) {
            comment.setUser(getUser(commentDTO.getUser()));
        }
        if (commentDTO.getNews() != null) {
            News news = new News();
            news.setId(commentDTO.getNews().getId());
            convert(commentDTO.getNews(), news);
            comment.setNews(news);
        }
        if (commentDTO.getTimestamp() != null) {
            comment.setTimestamp(commentDTO.getTimestamp());
        }
    }
}
