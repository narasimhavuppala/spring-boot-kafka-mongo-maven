# Springboot-kafka-mongodb

Example data:-

{
  "id": 1,
  "name": "Narasimha",
  "dept": "Software",
  "salary": 15000.0
}
## Swagger UI
 - http://localhost:8192/swagger-ui/index.html
 
## Queries:

  - mongo
  - use kafkamongodb;
  - db.user.find({_id:{$gt: 1794} , salary:{$gt: 34}}).limit(10);

## Include and Exclude fields
  - db.user.find({_id:11},{name:1,dept:1,_id:0,isConsumer:1})

## Execution Status 
  - db.user.find({_id:11},{name:1,dept:1,_id:0,isConsumer:1}).explain("executionStats")
  
## Kafdrop 
  - http://localhost:9000/
  - docker-compose up to bring up the Kafka , zookeeper 