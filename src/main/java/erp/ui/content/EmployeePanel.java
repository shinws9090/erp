package erp.ui.content;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import erp.dto.Department;
import erp.dto.Employee;
import erp.dto.Title;
import erp.service.EmployeeService;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class EmployeePanel extends JPanel implements ItemListener {
	private JTextField tfNo;
	private JTextField tfName;
	private JComboBox<Title> cmbTitle;
	private JComboBox<Employee> cmbManager;
	private JSpinner spinSalary;
	private JComboBox<Department> cmbDept;
	private EmployeeService service;

	/**
	 * Create the panel.
	 */
	public EmployeePanel() {

		initialize();
	}

	private void initialize() {
		setBorder(
				new TitledBorder(null, "\uC0AC\uC6D0\uC815\uBCF4", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));

		JPanel pDept = new JPanel();
		add(pDept);
		pDept.setLayout(new GridLayout(0, 2, 5, 5));

		JLabel lblNo = new JLabel("사원번호");
		lblNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pDept.add(lblNo);

		tfNo = new JTextField();
		tfNo.setColumns(10);
		pDept.add(tfNo);

		JLabel lblName = new JLabel("사원명");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		pDept.add(lblName);

		tfName = new JTextField();
		tfName.setColumns(10);
		pDept.add(tfName);

		JLabel lblDept = new JLabel("부서");
		lblDept.setHorizontalAlignment(SwingConstants.RIGHT);
		pDept.add(lblDept);

		cmbDept = new JComboBox<>();
		cmbDept.addItemListener(this);
		pDept.add(cmbDept);

		JLabel lblManager = new JLabel("직속상사");
		lblManager.setHorizontalAlignment(SwingConstants.RIGHT);
		pDept.add(lblManager);

		cmbManager = new JComboBox<>();
		pDept.add(cmbManager);

		JLabel lblTitle = new JLabel("직책");
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		pDept.add(lblTitle);

		cmbTitle = new JComboBox<>();
		pDept.add(cmbTitle);

		JLabel lblSalary = new JLabel("급여");
		lblSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		pDept.add(lblSalary);

		spinSalary = new JSpinner();
		spinSalary.setModel(new SpinnerNumberModel(2000000, 1500000, 5000000, 100000));
		pDept.add(spinSalary);

	}

	public void setEmployee(Employee emp) {

//		int empno, String empname, Title title, Employee manager, int salary, Department dept

		tfNo.setText(emp.getEmpno() + "");
		tfName.setText(emp.getEmpname());
		cmbTitle.setSelectedItem(emp.getTitle());
		cmbManager.setSelectedItem(emp.getManager());
		spinSalary.setValue(emp.getSalary());
		cmbDept.setSelectedItem(emp.getDept());
	}

	public Employee getEmployee() {
		int empno = Integer.parseInt(tfNo.getText().trim());
		String empname = tfName.getText().trim();
		Title title = (Title) cmbTitle.getSelectedItem();
		Employee manager = (Employee) cmbManager.getSelectedItem();
		int salary = (int) spinSalary.getValue();
		Department dept = (Department) cmbDept.getSelectedItem();
		return new Employee(empno, empname, title, manager, salary, dept);
	}

	public void clearTf() {
		tfNo.setText("");
		tfName.setText("");
		cmbTitle.setSelectedIndex(-1);
		cmbManager.setSelectedIndex(-1);
		spinSalary.setValue(2000000);
		cmbDept.setSelectedIndex(-1);

	}

	public void setService(EmployeeService service) {
		this.service = service;
		List<Department> deptList = service.showDeptList();
		DefaultComboBoxModel deptModel = new DefaultComboBoxModel<Department>(new Vector<Department>(deptList));
		cmbDept.setModel(deptModel);
		List<Title> titleList = service.showTitleList();
		DefaultComboBoxModel titleModel = new DefaultComboBoxModel<Title>(new Vector<Title>(titleList));
		cmbTitle.setModel(titleModel);
		cmbDept.setSelectedIndex(-1);
		cmbTitle.setSelectedIndex(-1);
		cmbManager.setSelectedIndex(-1);

	}

//	public void actionPerformed(ActionEvent e) {
//		if (e.getSource() == cmbDept) {
//			actionPerformedCmbDept(e);
//		}
//	}
//	protected void actionPerformedCmbDept(ActionEvent e) {
//		if(cmbDept.getSelectedIndex() != -1) {
//		List<Employee> empList = service.showEmpListByDept((Department) cmbDept.getSelectedItem());
//		DefaultComboBoxModel empModel =null;
//		if(empList != null) {
//		 empModel = new DefaultComboBoxModel<Employee>(new Vector<Employee>(empList));
//		 cmbManager.setModel(empModel);
//		}
//		}
//		
//	}
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cmbDept) {
			itemStateChangedCmbDept(e);
		}
	}

	protected void itemStateChangedCmbDept(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			List<Employee> empList = service.showEmpListByDept((Department) cmbDept.getSelectedItem());
			DefaultComboBoxModel empModel = null;
			if (empList != null) {
				empModel = new DefaultComboBoxModel<Employee>(new Vector<Employee>(empList));
				cmbManager.setModel(empModel);
			}else{
				empList = service.showEmpListByTitle(new Title(1,"사장"));
				empModel = new DefaultComboBoxModel<Employee>(new Vector<Employee>(empList));
				cmbManager.setModel(empModel);
			}
		}
	}
}
