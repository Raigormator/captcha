package com.mightyjava.service;

import org.springframework.stereotype.Component;

@Component
public interface CaptchaValidator {

    void validate(String captcha, String key);

}
