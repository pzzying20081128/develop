package cn.zy.apps.tools.jpa ;

@java.lang.annotation.Target(value = { java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.FIELD })
@java.lang.annotation.Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
public abstract @interface FieldDesc {

    public int showWidth() default 200 ;

    public abstract boolean isShow() default true ;

    public abstract String[] desc() default "" ;

    public String name() default "" ;

    public String mapping() default "" ;

    public boolean isSort() default true ;

}
