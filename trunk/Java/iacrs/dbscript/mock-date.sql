use iacrs;
set character_set_server = utf8;

# 模拟用户信息
INSERT INTO IACRS_USER (ID, USERNAME, PASSWORD, TYPE, DISABLED) VALUES (10000, 'user1', '0937afa17f4dc08f3c0e5dc908158370ce64df86', 1, 0);
INSERT INTO IACRS_USER_INFO (USER_ID, NAME, ID_CARD_NO, PHONE, EMAIL, ADDRESS) VALUES (10000, '用户1', '320902198410290000', '18662019757', '18662019757@163.com', '江苏盐城希望大道');
INSERT INTO IACRS_USER_ACCOUNT (USER_ID, BALANCE, PRE_AUTH) VALUES (10000, 1000, 3000);
INSERT INTO IACRS_USER (ID, USERNAME, PASSWORD, TYPE, DISABLED) VALUES (10001, 'user2', '0937afa17f4dc08f3c0e5dc908158370ce64df86', 1, 0);
INSERT INTO IACRS_USER_INFO (USER_ID, NAME, ID_CARD_NO, PHONE, EMAIL, ADDRESS) VALUES (10001, '用户2', '320902198410290001', '18662019758', '18662019758@163.com', '江苏盐城希望大道');
INSERT INTO IACRS_USER_ACCOUNT (USER_ID, BALANCE, PRE_AUTH) VALUES (10001, 300, 1000);
INSERT INTO IACRS_USER (ID, USERNAME, PASSWORD, TYPE, DISABLED) VALUES (10002, 'user3', '0937afa17f4dc08f3c0e5dc908158370ce64df86', 1, 0);
INSERT INTO IACRS_USER_INFO (USER_ID, NAME, ID_CARD_NO, PHONE, EMAIL, ADDRESS) VALUES (10002, '用户3', '320902198410290002', '18662019759', '18662019759@163.com', '江苏盐城希望大道');
INSERT INTO IACRS_USER_ACCOUNT (USER_ID, BALANCE, PRE_AUTH) VALUES (10002, 0, 3000);
INSERT INTO IACRS_USER (ID, USERNAME, PASSWORD, TYPE, DISABLED) VALUES (10003, 'user4', '0937afa17f4dc08f3c0e5dc908158370ce64df86', 1, 0);
INSERT INTO IACRS_USER_INFO (USER_ID, NAME, ID_CARD_NO, PHONE, EMAIL, ADDRESS) VALUES (10003, '用户4', '320902198410290003', '18662019760', '18662019760@163.com', '江苏盐城希望大道');
INSERT INTO IACRS_USER_ACCOUNT (USER_ID, BALANCE, PRE_AUTH) VALUES (10003, 2000, 2000);

# 模拟车型信息
INSERT INTO IACRS_CAR_MODEL (ID, NAME, CATEGORY, BRAND, STYLE, COACH_TYPE, GEAR_TYPE, SWEPT_VOLUME, IMAGE_PATH) VALUES (10000, '福特福克斯两厢', '经济型', '福特', '福克斯', 0, 0, '1.6', 'upload/car/mock_1.jpg');
INSERT INTO IACRS_CAR_MODEL_PRICE (CAR_MODEL_ID, DAILY_RENTAL, DAILY_PREMIUM, PRE_AUTH) VALUES (10000, 150, 30, 3000);
INSERT INTO IACRS_CAR_MODEL (ID, NAME, CATEGORY, BRAND, STYLE, COACH_TYPE, GEAR_TYPE, SWEPT_VOLUME, IMAGE_PATH) VALUES (10001, '悦达起亚K3', '经济型', '悦达起亚', 'K3', 0, 0, '1.6', 'upload/car/mock_2.jpg');
INSERT INTO IACRS_CAR_MODEL_PRICE (CAR_MODEL_ID, DAILY_RENTAL, DAILY_PREMIUM, PRE_AUTH) VALUES (10001, 100, 30, 2500);

# 模拟车辆信息
INSERT INTO IACRS_CAR_INFO (ID, MODEL_ID, DAILY_RENTAL, DAILY_PREMIUM, PRE_AUTH, LICENCE_PLATE, GPS_ID) VALUES (10000, 10000, 150, 30, 3000, '苏JCQ999', '0999');
INSERT INTO IACRS_CAR_POSITION (ID, CAR_ID, LONGITUDE, LATITUDE, FEEDBACK_TIME) VALUES (10000, 10000, 116.3277, 39.8997, now());
INSERT INTO IACRS_CAR_INFO (ID, MODEL_ID, DAILY_RENTAL, DAILY_PREMIUM, PRE_AUTH, LICENCE_PLATE, GPS_ID) VALUES (10001, 10000, 150, 30, 3000, '苏JCQ888', '0888');
INSERT INTO IACRS_CAR_POSITION (ID, CAR_ID, LONGITUDE, LATITUDE, FEEDBACK_TIME) VALUES (10001, 10001, 116.3277, 39.8997, now());
INSERT INTO IACRS_CAR_INFO (ID, MODEL_ID, DAILY_RENTAL, DAILY_PREMIUM, PRE_AUTH, LICENCE_PLATE, GPS_ID) VALUES (10002, 10000, 150, 30, 3000, '苏JCQ777', '0777');
INSERT INTO IACRS_CAR_POSITION (ID, CAR_ID, LONGITUDE, LATITUDE, FEEDBACK_TIME) VALUES (10002, 10002, 116.3277, 39.8997, now());
INSERT INTO IACRS_CAR_INFO (ID, MODEL_ID, DAILY_RENTAL, DAILY_PREMIUM, PRE_AUTH, LICENCE_PLATE, GPS_ID) VALUES (10003, 10001, 100, 30, 2500, '苏JCQ666', '0666');
INSERT INTO IACRS_CAR_POSITION (ID, CAR_ID, LONGITUDE, LATITUDE, FEEDBACK_TIME) VALUES (10003, 10003, 116.3277, 39.8997, now());
INSERT INTO IACRS_CAR_INFO (ID, MODEL_ID, DAILY_RENTAL, DAILY_PREMIUM, PRE_AUTH, LICENCE_PLATE, GPS_ID) VALUES (10004, 10001, 100, 30, 2500, '苏JCQ555', '0555');
INSERT INTO IACRS_CAR_POSITION (ID, CAR_ID, LONGITUDE, LATITUDE, FEEDBACK_TIME) VALUES (10004, 10004, 116.3277, 39.8997, now());
INSERT INTO IACRS_CAR_INFO (ID, MODEL_ID, DAILY_RENTAL, DAILY_PREMIUM, PRE_AUTH, LICENCE_PLATE, GPS_ID) VALUES (10005, 10001, 100, 30, 2500, '苏JCQ444', '0444');
INSERT INTO IACRS_CAR_POSITION (ID, CAR_ID, LONGITUDE, LATITUDE, FEEDBACK_TIME) VALUES (10005, 10005, 116.3277, 39.8997, now());