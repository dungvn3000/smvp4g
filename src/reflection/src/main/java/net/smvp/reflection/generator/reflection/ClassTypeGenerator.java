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

package net.smvp.reflection.generator.reflection;

import net.smvp.generator.generator.AbstractGenerator;
import net.smvp.generator.scan.ClassScanner;
import net.smvp.generator.utils.ClassUtils;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.typeinfo.JClassType;
import net.smvp.reflection.client.clazz.ClassType;
import net.smvp.reflection.client.marker.ReflectionTarget;
import net.smvp.reflection.scan.reader.AnnotationReader;
import net.smvp.reflection.scan.reader.MethodReader;
import net.smvp.reflection.scan.reader.FieldReader;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class ClassTypeGenerator.
 *
 * @author Nguyen Duc Dung
 * @since 12/3/11, 9:09 AM
 */
public class ClassTypeGenerator extends AbstractGenerator<ClassTypeTemplateData> {

    public static final String FILE_PREFIX = "ClassType" + AbstractGenerator.DEFAULT_FILE_PREFIX;

    private static final String TEMPLATE_FILE = "ClassTypeImplGenerated.ftl";
    private static final String DATA_TEMPLATE = "data";

    @Override
    protected Map<String, ClassTypeTemplateData> scan() {
        JClassType targetClassType = getTargetClassType(context, classType);
        if (targetClassType != null) {
            Map<String, ClassTypeTemplateData> dataTemplateMap = new HashMap<String, ClassTypeTemplateData>();
            ClassTypeTemplateData classModel = new ClassTypeTemplateData(getClassName(), getPackageName());
            classModel.setClassName(targetClassType.getSimpleSourceName());

            AnnotationReader annotationReader = new AnnotationReader();
            FieldReader fieldReader = new FieldReader();
            MethodReader methodReader = new MethodReader();
            ClassScanner scanner = new ClassScanner();
            scanner.addReader(annotationReader);
            scanner.addReader(fieldReader);
            scanner.addReader(methodReader);
            scanner.scan(targetClassType, context);

            classModel.setAnnotationScanModels(annotationReader.getData());
            classModel.setFieldScanModels(fieldReader.getData());
            classModel.setMethodScanModels(methodReader.getData());
            dataTemplateMap.put(DATA_TEMPLATE, classModel);
            return dataTemplateMap;
        }
        return null;
    }

    @Override
    protected String getTemplateFileName() {
        return TEMPLATE_FILE;
    }

    @Override
    protected Class<?> getResourceClass() {
        return ClassType.class;
    }

    @Override
    protected String getFilePrefix() {
        return FILE_PREFIX;
    }

    @Override
    protected String getPackageName() {
        return getTargetClassType(context, classType).getPackage().getName();
    }

    @Override
    protected String getClassName() {
        return getTargetClassType(context, classType).getSimpleSourceName() + getFilePrefix();
    }

    private JClassType getTargetClassType(GeneratorContext context, JClassType hostClassType) {
        ReflectionTarget reflectionTarget = hostClassType.getAnnotation(ReflectionTarget.class);
        if (reflectionTarget != null) {
            return ClassUtils.getJClassType(reflectionTarget.target().getName(), context);
        }
        return null;
    }
}
