package erp.ui.list;

import javax.swing.SwingConstants;

import erp.dto.Title;
import erp.service.TitleService;

public class TitleTablePanel extends AbstractCustomTablePanel<Title> {
	private TitleService service;
	


	@Override
	public String[] getClumnNames() {
		// TODO Auto-generated method stub
		return new String[] { "직책번호	", "직책명" };
	}

	@Override
	protected void setAlingAndWidth() {
		// 컬럼내용 위치정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1);
		// 컬럼별 간격 조정
		setTableCellWidth(100, 500);

	}

	@Override
	public Object[] toArray(Title t) {
		return new Object[] { t.getTno(), t.getTname() };
	}

	@Override
	public void initList() {
		list = service.showTitles();
		
	}

	public void setService(TitleService service) {
		this.service = service;
	}



}
