package cn.zy.apps.tools.units ;

public abstract class SimpleIdAutoWritePrpertiesObjectService extends AutoWritePrpertiesObjectService {

    private String idfield ;

    public SimpleIdAutoWritePrpertiesObjectService(String regexPackage, String idfield) {
        super(regexPackage) ;
        this.idfield = idfield ;

    }

    @Override
    protected boolean isEqualsParents(Object child, Object parents) {
        Object id = readFieldValue(idfield, child) ;
        if (id == null) return true ;

        Object parentsId = readFieldValue(idfield, parents) ;
        if (parentsId == null) return true ;
        
        return id.equals(parentsId) ;
    }

}
