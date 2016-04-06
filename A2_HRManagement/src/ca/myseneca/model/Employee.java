package ca.myseneca.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
* The Employee class a JavaBean class which is 
* corresponding to Employees table in Oracle database.
* @author  Ge Yang, Bohao Liu, Yan Liu
* @version 1.0
* @since   2016-03-12 
*/

public class Employee implements Serializable {
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", commissionPct=" + commissionPct + ", email=" + email
				+ ", firstName=" + firstName + ", hireDate=" + hireDate + ", lastName=" + lastName + ", managerId="
				+ managerId + ", phoneNumber=" + phoneNumber + ", salary=" + salary + ", department=" + departmentId
				+ ", job=" + jobId + "]";
	}

	private static final long serialVersionUID = 1L;

	private int employeeId;

	private BigDecimal commissionPct;
	private String email;
	private String firstName;
	private Date hireDate;
	private String lastName;
	private int managerId;
	private String phoneNumber;
	private BigDecimal salary;
	private int departmentId;
	private String jobId;

	public Employee() {
	}

	public Employee(int empID, String fname, String lname, String email, String phone, Date hrDate, String jobid,
			BigDecimal sal, BigDecimal commPct, int mgrid, int deptid) {
		this.employeeId=empID;
		this.firstName=fname;
		this.lastName= lname;
		this.email=email;
		this.phoneNumber=phone;
		this.hireDate=hrDate;
		this.jobId=jobid;
		this.salary=sal;
		this.commissionPct=commPct;
		this.managerId=mgrid;
		this.departmentId=deptid;
		
	}

	public int getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public BigDecimal getCommissionPct() {
		return this.commissionPct;
	}

	public void setCommissionPct(BigDecimal commissionPct) {
		this.commissionPct = commissionPct;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getHireDate() {
		return this.hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getManagerId() {
		return this.managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public BigDecimal getSalary() {
		return this.salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public int getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getJobId() {
		return this.jobId;
	}

	public void setJob(String jobId) {
		this.jobId = jobId;
	}

}
