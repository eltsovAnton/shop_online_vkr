package com.gmail.shop.ecommerce.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class SearchRequest {
    private List<String> genders;
    private List<String> type;
    private Integer price = 0;
    private String searchType;
    private String text;
}
