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

package com.smvp4g.reflection.client.field;

import com.smvp4g.reflection.client.executor.Executor;
import com.smvp4g.reflection.client.executor.HasGetter;
import com.smvp4g.reflection.client.executor.HasSetter;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class FieldTypeImpl.
 *
 * @author Nguyen Duc Dung
 * @since 12/4/11, 10:02 AM
 */
public class FieldTypeImpl implements FieldType, HasGetter, HasSetter {

    private String name;
    private Class<?> type;
    private Executor getterExecutor;
    private Executor setterExecutor;
    private List<Annotation> annotations = new ArrayList<Annotation>();

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Annotation> T getAnnotation(Class<T> clazz) {
        for (Annotation annotation : annotations) {
            if (clazz == annotation.annotationType()) {
                return (T) annotation;
            }
        }
        return null;
    }

    @Override
    public Object get(Object object) {
        if (getterExecutor != null) {
            return getterExecutor.execute(object);
        }
        return null;
    }

    @Override
    public void set(Object object, Object value) {
        if (setterExecutor != null) {
            setterExecutor.execute(object, value);
        }
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setType(Class<?> type) {
        this.type = type;
    }

    @Override
    public Class<?> getType() {
        return type;
    }

    @Override
    public void addAnnotation(Annotation annotation) {
        annotations.add(annotation);
    }

    @Override
    public void setGetterExecutor(Executor executor) {
        this.getterExecutor = executor;
    }

    @Override
    public void setSetterExecutor(Executor executor) {
        this.setterExecutor = executor;
    }
}
