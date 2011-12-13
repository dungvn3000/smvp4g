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

package net.smvp.factory.scan.reader;

import net.smvp.aop.client.marker.Aspectable;
import net.smvp.generator.scan.model.ClassScanModel;
import net.smvp.generator.utils.ClassUtils;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.typeinfo.JClassType;
import net.smvp.generator.scan.reader.Reader;
import net.smvp.reflection.client.marker.Reflection;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class ClassReflectionReader.
 *
 * @author Nguyen Duc Dung
 * @since 11/23/11, 9:16 PM
 */
public class ClassReflectionReader implements Reader<ClassScanModel> {

    private List<ClassScanModel> modelFactories = new ArrayList<ClassScanModel>();

    @Override
    public void read(JClassType classType, GeneratorContext context) {
        if (isMath(classType.getAnnotation(Reflection.class))) {
            for (JClassType subType : classType.getSubtypes()) {
                if(!subType.isAbstract()) {
                    ClassScanModel model = new ClassScanModel();
                    model.setClassName(subType.getParameterizedQualifiedSourceName());
                    model.setSimpleClassName(subType.getSimpleSourceName());
                    JClassType aspectable = ClassUtils.getJClassType(Aspectable.class.getName(), context);
                    if (subType.isAssignableTo(aspectable)) {
                        model.setAspectable(true);
                    }
                    modelFactories.add(model);
                }
            }
        }
    }

    @Override
    public boolean isMath(Annotation checkAnnotation) {
        return checkAnnotation != null;
    }

    @Override
    public List<ClassScanModel> getData() {
        return modelFactories;
    }

}
