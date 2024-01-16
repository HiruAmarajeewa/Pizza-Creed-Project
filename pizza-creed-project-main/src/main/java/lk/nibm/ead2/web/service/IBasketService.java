package lk.nibm.ead2.web.service;

import lk.nibm.ead2.web.model.Basket;
import lk.nibm.ead2.web.model.BasketItemDTO;

import java.util.List;

public interface IBasketService {
    Basket save(Basket basket);

    boolean delete(Long id);

    Basket saveAll(List<BasketItemDTO> basketItemDTOS);
}
