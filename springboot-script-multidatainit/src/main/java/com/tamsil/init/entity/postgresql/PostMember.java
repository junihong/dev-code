package com.tamsil.init.entity.postgresql;

import com.tamsil.init.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "member")
@Data
public class PostMember extends BaseTimeEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    @Column
    private String gender;
}
