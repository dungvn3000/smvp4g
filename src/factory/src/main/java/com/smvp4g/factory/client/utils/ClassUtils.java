/*
 * Copyright (C) 2009 - 2012 SMVP4G.COM
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package com.smvp4g.factory.client.utils;

import com.google.gwt.core.client.GWT;
import com.smvp4g.aop.client.utils.AopUtils;
import com.smvp4g.aop.client.utils.AopUtilsImpl;
import com.smvp4g.aop.client.wrapper.ClassWrapper;
import com.smvp4g.factory.client.ClassFactory;
import com.smvp4g.factory.client.ClassFactoryImpl;
import com.smvp4g.reflection.client.annotation.HasAnnotations;
import com.smvp4g.reflection.client.clazz.ClassType;
import com.smvp4g.reflection.client.field.FieldType;
import com.smvp4g.reflection.client.field.HasFields;
import com.smvp4g.reflection.client.method.HasMethods;
import com.smvp4g.reflection.client.method.MethodType;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * The Class ClassUtils.
 *
 * @author Nguyen Duc Dung
 * @since 12/3/11, 4:17 PM
 */
public final class ClassUtils {

    private static ClassFactory classFactory = GWT.create(ClassFactoryImpl.class);
    private static AopUtils aopUtils = GWT.create(AopUtilsImpl.class);

    private ClassUtils() {

    }

    public static <T extends Annotation> T getAnnotation(Class<?> clazz, Class<T> annotationClass) {
        Object proxy = getClassType(clazz);
        if (proxy instanceof HasAnnotations) {
            return ((HasAnnotations) proxy).getAnnotation(annotationClass);
        }
        return null;
    }

    public static List<FieldType> getFields(Class<?> clazz) {
        Object proxy = getClassType(clazz);
        if (proxy instanceof HasFields) {
            return ((HasFields) proxy).getFields();
        }
        return null;
    }
    
    public static FieldType getField(Class<?> clazz, String fieldName) {
        Object proxy = getClassType(clazz);
        if (proxy instanceof HasFields) {
            return ((HasFields) proxy).getField(fieldName);
        }
        return null;
    }

    public static List<MethodType> getMethods(Class<?> clazz) {
        Object proxy = getClassType(clazz);
        if (proxy instanceof HasMethods) {
            return ((HasMethods) proxy).getMethods();
        }
        return null;
    }

    public static MethodType getMethod(Class<?> clazz, String methodName) {
        Object proxy = getClassType(clazz);
        if (proxy instanceof HasMethods) {
            return ((HasMethods) proxy).getMethod(methodName);
        }
        return null;
    }


    public static ClassType getClassType(Class<?> clazz) {
        return classFactory.instantiate(getRealClass(clazz), ClassType.class);
    }

    @SuppressWarnings("unchecked")
    public static <T> T instantiate(Class<T> clazz) {
        return (T) classFactory.instantiate(clazz, clazz);
    }

    public static Class<?> getRealClass(Class<?> proxyClass) {
        return aopUtils.getRealClass(proxyClass);
    }

    public static Class<?> getRealClass(Object proxy) {
        if (proxy instanceof ClassWrapper) {
            return ((ClassWrapper) proxy).getRealClass();
        }
        return proxy.getClass();
    }
}
