package erp;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp.dto.Department;
import erp.dto.Employee;
import erp.dto.Title;
import erp.service.EmployeeService;
import erp.ui.content.EmployeePanel;
import erp.ui.exception.InvalidCheckException;
import erp.ui.list.DeptTablePanel;
import erp.ui.list.TitleTablePanel;
import erp.ui.list.EmployeeTablePanel;

public class TestFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnAdd;
	private EmployeePanel pEmp;
	private JButton btnClear;
	private JButton btnSet;
	private EmployeeTablePanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame frame = new TestFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestFrame() {
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		EmployeeService service = new EmployeeService();
		
		pEmp = new EmployeePanel();
		contentPane.add(pEmp);
		pEmp.setService(service);
		
		JPanel panel_3 = new JPanel();
		pEmp.add(panel_3, BorderLayout.SOUTH);
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		panel_3.add(btnAdd);
		
		btnSet = new JButton("Set");
		btnSet.addActionListener(this);
		panel_3.add(btnSet);
		
		btnClear = new JButton("취소");
		btnClear.addActionListener(this);
		panel_3.add(btnClear);
		
		panel = new EmployeeTablePanel();
		panel.setService(service);
		contentPane.add(panel);
		panel.loadData();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSet) {
			actionPerformedBtnSet(e);
		}
		if (e.getSource() == btnClear) {
			actionPerformedBtnClear(e);
		}
		if (e.getSource() == btnAdd) {
			actionPerformedBtnAdd(e);
		}
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
		try {
			Employee employee = pEmp.getItem();
			JOptionPane.showMessageDialog(null, employee.toString2());
			pEmp.clearTf();
			
		}catch(InvalidCheckException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		
	}
	protected void actionPerformedBtnClear(ActionEvent e) {
		pEmp.clearTf();
	}
	protected void actionPerformedBtnSet(ActionEvent e) {
		// 1003	조민희	3	4377	3000000	2
		Employee emp = new Employee(1003,"조민희",new Title(3),new Employee(4377),3000000,new Department(2));
		pEmp.setItem(emp);
	}
}
