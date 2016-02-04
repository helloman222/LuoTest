import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	public static final String url = "jdbc:mysql://localhost:3306/bbs";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "123qwe";

	public Connection conn = null;

	public UserDao() {
		try {
			Class.forName(name);//ָ����������
			conn = DriverManager.getConnection(url, user, password);//��ȡ����
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	public boolean login(String name,String pwd){
		String sql = "select * from user where name=? and pwd=?";
		PreparedStatement pst = null;
		ResultSet ret = null;
		try {
			pst = conn.prepareStatement(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pst.setString(1, name);
			pst.setString(2, pwd);
			
			ret = pst.executeQuery();
			if(ret.next()){
				System.out.println("��¼�ɹ�");
				return true;
			}else{
				System.out.println("��¼ʧ��");
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(ret!=null)
				ret.close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return false;
	}
	
	public void addUser(String name,String pwd){
		String sql = "insert into user(name,pwd) values (?,?);";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, pwd);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			int num = pst.executeUpdate();
			System.out.println("��Ӱ������:"+num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
	}
	
	public void showAll(){
		String sql = "select * from user";//SQL���
		

		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet ret = pst.executeQuery();//ִ����䣬�õ������
			while (ret.next()) {
				String uid = ret.getString(1);
				String ufname = ret.getString(2);
				System.out.println(uid + "\t" + ufname );
			}//��ʾ����
			ret.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
