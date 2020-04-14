package egov.main.web;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import egov.main.service.LoginService;

@Controller
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Resource(name="LoginService") LoginService loginService;
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String Login(HttpServletRequest request, ModelMap model){

		return "login/login";
	}
	
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String tryLogin(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpServletRequest request, ModelMap model) throws Exception{
		
		HashMap<String, Object> inputMap = new HashMap<String, Object>();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		if(!email.equals("") && (password.length() > 6)){
			inputMap.put("email", email);
			inputMap.put("password", password);
			
			resultMap = loginService.getUser(inputMap);
			
			if(resultMap == null){
				return "redirect:/login.do";
			} else {
				String name = (String) resultMap.get("FULLNAME");
				String input = (String) resultMap.get("ROLE");
				
				switch(input){
					case "USER":
						request.getSession().setAttribute("USER", name);
						log.info("<<===== " + name + " ====>> 로그인을 하였습니다.");
						return "redirect:/user/home.do";
					case "ADMIN":
						request.getSession().setAttribute("ADMIN", name);
						log.info("<<===== " + name + " ====>> 로그인을 하였습니다.");
						return "redirect:/admin/home.do";
					default:
						return "redirect:/login.do";
				}
			}
		}
		return "redirect:/login.do";
	}
	
	@RequestMapping(value = "/logout.do")
	public String Logout(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			session.invalidate();
			return "redirect:/login.do";
		}
		
		return "redirect:/login.do"; // 404 error
	}
}
