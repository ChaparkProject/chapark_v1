package com.springframework.chapark.utils;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

public class MybatisUtils {
	/**
	 * 인자가 비어있는지 체크
	 * 
	 * @param obj
	 * @return
	 */
	public static Boolean isEmpty(Object obj) {
		if (obj instanceof String || obj instanceof Character)
			return obj == null || "".equals(obj.toString().trim());
		else if (obj instanceof Integer)
			return obj == null;
		else if (obj instanceof List)
			return obj == null || ((List<?>) obj).isEmpty();
		else if (obj instanceof Map)
			return obj == null || ((Map<?, ?>) obj).isEmpty();
		else if (obj instanceof Object[])
			return obj == null || Array.getLength(obj) == 0;
		else
			return obj == null;
	}

	/**
	 * 인자가 비어있지 않는지 체크
	 * 
	 * @param obj
	 * @return
	 */
	//@Deprecated
	public static Boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}
}
