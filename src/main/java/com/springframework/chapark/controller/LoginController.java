package com.springframework.chapark.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springframework.chapark.common.ChaparkService;
import com.springframework.chapark.common.CommonMap;
=======
>>>>>>> branch 'master' of https://github.com/ChaparkProject/chapark_v1.git

@Controller
public class LoginController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

<<<<<<< HEAD
	@Autowired
	private ChaparkService ChaparkService;
=======
    @RequestMapping(value = "/login.do")
    public String loginPage() {
        return "login";
    }
>>>>>>> branch 'master' of https://github.com/ChaparkProject/chapark_v1.git

<<<<<<< HEAD
	@SuppressWarnings({ "rawtypes", "unchecked" } )
	@RequestMapping(value = "/login.do")

	public String loginMain(CommonMap CommonMap, HttpServletRequest request) throws Exception {
		//commonMap.put("mberId",(String) commonMap.get("mberId"));
		//commonMap.put("mberPw",(String) commonMap.get("mberPw"));
			
			Map memberInfo = ChaparkService.selectMap( "lo_login.selectUserInfo", CommonMap.getMap());

			if (memberInfo != null && !memberInfo.isEmpty()) { // 정보가 null이 아니고 비어있지 않을 때

					HttpSession session = request.getSession();
					session.setAttribute("memberInfo", memberInfo);
					session.setMaxInactiveInterval(60 * 30); // 세션 유지시간 30분 설정, 60초 * 30분 = 1800초
					//return "WEB-INF/views/mberTest";
			} else { // 로그인 정보가 없을 시
				return "login";
			}
		return "login";
	}

	@RequestMapping(value = "/logout.do")
	public String logout(Model model, Map paramMap, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("memberInfo");
		return "logout";
	}

=======
    @RequestMapping(value = "/logout.do")
    public String logout(Model model, Map paramMap, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("memberInfo");
        return "logout";
    }
>>>>>>> branch 'master' of https://github.com/ChaparkProject/chapark_v1.git
}
