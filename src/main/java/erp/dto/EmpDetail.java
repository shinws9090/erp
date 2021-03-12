package erp.dto;

import java.util.Arrays;
import java.util.Date;

public class EmpDetail {
	private int empno;
	private boolean gender;
	private Date hireDate;
	private byte[] pic;
	public EmpDetail() {
		// TODO Auto-generated constructor stub
	}
	public EmpDetail(int empno) {
		this.empno = empno;
	}
	public EmpDetail(int empno, boolean gender, Date hireDate, byte[] pic) {
		this.empno = empno;
		this.gender = gender;
		this.hireDate = hireDate;
		this.pic = pic;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public byte[] getPic() {
		return pic;
	}
	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	@Override
	public String toString() {
		return String.format("EmpDetail [empno=%s, gender=%s, hireDate=%s, pic=%s]", empno, gender, hireDate,
				Arrays.toString(pic));
	}
	
}
