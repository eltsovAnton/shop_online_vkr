package com.gmail.shop.ecommerce.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "clothes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Clothe {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clothe_id_seq")
    @SequenceGenerator(name = "clothe_id_seq", sequenceName = "clothe_id_seq", initialValue = 109, allocationSize = 1)
    private Long id;

    @Column(name = "clothe_title", nullable = false)
    private String clotheTitle;

    @Column(name = "clothe_gender", nullable = false)
    private String clotheGender;

    @Column(name = "clothe_color", nullable = false)
    private String clotheColor;

    @Column(name = "description")
    private String description;

    @Column(name = "filename")
    private String filename;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "type", nullable = false)
    private String type;
}
