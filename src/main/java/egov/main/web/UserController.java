package egov.main.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value = "user/home.do")
	public String userHome(){
		log.info(">>>>>>>>>USER HOME<<<<<<<<<<");
		return "user/index";
	}
}
