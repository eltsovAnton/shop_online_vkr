package com.gmail.shop.ecommerce.service;

import com.gmail.shop.ecommerce.domain.Clothe;
import com.gmail.shop.ecommerce.dto.request.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClotheService {

    Clothe getClotheById(Long clotheId);

    List<Clothe> getPopularClothes();

    Page<Clothe> getClothesByFilterParams(SearchRequest searchRequest, Pageable pageable);

    Page<Clothe> searchClothes(SearchRequest searchRequest, Pageable pageable);
}
