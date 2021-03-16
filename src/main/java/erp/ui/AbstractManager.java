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

import erp.dto.Title;
import erp.service.TitleService;
import erp.ui.content.AbstractContentPanel;
import erp.ui.content.TitlePanel;
import erp.ui.exception.InvalidCheckException;
import erp.ui.exception.NotSelectedException;
import erp.ui.exception.SqlConstraintException;
import erp.ui.list.AbstractCustomTablePanel;
import erp.ui.list.TitleTablePanel;

public abstract class AbstractManager<T> extends JFrame implements ActionListener {

	protected JPanel contentPane;
	protected JButton btnAdd;
	protected JPanel pBtns;
	protected JButton btnClear;

	protected AbstractContentPanel<T> pContent;
	protected AbstractCustomTablePanel<T> pList;



	public AbstractManager() {
		System.out.println("생성--------------------------");
//		service = new TitleService();
		setService();

		initialize();

		tableLoadData();
	}

	protected void initialize() {
		System.out.println("왜 안들어옴");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		pContent = getItemPanel();
		contentPane.add(pContent);

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


	protected JPopupMenu createPopupMenu() {
		JPopupMenu popupMenu = new JPopupMenu();

		JMenuItem update = new JMenuItem("수정");
		update.addActionListener(this);
		popupMenu.add(update);

		JMenuItem delete = new JMenuItem("삭제");
		delete.addActionListener(this);
		popupMenu.add(delete);

		JMenuItem empListByTitleItem = new JMenuItem("동일직책 사원");
		empListByTitleItem.addActionListener(this);
		popupMenu.add(empListByTitleItem);

		return popupMenu;
	}

	public void actionPerformed(ActionEvent e) {

		try {
			// 팝업메뉴 엑션
			if (e.getSource() instanceof JMenuItem) {
				if (e.getActionCommand().equals("삭제")) {
					actionPerformedMenuDelete();
				}
				if (e.getActionCommand().equals("수정")) {
					actionPerformedMenuUpdate();
				}
				if (e.getActionCommand().equals("동일직책 사원")) {
					actionPerformedMenuGubun();
				}
			} else {

				if (e.getSource() == btnClear) {
					actionPerformedBtnClear(e);
				}
				if (e.getSource() == btnAdd) {
					if (e.getActionCommand().equals("추가"))
						actionPerformedBtnAdd(e);
					if (e.getActionCommand().equals("수정"))
						actionPerformedBtnUp(e);

				}
			}
		} catch (InvalidCheckException | NotSelectedException | SqlConstraintException | UnsupportedOperationException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (NullPointerException e1) {
			JOptionPane.showMessageDialog(null, "사원이 없습니다.");
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	protected void actionPerformedBtnClear(ActionEvent e) {
		pContent.clearTf();

		if (btnAdd.getText().contentEquals("수정")) {
			btnAdd.setText("추가");
		}
	}

	// 추상 모음 -------------------------------------------------------

	/**
	 * service = new TitleService();
	 */
	protected abstract void setService();
	/**
	 * ((TitleTablePanel) pList).setService(service);
		pList.loadData();
	 */
	protected abstract void tableLoadData();
	/**
	 * return new TitlePanel();
	 */
	protected abstract AbstractContentPanel<T> getItemPanel();
	/**
	 * return new TitleTablePanel();
	 */
	protected abstract AbstractCustomTablePanel<T> getItemTable();
	
	/**
	 * T delItem = pList.getItem(); 
	 * service.removeTitle(delItem);
	 * JOptionPane.showMessageDialog(null, delItem + "삭제완료"); 
	 * pList.loadData();
	 */
	protected abstract void actionPerformedMenuDelete();
	/**
	 *  pCotent.setItem((Title) pList.getItem());
	 *  ((TitlePanel)pCotent).getTfTitleNo().setEditable(false); 
	 *  btnAdd.setText("수정");
	 */
	protected abstract void actionPerformedMenuUpdate();
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
	protected abstract void actionPerformedMenuGubun();
	/**
	 * Title item = pCotent.getItem();
		service.addTitle(item);
		JOptionPane.showMessageDialog(null, item + "추가완료");
		pList.loadData();
		pCotent.clearTf();
	 */
	protected abstract void actionPerformedBtnAdd(ActionEvent e);
	/**
	 * Title item = pCotent.getItem();
		service.upTitle(item);
		JOptionPane.showMessageDialog(null, item + "수정완료");
		pList.loadData();
		pCotent.clearTf();
		btnAdd.setText("추가");
		((TitlePanel) pCotent).getTfTitleNo().setEditable(true);
	 */
	protected abstract void actionPerformedBtnUp(ActionEvent e);


}
