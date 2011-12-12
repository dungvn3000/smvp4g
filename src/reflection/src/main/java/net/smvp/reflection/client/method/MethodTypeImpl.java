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

package net.smvp.reflection.client.method;

import net.smvp.reflection.client.executor.Executor;
import net.smvp.reflection.client.executor.HasGetter;

import java.lang.annotation.Annotation;

/**
 * The Class MethodTypeImpl.
 *
 * @author Nguyen Duc Dung
 * @since 12/4/11, 4:02 PM
 */
public class MethodTypeImpl implements MethodType, HasGetter {
    
    private String name;
    private Class<?> returnType;
    private Executor executor;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Class<?> getReturnType() {
        return returnType;
    }

    @Override
    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }

    @Override
    public Object invoke(Object object) {
        if (executor != null) {
            return executor.execute(object);
        }
        return null;
    }

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> clazz) {
        return null;
    }

    @Override
    public void setGetterExecutor(Executor executor) {
        this.executor = executor;
    }
}
