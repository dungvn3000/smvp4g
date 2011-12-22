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

package com.smvp4g.aop.generator.wrapper;

import com.smvp4g.aop.client.wrapper.ClassWrapper;
import com.smvp4g.aop.scan.reader.AspectMethodReader;
import com.smvp4g.generator.generator.AbstractGenerator;
import com.smvp4g.generator.scan.ClassScanner;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class ClassWrapperGenerator.
 *
 * @author Nguyen Duc Dung
 * @since 12/8/11, 2:20 PM
 */
public class ClassWrapperGenerator extends AbstractGenerator<ClassWrapperTemplateData> {

    public static final String FILE_PREFIX = "ClassWrapper" + AbstractGenerator.DEFAULT_FILE_PREFIX;

    private static final String TEMPLATE_FILE = "ClassWrapperImplGenerated.ftl";
    private static final String DATA_TEMPLATE = "data";

    @Override
    protected Map<String, ClassWrapperTemplateData> scan() {
        Map<String, ClassWrapperTemplateData> dataTemplateMap =
                new HashMap<String, ClassWrapperTemplateData>();

        ClassScanner scanner = new ClassScanner();
        AspectMethodReader aspectMethodReader = new AspectMethodReader();
        scanner.addReader(aspectMethodReader);
        scanner.scan(classType, context);

        ClassWrapperTemplateData dataTemplate = new ClassWrapperTemplateData(getClassName(), getPackageName());
        dataTemplate.setRealClassName(classType.getParameterizedQualifiedSourceName());
        dataTemplateMap.put(DATA_TEMPLATE, dataTemplate);
        dataTemplate.setMethodScanModels(aspectMethodReader.getData());
        return dataTemplateMap;
    }

    @Override
    protected String getTemplateFileName() {
        return TEMPLATE_FILE;
    }

    @Override
    protected Class<?> getResourceClass() {
        return ClassWrapper.class;
    }

    @Override
    protected String getFilePrefix() {
        return FILE_PREFIX;
    }
}
