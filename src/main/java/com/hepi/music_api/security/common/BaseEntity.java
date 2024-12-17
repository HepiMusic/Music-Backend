package com.hepi.music_api.security.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;




    @MappedSuperclass
    @EntityListeners(EntityListeners.class)
    @Data

    public abstract class BaseEntity implements Serializable {


        @CreatedDate
        @Column(
                nullable = false,
                updatable = false
        )
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        private LocalDateTime createDate;

        @LastModifiedDate
        @Column(insertable = false)
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        private LocalDateTime lastModified;


        @CreatedBy
        @Column(name = "role_created_by",
                nullable = true,
                updatable = false
        )
        private Integer createdBy;

        @LastModifiedBy
        @Column(name = "role_updated_by", insertable = false)
        private Integer lastModifiedBy;


        @PrePersist
        @PreUpdate
        @PreRemove
        private void init() {
            this.createDate = LocalDateTime.now();
            this.lastModified = LocalDateTime.now();

        }
        @Version
        @Column(name = "VERSION")
        private Long version;
    }



