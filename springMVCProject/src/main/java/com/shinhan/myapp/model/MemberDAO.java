package com.shinhan.myapp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shinhan.myapp.vo.MemberDTO;

import net.firstzone.util.DBUtil;

@Repository // '@Component + DAO' 역할 ==> Bean 객체
public class MemberDAO {
	
	@Autowired
	@Qualifier("dataSource")
	DataSource ds;

	Connection conn;
	PreparedStatement st;
	ResultSet rs;
	String select_login = "SELECT * FROM MEMBERS WHERE MEMBER_ID = ?";
	
	public MemberDTO login(String id, String pw) {
		MemberDTO memberDTO = null;
		
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(select_login);
			st.setString(1, id);
			
			rs = st.executeQuery();
			if (rs.next()) {
				String getPW = rs.getString("member_pw");
				
				if (pw.equals(getPW)) { // 로그인 성공
					// memberDTO에 id, pw, name, email 담기
					memberDTO = new MemberDTO(id, pw, rs.getString("member_name"), rs.getString("member_email"));
				} else { // 비밀번호 틀림
					// memberDTO에 들어갈 수 없는 값 넣기
					memberDTO = MemberDTO.builder().member_id("-1").build();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnet(conn, st, rs);
		}
		
		return memberDTO;
	}

}
