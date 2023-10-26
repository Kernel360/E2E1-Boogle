CREATE TABLE IF NOT EXISTS `BOOK` (
                        `BOOK_ID` bigint NOT NULL AUTO_INCREMENT,
                        `BOOK_TITLE` varchar(200) NOT NULL,
                        `CATEGORY_ID` bigint NOT NULL,
                        `THUMBNAIL_URL` varchar(500) DEFAULT NULL,
                        `AUTHOR` varchar(200) NOT NULL,
                        `PUBLISHER` varchar(100) NOT NULL,
                        `ISBN` int NOT NULL,
                        `DESCRIPTION` varchar(2000) DEFAULT NULL,
                        `PUBLISH_DATE` date NOT NULL,
                        `SALES_PRICE` int DEFAULT NULL,
                        `CREATED_BY` varchar(50) NOT NULL,
                        `CREATED_AT` datetime NOT NULL,
                        `LAST_MODIFIED_BY` varchar(50) DEFAULT NULL,
                        `LAST_MODIFIED_AT` datetime DEFAULT NULL,
                        `DELETED_AT` varchar(1) DEFAULT NULL,
                        `DELETED_YN` datetime DEFAULT NULL,
                        PRIMARY KEY (`BOOK_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;