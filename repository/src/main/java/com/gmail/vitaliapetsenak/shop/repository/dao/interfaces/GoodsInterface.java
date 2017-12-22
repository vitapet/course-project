package com.gmail.vitaliapetsenak.shop.repository.dao.interfaces;

import com.gmail.vitaliapetsenak.shop.repository.model.Goods;
import com.gmail.vitaliapetsenak.shop.repository.model.GoodsCategory;
import com.gmail.vitaliapetsenak.shop.repository.model.GoodsIsDeleted;

import java.util.List;

public interface GoodsInterface extends GenericDAO<Goods> {
    List<Goods> getByCategory(GoodsCategory category, GoodsIsDeleted isDeleted);

    List<Goods> getByCategory(GoodsCategory category);

    List<Goods> getNotDeleted();
}
