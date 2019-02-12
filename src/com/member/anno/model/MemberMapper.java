package com.member.anno.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MemberMapper implements RowMapper<MemberVO>{

	@Override
	public MemberVO mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		MemberVO vo = new MemberVO();
		vo.setId(rs.getString("id"));
		vo.setPass(rs.getString("pass"));
		vo.setName(rs.getString("name"));
		vo.setMemo(rs.getString("memo"));
		vo.setAddr(rs.getString("addr"));
		vo.setReg_date(rs.getString("reg_date"));

		return vo;
	}

}
