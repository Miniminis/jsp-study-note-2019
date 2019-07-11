package guestBook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import guestBook.Model.Message;
import jdbc.ConnectionProvider;

public class MessageDao {
	
	//싱글톤 처리 
	//* 		1. 외부 직접 참조 및 변경을 사전 차단 
	//* 		2. 이 클래스는 기능클래스로 굳이 객체를 만들어서 참조할 필요가 없음 (참조해야할 특별한 변수가 없음)
	//*			- 매번 객체를 만들어 참조하는 방법 보다는, getInstance() 매서드 호출만을 통해서 클래스에 접근할 수 있도록 한다. 
	
	//1. private 생성자 
	private MessageDao () {}
	
	//2. MessageDao 인스턴스 생성 
	private static MessageDao dao = new MessageDao();
	
	//3. 외부에서 참조할 수 있는 매서드 생성 
	// - 객체 생성 없이 참조하기 위해서 static 
	// - 모든 패키지에서 참조 가능하게 하기 위해서 public 
	public static MessageDao getInstance() {
		return dao;
	}
	
	////////////////////////////////////////////////
	
	//1. insert 기능 : 게시글 추가기능
	public int insert(Connection conn, Message message) {
		String sql = "insert into guestbook_message "
				+ " (message_id, gname, gpassword, gmessage) "
				+ " values (GM_MID_SEQ.nextval, ?,?,?)";
		PreparedStatement pstmt = null;		
		int resultCnt = 0; 
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, message.getGname());
			pstmt.setString(2, message.getGpassword());
			pstmt.setString(3, message.getGmessage());		
			
			resultCnt = pstmt.executeUpdate();
					
		} catch(SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
		return resultCnt;
	}
	
	
	//2. 메시지 선택 기능 
	public Message select(Connection conn, int mid) {
		Message message = null;
		String sql02 = "select * from guestbook_message where message_id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql02);
			
			pstmt.setInt(1, mid);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//사용자가 클릭한 message id 가 데이터 베이스에 있다면, message 객체 생성! 
				message = new Message();
				
				//message 클래스에 데이터베이스에서 받은 데이터를 삽입
				message.setMessage_id(rs.getInt(1));
				message.setGname(rs.getString(2));
				message.setGpassword(rs.getString(3));
				message.setGmessage(rs.getString(4));
				
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		}
		
		//message 타입 반환
		return message;
	}
	
	//3.전체 게시물의 개수 반환 
	public int selectCnt(Connection conn) {
		
		String sql = "select count(*) from guestbook_message";
		Statement stmt = null;
		ResultSet rs = null;
		int totalCnt = 0;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				totalCnt = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return totalCnt;
	}

	public List<Message> selectList(Connection conn, int startRow, int endRow) {
		
		List<Message> list = new ArrayList<Message>();
		
		String sql = "select MESSAGE_ID, GNAME, GPASSWORD, GMESSAGE from ( "
				+ " select rownum rnum, MESSAGE_ID, GNAME, GPASSWORD, GMESSAGE from ( "
				+ " select * from GUESTBOOK_MESSAGE m order by m.message_id desc "
				+ " ) where rownum <= ? "
				+ ") where rnum >= ?";
		//정렬 : message_id 순서대로 먼저 정렬 --> rownum 순서대로 또 정렬
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, startRow);	
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Message msg = new Message();
				msg.setMessage_id(rs.getInt(1));
				msg.setGname(rs.getString(2));
				msg.setGpassword(rs.getString(3));
				msg.setGmessage(rs.getString(4));
				
				list.add(msg);
			}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return list;
	}
	
  
	
}
