package egov.main.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import egov.main.dao.BoardDAO;
import egov.main.service.BoardService;
import egov.main.vo.Board;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("BoardService")
public class BoardServiceImpl extends EgovAbstractServiceImpl implements BoardService {
	//private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	@Resource(name = "BoardDAO") BoardDAO boardDAO;

	@Override
	public void getList(HashMap<String, Object> paramMap) throws Exception {
		boardDAO.getList(paramMap);
	}

	@Override
	public void createList(HashMap<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		boardDAO.createList(paramMap);
	}

	@Override
	public void updateList(HashMap<String, Object> paramMap) throws Exception {
		boardDAO.updateList(paramMap);
	}

	@Override
	public void deleteList(List<Long> list) throws Exception {
		boardDAO.deleteList(list);
	}

	@Override
	public Board getDetail(HashMap<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return boardDAO.getDetail(paramMap);
	}

	@Override
	public void updateView(HashMap<String, Object> paramMap) throws Exception {
		boardDAO.updateView(paramMap);
	}

}
