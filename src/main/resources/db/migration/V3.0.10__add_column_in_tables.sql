alter table BOOK
    modify author varchar(255) not null;

alter table BOOK
    modify category_id bigint not null;

alter table BOOK
    modify created_at datetime not null;

alter table BOOK
    modify isbn bigint not null;

alter table BOOK
    modify publish_date date not null;

alter table BOOK
    modify publisher varchar(255) not null;

alter table BOOK
    modify thumbnail_url varchar(255) not null;

alter table BOOK
    modify title varchar(255) not null;

alter table MEMBER
    modify signup_date datetime not null;

alter table BOOK_REPORT
    modify created_at datetime not null;

alter table BOOK_REPORT
    modify created_by varchar(255) not null;

alter table BOOK_REPORT
    modify member_id bigint not null;

alter table REPLY
    modify created_at datetime not null;

alter table REPLY
    modify member_id bigint not null;
