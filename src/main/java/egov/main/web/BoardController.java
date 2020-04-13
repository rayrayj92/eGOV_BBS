package egov.main.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egov.main.service.BoardService;
import egov.main.vo.Board;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class BoardController {
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@Resource(name="BoardService") BoardService boardService;
	@Resource(name = "fileUploadProperty") Properties fileUploadProperty;
	
	@RequestMapping(value = "/admin/home.do" , method = RequestMethod.GET)
	public String adminHome(HttpServletRequest request, ModelMap model){
		if(request.getSession().getAttribute("ADMIN") == null){
			request.getSession().invalidate();
			return "redirect:/login.do";
		} else {
			log.info(">>>>>>>>>>>>>>>>>ADMIN HOME<<<<<<<<<<<<<<<<<<<");
			return "admin/index";
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/list.do" , method = RequestMethod.GET)
	public String getList(HttpServletRequest request, ModelMap model) throws Exception{
		if(request.getSession().getAttribute("ADMIN") == null){
			request.getSession().invalidate();
			return "redirect:/login.do";
		} else {
			ArrayList<HashMap<String, Object>> list = new ArrayList<>();
			HashMap<String, Object> InputMap = new HashMap<String, Object>();
			InputMap.put("ref_cursor", null);
			
			String pageNo = "";
			pageNo = request.getParameter("pageNo");
			
			if(pageNo==null || pageNo.equals("")){
				pageNo = "1";
			}
			
			
			PaginationInfo page = new PaginationInfo();
			page.setCurrentPageNo(Integer.parseInt(pageNo));
			page.setRecordCountPerPage(5); //게시물 No
			page.setPageSize(5);
			
			int currentPageNo = page.getCurrentPageNo();
			int recordCountPerPage = page.getRecordCountPerPage(); // 한 페이지에 게시되는 게시물 건수
			InputMap.put("currentPageNo", currentPageNo);
			InputMap.put("recordCountPerPage", recordCountPerPage);
			
			boardService.getList(InputMap);
			int listCount = 0;
			listCount = Integer.parseInt(InputMap.get("list_count").toString());
			page.setTotalRecordCount(listCount); //전체 게시물 건 수
			
			list = (ArrayList<HashMap<String, Object>>) InputMap.get("ref_cursor");
			model.addAttribute("board", list);
			model.addAttribute("paginationInfo", page);
			model.addAttribute("pageNo", pageNo);
			
			return "admin/list";
		}
	}
	
	@RequestMapping(value = "/admin/detail.do" , method = RequestMethod.GET)
	public String getDetail(@RequestParam("id") long id, HttpServletRequest request, ModelMap model) throws Exception{
		if(request.getSession().getAttribute("ADMIN") == null){
			request.getSession().invalidate();
			return "redirect:/login.do";
		} else {
			HashMap<String, Object> InputMap = new HashMap<String, Object>();
			InputMap.put("id", id);
			
			boardService.updateView(InputMap);
			
			Board board = boardService.getDetail(InputMap);
			
			model.addAttribute("detail", board);
			
			return "admin/detail";
		}
	}
	
	@RequestMapping(value = "/admin/edit.do" , method = RequestMethod.GET)
	public String editPage(@RequestParam("id") long id, HttpServletRequest request, ModelMap model) throws Exception{
		if(request.getSession().getAttribute("ADMIN") == null){
			request.getSession().invalidate();
			return "redirect:/login.do";
		} else {
			HashMap<String, Object> InputMap = new HashMap<String, Object>();
			InputMap.put("id", id);
			
			Board board = boardService.getDetail(InputMap);
			
			model.addAttribute("detail", board);
			return "admin/edit";
		}
	}
	
	@RequestMapping(value = "/admin/edit.do" , method = RequestMethod.POST)
	public String editBoard(@RequestParam("id") long id, @RequestParam("title") String title, 
			@RequestParam("content") String content,
			HttpServletRequest request, ModelMap model) throws Exception{
		if(request.getSession().getAttribute("ADMIN") == null){
			request.getSession().invalidate();
			return "redirect:/login.do";
		} else {
			HashMap<String, Object> InputMap = new HashMap<String, Object>();
			
			final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			//알아서 파일 가져옴
			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			
			String uploadPath = fileUploadProperty.getProperty("file.uploadFile.path");
			File saveFolder = new File(uploadPath);
			
			//없으면 폴더 생성
			if(!saveFolder.exists() || saveFolder.isFile()) {
				saveFolder.mkdir();
			}
			
			Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
			
			MultipartFile multipartFile;
			String filePath = "";
			String fileName = "";
			
			while(itr.hasNext()){
				Entry<String, MultipartFile> entry = itr.next();
				multipartFile = entry.getValue();
				if(!"".equals(multipartFile.getOriginalFilename())){
					fileName = multipartFile.getOriginalFilename();
					filePath = uploadPath + "\\" + fileName;
					multipartFile.transferTo(new File(filePath));
				}
			}
			
			String newContent = StringEscapeUtils.unescapeHtml4(content);
			
			InputMap.put("title", title);
			InputMap.put("content", newContent);
			InputMap.put("filename", fileName);
			InputMap.put("fileurl", "");
			InputMap.put("id", id);
			
			boardService.updateList(InputMap);
			
			return "redirect:/admin/list.do";
		}
	}
	
	@RequestMapping(value = "/admin/create.do" , method = RequestMethod.GET)
	public String createPage(HttpServletRequest request, ModelMap model){
		if(request.getSession().getAttribute("ADMIN") == null){
			request.getSession().invalidate();
			return "redirect:/login.do";
		} else {
			return "admin/create";
		}
	}
	
	@RequestMapping(value = "/admin/create.do" , method = RequestMethod.POST)
	public String createBoard(@RequestParam("title") String title, 
			@RequestParam("content") String content,
			HttpServletRequest request, ModelMap model) throws Exception{
		
		HashMap<String, Object> InputMap = new HashMap<String, Object>();
		String author = (String) request.getSession().getAttribute("ADMIN");
		
		Date date = new Date();

		if(author == null){
			request.getSession().invalidate();
			return "redirect:/login.do";
		} else {
			
			if(title.equals("") || content.equals("")){
				return "redirect:/admin/create.do";
			} else {
				
				final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				
				//알아서 파일 가져옴
				final Map<String, MultipartFile> files = multiRequest.getFileMap();
				
				String uploadPath = fileUploadProperty.getProperty("file.uploadFile.path");
				File saveFolder = new File(uploadPath);
				
				//없으면 폴더 생성
				if(!saveFolder.exists() || saveFolder.isFile()) {
					saveFolder.mkdir();
				}
				
				Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
				
				MultipartFile multipartFile;
				String filePath = "";
				String fileName = "";
				
				while(itr.hasNext()){
					Entry<String, MultipartFile> entry = itr.next();
					multipartFile = entry.getValue();
					if(!"".equals(multipartFile.getOriginalFilename())){
						fileName = multipartFile.getOriginalFilename();
						filePath = uploadPath + "\\" + fileName;
						multipartFile.transferTo(new File(filePath));
					}
				}
				
				String newContent = StringEscapeUtils.unescapeHtml4(content);
				InputMap.put("title", title);
				InputMap.put("content", newContent);
				InputMap.put("author", author);
				InputMap.put("regdate", date);
				InputMap.put("hit", 0);
				InputMap.put("filename", fileName);
				InputMap.put("fileurl", "");

				boardService.createList(InputMap);
				
				return "redirect:/admin/list.do";
			}
			
		}
	}
	
	@RequestMapping(value = "/admin/delete.do", method = RequestMethod.POST)
	public String deleteLists(@RequestParam("del-id") long[] ids) throws Exception {
		if(ids.length == 0){
			return "redirect:/admin/list.do";
		}
		List<Long> list = new ArrayList<Long>();
		
		for(long i: ids){
			list.add(i);
		}
		
		boardService.deleteList(list);

		return "redirect:/admin/list.do";
	}
	
	@RequestMapping(value = "/admin/downloadFile.do")
	public void downloadFile(@RequestParam("filename") String filename,
			HttpServletResponse response) throws Exception{
		
		String uploadPath = fileUploadProperty.getProperty("file.uploadFile.path");
		
		File uFile = new File(uploadPath, filename);
		int fSize = (int) uFile.length();
		
		if(fSize > 0){
			BufferedInputStream in = new BufferedInputStream(
					new FileInputStream(uFile));
			//String mimetype = servletContext.getMimeType(requestedFile);
			String mimetype = "text/html";
 
			response.setBufferSize(fSize);
			response.setContentType(mimetype);
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ filename + "\"");
			response.setContentLength(fSize);
 
			FileCopyUtils.copy(in, response.getOutputStream());
			in.close();
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
		
		
	}
}
