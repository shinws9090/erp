package erp.ui;

import java.util.List;

import javax.swing.JOptionPane;

import erp.dto.Employee;
import erp.dto.Title;
import erp.service.EmployeeService;
import erp.ui.content.EmployeePanel;
import erp.ui.content.AbstractContentPanel;
import erp.ui.content.TitlePanel;
import erp.ui.list.AbstractCustomTablePanel;
import erp.ui.list.EmployeeTablePanel;

public class EmployeeManager extends AbstractManager<Employee> {
	private EmployeeService service;
	
	@Override
	public void setService() {
		service = new EmployeeService();
		((EmployeeTablePanel) pList).setService(service);
		((EmployeePanel)pCotent).setService(service);
		pList.loadData();
		
	}

	@Override
	public AbstractContentPanel<Employee> getItemPanel() {
		return new EmployeePanel();
	}

	@Override
	public AbstractCustomTablePanel<Employee> getItemTable() {
		return new EmployeeTablePanel();
	}

	@Override
	protected void removeItemService() {
		Employee delItem = pList.getItem();
		service.removeEmployee(delItem);
		JOptionPane.showMessageDialog(null, delItem + "삭제완료");
		pList.loadData();
		
	}

	@Override
	protected void addItemService() {
		Employee item = pCotent.getItem();
		service.addEmployee(item);
		JOptionPane.showMessageDialog(null, item + "추가완료");
		pList.loadData();
		pCotent.clearTf();
		
	}

	@Override
	protected void updateItemService() {
		Employee item = pCotent.getItem();
		service.upEmployee(item);
		JOptionPane.showMessageDialog(null, item + "수정완료");
		pList.loadData();
		pCotent.clearTf();
		btnAdd.setText("추가");
		
	}

	@Override
	protected void showEqualsByGubun() {
		Title empTilte = pList.getItem().getTitle();
		List<Employee> empList = service.showEmpListByTitle(empTilte);
		Object[] arr = empList.toArray();
		JOptionPane.showInputDialog(null, "", "동일직책사원", JOptionPane.QUESTION_MESSAGE, null, arr, JOptionPane.NO_OPTION);
		
	}

	@Override
	protected void clearItem() {
		btnAdd.setText("추가");
		((EmployeePanel)pCotent).clearTf();
		
	}

}
