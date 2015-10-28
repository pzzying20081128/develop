package cn.zy.apps.tools.units ;

public abstract class SimpleAutoWritePrpertiesObjectService extends AutoWritePrpertiesObjectService {

    private String idfield ;

    public SimpleAutoWritePrpertiesObjectService(String regexPackage, String idfield) {
        super(regexPackage) ;
        this.idfield = idfield ;

    }
    
    
    @Override
    protected abstract boolean isEqualsParents(Object child, Object parents);

//    @Override
//    protected abstract boolean isEqualsParents(Object child, Object parents) {
//
//        Object id = readFieldValue(idfield, child) ;
//
//        Object parentsId = readFieldValue(idfield, parents) ;
//
////        if (id == null || parentsId == null) return true ;
////
////        else
//           
//
//            return id.equals(parentsId) ;
//    }

}
