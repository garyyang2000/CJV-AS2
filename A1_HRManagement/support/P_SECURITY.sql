create or replace package body P_SECURITY  as
FUNCTION F_SECURITY(
 P_SECID IN SECURITY.SEC_ID%TYPE,
 P_SECPASSWORD IN SECURITY.SEC_PASSWORD%TYPE)
 RETURN NUMBER IS
 p_sec_num number(6):=0;
 BEGIN
	SELECT EMPLOYEE_ID INTO p_sec_num
	FROM security
	WHERE sec_id=P_SECID
	AND sec_password=P_SECPASSWORD;
	return p_sec_num;
exception  when OTHERS then
	return 0;
END F_SECURITY;

PROCEDURE P_EMP_INFO (
 P_EMPLOYEEID IN EMPLOYEES.EMPLOYEE_ID%TYPE,
 p_info OUT cur_EmpInfo) AS
BEGIN
	OPEN p_info FOR
	SELECT * FROM EMPLOYEES
	WHERE	EMPLOYEE_ID = P_EMPLOYEEID;
exception when OTHERS then
	p_info := null;
END P_EMP_INFO;

END P_SECURITY;
