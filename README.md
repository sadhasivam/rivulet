# .devcontainer docs

## To Run

cd rivulet
docker compose -f .devcontainer/compose.yaml -p shipment_visibility up -d

## Service Endpoints:

- PostgreSQL: localhost:5432 (app_user/password)
- Kafka: localhost:9092
- Kafka UI: http://localhost:9090
- EventHubs Emulator: localhost:7074 (Kafka-compatible)
- EventHubs Dashboard: http://localhost:7300
- Azurite Blob: localhost:10000
- SingleStore UI: http://localhost:6060
- SingleStore DB: 3306
