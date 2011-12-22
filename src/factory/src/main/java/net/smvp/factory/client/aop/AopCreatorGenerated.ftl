[#ftl]
[#-- @ftlvariable name="data" type="net.smvp.factory.generator.aop.AopCreatorTemplateData" --]
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

package ${data.generatePackageName};

import net.smvp.factory.client.creator.Creator;
import com.google.gwt.core.client.GWT;
import net.smvp.aop.client.interceptor.*;

/**
 * The Class AopCreator.
 *
 * @author Nguyen Duc Dung
 * @since 12/13/11, 9:55 AM
 */
public class ${data.generateClassName} extends AopCreator {

    public ${data.generateClassName}() {
        createIntercrepter();
    }

    @Override
    public <T> T create(Class<?> clazz) {
        T obj = null;
        [#list data.classScanModels as class]
            if (clazz == ${class.className}.class) {
                obj = GWT.create(${class.className}.class);
                injectInterceptor((HasInterceptor)obj);
            }
        [/#list]
        return obj;
    }

    @Override
    public boolean isFor(Class<?> classType) {
        [#list data.classScanModels as class]
        if (classType == ${class.className}.class) {
            return true;
        }
        [/#list]
        return false;
    }

    public void createIntercrepter() {
        [#list data.interceptorClassModels as intercreptor]
            interceptors.add(new ${intercreptor.className}());
        [/#list]
    }
}