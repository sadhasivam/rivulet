package com.ssj.rivulet.core.shipment;

import java.time.LocalDateTime;

public record Shipment(
        String carrierCode,
        String trackingNumber,
        String orgId,
        String trackingQualifier,
        String shipmentSource,
        double packageWeight,
        String packageWeightUnit,
        LocalDateTime carrierPromiseDate,
        String originCity,
        String originState,
        String originPostalCode,
        String originCountry,
        String shipmentStatusName,
        String shipmentStatusCode,
        String destinationCity,
        String destinationState,
        String destinationPostalCode,
        String destinationCountry,
        boolean isReturn,
        String merchantOrderId,
        LocalDateTime shipDate,
        String shipperReference,
        String serviceTypeCode,
        String serviceTypeName,
        int noOfPackages,
        String shipperCompany,
        String recipientCompany,
        LocalDateTime createdDate) {}
