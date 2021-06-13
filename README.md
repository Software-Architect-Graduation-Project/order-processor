# order-processor
Project to process eccomerce orders

docker run -d \
    --name order-processor-db \
    -p 5432:5432 \
    -e POSTGRES_PASSWORD=postgres \
    -v $(pwd)/docker/setup-db.sh:/docker-entrypoint-initdb.d/setup-db.sh \
    postgres:13.3


docker exec -it broker /bin/kafka-console-producer --broker-list localhost:9092 --topic new_order

{ "clientId" : "123", "paymentPlan" : "10x", "products" : "453,586,654" }

docker exec -it broker /bin/kafka-console-producer --broker-list localhost:9092 --topic payment_processed

{ "orderId" : "1", "updateTime" : "2021-06-12T20:30:00", "newStatus" : "PAYMENT_PROCESSED" }