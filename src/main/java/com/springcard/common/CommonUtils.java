package com.springcard.common;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * @author mohamed.refaat
 *
 */
public class CommonUtils {

    public static String asJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
