package com.tang.common.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
*
* @author tanghaibin
* @date 2023-06-05
*/
public class BeanUtils {

	public static <T1, T2> List<T2> convert(List<T1> sourceList, Class<T2> toType) {
		if (CollectionUtils.isEmpty(sourceList)) {
			return Collections.emptyList();
		}
		return sourceList.parallelStream().map(item -> convert(item, toType)).collect(Collectors.toList());
	}

	public static <T1, T2> T2 convert(T1 source, Class<T2> toType) {
		if (null == source) {
			return null;
		}
		
		//return JSON.parseObject(JSON.toJSONString(source), toType);
		
		T2 target = null;
		try {
			target = toType.newInstance();
		} catch (Exception e) {
			Assert.isTrue(false,"类型转换错误");
		}
		org.springframework.beans.BeanUtils.copyProperties(source, target);
		return target;
	}


	public static <T> String beanToString(T value) {
		if (value == null) {
			return null;
		}
		Class<?> clazz = value.getClass();
		if (clazz == int.class || clazz == Integer.class) {
			return "" + value;
		} else if (clazz == String.class) {
			return (String) value;
		} else if (clazz == long.class || clazz == Long.class) {
			return "" + value;
		} else {
			return JSON.toJSONString(value);
		}
	}

	/**
	 * 拷贝bean中的属性
	 * @param source
	 * @param target
	 * @param ignores
	 */
	public static void copyProperties(Object source, Object target, String... ignores){
		org.springframework.beans.BeanUtils.copyProperties(source, target, ignores);
	}
	/**
	 * 拷贝bean中的属性 忽略掉空值属性和ID属性
	 * @param source
	 * @param target
	 */
	public static void copyPropertiesIgnoreNullValueAndId(Object source, Object target){
		copyProperties(source, target, getNullPropertyNamesAndIgnoreNames(source, "id"));
	}
	/**
	 * 拷贝bean中的属性 忽略掉空值属性和ID属性
	 * @param source
	 * @param target
	 */
	public static void copyPropertiesIgnoreNullValueAndIdAndTime(Object source, Object target){
		copyProperties(source, target, getNullPropertyNamesAndIgnoreNames(source, "id","createEmployeeId","createTime"
				,"updateEmployeeId","updateUserId","updateTime","createUserId"));
	}

	/**
	 * 拷贝bean中的属性 忽略掉空值属性
	 * @param source
	 * @param target
	 */
	public static void copyPropertiesIgnoreNullValue(Object source, Object target){
		copyProperties(source, target, getNullPropertyNamesAndIgnoreNames(source));
	}
	/**
	 * 获取空值属性名称或者忽略
	 * @param source
	 * @param ignores
	 * @return
	 */
	public static String[] getNullPropertyNamesAndIgnoreNames (Object source, String... ignores ) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		if (ignores != null && ignores.length > 0){
			emptyNames.addAll(Arrays.asList(ignores));
		}
		for(java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null) {
				emptyNames.add(pd.getName());
			}
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	public static String[] convertStringToArray(String ids){
		if (StringUtils.isEmpty(ids)){
			return null;
		}
		return ids.split(",");
	}


	/**
	 * 将数据分组，根据方法引用（bean的get方法）
	 *
	 * @param list      为分组的数据
	 * @param functions get方法数组
	 */
	@SafeVarargs
	public static <T, R> Map<String, List<T>> groupingBy(List<T> list, Function<T, R>... functions) {
		return list.stream().collect(Collectors.groupingBy(t -> groupingBy(t, functions)));
	}

	/**
	 * 分组工具根据函数式接口使用分组，将数据根据分组结果进行拆分
	 */
	@SafeVarargs
	public static <T, R> String groupingBy(T t, Function<T, R>... functions) {
		if (functions == null || functions.length == 0) {
			throw new NullPointerException("functions数组不可以为空");
		} else if (functions.length == 1) {
			return functions[0].apply(t).toString();
		} else {
			return Arrays.stream(functions).map(fun -> fun.apply(t).toString()).reduce((str1, str2) -> str1 + "|" + str2).get();
		}
	}

	/**
	 * 泛型List转List对象
	 * @param obj 转换参数
	 * @param cla 实体类
	 * @param <T> 泛型
	 * @return
	 */
	public static <T> List<T> toList(Object obj, Class<T> cla) {
		List<T> list = new ArrayList<T>();
		//判断参数是否为List类型
		if (obj instanceof List<?>) {
			for (Object o : (List<?>) obj) {
				//将父类对象强制转换为子类对象
				list.add(cla.cast(o));
			}
			return list;
		}
		return null;
	}

}
