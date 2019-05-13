package com.element.testapp.dto;

import lombok.Data;

import java.util.Map;

/**
 * Coverage request
 * @author Viktar Lebedzeu
 */
@Data
public class CoverageRequest {
    /** Map of string module id and desired coverage */
    private Map<String, Long> modules;
}
