-- 创建库
create database if not exists my_db;

use my_db;

-- 用户表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    userName     varchar(256)                           null comment '用户昵称',
    userAccount  varchar(256)                           not null comment '账号',
    userAvatar   varchar(1024)                          null comment '用户头像',
    gender       tinyint                                null comment '性别',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user / admin',
    userPassword varchar(512)                           not null comment '密码',
    accessKey    varchar(512) not null comment 'accessKey',
    secretKey    varchar(512) not null comment 'secretKey',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除',
    constraint uni_userAccount
    unique (userAccount)
    ) comment '用户';


-- 接口信息
CREATE TABLE if not exists my_db.`interface_info`
(
   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
   `name` varchar(256)  NOT NULL COMMENT '名称',
   `description` varchar(256) NULL DEFAULT NULL COMMENT '描述',
   `url` varchar(512) NOT NULL COMMENT '接口地址',
   `requestHeader` text NULL COMMENT '请求头',
   `responseHeader` text COMMENT '响应头',
   `requestParams` varchar(255) NULL DEFAULT NULL COMMENT '请求参数',
   `status` int(11) NOT NULL DEFAULT 0 COMMENT '接口状态（0-关闭，1-开启）',
   `method` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '请求类型',
   `userId` bigint(20) NOT NULL COMMENT '创建人',
   `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
   `isDelete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除(0-未删, 1-已删)',
)COMMENT = '接口信息';


-- 用户调用接口关系表
create table if not exists my_db.`user_interface_info`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `userId` bigint not null comment '调用用户 id',
    `interfaceInfoId` bigint not null comment '接口 id',
    `totalNum` int default 0 not null comment '总调用次数',
    `leftNum` int default 0 not null comment '剩余调用次数',
    `status` int default 0 not null comment '0-正常，1-禁用',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDelete` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment '用户调用接口关系';