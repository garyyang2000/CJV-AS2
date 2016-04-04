package ca.myseneca.rmi.server;

import java.rmi.RemoteException;
import java.util.ArrayList;
import ca.myseneca.model.Employee;

/**
* The DAMgrIntf interface defines all of 
* the remote features offered by the server .
* 
* @author  Ge Yang, Bohao Liu, Yan Liu
* @version 1.0
* @since   2016-03-12 
*/
public interface DAMgrIntf extends java.rmi.Remote{
	public  int getEmployeeID (String user, String password)throws java.rmi.RemoteException;
	public  void addEmployee(Employee emp)throws java.rmi.RemoteException;
	public  ArrayList<Employee> getAllEmployees()throws java.rmi.RemoteException;
	public  ArrayList<Employee> getEmployeesByDepartmentID (int depid)throws java.rmi.RemoteException;
	public  Employee getEmployeeByID(int empid)throws java.rmi.RemoteException;
	public  int updateEmployee(Employee emp)throws java.rmi.RemoteException;
	public  int deleteEmployeeByID(int empid)throws java.rmi.RemoteException;
	public boolean batchUpdate(String[] SQLs)throws java.rmi.RemoteException;
}
