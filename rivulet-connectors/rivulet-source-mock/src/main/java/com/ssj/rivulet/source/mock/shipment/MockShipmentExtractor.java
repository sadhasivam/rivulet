package com.ssj.rivulet.source.mock.shipment;

import com.ssj.rivulet.core.Extractor;
import com.ssj.rivulet.core.shipment.ShipmentEvent;
import java.time.LocalDateTime;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.connector.datagen.source.DataGeneratorSource;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/** Mock Extractor that generates synthetic ShipmentEvent records. */
public class MockShipmentExtractor implements Extractor {

    @Override
    public void extract(StreamExecutionEnvironment env, StreamTableEnvironment tEnv) {
        DataGeneratorSource<ShipmentEvent> generator =
                new DataGeneratorSource<>(
                        value ->
                                new ShipmentEvent(
                                        "FDX",
                                        "TRK" + value,
                                        "ORG1",
                                        "Q1",
                                        "IN_TRANSIT",
                                        "Scanned",
                                        "E01",
                                        LocalDateTime.now(),
                                        "Atlanta",
                                        "GA",
                                        "US",
                                        "30301",
                                        LocalDateTime.now()),
                        50,
                        TypeInformation.of(ShipmentEvent.class));

        DataStream<ShipmentEvent> stream =
                env.fromSource(generator, WatermarkStrategy.noWatermarks(), "MockShipmentEvents");

        // ✅ Works when flink-table-api-java-bridge-streaming is on classpath
        Table eventTable = tEnv.fromDataStream(stream);
        tEnv.createTemporaryView("shipment_events_view", eventTable);
    }
}
