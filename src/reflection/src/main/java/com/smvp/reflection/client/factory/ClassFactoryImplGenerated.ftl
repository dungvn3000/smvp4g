[#ftl]
[#-- @ftlvariable name="data" type="com.smvp.reflection.generator.factory.ClassFactoryTemplateData" --]
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

import com.google.gwt.core.client.GWT;
import com.smvp.reflection.client.marker.Reflectable;
import com.smvp.reflection.client.marker.ReflectionTarget;
import com.smvp.reflection.client.clazz.ClassType;

/**
* The Class ClassFactoryImpl.
*
* @author Nguyen Duc Dung
* @since 11/23/11, 8:31 PM
*/
public class ${data.generateClassName} extends ClassFactoryImpl {
    @Override
    public <T> T instantiate(Class<T> clazz) {
        Object obj = null;
        [#list data.classScanModels as class]
            if (clazz == ${class.className}.class) {
            [#if class.aspectable]
                obj = AOP_FACTORY.instantiate(${class.className}.class);
            [#else]
                obj = new ${class.className}();
            [/#if]
            }
        [/#list]
        return (T) obj;
    }

    @Override
    public ClassType getClassType(Class<?> clazz) {
        Object obj = null;
        [#list data.classScanModels as class]
        if (clazz == ${class.className}.class) {
            obj = GWT.create(${class.simpleClassName}.class);
        }
        [/#list]
        return (ClassType) obj;
    }

    [#list data.classScanModels as class]
    @ReflectionTarget(target = ${class.className}.class)
    public static class ${class.simpleClassName} implements Reflectable {}
    [/#list]
}
