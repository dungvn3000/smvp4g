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
 * The Class FieldScanModel.
 *
 * @author Nguyen Duc Dung
 * @since 12/4/11, 10:45 AM
 */
public class FieldScanModel {
    
    private String name;
    private String typeClassName;
    private List<AnnotationScanModel> annotationScanModels;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeClassName() {
        return typeClassName;
    }

    public void setTypeClassName(String typeClassName) {
        this.typeClassName = typeClassName;
    }

    public void setAnnotationScanModels(List<AnnotationScanModel> annotationScanModels) {
        this.annotationScanModels = annotationScanModels;
    }

    public List<AnnotationScanModel> getAnnotationScanModels() {
        return annotationScanModels;
    }
}
