package erp.ui.list;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import erp.ui.exception.NotSelectedException;

@SuppressWarnings("serial")
public abstract class AbstractCustomTablePanel<T> extends JPanel {
	protected JTable table;
	protected List<T> list;

	public AbstractCustomTablePanel() {
		initialize();
	}

	public void loadData() {
		initList();
		setList();
	}
	
	public T getItem() {
		int idx = table.getSelectedRow();
		if (idx == -1 ) {
			throw new NotSelectedException();
		}
		return list.get(idx);
	}
	
	public void setPopupMenu(JPopupMenu popupMenu) {
		table.setComponentPopupMenu(popupMenu);
	}
	public abstract void initList();

	private void initialize() {
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
	}



	

	public abstract String[] getClumnNames();
	
	public void setList() {
		Object[][] datas = new Object[list.size()][];
		for(int i = 0; i<datas.length;i++) {
			datas[i] = toArray(list.get(i));
		}
		CustomTableModel model = new CustomTableModel(datas, getClumnNames());
		table.setModel(model);
		
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		table.setRowSorter(sorter);
		
		setAlingAndWidth(); //하위클래스에서 메서드 적어주게하기
		
	}
	/*
	 * // 컬럼내용 위치정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1,2);
		// 컬럼별 간격 조정
		setTableCellWidth(100,500,80);
	 */
	protected abstract void setAlingAndWidth();

	public abstract Object[] toArray(T t);
	
	
	protected void setTableCellWidth(int ...width) {
		TableColumnModel tcm = table.getColumnModel();
		
		for (int i = 0; i < width.length; i++) {
			tcm.getColumn(i).setPreferredWidth(width[i]);
		}
	}
	
	protected void setTableCellAlign(int align, int...idx) {
		TableColumnModel tcm = table.getColumnModel();

		//기본 렌더러
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align); // 가운대 정렬

		for (int i = 0; i < idx.length; i++) {
			tcm.getColumn(idx[i]).setCellRenderer(dtcr);
		}
	}
	
	
	private class CustomTableModel extends DefaultTableModel {

		public CustomTableModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}


}
