package com.ssj.rivulet.source.mock.shipment;

import com.ssj.rivulet.core.shipment.Shipment;
import com.ssj.rivulet.core.shipment.ShipmentEvent;
import java.time.LocalDateTime;
import java.util.Random;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.connector.datagen.source.DataGeneratorSource;

/**
 * A simple Flink SourceFunction that emits mock ShipmentEvent records every 500ms with random
 * values.
 */
public class MockShipmentSource {

    private static final String[] ORGS = {"ORG1", "ORG2"};
    private static final String[] CARRIERS = {"FDX", "UPS", "DHL"};
    private static final String[] STATUS = {"CREATED", "IN_TRANSIT", "DELIVERED"};
    private static final String[] CITIES = {"Atlanta", "Memphis", "Dallas"};
    private static final String[] STATES = {"GA", "TN", "TX"};
    private static final String[] COUNTRIES = {"US"};

    public static DataGeneratorSource<Shipment> shipmentGenerator(int max) {
        Random random = new Random();

        return new DataGeneratorSource<>(
                value ->
                        new Shipment(
                                CARRIERS[random.nextInt(CARRIERS.length)],
                                "TRK" + value,
                                ORGS[random.nextInt(ORGS.length)],
                                "Q" + random.nextInt(100),
                                "API",
                                1.0 + random.nextDouble() * 10,
                                "LB",
                                LocalDateTime.now().plusDays(3),
                                CITIES[random.nextInt(CITIES.length)],
                                STATES[random.nextInt(STATES.length)],
                                "300" + random.nextInt(99),
                                "US",
                                "Created",
                                "CREATED",
                                "New York",
                                "NY",
                                "10001",
                                "US",
                                false,
                                "ORD" + value,
                                LocalDateTime.now(),
                                "REF" + value,
                                "EXP",
                                "Express",
                                1,
                                "FedEx",
                                "Customer",
                                LocalDateTime.now()),
                max,
                TypeInformation.of(Shipment.class));
    }

    public static DataGeneratorSource<ShipmentEvent> shipmentEventGenerator(int max) {
        Random random = new Random();

        return new DataGeneratorSource<>(
                value ->
                        new ShipmentEvent(
                                CARRIERS[random.nextInt(CARRIERS.length)],
                                "TRK" + value,
                                ORGS[random.nextInt(ORGS.length)],
                                "Q" + random.nextInt(100),
                                STATUS[random.nextInt(STATUS.length)],
                                "Package scanned",
                                "E" + (100 + random.nextInt(50)),
                                LocalDateTime.now(),
                                CITIES[random.nextInt(CITIES.length)],
                                STATES[random.nextInt(STATES.length)],
                                COUNTRIES[0],
                                "300" + random.nextInt(99),
                                LocalDateTime.now()),
                max,
                TypeInformation.of(ShipmentEvent.class));
    }
}
