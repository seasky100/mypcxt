package com.pphgzs.domain.DO;

public class pypcxt_unit {

	private String pypcxt_unit_id;
	private String unit_name;
	private String unit_gmt_create;
	private String unit_gmt_modified;

	@Override
	public String toString() {
		return "pypcxt_unit [\npypcxt_unit_id=" + pypcxt_unit_id + ",\nunit_name=" + unit_name + ",\nunit_gmt_create="
				+ unit_gmt_create + ",\nunit_gmt_modified=" + unit_gmt_modified + "\n]";
	}

	public String getPypcxt_unit_id() {
		return pypcxt_unit_id;
	}

	public void setPypcxt_unit_id(String pypcxt_unit_id) {
		this.pypcxt_unit_id = pypcxt_unit_id;
	}

	public String getUnit_name() {
		return unit_name;
	}

	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}

	public String getUnit_gmt_create() {
		return unit_gmt_create;
	}

	public void setUnit_gmt_create(String unit_gmt_create) {
		this.unit_gmt_create = unit_gmt_create;
	}

	public String getUnit_gmt_modified() {
		return unit_gmt_modified;
	}

	public void setUnit_gmt_modified(String unit_gmt_modified) {
		this.unit_gmt_modified = unit_gmt_modified;
	}

}
