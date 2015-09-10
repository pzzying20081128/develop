package cn.zy.apps.tools.units;

public  abstract class SimpleAutoWritePrpertiesObjectService extends AutoWritePrpertiesObjectService {

	private String idfield;

	public SimpleAutoWritePrpertiesObjectService(String regexPackage, String idfield) {
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
