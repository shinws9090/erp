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

import erp.dto.Employee;
import erp.service.EmployeeService;
import erp.ui.content.EmployeePanel;
import erp.ui.list.DeptTablePanel;
import erp.ui.list.TitleTablePanel;

public class TestFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnAdd;
	private EmployeePanel pEmp;
	private JButton btnClear;

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
		setBounds(100, 100, 450, 339);
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
		
		btnClear = new JButton("취소");
		btnClear.addActionListener(this);
		panel_3.add(btnClear);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnClear) {
			actionPerformedBtnClear(e);
		}
		if (e.getSource() == btnAdd) {
			actionPerformedBtnAdd(e);
		}
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
		
		Employee employee = pEmp.getEmployee();
		JOptionPane.showMessageDialog(null, employee.toString2());
		pEmp.clearTf();
		
	}
	protected void actionPerformedBtnClear(ActionEvent e) {
		pEmp.clearTf();
	}
}
