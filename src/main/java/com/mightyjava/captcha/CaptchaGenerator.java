package com.mightyjava.captcha;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.BackgroundProducer;
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
import cn.apiclub.captcha.backgrounds.SquigglesBackgroundProducer;
import cn.apiclub.captcha.backgrounds.TransparentBackgroundProducer;
import cn.apiclub.captcha.gimpy.FishEyeGimpyRenderer;
import cn.apiclub.captcha.gimpy.GimpyRenderer;
import cn.apiclub.captcha.noise.CurvedLineNoiseProducer;
import cn.apiclub.captcha.noise.NoiseProducer;
import cn.apiclub.captcha.text.producer.DefaultTextProducer;
import cn.apiclub.captcha.text.producer.TextProducer;
import cn.apiclub.captcha.text.renderer.DefaultWordRenderer;
import cn.apiclub.captcha.text.renderer.WordRenderer;
import org.springframework.beans.factory.InitializingBean;

public class CaptchaGenerator implements InitializingBean {

	private static final String DEFAULT_CAPTCHA_ALPHANUMERIC = "abcdefghjklmnopqrstuvwxyz1234567890";

	private BackgroundProducer backgroundProducer;
	private TextProducer textProducer;
	private WordRenderer wordRenderer;
	private NoiseProducer noiseProducer;
	private GimpyRenderer gimpyRenderer;
	
	public Captcha createCaptcha(int width, int height) {
		return new Captcha.Builder(width, height)
				.addBackground(backgroundProducer)
				.addText(textProducer, wordRenderer)
				.addNoise(noiseProducer)
				.gimp(gimpyRenderer)
				.build();
	}

	@Override
	public void afterPropertiesSet() {
		if (this.backgroundProducer == null) {
			this.backgroundProducer = new GradiatedBackgroundProducer();
		}
		if (this.textProducer == null) {
			this.textProducer = new DefaultTextProducer(6, DEFAULT_CAPTCHA_ALPHANUMERIC.toCharArray());
		}
		if (this.wordRenderer == null) {
			this.wordRenderer = new DefaultWordRenderer();
		}
		if (this.noiseProducer == null) {
			this.noiseProducer = new CurvedLineNoiseProducer();
		}
		if (this.gimpyRenderer == null) {
			this.gimpyRenderer = new FishEyeGimpyRenderer();
		}
	}

}
