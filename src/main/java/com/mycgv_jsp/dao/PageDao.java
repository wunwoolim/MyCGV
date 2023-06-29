package com.mycgv_jsp.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PageDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int totalRowCount(String sname) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("sname", sname);
		
		return sqlSession.selectOne("mapper.page.count",param);
		//여러곳에서 호출이 되어 사용되면 어디에서 사용하는 누구인지 알고싶으면 매개변수값으로 구분한다
		//marper는 동적 sql이기때문에 jstl의
		/*
		 * int count = 0; String sql = "select count(*) from mycgv_notice";
		 * getPreparedStatement(sql);
		 * 
		 * try { rs = pstmt.executeQuery(); while(rs.next()) { count = rs.getInt(1); }
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 * 
		 * return count;
		 */	
	}
}
