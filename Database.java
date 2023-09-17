package aa;

import java.sql.*;
import java.util.*;

public class Database {
	Connection con = null;
	Statement stmt = null;
	String url = "jdbc:mysql://localhost:3306/asa";	
	String user = "root";
	String passwd = "123456789";		//MySQL에 저장한 root 계정의 비밀번호를 적어주면 된다.
	
	public static void main(String[] args) {
		Database db = new Database();

		/* 데이터베이스 관련 코드는 try-catch문으로 예외 처리를 꼭 해주어야 한다. */
		try {
			//데이터베이스 연결	
			Class.forName("com.mysql.cj.jdbc.Driver");
			db.con = DriverManager.getConnection(db.url, db.user, db.passwd);
			db.stmt = db.con.createStatement();
			
			//메뉴 리스트
			System.out.println("[1] 테이블 생성");
			System.out.println("[2] 데이터 추가");
			System.out.println("[3] 데이터 삭제");
			System.out.println("[4] 데이터 변경");
			System.out.println("[5] 데이터 조회");
			
			System.out.print("= 번호 입력 : ");
			Scanner s = new Scanner(System.in);
			int num = s.nextInt();
			while(true) {
			switch(num) {
				case 1 :
					db.createTable();	//테이블 생성
					break;
				case 2 :
					db.insertData();	//데이터 삽입
					break;
				case 3 :
					db.removeData();	//데이터 삭제
					break;
				case 4 :
					db.changeData();	//데이터 수정
					break;
				case 5 :
					db.viewData();		//데이터 조회
					break;
			}
			break;
			}
		} catch(Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				db.stmt.close();
				db.con.close();				
			} catch(Exception e) {
				System.out.println(e.toString());
			}
		}
	}

	//테이블 생성
	void createTable() {
		try {
			String createStr = "CREATE TABLE user (name varchar(10) not null, id varchar(20) not null,"
							 + " password varchar(20) not null, PRIMARY KEY (id))";
			stmt.execute(createStr);
			System.out.println("테이블 생성 성공!");
		} catch(Exception e) {
			System.out.println("테이블 생성 실패 이유 : " + e.toString());
		}
	}

	//삽입
	void insertData() {
		try {
			String insertStr = "INSERT INTO user VALUES('res', 'asd123', 'qwer1234')";
			stmt.executeUpdate(insertStr);
			System.out.println("데이터 추가 성공!");
		} catch(Exception e) {
			System.out.println("데이터 추가 실패 이유 : " + e.toString());
		}
	}

	//삭제
	void removeData() {
		try {
			String removeStr = "DELETE FROM user where name='이지수'";
			stmt.executeUpdate(removeStr);
			System.out.println("데이터 삭제 성공!");
		} catch(Exception e) {
			System.out.println("데이터 삭제 실패 이유 : " + e.toString());
		}
	}

	//수정
	void changeData() {
		try {
			String changeStr = "UPDATE user SET name='가나다'";
			stmt.executeUpdate(changeStr);
			System.out.println("데이터 변경 성공!");
		} catch(Exception e) {
			System.out.println("데이터 변경 실패 이유 : " + e.toString());
		}
	}

	//조회
	void viewData() {
		try {
			System.out.println("== user 테이블 조회 ==");
			String viewStr1 = "SELECT * FROM user";
			ResultSet result1 = stmt.executeQuery(viewStr1);
			int cnt1 = 0;
			while(result1.next()) {
				System.out.print(result1.getString("name") + "\t" + result1.getString("id")
								 + "\t" + result1.getString("password") + "\n");
				cnt1++;
			}
			
			System.out.println("");
			System.out.println("== students 테이블 조회 ==");
			String viewStr2 = "SELECT * FROM user";
			ResultSet result2 = stmt.executeQuery(viewStr2);
			int cnt2 = 0;
			while(result2.next()) {
				System.out.print(result2.getInt("number") + "\t" + result2.getString("name") + "\t"
								 + result2.getString("gender") + "\t" + result2.getString("department") + "\n");
				cnt2++;
			}
			
			System.out.println("");
			System.out.println("데이터 조회 성공!");
		} catch(Exception e) {
			System.out.println("데이터 조회 실패 이유 : " + e.toString());
		}
	}
}
