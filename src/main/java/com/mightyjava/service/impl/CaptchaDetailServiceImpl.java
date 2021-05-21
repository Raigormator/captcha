package com.mightyjava.service.impl;

import cn.apiclub.captcha.Captcha;
import com.mightyjava.captcha.CaptchaGenerator;
import com.mightyjava.captcha.CaptchaUtils;
import com.mightyjava.exception.CaptchaException;
import com.mightyjava.model.CaptchaDetail;
import com.mightyjava.model.dto.CaptchaDetailDto;
import com.mightyjava.repository.CaptchaDetailRepository;
import com.mightyjava.service.CaptchaDetailService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static javax.transaction.Transactional.TxType.REQUIRES_NEW;

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
    @Transactional(value = REQUIRES_NEW)
    public CaptchaDetailDto create() {
        Captcha captcha = captchaGenerator.createCaptcha(200, 50);
        String uuid = UUID.randomUUID().toString();

        CaptchaDetail captchaDetail = new CaptchaDetail();
        captchaDetail.setCaptchaCode(captcha.getAnswer());
        captchaDetail.setCaptchaKey(uuid);
        captchaDetail.setCreatedDate(LocalDateTime.now());

        return convertToDto(captchaDetailRepository.save(captchaDetail), captcha);
    }

    @Override
    @Transactional(value = REQUIRES_NEW)
    public CaptchaDetailDto read(String uuid) {
        CaptchaDetail captchaDetail = captchaDetailRepository.findByCaptchaKey(uuid);
        if (captchaDetail == null) {
            throw new CaptchaException("Invalid Captcha");
        }
        return convertToDto(captchaDetail);
    }

    @Override
    @Transactional(value = REQUIRES_NEW)
    public List<CaptchaDetailDto> read() {
        return captchaDetailRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(value = REQUIRES_NEW)
    public void delete(String uuid) {
        captchaDetailRepository.deleteByCaptchaKey(uuid);
    }

    private CaptchaDetailDto convertToDto(CaptchaDetail captchaDetail, Captcha captcha) {
        CaptchaDetailDto captchaDetailDto = new CaptchaDetailDto(captchaDetail.getCaptchaCode(),
                captchaDetail.getCaptchaKey(), captchaDetail.getCreatedDate());
        captchaDetailDto.setCaptchaImage(captchaUtils.encodeBase64(captcha));
        return captchaDetailDto;
    }

    private CaptchaDetailDto convertToDto(CaptchaDetail captchaDetail) {
        return new CaptchaDetailDto(captchaDetail.getCaptchaCode(), captchaDetail.getCaptchaKey(),
                captchaDetail.getCreatedDate());
    }

}
