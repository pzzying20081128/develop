package cn.zy.apps.tools.dev.javascript ;

import java.io.File ;
import java.lang.reflect.Field ;
import java.util.ArrayList ;
import java.util.HashMap ;
import java.util.List ;
import java.util.Map ;

import org.apache.commons.lang3.reflect.FieldUtils ;
import org.apache.velocity.VelocityContext ;

import cn.zy.apps.tools.jpa.FieldDesc ;
import cn.zy.apps.tools.units.ToolsUnits ;

public class BuildConfigs extends IBuildJavaScriptFile {

    public BuildConfigs(File velocityFile, JavaScriptCommBean javaScriptCommBean) {
        super(velocityFile, javaScriptCommBean) ;

    }

    @Override
    public void build(VelocityContext context, Class<?> clazz) throws Exception {
        Map<String, Object> configs = new HashMap<String, Object>() ;

        List<Record> records = new ArrayList<Record>() ;
        configs.put("records", records) ;

        List<Field> fieldList = FieldUtils.getAllFieldsList(clazz) ;

        for (Field field : fieldList) {
            if (field.isAnnotationPresent(FieldDesc.class)) {
                FieldDesc fieldDesc = field.getAnnotation(FieldDesc.class) ;
                if (fieldDesc != null) {
                    Record record = new Record() ;
                    records.add(record) ;
                    record.setName(ToolsUnits.isNOtNulll(fieldDesc.mapping()) ? fieldDesc.mapping() : field.getName()) ;
                    record.setMapping(field.getName()) ;
                    record.setShowName(fieldDesc.name()) ;
                    record.setShowWidth(fieldDesc.showWidth()) ;
                    record.setCanSort(fieldDesc.isSort()) ;
                    record.setCanShow(fieldDesc.isShow()) ;
                    //                    record.setRendererValueKey("value") ;
                    record.setClassfiled(clazz.getSimpleName().toLowerCase()) ;

                    if (ToolsUnits.isNOtNulll(fieldDesc.mapping())) {

                        String[] mappings = fieldDesc.mapping().split("\\.") ;

                        System.out.println(fieldDesc.mapping() + " " + fieldDesc.mapping().split("\\.").length + "==>  " + mappings.length) ;

                        if (mappings.length == 0) return ;

                        mappings[0] = "value" ;

                        String x = " " ;

                        for (int i = 0; i < mappings.length; i++) {
                            String  ii="";
                            for(int j=0;j<=i;j++){
                                ii= ii+ mappings[j] ;
                                if(j <  i)ii= ii+".";
                            }
                          

                            x = x + "if(" + ii + "==null ||  typeof(" + ii + ") =='undefined' )   \r\n  return null   \r\n   else  \r\n" ;

                        }

                        x = x + " return " ;
                        for (int i = 0; i < mappings.length; i++) {
                            if (i == mappings.length - 1) x = x + mappings[i] ;
                            else
                                x = x + mappings[i] + "." ;
                        }

                        record.setRendererValueKey(x) ;
                    }
                }

            }

        }
        BuildJavaScript buildJavaScript = new BuildJavaScript(configs) ;
        buildJavaScript.build(context, velocityFile, javaScriptCommBean) ;
    }

    //    public static void main(String[] args) {
    //        String[] mappings =new  String[]{"ww","wwww","www" };
    //        for( ){
    //            
    //        }
    //    }

}
