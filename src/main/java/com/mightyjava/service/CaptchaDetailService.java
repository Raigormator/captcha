package com.mightyjava.service;

import com.mightyjava.model.dto.CaptchaDetailDto;

import java.util.List;

public interface CaptchaDetailService {

    CaptchaDetailDto create();

    CaptchaDetailDto read(String uuid);

    List<CaptchaDetailDto> read();

    void delete(String uuid);
}
