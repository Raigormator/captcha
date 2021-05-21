package com.mightyjava.service.impl;

import com.mightyjava.exception.CaptchaException;
import com.mightyjava.model.dto.CaptchaDetailDto;
import com.mightyjava.service.CaptchaDetailService;
import com.mightyjava.service.CaptchaValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CaptchaValidatorImpl implements CaptchaValidator {

    private final CaptchaDetailService captchaDetailService;

    public CaptchaValidatorImpl(CaptchaDetailService captchaDetailService) {
        this.captchaDetailService = captchaDetailService;
    }

    @Override
    public void validate(String captcha, String key) {
        CaptchaDetailDto captchaDetailDto = captchaDetailService.read(key);
        LocalDateTime expiryDate = captchaDetailDto.getCreatedDate().plusSeconds(25L);
        LocalDateTime requestTime = LocalDateTime.now();

        if (!captchaDetailDto.getCaptchaCode().equals(captcha)) {
            throw new CaptchaException("Captcha is Incorrect");
        } else if(requestTime.isAfter(expiryDate)) {
            throw new CaptchaException("Captcha is Expired");
        } else {
            captchaDetailService.delete(key);
        }
    }

}
