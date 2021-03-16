package erp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import erp.ui.content.AbstractContentPanel;
import erp.ui.exception.InvalidCheckException;
import erp.ui.exception.NotSelectedException;
import erp.ui.exception.SqlConstraintException;
import erp.ui.list.AbstractCustomTablePanel;

public abstract class AbstractManager<T> extends JFrame implements ActionListener {

	protected JPanel contentPane;
	protected JButton btnAdd;
	protected JPanel pBtns;
	protected JButton btnClear;

	protected AbstractContentPanel<T> pCotent;
	protected AbstractCustomTablePanel<T> pList;

//	private TitleService service;

	public AbstractManager() {
		System.out.println("생성--------------------------");
		initialize();
//		service = new TitleService();
		setService();
	}

	protected void initialize() {
		System.out.println("왜 안들어옴");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		pCotent = getItemPanel();
		contentPane.add(pCotent);

		pBtns = new JPanel();
		contentPane.add(pBtns);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);

		btnClear = new JButton("취소");
		btnClear.addActionListener(this);
		pBtns.add(btnClear);

		pList = getItemTable();

		contentPane.add(pList);

		JPopupMenu popupMenu = createPopupMenu();
		pList.setPopupMenu(popupMenu);
	}

	/**
	 * ((TitleTablePanel)(pList)).setService(service);
	 */
	public abstract void setService();

	public abstract AbstractContentPanel<T> getItemPanel();

	public abstract AbstractCustomTablePanel<T> getItemTable();

	protected JPopupMenu createPopupMenu() {
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
					removeItemService();
//					T delItem = pList.getItem();
//					service.removeTitle(delItem);
//					JOptionPane.showMessageDialog(null, delItem + "삭제완료");
//					pList.loadData();
				}
				if (e.getActionCommand().equals("수정")) {
					pCotent.setItem((T) pList.getItem());
//					 ((TitlePanel) pCotent).getTfTitleNo().setEditable(false);
					btnAdd.setText("수정");
				}
				if (e.getActionCommand().equals("동일직책 사원")) {

					showEqualsByGubun();
//					T empTilte = (T) pList.getItem();
//					List<Employee> empList = service.showEmpByTitle(empTilte);
//					Object[] arr = empList.toArray();
//					
////					JOptionPane.showMessageDialog(null, empList, "동일직책사원", JOptionPane.QUESTION_MESSAGE);
//					
//					JOptionPane.showInputDialog(null, "", "동일직책사원", JOptionPane.QUESTION_MESSAGE, null, arr, JOptionPane.NO_OPTION);
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
		if (e.getSource() == btnClear) {
			actionPerformedBtnClear(e);
		}
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

		updateItemService();
//		T item = pCotent.getItem();
//		service.upTitle(item);
//		JOptionPane.showMessageDialog(null, item + "수정완료");
//		pList.loadData();
//		pCotent.clearTf();
//		btnAdd.setText("추가");
//		((TitlePanel) pCotent).getTfTitleNo().setEditable(true);
	}

	protected void actionPerformedBtnAdd(ActionEvent e) {

		addItemService();
//		T item = pCotent.getItem();
//		service.addTitle(item);
//		JOptionPane.showMessageDialog(null, item + "추가완료");
//		pList.loadData();
//		pCotent.clearTf();
	}

	/**
	 * service.removeTitle(delItem);
	 */
	protected abstract void removeItemService();

	/**
	 * service.addTitle(item);
	 */
	protected abstract void addItemService();

	/**
	 * service.upTitle(item);
	 */
	protected abstract void updateItemService();

	/**
	 * T empTilte = (T) pList.getItem(); List<Employee> empList =
	 * service.showEmpByTitle(empTilte); Object[] arr = empList.toArray();
	 * 
	 * // JOptionPane.showMessageDialog(null, empList, "동일직책사원",
	 * JOptionPane.QUESTION_MESSAGE);
	 * 
	 * JOptionPane.showInputDialog(null, "", "동일직책사원", JOptionPane.QUESTION_MESSAGE,
	 * null, arr, JOptionPane.NO_OPTION);
	 * 
	 */
	protected abstract void showEqualsByGubun();

	protected void actionPerformedBtnClear(ActionEvent e) {
		clearItem();
	}

	protected abstract void clearItem();
}
