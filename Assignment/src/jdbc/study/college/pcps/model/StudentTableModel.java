package jdbc.study.college.pcps.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class StudentTableModel extends AbstractTableModel {
	private List<Student> stds;
	private String[] columns;

	public StudentTableModel(List<Student> stdList) {
		super();
		stds = stdList;
		columns = new String[] { "ID", "NAME", "REGDNO", "SECTION", "AGE" };
	}

	public int getRowCount() {
		return stds.size();
	}

	public int getColumnCount() {
		return columns.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Student std = stds.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return std.getStudentId();
		case 1:
			return std.getName();
		case 2:
			return std.getRegdNo();
		case 3:
			return std.getSection();
		case 4:
			return std.getAge();
		default:
			return null;
		}
	}

	public String getColumnName(int col) {
		return columns[col];
	}
}
