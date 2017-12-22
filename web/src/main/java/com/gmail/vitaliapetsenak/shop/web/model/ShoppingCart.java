package com.gmail.vitaliapetsenak.shop.web.model;

import com.gmail.vitaliapetsenak.shop.service.PurchasesService;
import com.gmail.vitaliapetsenak.shop.service.model.PurchasesDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final PurchasesService purchasesService = PurchasesService.getInstance();
    private List<PurchasesDTO> cart;
    private BigDecimal totalCost;
    private Integer count;

    public ShoppingCart() {
        cart = new ArrayList<>();
        totalCost = BigDecimal.ZERO;
        count = 0;
    }

    public List<PurchasesDTO> getCart() {
        return cart;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void updateTotalCost() {
        totalCost = BigDecimal.ZERO;
        for (PurchasesDTO purchase : cart) {
            updateAmount(purchase);
            totalCost = totalCost.add(purchase.getAmount());
        }
    }

    public void addToCart(PurchasesDTO purchase) {
        purchase.setId(Long.valueOf(count));
        purchase.setAmount(BigDecimal.valueOf(purchase.getCount()).multiply(purchase.getGoods().getPrice()));
        cart.add(purchase);
        totalCost = totalCost.add(purchase.getAmount());
        count++;
    }

    public void deleteFromCart(Long purchaseId) {
        PurchasesDTO purchaseDel = null;
        for (PurchasesDTO purchase : cart) {
            if (purchase.getId().equals(purchaseId)) {
                purchaseDel = purchase;
            }
        }
        totalCost = totalCost.subtract(BigDecimal.valueOf(purchaseDel.getCount()).multiply(purchaseDel.getGoods().getPrice()));
        cart.remove(purchaseDel);
    }

    public void deleteFromCart(PurchasesDTO purchaseDel) {
        totalCost = totalCost.subtract(BigDecimal.valueOf(purchaseDel.getCount()).multiply(purchaseDel.getGoods().getPrice()));
        cart.remove(purchaseDel);
    }

    public void confirm() {
        for (PurchasesDTO purchase : cart) {
            purchasesService.addPurchases(purchase);
        }
        cart.clear();
        totalCost = BigDecimal.ZERO;
        count = 0;
    }

    public Integer getCount() {
        return count;
    }

    public PurchasesDTO getPurchaseById(Integer id) {
        for (PurchasesDTO purchase : cart) {
            if (purchase.getId().equals(id.longValue())) {
                return purchase;
            }
        }
        return null;
    }

    private void updateAmount(PurchasesDTO purchase) {
        purchase.setAmount(BigDecimal.valueOf(purchase.getCount()).multiply(purchase.getGoods().getPrice()));
    }
}
