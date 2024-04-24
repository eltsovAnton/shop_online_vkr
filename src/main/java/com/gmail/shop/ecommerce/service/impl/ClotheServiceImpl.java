package com.gmail.shop.ecommerce.service.impl;

import com.gmail.shop.ecommerce.constants.ErrorMessage;
import com.gmail.shop.ecommerce.domain.Clothe;
import com.gmail.shop.ecommerce.dto.request.SearchRequest;
import com.gmail.shop.ecommerce.repository.ClotheRepository;
import com.gmail.shop.ecommerce.service.ClotheService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClotheServiceImpl implements ClotheService {

    private final ClotheRepository clotheRepository;
    private final ModelMapper modelMapper;

    @Override
    public Clothe getClotheById(Long clotheId) {
        return clotheRepository.findById(clotheId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.CLOTHE_NOT_FOUND));
    }

    @Override
    public List<Clothe> getPopularClothes() {
        List<Long> clotheIds = Arrays.asList(2L, 3L);
        return clotheRepository.findByIdIn(clotheIds);
    }

    @Override
    public Page<Clothe> getClothesByFilterParams(SearchRequest request, Pageable pageable) {
        Integer startingPrice = request.getPrice();
        Integer endingPrice = startingPrice + (startingPrice == 0 ? 50000 : 50);
        return clotheRepository.getClothesByFilterParams(
                request.getType(),
                startingPrice,
                endingPrice,
                pageable);
    }

    @Override
    public Page<Clothe> searchClothes(SearchRequest request, Pageable pageable) {
        return clotheRepository.searchClothes(request.getSearchType(), request.getText(), pageable);
    }
}
