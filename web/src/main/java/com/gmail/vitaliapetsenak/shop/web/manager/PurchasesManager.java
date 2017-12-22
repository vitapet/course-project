package com.gmail.vitaliapetsenak.shop.web.manager;

import com.gmail.vitaliapetsenak.shop.service.GoodsService;
import com.gmail.vitaliapetsenak.shop.service.PurchasesService;
import com.gmail.vitaliapetsenak.shop.service.model.PurchasesDTO;
import com.gmail.vitaliapetsenak.shop.service.model.PurchasesStatusDTO;
import com.gmail.vitaliapetsenak.shop.service.model.UserDTO;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class PurchasesManager {
    private static PurchasesService purchasesService = PurchasesService.getInstance();
    private static final GoodsService goodsService = GoodsService.getInstance();

    public static void putListToRequest(HttpServletRequest request) {
        List<PurchasesDTO> purchases = purchasesService.getAllPurchases();
        request.setAttribute("purchases", purchases);
    }

    public static void putListForUser(HttpServletRequest request) {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        List<PurchasesDTO> purchases = purchasesService.getByUser(user.getId());
        request.setAttribute("purchases", purchases);
    }

    public static void putPurchaseToRequest(HttpServletRequest request) {
        String id = request.getParameter("id");
        PurchasesDTO purchase = purchasesService.getPurchases(Long.valueOf(id));
        List<PurchasesStatusDTO> statusList = Arrays.asList(PurchasesStatusDTO.values());
        request.setAttribute("statusList", statusList);
        request.setAttribute("purchase", purchase);
    }

    public static void putPurchaseForUser(HttpServletRequest request) {
        String id = request.getParameter("id");
        PurchasesDTO purchase = purchasesService.getPurchases(Long.valueOf(id));
        request.setAttribute("purchase", purchase);
    }

    public static void updatePurchaseStatus(HttpServletRequest request) {
        PurchasesDTO purchase = PurchasesDTO.newBuilder()
                .id(Long.valueOf(request.getParameter("id")))
                .status(PurchasesStatusDTO.valueOf(request.getParameter("status")))
                .build();
        purchasesService.updatePurchases(purchase);
        request.getSession().setAttribute("message", "Purchase was updated successfully");
    }

    public static void updatePurchase(HttpServletRequest request) {
        PurchasesDTO purchase = purchasesService.getPurchases(Long.valueOf(request.getParameter("id")));
        purchase.setCount(Long.valueOf(request.getParameter("count")));
        purchase.setAmount(BigDecimal.valueOf(purchase.getCount()).multiply(purchase.getGoods().getPrice()));
        purchasesService.updatePurchases(purchase);
        request.getSession().setAttribute("message", "Purchase was updated successfully");
    }

    public static void deletePurchase(HttpServletRequest request) {
        String id = request.getParameter("id");
        purchasesService.deletePurchases(Long.valueOf(id));
        request.getSession().setAttribute("message", "Purchase was deleted successfully");
    }
}
