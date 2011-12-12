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

import net.smvp.generator.scan.model.ClassScanModel;
import net.smvp.generator.utils.ClassUtils;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JPackage;
import net.smvp.aop.client.marker.Aspectable;
import net.smvp.generator.scan.reader.Reader;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class AspectableClassReader.
 *
 * @author Nguyen Duc Dung
 * @since 12/9/11, 11:55 AM
 */
public class AspectableClassReader implements Reader<ClassScanModel> {
    
    private List<ClassScanModel> data = new ArrayList<ClassScanModel>();
    
    @Override
    public void read(JClassType classType, GeneratorContext context) {
        for (JPackage jPackage : context.getTypeOracle().getPackages()) {
            for (JClassType jClassType : jPackage.getTypes()) {
                JClassType aspectable = ClassUtils.getJClassType(Aspectable.class.getName(), context);
                if (jClassType.isAssignableTo(aspectable) && !jClassType.isAbstract()) {
                    ClassScanModel model = new ClassScanModel();
                    model.setClassName(jClassType.getParameterizedQualifiedSourceName());
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
    public List<ClassScanModel> getData() {
        return data;
    }
}
