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

package com.smvp.generator.scan.utils;

import com.google.gwt.core.ext.typeinfo.HasAnnotations;
import com.smvp.generator.scan.model.AnnotationScanModel;
import com.smvp.generator.scan.model.MethodScanModel;
import com.smvp.generator.utils.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class ScanUtils.
 *
 * @author Nguyen Duc Dung
 * @since 12/4/11, 11:45 AM
 */
public final class ScanUtils {

    private ScanUtils() {

    }

    public static List<AnnotationScanModel> getAnnotations(HasAnnotations object) {
        List<AnnotationScanModel> models = new ArrayList<AnnotationScanModel>();
        for (Annotation annotation : object.getAnnotations()) {
            AnnotationScanModel annotationScanModel = new AnnotationScanModel();
            annotationScanModel.setAnnotationClassName(annotation.annotationType().getName());
            for (Method method : annotation.annotationType().getMethods()) {
                if (method.getDeclaringClass() == annotation.annotationType()) {
                    MethodScanModel methodScanModel = new MethodScanModel();
                    methodScanModel.setName(method.getName());
                    methodScanModel.setReturnType(method.getReturnType().getName());
                    methodScanModel.setReturnValue(buildReturnValueString(ClassUtils.
                            invokeMethod(method, annotation)));
                    annotationScanModel.getMethods().add(methodScanModel);
                }
            }
            models.add(annotationScanModel);
        }
        return models;
    }

    public static String buildReturnValueString(Object returnValue) {
        if (returnValue instanceof Class) {
            Class clazz = (Class) returnValue;
            return clazz.getName() + ".class";
        } else if (returnValue instanceof String) {
            String st = (String) returnValue;
            return "\"" + st + "\"";
        }
        return returnValue.toString();
    }

}
