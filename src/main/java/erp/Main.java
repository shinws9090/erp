package erp;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp.ui.DeprManager;
import erp.ui.EmployeeManager;
import erp.ui.TitleManager;

public class Main extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnTitle;
	private JButton btnDept;
	private JButton btnEmp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 182);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		btnTitle = new JButton("직책관리");
		btnTitle.addActionListener(this);
		contentPane.add(btnTitle);
		
		btnDept = new JButton("부서관리");
		btnDept.addActionListener(this);
		contentPane.add(btnDept);
		
		btnEmp = new JButton("사원관리");
		btnEmp.addActionListener(this);
		contentPane.add(btnEmp);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEmp) {
			actionPerformedBtnEmp(e);
		}
		if (e.getSource() == btnDept) {
			actionPerformedBtnDept(e);
		}
		if (e.getSource() == btnTitle) {
			actionPerformedBtnTitle(e);
		}
	}
	protected void actionPerformedBtnTitle(ActionEvent e) {
		TitleManager frame = new TitleManager();
		frame.setVisible(true);
	}
	protected void actionPerformedBtnDept(ActionEvent e) {
		DeprManager frame = new DeprManager();
		frame.setVisible(true);
	}
	protected void actionPerformedBtnEmp(ActionEvent e) {
		EmployeeManager frame = new EmployeeManager();
		frame.setVisible(true);
		
	}
}
