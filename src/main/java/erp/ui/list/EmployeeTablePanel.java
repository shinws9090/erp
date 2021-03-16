package erp.ui.list;

import javax.swing.SwingConstants;

import erp.dto.Employee;
import erp.service.EmployeeService;

public class EmployeeTablePanel extends AbstractCustomTablePanel<Employee> {
	private EmployeeService service;

	@Override
	public String[] getClumnNames() {
		// TODO Auto-generated method stub
		return new String[] { "사원번호", "사원명", "직책", "직속상사", "급여", "부서" };
	}

	@Override
	protected void setAlingAndWidth() {
		// 컬럼내용 위치정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 5);
		setTableCellAlign(SwingConstants.RIGHT, 4);
		// 컬럼별 간격 조정
		setTableCellWidth(100, 100, 100, 200, 150, 100);

	}

	@Override
	public Object[] toArray(Employee t) {
		String manager = String.format("%s(%d)", t.getManager().getEmpname(), t.getManager().getEmpno());
		
		return new Object[] {
				t.getEmpno(), 
				t.getEmpname(),
				String.format("%s(%d)", t.getTitle().getTname(),t.getTitle().getTno()),
				t.getManager().getEmpname() == null?"":manager, 
				t.getSalary(),
				String.format("%s(%d)", t.getDept().getDeptName(),t.getDept().getDeptNo()) };

	}

	@Override
	public void initList() {
		list = service.showEmpListByAll();

	}

	public void setService(EmployeeService service) {
		this.service = service;
	}

}
