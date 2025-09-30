package com.ssj.rivulet.job.shipment;

import com.ssj.rivulet.core.Transformer;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

public class ShipmentStatusTransformer implements Transformer {
    @Override
    public void transform(StreamExecutionEnvironment env, StreamTableEnvironment tEnv) {
        Table summary =
                tEnv.sqlQuery(
                        "SELECT status, COUNT(*) AS event_count "
                                + "FROM shipment_events_view "
                                + "GROUP BY status");
        tEnv.createTemporaryView("shipment_event_summary_view", summary);
    }
}
