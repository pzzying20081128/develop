package cn.zy.apps.tools.units.powers;

import java.util.ArrayList ;
import java.util.List ;

public class TreeData {
	protected String id;
	protected String text;
	protected boolean leaf;
	protected String cls;
	protected String powers;
	// private boolean checked=false;
	private List<TreeData> children = new ArrayList<TreeData>();

	public TreeData(String id, String text, boolean leaf, String cls) {
		super();
		this.id = id;
		this.text = text;
		this.leaf = leaf;
		this.cls = cls;
	}

	public TreeData(String id, String text, boolean leaf, String cls, String powers) {
		super();
		this.id = id;
		this.text = text;
		this.leaf = leaf;
		this.cls = cls;
		this.powers = powers;
	}

	public String getPowers() {
		return powers;
	}

	public void setPowers(String powers) {
		this.powers = powers;
	}

	public List<TreeData> getChildren() {
		return children;
	}

	public void setChildren(List<TreeData> children) {
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

}
