package com.mightyjava.service;

import com.mightyjava.model.dto.CaptchaDetailDto;

public interface CaptchaDetailService {

    CaptchaDetailDto create();

    CaptchaDetailDto read(String uuid);

}
