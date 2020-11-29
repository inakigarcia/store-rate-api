package com.inakilab.storerateapi.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "prices")
public class Price {

    @Id
    @SequenceGenerator(name = "price_list_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "price_list_id_seq")
    @Column(name = "PRICE_LIST", unique = true, nullable = false)
    private Long priceList;

    @Column(name = "BRAND_ID", nullable = false)
    @NotNull
    @NotEmpty
    private Long brandId;

    @Column(name = "START_DATE", nullable = false)
    @NotNull
    @NotEmpty
    private LocalDateTime startDate;

    @Column(name = "END_DATE", nullable = false)
    @NotNull
    @NotEmpty
    private LocalDateTime endDate;

    @Column(name = "PRODUCT_ID", nullable = false)
    @NotNull
    @NotEmpty
    private Long productId;

    @Column(name = "PRIORITY", nullable = false)
    @NotNull
    @NotEmpty
    private Long priority;

    @Column(name = "PRICE", nullable = false)
    @NotNull
    @NotEmpty
    private BigDecimal value;

    @Column(name = "CURRENCY", length = 20, nullable = false)
    @Size(max = 20)
    private String currency;
}
