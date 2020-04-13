package egov.main.service;

import java.util.HashMap;

public interface LoginService {
	HashMap<String, Object> getUser(HashMap<String, Object> paramMap) throws Exception;
}
