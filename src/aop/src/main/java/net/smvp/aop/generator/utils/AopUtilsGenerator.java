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

package net.smvp.aop.generator.utils;

import net.smvp.generator.generator.AbstractGenerator;
import net.smvp.generator.scan.ClassScanner;
import net.smvp.aop.client.utils.AopUtils;
import net.smvp.aop.generator.wrapper.ClassWrapperGenerator;
import net.smvp.aop.scan.reader.AopUtilsReader;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class AopUtilsGenerator.
 *
 * @author Nguyen Duc Dung
 * @since 12/8/11, 5:47 PM
 */
public class AopUtilsGenerator extends AbstractGenerator<AopUtilsTemplateData> {
    
    private static final String TEMPLATE_FILE = "AopUtilsImplGenerated.ftl";
    private static final String DATA_TEMPLATE = "data";
    
    @Override
    protected Map<String, AopUtilsTemplateData> scan() {
        Map<String, AopUtilsTemplateData> dataTemplateMap =
                new HashMap<String, AopUtilsTemplateData>();
        ClassScanner scanner = new ClassScanner();
        AopUtilsReader aopUtilsReader = new AopUtilsReader();
        scanner.addReader(aopUtilsReader);
        scanner.scan(classType, context);        

        AopUtilsTemplateData data = new AopUtilsTemplateData(getClassName(), getPackageName());
        data.setClassWrapperModels(aopUtilsReader.getData());
        data.setClassWrapperPrefix(ClassWrapperGenerator.FILE_PREFIX);
        dataTemplateMap.put(DATA_TEMPLATE, data);
        return dataTemplateMap;
    }

    @Override
    protected String getTemplateFileName() {
        return TEMPLATE_FILE;
    }

    @Override
    protected Class<?> getResourceClass() {
        return AopUtils.class;
    }
}
