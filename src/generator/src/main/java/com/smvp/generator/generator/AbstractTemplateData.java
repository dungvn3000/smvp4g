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

package com.smvp.generator.generator;

/**
 * The Class AbstractTemplateData.
 *
 * @author Nguyen Duc Dung
 * @since 12/8/11, 7:14 PM
 */
public abstract class AbstractTemplateData {
    
    private String generateClassName;
    private String generatePackageName;
    
    public AbstractTemplateData(String generateClassName, String generatePackageName) {
        this.generateClassName = generateClassName;
        this.generatePackageName = generatePackageName;
    }
    
    public String getGenerateClassName() {
        return generateClassName;
    }

    public void setGenerateClassName(String generateClassName) {
        this.generateClassName = generateClassName;
    }

    public String getGeneratePackageName() {
        return generatePackageName;
    }

    public void setGeneratePackageName(String generatePackageName) {
        this.generatePackageName = generatePackageName;
    }
}
