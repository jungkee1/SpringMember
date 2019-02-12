package com.member.anno.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("dao")
public class MemberDAOImpl implements MemberDAO{
	@Autowired
	private JdbcTemplate template; //setter�� ������ �ʴ´�. (Repository �س��� �׷�)

	
	//��ü����
	@Override
	public List<MemberVO> getMemberList() {
		// TODO Auto-generated method stub
		String sql = "select * from springmember";
		List<MemberVO> userList = template.query(sql, new MemberMapper()); //sql �����ؼ� �� ���� rs�ϴ°��� mapper������ �Ѵ����� ����� �ٽ� ���ƿ�
		return userList;
	}

	//�󼼺���
	@Override
	public MemberVO detail(String id) {
		// TODO Auto-generated method stub
		String sql = "select * from springmember where id = ?";
		MemberVO vo = template.queryForObject(sql, new Object[] {id}, new MemberMapper()); //List���� query ������ Object
		return vo;
	}

	//�߰��ϱ�
	@Override
	public void insert(MemberVO user) {
		// TODO Auto-generated method stub
		String sql = "insert into springmember values(?,?,?,?,?,sysdate)";
		Object[] param = new Object[] {
				user.getId(),user.getPass(),user.getName(),user.getAddr(),user.getMemo()
		};
		template.update(sql,param);
		
	}

	@Override
	public void update(MemberVO user) {
		// TODO Auto-generated method stub
		String sql = "update springmember set pass=?, name=?, addr=?, memo=? where id = ?";
		Object[] param = new Object[] {
				user.getPass(),user.getName(),user.getAddr(),user.getMemo(),user.getId()
		};
		template.update(sql,param);
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		String sql = "delete from springmember where id = ?";
		template.update(sql,id);	
	}

}
