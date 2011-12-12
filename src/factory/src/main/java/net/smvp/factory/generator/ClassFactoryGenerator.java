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

package net.smvp.factory.generator;

import net.smvp.factory.scan.reader.ClassFactoryReader;
import net.smvp.generator.scan.ClassScanner;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JPackage;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import net.smvp.generator.generator.AbstractGenerator;
import net.smvp.factory.client.ClassFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class ClassFactoryGenerator.
 *
 * @author Nguyen Duc Dung
 * @since 11/23/11, 8:30 PM
 */
public class ClassFactoryGenerator extends AbstractGenerator<ClassFactoryTemplateData> {

    private static final String TEMPLATE_FILE = "ClassFactoryImplGenerated.ftl";
    private static final String DATA_TEMPLATE = "data";

    @Override
    protected Map<String, ClassFactoryTemplateData> scan() {
        ClassFactoryReader classFactoryReader = new ClassFactoryReader();
        ClassScanner scanner = new ClassScanner();
        scanner.addReader(classFactoryReader);
        TypeOracle typeOracle = context.getTypeOracle();
        for (JPackage jPackage : typeOracle.getPackages()) {
            for (JClassType jClassType : jPackage.getTypes()) {
                scanner.scan(jClassType, context);
            }
        }
        scanner.analyzeResult(context);
        Map<String, ClassFactoryTemplateData> dataTemplateMap = new HashMap<String, ClassFactoryTemplateData>();
        ClassFactoryTemplateData data = new ClassFactoryTemplateData(getClassName(), getPackageName());
        data.setClassScanModels(classFactoryReader.getData());
        dataTemplateMap.put(DATA_TEMPLATE, data);
        return dataTemplateMap;
    }

    @Override
    protected String getTemplateFileName() {
        return TEMPLATE_FILE;
    }

    @Override
    protected Class<?> getResourceClass() {
        return ClassFactory.class;
    }
}
