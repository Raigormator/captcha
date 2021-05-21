package com.mightyjava.model;

import com.mightyjava.captcha.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "CAPTCHA_DETAIL", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"CAPTCHA_KEY"})
})
public class CaptchaDetail {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CAPTCHA_CODE", nullable = false)
    private String captchaCode;

    @Column(name = "CAPTCHA_KEY", nullable = false)
    private String captchaKey;

    @Column(name = "CREATED_DATE", nullable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public String getCaptchaKey() {
        return captchaKey;
    }

    public void setCaptchaKey(String captchaKey) {
        this.captchaKey = captchaKey;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "{\n" +
                "id: " + id + ",\n" +
                "captchaCode: " + captchaCode + ",\n" +
                "captchaKey:" + captchaKey + "\n" +
                "createdDate:" + createdDate + "\n" +
                "}";
    }
}