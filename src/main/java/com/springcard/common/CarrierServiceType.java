package com.springcard.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum CarrierServiceType {
    FEDEX("fedex"), UPS("ups");

    private String carrierServiceType;

    private static Map<String, CarrierServiceType> carrierServiceTypeMap;
    static {
        carrierServiceTypeMap = Stream.of(values()).collect(Collectors.toMap(CarrierServiceType::getCarrierServiceType, Function.identity()));
    }

    public static CarrierServiceType carrierServiceType(String givenName) {
        return carrierServiceTypeMap.get(givenName);
    }

    public static CarrierServiceType[] getValues(){
        return CarrierServiceType.values();
    }

}
