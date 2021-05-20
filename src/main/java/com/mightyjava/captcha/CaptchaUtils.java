package com.mightyjava.captcha;

import cn.apiclub.captcha.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.*;
import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

@Service
public class CaptchaUtils {

	private final BCryptPasswordEncoder encoder;

	@Autowired
	public CaptchaUtils(BCryptPasswordEncoder encoder) {
		this.encoder = encoder;
	}

	public String encodeBase64(Captcha captcha) {
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			ImageIO.write(captcha.getImage(), "png", outputStream);
			String data = DatatypeConverter.printBase64Binary(outputStream.toByteArray());
			outputStream.close();
			return data;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
