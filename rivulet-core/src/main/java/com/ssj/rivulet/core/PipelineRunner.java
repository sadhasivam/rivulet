package com.ssj.rivulet.core;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

public class PipelineRunner {

    private final Extractor extractor;
    private final Transformer transformer; // can be null if no transform
    private final Loader loader;

    public PipelineRunner(Extractor extractor, Transformer transformer, Loader loader) {
        this.extractor = extractor;
        this.transformer = transformer;
        this.loader = loader;
    }

    public void run(String jobName) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        StreamTableEnvironment tEnv = StreamTableEnvironment.create(env);

        extractor.extract(env, tEnv);
        if (transformer != null) {
            transformer.transform(env, tEnv);
        }
        loader.load(env, tEnv);

        env.execute(jobName);
    }
}
