package com.inakilab.storerateapi.controller;

import com.inakilab.storerateapi.dto.PriceDto;
import com.inakilab.storerateapi.exception.PriceNotFoundException;
import com.inakilab.storerateapi.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Validated
@RestController
@RequestMapping("/api/price")
@RequiredArgsConstructor
@Log4j2
public class PriceController {

    public static final String PRICE_NOT_FOUND_MESSAGE_PATTERN = "No price found for [brandId: %d] [productId: %d] [selectedDate: %s]";
    private final PriceService priceService;

    @Operation(summary = "Get the highest priority price of a product for the given product identifier, Brand and Date")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Price found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PriceDto.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Price not found",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping(value = "/{brandId}/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PriceDto findPriorPrice(
            @Parameter(name = "brandId", description = "Id of the brand", example = "2", required = true)
            @PathVariable(value = "brandId") @Valid @NotNull Long brandId,
            @Parameter(name = "productId", description = "Id of the product", example = "201002", required = true)
            @PathVariable(value = "productId") @Valid @NotNull Long productId,
            @Parameter(name = "date", description = "Date of the price to apply to the product", example = "2020-05-10T00:00:00Z", required = true)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam(value = "date") @Valid @NotNull LocalDateTime selectedDate
    ) {
        try {
            return priceService.getPriceByPriority(brandId, productId, selectedDate);
        } catch(PriceNotFoundException e) {
            String errorMessage = String.format(PRICE_NOT_FOUND_MESSAGE_PATTERN, brandId, productId, selectedDate.toString());
            log.error(errorMessage);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    errorMessage,
                    e);
        }
    }

}
