package com.edu.springboot.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

/*
 * 이 어노테이션도 스프링 컨테이너 시작시 자동으로 빈이 생성된다.
 * @Controller, @Service와 동일한 역할을 한다.
 * */
@Repository
/*IMemberService 인터페이스를 구현했으므로 정의된 추상 메서드는 
 * 일괄적으로 오버라이딩 해야한다. 컨트롤러에서 서비스 인터페이스를 통해
 * DAO의 각 메서드를 호출하게된다.(상속이 되면 부모의 추상 메서드를 통해 오버라이딩 된
 * 자식의 메서드를 호출할 수 있다.)*/
public class MemberDAO implements IMemberService{
	
	/*
	 * JDBC 작업을 위해 자동 주입 받는다. JdbcTemplate 빈은 개발자가 직접
	 * 설정하지 않고, build.gradle에 의존성추가가 되어있으면 스프링 컨테이너가
	 * 자동으로 빈을 생성해준다.
	 * */
	
	/*추상 메서드를 가지고 있는 클래스는 abstract를 선언해야 한다.
	 * abstract는 인스턴스화 시킬 수 없다.
	 * 또는 부모의 추상메서드를 전부 오버라이딩 해야한다.*/
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	//회원목록
	@Override
	public List<MemberDTO> select(){
		//회원 레코드를 가입일을 기준으로 내림차순 정렬하여 인출
		String sql = "select * from member"
				+ " order by regidate desc";
		
		/*query() 메서드를 통해 select문을 실행한다.쿼리문 실행 후 반환되는
		 * ResultSet은 RowMapper가 자동으로 반복하여 DTO에 저장한 후 
		 * 이를 List에 추가해서 반환해준다.
		 * 즉 레코드를 List에 저장하기 위한 반복적인 작업을 자동으로 수행해준다.*/
		return jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class));
		
	}

	
	//입력처리
	@Override
	public int insert(MemberDTO memberDTO) {
		/*
		insert , update,delete와 같이 행의 변화가 생기는 쿼리문을
		update() 메서드를 사용한다. 실행 후 적용된 행의 갯수가 int 형으로 
		반환된다.
		 */
		int result = jdbcTemplate.update(
				/*
				 * PreparedStatementCreator 인터페이스로 익명클래스를 생성한 후
				 * 오버라이딩 된 메서드 내에서 쿼리문을 실행하고 결과를 반환한다.
				 * */
				new PreparedStatementCreator() {
					
					@Override
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						
						//인파라미터가 있는 쿼리문 작성
						String sql = "insert into member(id,pass,name)"
								+" values (?, ?, ?)";
						//인파라미터를 세팅한 후 쿼리문을 실행한다.
						PreparedStatement psmt = con.prepareStatement(sql);
						psmt.setString(1, memberDTO.getId());
						psmt.setString(2, memberDTO.getPass());
						psmt.setString(3, memberDTO.getName());

						//insert 쿼리문 실행 후 결과를 컨트롤러로 반환한다.
						return psmt;
					}
				});
		return result;
	}


	//정보수정1: 기존 회원정보 불러오기 
	//회원 조회
	@Override
	public MemberDTO selectOne(MemberDTO memberDTO) {
		String sql = "select * from member where id=?";
		/*
		 * queryForObject 메서드는 반드시 하나의 결과가 있어야하므로
		 * 결과가 없는 경우에는 예외가 발생한다. 따라서 try~catch로
		 * 감싸는게 좋다.
		 * */
		try {
			/*
			 * queryForObject() : 하나의 결과를 반환하는 select를 실행할때 사용
			 * 인수1 : 쿼리문 
			 * 인수2 : RowMapper(인출한 ResultSet을 DTO에 자동으로 입력
			 * 인수3 : 인파라미터에 설정할 값을 배열로 선언
			 * */
		memberDTO = jdbcTemplate.queryForObject(sql,
				new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class),
				new Object[] { memberDTO.getId()});
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return memberDTO;
		
	}


	//수정처리
	@Override
	public int update(MemberDTO memberDTO) {
	//인파라미터가 있는 쿼리문 작성
	String sql = "update member set pass=?, name=? where id=?";
	/*PreparedStatementSetter 인터페이스의 익명클래스를 이용해서
		인파라미터를 설정하고 쿼리문을 실행한다.
	*/
	int result = jdbcTemplate.update(sql,new PreparedStatementSetter() {
		
		@Override
		public void setValues(PreparedStatement ps) throws SQLException {
			// TODO Auto-generated method stub
			ps.setString(1, memberDTO.getPass());
			ps.setString(2, memberDTO.getName());
			ps.setString(3, memberDTO.getId());
		}
	});
	return result;
	
	}


	@Override
	public int delete(MemberDTO memberDTO) {
		String sql = "delete from member where id=?";
		//update() 함수에서 인파라미터 설정시 Object 클래스의 배열 사용
		int result = jdbcTemplate.update(sql,
				new Object[] {memberDTO.getId()});
		return result;
	}
		
}
