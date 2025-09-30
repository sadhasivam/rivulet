package com.ssj.rivulet.core.shipment;

import java.time.LocalDateTime;

public record ShipmentEvent(
        String carrierCode,
        String trackingNumber,
        String orgId,
        String trackingQualifier,
        String status,
        String statusDetail,
        String eventCode,
        LocalDateTime eventDate,
        String city,
        String state,
        String country,
        String postalCode,
        LocalDateTime createdDate) {}
