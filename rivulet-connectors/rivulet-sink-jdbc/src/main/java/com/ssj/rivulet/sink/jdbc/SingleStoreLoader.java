package com.ssj.rivulet.sink.jdbc;

import com.ssj.rivulet.core.Loader;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

public class SingleStoreLoader implements Loader {
    @Override
    public void load(StreamExecutionEnvironment env, StreamTableEnvironment tEnv) {
        tEnv.executeSql(
            "CREATE TABLE shipment_event_summary (" +
                " status STRING," +
                " event_count BIGINT" +
                ") WITH (" +
                " 'connector' = 'jdbc'," +
                " 'url' = 'jdbc:mysql://singlestore:3306/rivulet_db'," +
                " 'table-name' = 'shipment_event_summary'," +
                " 'driver' = 'com.mysql.cj.jdbc.Driver'," +
                " 'username' = 'root'," +
                " 'password' = 'password'" +
                ")"
        );

        tEnv.executeSql("INSERT INTO shipment_event_summary SELECT * FROM shipment_event_summary_view");
    }
}
