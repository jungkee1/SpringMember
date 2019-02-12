package com.member.anno.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.member.anno.model.MemberDAOImpl;
import com.member.anno.model.MemberVO;

@Controller //�̰� ���� annotation�Ǽ� �ڵ����� extends AbstractController ���ذŶ� ���� ȿ��
public class MemberController {
	@Autowired // -> Repository �� ã�ư� [DAOImpl] (�׷��� setter �ʿ����)
	private MemberDAOImpl dao;
	
	//test ��
	@RequestMapping("/test.go")
	public ModelAndView test(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("data","anno controller���� ������ ��");
		mv.setViewName("result"); 
		//mv.setViewName("WEB-INF/view/result.jsp"); //���� �̷��� ������ servlet.xml�� ������ �뿡 ���Ͱ��� �����ϰ� ��밡��
		return mv;
	}
	
	//insert-form
	@RequestMapping("/insert.go") //default�ϸ� get��� �ؿ��� post������� �� 
	public ModelAndView insert() {
		return new ModelAndView("memberInsert");
	}
	
	
	//insert
	@RequestMapping(value="/insert.go", method=RequestMethod.POST)
	public ModelAndView insert(MemberVO user) { //�Ѿ���� ���� �޴� ���� (MemberVO�� user�� ���� �Ǿ �޾��� = req.getParameter 
		dao.insert(user);
		//return null; //null�� �س����� �ڵ����� Mapping ���ΰ� insert.go
		return new ModelAndView("redirect:list.go");
	}
	
	//list
	@RequestMapping("/list.go")
	public ModelAndView list() {
		List<MemberVO> vo = dao.getMemberList();
		ModelAndView mv = new ModelAndView();
		mv.addObject("vo",vo);
		mv.setViewName("list"); //��� ���°��ε� xml�� �����س��� �̷��Ը� �ᵵ �˾� �Դ´�
		return mv;	//������ �����ϴ� ��
		
	}
	
	//delete
	@RequestMapping("/delete.go")
	public ModelAndView delete(String id) { //(Sting id�� �Ѿ���� id�� �ٷ� ����
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
