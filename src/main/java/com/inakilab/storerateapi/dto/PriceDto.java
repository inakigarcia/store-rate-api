package com.inakilab.storerateapi.dto;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "PriceDto api message to model Price")
public class PriceDto {

    @Schema(description = "Id of the Price", example = "1")
    @NotNull
    @NotEmpty
    private Long priceList;

    @Schema(description = "Id of the brand", example = "1")
    @NotNull
    @NotEmpty
    private Long brandId;

    @Schema(description = "Start date for the price", example = "2020-06-11T12:00:00")
    @NotNull
    @NotEmpty
    private LocalDateTime startDate;

    @Schema(description = "End date for the price", example = "2020-06-13T12:00:00")
    @NotNull
    @NotEmpty
    private LocalDateTime endDate;

    @Schema(description = "Id of the product", example = "34355")
    @NotNull
    @NotEmpty
    private Long productId;

    @Schema(description = "Value of the product", example = "34.55")
    @NotNull
    @NotEmpty
    private BigDecimal value;

    @Schema(description = "Currency for the price value", example = "EUR", required = true)
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 20)
    private String currency;
}
