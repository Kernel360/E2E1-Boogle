CREATE TABLE `REPLY` (
                         `ID`   BIGINT NOT NULL AUTO_INCREMENT,
                         `MEMBER_ID`    BIGINT NOT NULL,
                         `BOOK_REPORT_ID`   BIGINT NOT NULL,
                         `CONTENT`  VARCHAR(200)   NOT NULL,
                         `PARENT_REPLY_ID`  BIGINT NULL,
                         `CREATED_AT`   DATETIME   NOT NULL,
                         `LAST_MODIFIED_AT` DATETIME   NULL,
                         `IS_DELETED`   VARCHAR(1) NULL,
                         `DELETED_AT`   DATETIME   NULL,
                         PRIMARY KEY (`ID`)
);