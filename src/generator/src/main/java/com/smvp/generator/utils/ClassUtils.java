/*
 * Copyright (C) 2009 - 2012 SMVP.NET
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

package com.smvp.generator.utils;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * The Class ClassUtils.
 *
 * @author Nguyen Duc Dung
 * @since 11/20/11, 4:59 PM
 */
@SuppressWarnings("CallToPrintStackTrace")
public final class ClassUtils {

    private ClassUtils() {

    }

    public static <T extends Annotation> T getAnnotation(Class<?> clazz, Class<T> annotationClass,
                                                         GeneratorContext context) {
        return getAnnotation(clazz.getName(), annotationClass, context);
    }

    public static <T extends Annotation> T getAnnotation(String className, Class<T> annotationClass,
                                                         GeneratorContext context) {
        try {
            JClassType classType = context.getTypeOracle().getType(className);
            return classType.getAnnotation(annotationClass);
        } catch (NotFoundException e) {
            //TODO: will be remove soon @dungvn3000
            e.printStackTrace();
        }
        return null;
    }

    public static String getSimpleNameOfClass(String className, GeneratorContext context) {
        try {
            JClassType classType = context.getTypeOracle().getType(className);
            return classType.getSimpleSourceName();
        } catch (NotFoundException e) {
            //TODO: will be remove soon @dungvn3000
            e.printStackTrace();
        }
        return null;
    }

    public static <T> Class<T> getClass(String className, Class<T> clazz) {
        try {
            return (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            //TODO: will be remove soon @dungvn3000
            e.printStackTrace();
        }
        return null;
    }

    public static <T> JClassType getJClassType(String className, GeneratorContext context) {
        try {
            return context.getTypeOracle().getType(className);
        } catch (NotFoundException e) {
            //TODO: will be remove soon @dungvn3000
            e.printStackTrace();
        }
        return null;
    }

    public static Object invokeMethod(Method method, Object instance) {
        try {
            return method.invoke(instance);
        } catch (IllegalAccessException e) {
            //TODO: will be remove soon @dungvn3000
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            //TODO: will be remove soon @dungvn3000
            e.printStackTrace();
        }
        return null;
    }
}
