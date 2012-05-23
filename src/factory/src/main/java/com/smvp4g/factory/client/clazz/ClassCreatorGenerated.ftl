[#ftl]
[#-- @ftlvariable name="data" type="com.smvp4g.factory.generator.clazz.ClassCreatorTemplateData" --]
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

package ${data.getGeneratePackageName()};

import com.smvp4g.factory.client.creator.Creator;
import com.smvp4g.reflection.client.clazz.ClassType;
import com.google.gwt.core.client.GWT;

/**
 * The Class ClassCreatorGenerated.
 *
 * @author Nguyen Duc Dung
 * @since 12/13/11, 11:12 AM
 */
public class ${data.getGenerateClassName()} extends ClassCreator {

    @Override
    public <T> T create(Class<?> clazz) {
        Object obj = null;
        [#list data.getClassScanModels() as class]
        if (clazz == ${class.getClassName()}.class) {
            [#if class.getIsClassLiteral()]
                obj = GWT.create(${class.getClassName()}.class);
            [#else]
                obj = new ${class.getClassName()}();
            [/#if]
        }
        [/#list]
        return (T)obj;
    }

    @Override
    public boolean isFor(Class<?> classType) {
        [#list data.getClassScanModels() as class]
        if (classType == ${class.getClassName()}.class) {
            return true;
        }
        [/#list]
        return false;
    }

}
