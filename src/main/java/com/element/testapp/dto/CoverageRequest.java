package com.element.testapp.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Coverage request
 * @author Viktar Lebedzeu
 */
@Data
public class CoverageRequest {
    /** Map of string module id and desired coverage */
    private Map<String, Long> modules = new HashMap<>();

    /**
     * Adds module value
     * @param moduleId Module Id
     * @param value Selected coverage value
     * @return Self reference to build chain of calls
     */
    public CoverageRequest addModule(String moduleId, Long value) {
        modules.put(moduleId, value);
        return this;
    }
}
