package erp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import erp.ui.list.TitleTablePanel;
import java.awt.GridLayout;
import erp.ui.list.DeptTablePanel;

public class TestFrame extends JFrame {

	private JPanel contentPane;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		TitleTablePanel panel = new TitleTablePanel();
		panel.loadData();
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(panel);
		
		DeptTablePanel panel_1 = new DeptTablePanel();
		panel_1.loadData();
		contentPane.add(panel_1);
	}

}
