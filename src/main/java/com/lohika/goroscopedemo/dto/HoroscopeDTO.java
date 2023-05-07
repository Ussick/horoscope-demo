package com.lohika.goroscopedemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoroscopeDTO {

    private Long id;

    private String sign;

    private String title;

    private String introduction;

    private String moonDesc;

    private String monthDesc;

    private String yearDesc;

    private String bodyInfo;
}
