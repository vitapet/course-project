package com.gmail.vitaliapetsenak.shop.repository.dao.interfaces;

import com.gmail.vitaliapetsenak.shop.repository.model.Purchases;
import com.gmail.vitaliapetsenak.shop.repository.model.PurchasesStatus;

import java.util.List;

public interface PurchasesInterface extends GenericDAO<Purchases> {
    List<Purchases> getPurchasesByUser(Long userId);

    List<Purchases> getPurchasesByStatus(PurchasesStatus status);

    List<Purchases> getPurchasesByGoods(Long goodsId);
}
