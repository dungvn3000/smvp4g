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

package com.smvp.aop.scan.reader;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.smvp.aop.client.marker.Aspectable;
import com.smvp.generator.scan.model.MethodScanModel;
import com.smvp.generator.scan.reader.Reader;
import com.smvp.generator.utils.ClassUtils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class AspectMethodReader.
 *
 * @author Nguyen Duc Dung
 * @since 12/9/11, 10:33 AM
 */
public class AspectMethodReader implements Reader<MethodScanModel> {

    private List<MethodScanModel> data = new ArrayList<MethodScanModel>();

    @Override
    public void read(JClassType classType, GeneratorContext context) {
        JClassType aspectable = ClassUtils.getJClassType(Aspectable.class.getName(), context);
        if (classType.isAssignableTo(aspectable)) {
            for (JMethod jMethod : classType.getMethods()) {
                if (jMethod.getParameters().length == 0
                        && jMethod.getReturnType().getSimpleSourceName().contains("void")
                        && !jMethod.isPrivate()) {
                    MethodScanModel model = new MethodScanModel();
                    model.setName(jMethod.getName());
                    model.setReturnType(jMethod.getReturnType().getParameterizedQualifiedSourceName());
                    data.add(model);
                }
            }
        }
    }

    @Override
    public boolean isMath(Annotation checkAnnotation) {
        return false;
    }

    @Override
    public List<MethodScanModel> getData() {
        return data;
    }
}
