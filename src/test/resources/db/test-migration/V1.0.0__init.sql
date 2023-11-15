-- E2E_BOOGLE.BOOK definition
CREATE TABLE `BOOK` (
                        `ID` bigint NOT NULL AUTO_INCREMENT,
                        `TITLE` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                        `CATEGORY_ID` bigint NOT NULL,
                        `THUMBNAIL_URL` varchar(500) DEFAULT NULL,
                        `AUTHOR` varchar(200) NOT NULL,
                        `PUBLISHER` varchar(100) NOT NULL,
                        `ISBN` bigint NOT NULL,
                        `DESCRIPTION` varchar(2000) DEFAULT NULL,
                        `PUBLISH_DATE` date NOT NULL,
                        `SALES_PRICE` int DEFAULT NULL,
                        `CREATED_BY` varchar(50) NOT NULL,
                        `CREATED_AT` datetime NOT NULL,
                        `LAST_MODIFIED_BY` varchar(50) DEFAULT NULL,
                        `LAST_MODIFIED_AT` datetime DEFAULT NULL,
                        `DELETED_AT` datetime DEFAULT NULL,
                        `IS_DELETED` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`ID`),
                        KEY `idx_book_title` (`TITLE`)
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- E2E_BOOGLE.BOOK_REPORT definition
CREATE TABLE `BOOK_REPORT` (
                               `ID` bigint NOT NULL AUTO_INCREMENT,
                               `BOOK_ID` bigint NOT NULL,
                               `MEMBER_ID` bigint NOT NULL,
                               `IS_PUBLIC` varchar(1) NOT NULL,
                               `TITLE` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                               `CONTENT` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                               `CREATED_BY` varchar(50) NOT NULL,
                               `CREATED_AT` datetime NOT NULL,
                               `LAST_MODIFIED_BY` varchar(50) DEFAULT NULL,
                               `LAST_MODIFIED_AT` datetime DEFAULT NULL,
                               `DELETED_AT` datetime DEFAULT NULL,
                               `IS_DELETED` varchar(255) DEFAULT NULL,
                               PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- E2E_BOOGLE.`MEMBER` definition
CREATE TABLE `MEMBER` (
                          `ID` bigint NOT NULL AUTO_INCREMENT,
                          `EMAIL` varchar(100) NOT NULL,
                          `PASSWORD` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                          `NAME` varchar(50) NOT NULL,
                          `NICKNAME` varchar(100) NOT NULL,
                          `GENDER` varchar(1) DEFAULT NULL,
                          `BIRTHDATE` date DEFAULT NULL,
                          `PHONE_NUMBER` varchar(13) DEFAULT NULL,
                          `SIGNUP_DATE` datetime NOT NULL,
                          `WITHDRAWAL_DATE` datetime DEFAULT NULL,
                          `LAST_MODIFIED_BY` varchar(50) DEFAULT NULL,
                          `LAST_MODIFIED_AT` datetime DEFAULT NULL,
                          `ROLE` varchar(20) NOT NULL,
                          PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- E2E_BOOGLE.REPLY definition
CREATE TABLE `REPLY` (
                         `ID` bigint NOT NULL AUTO_INCREMENT,
                         `MEMBER_ID` bigint NOT NULL,
                         `BOOK_REPORT_ID` bigint NOT NULL,
                         `CONTENT` varchar(200) NOT NULL,
                         `PARENT_REPLY_ID` bigint DEFAULT NULL,
                         `CREATED_AT` datetime NOT NULL,
                         `LAST_MODIFIED_AT` datetime DEFAULT NULL,
                         PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;