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

package com.smvp.reflection.scan.reader;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.smvp.generator.scan.model.AnnotationScanModel;
import com.smvp.generator.scan.reader.Reader;
import com.smvp.generator.scan.utils.ScanUtils;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * The Class AnnotationReader.
 *
 * @author Nguyen Duc Dung
 * @since 12/3/11, 1:36 PM
 */
public class AnnotationReader implements Reader<AnnotationScanModel> {

    List<AnnotationScanModel> models;

    @Override
    public void read(JClassType classType, GeneratorContext context) {
        models = ScanUtils.getAnnotations(classType);
    }

    @Override
    public boolean isMath(Annotation checkAnnotation) {
        //TODO using criteria @dungvn3000
        return true;
    }

    @Override
    public List<AnnotationScanModel> getData() {
        return models;
    }
}
