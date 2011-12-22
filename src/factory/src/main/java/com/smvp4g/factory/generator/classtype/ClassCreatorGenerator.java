/*
 * Copyright (C) 2009 - 2012 SMVP4G.COM
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

package com.smvp4g.factory.generator.classtype;

import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JPackage;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.smvp4g.factory.client.classtype.ClassTypeCreator;
import com.smvp4g.factory.scan.reader.ClassReflectionReader;
import com.smvp4g.generator.generator.AbstractGenerator;
import com.smvp4g.generator.scan.ClassScanner;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class ClassCreatorGenerator.
 *
 * @author Nguyen Duc Dung
 * @since 11/23/11, 8:30 PM
 */
public class ClassCreatorGenerator extends AbstractGenerator<ClassTypeTemplateData> {

    private static final String TEMPLATE_FILE = "ClassTypeCreatorGenerated.ftl";
    private static final String DATA_TEMPLATE = "data";

    @Override
    protected Map<String, ClassTypeTemplateData> scan() {
        ClassReflectionReader classReflectionReader = new ClassReflectionReader();
        ClassScanner scanner = new ClassScanner();
        scanner.addReader(classReflectionReader);
        TypeOracle typeOracle = context.getTypeOracle();
        for (JPackage jPackage : typeOracle.getPackages()) {
            for (JClassType jClassType : jPackage.getTypes()) {
                scanner.scan(jClassType, context);
            }
        }
        Map<String, ClassTypeTemplateData> dataTemplateMap = new HashMap<String, ClassTypeTemplateData>();
        ClassTypeTemplateData data = new ClassTypeTemplateData(getClassName(), getPackageName());
        data.setClassScanModels(classReflectionReader.getData());
        dataTemplateMap.put(DATA_TEMPLATE, data);
        return dataTemplateMap;
    }

    @Override
    protected String getTemplateFileName() {
        return TEMPLATE_FILE;
    }

    @Override
    protected Class<?> getResourceClass() {
        return ClassTypeCreator.class;
    }
}
