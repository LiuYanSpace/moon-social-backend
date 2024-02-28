package com.tothemoon.common.entity;

/**
 * @ClassName:DoorKey
 * @Auther: yyj
 * @Description:
 * @Date: 28/02/2024 21:29
 * @Version: v1.0
 */
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "doorkeys")
public class DoorKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "key", nullable = false)
    private String key;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "max_uses", nullable = false)
    private Integer maxUses;

    @Column(name = "uses", nullable = false)
    private Integer uses;

    @Column(name = "activates", nullable = false)
    private Boolean activates;
}
