package com.member.anno.model;

import java.util.List;

public interface MemberDAO {
	//interface로 만들어놓은거임(Implements에 구성할것들을 정하는 곳)
	
	
	//전체보기
	//상세보기
	//추가
	//수정
	//삭제
	
	public List<MemberVO> getMemberList();
	public MemberVO detail(String id);
	public void insert(MemberVO user);
	public void update(MemberVO user);
	public void delete(String id);

}
