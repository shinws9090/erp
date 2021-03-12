package erp.service;

import java.util.List;

import erp.dao.EmployeeDao;
import erp.dao.TitleDao;
import erp.dao.impl.EmployeeDaoImpl;
import erp.dao.impl.TitleDaoImpl;
import erp.dto.Employee;
import erp.dto.Title;

public class TitleService {
	private TitleDao dao = TitleDaoImpl.getInstance();
	private EmployeeDao empDao = EmployeeDaoImpl.getInstance();
	public List<Title> showTitles(){
		return dao.selectTitleByAll();
	}
	public void addTitle(Title title) {
		dao.insertTitle(title);
	}
	public void removeTitle(Title title) {
		dao.deleteTitle(title.getTno());
	}
	public void upTitle(Title title) {
		dao.updateTitle(title);
	}
	public List<Employee> showEmpByTitle(Title title){
		return empDao.selectJoinEmployeeByTitle(title);
	}
	
}
