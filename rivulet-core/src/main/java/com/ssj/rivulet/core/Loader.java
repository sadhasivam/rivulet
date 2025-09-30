package com.ssj.rivulet.core;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/** Load: write transformed results to sinks. */
public interface Loader {
    void load(StreamExecutionEnvironment env, StreamTableEnvironment tEnv) throws Exception;
}
