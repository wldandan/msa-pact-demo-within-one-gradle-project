== 1.运行Consumer端测试

----
$ ./gradlew test
----
生成Consumer和Provider之间的Contract文件


== 2.运行Provider端测试

使用如下命令
----
$ ./gradlew assemble 
$ export PACT_FILE={xxxx} 导出PACT文件的路径
$ ./gradlew pactVerify
----


== 参考

This project contains a *very simple* demo of using http://martinfowler.com/articles/consumerDrivenContracts.html[consumer-driven contracts] to verify the interactions between microservices.
It leverages http://projects.spring.io/spring-boot[Spring Boot] for both the provider and consumer services.

Testing is achieved using the https://github.com/DiUS/pact-jvm[pact-jvm] project, which is a JVM port of the original https://github.com/realestate-com-au/pact[pact].
