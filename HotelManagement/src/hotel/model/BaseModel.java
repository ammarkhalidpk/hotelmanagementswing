package hotel.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public abstract class BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public abstract String getAttLabel(String attName);
	
	public BaseModel() {
	}
	
	public String[] getAttributeNames() {
		List fields = this.getFields();
		String[] result = new String[fields.size()];
		int count = 0;
		for (Iterator iter = fields.iterator(); iter.hasNext(); ) {
			Field field = (Field) iter.next();
			result[count++] = field.getName();
		}
		return result;
	}
	
	protected List getFields() {
		List result = new ArrayList();
		Field[] fields = this.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (hasSetterAndGetter(field)) {
				result.add(field);
			}
		}
		return result;
	}

	protected boolean hasSetterAndGetter(Field field) {
		boolean hasSetter = false;
		boolean hasGetter = false;
		String fieldName = field.getName();
		String setter = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		String getter = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		Method[] methods = this.getClass().getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			if (method.getName().equals(setter)) {
				hasSetter = true;
			}
			if (method.getName().equals(getter)) {
				hasGetter = true;
			}
		}
		if (hasSetter && hasGetter) {
			return true;
		}
		return false;
	}

	public Object getAttributeValue(String attName) throws NoSuchMethodException {
		String getter = "get" + attName.substring(0, 1).toUpperCase() + attName.substring(1);
		try {
			Method method = this.getClass().getDeclaredMethod(getter, null);
			return method.invoke(this, null);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw e;
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
}
