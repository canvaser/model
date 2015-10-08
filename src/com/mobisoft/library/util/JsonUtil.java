package com.mobisoft.library.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {

	/**
	 * json2list: json字符串转换为list.
	 */
	public static <T> List<T> json2list(String json, Class<T> clazz) {

		if (json == null || StringUtil.isEmpty(json)) {
			return null;
		}
		List<T> list = JSONArray.parseArray(json, clazz);
		return list;
	}

	/**
	 * json2map: json字符串转换为map.
	 */
	public static <K, V> Map<K, V> json2map(String json) {

		if (json == null || StringUtil.isEmpty(json)) {
			return null;
		}
		@SuppressWarnings("unchecked")
		Map<K, V> map = (Map<K, V>) JSONArray.parse(json);
		return map;

	}

	/**
	 * map 转json
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String map2json(Map map) {
		if (map == null) {
			map = new HashMap();
		}
		return JSONObject.toJSONString(map);
	}

	/**
	 * json2entity: json字符串转换为entity.
	 */
	public static <T> T json2entity(String json, Class<T> clazz) {

		if (json == null || StringUtil.isEmpty(json)) {
			return null;
		}
		T entity = JSONArray.parseObject(json, clazz);
		return entity;

	}

	/**
	 * obj2entity: obj字符串转换为entity.
	 */
	public static <T> T obj2entity(Object obj, Class<T> clazz) {
		if (obj == null) {
			return null;
		}
		return json2entity(obj.toString(), clazz);
	}

	/**
	 * obj2list: json字符串转换为list.
	 */
	public static <T> List<T> obj2list(Object obj, Class<T> clazz) {

		if (obj == null) {
			return null;
		}
		return json2list(obj.toString(), clazz);
	}
}
