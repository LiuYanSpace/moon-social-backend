package com.tothemoon.common.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "`groups`")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, columnDefinition = "int unsigned")
    private Long id;

    @Column(name = "name_singular", nullable = false, length = 100)
    private String nameSingular;

    @Column(name = "name_plural", nullable = false, length = 100)
    private String namePlural;

    @Column(name = "color", length = 20)
    private String color;

    @Column(name = "icon", length = 100)
    private String icon;

    @Column(name = "is_hidden", nullable = false)
    private boolean isHidden;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
