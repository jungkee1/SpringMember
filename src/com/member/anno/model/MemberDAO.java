package com.member.anno.model;

import java.util.List;

public interface MemberDAO {
	//interface�� ������������(Implements�� �����Ұ͵��� ���ϴ� ��)
	
	
	//��ü����
	//�󼼺���
	//�߰�
	//����
	//����
	
	public List<MemberVO> getMemberList();
	public MemberVO detail(String id);
	public void insert(MemberVO user);
	public void update(MemberVO user);
	public void delete(String id);

}
