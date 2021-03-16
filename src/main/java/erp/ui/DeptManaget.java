package erp.ui;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JOptionPane;

import erp.dto.Department;
import erp.dto.Employee;
import erp.service.DeptService;
import erp.ui.content.AbstractContentPanel;
import erp.ui.content.DeptPanel;
import erp.ui.list.AbstractCustomTablePanel;
import erp.ui.list.DeptTablePanel;

public class DeptManaget extends AbstractManager<Department> {
	private DeptService service;

	@Override
	protected void setService() {
		service = new DeptService();
		
	}

	@Override
	protected void tableLoadData() {
		((DeptTablePanel)pList).setService(service);
		pList.loadData();
		
	}

	@Override
	protected AbstractContentPanel<Department> getItemPanel() {
		return new DeptPanel();
	}

	@Override
	protected AbstractCustomTablePanel<Department> getItemTable() {
		return new DeptTablePanel();
	}

	@Override
	protected void actionPerformedMenuDelete() {
		Department department = pList.getItem();
		service.removeDepartment(department);
		JOptionPane.showMessageDialog(null, department + "삭제완료");
		pList.loadData();
		
	}

	@Override
	protected void actionPerformedMenuUpdate() {
		btnAdd.setText("수정");
		pContent.setItem(pList.getItem());
		((DeptPanel) pContent).getTfDeptno().setEditable(false);
		
	}

	@Override
	protected void actionPerformedMenuGubun() {
		Department department = pList.getItem();
		List<Employee>  empList = service.showEmpByDepartment(department);
		Object[] arr = empList.toArray();
		JOptionPane.showInputDialog(null, "", "소속사원", JOptionPane.QUESTION_MESSAGE, null, arr, JOptionPane.NO_OPTION);
		
	}

	@Override
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Department department = pContent.getItem();
		System.out.println(department);
		service.addDepartment(department);
		JOptionPane.showMessageDialog(null, department + "추가완료");
		pList.loadData();
		pContent.clearTf();
		
	}

	@Override
	protected void actionPerformedBtnUp(ActionEvent e) {
		Department department = pContent.getItem();
		service.upDepartment(department);
		JOptionPane.showMessageDialog(null, department + "수정완료");
		pList.loadData();
		pContent.clearTf();
		btnAdd.setText("추가");
		((DeptPanel) pContent).getTfDeptno().setEditable(true);
		
	}

}
