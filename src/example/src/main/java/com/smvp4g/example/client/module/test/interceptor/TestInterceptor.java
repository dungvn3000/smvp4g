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

package com.smvp4g.example.client.module.test.interceptor;

import com.allen_sauer.gwt.log.client.Log;
import com.smvp4g.aop.client.interceptor.MethodInterceptor;
import com.smvp4g.aop.client.marker.Aspect;

/**
 * The Class TestInterceptor.
 *
 * @author Nguyen Duc Dung
 * @since 12/9/11, 10:26 AM
 */
@Aspect
public class TestInterceptor implements MethodInterceptor {
    @Override
    public void invoke(Object... prams) {
        Log.debug("Method " + prams[0].toString() + " is calling");
    }
}
