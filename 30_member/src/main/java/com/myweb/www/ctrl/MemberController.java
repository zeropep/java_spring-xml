package com.myweb.www.ctrl;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.MemberVO;
import com.myweb.www.service.MemberService;

@RequestMapping("/member/*")
@Controller
public class MemberController {
	private static Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	private MemberService msv;
	
//	@RequestMapping(value = "/register", method = RequestMethod.GET) // 3.x 버전용
	@GetMapping("/register") // 4.3버전 이상
	public void register() {
		logger.debug(">>> /member/register - GET");
	}
	
//	@RequestMapping(value = "/register", method = RequestMethod.POST) // 3.x 버전용
	@PostMapping("/register")
	public String register(MemberVO mvo, RedirectAttributes reAttr) {
		logger.debug(">>> /member/register - POST");
		logger.debug(">>> mvo > {}", mvo);
		int isUp = msv.register(mvo);
		logger.debug(">>> Member Register > {}", isUp > 0 ? "Success": "Fail");
		reAttr.addFlashAttribute("isUp", isUp);
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public void login() {
		logger.debug(">>> /member/login - GET");
	}
	
	@PostMapping("/login")
	public String login(MemberVO mvo, HttpSession ses, RedirectAttributes reAttr) {
		logger.debug(">>> mvo > {}", mvo);
		MemberVO loginMvo = msv.login(mvo);
		if (loginMvo != null) {
			ses.setAttribute("ses", loginMvo);	
			ses.setMaxInactiveInterval(10 * 60);
		} else {
		}
		reAttr.addFlashAttribute("isLogin", ses.getAttribute("ses") != null ? 1 : 0);
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession ses, RedirectAttributes reAttr) {
		ses.removeAttribute("ses"); // Session 객체 내부 ses라는 이름을 갖는 객체 삭제
		reAttr.addFlashAttribute("isOut", ses.getAttribute("ses") == null ? 1 : 0);
		ses.invalidate(); // Session 객체 만료
		return "redirect:/";
	}
	
	@GetMapping("/detail")
	public void detail(@RequestParam("email") String email, Model model) {
		MemberVO mvo = msv.getDetail(email);
		model.addAttribute("mvo", mvo);
	}
	
	@GetMapping("/remove")
	public String remove(@RequestParam("email") String email, HttpSession ses, RedirectAttributes reAttr) {
		ses.removeAttribute("ses");
		ses.invalidate();
		int isDel = msv.remove(email);
		reAttr.addFlashAttribute("isDel", isDel > 0 ? 1 : 0);
		return "redirect:/";
	}
	
	@PostMapping("/modify")
	public String modify(MemberVO mvo, RedirectAttributes reAttr) {
		int isUp = msv.modify(mvo);
		reAttr.addFlashAttribute("isUp", isUp);
		return "redirect:/member/detail?email=" + mvo.getEmail();
	}
	
	@GetMapping("/list")
	public void list(Model model) {
		model.addAttribute("list", msv.getList());
	}
}
