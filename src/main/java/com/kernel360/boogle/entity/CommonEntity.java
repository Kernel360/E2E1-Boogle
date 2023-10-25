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
    private String createdBy;
    @CreatedDate
    @Column(name = "created_at", columnDefinition = "DATETIME")
    private LocalDateTime createdAt;
    private String lastModifiedBy;
    @LastModifiedDate
    @Column(name = "modified_at", columnDefinition = "DATETIME")
    private LocalDateTime lastModifiedAt;
    private boolean isDeleted;
    private LocalDateTime deletedAt;
}
