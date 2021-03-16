select deptno,deptname,floor from department;


drop view VW_EMPLOYEE;

CREATE OR REPLACE VIEW 
VW_EMPLOYEE
as
SELECT e.empno, 
	   e.empname, 
	   t.tno as title, 
	   t.tname , 
	   e.manager , 
	   m.empname as mgrname, 
	   e.salary,
	   d.deptno as dept,
	   d.deptname ,
	   d.floor 
FROM EMPLOYEE e JOIN TITLE t ON e.TITLE = t.TNO
	LEFT JOIN EMPLOYEE m ON e.MANAGER = m.EMPNO
	JOIN DEPARTMENT d ON e.dept = d.DEPTNO ;
	
select empname,empno
	from employee e 
	join title t 
	on e.title =t.tno 
	where t.tno = 5;
	
select empname,empno
	from employee e 
	join department d 
	on e.dept = d.deptno 
	where d.deptno = 1;

select *from employee e where empno =1003;