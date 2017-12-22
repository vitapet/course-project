package com.gmail.vitaliapetsenak.shop.service;

import com.gmail.vitaliapetsenak.shop.repository.dao.PurchasesDAOImpl;
import com.gmail.vitaliapetsenak.shop.repository.dao.interfaces.PurchasesInterface;
import com.gmail.vitaliapetsenak.shop.repository.model.Purchases;
import com.gmail.vitaliapetsenak.shop.service.converter.Converter;
import com.gmail.vitaliapetsenak.shop.service.model.PurchasesDTO;
import com.gmail.vitaliapetsenak.shop.service.model.PurchasesStatusDTO;

import java.util.ArrayList;
import java.util.List;

public class PurchasesService {
    private static volatile PurchasesService instance;
    private PurchasesInterface purchasesDAO = PurchasesDAOImpl.getInstance();

    private PurchasesService() {
    }

    public static synchronized PurchasesService getInstance() {
        if (instance == null) {
            instance = new PurchasesService();
        }
        return instance;
    }

    public List<PurchasesDTO> getAllPurchases() {
        List<PurchasesDTO> purchasesDTOList = new ArrayList<>();
        List<Purchases> purchasesList = purchasesDAO.readAll();
        for (Purchases purchases : purchasesList) {
            purchasesDTOList.add(new PurchasesDTO(purchases));
        }
        return purchasesDTOList;
    }

    public PurchasesDTO getPurchases(Long id) {
        Purchases purchases = purchasesDAO.readById(id);
        if (purchases != null) {
            return new PurchasesDTO(purchases);
        }
        return null;
    }

    public void addPurchases(PurchasesDTO purchasesDTO) {
        Purchases purchases = Purchases.newBuilder().build();
        Converter.convert(purchasesDTO, purchases);
        purchasesDAO.create(purchases);
    }

    public void updatePurchases(PurchasesDTO purchasesDTO) {
        Purchases purchases = purchasesDAO.readById(purchasesDTO.getId());
        Converter.convert(purchasesDTO, purchases);
        purchasesDAO.update(purchases);
    }

    public void deletePurchases(Long id) {
        purchasesDAO.delete(id);
    }

    public List<PurchasesDTO> getByUser(Long userId) {
        List<PurchasesDTO> purchasesDTOList = new ArrayList<>();
        List<Purchases> purchasesList = purchasesDAO.getPurchasesByUser(userId);
        for (Purchases purchases : purchasesList) {
            purchasesDTOList.add(new PurchasesDTO(purchases));
        }
        return purchasesDTOList;
    }

    public List<PurchasesDTO> getByStatus(PurchasesStatusDTO status) {
        List<PurchasesDTO> purchasesDTOList = new ArrayList<>();
        List<Purchases> purchasesList = purchasesDAO.getPurchasesByStatus(status.getStatus());
        for (Purchases purchases : purchasesList) {
            purchasesDTOList.add(new PurchasesDTO(purchases));
        }
        return purchasesDTOList;
    }

    public List<PurchasesDTO> getByGoods(Long goodsId) {
        List<PurchasesDTO> purchasesDTOList = new ArrayList<>();
        List<Purchases> purchasesList = purchasesDAO.getPurchasesByGoods(goodsId);
        for (Purchases purchases : purchasesList) {
            purchasesDTOList.add(new PurchasesDTO(purchases));
        }
        return purchasesDTOList;
    }
}
