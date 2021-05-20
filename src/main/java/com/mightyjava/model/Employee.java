package com.mightyjava.model;

public class Employee {
	private String name;
	private String dateOfBirth;
	private String salary;
	private String captcha;
	private String captchaKey;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getCaptchaKey() {
		return captchaKey;
	}

	public void setCaptchaKey(String captchaKey) {
		this.captchaKey = captchaKey;
	}
}
