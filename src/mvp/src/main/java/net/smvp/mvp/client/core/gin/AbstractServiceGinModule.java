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

package net.smvp.mvp.client.core.gin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.gwtent.reflection.client.ClassHelper;
import net.smvp.mvp.client.core.service.RemoteServiceAsync;
import net.smvp.mvp.client.core.utils.StringUtils;

/**
 * The Class AbstractServiceGinModule.
 *
 * @author dungvn3000
 * @since 5/8/11, 11:08 AM
 */
public abstract class AbstractServiceGinModule extends AbstractGinModule {
    @Override
    protected final void configure() {
        //Do nothing.
    }

    /**
     * Build configure for service.
     *
     * @param s   Remote Service Class
     * @param t   Remote Service Async.
     * @param <T> Remote Service Async.
     * @return A instance of Async Remote Service was be configured.
     */
    protected <T extends RemoteServiceAsync<?>> T buildConfigure(Class<?> s, T t) {
        if (StringUtils.isNotEmpty(getCustomizeServiceEntryPoint())) {
            RemoteServiceRelativePath relativePath = ClassHelper.AsClass(s).getAnnotation(RemoteServiceRelativePath.class);
            if (relativePath != null && StringUtils.isNotEmpty(relativePath.value())) {
                ServiceDefTarget endpoint = (ServiceDefTarget) t;
                endpoint.setServiceEntryPoint(StringUtils.concatUrlPath(getCustomizeServiceEntryPoint(),
                        relativePath.value()));
                return t;
            }
        }
        return t;
    }


    /**
     * Override it if need customize ServiceEntryPoint url.
     *
     * @return String url path of service.
     */
    protected String getCustomizeServiceEntryPoint() {
        return StringUtils.EMPTY;
    }
}
