package ca.myseneca.rmi.server;

import java.rmi.*;
import java.util.ArrayList;
import ca.myseneca.model.*;


/**
* The DAMgrImpl class implements all methods  in DAMgrInft 
* Call respond method in DAManager class.
* @author  Ge Yang, Bohao Liu, Yan Liu
* @version 1.0
* @since   2016-03-12 
*/
public class DAMgrImpl extends java.rmi.server.UnicastRemoteObject
implements DAMgrIntf{
	protected DAMgrImpl() throws java.rmi.RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getEmployeeID(String user, String password) throws java.rmi.RemoteException
	{
		int result = DAManager.getEmployeeID(user, password);
		return result;
	}
	public  void addEmployee(Employee emp) throws java.rmi.RemoteException{
		DAManager.addEmployee(emp);
	}
	public  ArrayList<Employee> getAllEmployees() throws java.rmi.RemoteException{
		ArrayList<Employee> allEmps=		DAManager.getAllEmployees();
		return allEmps;
	}
	public  ArrayList<Employee> getEmployeesByDepartmentID (int depid) throws java.rmi.RemoteException
	{
		ArrayList<Employee> deptEmps=DAManager.getEmployeesByDepartmentID(depid);
		return deptEmps;
		
	}
	public  Employee getEmployeeByID(int empid) throws java.rmi.RemoteException{
		Employee emp=DAManager.getEmployeeByID(empid);
		return emp;
	}
	public  int updateEmployee(Employee emp) throws java.rmi.RemoteException{
		int result=DAManager.updateEmployee(emp);
		return result;
	}
	public  int deleteEmployeeByID(int empid) throws java.rmi.RemoteException{
		int result = DAManager.deleteEmployeeByID(empid);
		return result;
	}
	public boolean batchUpdate(String[] SQLs) throws java.rmi.RemoteException{
		boolean result= DAManager.batchUpdate(SQLs);
		return result;
	}
}
