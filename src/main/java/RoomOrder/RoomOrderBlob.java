package RoomOrder;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomOrderBlob {

	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/cactus?serverTimezone=Asia/Taipei";
	public static final String USER = "root";
	public static final String PASSWORD = "ciaJB";

	private static final String SQL = "UPDATE room_order SET id_confirm = ? WHERE room_order_id = ?";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(SQL);

			byte[] pic = getPictureByteArray("src/main/webapp/images/FC_Barcelona.png");

			pstmt.setBytes(1, pic);
			pstmt.setInt(2, 3); // 假設要設置 room_order_Id 的值為 2

			pstmt.executeUpdate();
			System.out.println("新增成功");

		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace(); // 適當地處理錯誤，例如日誌記錄或者拋出異常
		} finally {
			// 依建立順序關閉資源 (越晚建立越早關閉)
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}
