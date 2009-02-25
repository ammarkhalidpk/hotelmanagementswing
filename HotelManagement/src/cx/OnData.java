package cx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;

//针对数据库，进行数据操作
public class OnData {

	private Connection con;
	public infoPack get;

	public OnData() {
		// 构造方法
	}

	public boolean OnData(String sAdd) {

		String mAdd = sAdd;
		try {
			// 连接数据库
			con = ConnectionDatabase.getConnection();
			System.out.println("已经进入数据库连接——添加客房基本信息……");
			String strSql = "INSERT INTO guestroomBase VALUES(?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(strSql);

			// 调用"对输入数据进行封装" 的方法
			this.usePack();
			ps.setInt(1, Types.NULL);
			ps.setInt(2, this.get.getNum());
			ps.setString(3, get.getType());
			ps.setString(4, get.getPlace());
			ps.setFloat(5, get.getPrice());
			ps.setString(6, get.getInfo());

			// ResultSet rs = ps.executeQuery(strSql);
			// ArrayList al = new ArrayList();
			// while (rs.next())
			// {
			// int num = rs.getInt("客房编号");
			// String type = rs.getString("客房类型");
			// String place = rs.getString("客房位置");
			// float price = rs.getFloat("客房单价");
			// String info = rs.getString("备份信息");
			// al.add(new infoPack(num, type, place, price, info));

			// }
			// rs.close();

			int count = ps.executeUpdate();
			ps.close();

			// 根据执行结果返回true/false
			return (count > 0 ? true : false);
		} catch (Exception e) {
			e.printStackTrace();
			return (false);
		} finally {
			try {
				ConnectionDatabase.close(con);
				System.out.println("添加客房基本信息时成功关闭数据库的连接");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("数据库的连接尚未关闭！");
			}
		}
	}

	public void usePack() {
		try {
			String n = BaseInfo.bAdd.txtNum.getText().trim();// 使用原窗体对象
			int num = Integer.parseInt(n);
			float price = Float.parseFloat(BaseInfo.bAdd.txtPrice.getText()
					.trim());
			String type = BaseInfo.bAdd.txtType.getText().trim();
			String place = BaseInfo.bAdd.txtPlace.getText().trim();
			String info = BaseInfo.bAdd.txtArea.getText().trim();

			if (info.equals("")) {
				info = null;
			}
			// 对输入的数据进行封装
			get = new infoPack(num, type, place, price, info);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

}