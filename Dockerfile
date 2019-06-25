# 项目发布镜像构建

# java:8 做为基层镜像
FROM java:8
# 工作目录
WORKDIR /app
# 拷贝文件
COPY onenet-iot.jar /app
# 镜像服务的守护端口
EXPOSE 8080
# 执行命令
ENTRYPOINT [ "java", "-jar", "./onenet-iot.jar" ]