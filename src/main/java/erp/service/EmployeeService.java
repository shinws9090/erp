package erp.service;

import java.util.List;

import erp.dao.DepartmentDao;
import erp.dao.EmployeeDao;
import erp.dao.TitleDao;
import erp.dao.impl.DepartmentDaoImpl;
import erp.dao.impl.EmployeeDaoImpl;
import erp.dao.impl.TitleDaoImpl;
import erp.dto.Department;
import erp.dto.Employee;
import erp.dto.Title;

public class EmployeeService {
	private DepartmentDao deptDao = DepartmentDaoImpl.getInstance();
	private TitleDao TitleDao = TitleDaoImpl.getInstance();
	private EmployeeDao empDao = EmployeeDaoImpl.getInstance();
	
	public List<Department> showDeptList(){
		return deptDao.selectDepartmentByAll();
	}
	public List<Title> showTitleList(){
		return TitleDao.selectTitleByAll();
	}
	
	public List<Employee> showEmpListByDept(Department department){
		return empDao.selectJoinEmployeeBydepartment(department);
	}
	public List<Employee> showEmpListByTitle(Title title){
		return empDao.selectJoinEmployeeByTitle(title);
	}
	
	
	
}
