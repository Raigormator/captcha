package com.mightyjava.model;

import javax.persistence.*;

@Entity
@Table(name = "CAPTCHA_DETAIL", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"CAPTCHA_CODE"}),
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

    @Override
    public String toString() {
        return "{\nid: " + id + ",\ncaptchaCode: " + captchaCode + ",\ncaptchaKey:" + captchaKey + "\n}";
    }
}