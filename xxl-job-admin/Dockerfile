# Docker image for springboot file run

# 基础镜像使用 java-8
FROM registry-vpc.cn-shanghai.aliyuncs.com/fosun_health/health-openjre8:20.10

# 作者
MAINTAINER fosun-health team <jkdj@fosun.com>

ARG JAR_FILE=xxl-job-admin.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080
EXPOSE 9999


ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-XX:+UseG1GC","-XX:+UseContainerSupport","-XX:MaxRAMPercentage=75.0","-jar","/app.jar"]
