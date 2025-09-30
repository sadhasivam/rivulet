plugins {
    id("java")
    id("application")
    id("com.gradleup.shadow") version "9.2.2"
}

application {
    mainClass.set("com.ssj.rivulet.job.ShipmentPipelineJob")
}

dependencies {
    implementation(project(":rivulet-core"))
    implementation(project(":rivulet-connectors:rivulet-source-mock"))
    implementation(project(":rivulet-connectors:rivulet-sink-jdbc"))

//    // Core Flink runtime
//    implementation(libs.flink.streaming.java)
    implementation(libs.flink.table.api.java)
//    implementation(libs.flink.table.runtime)
    implementation(libs.flink.table.bridge.java)
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.ssj.rivulet.job.shipment.ShipmentPipelineJob"
    }
}

tasks.shadowJar {
  archiveClassifier.set("all")
  mergeServiceFiles()
  manifest {
    attributes["Main-Class"] = "com.ssj.rivulet.job.shipment.ShipmentPipelineJob"
  }
}
