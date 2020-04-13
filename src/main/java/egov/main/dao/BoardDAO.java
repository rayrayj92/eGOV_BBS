package egov.main.dao;

import java.util.HashMap;
import java.util.List;

import egov.main.vo.Board;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value = "BoardDAO")
public interface BoardDAO {
	void getList(HashMap<String, Object> paramMap) throws Exception;
	Board getDetail(HashMap<String, Object> paramMap) throws Exception;
	void createList(HashMap<String, Object> paramMap) throws Exception;
	void updateList(HashMap<String, Object> paramMap) throws Exception;
	void updateView(HashMap<String, Object> paramMap) throws Exception;
	void deleteList(List<Long> list) throws Exception;
}
