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

package com.smvp.aop.generator.utils;

import com.smvp.aop.scan.model.ClassWrapperModel;
import com.smvp.generator.generator.AbstractTemplateData;

import java.util.List;

/**
 * The Class AopUtilsTemplateData.
 *
 * @author Nguyen Duc Dung
 * @since 12/8/11, 5:48 PM
 */
public class AopUtilsTemplateData extends AbstractTemplateData {

    private String classWrapperPrefix;
    private List<ClassWrapperModel> classWrapperModels;

    public AopUtilsTemplateData(String generateClassName, String packageClassName) {
        super(generateClassName, packageClassName);
    }

    public List<ClassWrapperModel> getClassWrapperModels() {
        return classWrapperModels;
    }

    public void setClassWrapperModels(List<ClassWrapperModel> classWrapperModels) {
        this.classWrapperModels = classWrapperModels;
    }

    public String getClassWrapperPrefix() {
        return classWrapperPrefix;
    }

    public void setClassWrapperPrefix(String classWrapperPrefix) {
        this.classWrapperPrefix = classWrapperPrefix;
    }


}
