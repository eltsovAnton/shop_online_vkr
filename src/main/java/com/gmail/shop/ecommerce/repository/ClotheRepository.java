package com.gmail.shop.ecommerce.repository;

import com.gmail.shop.ecommerce.domain.Clothe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClotheRepository extends JpaRepository<Clothe, Long> {

    List<Clothe> findByIdIn(List<Long> perfumesIds);

    Page<Clothe> findAllByOrderByPriceAsc(Pageable pageable);

    @Query("SELECT clothe FROM Clothe clothe WHERE " +
            "(CASE " +
            "   WHEN :searchType = 'clotheTitle' THEN UPPER(clothe.clotheTitle) " +
            "   WHEN :searchType = 'type' THEN UPPER(clothe.type) " +
            "   ELSE UPPER(clothe.type) " +
            "END) " +
            "LIKE UPPER(CONCAT('%',:text,'%')) " +
            "ORDER BY clothe.price ASC")
    Page<Clothe> searchClothes(String searchType, String text, Pageable pageable);

    @Query("SELECT clothe FROM Clothe clothe " +
            "WHERE (coalesce(:genders, null) IS NULL OR clothe.clotheGender IN :genders) " +
            "AND (coalesce(:priceStart, null) IS NULL OR clothe.price BETWEEN :priceStart AND :priceEnd) " +
            "ORDER BY clothe.price ASC")
    Page<Clothe> getClothesByFilterParams(
            List<String> genders,
            Integer priceStart,
            Integer priceEnd,
            Pageable pageable);

}
