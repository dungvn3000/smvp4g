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

package net.smvp.aop.scan.reader;

import net.smvp.generator.utils.ClassUtils;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.typeinfo.JClassType;
import net.smvp.aop.client.marker.Aspectable;
import net.smvp.aop.scan.model.ClassWrapperModel;
import net.smvp.generator.scan.reader.Reader;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class ClassWrapperReader.
 *
 * @author Nguyen Duc Dung
 * @since 12/8/11, 3:21 PM
 */
public class ClassWrapperReader implements Reader<ClassWrapperModel> {
    
    private List<ClassWrapperModel> datas = new ArrayList<ClassWrapperModel>();
    
    @Override
    public void read(JClassType classType, GeneratorContext context) {
        JClassType aspectable = ClassUtils.getJClassType(Aspectable.class.getName(), context);
        if (classType.isAssignableTo(aspectable)) {
            ClassWrapperModel model = new ClassWrapperModel();
            model.setClassName(classType.getSimpleSourceName());
            model.setPackageName(classType.getPackage().getName());
            model.setRealClassName(classType.getParameterizedQualifiedSourceName());
            datas.add(model);
        }
    }

    @Override
    public boolean isMath(Annotation checkAnnotation) {
        return true;
    }

    @Override
    public List<ClassWrapperModel> getData() {
        return datas;
    }
}
