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

package com.smvp.generator.scan.model;

import java.util.List;

/**
 * The Class MethodScanModel.
 *
 * @author Nguyen Duc Dung
 * @since 12/3/11, 6:14 PM
 */
public class MethodScanModel {
    
    private String name;
    private String returnType;
    private String returnValue;
    private List<AnnotationScanModel> annotationScanModels;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    public List<AnnotationScanModel> getAnnotationScanModels() {
        return annotationScanModels;
    }

    public void setAnnotationScanModels(List<AnnotationScanModel> annotationScanModels) {
        this.annotationScanModels = annotationScanModels;
    }
}
