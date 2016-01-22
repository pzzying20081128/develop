package cn.zy.apps.tools.jpa;

import javax.persistence.EntityManager ;


public interface IERPBaseService extends IBasevisit {

	public String name = "IERPBaseService";
	
	
	public   EntityManager  getEm();
	


}
