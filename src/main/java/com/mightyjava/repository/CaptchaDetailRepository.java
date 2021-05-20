package com.mightyjava.repository;

import com.mightyjava.model.CaptchaDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaptchaDetailRepository extends JpaRepository<CaptchaDetail, Long> {

    CaptchaDetail findByCaptchaKey(String key);

}
