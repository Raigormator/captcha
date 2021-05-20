package com.mightyjava.service.impl;

import cn.apiclub.captcha.Captcha;
import com.mightyjava.captcha.CaptchaGenerator;
import com.mightyjava.captcha.CaptchaUtils;
import com.mightyjava.model.CaptchaDetail;
import com.mightyjava.model.dto.CaptchaDetailDto;
import com.mightyjava.repository.CaptchaDetailRepository;
import com.mightyjava.service.CaptchaDetailService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CaptchaDetailServiceImpl implements CaptchaDetailService {

    private final CaptchaDetailRepository captchaDetailRepository;
    private final CaptchaGenerator captchaGenerator;
    private final CaptchaUtils captchaUtils;

    public CaptchaDetailServiceImpl(CaptchaDetailRepository captchaDetailRepository, CaptchaGenerator captchaGenerator,
                                    CaptchaUtils captchaUtils) {
        this.captchaDetailRepository = captchaDetailRepository;
        this.captchaGenerator = captchaGenerator;
        this.captchaUtils = captchaUtils;
    }

    @Override
    public CaptchaDetailDto create() {
        Captcha captcha = captchaGenerator.createCaptcha(200, 50);
        String uuid = UUID.randomUUID().toString();

        CaptchaDetail captchaDetail = new CaptchaDetail();
        captchaDetail.setCaptchaCode(captcha.getAnswer());
        captchaDetail.setCaptchaKey(uuid);

        return convertToDto(captchaDetailRepository.save(captchaDetail), captcha);
    }

    @Override
    public CaptchaDetailDto read(String uuid) {
        return convertToDto(captchaDetailRepository.findByCaptchaKey(uuid));
    }

    private CaptchaDetailDto convertToDto(CaptchaDetail captchaDetail, Captcha captcha) {
        CaptchaDetailDto captchaDetailDto = new CaptchaDetailDto(captchaDetail.getCaptchaCode(),
                captchaDetail.getCaptchaKey());
        captchaDetailDto.setCaptchaImage(captchaUtils.encodeBase64(captcha));
        return captchaDetailDto;
    }

    private CaptchaDetailDto convertToDto(CaptchaDetail captchaDetail) {
        return new CaptchaDetailDto(captchaDetail.getCaptchaCode(), captchaDetail.getCaptchaKey());
    }

}
