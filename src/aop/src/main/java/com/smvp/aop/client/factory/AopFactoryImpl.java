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

package com.smvp.aop.client.factory;

import com.smvp.aop.client.interceptor.HasInterceptor;
import com.smvp.aop.client.interceptor.MethodInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class AopFactoryImpl.
 *
 * @author Nguyen Duc Dung
 * @since 12/9/11, 11:39 AM
 */
public class AopFactoryImpl implements AopFactory {

    protected List<MethodInterceptor> interceptors = new ArrayList<MethodInterceptor>();

    @Override
    public <T> T instantiate(Class<T> clazz) {
        //Don't do anything GWT generator will do it.
        return null;
    }

    protected void injectInterceptor(HasInterceptor hasInterceptor) {
        for (MethodInterceptor interceptor : interceptors) {
            hasInterceptor.addInterceptor(interceptor);
        }
    }
}
