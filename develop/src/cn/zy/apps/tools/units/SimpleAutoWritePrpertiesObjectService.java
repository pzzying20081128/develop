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
//		System.out.println("  ->   child   "+child.getClass().getName()+"  id  "+id) ;
		Object parentsId = readFieldValue(idfield, parents);
//		  System.out.println("  ->   parents   "+parents.getClass().getName()+"  parentsId  "+parentsId) ;
		return id.equals(parentsId);
	}

}
