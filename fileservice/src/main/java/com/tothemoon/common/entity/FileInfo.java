package com.tothemoon.common.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(length = 200)
    private String fileName;
    @Column(length = 200)
    private String originalName;
    @Column
    private long fileSize;
    @Column(length = 50)

    private String contentType;
    @Column
    private String url;
    @CreationTimestamp
    @Column
    private ZonedDateTime createTime;
    @CreationTimestamp
    @Column
    private ZonedDateTime updateTime;
}