package com.ssj.rivulet.job.shipment;

import com.ssj.rivulet.core.PipelineRunner;
import com.ssj.rivulet.sink.jdbc.SingleStoreLoader;
import com.ssj.rivulet.source.mock.shipment.MockShipmentExtractor;

public class ShipmentPipelineJob {
    public static void main(String[] args) throws Exception {
        PipelineRunner runner =
                new PipelineRunner(
                        new MockShipmentExtractor(), // Extract (mock stream)
                        new ShipmentStatusTransformer(), // Transform (Table API SQL)
                        new SingleStoreLoader() // Load (SingleStore JDBC sink)
                        );

        runner.run("ShipmentPipelineJob");
    }
}
