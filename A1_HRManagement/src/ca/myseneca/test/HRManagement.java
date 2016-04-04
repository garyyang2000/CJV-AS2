package ca.myseneca.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import ca.myseneca.model.*;
/**
* The is a test class that test all the method in DAMServer class. 
* Do the same work as the HTManagement class does.
* Prompt user input a user name and a password from the console. 
* If pass the credential check, show the user’s info as employee.
* Also show all the employees in the same department.
* Then ask user to input new employee's information, then ask user
* to update information.
* Finally run batch sql statements to test the batch update method.
* 
* @author  Ge Yang, Bohao Liu, Yan Liu
* @version 1.0
* @since   2016-03-12 
*/
public class HRManagement {
	public static void main(String args[]) {
		Scanner inputReader = new Scanner(System.in);
		try {
			System.out.println("Please enter user name: ");
			String userName = inputReader.next();
			System.out.println("Please enter pass word: ");
			String password = inputReader.next();
			int emp_id = DAManager.getEmployeeID(userName, password);
			if (emp_id > 0) {
				System.out.println("Here is the information of  " + emp_id);
				Employee emp1 = DAManager.getEmployeeByID(emp_id);
				System.out.println(emp1);

				int dept_id = emp1.getDepartmentId();
				System.out.println("Here is the department id: " + dept_id);
				ArrayList<Employee> dept1 = DAManager.getEmployeesByDepartmentID(dept_id);
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
				DAManager.addEmployee(emp1);

				dept1 = DAManager.getAllEmployees();

				System.out.print("Please enter emp email : ");
				String email = inputReader.next();
				System.out.print("Please enter emp phone : ");
				String phone = inputReader.next();
				emp1.setEmail(email);
				emp1.setPhoneNumber(phone);
				if (DAManager.updateEmployee(emp1) > 0) {
					System.out.println("Update successfully! ");
					
				}else{
					System.out.println("Update failed...... ");
				}
				System.out.println("Here is all the employees : ");
				for (Employee emp2 : dept1) {
					System.out.println(emp2);
				}
				DAManager.deleteEmployeeByID(emp_id);
				
				String[] SQLs = new String[3];
				SQLs[0]="DELETE FROM EMPLOYEES WHERE EMPLOYEE_ID=202";
				SQLs[1]="INSERT INTO EMPLOYEES(EMPLOYEE_ID,FIRST_NAME , LAST_NAME ,EMAIL , HIRE_DATE, JOB_ID ,MANAGER_ID,DEPARTMENT_ID  )"
						+"VALUES(299,Jon,Jones,jones@gmail.com, TO_DATE('21-SEP-1989', 'dd-MON-yyyy'),MK_REP,202,20)";
				SQLs[2]="UPDATE EMPLOYEES SET MANAGER_ID=110 WHERE EMPLOYEE_ID=299";
				System.out.println("Now testing batch : ");
				for(int i=0;i<SQLs.length;i++)
				{
					System.out.println(SQLs[i]);
				}
				DAManager.batchUpdate(SQLs);				
			}else{
				System.out.println("Sorry, the user and password can not match. ");
			}
		} catch (Exception ex) {
			System.err.println("Sorry, failed " + ex.toString());
		} finally {
			inputReader.close();
		}
	}
}
