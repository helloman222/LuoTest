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
			Class.forName(name);//指定连接类型
			conn = DriverManager.getConnection(url, user, password);//获取连接
			
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
				System.out.println("登录成功");
				return true;
			}else{
				System.out.println("登录失败");
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
			System.out.println("受影响行数:"+num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
	}
	
	public void showAll(){
		String sql = "select * from user";//SQL语句
		

		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet ret = pst.executeQuery();//执行语句，得到结果集
			while (ret.next()) {
				String uid = ret.getString(1);
				String ufname = ret.getString(2);
				System.out.println(uid + "\t" + ufname );
			}//显示数据
			ret.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
