package com.mightyjava.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.apiclub.captcha.Captcha;
import com.mightyjava.exception.CaptchaException;
import com.mightyjava.model.dto.CaptchaDetailDto;
import com.mightyjava.service.CaptchaDetailService;
import com.mightyjava.service.CaptchaValidator;
import com.mightyjava.service.impl.CaptchaDetailServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mightyjava.captcha.CaptchaGenerator;
import com.mightyjava.captcha.CaptchaUtils;
import com.mightyjava.model.Employee;
import com.mightyjava.service.EmployeeService;

@Controller
public class EmployeeController {

	private static final Logger LOG = Logger.getLogger(EmployeeController.class);

	private String message;
	private final EmployeeService employeeService;
	private CaptchaValidator captchaValidator;
	private CaptchaDetailService captchaDetailService;

	@Autowired
	public EmployeeController(EmployeeService employeeService, CaptchaValidator captchaValidator,
							  CaptchaDetailService captchaDetailService) {
		this.employeeService = employeeService;
		this.captchaValidator = captchaValidator;
		this.captchaDetailService = captchaDetailService;
	}
	
	@GetMapping("/")
	public String add(Model model, HttpSession httpSession) {
		model.addAttribute("message", message);
		model.addAttribute("employee", new Employee());
		CaptchaDetailDto captcha = captchaDetailService.create();
		model.addAttribute("captchaEncode", captcha.getCaptchaImage());
		model.addAttribute("captchaKey", captcha.getCaptchaKey());
		return "add";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("employee") Employee employee, HttpServletRequest request) {
		LOG.info("Captcha Answer 2: " + employee.getCaptcha());
		LOG.info("Captcha Key     : " + employee.getCaptchaKey());
		try {
			captchaValidator.validate(employee.getCaptcha(), employee.getCaptchaKey());
			employeeService.add(employee);
			return "redirect:/list";
		} catch (CaptchaException ce) {
			message = "Please verify captcha";
			return "redirect:/";
		}
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		message = null;
		model.addAttribute("employees", employeeService.employees());
		return "list";
	}
}
