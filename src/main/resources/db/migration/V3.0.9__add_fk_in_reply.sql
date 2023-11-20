alter table REPLY
    add constraint REPLY_MEMBER_id_fk
        foreign key (member_id) references MEMBER (id);