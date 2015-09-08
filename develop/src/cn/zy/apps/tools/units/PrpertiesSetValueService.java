package cn.zy.apps.tools.units;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.aspectj.lang.JoinPoint;
import org.hibernate.collection.internal.PersistentBag;

import cn.zy.apps.tools.logger.Loggerfactory;

/**
 * "^cn.communications.erp(\\.\\D+)*(.bean|.pojos|.views|.beans)$";
 * 
 * @author you
 *
 */
public abstract class PrpertiesSetValueService {

	protected static Map<Class<?>, PropertyDescriptor[]> cacheFactory = new HashMap<Class<?>, PropertyDescriptor[]>();

	protected static Map<String, PropertyDescriptor> fieldClass = new HashMap<String, PropertyDescriptor>();

	protected static Map<String, Method> cacheMethodFactory = new HashMap<String, Method>();

	protected static Map<String, Boolean> cacheNoHaveMethodFactory = new HashMap<String, Boolean>();

	private org.apache.log4j.Logger logger = Loggerfactory.instance(PrpertiesSetValueService.class);

	private String regexPackage;

	public PrpertiesSetValueService(String regexPackage) {
		Loggerfactory.info(logger, "load  Prperties   Copy  Service  Init  regexPackage : " + regexPackage);
		this.regexPackage = regexPackage;
	}

	protected <V> boolean contain(String fieldName, Class<V> result) {
		return true;
	}

	public void execactionafter(JoinPoint jp, Object element) throws Exception {
		if (element == null)
			return;
	}

	public void execactionaftereturn(Object result) throws Exception {
		if (result != null) {
			if (result instanceof List<?>) {
				List<?> resultList = (List<?>) result;
				for (Object object : resultList) {
					simpleObject(object, result);
				}
			} else {
				simpleObject(result, null);
			}
		}
	}

	private PropertyDescriptor searchPropertyDesriptor(String fieldName, Object result) throws IntrospectionException {
		PropertyDescriptor propertyRead = fieldClass.get(result.getClass().getName() + "_" + fieldName);
		if (propertyRead == null) {
			propertyRead = new PropertyDescriptor(fieldName, result.getClass());
			fieldClass.put(result.getClass().getName() + "_" + fieldName, propertyRead);
		}
		return propertyRead;

	}

	private boolean vpackage(Class<?> clzz) {
		if (clzz.getPackage() == null) {
			return false;
		} else {
			if (regexPackage == null)
				return true;
			boolean result = ToolsUnits.regex(regexPackage, clzz.getPackage().getName());
			return result;
		}
	}

	private void simpleObject(Object result, Object parent) {
		if (result == null || !vpackage(result.getClass()))
			return;
		PropertyDescriptor[] propertyDescriptors = cacheFactory.get(result.getClass());
		if (propertyDescriptors == null) {
			propertyDescriptors = PropertyUtils.getPropertyDescriptors(result.getClass());
			cacheFactory.put(result.getClass(), propertyDescriptors);
		}

		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			if (propertyDescriptor.getName().equals("serialVersionUID")
					|| propertyDescriptor.getName().contains("$JAVASSIST_READ_WRITE_HANDLER")
					|| propertyDescriptor.getName().contains("$$_javassist_")
					|| propertyDescriptor.getName().contains("_$$_jvst"))
				continue;

			if (propertyDescriptor.getReadMethod().getReturnType().equals(Set.class)
					|| propertyDescriptor.getReadMethod().getReturnType().equals(List.class)) {
				Method methodRead = propertyDescriptor.getReadMethod();
				try {
					Object results = methodRead.invoke(result);
					if (results == null) {
						continue;
					} else if (results instanceof List<?>) {
						for (Object object : (List<?>) results) {
							simpleObject(object, result);
						}
					} else if (results instanceof PersistentBag) {
						PersistentBag results_ = (PersistentBag) results;
						if (results_.wasInitialized()) {
							for (Object object : results_) {
								simpleObject(object, result);
							}
						}
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					Loggerfactory.devError(logger, e);
				}
				// simpleObject
			} else if (vpackage(propertyDescriptor.getReadMethod().getReturnType())) {
				Method methodRead = propertyDescriptor.getReadMethod();
				try {
					Object objs = methodRead.invoke(result);
					if (objs == null || objs.getClass().toString().contains("$$_javassist_")
							|| objs.getClass().toString().contains("_$$_jvst")) {

					} else {
						if (objs.equals(parent)) {
						} else {
							simpleObject(objs, result);
						}
					}

				} catch (Exception e) {
					Loggerfactory.devError(logger, e);
				}
			} else {
				// simpleObject Properties
				try {
					String fieldName = propertyDescriptor.getName();
					if (fieldName.contains("$$_javassist_") || fieldName.contains("_$$_jvst"))
						continue;
					if (contain(fieldName, result.getClass())) {
						handField(fieldName, result);
					} else {
						continue;
					}
				} catch (IllegalArgumentException e) {
					Loggerfactory.devError(logger, e);
				}
			}
		}
	}

	protected abstract void handField(String fieldName, Object result);

	protected <V> V readFieldValue(String fieldName, Object result) {
		try {
			PropertyDescriptor propertyRead = searchPropertyDesriptor(fieldName, result);
			Method methodRead = propertyRead.getReadMethod();
			@SuppressWarnings("unchecked")
			V value = (V) methodRead.invoke(result);
			return value;
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| IntrospectionException e) {
			Loggerfactory.error(logger, " handusePosition -> " + e.getMessage());
		}
		return null;
	}

	protected <V> void writeFieldValue(String fieldName, Object result, V value) {
		try {
			PropertyDescriptor propertyWrite = searchPropertyDesriptor(fieldName, result);
			Method methodWrite = propertyWrite.getWriteMethod();
			methodWrite.invoke(result, value);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| IntrospectionException e) {
			Loggerfactory.error(logger, " handusePosition -> " + e.getMessage());
		}
	}

}
