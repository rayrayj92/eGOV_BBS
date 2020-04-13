package egov.main.service;

import java.util.HashMap;
import java.util.List;

import egov.main.vo.Board;

public interface BoardService {
	void getList(HashMap<String, Object> paramMap) throws Exception;
	Board getDetail(HashMap<String, Object> paramMap) throws Exception;
	void createList(HashMap<String, Object> paramMap) throws Exception;
	void updateList(HashMap<String, Object> paramMap) throws Exception;
	void updateView(HashMap<String, Object> paramMap) throws Exception;
	void deleteList(List<Long> list) throws Exception;
}
