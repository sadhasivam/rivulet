package com.ssj.rivulet.source.mock.shipment;

import com.ssj.rivulet.core.shipment.ShipmentEvent;
import java.time.LocalDateTime;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.connector.datagen.source.DataGeneratorSource;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.junit.jupiter.api.Test;

class MockShipmentSourceTest {

    @Test
    void testMockSourceProducesEvents() throws Exception {
        // Local execution env
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();

        // Generator: emit 5 ShipmentEvents then stop
        DataGeneratorSource<ShipmentEvent> generator =
                new DataGeneratorSource<>(
                        value ->
                                new ShipmentEvent(
                                        "FDX",
                                        "TRK" + value,
                                        "ORG1",
                                        "Q1",
                                        "IN_TRANSIT",
                                        "Package scanned",
                                        "E01",
                                        LocalDateTime.now(),
                                        "Atlanta",
                                        "GA",
                                        "US",
                                        "30301",
                                        LocalDateTime.now()),
                        5, // finite number of events
                        TypeInformation.of(ShipmentEvent.class));

        DataStream<ShipmentEvent> stream =
                env.fromSource(generator, WatermarkStrategy.noWatermarks(), "MockShipmentSource");

        // Just print to stdout (visible in test logs)
        stream.print();

        // Run synchronously; job finishes after 5 events
        env.execute("Mock Source Test");
    }
}
