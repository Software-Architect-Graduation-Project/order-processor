# order-processor
Project to process eccomerce orders

docker exec -it broker /bin/kafka-console-producer --broker-list localhost:9092 --topic order

{ "clientId" : "123", "paymentPlan" : "10x", "products" : "453,586,654" }

{ "orderId" : "1", "updateTime" : "2021-06-12T20:30:00", "newStatus" : "PAYMENT_PROCESSED" }