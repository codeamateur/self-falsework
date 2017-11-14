package com.tianqian.self.common.utils;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
}
