package com.mightyjava.scheduler;

import com.mightyjava.model.CaptchaDetail;
import com.mightyjava.model.dto.CaptchaDetailDto;
import com.mightyjava.repository.CaptchaDetailRepository;
import com.mightyjava.service.CaptchaDetailService;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
public class CaptchaScheduler {

    private static final Logger LOG = Logger.getLogger(CaptchaScheduler.class);

    private final CaptchaDetailService captchaDetailService;


    public CaptchaScheduler(CaptchaDetailService captchaDetailService) {
        this.captchaDetailService = captchaDetailService;
    }

    @Scheduled(fixedDelay = 5000)
    public void deleteExpiredCaptcha() {
        List<CaptchaDetailDto> captchaDetails = captchaDetailService.read();
        captchaDetails.stream().filter(captchaDetail -> {
            LocalDateTime expiryDate = captchaDetail.getCreatedDate().plusSeconds(25L);
            LocalDateTime requestTime = LocalDateTime.now();
            return requestTime.isAfter(expiryDate);
        }).forEach(captchaDetail -> {
            LOG.info("Expired Captcha::" + captchaDetail);
            captchaDetailService.delete(captchaDetail.getCaptchaKey());
        });

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        LOG.info("Java cron job expression:: " + strDate);
    }

    public void checkCronExpression(final String cronExpression) {
        try {
            Date now = new Date();

            LOG.info(now);

            final CronSequenceGenerator gen = new CronSequenceGenerator(cronExpression);

            for (int i = 0; i < 5; i++) {
                now = gen.next(now);

                LOG.info(now);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

}
