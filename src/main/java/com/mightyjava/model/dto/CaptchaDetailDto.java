package com.mightyjava.model.dto;

import javax.persistence.Column;

public class CaptchaDetailDto {

    private String captchaCode;
    private String captchaKey;
    private String captchaImage;

    public CaptchaDetailDto() {}

    public CaptchaDetailDto(String captchaCode, String captchaKey) {
        this.captchaCode = captchaCode;
        this.captchaKey = captchaKey;
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

    @Override
    public String toString() {
        return "{\ncaptchaCode: " + captchaCode + ",\ncaptchaKey:" + captchaKey + "\n}";
    }
}
