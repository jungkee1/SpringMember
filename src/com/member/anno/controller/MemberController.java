package com.member.anno.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.member.anno.model.MemberDAOImpl;
import com.member.anno.model.MemberVO;

@Controller //이거 쓰면 annotation되서 자동으로 extends AbstractController 해준거랑 같은 효과
public class MemberController {
	@Autowired // -> Repository 를 찾아감 [DAOImpl] (그래서 setter 필요없음)
	private MemberDAOImpl dao;
	
	//test 용
	@RequestMapping("/test.go")
	public ModelAndView test(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("data","anno controller에서 지정한 값");
		mv.setViewName("result"); 
		//mv.setViewName("WEB-INF/view/result.jsp"); //본래 이렇게 쓰지만 servlet.xml에 설정을 통에 위와같이 간단하게 사용가능
		return mv;
	}
	
	//insert-form
	@RequestMapping("/insert.go") //default하면 get방식 밑에는 post방식으로 들어감 
	public ModelAndView insert() {
		return new ModelAndView("memberInsert");
	}
	
	
	//insert
	@RequestMapping(value="/insert.go", method=RequestMethod.POST)
	public ModelAndView insert(MemberVO user) { //넘어오는 값을 받는 곳이 (MemberVO형 user로 주입 되어서 받아짐 = req.getParameter 
		dao.insert(user);
		//return null; //null로 해놓으면 자동으로 Mapping 으로감 insert.go
		return new ModelAndView("redirect:list.go");
	}
	
	//list
	@RequestMapping("/list.go")
	public ModelAndView list() {
		List<MemberVO> vo = dao.getMemberList();
		ModelAndView mv = new ModelAndView();
		mv.addObject("vo",vo);
		mv.setViewName("list"); //경로 쓰는곳인데 xml에 설정해놔서 이렇게만 써도 알아 먹는다
		return mv;	//실제로 리턴하는 곳
		
	}
	
	//delete
	@RequestMapping("/delete.go")
	public ModelAndView delete(String id) { //(Sting id로 넘어오는 id를 바로 받음
		dao.delete(id);
		return new ModelAndView("redirect:list.go");
	}
	
	//detail
	@RequestMapping("/detail.go")
	public ModelAndView detail(String id) {
		MemberVO vo = dao.detail(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("vo", vo);
		mv.setViewName("detail");
		return mv;
	}
	
	//update
	@RequestMapping("/update.go")
	public ModelAndView update(MemberVO vo) {
		dao.update(vo);
		return new ModelAndView("redirect:list.go");
	}

}
