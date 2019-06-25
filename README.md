# onenet-iot-project

设备控制，数据采集和产品溯源系统，完整的物联网项目

#### 生成可执行的 jar 包
```mvn clean package -Dmaven.test.skip=true```

#### 创建 MySQL 容器
```docker run --name=mysql -p 8888:3306 -e MYSQL_ROOT_PASSWORD=123 -d mysql:latest```

#### 服务器后台运行
```nohup java -jar xxx.jar > xxx.log &```