package cn.zy.apps.tools.units ;

import java.beans.PropertyDescriptor ;
import java.lang.reflect.InvocationTargetException ;
import java.lang.reflect.Method ;
import java.util.HashMap ;
import java.util.List ;
import java.util.Map ;

import org.apache.commons.beanutils.PropertyUtils ;
import org.hibernate.collection.internal.PersistentBag ;

import cn.zy.apps.tools.logger.Loggerfactory ;
import cn.zy.apps.tools.web.SelectPage ;

public abstract class AutoWritePrpertiesObjectService {

    protected static Map<Class<?>, Map<String, PropertyDescriptor>> cacheFactory = new HashMap<Class<?>, Map<String, PropertyDescriptor>>() ;

    private org.apache.log4j.Logger logger = Loggerfactory.instance(AutoWritePrpertiesObjectService.class) ;

    private String regexPackage ;

    public AutoWritePrpertiesObjectService(String regexPackage) {
        Loggerfactory.info(logger, "load  Prperties   Copy  Service  Init  regexPackage : " + regexPackage) ;
        this.regexPackage = regexPackage ;
    }

    protected <V> V readFieldValue(String fieldName, Object result) {
        try {
            if (result == null) return null ;
            Map<String, PropertyDescriptor> propertyDescriptorsMap = searchPropertyDescriptor(result) ;

            PropertyDescriptor propertyRead = propertyDescriptorsMap.get(fieldName) ;

            if (propertyRead == null) return null ;

            Method methodRead = propertyRead.getReadMethod() ;
            @SuppressWarnings("unchecked")
            V value = (V) methodRead.invoke(result) ;
            return value ;
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            Loggerfactory.error(logger, " handusePosition -> " + e.getMessage()) ;
        }
        return null ;
    }

    protected <V> void writeFieldValue(String fieldName, Object result, V value) {
        try {

            Map<String, PropertyDescriptor> propertyDescriptorsMap = searchPropertyDescriptor(result) ;

            PropertyDescriptor propertyWrite = propertyDescriptorsMap.get(fieldName) ;
            Method methodWrite = propertyWrite.getWriteMethod() ;
            methodWrite.invoke(result, value) ;
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            Loggerfactory.error(logger, " handusePosition -> " + e.getMessage()) ;
        }
    }

    private Map<String, PropertyDescriptor> searchPropertyDescriptor(Object result) {
        Map<String, PropertyDescriptor> propertyDescriptorsMap = cacheFactory.get(result.getClass()) ;
        if (propertyDescriptorsMap == null) {
            propertyDescriptorsMap = new HashMap<String, PropertyDescriptor>() ;

            PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(result.getClass()) ;

            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                propertyDescriptorsMap.put(propertyDescriptor.getName(), propertyDescriptor) ;
            }

            cacheFactory.put(result.getClass(), propertyDescriptorsMap) ;

        }
        return propertyDescriptorsMap ;
    }

    protected abstract PrpertiesSetValueService autoPrpertiesSetValueService() ;

    public void intToPrpertiesUnits(Object result) throws Exception {
        try {
            if (result == null) return ;
            if (result instanceof List<?>) {
                List<?> resultList = (List<?>) result ;
                for (Object object : resultList) {
                    simpleObject(object, result) ;
                }
            } else {
                simpleObject(result, null) ;
            }
            PrpertiesSetValueService prpertiesSetValueService = autoPrpertiesSetValueService() ;
            prpertiesSetValueService.execactionaftereturn(result) ;
        } catch (Exception e) {
            Loggerfactory.error(logger, e) ;

        }
    }

    private boolean vpackage(Class<?> clzz) {

        if (clzz.getPackage() == null) {
            return false ;
        } else {
            if (regexPackage == null) return true ;
            boolean result = ToolsUnits.regex(regexPackage, clzz.getPackage().getName()) ;
            if (!result) {
                result = clzz.getName().equals(SelectPage.class.getName()) ;
            }
            return result ;

        }
    }

    protected abstract boolean isEqualsParents(Object child, Object parents) ;

    private void simpleObject(Object result, Object parent) throws Exception {
        try {
            if (result == null || result.equals("serialVersionUID") || !vpackage(result.getClass()) || result.getClass().toString().contains("_$$_jvst") || result.getClass().toString().contains("_$$_javassist_")) return ;

            Map<String, PropertyDescriptor> propertyDescriptorsMap = searchPropertyDescriptor(result) ;

            for (PropertyDescriptor propertyDescriptor : propertyDescriptorsMap.values()) {

                // void
                if (propertyDescriptor.getPropertyType() == null || propertyDescriptor.getPropertyType().getPackage() == null) continue ;

                //

                if (propertyDescriptor.getPropertyType().equals(List.class)) {

                    Method methodRead = propertyDescriptor.getReadMethod() ;

                    Object results = methodRead.invoke(result) ;

                    if (results == null) continue ;
                    else if (results instanceof PersistentBag) {
                        PersistentBag persistentBag = (PersistentBag) results ;
                        if (persistentBag.wasInitialized()) {
                            for (Object object : persistentBag) {
                                simpleObject(object, result) ;
                            }
                        }
                    } else if (results instanceof List<?>) {
                        for (Object object : (List<?>) results) {
                            simpleObject(object, result) ;
                        }
                    }

                } else {
                    if (vpackage(propertyDescriptor.getPropertyType())) {

                        handProperty(result, propertyDescriptor) ;

                        /////////////////////////////////////////////////////
                        Method methodRead = propertyDescriptor.getReadMethod() ;
                        Object objs = methodRead.invoke(result) ;
                       
                        if (objs == null || objs.equals("serialVersionUID")  || objs.getClass().toString().contains("_$$_jvst") ||
                                objs.getClass().toString().contains("_$$_javassist_")) {
                            
                        }else{
                            
                            if (objs != null && !objs.getClass().equals(SelectPage.class)) {
                                if (parent != null && !parent.getClass().equals(SelectPage.class)) {
                                    if (!isEqualsParents(objs, parent)) {
                                        simpleObject(objs, result) ;
                                    }
                                } else {
                                    simpleObject(objs, result) ;
                                }

                            }               
                        }

             

                        /////////////////////////////////////////////////////

                    }

                }

            }

        } catch (Exception e) {

            Loggerfactory.error(logger, e.getMessage(), e) ;
        }
    }

    protected abstract AutoWriteObject searchAutoWriteObject() ;

    protected void handProperty(Object object, PropertyDescriptor popertyDescriptors) {

        AutoWriteObject autoWriteObject = searchAutoWriteObject() ;

        autoWriteObject.handValues(object, popertyDescriptors) ;
    }

    //    public abstract  void setAutoWriteObject(AutoWriteObject autoWriteObject) {
    ////        this.autoWriteObject = autoWriteObject ;
    //    }

    // public static void main(String[] args) {
    // String tax_rate =
    // "^cn.communications.erp(\\.\\D+)*.[bean|jopo|jopoView|beans]$";
    // boolean xxx = ToolsRegex.regex(ToolsRegex.erp_pojo_packages_start,
    // StaffInfo.class.getPackage().getName());
    // System.out.println("==> " + xxx);
    //
    // }

    public static void main(String[] args) {
        AutoWriteObject xx = null ;
        System.out.println("==>  " + xx.getClass()) ;
    }
}