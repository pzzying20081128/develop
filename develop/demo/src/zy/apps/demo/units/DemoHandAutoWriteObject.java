package zy.apps.demo.units;

import java.util.HashMap;
import java.util.Map;

import cn.zy.apps.tools.units.HandAutoWriteObject;
import cn.zy.apps.tools.units.ToolsUnits;

public class DemoHandAutoWriteObject extends HandAutoWriteObject {

	String regexPackage = "^zy.apps.demo(\\.\\D+)*(.pojos|.views|.beans)$";

	public Map<Object, Object> cache = new HashMap<Object, Object>();

	public DemoHandAutoWriteObject(Map<Object, Object> cache) {
		this.cache.putAll(cache);
	}

	@Override
	protected String getFieldOfFieldId(String fieldName) {

		return fieldName + "Id";
	}

	@Override
	protected boolean filterSetProperties(Class<?> classes) {

		boolean result = ToolsUnits.regex(regexPackage, classes.getPackage().getName());

		return result;
	}

	@Override
	protected Object searchCacheObject(Object key) {

		return cache.get(key);
	}

}
