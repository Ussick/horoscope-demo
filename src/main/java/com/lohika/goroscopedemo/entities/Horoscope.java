package com.lohika.goroscopedemo.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "horoscopes")
//@NamedEntityGraph(name = "Horoscope.user",
//        attributeNodes = @NamedAttributeNode("user")
//)
public class Horoscope {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String sign;

    private String introduction;

    @Column(name = "moon_desc")
    @Length(max = 2048)
    private String moonDesc;

    @Column(name = "month_desc")
    @Length(max = 2048)
    private String monthDesc;

    @Column(name = "year_desc")
    @Length(max = 2048)
    private String yearDesc;

    @Column(name = "body_info")
    @Length(max = 2048)
    private String bodyInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "id"))
    private User user;
}
