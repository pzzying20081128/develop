package cn.zy.apps.tools.dev.javascript ;

@java.lang.annotation.Target(value = { java.lang.annotation.ElementType.TYPE })
@java.lang.annotation.Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
public abstract @interface MdouleDesc {
    

    public String name() default "" ;


}
