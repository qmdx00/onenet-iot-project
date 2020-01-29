## onenet-iot-project

设备控制，数据采集和产品溯源系统，完整的物联网项目

### 项目说明

测试用的项目，有很多地方没有做好优化，因本人精力有限，且NB设备丢失，故不再维护。给大家做个参考使用。

### 涉及技术
- protobuf
- lombok
- jwt
- okhttp3
- mqtt
- websocket

### 需要完善的地方
- controller层的token检测每个接口几乎都有，可以单独抽出来用拦截器和自定义注解统一处理，减少重复代码。
- docker构建可以写成docker-compose配置文件，直接构建整个项目环境。
- 实体类PO与VO分离
- 单元测试未完成，必要时请自行做好测试工作。
- 使用[Swagger](https://swagger.io/)自动生成API文档。
- 自行完成前端页面和对接，可参考[data-edage-gateway](https://github.com/0721Betty/data-edage-gateway)。


### 用到的命令
- 生成可执行的 jar 包
```mvn clean package -Dmaven.test.skip=true```

- 创建 MySQL 容器
```docker run --name=mysql -p 8888:3306 -e MYSQL_ROOT_PASSWORD=123 -d mysql:latest```

- 修改 MySql远程访问密码加密方式
```alter user 'root'@'%' identified with mysql_native_password by 'xxx';```
```flush privileges;```

- 服务器后台运行
```nohup java -jar xxx.jar > xxx.log &```