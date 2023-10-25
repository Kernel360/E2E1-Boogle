package com.kernel360.boogle.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class CommonEntity {
    @Column(name = "CREATED_BY")
    private String createdBy;
    @CreatedDate
    @Column(name = "CREATED_AT", columnDefinition = "DATETIME")
    private LocalDateTime createdAt;
    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;
    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_AT", columnDefinition = "DATETIME")
    private LocalDateTime lastModifiedAt;

    @Column(name = "DELETED_AT")
    private LocalDateTime deletedAt;
}
