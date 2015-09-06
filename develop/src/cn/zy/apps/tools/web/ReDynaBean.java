package cn.zy.apps.tools.web;

import org.apache.commons.beanutils.LazyDynaBean;

public class ReDynaBean extends LazyDynaBean {

	/**
     * 
     */
	private static final long serialVersionUID = 8257067152761123101L;

	private String dir;

	private String sort;

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

}
