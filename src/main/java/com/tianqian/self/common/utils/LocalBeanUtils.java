package com.tianqian.self.common.utils;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class LocalBeanUtils {

	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}
	
    public static void copyPropertiesIgnoreNull(Object src, Object target){
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }
    
    public static <S, D> List<D> copyListProperties(List<S> sourceList,Class<D> destinationClass){
        if (sourceList == null || sourceList.isEmpty()) {
            return null;
        }
        List<D> targetList = Lists.newArrayList();
        D target = null;
        for(S source : sourceList){
        	target = BeanUtils.instantiateClass(destinationClass);
        	BeanUtils.copyProperties(source, target);
        	targetList.add(target);
        }
    	return targetList;
    }
    
    /**
     * list<map> 转javaBean的list
     * @param list 源List
     * @param clazz javaBean类
     * @return
     */
    public static <T> List<T> transListMapToBean(List<Map<String,Object>> list, Class<T> clazz){
    	List<T> tlist = new ArrayList<>();
    	try {
	    	for (Map<String,Object> map : list) {
	    		
	    		T t = clazz.newInstance();
	    		org.apache.commons.beanutils.BeanUtils.populate(t, map);
	    		tlist.add(t);
			}
    	} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
    	return tlist;
    }
    
    /**
     * map转javaBean
     * @param map
     * @param t
     * @return
     */
    public static <T> T transMapToBean(Map<String,Object> map, T t){
    	try {
	    		org.apache.commons.beanutils.BeanUtils.populate(t, map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
    	return t;
    }
}
