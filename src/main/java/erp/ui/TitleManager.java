package erp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import erp.dto.Employee;
import erp.dto.Title;
import erp.service.TitleService;
import erp.ui.content.EmployeePanel;
import erp.ui.content.AbstractContentPanel;
import erp.ui.content.TitlePanel;
import erp.ui.list.AbstractCustomTablePanel;
import erp.ui.list.TitleTablePanel;

public class TitleManager extends AbstractManager<Title> implements ActionListener {

	private TitleService service;

//	protected void initialize() {
//		setTitle("직책관리");
////		pList = new TitleTablePanel();
////		((TitleTablePanel) (pList)).setService(service);
////		pList.loadData();
////		contentPane.add(pList);
//	}

	@Override
	protected void setService() {
		service = new TitleService();
	}

	@Override
	protected void tableLoadData() {
		((TitleTablePanel) pList).setService(service);
		pList.loadData();
	}

	@Override
	protected AbstractContentPanel<Title> getItemPanel() {
		return new TitlePanel();
	}

	@Override
	protected AbstractCustomTablePanel<Title> getItemTable() {
		return new TitleTablePanel();
	}

	@Override
	protected void actionPerformedMenuDelete() {
		Title delItem = pList.getItem();
		service.removeTitle(delItem);
		JOptionPane.showMessageDialog(null, delItem + "삭제완료");
		pList.loadData();
	}

	@Override
	public void actionPerformedMenuUpdate() {
		pContent.setItem((Title) pList.getItem());
		((TitlePanel) pContent).getTfTitleNo().setEditable(false);
		btnAdd.setText("수정");
	}

	@Override
	protected void actionPerformedMenuGubun() {
		Title empTilte = pList.getItem();
		List<Employee> empList = service.showEmpByTitle(empTilte);
		Object[] arr = empList.toArray();

		JOptionPane.showInputDialog(null, "", "동일직책사원", JOptionPane.QUESTION_MESSAGE, null, arr, JOptionPane.NO_OPTION);
	}

	@Override
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Title item = pContent.getItem();
		service.addTitle(item);
		JOptionPane.showMessageDialog(null, item + "추가완료");
		pList.loadData();
		pContent.clearTf();

	}

	@Override
	protected void actionPerformedBtnUp(ActionEvent e) {
		Title item = pContent.getItem();
		service.upTitle(item);
		JOptionPane.showMessageDialog(null, item + "수정완료");
		pList.loadData();
		pContent.clearTf();
		btnAdd.setText("추가");
		((TitlePanel) pContent).getTfTitleNo().setEditable(true);

	}

}
