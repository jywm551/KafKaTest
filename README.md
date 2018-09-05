# 关于消息队列的一些练习
## kafka rabbitMQ

- kafka  
    下载zookeeper  
    下载kafka     
     1. 启动zookeeper   
    ~~~
        bin/zookeeper-server-start.sh config/zookeeper.properties
    ~~~
     2. 启动kafka   
    ~~~
        bin/kafka-server-start.sh config/server.properties
    ~~~
    
- rabbitMQ  
     1. 下载Erlang，地址：http://www.erlang.org/download/otp_win32_R15B.exe  
     2. 下载RabbitMQ，地址：http://www.rabbitmq.com/releases/rabbitmq-server/v3.3.4/rabbitmq-server-3.3.4.exe 
     3. 进入sbin目录，运行  rabbitmq-server start