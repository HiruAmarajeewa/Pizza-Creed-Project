package lk.nibm.ead2.web.service.impl;

import lk.nibm.ead2.web.model.Basket;
import lk.nibm.ead2.web.model.BasketItem;
import lk.nibm.ead2.web.model.BasketItemDTO;
import lk.nibm.ead2.web.model.Product;
import lk.nibm.ead2.web.repository.BasketRepository;
import lk.nibm.ead2.web.service.IBasketService;
import lk.nibm.ead2.web.service.IProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BasketService implements IBasketService {

    @Autowired
    BasketRepository basketRepository;
    @Autowired
    IProductService productservice;

    @Override
    public Basket save(Basket basket) {
        Basket dto = null;
        if (basket.getId() == null) {
            dto = new Basket();
        } else {
            Optional<Basket> optionalBasket = basketRepository.findById(basket.getId());
            if (optionalBasket.isPresent()) {
                dto = optionalBasket.get();
            }
        }
        BeanUtils.copyProperties(basket, dto);
        dto = basketRepository.save(dto);
        basket.setId(dto.getId());
        return basket;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Basket> basket = basketRepository.findById(id);
        if (basket.isPresent()) {
            basketRepository.delete(basket.get());
        } else {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Basket saveAll(List<BasketItemDTO> basketItemDTOS) {

        List<BasketItem> basketItemList = new ArrayList<>();

        Basket basket = Basket.builder()
                .basketItems(basketItemList)
                .build();

        basketItemDTOS.forEach(basketItem -> {
            if (basketItem.getProduct() != null) {
                Product product = productservice.find(basketItem.getProduct());
                BasketItem item = BasketItem.builder()
                        .product(product)
                        .quantity(basketItem.getQuantity())
                        .basket(basket)
                        .price(basketItem.getQuantity() * product.getPrice())
                        .build();
                basketItemList.add(item);
            }
        });


        if (!basketItemList.isEmpty()) {
            basket.setAmount(basketItemList.stream().mapToDouble(BasketItem::getPrice).sum());
            basketRepository.save(basket);
        }

        return null;
    }
}
