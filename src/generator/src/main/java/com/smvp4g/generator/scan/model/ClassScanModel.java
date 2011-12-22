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

package com.smvp4g.generator.scan.model;

/**
 * The Class ClassScanModel.
 *
 * @author Nguyen Duc Dung
 * @since 12/3/11, 1:25 PM
 */
public class ClassScanModel {

    private String className;
    private String simpleClassName;
    private boolean isClassLiteral;
    private boolean isAspectable;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSimpleClassName() {
        return simpleClassName;
    }

    public void setSimpleClassName(String simpleClassName) {
        this.simpleClassName = simpleClassName;
    }

    public boolean isAspectable() {
        return isAspectable;
    }

    public void setAspectable(boolean aspectable) {
        isAspectable = aspectable;
    }

    public boolean isClassLiteral() {
        return isClassLiteral;
    }

    public void setClassLiteral(boolean classLiteral) {
        isClassLiteral = classLiteral;
    }
}