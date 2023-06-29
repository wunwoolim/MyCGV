package com.mycgv_jsp.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycgv_jsp.vo.NoticeVo;

@Repository
public class NoticeDao implements MycgvDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	/**
	 * 전체 로우 카운트 - 페이징 처리
	 * @return
	 */
	/*
	 * public int totalRowCount() { int count = 0; String sql =
	 * "select count(*) from mycgv_notice"; getPreparedStatement(sql);
	 * 
	 * try { rs = pstmt.executeQuery(); while(rs.next()) { count = rs.getInt(1); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return count; }
	 */
	
	/**
	 * updateHits - 공지사항 조회수 업데이트
	 */
	public void updateHits(String nid) {
		sqlSession.update("mapper.notice.updateHits", nid);
		/*
		 * String sql = "update mycgv_notice set nhits=nhits+1 where nid=?";
		 * getPreparedStatement(sql);
		 * 
		 * try { pstmt.setString(1, nid); pstmt.executeUpdate();
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */
		
	}
	
	/**
	 * delete - 공지사항 삭제처리
	 */
	public int delete(String nid) {
		return sqlSession.delete("mapper.notice.delete", nid);
		/*
		 * int result = 0; String sql = "delete from mycgv_notice where nid=?";
		 * getPreparedStatement(sql);
		 * 
		 * try { pstmt.setString(1, nid); result = pstmt.executeUpdate();
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 * 
		 * return result;
		 */
	}
	
	/**
	 * update - 공지사항 수정처리
	 */
	public int update(NoticeVo noticeVo) {
		return sqlSession.update("mapper.notice.update", noticeVo);
		/*
		 * int result = 0; String sql =
		 * "update mycgv_notice set ntitle=?, ncontent=? where nid=? ";
		 * getPreparedStatement(sql);
		 * 
		 * try { pstmt.setString(1, noticeVo.getNtitle()); pstmt.setString(2,
		 * noticeVo.getNcontent()); pstmt.setString(3, noticeVo.getNid());
		 * 
		 * result = pstmt.executeUpdate();
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 * 
		 * return result;
		 */
	}
	
	/**
	 * select(String nid) - 공지사항 상세보기
	 */
	public NoticeVo select(String nid){
		return sqlSession.selectOne("mapper.notice.content", nid);
		/*
		 * NoticeVo noticeVo = new NoticeVo(); String sql =
		 * "select nid, ntitle,ncontent,nhits,ndate from mycgv_notice " +
		 * " where nid=?"; getPreparedStatement(sql);
		 * 
		 * try { pstmt.setString(1, nid); rs = pstmt.executeQuery(); while(rs.next()) {
		 * noticeVo.setNid(rs.getString(1)); noticeVo.setNtitle(rs.getString(2));
		 * noticeVo.setNcontent(rs.getString(3)); noticeVo.setNhits(rs.getInt(4));
		 * noticeVo.setNdate(rs.getString(5)); }
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 * 
		 * return noticeVo;
		 */
	}
	
	
	/**
	 * select - 공지사항 전체 리스트
	 */
	/*
	 * public ArrayList<NoticeVo> select(){ ArrayList<NoticeVo> list = new
	 * ArrayList<NoticeVo>(); String sql =
	 * "select rownum rno, nid, ntitle,nhits, to_char(ndate,'yyyy-mm-dd') ndate " +
	 * " from (select nid, ntitle, nhits, ndate from mycgv_notice " +
	 * "         order by ndate desc) "; getPreparedStatement(sql);
	 * 
	 * try { rs = pstmt.executeQuery(); while(rs.next()) { NoticeVo noticeVo = new
	 * NoticeVo(); noticeVo.setRno(rs.getInt(1)); noticeVo.setNid(rs.getString(2));
	 * noticeVo.setNtitle(rs.getString(3)); noticeVo.setNhits(rs.getInt(4));
	 * noticeVo.setNdate(rs.getString(5));
	 * 
	 * list.add(noticeVo); }
	 * 
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return list; }
	 */
	
	/**
	 * select - 공지사항 전체 리스트(페이징 처리)
	 */
	@Override
	public List<Object> select(int startCount, int endCount){
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("start", startCount);
		param.put("end", endCount);
		
		//List<NoticeVo> list = sqlSession.selectList("mapper.notice.list", param);
		
		return sqlSession.selectList("mapper.notice.list", param);
		/*
		 * ArrayList<NoticeVo> list = new ArrayList<NoticeVo>(); String sql =
		 * " select rno, nid, ntitle, nhits, ndate " +
		 * " from (select rownum rno, nid, ntitle,nhits, to_char(ndate,'yyyy-mm-dd') ndate "
		 * + " 		from (select nid, ntitle, nhits, ndate from mycgv_notice " +
		 * "         order by ndate desc)) " + " where rno between ? and ? ";
		 * getPreparedStatement(sql);
		 * 
		 * try { pstmt.setInt(1, startCount); pstmt.setInt(2, endCount);
		 * 
		 * rs = pstmt.executeQuery(); while(rs.next()) { NoticeVo noticeVo = new
		 * NoticeVo(); noticeVo.setRno(rs.getInt(1)); noticeVo.setNid(rs.getString(2));
		 * noticeVo.setNtitle(rs.getString(3)); noticeVo.setNhits(rs.getInt(4));
		 * noticeVo.setNdate(rs.getString(5));
		 * 
		 * list.add(noticeVo); }
		 * 
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 * 
		 * return list;
		 */
	}
	
	/**
	 * insert - 공지사항 등록
	 */
	public int insert(Object noticeVo) {
		return sqlSession.insert("mapper.notice.insert", noticeVo);
	}
		/*int result = 0;
		String sql = "insert into mycgv_notice(nid,ntitle,ncontent,nhits,ndate) "
		 + " values('n_'||ltrim(to_char(sequ_mycgv_notice.nextval,'0000')),?,?,0,sysdate)";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, noticeVo.getNtitle());
			pstmt.setString(2, noticeVo.getNcontent());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}*/
}
