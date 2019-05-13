package com.element.testapp.dto;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Price response object
 * @author Viktar Lebedzeu
 */
public class PriceResponse {
    /** Price response records list */
    @Getter
    private List<PriceResponseRecord> records = new ArrayList<>();

    @Getter
    private double totalPrice = 0;

    @Getter
    private int errorsCount = 0;

    /**
     * Adds new proce response records and automatically calculates additional fields
     * @param moduleId Module Id
     * @param price Calculated price
     * @param errorMessage Error message
     */
    public void addRecord(String moduleId, Double price, String errorMessage) {
        if (price != null) {
            totalPrice += price;
        }
        if (StringUtils.isNotBlank(errorMessage)) {
            errorsCount++;
        }
        records.add(new PriceResponseRecord(moduleId, price, errorMessage));
    }
}
