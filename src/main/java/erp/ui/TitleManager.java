package erp.ui;

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

import erp.dto.Employee;
import erp.dto.Title;
import erp.service.TitleService;
import erp.ui.content.TitlePanel;
import erp.ui.exception.InvalidCheckException;
import erp.ui.exception.NotSelectedException;
import erp.ui.exception.SqlConstraintException;
import erp.ui.list.TitleTablePanel;

public class TitleManager extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnAdd;
	private TitlePanel pCotent;
	private JPanel pBtns;
	private JButton btnClear;
	private TitleTablePanel pList;
	private TitleService service;

	public TitleManager() {
		service = new TitleService();
		initialize();
	}

	private void initialize() {
		setTitle("직책관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		pCotent = new TitlePanel();
		contentPane.add(pCotent);

		pBtns = new JPanel();
		contentPane.add(pBtns);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);

		btnClear = new JButton("취소");
		pBtns.add(btnClear);

		pList = new TitleTablePanel();
		pList.setService(service);
		pList.loadData();
		contentPane.add(pList);

		JPopupMenu popupMenu = createPopupMenu();
		pList.setPopupMenu(popupMenu);
	}
	

	private JPopupMenu createPopupMenu() {
		JPopupMenu popupMenu = new JPopupMenu();

		JMenuItem update = new JMenuItem("수정");
		update.addActionListener(popupMenuListner);
		popupMenu.add(update);
		JMenuItem delete = new JMenuItem("삭제");
		delete.addActionListener(popupMenuListner);
		popupMenu.add(delete);
		JMenuItem empListByTitleItem = new JMenuItem("동일직책 사원");
		empListByTitleItem.addActionListener(popupMenuListner);
		popupMenu.add(empListByTitleItem);
		return popupMenu;
	}

	ActionListener popupMenuListner = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getActionCommand().equals("삭제")) {
					Title delTitle = pList.getItem();
					service.removeTitle(delTitle);
					JOptionPane.showMessageDialog(null, delTitle + "삭제완료");
					pList.loadData();
				}
				if (e.getActionCommand().equals("수정")) {
					pCotent.setTitle(pList.getItem());
					pCotent.getTfTitleNo().setEditable(false);
					btnAdd.setText("수정");
				}
				if (e.getActionCommand().equals("동일직책 사원")) {
					Title empTilte = pList.getItem();
					List<Employee> empList = service.showEmpByTitle(empTilte);
					Object[] arr = empList.toArray();
					
//					JOptionPane.showMessageDialog(null, empList, "동일직책사원", JOptionPane.QUESTION_MESSAGE);
					
					JOptionPane.showInputDialog(null, "", "동일직책사원", JOptionPane.QUESTION_MESSAGE, null, arr, JOptionPane.NO_OPTION);
				}
			} catch (NotSelectedException | SqlConstraintException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(null, "사원이 없습니다.");
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}

	};

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnAdd) {
				if (e.getActionCommand().equals("추가"))
					actionPerformedBtnAdd(e);
				if (e.getActionCommand().equals("수정"))
					actionPerformedBtnUp(e);

			}
		} catch (InvalidCheckException | SqlConstraintException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
//			pCotent.clearTf();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	protected void actionPerformedBtnUp(ActionEvent e) {

		Title title = pCotent.getTitle();
		service.upTitle(title);
		JOptionPane.showMessageDialog(null, title + "수정완료");
		pList.loadData();
		pCotent.clearTf();
		btnAdd.setText("추가");
		pCotent.getTfTitleNo().setEditable(true);
	}

	protected void actionPerformedBtnAdd(ActionEvent e) {

		Title title = pCotent.getTitle();
		service.addTitle(title);
		JOptionPane.showMessageDialog(null, title + "추가완료");
		pList.loadData();
		pCotent.clearTf();
	}
}
