package cn.zy.apps.tools.units ;

import java.beans.PropertyDescriptor ;
import java.lang.reflect.InvocationTargetException ;
import java.lang.reflect.Method ;
import java.math.BigDecimal ;
import java.util.ArrayList ;
import java.util.HashMap ;
import java.util.List ;
import java.util.Map ;
import java.util.regex.Matcher ;
import java.util.regex.Pattern ;

import org.apache.commons.beanutils.BeanUtils ;

public class ToolsUnits {
    
    public static Map<String,Object> createSearchMap() {
                   return new HashMap<String, Object>();

    }

    private static boolean matcher(String zz, String matcherStr) {

        Pattern pattern = Pattern.compile(zz) ;
        Matcher matcher = pattern.matcher(matcherStr) ;
        boolean b = matcher.matches() ;
        return b ;
    }

    public static boolean regex(String regex, String input) {
        return matcher(regex, input) ;
    }

    public static void copyBeanProperties(Object tagBean, Object srcBean, String... properties) throws Exception {

        for (String property : properties) {
            Object value = getValueProperties(srcBean, property) ;
            writeValueProperties(tagBean, property, value) ;
        }

    }

    @SuppressWarnings("unchecked")
    private static <V> V getValueProperties(Object srcBean, String property) throws Exception {
        PropertyDescriptor propertyRead = new PropertyDescriptor(property, srcBean.getClass()) ;
        Method read = propertyRead.getReadMethod() ;
        return (V) read.invoke(srcBean) ;
    }

    private static void writeValueProperties(Object srcBean, String property, Object value) throws Exception {
        PropertyDescriptor propertyRead = new PropertyDescriptor(property, srcBean.getClass()) ;
        Method write = propertyRead.getWriteMethod() ;
        write.invoke(srcBean, value) ;
    }

    public static <T> List<T> switchStringToIntegerList(String ins, Class<T> clazz) {
        if (!isNOtNulll(ins)) return null ;
        List<T> values = new ArrayList<T>() ;
        String[] ins_ = ins.split(",") ;

        for (String in_ : ins_) {
            if (isNOtNulll(in_)) {
                T value_ = null ;
                if (clazz.equals(Integer.class))

                value_ = clazz.cast(Integer.parseInt(in_.trim())) ;

                else if (clazz.equals(String.class)) value_ = (T) ins ;
                else

                    value_ = clazz.cast(in_.trim()) ;

                ///////////////////////////////////////////////////////////////////////////////////////
                if (value_ != null) values.add(value_) ;
            }
        }
        return values ;
    }

    public static enum charset {
        utf8, gbk
    }

    public static Long switchBigDecimal(Object object) {
        BigDecimal bigDecimal = (BigDecimal) object ;
        return bigDecimal.longValue() ;
    }

    public static boolean isNOtNulll(String in) {
        return (in != null && !in.equals("")) ;
    }

    public static String getValue(Object object, String field) {
        try {
            return BeanUtils.getProperty(object, field) ;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new ToolsUnitsException(e) ;
        }
    }

    public static void wirteValue(Object object, String field, Object value) {

        try {
            BeanUtils.setProperty(object, field, value) ;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new ToolsUnitsException(e) ;
        }

    }

    public static String buildParamsMd5(Map<String, String> params, String signKey, String input_charset) throws ToolsUnitsException {
        return PayMD5Units.buildParamsMd5(params, signKey, input_charset) ;
    }

}
