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

package com.smvp4g.aop.generator.wrapper;

import com.smvp4g.generator.generator.AbstractTemplateData;
import com.smvp4g.generator.scan.model.MethodScanModel;

import java.util.List;

/**
 * The Class ClassWrapperTemplateData.
 *
 * @author Nguyen Duc Dung
 * @since 12/8/11, 2:58 PM
 */
public class ClassWrapperTemplateData extends AbstractTemplateData {

    private String realClassName;
    private List<MethodScanModel> methodScanModels;

    public ClassWrapperTemplateData(String generateClassName, String packageClassName) {
        super(generateClassName, packageClassName);
    }

    public String getRealClassName() {
        return realClassName;
    }

    public void setRealClassName(String realClassName) {
        this.realClassName = realClassName;
    }

    public List<MethodScanModel> getMethodScanModels() {
        return methodScanModels;
    }

    public void setMethodScanModels(List<MethodScanModel> methodScanModels) {
        this.methodScanModels = methodScanModels;
    }
}
