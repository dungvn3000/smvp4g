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

package com.smvp.reflection.client.clazz;

import com.smvp.reflection.client.field.FieldType;
import com.smvp.reflection.client.method.MethodType;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * The Class ClassTypeImpl.
 *
 * @author Nguyen Duc Dung
 * @since 12/5/11, 3:46 PM
 */
public class ClassTypeImpl implements ClassType {
    @Override
    public <T extends Annotation> T getAnnotation(Class<T> clazz) {
        return null;
    }

    @Override
    public List<FieldType> getFields() {
        return null;
    }

    @Override
    public FieldType getField(String fieldName) {
        return null;
    }

    @Override
    public List<MethodType> getMethods() {
        return null;
    }

    @Override
    public MethodType getMethod(String name) {
        return null;
    }
}
