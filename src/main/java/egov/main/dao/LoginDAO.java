package egov.main.dao;

import java.util.HashMap;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value = "LoginDAO")
public interface LoginDAO {
	HashMap<String, Object> getUser(HashMap<String, Object> paramMap) throws Exception;
}
