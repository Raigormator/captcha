package com.mightyjava.captcha;

import java.io.Serializable;
import cn.apiclub.captcha.Captcha;

public class CaptchaDetails implements Serializable {

	private static final long serialVersionUID = 739484691638714603L;

	private final String answer;
	
	public CaptchaDetails(Captcha captcha) {
		this.answer = captcha.getAnswer();
	}
	
	public String getAnswer() {
		return answer;
	}
}
