package com.member.anno.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("dao")
public class MemberDAOImpl implements MemberDAO{
	@Autowired
	private JdbcTemplate template; //setter는 만들지 않는다. (Repository 해놔서 그럼)

	
	//전체보기
	@Override
	public List<MemberVO> getMemberList() {
		// TODO Auto-generated method stub
		String sql = "select * from springmember";
		List<MemberVO> userList = template.query(sql, new MemberMapper()); //sql 실행해서 그 값을 rs하는것을 mapper에가서 한다음에 여기로 다시 돌아옴
		return userList;
	}

	//상세보기
	@Override
	public MemberVO detail(String id) {
		// TODO Auto-generated method stub
		String sql = "select * from springmember where id = ?";
		MemberVO vo = template.queryForObject(sql, new Object[] {id}, new MemberMapper()); //List형은 query 단일은 Object
		return vo;
	}

	//추가하기
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
