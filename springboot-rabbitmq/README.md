### RabbitMQ Essentials
```text
  -RabbitMQ is a message broker that implements Advanced Message Queing Protocol(AMQP).
  -It increases loose coupling and scalability.
  -With a complex system, we will have multiple queues and multiple consumers. In that case, the producer sends message to the exchange with a routing key and the exchange connects with the Queue only with binding key and then the messages are distributed to all the queues.   
  -There are different types of exchange.
   1. Direct Exchange - It routes messages to a queue by matching routing key equal to binding key.
   2. Fanout Exchange - It ignores the routing key and sends message to all the available queues.
   3. Topic Exchange –  It routes messages to multiple queues by a partial matching of a routing key. It uses patterns to match the routing and binding key.
   4. Headers Exchange – It uses message header instead of routing key.
   5. Default(Nameless)
 Exchange - It routes the message to queue name that exactly matches with the routing key.
  
```
### RabbitMq Installation Step on Ubuntu 18.04
## Install Erlang
---
```text
    wget https://packages.erlang-solutions.com/erlang-solutions_1.0_all.deb
    sudo dpkg -i erlang-solutions_1.0_all.deb
    sudo apt-get update -y
    sudo apt-get install -y erlang erlang-nox
```
## Add RabbitMQ apt repository
```text
   echo 'deb http://www.rabbitmq.com/debian/ testing main' | sudo tee /etc/apt/sources.list.d/rabbitmq.list
   wget -O- https://www.rabbitmq.com/rabbitmq-release-signing-key.asc | sudo apt-key add -

```

## Update the package list
```text
   sudo apt-get update -y
```
## Install RabbitMQ
```text
   sudo apt-get install -y rabbitmq-server
```

## Start RabbitMQ
```text
    sudo systemctl start rabbitmq-server
```

## Check RabbitMQ status

```text
   sudo systemctl status rabbitmq-server
```

## Enable RabbitMQ service so it starts on boot
```text
   sudo systemctl enable rabbitmq-server
```

## Setup RabbitMQ Web Management Console
```text
    sudo rabbitmq-plugins enable rabbitmq_management
```
## Create Admin User in RabbitMQ
```text
    sudo rabbitmqctl add_user admin password 
    sudo rabbitmqctl set_user_tags admin administrator
    sudo rabbitmqctl set_permissions -p / admin ".*" ".*" ".*"
```

## Check Web UI Interface
```text
     http://localhost:15672/
```

### SpringBoot with RabbitMq

## Add Rabbitmq depenedency
```text
   <dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-amqp</artifactId>
   </dependency>

```
## add the following properties value
```text
   spring.rabbitmq.host=localhost # RabbitMQ host.
   spring.rabbitmq.password= # Login to authenticate against the broker.
   spring.rabbitmq.port=5672 # RabbitMQ port.
   spring.rabbitmq.username= # Login user to authenticate to the broker.
```

## Create RabbitMqDirectConfig File
 [RabbitMqDirectConfig](https://github.com/BrajeshKumarchaudhary/SpringBootConcepts/blob/master/springboot-rabbitmq/src/main/java/com/app/rabbitmq/config/RabbitMqDirectConfig.java)

## Create one RestController
[RabbitMqDirectController](https://github.com/BrajeshKumarchaudhary/SpringBootConcepts/blob/master/springboot-rabbitmq/src/main/java/com/app/rabbitmq/controller/RabbitMqDirectController.java)

## Create One RabbitMQListner
[RabbitMQListner](https://github.com/BrajeshKumarchaudhary/SpringBootConcepts/blob/master/springboot-rabbitmq/src/main/java/com/app/rabbitmq/listener/RabbitMQListner.java)

----






