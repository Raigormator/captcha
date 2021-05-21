package com.mightyjava.model.dto;

import java.time.LocalDateTime;

public class CaptchaDetailDto {

    private String captchaCode;
    private String captchaKey;
    private String captchaImage;
    private LocalDateTime createdDate;

    public CaptchaDetailDto() {}

    public CaptchaDetailDto(String captchaCode, String captchaKey, LocalDateTime createdDate) {
        this.captchaCode = captchaCode;
        this.captchaKey = captchaKey;
        this.createdDate = createdDate;
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

    public String getCaptchaImage() {
        return captchaImage;
    }

    public void setCaptchaImage(String captchaImage) {
        this.captchaImage = captchaImage;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "{\ncaptchaCode: " + captchaCode + ",\ncaptchaKey:" + captchaKey + "\n}";
    }
}
