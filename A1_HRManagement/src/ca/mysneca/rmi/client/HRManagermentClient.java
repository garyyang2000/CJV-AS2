package ca.mysneca.rmi.client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import ca.myseneca.rmi.server.*;

import ca.myseneca.model.Employee;

/**
* The HRManagermentClient class implements all methods  that can
* be used to operate employees table.
* @author  Ge Yang, Bohao Liu, Yan Liu
* @version 1.0
* @since   2016-03-12 
*/

public class HRManagermentClient {

	public static void main(String[] args) {
		Scanner inputReader = new Scanner(System.in);
		try {
			DAMgrIntf d = (DAMgrIntf) Naming.lookup("rmi://localhost:1199/DAMService");

			System.out.println("Please enter user name: ");
			String userName = inputReader.next();
			System.out.println("Please enter pass word: ");
			String password = inputReader.next();
			int emp_id = d.getEmployeeID(userName, password);
			if (emp_id > 0) {
				System.out.println("Here is the information of  " + emp_id);
				Employee emp1 = d.getEmployeeByID(emp_id);
				System.out.println(emp1);

				int dept_id = emp1.getDepartmentId();
				System.out.println("Here is the department id: " + dept_id);
				ArrayList<Employee> dept1 = d.getEmployeesByDepartmentID(dept_id);
				System.out.println("Here is the employees in this department: ");
				for (Employee emp2 : dept1) {
					System.out.println(emp2);
				}

				System.out.println("Please enter emp id : ");
				emp_id = inputReader.nextInt();
				emp1 = new Employee();
				emp1.setEmployeeId(emp_id);
				System.out.println("Please enter emp first name : ");
				String empName = inputReader.next();
				emp1.setFirstName(empName);
				System.out.println("Please enter emp last name : ");
				empName = inputReader.next();
				emp1.setLastName(empName);
				System.out.println("Please enter emp mail : ");
				empName = inputReader.next();
				emp1.setEmail(empName);
				emp1.setDepartmentId(dept_id);
				emp1.setJob("IT_PROG");
				emp1.setManagerId(202);
				String startDate = "01-02-2013";

				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date date = sdf1.parse(startDate);
				java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
				emp1.setHireDate(sqlStartDate);

				System.out.println("Following employee is added to same department : " + emp1);
				d.addEmployee(emp1);

				dept1 = d.getAllEmployees();

				System.out.print("Please enter emp email : ");
				String email = inputReader.next();
				System.out.print("Please enter emp phone : ");
				String phone = inputReader.next();
				emp1.setEmail(email);
				emp1.setPhoneNumber(phone);
				if (d.updateEmployee(emp1) > 0) {
					System.out.println("Update successfully! ");

				}
				System.out.println("Here is all the employees : ");
				for (Employee emp2 : dept1) {
					System.out.println(emp2);
				}
				d.deleteEmployeeByID(emp_id);
				String[] SQLs = new String[3];
				SQLs[0] = "DELETE FROM EMPLOYEES WHERE EMPLOYEE_ID=202";
				SQLs[1] = "INSERT INTO EMPLOYEES(EMPLOYEE_ID,FIRST_NAME , LAST_NAME ,EMAIL , HIRE_DATE, JOB_ID ,MANAGER_ID,DEPARTMENT_ID  )"
						+ "VALUES(299,Jon,Jones,jones@gmail.com, TO_DATE('21-SEP-1989', 'dd-MON-yyyy'),MK_REP,202,20)";
				SQLs[2] = "UPDATE EMPLOYEES SET MANAGER_ID=110 WHERE EMPLOYEE_ID=299";
				System.out.println("Now testing batch : ");
				for (int i = 0; i < SQLs.length; i++) {
					System.out.println(SQLs[i]);
				}
				d.batchUpdate(SQLs);
			} else {
				System.out.println("Sorry, the user and password can not match. ");
			}
		} catch (MalformedURLException murle) {
			System.out.println(murle);
		} catch (RemoteException re) {
			System.out.println(re);
		} catch (NotBoundException nbe) {
			System.out.println(nbe);
		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			inputReader.close();
		}

	}

}
