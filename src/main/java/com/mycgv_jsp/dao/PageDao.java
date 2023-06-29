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
		//���������� ȣ���� �Ǿ� ���Ǹ� ��𿡼� ����ϴ� �������� �˰������ �Ű����������� �����Ѵ�
		//marper�� ���� sql�̱⶧���� jstl��
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
