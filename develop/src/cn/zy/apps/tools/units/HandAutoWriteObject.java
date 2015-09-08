package cn.zy.apps.tools.units;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import cn.zy.apps.tools.logger.Loggerfactory;

public abstract class HandAutoWriteObject {

	private boolean isSetNull = false;

	public  HandAutoWriteObject() {
//		this.isSetNull = isSetNull;
	}

	private org.apache.log4j.Logger logger = Loggerfactory.instance(HandAutoWriteObject.class);

	/**
	 * MAP FieldName
	 */
	private Map<Class<?>, Map<String, PropertyDescriptor>> readPropertyDescriptors = new HashMap<Class<?>, Map<String, PropertyDescriptor>>();

	// private Map<Class<?>, Map<String, Field>> classFieldMap = new
	// HashMap<Class<?>, Map<String, Field>>();

	protected abstract String getFieldOfFieldId(String fieldName);

	protected abstract boolean filterSetProperties(Class<?> classes);

	public void handValues(Object object, PropertyDescriptor popertyDescriptors) {
		
		

		if (!filterSetProperties(popertyDescriptors.getPropertyType()))
			return;

		try {

			Class<?> clazz = object.getClass();

			Method readMethod = popertyDescriptors.getReadMethod();

			try {
				Object result_ = readMethod.invoke(object);
				if (result_ != null) {
					return;
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				Loggerfactory.devError(logger, e,
						e.getMessage() + " ex class" + e.getClass().toString() + "  clazz   " + clazz.getName());
			}

			String fieldName = popertyDescriptors.getName();

			String fieldNameId = getFieldOfFieldId(fieldName);

			PropertyDescriptor propertyDescriptor_ = searchPropertyDescriptor(clazz, fieldNameId);
			{
				Method methodStaffInfoRead = propertyDescriptor_.getReadMethod();

				Object id_ = methodStaffInfoRead.invoke(object);
				if (id_ == null) {
					if (isSetNull) {
						Object result = popertyDescriptors.getPropertyType().newInstance();
						popertyDescriptors.getWriteMethod().invoke(object, result);
					}
					return;
				}

				Object value = searchCacheObject(id_);
				if (value == null)
					value = popertyDescriptors.getPropertyType().newInstance();
				popertyDescriptors.getWriteMethod().invoke(object, value);

			}

		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException
				| IntrospectionException e) {
			Loggerfactory.devError(logger, e, e.getMessage() + " handValues  ex class" + e.getClass().toString()
					+ "  clazz   " + popertyDescriptors.getPropertyType().getName());
		}
	}

	protected abstract Object searchCacheObject(Object key);

	private PropertyDescriptor searchPropertyDescriptor(Class<?> clazz, String fieldNameId)
			throws IntrospectionException {
		Map<String, PropertyDescriptor> propertyDescriptorMapses = readPropertyDescriptors.get(clazz);
		if (propertyDescriptorMapses == null) {
			propertyDescriptorMapses = new HashMap<String, PropertyDescriptor>();
			readPropertyDescriptors.put(clazz, propertyDescriptorMapses);
		}

		PropertyDescriptor propertyDescriptor_ = propertyDescriptorMapses.get(fieldNameId);

		if (propertyDescriptor_ == null) {
			propertyDescriptor_ = new PropertyDescriptor(fieldNameId, clazz);
			propertyDescriptorMapses.put(fieldNameId, propertyDescriptor_);
		}
		return propertyDescriptor_;
	}

}
