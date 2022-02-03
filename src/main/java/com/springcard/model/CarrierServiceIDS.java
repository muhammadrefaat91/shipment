package com.springcard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum CarrierServiceIDS {
    FEDEX_AIR("fedexair"), FEDEX_GROUND("fedexground"),
    UPS_EXPRESS("upsexpress"), UPS_2DAY("ups2day");

    private String carrierServiceID;

    private static Map<String, CarrierServiceIDS> carrierServiceIDSMap;
    static {
        carrierServiceIDSMap = Stream.of(values()).collect(Collectors.toMap(CarrierServiceIDS::getCarrierServiceID, Function.identity()));
    }

    public static CarrierServiceIDS carrierServiceIDSValue(String givenName) {
        return carrierServiceIDSMap.get(givenName);
    }

    public static CarrierServiceIDS[] getValues(){
        return CarrierServiceIDS.values();
    }

    @Override
    public String toString() {
        return "CarrierServiceIDS{" +
                "carrierServiceID='" + carrierServiceID + '\'' +
                '}';
    }
}
