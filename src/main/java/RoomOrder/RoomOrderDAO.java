package RoomOrder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class RoomOrderDAO implements RoomOrderDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cactus");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
		private static final String INSERT_STMT = 
			"INSERT INTO room_order (room_order_date, room_order_status, room_amount, check_in_date, check_out_date) VALUES (?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT room_order_id, member_id, promotion_id, room_order_date, room_order_status, room_amount, pay_amount, promotion_price,  check_in_date, check_out_date,id_confirm, room_order_id_req from room_order order by room_order_id";
		private static final String GET_ONE_STMT = 
			"SELECT room_order_id, member_id, room_order_date, room_order_status, room_amount, pay_amount, promotion_price,  check_in_date, check_out_date,id_confirm, room_order_id_req from room_order where room_order_date = ?";
		private static final String DELETE = 
			"DELETE FROM room_order where room_order_id = ?";
		private static final String UPDATE = 
			"UPDATE room_order SET promotion_id=?, room_amount=?, pay_amount=?, promotion_price=? WHERE check_in_date = ?";
		private static final String UPDATE_BLOB = "UPDATE 　room_order SET id_confirm = ? where member_id = ?";
		
		
	@Override
	public void insert(RoomOrderVO roomorderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setDate(1, roomorderVO.getRoomOrderDate());
			pstmt.setBoolean(2, roomorderVO.getRoomOrderStatus());
			pstmt.setInt(3, roomorderVO.getRoomAmount());
			pstmt.setDate(4, roomorderVO.getCheckInDate());
			pstmt.setDate(5, roomorderVO.getCheckInDate());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	
	@Override
	public void update(RoomOrderVO roomorderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, roomorderVO.getPromotionId());
			pstmt.setInt(2, roomorderVO.getRoomAmount());
			pstmt.setInt(3, roomorderVO.getPayAmount());
			pstmt.setInt(4, roomorderVO.getPromotionPrice());
			
			pstmt.setDate(5, java.sql.Date.valueOf("2024-04-04"));

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer roomorderid) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, roomorderid);

			pstmt.executeUpdate();
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	
	
	public void updateblob (RoomOrderVO roomorderVO) {
	
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_BLOB);
			
			InputStream img = getPictureStream("items/FC_Real_Madrid.png");
			pstmt.setBinaryStream(1,img);
			pstmt.setInt(2, 12);
			
			pstmt.executeUpdate();
			img.close();
			System.out.println("新增成功");
			
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException | IOException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	
	
	
	@Override
	public RoomOrderVO findByPrimaryKey(Integer roomorderid) {

		RoomOrderVO roomorderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, roomorderid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				roomorderVO = new RoomOrderVO();
				roomorderVO.setRoomOrderId(rs.getInt("room_order_id"));
				roomorderVO.setMemberId(rs.getInt("membre_id"));
				roomorderVO.setPromotionId(rs.getInt("promotion_id"));
				roomorderVO.setRoomOrderDate(rs.getDate("room_order_date"));
				roomorderVO.setRoomOrderStatus(rs.getBoolean("room_order_status"));
				roomorderVO.setRoomAmount(rs.getInt("rooom_amount"));
				roomorderVO.setPayAmount(rs.getInt("pay_amount"));
				roomorderVO.setPromotionPrice(rs.getInt("promotion_price"));
				roomorderVO.setCheckInDate(rs.getDate("check_in_date"));
				roomorderVO.setCheckOutDate(rs.getDate("check_out_date"));
				roomorderVO.setRoomOrderIdReq(rs.getString("room_order_id_req"));
				
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return roomorderVO;
	}
	
	
	@Override
	public List<RoomOrderVO> getAll() {
		
		List<RoomOrderVO> list = new ArrayList<RoomOrderVO>();
		RoomOrderVO roomorderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				roomorderVO = new RoomOrderVO();
				roomorderVO.setRoomOrderId(rs.getInt("room_order_id"));
				roomorderVO.setMemberId(rs.getInt("membre_id"));
				roomorderVO.setPromotionId(rs.getInt("promotion_id"));
				roomorderVO.setRoomOrderDate(rs.getDate("room_order_date"));
				roomorderVO.setRoomOrderStatus(rs.getBoolean("room_order_status"));
				roomorderVO.setRoomAmount(rs.getInt("rooom_amount"));
				roomorderVO.setPayAmount(rs.getInt("pay_amount"));
				roomorderVO.setPromotionPrice(rs.getInt("promotion_price"));
				roomorderVO.setCheckInDate(rs.getDate("check_in_date"));
				roomorderVO.setCheckOutDate(rs.getDate("check_out_date"));
				roomorderVO.setRoomOrderIdReq(rs.getString("room_order_id_req"));
				
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}
		

		public static InputStream getPictureStream(String path) throws IOException {
			FileInputStream fis = new FileInputStream(path);
			return fis;
		}
		

}