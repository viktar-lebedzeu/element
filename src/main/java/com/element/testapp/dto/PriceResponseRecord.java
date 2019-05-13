package com.element.testapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Price response record
 * @author Viktar Lebedzeu
 */
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
class PriceResponseRecord {
    /** Module Id */
    private String id;
    /** Calculated price */
    private Double price;
    /** Error message if any */
    private String errorMessage;
}
