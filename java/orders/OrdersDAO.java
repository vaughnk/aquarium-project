package orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import common.JDBCUtil;
import product.ProductDAO;
import product.ProductVO;

public class OrdersDAO {
	Connection conn;
	PreparedStatement pstmt;

	final String SELECTALL = "SELECT * FROM ORDERS ORDER BY O_NUM DESC";
	final String SELECTALL_USER= "SELECT * FROM ORDERS WHERE M_ID=? ORDER BY O_NUM DESC";
	final String SELECTONE = "SELECT * FROM ORDERS WHERE O_NUM = ?";
	//final String SELECTONE = "SELECT * FROM ORDERS O, MEMBER M WHERE O.M_ID = M.M_ID AND O_NUM = ?";
	final String UPDATE_MEMBERID = "UPDATE ORDERS SET M_ID = '알수없음.' WHERE O_NUM = ?";
	final String UPDATE_ORDERS_PAYMENT = "UPDATE ORDERS SET O_PAYMENT = ? WHERE O_NUM = ?";
	final String UPDATE_ORDERS_STATE = "UPDATE ORDERS SET O_STATE = ? WHERE O_NUM = ?";
	// final String UPDATE = "ORDERS SET O_DATE = SYSDATE, O_STATE = ?, O_CNT = ?,
	// O_MEMO = ? WHERE O_NUM = ?";
	final String DELETE = "DELETE FROM ORDERS WHERE O_NUM = ?";
	final String MEMBER_ORDER_LIST = "SELECT" + "	p.P_NAME," + "	p.P_CATEGORY," + "	p.P_PRICE," + "	p.P_INFOIMG,"
			+ "	o.O_STATE," + "	o.O_CNT" + "FROM" + "	PRODUCT p, ORDERS o" + "WHERE" + "	o.M_ID = ?"
			+ "	AND p.P_NUM = o.P_NUM";
	final String AMOUNT_TOTAL = "SELECT" + "? AS CATEGORY," + "	SUM(A.ORDERCNT) AS ORDERCNT,"
			+ "	SUM((A.PRODUCTPRICE * A.ORDERCNT)) AS TOTAL" + "FROM" + "(SELECT" + "	p.PRODCUTCATEGORY,"
			+ "	p.PRODUCTPRICE," + "	o.ORDERCNT" + "FROM" + "	PRODUCT p, ORDERS o"
			+ "WHERE p.PRODUCTNUM = o.PRODUCTNUM) A" + "WHERE A.PRODCUTCATEGORY = ?";

	// productNum으로 해당 product 찾기
	final String SELECT_FIND = "SELECT a.* FROM (SELECT PRODUCT.* FROM ORDERS,PRODUCT "
			+ "WHERE ORDERS.P_NUM = PRODUCT.P_NUM)a WHERE a.P_NUM =?";

	final String INSERT = "INSERT INTO ORDERS VALUES(emp_seq.NEXTVAL,?,?,?,?,SYSDATE,?,?,?,?,?)";
	
	public boolean insert(OrdersVO ovo) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setInt(1, ovo.getProductNum());
			pstmt.setString(2, ovo.getMemberId());
			pstmt.setString(3, ovo.getMemberName());
			pstmt.setString(4, ovo.getMemberPhone());
			pstmt.setInt(5, ovo.getOrdersCnt());
			pstmt.setString(6, ovo.getOrdersMemo());
			pstmt.setInt(7, ovo.getOrdersPayment()); // 결제상태 (Default값 있음)
			pstmt.setString(8, ovo.getOrdersPaymentMethod()); // 결제방식
			pstmt.setInt(9, ovo.getOrdersState()); // 배송상태 (Default값 있음)
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(conn, pstmt);
		}
		return true;
	}

	public boolean update(OrdersVO ovo) {
		conn = JDBCUtil.connect();
		try {
			int res = pstmt.executeUpdate();
			if (ovo.getOrdersPayment() == 1) {
				pstmt = conn.prepareStatement(UPDATE_ORDERS_PAYMENT);
				pstmt.setInt(1, ovo.getOrdersPayment());
				pstmt.setInt(2, ovo.getOrdersNum());
				pstmt.executeUpdate();
			} else if (ovo.getOrdersState() >= 1) {
				pstmt = conn.prepareStatement(UPDATE_ORDERS_STATE);
				pstmt.setInt(1, ovo.getOrdersState());
				pstmt.setInt(2, ovo.getOrdersNum());
			}
//			else {
//				pstmt = conn.prepareStatement(UPDATE);
//				pstmt.setInt(1, ovo.getOrdersState());
//				pstmt.setInt(2, ovo.getOrdersCnt());
//				pstmt.setString(3, ovo.getOrdersMemo());
//				pstmt.executeUpdate();
//			}
			if (res <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(conn, pstmt);
		}
		return true;
	}

//	public boolean delete(OrdersVO ovo) {
//		conn = JDBCUtil.connect();
//		try {
//			pstmt = conn.prepareStatement(UPDATE_MEMBERID);
//			pstmt.setString(1, ovo.getMemberId());
//			pstmt.executeUpdate();
//
//			pstmt = conn.prepareStatement(DELETE);
//			pstmt.setInt(1, ovo.getOrdersNum());
//			int res = pstmt.executeUpdate();
//			if (res <= 0) {
//				return false;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return false;
//		} finally {
//			JDBCUtil.disconnect(conn, pstmt);
//		}
//		return true;
//	}

	public OrdersVO selectOne(OrdersVO ovo) {
		OrdersVO data = null;
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(SELECTONE);
			pstmt.setInt(1, ovo.getOrdersNum());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				data = new OrdersVO();
				data.setOrdersNum(rs.getInt("O_NUM"));
				data.setProductNum(rs.getInt("P_NUM"));
				data.setMemberId(rs.getString("M_ID"));
				data.setMemberName(rs.getString("M_NAME"));
				data.setMemberPhone(rs.getString("M_PHONE"));
				data.setOrdersDate(rs.getDate("O_DATE"));
				data.setOrdersCnt(rs.getInt("O_CNT"));
				data.setOrdersMemo(rs.getString("O_MEMO"));
				data.setOrdersPayment(rs.getInt("O_PAYMENT"));
				data.setOrdersPaymentMethod(rs.getString("O_PAYMENT_METHOD"));
				data.setOrdersState(rs.getInt("O_STATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JDBCUtil.disconnect(conn, pstmt);
		}
		return data;
	}

	public ArrayList<OrdersVO> selectAll(OrdersVO ovo) {
		ArrayList<OrdersVO> datas = new ArrayList<OrdersVO>();
		conn = JDBCUtil.connect();
		try {
			if(ovo.getMemberId()!=null) {
				pstmt=conn.prepareStatement(SELECTALL_USER);
				pstmt.setString(1,ovo.getMemberId());
				System.out.println("selelctAll 로그:"+ovo.getMemberId());
			} else {
				pstmt = conn.prepareStatement(SELECTALL);
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				OrdersVO data = new OrdersVO();
				if(findProduct(rs.getInt("P_NUM"))==null) {
					data.setProductName("단종");
					data.setProductPrice(0); // 추가(productPrice)
					data.setProductCategory("discontinued");
				} else {
					data.setProductName(findProduct(rs.getInt("P_NUM")).getProductName());
					data.setProductPrice(findProduct(rs.getInt("P_NUM")).getProductPrice()); // 추가(productPrice)
					data.setProductCategory(findProduct(rs.getInt("P_NUM")).getProductCategory()); // 추가(productCategory)
				}
				data.setOrdersNum(rs.getInt("O_NUM"));
				data.setProductNum(rs.getInt("P_NUM")); // 추가(productNum)
				data.setMemberId(rs.getString("M_ID"));
				data.setMemberName(rs.getString("M_NAME"));
				data.setOrdersDate(rs.getDate("O_DATE"));
				data.setOrdersState(rs.getInt("O_STATE"));
				data.setOrdersCnt(rs.getInt("O_CNT"));
				data.setOrdersMemo(rs.getString("O_MEMO"));
				data.setOrdersPaymentMethod(rs.getString("O_PAYMENT_METHOD")); // 추가 (결제방식)
				
				datas.add(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(conn, pstmt);
		}
		return datas;
	}

	// productNum으로 해당 product 찾기(추가)
	public ProductVO findProduct(int pNum) {
		conn = JDBCUtil.connect();

		ProductDAO pdao = null;
		ProductVO product = null;

		try {
			pstmt = conn.prepareStatement(SELECT_FIND);
			pstmt.setInt(1, pNum);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {

				ProductVO pvo = new ProductVO();
				pvo.setProductNum(rs.getInt("P_NUM"));
				pdao = new ProductDAO();
				product = pdao.selectOne(pvo);
				System.out.println("로그 셀렉했니? " + pdao.selectOne(pvo));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(conn, pstmt);
		}

		return product;
	}

	public HashMap<String, Object> selectMemberOrderList(OrdersVO ovo) {
		conn = JDBCUtil.connect();
		HashMap<String, Object> map = new HashMap<>();
		try {
			pstmt = conn.prepareStatement(MEMBER_ORDER_LIST);
			pstmt.setString(1, ovo.getMemberId());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				map.put("P_NAME", rs.getString("P_NAME"));
				map.put("P_CATEGORY", rs.getString("P_CATEGORY"));
				map.put("P_PRICE", rs.getString("P_PRICE"));
				map.put("P_INFOIMG", rs.getString("P_INFOIMG"));
				map.put("O_STATE", rs.getString("O_STATE"));
				map.put("O_CNT", rs.getString("O_CNT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(conn, pstmt);
		}
		return map;
	}

	public HashMap<String, Object> amountTotal(String category) {
		conn = JDBCUtil.connect();
		HashMap<String, Object> map = new HashMap<>();
		try {
			pstmt = conn.prepareStatement(AMOUNT_TOTAL);
			pstmt.setString(1, category);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				map.put("CATEGORY", rs.getString("CATEGORY"));
				map.put("O_CNT", rs.getInt("O_CNT"));
				map.put("TOTAL", rs.getInt("TOTAL"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(conn, pstmt);
		}
		return map;
	}

}
