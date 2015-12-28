# 新建数据库
DROP DATABASE IF EXISTS swt;
CREATE DATABASE swt CHARACTER SET UTF8;

use swt;
set character_set_server = utf8;

# 菜单表
DROP TABLE IF EXISTS `SWT_MENU`;
CREATE TABLE `SWT_MENU`
(
    `CODE`              varchar(32)         NOT NULL        COMMENT '菜单编号',
    `PARENT_CODE`       varchar(32)         NOT NULL        COMMENT '菜单上级编号',
    `NAME`              varchar(64)         NOT NULL        COMMENT '菜单名称',
    `FORWARD_URI`       varchar(128)                        COMMENT '菜单转向链接',
    `SORT_NO`           integer             NOT NULL        COMMENT '菜单排序编号',
    PRIMARY KEY (`CODE`)
)
;

# 用户账号表
DROP TABLE IF EXISTS `SWT_USER`;
CREATE TABLE `SWT_USER`
(
    `ID`                integer             NOT NULL        AUTO_INCREMENT,
    `USERNAME`          varchar(32)         NOT NULL        COMMENT '用户名',
    `PASSWORD`          varchar(128)        NOT NULL        COMMENT '密码',
    `TYPE`              integer             NOT NULL        COMMENT '账号类型：0-管理员 1-会员',
    `DISABLED`          integer             NOT NULL        COMMENT '账号状态：0-启用 1-禁用',
    `ARCHIVE_ID`        integer                             COMMENT '用户资料',
    CONSTRAINT PK_SWT_USER PRIMARY KEY (`ID`)
)
;
# 用户信息表
DROP TABLE IF EXISTS `SWT_USER_ARCHIVE`;
CREATE TABLE `SWT_USER_ARCHIVE`
(
    `ID`                integer             NOT NULL        AUTO_INCREMENT,
    `NAME`              varchar(32)         NOT NULL        COMMENT '用户姓名',
    `PHONE`             varchar(32)                         COMMENT '联系电话',
    `EMAIL`             varchar(128)                        COMMENT '电子邮箱',
    `ADDRESS`           varchar(256)                        COMMENT '联系地址',
    CONSTRAINT PK_SWT_USER_ARCHIVE PRIMARY KEY (`ID`)
)
;

DROP TABLE IF EXISTS `SWT_NOSHERY`;
CREATE TABLE `SWT_NOSHERY`
(
    `ID`                integer             NOT NULL        AUTO_INCREMENT,
    `NAME`              varchar(32)         NOT NULL        COMMENT '店名',
    `PHONE`             varchar(32)                         COMMENT '联系电话',
    `ADDRESS`           varchar(256)                        COMMENT '联系地址',
    `IMAGE_PATH`        varchar(128)                        COMMENT '图片路径',
    `DESCRIPTION`       varchar(256)                        COMMENT '描述',
    CONSTRAINT PK_SWT_NOSHERY PRIMARY KEY (`ID`)
)
;

DROP TABLE IF EXISTS `SWT_CATEGORY`;
CREATE TABLE `SWT_CATEGORY`
(
    `ID`                integer             NOT NULL        AUTO_INCREMENT,
    `NAME`              varchar(32)         NOT NULL        COMMENT '分类名称',
    CONSTRAINT PK_SWT_CATEGORY PRIMARY KEY (`ID`)
)
;

DROP TABLE IF EXISTS `SWT_COOKBOOK`;
CREATE TABLE `SWT_COOKBOOK`
(
    `ID`                integer             NOT NULL        AUTO_INCREMENT,
    `NOSHERY_ID`        integer             NOT NULL        COMMENT '店铺ID',
    `CATEGORY_ID`       integer                             COMMENT '分类ID',
    `NAME`              varchar(32)         NOT NULL        COMMENT '菜名',
    `PRICE`             decimal(10,2)                       COMMENT '价格',
    `IMAGE_PATH`        varchar(128)                        COMMENT '图片路径',
    `DESCRIPTION`       varchar(256)                        COMMENT '描述',
    CONSTRAINT PK_SWT_COOKBOOK PRIMARY KEY (`ID`)
)
;

DROP TABLE IF EXISTS `SWT_ORDER`;
CREATE TABLE `SWT_ORDER`
(
    `ID`                integer             NOT NULL        AUTO_INCREMENT,
    `CODE`              varchar(32)         NOT NULL        COMMENT '订单编号',
    `AMOUNT`            decimal(10,2)                       COMMENT '金额',
    `ORDER_TIME`        datetime            NOT NULL        COMMENT '下单时间',
    `STATE`             integer                             COMMENT '状态：0-未确认 1-已确认 2-已取消',
    `PAY_MODE`          integer                             COMMENT '付款方式：0-货到付款 1-在线支付',
    `PAY_STATE`         integer                             COMMENT '付款状态：0-已支付 1-未支付',
    `DELIVER_ADDRESS`   varchar(256)                        COMMENT '送货地址',
    CONSTRAINT PK_SWT_ORDER PRIMARY KEY (`ID`)
)
;

DROP TABLE IF EXISTS `SWT_ORDER_ENTRY`;
CREATE TABLE `SWT_ORDER_ENTRY`
(
    `ID`                integer             NOT NULL        AUTO_INCREMENT,
    `ORDER_ID`          integer             NOT NULL        COMMENT '订单ID',
    `CATEGORY_NAME`     varchar(32)                         COMMENT '分类名称',
    `NOSHERY_NAME`     varchar(32)                          COMMENT '店铺名称',
    `COOKBOOK_NAME`     varchar(32)                         COMMENT '菜单名称',
    `PRICE`             decimal(10,2)                       COMMENT '单价',
    `COUNT`             integer                             COMMENT '数量',
    CONSTRAINT PK_SWT_ORDER_ENTRY PRIMARY KEY (`ID`)
)
;

# 添加初始化数据
INSERT INTO SWT_USER (ID, USERNAME, PASSWORD, TYPE, DISABLED) VALUES (1, 'admin', '90b9aa7e25f80cf4f64e990b78a9fc5ebd6cecad', 0, 0);

INSERT INTO SWT_MENU (NAME, CODE, PARENT_CODE, FORWARD_URI, SORT_NO) VALUES ('会员管理', '01', '0', '/admin/user_list.do', 1);
INSERT INTO SWT_MENU (NAME, CODE, PARENT_CODE, FORWARD_URI, SORT_NO) VALUES ('店铺管理', '02', '0', '/admin/noshery_list.do', 2);
INSERT INTO SWT_MENU (NAME, CODE, PARENT_CODE, FORWARD_URI, SORT_NO) VALUES ('菜单分类', '03', '0', '/admin/category_list.do', 3);
INSERT INTO SWT_MENU (NAME, CODE, PARENT_CODE, FORWARD_URI, SORT_NO) VALUES ('菜单管理', '04', '0', '/admin/cookbook_list.do', 4);
INSERT INTO SWT_MENU (NAME, CODE, PARENT_CODE, FORWARD_URI, SORT_NO) VALUES ('订单管理', '05', '0', '/admin/order_list.do', 5);
INSERT INTO SWT_MENU (NAME, CODE, PARENT_CODE, FORWARD_URI, SORT_NO) VALUES ('留言管理', '06', '0', '/admin/message_list.do', 6);

# 测试数据
INSERT INTO SWT_USER_ARCHIVE (ID, NAME, PHONE, EMAIL, ADDRESS) VALUES (1, '会员一', '18662060001', '18662060001@163.com', '解放南路101号');
INSERT INTO SWT_USER_ARCHIVE (ID, NAME, PHONE, EMAIL, ADDRESS) VALUES (2, '会员二', '18662060002', '18662060002@163.com', '解放南路102号');
INSERT INTO SWT_USER_ARCHIVE (ID, NAME, PHONE, EMAIL, ADDRESS) VALUES (3, '会员三', '18662060003', '18662060003@163.com', '解放南路103号');
INSERT INTO SWT_USER_ARCHIVE (ID, NAME, PHONE, EMAIL, ADDRESS) VALUES (4, '会员四', '18662060004', '18662060004@163.com', '解放南路104号');

INSERT INTO SWT_USER (USERNAME, PASSWORD, TYPE, DISABLED, ARCHIVE_ID) VALUES ('0001', '0937afa17f4dc08f3c0e5dc908158370ce64df86', 1, 0, 1);
INSERT INTO SWT_USER (USERNAME, PASSWORD, TYPE, DISABLED, ARCHIVE_ID) VALUES ('0002', '0937afa17f4dc08f3c0e5dc908158370ce64df86', 1, 0, 2);
INSERT INTO SWT_USER (USERNAME, PASSWORD, TYPE, DISABLED, ARCHIVE_ID) VALUES ('0003', '0937afa17f4dc08f3c0e5dc908158370ce64df86', 1, 0, 3);
INSERT INTO SWT_USER (USERNAME, PASSWORD, TYPE, DISABLED, ARCHIVE_ID) VALUES ('0004', '0937afa17f4dc08f3c0e5dc908158370ce64df86', 1, 0, 4);

INSERT INTO SWT_CATEGORY(ID, NAME) VALUES (1, '中式炒菜');
INSERT INTO SWT_CATEGORY(ID, NAME) VALUES (2, '中式便当');
INSERT INTO SWT_CATEGORY(ID, NAME) VALUES (3, '西式快餐');
INSERT INTO SWT_CATEGORY(ID, NAME) VALUES (4, '日韩料理');
INSERT INTO SWT_CATEGORY(ID, NAME) VALUES (5, '奶茶饮料');

INSERT INTO SWT_NOSHERY(ID, NAME, PHONE, ADDRESS, IMAGE_PATH, DESCRIPTION) VALUES (1, '徽香阁', '88888888', '解放北路101号', '', '中式炒菜');
INSERT INTO SWT_NOSHERY(ID, NAME, PHONE, ADDRESS, IMAGE_PATH, DESCRIPTION) VALUES (2, '肯德基', '88888889', '解放北路102号', '', '西式快餐');
INSERT INTO SWT_NOSHERY(ID, NAME, PHONE, ADDRESS, IMAGE_PATH, DESCRIPTION) VALUES (3, '日月便当', '88888880', '解放北路103号', '', '中式便当');

INSERT INTO SWT_COOKBOOK(ID, CATEGORY_ID, NOSHERY_ID, NAME , PRICE, IMAGE_PATH, DESCRIPTION) VALUES (1, 1, 1, '酸辣土豆丝', 6, '', '酸辣土豆丝');
INSERT INTO SWT_COOKBOOK(ID, CATEGORY_ID, NOSHERY_ID, NAME , PRICE, IMAGE_PATH, DESCRIPTION) VALUES (2, 1, 1, '油焖茄子', 8, '', '油焖茄子');
INSERT INTO SWT_COOKBOOK(ID, CATEGORY_ID, NOSHERY_ID, NAME , PRICE, IMAGE_PATH, DESCRIPTION) VALUES (3, 1, 1, '宫保鸡丁', 10, '', '宫保鸡丁');
INSERT INTO SWT_COOKBOOK(ID, CATEGORY_ID, NOSHERY_ID, NAME , PRICE, IMAGE_PATH, DESCRIPTION) VALUES (4, 3, 2, '薯条', 6, '', '薯条');
INSERT INTO SWT_COOKBOOK(ID, CATEGORY_ID, NOSHERY_ID, NAME , PRICE, IMAGE_PATH, DESCRIPTION) VALUES (5, 3, 2, '原味鸡翅', 12, '', '原味鸡翅');
INSERT INTO SWT_COOKBOOK(ID, CATEGORY_ID, NOSHERY_ID, NAME , PRICE, IMAGE_PATH, DESCRIPTION) VALUES (6, 3, 2, '老北京鸡肉卷', 15, '', '老北京鸡肉卷');
INSERT INTO SWT_COOKBOOK(ID, CATEGORY_ID, NOSHERY_ID, NAME , PRICE, IMAGE_PATH, DESCRIPTION) VALUES (7, 2, 3, '鱼香茄子饭', 10, '', '鱼香茄子饭');
INSERT INTO SWT_COOKBOOK(ID, CATEGORY_ID, NOSHERY_ID, NAME , PRICE, IMAGE_PATH, DESCRIPTION) VALUES (8, 2, 3, '梅菜扣肉饭', 15, '', '梅菜扣肉饭');
