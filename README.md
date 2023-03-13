# E航 - API 开放平台

## 项目背景

API接口调用平台，帮助企业、个人统一开放接口，减少前后端沟通成本，避免重复造轮子。

- 普通用户：注册登录，开通接口调用权限，使用接口。

- 管理员 ：管理员管理接口（发布、下线、新增），调用统计和可视化分析接口查看调用情况。

主要功能：

- API接入
- 防止攻击（安全性） 
- 不能随便调用（限制、开通） 
- 统计调用次数 

技术选型：

后端：

- Spring Boot
- Spring Boot Starter(SDK开发)
- Dubbo (RPC)
- Nacos(注册中心)
- Spring Cloud Gateway(网关、限流、日志实现)
- Redis(数据缓存)

目录结构：

- ehong-backend：7529端口，后端接口管理（上传、下线、用户登录）http://localhost:7529/api/doc.html
- ehong-gateway：8090端口，网关
- ehong-interface：8123端口，提供各种接口服务（可以有很多个且分布在各个服务器）。这里的tests有个发送请求的跑通流程的测试用例。
- ehong-client-sdk：客户端SDK，无端口，发送请求到8090端口，由网关进行转发到后端的api-interface

项目启动：

详情查看 ~/doc/db.md
