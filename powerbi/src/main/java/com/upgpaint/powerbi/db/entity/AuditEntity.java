package com.upgpaint.powerbi.db.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditEntity {

    @Column(name = "creator_id")
    @CreatedBy
    private Long creatorId;

    @Column(name = "updater_id")
    @LastModifiedBy
    private Long updaterId;

    @Column(name = "create_ts")
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTs = new Date();

    @Column(name = "update_ts")
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTs = new Date();

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Long updaterId) {
        this.updaterId = updaterId;
    }

    public Date getCreateTs() {
        return createTs;
    }

    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }

    public Date getUpdateTs() {
        return updateTs;
    }

    public void setUpdateTs(Date updateTs) {
        this.updateTs = updateTs;
    }
}