package com.ssj.rivulet.core;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/** Transform: apply business logic on registered tables. */
public interface Transformer {
    void transform(StreamExecutionEnvironment env, StreamTableEnvironment tEnv) throws Exception;
}
