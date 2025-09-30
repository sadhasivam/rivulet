package com.ssj.rivulet.core;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/** Extract: produce a source stream or table. */
public interface Extractor {
    void extract(StreamExecutionEnvironment env, StreamTableEnvironment tEnv) throws Exception;
}
