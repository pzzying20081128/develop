package cn.zy.apps.tools.units;

public class DefaultsERPSetPrpertiesUnits extends ERPSetPrpertiesUnits {

	private String idfield;

	public DefaultsERPSetPrpertiesUnits(String regexPackage, String idfield) {
		super(regexPackage);
		this.idfield = idfield;

	}
   
	@Override
	protected boolean isEqualsParents(Object child, Object parents) {
		Object id = readFieldValue(idfield, child);

		Object parentsId = readFieldValue(idfield, parents);

		return id.equals(parentsId);
	}

}
