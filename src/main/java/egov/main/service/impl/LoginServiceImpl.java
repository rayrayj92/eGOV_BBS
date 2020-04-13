package egov.main.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egov.main.dao.LoginDAO;
import egov.main.service.LoginService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("LoginService")
public class LoginServiceImpl extends EgovAbstractServiceImpl implements LoginService{

	@Resource(name = "LoginDAO") LoginDAO loginDAO;
	
	@Override
	public HashMap<String, Object> getUser(HashMap<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return loginDAO.getUser(paramMap);
	}

}
