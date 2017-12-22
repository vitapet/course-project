package com.gmail.vitaliapetsenak.shop.service;


import com.gmail.vitaliapetsenak.shop.service.model.RoleDTO;
import com.gmail.vitaliapetsenak.shop.service.model.UserDTO;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        UserService userService = UserService.getInstance();
        NewsService newsService = NewsService.getInstance();
        GoodsService goodsService = GoodsService.getInstance();
        PurchasesService purchasesService = PurchasesService.getInstance();
        System.out.println(userService.getUser(1L));
        UserDTO userDTO = UserDTO.newBuilder()
                .id(1L)
                .role(RoleDTO.USER)
                .build();
        userService.updateUserInfo(userDTO);
        System.out.println(userDTO);
        System.out.println(userService.getUser(1L));
        System.out.println(purchasesService.getAllPurchases());
        System.out.println(purchasesService.getPurchases(1L));
        System.out.println();
        System.out.println(newsService.getAllNews());
        System.out.println(newsService.getNews(1L));
        System.out.println();
        System.out.println(goodsService.getAllGoods());
        System.out.println(goodsService.getGoods(1L));
    }
}
