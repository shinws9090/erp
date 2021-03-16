package erp.ui;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import erp.dto.Employee;
import erp.service.EmployeeService;
import erp.ui.content.AbstractContentPanel;
import erp.ui.content.EmployeePanel;
import erp.ui.list.AbstractCustomTablePanel;
import erp.ui.list.EmployeeTablePanel;

public class EmployeeManager extends AbstractManager<Employee> {
	private EmployeeService service;

	@Override
	public void setService() {
		service = new EmployeeService();
	}

	@Override
	protected void tableLoadData() {
		pList.loadData();
	}

	@Override
	public AbstractContentPanel<Employee> getItemPanel() {
		EmployeePanel empPanel = new EmployeePanel();
		empPanel.setService(service);
		return empPanel;
	}

	@Override
	public AbstractCustomTablePanel<Employee> getItemTable() {
		EmployeeTablePanel empTable = new EmployeeTablePanel();
		empTable.setService(service);
		return empTable;
	}

	@Override
	protected void actionPerformedMenuDelete() {
		Employee delItem = pList.getItem();
		service.removeEmployee(delItem);
		JOptionPane.showMessageDialog(null, delItem + "삭제완료");
		pList.loadData();

	}

	@Override
	protected void actionPerformedMenuUpdate() {
		pContent.setItem(pList.getItem());
		btnAdd.setText("수정");
		((EmployeePanel) pContent).getTfNo().setEditable(false);

	}

	@Override
	protected void actionPerformedMenuGubun() {
		throw new UnsupportedOperationException("구현 안함");

	}

	@Override
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Employee item = pContent.getItem();
		service.addEmployee(item);
		JOptionPane.showMessageDialog(null, item + "추가완료");
		pList.loadData();
		pContent.clearTf();

	}

	@Override
	protected void actionPerformedBtnUp(ActionEvent e) {
		Employee item = pContent.getItem();
		service.modifyEmployee(item);
		JOptionPane.showMessageDialog(null, item + "수정완료");
		pList.loadData();
		pContent.clearTf();
		btnAdd.setText("추가");
		((EmployeePanel) pContent).getTfNo().setEditable(true);

	}

}
