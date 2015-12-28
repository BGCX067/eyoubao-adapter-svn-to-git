# 新建数据库
DROP DATABASE IF EXISTS iacrs;
CREATE DATABASE iacrs CHARACTER SET UTF8;

use iacrs;
set character_set_server = utf8;

# 菜单表
DROP TABLE IF EXISTS `IACRS_MENU`;
CREATE TABLE `IACRS_MENU`
(
    `NAME`              varchar(64)         NOT NULL        COMMENT '菜单名称',
    `CODE`              varchar(32)         NOT NULL        COMMENT '菜单编号',
    `PARENT_CODE`       varchar(32)         NOT NULL        COMMENT '菜单上级编号',
    `FORWARD_URI`       varchar(128)                        COMMENT '菜单转向链接',
    `SORT_NO`           integer             NOT NULL        COMMENT '菜单排序编号',
    PRIMARY KEY (`CODE`)
)
;

# 用户账号表
DROP TABLE IF EXISTS `IACRS_USER`;
CREATE TABLE `IACRS_USER`
(
    `ID`                integer             NOT NULL        AUTO_INCREMENT,
    `USERNAME`          varchar(32)         NOT NULL        COMMENT '用户名',
    `PASSWORD`          varchar(128)        NOT NULL        COMMENT '密码',
    `TYPE`              integer             NOT NULL        COMMENT '账号类型：0-管理账号 1-用户账号',
    `DISABLED`          integer             NOT NULL        COMMENT '账号状态：0-启用 1-禁用',
    CONSTRAINT PK_IACRS_USER PRIMARY KEY (`ID`)
)
;
# 用户信息表
DROP TABLE IF EXISTS `IACRS_USER_INFO`;
CREATE TABLE `IACRS_USER_INFO`
(
    `USER_ID`           integer             NOT NULL,
    `NAME`              varchar(32)         NOT NULL        COMMENT '用户姓名',
    `ID_CARD_NO`        varchar(32)                         COMMENT '身份证号',
    `PHONE`             varchar(32)                         COMMENT '联系电话',
    `EMAIL`             varchar(128)                        COMMENT '电子邮箱',
    `ADDRESS`           varchar(256)                        COMMENT '联系地址',
    CONSTRAINT PK_IACRS_USER_INFO PRIMARY KEY (`USER_ID`),
    CONSTRAINT FK_IACRS_UI_USER foreign key (USER_ID) references IACRS_USER(ID)
)
;
# 用户账户表
DROP TABLE IF EXISTS `IACRS_USER_ACCOUNT`;
CREATE TABLE `IACRS_USER_ACCOUNT`
(
    `USER_ID`           integer             NOT NULL,
    `BALANCE`           integer             NOT NULL        COMMENT '账户余额',
    `PRE_AUTH`          integer             NOT NULL        COMMENT '预授权',
    CONSTRAINT PK_IACRS_USER_ACCOUNT PRIMARY KEY (`USER_ID`),
    CONSTRAINT FK_IACRS_UA_USER foreign key (USER_ID) references IACRS_USER(ID)
)
;
# 充值记录表
DROP TABLE IF EXISTS `IACRS_CHARGE_RECORD`;
CREATE TABLE `IACRS_CHARGE_RECORD`
(
    `ID`                integer             NOT NULL        AUTO_INCREMENT,
    `USER_ID`           integer             NOT NULL        COMMENT '充值账号',
    `CHARGE_TIME`       datetime            NOT NULL        COMMENT '充值日期',
    `AMOUNT`            integer             NOT NULL        COMMENT '充值金额',
    CONSTRAINT PK_IACRS_CHARGE_RECORD PRIMARY KEY (`ID`),
    CONSTRAINT FK_IACRS_UC_USER foreign key (USER_ID) references IACRS_USER(ID)
)
;
# 车型信息表
DROP TABLE IF EXISTS `IACRS_CAR_MODEL`;
CREATE TABLE `IACRS_CAR_MODEL`
(
    `ID`                integer             NOT NULL        AUTO_INCREMENT,
    `NAME`              varchar(128)                        COMMENT '名称',
    `CATEGORY`          varchar(32)                         COMMENT '类别',
    `BRAND`             varchar(32)                         COMMENT '品牌',
    `STYLE`             varchar(32)                         COMMENT '款式',
    `COACH_TYPE`        integer                             COMMENT '车厢类型：0-两厢 1-三厢 2-其他',
    `GEAR_TYPE`         integer                             COMMENT '档位类型：0-手动挡 1-自动挡 2-其他',
    `SWEPT_VOLUME`      varchar(16)                         COMMENT '排量',
    `IMAGE_PATH`        varchar(256)                        COMMENT '图片路径',
    CONSTRAINT PK_IACRS_CAR_MODEL PRIMARY KEY (`ID`)
)
;
# 车型定价表
DROP TABLE IF EXISTS `IACRS_CAR_MODEL_PRICE`;
CREATE TABLE `IACRS_CAR_MODEL_PRICE`
(
    `CAR_MODEL_ID`      integer             NOT NULL,
    `DAILY_RENTAL`      integer             NOT NULL        COMMENT '租金（元/日）',
    `DAILY_PREMIUM`     integer             NOT NULL        COMMENT '保险费（元/日）',
    `PRE_AUTH`          integer             NOT NULL        COMMENT '预授权',
    CONSTRAINT PK_IACRS_CAR_MODEL_PRICE PRIMARY KEY (`CAR_MODEL_ID`),
    CONSTRAINT FK_IACRS_CMP_CAR foreign key (CAR_MODEL_ID) references IACRS_CAR_MODEL(ID)
)
;
# 车辆信息表
DROP TABLE IF EXISTS `IACRS_CAR_INFO`;
CREATE TABLE `IACRS_CAR_INFO`
(
    `ID`                integer             NOT NULL        AUTO_INCREMENT,
    `MODEL_ID`          integer             NOT NULL        COMMENT '所属车型',
    `DAILY_RENTAL`      integer             NOT NULL        COMMENT '租金（元/日）',
    `DAILY_PREMIUM`     integer             NOT NULL        COMMENT '保险费（元/日）',
    `PRE_AUTH`          integer             NOT NULL        COMMENT '预授权',
    `LICENCE_PLATE`     varchar(16)         NOT NULL        COMMENT '车牌',
    `GPS_ID`            varchar(32)         NOT NULL        COMMENT '车载GPS标识',
    CONSTRAINT PK_IACRS_CAR_INFO PRIMARY KEY (`ID`),
    CONSTRAINT FK_IACRS_CMC_CAR foreign key (MODEL_ID) references IACRS_CAR_MODEL(ID)
)
;
# 车辆位置表
DROP TABLE IF EXISTS `IACRS_CAR_POSITION`;
CREATE TABLE `IACRS_CAR_POSITION`
(
    `ID`                integer             NOT NULL        AUTO_INCREMENT,
    `CAR_ID`            integer             NOT NULL,
    `LONGITUDE`         decimal(10,4)       NOT NULL        COMMENT '经度',
    `LATITUDE`          decimal(10,4)       NOT NULL        COMMENT '纬度',
    `FEEDBACK_TIME`     datetime            NOT NULL        COMMENT 'GPS反馈车辆位置的时间',
    CONSTRAINT PK_IACRS_CAR_POSITION PRIMARY KEY (`ID`),
    CONSTRAINT FK_IACRS_CP_CAR foreign key (CAR_ID) references IACRS_CAR_INFO(ID)
)
;
# 用户帐单表
DROP TABLE IF EXISTS `IACRS_BILL`;
CREATE TABLE `IACRS_BILL`
(
    `ID`                integer             NOT NULL        AUTO_INCREMENT,
    `USER_ID`           integer             NOT NULL,
    `CAR_ID`            integer             NOT NULL,
    `START_TIME`        datetime                            COMMENT '租用开始时间',
    `END_TIME`          datetime                            COMMENT '租用结束时间',
    `DURATION`          integer                             COMMENT '租用时长',
    `DAILY_RENTAL`      integer                             COMMENT '租金（元/日）',
    `DAILY_PREMIUM`     integer                             COMMENT '保险费（元/日）',
    `AMOUNT`            integer                             COMMENT '租用金额',
    `BALANCE_TIME`      datetime                            COMMENT '结算时间',
    CONSTRAINT PK_IACRS_BILL PRIMARY KEY (ID),
    CONSTRAINT FK_IACRS_OU_USER foreign key (USER_ID) references IACRS_USER(ID),
    CONSTRAINT FK_IACRS_OC_CAR foreign key (CAR_ID) references IACRS_CAR_INFO(ID)
)
;

# 添加初始化数据
INSERT INTO IACRS_USER (ID, USERNAME, PASSWORD, TYPE, DISABLED) VALUES (-1, 'admin', '90b9aa7e25f80cf4f64e990b78a9fc5ebd6cecad', 0, 0);

INSERT INTO IACRS_MENU (NAME, CODE, PARENT_CODE, FORWARD_URI, SORT_NO) VALUES ('个人中心', '01', '0', NULL, 1);
INSERT INTO IACRS_MENU (NAME, CODE, PARENT_CODE, FORWARD_URI, SORT_NO) VALUES ('个人信息', '0101', '01', '/subscriber/dashboard.do', 1);
INSERT INTO IACRS_MENU (NAME, CODE, PARENT_CODE, FORWARD_URI, SORT_NO) VALUES ('充值记录', '0102', '01', '/subscriber/charge_list.do', 2);
INSERT INTO IACRS_MENU (NAME, CODE, PARENT_CODE, FORWARD_URI, SORT_NO) VALUES ('账单记录', '0103', '01', '/subscriber/bill_list.do', 3);
INSERT INTO IACRS_MENU (NAME, CODE, PARENT_CODE, FORWARD_URI, SORT_NO) VALUES ('修改密码', '0104', '01', '/master/forward_modify_password.do', 4);
INSERT INTO IACRS_MENU (NAME, CODE, PARENT_CODE, FORWARD_URI, SORT_NO) VALUES ('租赁业务', '02', '0', NULL, 2);
INSERT INTO IACRS_MENU (NAME, CODE, PARENT_CODE, FORWARD_URI, SORT_NO) VALUES ('价格体系', '0201', '02', '/subscriber/car_price_list.do', 1);
INSERT INTO IACRS_MENU (NAME, CODE, PARENT_CODE, FORWARD_URI, SORT_NO) VALUES ('会员管理', '03', '0', NULL, 3);
INSERT INTO IACRS_MENU (NAME, CODE, PARENT_CODE, FORWARD_URI, SORT_NO) VALUES ('会员管理', '0301', '03', '/admin/member/member_list.do', 1);
INSERT INTO IACRS_MENU (NAME, CODE, PARENT_CODE, FORWARD_URI, SORT_NO) VALUES ('车辆管理', '04', '0', NULL, 4);
INSERT INTO IACRS_MENU (NAME, CODE, PARENT_CODE, FORWARD_URI, SORT_NO) VALUES ('车型定价', '0401', '04', '/admin/car/model_list.do', 1);
INSERT INTO IACRS_MENU (NAME, CODE, PARENT_CODE, FORWARD_URI, SORT_NO) VALUES ('车辆管理', '0402', '04', '/admin/car/car_list.do', 2);
INSERT INTO IACRS_MENU (NAME, CODE, PARENT_CODE, FORWARD_URI, SORT_NO) VALUES ('租赁管理', '05', '0', NULL, 5);
INSERT INTO IACRS_MENU (NAME, CODE, PARENT_CODE, FORWARD_URI, SORT_NO) VALUES ('租赁记录', '0501', '05', '/admin/rent/rent_list.do', 1);
