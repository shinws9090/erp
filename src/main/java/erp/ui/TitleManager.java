package erp.ui;

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
	public void setService() {
		service = new TitleService();
		((TitleTablePanel) pList).setService(service);
		pList.loadData();

	}

	@Override
	protected void removeItemService() {
		Title delItem = pList.getItem();
		service.removeTitle(delItem);
		JOptionPane.showMessageDialog(null, delItem + "삭제완료");
		pList.loadData();
	}

	@Override
	protected void addItemService() {
		Title item = pCotent.getItem();
		service.addTitle(item);
		JOptionPane.showMessageDialog(null, item + "추가완료");
		pList.loadData();
		pCotent.clearTf();

	}

	@Override
	protected void updateItemService() {
		Title item = pCotent.getItem();
		service.upTitle(item);
		JOptionPane.showMessageDialog(null, item + "수정완료");
		pList.loadData();
		pCotent.clearTf();
		btnAdd.setText("추가");
		((TitlePanel) pCotent).getTfTitleNo().setEditable(true);

	}

	@Override
	protected void showEqualsByGubun() {
		Title empTilte = pList.getItem();
		List<Employee> empList = service.showEmpByTitle(empTilte);
		Object[] arr = empList.toArray();
		
		
		JOptionPane.showInputDialog(null, "", "동일직책사원", JOptionPane.QUESTION_MESSAGE, null, arr, JOptionPane.NO_OPTION);
	}

	@Override
	public AbstractContentPanel<Title> getItemPanel() {
		return new TitlePanel();
	}

	@Override
	public AbstractCustomTablePanel<Title> getItemTable() {
		return new TitleTablePanel();
	}

	@Override
	protected void clearItem() {
		btnAdd.setText("추가");
		((TitlePanel)pCotent).clearTf();
		
	}
}
