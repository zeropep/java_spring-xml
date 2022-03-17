package com.myweb.www.ctrl;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET) // get방식으로 localhost:8088/으로 들어오는 req를 받겠다.
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale); // 로거
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate ); // model : req대신 view에다가 정보를 담는 전용 객체
		
		return "index"; // 이후 servlet-context의 resolver로 가서 과정을 거친 뒤 맞는 페이지를 렌더링
	}
	
}
