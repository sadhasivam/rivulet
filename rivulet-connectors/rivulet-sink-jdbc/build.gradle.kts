dependencies {
    implementation(project(":rivulet-core"))

    // JDBC + SingleStore dependencies belong here
    implementation(libs.flink.connector.jdbc)
    implementation(libs.singlestore.jdbc.client)

    // Table APIs (needed for createTable, executeSql, etc.)
    implementation(libs.flink.table.api.java)
    implementation(libs.flink.table.bridge.java)

}
