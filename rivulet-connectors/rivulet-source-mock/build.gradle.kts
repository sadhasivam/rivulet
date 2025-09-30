dependencies {
    implementation(project(":rivulet-core"))
    //implementation(libs.flink.streaming.java)
    //implementation(libs.flink.clients)
    //implementation(libs.flink.runtime)
    implementation(libs.flink.table.api.java)
    implementation(libs.flink.table.bridge.java)
    // implementation(libs.flink.table.bridge.java.streaming)
    implementation(libs.flink.datagen)
    implementation(libs.flink.test.utils)
}
