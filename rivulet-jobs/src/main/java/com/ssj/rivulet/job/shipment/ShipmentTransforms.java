package com.ssj.rivulet.job.shipment;

import com.ssj.rivulet.core.Transformer;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/** Domain-specific transformations for Shipment-related jobs. */
public class ShipmentTransforms {

    /** Counts events grouped by status (e.g., CREATED, IN_TRANSIT, DELIVERED). */
    public static Transformer countEventsByStatus() {
        return (StreamExecutionEnvironment env, StreamTableEnvironment tEnv) -> {
            Table summary =
                    tEnv.sqlQuery(
                            "SELECT status, COUNT(*) AS event_count "
                                    + "FROM shipment_events_view "
                                    + "GROUP BY status");
            tEnv.createTemporaryView("shipment_event_summary_view", summary);
        };
    }

    /** Filters only delivered shipments. */
    public static Transformer filterDeliveredShipments() {
        return (StreamExecutionEnvironment env, StreamTableEnvironment tEnv) -> {
            Table delivered =
                    tEnv.sqlQuery("SELECT * FROM shipment_events_view WHERE status = 'DELIVERED'");
            tEnv.createTemporaryView("delivered_shipments_view", delivered);
        };
    }

    /** Groups by facility (mock example). */
    public static Transformer groupByFacility() {
        return (StreamExecutionEnvironment env, StreamTableEnvironment tEnv) -> {
            Table grouped =
                    tEnv.sqlQuery(
                            "SELECT city, state, COUNT(*) AS facility_events "
                                    + "FROM shipment_events_view "
                                    + "GROUP BY city, state");
            tEnv.createTemporaryView("facility_event_summary_view", grouped);
        };
    }
}
