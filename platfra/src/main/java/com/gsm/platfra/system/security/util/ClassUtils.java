package com.gsm.platfra.system.security.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public abstract class ClassUtils extends org.springframework.util.ClassUtils {

	/**
	 * 해당 클래스의 필드 정보 목록을 반환하며 이때  상위 클래스의  필드까지 모두 포함함.
	 * 
	 * @param cls
	 * @return
	 */
	public static List<Field> getFields(Class<?> cls) {

		List<Field> fields = new ArrayList<Field>();

		Field[] fieldArr = cls.getDeclaredFields();

		for (Field field : fieldArr) {
			int modifier = field.getModifiers();

			if ((modifier & 0x08) == 0x08) // static
				continue;
			if ((modifier & 0x10) == 0x10) // final
				continue;

			fields.add(field);
		}

		Class<?> superclass = cls.getSuperclass();
		if (superclass != null) {
			if (superclass == Object.class)
				return fields;

			// Recursive call.
			List<Field> superFields = getFields(cls.getSuperclass());
			if (superFields != null)
				fields.addAll(superFields);
		}

		return fields;
	}

	/**
	 * 해당 이름의 클래스를 생성함
	 * @param clsName
	 * @return
	 */
	public static <T> T createInstance(String clsName) {

		return (T) createInstance(clsName, null);
	}

	/**
	 * 해당 타입의 클래스를 생성함
	 * @param voCls
	 * @return
	 */
	public static <T> T createInstance(Class<?> voCls) {

		return (T) createInstance(voCls, null);
	}

	/**
	 * 해당  이름의 클래스를 생성함
	 * @param clsName
	 * @param objects 생성자 파라미터
	 * @return
	 */
	public static <T> T createInstance(String clsName, Object[] objects) {
		return (T) createInstance(forName(clsName), objects);

	}

	/**
	 * 해당 타입의 클래스를 생성함
	 * @param cls
	 * @param objects 생성자 파라미터
	 * @return
	 */
	public static <T> T createInstance(Class<?> cls, Object[] objects) {
		Class<?>[] classes = getParameterTypes(objects);

		return (T) createInstance(cls, objects, classes);
	}

	/**
	 * 해당 객체등의 타입들을 반환함
	 * @param objects
	 * @return
	 */
	public static Class<?>[] getParameterTypes(Object[] objects) {
		Class<?>[] classes = null;
		if (objects != null) {
			classes = new Class<?>[objects.length];
			int i = 0;
			for (Object obj : objects) {
				classes[i++] = obj.getClass();
			}
		}
		return classes;
	}

	/**
	 * 해당 타입의 클래스를 생성함
	 * @param cls
	 * @param objects 생성자 파라미터들
	 * @param classes 생성자 파라미터 타입들
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static <T> T createInstance(Class cls, Object[] objects, Class<?>[] classes) {

		try {
			Constructor<?> declaredConstructor = cls.getDeclaredConstructor(classes);
			return (T) declaredConstructor.newInstance(objects);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Class<?> getParameterizedType(Object obj, String fieldName) {

		try {
			Field field = obj.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);

			return getParameterizedType(field);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @param list
	 * @return
	 */
	public static Class<?> getParameterizedType(List<Object> list) {
		if (list == null || list.size() == 0)
			return null;
		Object object = list.get(0);
		return object.getClass();
	}

	/**
	 * 
	 * @param field
	 * @return
	 */
	public static Class<?> getParameterizedType(Field field) {

		if (field == null)
			return null;

		Type genericType = field.getGenericType();

		if (genericType == null)
			return null;

		if (genericType instanceof ParameterizedType) {
			ParameterizedType wrappedType = (ParameterizedType) genericType;

			return (Class<?>) wrappedType.getActualTypeArguments()[0];
		}
		return null;
	}

	/**
	 * 
	 * @param clazz
	 * @return
	 */
	public static boolean isSimpleProperty(Class<?> clazz) {
		return isSimpleValueType(clazz) || (clazz.isArray() && isSimpleValueType(clazz.getComponentType()));
	}

	/**
	 * 해당 타입이 simple value 타입인지 체크
	 * Primitive, PrimitiveWrapper,Enum,CharSequence,Number,Date,URI,URL,Locale,Class인지를 체크하게 됨.
	 * @param clazz
	 * @return
	 */
	public static boolean isSimpleValueType(Class<?> clazz) {
		return isPrimitiveOrWrapper(clazz) || clazz.isEnum() || CharSequence.class.isAssignableFrom(clazz)
				|| Number.class.isAssignableFrom(clazz) || Date.class.isAssignableFrom(clazz)
				|| clazz.equals(URI.class) || clazz.equals(URL.class) || clazz.equals(Locale.class)
				|| clazz.equals(Class.class);
	}

	/**
	 * 해당 이름의 클래스 타입을 반환함
	 * @param classname
	 * @return
	 * @throws
	 */
	public static Class<?> forName(String classname) {
		try {
			return Class.forName(classname);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}