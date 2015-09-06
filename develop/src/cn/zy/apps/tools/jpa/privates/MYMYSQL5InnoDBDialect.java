package cn.zy.apps.tools.jpa.privates;

import java.sql.Types ;

import org.hibernate.dialect.MySQL5InnoDBDialect ;

/**
 * 
 * @author zy
 * 
 */
public class MYMYSQL5InnoDBDialect extends MySQL5InnoDBDialect {

	public MYMYSQL5InnoDBDialect() {
		super();
		// registerHibernateType(Types.LONGVARCHAR, Hibernate.TEXT.getName() );
		registerHibernateType(Types.NULL, "null");
	}

}
