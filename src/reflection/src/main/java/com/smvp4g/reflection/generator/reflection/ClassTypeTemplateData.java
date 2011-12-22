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

package com.smvp4g.reflection.generator.reflection;

import com.smvp4g.generator.generator.AbstractTemplateData;
import com.smvp4g.generator.scan.model.AnnotationScanModel;
import com.smvp4g.generator.scan.model.FieldScanModel;
import com.smvp4g.generator.scan.model.MethodScanModel;

import java.util.List;

/**
 * The Class ClassTypeTemplateData.
 *
 * @author Nguyen Duc Dung
 * @since 12/3/11, 1:26 PM
 */
public class ClassTypeTemplateData extends AbstractTemplateData {
    
    private String className;

    private List<AnnotationScanModel> annotationScanModels;
    private List<FieldScanModel> fieldScanModels;
    private List<MethodScanModel> methodScanModels;

    public ClassTypeTemplateData(String generateClassName, String packageClassName) {
        super(generateClassName, packageClassName);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<AnnotationScanModel> getAnnotationScanModels() {
        return annotationScanModels;
    }

    public void setAnnotationScanModels(List<AnnotationScanModel> annotationScanModels) {
        this.annotationScanModels = annotationScanModels;
    }

    public List<FieldScanModel> getFieldScanModels() {
        return fieldScanModels;
    }

    public void setFieldScanModels(List<FieldScanModel> fieldScanModels) {
        this.fieldScanModels = fieldScanModels;
    }

    public List<MethodScanModel> getMethodScanModels() {
        return methodScanModels;
    }

    public void setMethodScanModels(List<MethodScanModel> methodScanModels) {
        this.methodScanModels = methodScanModels;
    }
}
