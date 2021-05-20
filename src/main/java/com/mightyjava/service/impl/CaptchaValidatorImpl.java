package com.mightyjava.service.impl;

import com.mightyjava.exception.CaptchaException;
import com.mightyjava.model.dto.CaptchaDetailDto;
import com.mightyjava.service.CaptchaDetailService;
import com.mightyjava.service.CaptchaValidator;
import org.springframework.stereotype.Service;

@Service
public class CaptchaValidatorImpl implements CaptchaValidator {

    private CaptchaDetailService captchaDetailService;

    public CaptchaValidatorImpl(CaptchaDetailService captchaDetailService) {
        this.captchaDetailService = captchaDetailService;
    }

    @Override
    public void validate(String captcha, String key) {
        CaptchaDetailDto captchaDetailDto = captchaDetailService.read(key);
        if (captchaDetailDto == null || !captchaDetailDto.getCaptchaCode().equals(captcha)) {
            throw new CaptchaException("Captcha is Incorrect");
        }
    }

}
