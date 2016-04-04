/**
* The DAManager class implements all methods  that can
* be used to operate employees table.
* @author  Ge Yang, Bohao Liu, Yan Liu
* @version 1.0
* @since   2016-03-12 
*/
package ca.myseneca.model;

import java.sql.*;
import java.util.ArrayList;
import java.math.BigDecimal;
import oracle.jdbc.*;

public class DAManager {

	public static int getEmployeeID(String user, String password) {
		int result = -1;
		try {
			DBUtil dbUtil = new DBUtil();
			Connection conn = dbUtil.ConnectOracleNeptune();
			CallableStatement stmt = conn.prepareCall("{?=call P_SECURITY.F_SECURITY(?,?)}");
			stmt.setInt(1, result);
			stmt.setString(2, user);
			stmt.setString(3, password);
			stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
			stmt.executeQuery();
			result = stmt.getInt(1);
			SQLWarning w = stmt.getWarnings();
			DBUtil.printWarnings(w);
			stmt.close();
			conn.close();

		} catch (BatchUpdateException batchEx) {
			DBUtil.printBatchUpdateException(batchEx);
		} catch (SQLException sqlEx) {
			DBUtil.printSQLException(sqlEx);

		} catch (Exception ex) {
			System.err.println("Failed to get :" + ex.toString());
		}
		return result;
	}

	public static void addEmployee(Employee emp) {
		try {
			DBUtil dbUtil = new DBUtil();
			Connection conn = dbUtil.ConnectOracleNeptune();

			String sql = "INSERT INTO EMPLOYEES " + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp.getEmployeeId());
			String fname = emp.getFirstName();
			if (fname != null) {
				pstmt.setString(2, fname);
			} else {
				pstmt.setNull(2, OracleTypes.NULL);
			}

			pstmt.setString(3, emp.getLastName());
			pstmt.setString(4, emp.getEmail());
			// String phone = emp.getPhoneNumber()
			// pstmt.setNull(5, Types.NULL);

			/*
			 * pstmt.setNull(6, Types.NULL); pstmt.setNull(7, Types.NULL);
			 * pstmt.setNull(8, Types.NULL); pstmt.setNull(9, Types.NULL);
			 * pstmt.setNull(10,Types.NULL);
			 */
			pstmt.setString(5, emp.getPhoneNumber());
			pstmt.setDate(6, emp.getHireDate());
			pstmt.setString(7, emp.getJobId());
			pstmt.setBigDecimal(8, emp.getSalary());
			pstmt.setBigDecimal(9, emp.getCommissionPct());
			pstmt.setInt(10, emp.getManagerId());
			pstmt.setInt(11, emp.getDepartmentId());

			pstmt.executeUpdate();
			SQLWarning w = pstmt.getWarnings();
			DBUtil.printWarnings(w);
			pstmt.close();
			conn.close();

		} catch (BatchUpdateException batchEx) {
			DBUtil.printBatchUpdateException(batchEx);
		} catch (SQLException sqlEx) {
			DBUtil.printSQLException(sqlEx);

		} catch (Exception ex) {
			System.err.println("Failed to add employee :" + ex.toString());
		}

	}

	public static ArrayList<Employee> getAllEmployees() {
		ArrayList<Employee> allEmps = new ArrayList<Employee>();

		try {
			DBUtil dbUtil = new DBUtil();
			Connection conn = dbUtil.ConnectOracleNeptune();
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM EMPLOYEES";
			stmt.execute(sql);
			SQLWarning w = stmt.getWarnings();
			DBUtil.printWarnings(w);
			ResultSet rset = stmt.getResultSet();
			while (rset.next()) {
				int empId = rset.getInt(1);
				String fName = rset.getString(2);
				String lName = rset.getString(3);
				String email = rset.getString(4);
				String phone = rset.getString(5);
				Date hrDate = rset.getDate(6);
				String jobID = rset.getString(7);
				BigDecimal sal = rset.getBigDecimal(8);
				BigDecimal commiPct = rset.getBigDecimal(9);
				int mgrID = rset.getInt(10);
				int deptID = rset.getInt(11);
				Employee newEmp = new Employee(empId, fName, lName, email, phone, hrDate, jobID, sal, commiPct, mgrID,
						deptID);
				allEmps.add(newEmp);
			}
			rset.close();
			stmt.close();
			conn.close();

		} catch (BatchUpdateException batchEx) {
			DBUtil.printBatchUpdateException(batchEx);
		} catch (SQLException sqlEx) {
			DBUtil.printSQLException(sqlEx);

		} catch (Exception ex) {
			System.err.println("Failed to add employee :" + ex.toString());
		}
		return allEmps;
	}

	public static ArrayList<Employee> getEmployeesByDepartmentID(int depid) {
		ArrayList<Employee> allEmps = new ArrayList<Employee>();

		try {
			DBUtil dbUtil = new DBUtil();
			Connection conn = dbUtil.ConnectOracleNeptune();
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM EMPLOYEES WHERE DEPARTMENT_ID=" + depid;
			stmt.executeUpdate(sql);
			SQLWarning w = stmt.getWarnings();
			DBUtil.printWarnings(w);
			ResultSet rset = stmt.getResultSet();
			while (rset.next()) {
				int empId = rset.getInt(1);
				String fName = rset.getString(2);
				String lName = rset.getString(3);
				String email = rset.getString(4);
				String phone = rset.getString(5);
				Date hrDate = rset.getDate(6);
				String jobID = rset.getString(7);
				BigDecimal sal = rset.getBigDecimal(8);
				BigDecimal commiPct = rset.getBigDecimal(9);
				int mgrID = rset.getInt(10);
				int deptID = rset.getInt(11);
				Employee newEmp = new Employee(empId, fName, lName, email, phone, hrDate, jobID, sal, commiPct, mgrID,
						deptID);
				allEmps.add(newEmp);

			}
			rset.close();
			stmt.close();
			conn.close();
		} catch (BatchUpdateException batchEx) {
			DBUtil.printBatchUpdateException(batchEx);
		} catch (SQLException sqlEx) {
			DBUtil.printSQLException(sqlEx);
		} catch (Exception ex) {
			System.err.println("Failed to add employee :" + ex.toString());
		}
		return allEmps;
	}

	public static Employee getEmployeeByID(int empid) {
		Employee newEmp = new Employee();
		try {
			DBUtil dbUtil = new DBUtil();
			Connection conn = dbUtil.ConnectOracleNeptune();

			OracleCallableStatement ostmt = (OracleCallableStatement) conn
					.prepareCall("{call P_SECURITY.P_EMP_INFO(?,?)}");
			ostmt.setInt(1, empid);
			ostmt.registerOutParameter(2, OracleTypes.CURSOR);
			ostmt.execute();
			SQLWarning w = ostmt.getWarnings();
			DBUtil.printWarnings(w);
			ResultSet rset = (OracleResultSet) ostmt.getCursor(2);
			if (rset.next()) {
				int empId = rset.getInt(1);
				String fName = rset.getString(2);
				String lName = rset.getString(3);
				String email = rset.getString(4);
				String phone = rset.getString(5);
				Date hrDate = rset.getDate(6);
				String jobID = rset.getString(7);
				BigDecimal sal = rset.getBigDecimal(8);
				BigDecimal commiPct = rset.getBigDecimal(9);
				int mgrID = rset.getInt(10);
				int deptID = rset.getInt(11);
				newEmp = new Employee(empId, fName, lName, email, phone, hrDate, jobID, sal, commiPct, mgrID, deptID);
			}

		} catch (BatchUpdateException batchEx) {
			DBUtil.printBatchUpdateException(batchEx);
		} catch (SQLException sqlEx) {
			DBUtil.printSQLException(sqlEx);

		} catch (Exception ex) {
			System.err.println("Failed to add employee :" + ex.toString());
		}
		return newEmp;
	}

	public static int updateEmployee(Employee emp) {
		int result = -1;
		ResultSet uprs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			DBUtil dbUtil = new DBUtil();
			conn = dbUtil.ConnectOracleNeptune();
			// Statement stmt =
			// conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
			// ResultSet.CONCUR_UPDATABLE);

			String sql = "SELECT EMPLOYEE_ID, FIRST_NAME,LAST_NAME, EMAIL,PHONE_NUMBER,HIRE_DATE, JOB_ID,SALARY,COMMISSION_PCT,MANAGER_ID,DEPARTMENT_ID   FROM EMPLOYEES WHERE EMPLOYEE_ID= ?";
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, emp.getEmployeeId()); // set input values

			uprs = pstmt.executeQuery();
			if (uprs.next()) {

				uprs.updateString("FIRST_NAME", emp.getFirstName());
				uprs.updateString("LAST_NAME", emp.getLastName());
				uprs.updateString("EMAIL", emp.getEmail());
				uprs.updateString("PHONE_NUMBER", emp.getPhoneNumber());
				uprs.updateDate("HIRE_DATE", emp.getHireDate());
				uprs.updateString("JOB_ID", emp.getJobId());
				uprs.updateBigDecimal("SALARY", emp.getSalary());
				uprs.updateBigDecimal("COMMISSION_PCT", emp.getCommissionPct());
				uprs.updateInt("MANAGER_ID", emp.getManagerId());
				uprs.updateInt("DEPARTMENT_ID", emp.getDepartmentId());
				uprs.updateRow();
				result = 1;

			}

			SQLWarning w = pstmt.getWarnings();
			DBUtil.printWarnings(w);

		} catch (Exception ex) {
			System.err.println("Failed to add employee :" + ex.toString());
		} finally {
			try {
				uprs.close();
				pstmt.close();
				conn.close();
			} catch (BatchUpdateException batchEx) {
				DBUtil.printBatchUpdateException(batchEx);
			} catch (SQLException sqlEx) {
				DBUtil.printSQLException(sqlEx);
			}
			return result;
		}
	}

	public static int deleteEmployeeByID(int empid) {
		int result = -1;
		try {
			DBUtil dbUtil = new DBUtil();
			Connection conn = dbUtil.ConnectOracleNeptune();

			String sql = "DELETE FROM EMPLOYEES WHERE EMPLOYEE_ID=" + empid;
			Statement stmt = conn.createStatement();

			result = stmt.executeUpdate(sql);
			SQLWarning w = stmt.getWarnings();
			DBUtil.printWarnings(w);
			stmt.close();
			conn.close();

		} catch (BatchUpdateException batchEx) {
			DBUtil.printBatchUpdateException(batchEx);
		} catch (SQLException sqlEx) {
			DBUtil.printSQLException(sqlEx);

		} catch (Exception ex) {
			System.err.println("Failed to add employee :" + ex.toString());
		}
		return result;
	}

	public static boolean batchUpdate(String[] SQLs) {
		boolean result = false;
		try {
			DBUtil dbUtil = new DBUtil();
			Connection conn = dbUtil.ConnectOracleNeptune();			
			conn.setAutoCommit(false);
			Savepoint savPnt = conn.setSavepoint("savpnt1");
			Statement stmt = conn.createStatement();
			for (int i = 0; i < SQLs.length; i++) {
				stmt.addBatch(SQLs[i]);
			}
			int[] count = stmt.executeBatch();
			if (count.length == SQLs.length) {
				conn.commit();
			} else {
				conn.rollback(savPnt);
			}
			SQLWarning w = stmt.getWarnings();
			DBUtil.printWarnings(w);
			stmt.close();
			conn.close();
		} catch (BatchUpdateException batchEx) {
			DBUtil.printBatchUpdateException(batchEx);
		} catch (SQLException sqlEx) {
			DBUtil.printSQLException(sqlEx);

		} catch (Exception ex) {
			System.err.println("Failed to add employee :" + ex.toString());
		}
		return result;
	}

}
