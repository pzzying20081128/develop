package cn.zy.apps.tools.jpa ;

@java.lang.annotation.Target(value = { java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.FIELD })
@java.lang.annotation.Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
public abstract @interface FieldDesc {

    public abstract String[] desc() default "" ;

    public String name() default "" ;

}
