package erp.ui.list;

import javax.swing.SwingConstants;

import erp.dto.Department;
import erp.service.DeptService;

public class DeptTablePanel extends AbstractCustomTablePanel<Department> {
	private DeptService service = new DeptService();
	@Override
	public void initList() {
		list = service.showDept();
		
	}

	

	@Override
	public String[] getClumnNames() {
		return new String[] {"번호","이름","위치"};
	}

	@Override
	protected void setAlingAndWidth() {
		// 컬럼내용 위치정렬
				setTableCellAlign(SwingConstants.CENTER, 0, 1);
				// 컬럼별 간격 조정
				setTableCellWidth(100, 500);
	}

	@Override
	public Object[] toArray(Department t) {
		return new Object[] {t.getDeptNo(),t.getDeptName(),t.getFloor()};
	}

}
