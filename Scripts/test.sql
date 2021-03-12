select deptno,deptname,floor from department;


drop view VW_EMPLOYEE;

CREATE OR REPLACE VIEW 
VW_EMPLOYEE
as
SELECT e.empno, 
	   e.empname, 
	   t.tno , 
	   t.tname as title, 
	   e.manager as manager_no, 
	   m.empname as manager, 
	   e.salary,
	   d.deptno ,
	   d.deptname as dept,
	   d.floor 
FROM EMPLOYEE e JOIN TITLE t ON e.TITLE = t.TNO
	LEFT JOIN EMPLOYEE m ON e.MANAGER = m.EMPNO
	JOIN DEPARTMENT d ON e.dept = d.DEPTNO ;
	
select empname,empno
	from employee e 
	join title t 
	on e.title =t.tno 
	where t.tno = 5;