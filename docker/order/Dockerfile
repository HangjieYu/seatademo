FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.3_7

#设置内存限制
ENV MIN_MEORY -Xms64m
ENV MAX_MEORY -Xmx64m

#创建文件目录
RUN mkdir -p /md
RUN mkdir -p /md/dump
#将dockerfile所在目录包编译好的jar包copy到运行目录
COPY ./order-1.0-SNAPSHOT.jar /md/
#工作目录
WORKDIR /md

EXPOSE 8083
EXPOSE 9093

cmd java $MIN_MEORY $MAX_MEORY -Xverify:none -XX:TieredStopAtLevel=1 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/md/dump/md.hprof -jar order-1.0-SNAPSHOT.jar
