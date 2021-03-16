package erp.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import erp.dto.Department;
import erp.dto.Employee;
import erp.service.DeptService;
import erp.ui.content.DeptPanel;
import erp.ui.content.AbstractContentPanel;
import erp.ui.content.TitlePanel;
import erp.ui.exception.InvalidCheckException;
import erp.ui.exception.NotSelectedException;
import erp.ui.exception.SqlConstraintException;
import erp.ui.list.AbstractCustomTablePanel;
import erp.ui.list.DeptTablePanel;

public class DeprManager extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnAdd;
	private AbstractContentPanel<Department> pDept;
	private JPanel pBtns;
	private JButton btnClear;
	private AbstractCustomTablePanel<Department> pList;
	private DeptService service;

	public DeprManager() {
		service = new DeptService();
		initialize();
		
	}

	private void initialize() {
		setTitle("부서관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		pDept = new DeptPanel();
		contentPane.add((DeptPanel) pDept);

		pBtns = new JPanel();
		contentPane.add(pBtns);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);

		btnClear = new JButton("취소");
		btnClear.addActionListener(this);
		pBtns.add(btnClear);

		pList = new DeptTablePanel();
		((DeptTablePanel)pList).setService(service);
		contentPane.add(pList);
		pList.loadData();
		
		JPopupMenu popupMenu = createPopupMenu();
		pList.setPopupMenu(popupMenu);

	}

	public JPopupMenu createPopupMenu() {
		JPopupMenu popupMenu = new JPopupMenu();

		JMenuItem update = new JMenuItem("수정");
		update.addActionListener(popupMenuListner);
		popupMenu.add(update);

		JMenuItem delete = new JMenuItem("삭제");
		delete.addActionListener(popupMenuListner);
		popupMenu.add(delete);

		JMenuItem empListDept = new JMenuItem("소속사원");
		empListDept.addActionListener(popupMenuListner);
		popupMenu.add(empListDept);

		return popupMenu;
	}

	ActionListener popupMenuListner = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
			if (e.getActionCommand().equals("수정")) {
				btnAdd.setText("수정");
				pDept.setItem(pList.getItem());
				((DeptPanel) pDept).getTfDeptno().setEditable(false);
				
			}
			if (e.getActionCommand().equals("삭제")) {
				Department department = pList.getItem();
				service.removeDepartment(department);
				JOptionPane.showMessageDialog(null, department + "삭제완료");
				pList.loadData();
			}
			if (e.getActionCommand().equals("소속사원")) {
				Department department = pList.getItem();
				List<Employee>  empList = service.showEmpByDepartment(department);
				Object[] arr = empList.toArray();
				JOptionPane.showInputDialog(null, "", "소속사원", JOptionPane.QUESTION_MESSAGE, null, arr, JOptionPane.NO_OPTION);
			}
			}catch(NotSelectedException | SqlConstraintException e1){
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}catch(NullPointerException e1){
				JOptionPane.showMessageDialog(null, "없듬");
			}catch(Exception e1) {
				e1.printStackTrace();
			}

		}
	};

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnClear) {
			actionPerformedBtnClear(e);
		}
		try {
			if (e.getSource() == btnAdd) {
				if (e.getActionCommand().equals("추가"))
					actionPerformedBtnAdd(e);
				if (e.getActionCommand().equals("수정"))
					actionPerformedBtnUpdate(e);
			}
		}catch(InvalidCheckException |SqlConstraintException e1){
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	private void actionPerformedBtnUpdate(ActionEvent e) {
		Department department = pDept.getItem();
		service.upDepartment(department);
		JOptionPane.showMessageDialog(null, department + "수정완료");
		pList.loadData();
		pDept.clearTf();
		btnAdd.setText("추가");
		((DeptPanel) pDept).getTfDeptno().setEditable(true);
		
	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		Department department = pDept.getItem();
		System.out.println(department);
		service.addDepartment(department);
		JOptionPane.showMessageDialog(null, department + "추가완료");
		pList.loadData();
		pDept.clearTf();
	}
	protected void actionPerformedBtnClear(ActionEvent e) {
		btnAdd.setText("추가");
		pDept.clearTf();
	}
}
