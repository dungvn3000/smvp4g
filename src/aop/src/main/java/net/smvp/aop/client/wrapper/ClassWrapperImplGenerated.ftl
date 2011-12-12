[#ftl]
[#-- @ftlvariable name="data" type="net.smvp.aop.generator.wrapper.ClassWrapperTemplateData" --]
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

import net.smvp.aop.client.wrapper.ClassWrapper;
import net.smvp.aop.client.interceptor.MethodInterceptor;
import java.util.List;
import java.util.ArrayList;

/**
 * The Class ClassWrapperImplGenerated.
 *
 * @author Nguyen Duc Dung
 * @since 12/8/11, 2:43 PM
 */
public class ${data.generateClassName} extends ${data.realClassName} implements ClassWrapper {

    protected List<MethodInterceptor> interceptors = new ArrayList<MethodInterceptor>();

    [#list data.methodScanModels as method]
    @Override
    public void ${method.name}() {
        invokeInterceptor("${method.name}()");
        super.${method.name}();
    }
    [/#list]

    @Override
    public Class<?> getRealClass() {
        return super.getClass();
    }

    protected void invokeInterceptor(Object... prams) {
        for (MethodInterceptor interceptor : interceptors) {
            interceptor.invoke(prams);
        }
    }

    public void addInterceptor(MethodInterceptor interceptor) {
        interceptors.add(interceptor);
    }
}
