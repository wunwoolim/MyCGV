package com.mycgv_jsp.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mycgv_jsp.service.BoardService;
import com.mycgv_jsp.service.PageServiceImpl;
import com.mycgv_jsp.vo.BoardVo;

@Controller
public class BoardController {	
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private PageServiceImpl pageService;
	
	/**
	 * board_delete_proc.do - �Խñ� ���� ó��
	 * 	 
	 * */
	@RequestMapping(value="/board_delete_proc.do", method=RequestMethod.POST)
	public String board_delete_proc(String bid) {
		String viewName = "";
		int result = boardService.getDelete(bid);
		if(result == 1){
			viewName = "redirect:/board_list.do";
		}else {
			//
		}
		
		return viewName;
	}
	
	/**
	 * board_delete.do - �Խñ� ���� ��
	 * 	 
	 * */
	@RequestMapping(value="/board_delete.do", method=RequestMethod.GET)
	public ModelAndView board_delete(String bid) {
		ModelAndView model = new ModelAndView();
		model.addObject("bid", bid);
		model.setViewName("/board/board_delete");
		return model;
	}
	
	
	/**
	 * board_update_proc.do - �Խñ� ���� ó��	 * 
	 * 	 */
	@RequestMapping(value="/board_update_proc.do", method=RequestMethod.POST)
	public String board_update_proc(BoardVo boardVo) {
		String viewName = "";
		int result = boardService.getUpdate(boardVo);
		if(result == 1){
			viewName = "redirect:/board_list.do";
		}else {
			//���������� ȣ��
		}
		
		return viewName;
	}
	
	
	
	/**
	 * board_update.do - �Խñ� ���� ��
	 */
	@RequestMapping(value="/board_update.do", method=RequestMethod.GET)
	public ModelAndView board_update(String bid) {
		ModelAndView model = new ModelAndView();
		BoardVo boardVo = boardService.getSelect(bid);
		
		model.addObject("boardVo", boardVo);
		model.setViewName("/board/board_update");
		
		return model;
	}
	
	/**
	 * board_write_proc.do - �Խñ� �۾��� ó��
	 */
	@RequestMapping(value="/board_write_proc.do", method=RequestMethod.POST)
	public String board_write_proc(BoardVo boardVo, HttpServletRequest request)
										throws Exception{
		String viewName = "";
		
		//bfile, bsfile ���ϸ� ����
		//������ ������ġ
		String root_path = request.getSession().getServletContext().getRealPath("/");
		String attach_path = "\\resources\\upload\\";  
		
		if(boardVo.getFile1().getOriginalFilename() != null
				&& !boardVo.getFile1().getOriginalFilename().equals("")) {  //������ �����ϸ�
			
			//BSFILE ���� �ߺ� ó��
			UUID uuid = UUID.randomUUID();
			String bfile = boardVo.getFile1().getOriginalFilename();
			String bsfile = uuid + "_" + bfile;
			
			System.out.println(root_path + attach_path);
			System.out.println("bfile-->"+bfile);
			System.out.println("bsfile-->"+bsfile);	
			
			boardVo.setBfile(bfile);
			boardVo.setBsfile(bsfile);
		}else {
			System.out.println("���� ����");
		}		
		
		
		int result = boardService.getInsert(boardVo);
		if(result == 1){
	
			//������ �����ϸ� ������ ����
			File saveFile = new File(root_path + attach_path+ boardVo.getBsfile());
			boardVo.getFile1().transferTo(saveFile);			
			
			viewName = "redirect:/board_list.do";
		}else {
			//���� ������ ȣ��
		}
		return viewName;		
	}
	
	
	/**
	 * board_write.do - �Խñ� �۾���
	 */
	@RequestMapping(value="/board_write.do", method=RequestMethod.GET)
	public String board_write() {
		return "/board/board_write";
	}
	
	/**
	 * board_content.do - �Խñ� �� ����
	 */
	@RequestMapping(value="/board_content.do", method=RequestMethod.GET)
	public ModelAndView board_content(String bid) {
		ModelAndView model = new ModelAndView();
		
		BoardVo boardVo = boardService.getSelect(bid);
		if(boardVo != null) {
			boardService.getUpdateHits(bid);
		}
		
		model.addObject("bvo", boardVo);
		model.setViewName("/board/board_content");
		
		return model;
	}
	
	//header �Խ���(JSON) ȣ��Ǵ� �ּ�
	@RequestMapping(value="/board_list_json.do", method=RequestMethod.GET)
	public String board_list_json() {
		return "/board/board_list_json";
	}	
	
	
	/**
	 * board_list_json_data.do - ajax���� ȣ��Ǵ� �Խñ� ��ü ����Ʈ(JSON) 
	 * @return
	 */
	@RequestMapping(value="/board_list_json_data.do", method=RequestMethod.GET,
						produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String board_list_json_data(String page) {		
		Map<String, Integer> param = pageService.getPageResult(page, "board");
		ArrayList<BoardVo> list 
			= boardService.getSelect(param.get("startCount"), param.get("endCount"));
		
		//list ��ü�� �����͸� JSON ���·� ����
		JsonArray jarray = new JsonArray();
		JsonObject jlist = new JsonObject();
		
		for(BoardVo boardVo : list) {
			JsonObject jobj = new JsonObject();  //{}
			jobj.addProperty("rno", boardVo.getRno()); //{rno:1}
			jobj.addProperty("btitle", boardVo.getBtitle()); //{rno:1, btitle:"df"}
			jobj.addProperty("bhits", boardVo.getBhits());
			jobj.addProperty("id", boardVo.getId());
			jobj.addProperty("bdate", boardVo.getBdate());
			
			jarray.add(jobj);
		}	
		jlist.add("jlist", jarray);	
		jlist.addProperty("totals", param.get("dbCount"));
		jlist.addProperty("pageSize", param.get("pageSize"));
		jlist.addProperty("maxSize", param.get("maxSize"));
		jlist.addProperty("page", param.get("page"));		
		
		return new Gson().toJson(jlist);
	}
	
	/**
	 * board_list.do - �Խñ� ��ü ����Ʈ 
	 * @return
	 */
	@RequestMapping(value="/board_list.do", method=RequestMethod.GET)
	public ModelAndView board_list(String page) {
		ModelAndView model = new ModelAndView();		
		Map<String, Integer> param = pageService.getPageResult(page, "board");		
		ArrayList<BoardVo> list 
			= boardService.getSelect(param.get("startCount"), param.get("endCount"));
	
		model.addObject("list", list);
		model.addObject("totals", param.get("dbCount"));
		model.addObject("pageSize", param.get("pageSize"));
		model.addObject("maxSize", param.get("maxSize"));
		model.addObject("page", param.get("page"));
		
		model.setViewName("/board/board_list");
		
		return model;
	}
	
}//BoardController






