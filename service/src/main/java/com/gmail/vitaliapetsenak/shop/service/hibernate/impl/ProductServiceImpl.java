package com.gmail.vitaliapetsenak.shop.service.hibernate.impl;

import com.gmail.vitaliapetsenak.shop.repository.dao.GoodsDAOImpl;
import com.gmail.vitaliapetsenak.shop.repository.dao.interfaces.GoodsInterface;
import com.gmail.vitaliapetsenak.shop.repository.model.Goods;
import com.gmail.vitaliapetsenak.shop.repository.model.GoodsIsDeleted;
import com.gmail.vitaliapetsenak.shop.service.converter.Converter;
import com.gmail.vitaliapetsenak.shop.service.model.CategoryDTO;
import com.gmail.vitaliapetsenak.shop.service.model.GoodsDTO;
import com.gmail.vitaliapetsenak.shop.service.model.GoodsIsDeletedDTO;
import com.gmail.vitaliapetsenak.shop.service.model.PurchasesDTO;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl {

    private static volatile ProductServiceImpl instance;
    private GoodsInterface goodsDAO = GoodsDAOImpl.getInstance();
    private OrderServiceImpl purchasesService = OrderServiceImpl.getInstance();

    private ProductServiceImpl() {
    }

    public static synchronized ProductServiceImpl getInstance() {
        if (instance == null) {
            instance = new ProductServiceImpl();
        }
        return instance;
    }

    public List<GoodsDTO> getAllGoods() {
        List<GoodsDTO> goodsDTOList = new ArrayList<>();
        List<Goods> goodsList = goodsDAO.readAll();
        for (Goods goods : goodsList) {
            goodsDTOList.add(new GoodsDTO(goods));
        }
        return goodsDTOList;
    }

    public GoodsDTO getGoods(Long id) {
        Goods goods = goodsDAO.readById(id);
        if (goods != null) {
            return new GoodsDTO(goods);
        }
        return null;
    }

    public void addGoods(GoodsDTO goodsDTO) {
        Goods goods = Goods.newBuilder().build();
        Converter.convert(goodsDTO, goods);
        goodsDAO.create(goods);
    }

    public void updateGoodsInfo(GoodsDTO goodsDTO) {
        Goods goods = goodsDAO.readById(goodsDTO.getId());
        Converter.convert(goodsDTO, goods);
        goodsDAO.update(goods);
    }

    public void deleteGoods(Long id) {
        List<PurchasesDTO> purchases = purchasesService.getByGoods(id);
        if (purchases.isEmpty()) {
            goodsDAO.delete(id);
        } else {
            Goods goods = goodsDAO.readById(id);
            goods.setIsDeleted(GoodsIsDeleted.DELETED);
            goodsDAO.update(goods);
        }
    }

    public List<GoodsDTO> getByCategoryIsDeleted(CategoryDTO category, GoodsIsDeletedDTO isDeleted) {
        List<GoodsDTO> goodsDTOList = new ArrayList<>();
        List<Goods> goodsList = goodsDAO.getByCategory(category.getCategory(), isDeleted.getIsDeleted());
        for (Goods goods : goodsList) {
            goodsDTOList.add(new GoodsDTO(goods));
        }
        return goodsDTOList;
    }

    public List<GoodsDTO> getAllByCategory(CategoryDTO category) {
        List<GoodsDTO> goodsDTOList = new ArrayList<>();
        List<Goods> goodsList = goodsDAO.getByCategory(category.getCategory());
        for (Goods goods : goodsList) {
            goodsDTOList.add(new GoodsDTO(goods));
        }
        return goodsDTOList;
    }

    public List<GoodsDTO> getAllNotDeletedGoods() {
        List<GoodsDTO> goodsDTOList = new ArrayList<>();
        List<Goods> goodsList = goodsDAO.getNotDeleted();
        for (Goods goods : goodsList) {
            goodsDTOList.add(new GoodsDTO(goods));
        }
        return goodsDTOList;
    }
}
