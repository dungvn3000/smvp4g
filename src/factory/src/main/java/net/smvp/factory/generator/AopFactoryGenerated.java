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

import net.smvp.generator.scan.ClassScanner;
import net.smvp.aop.scan.reader.AspectableClassReader;
import net.smvp.aop.scan.reader.InterceptorReader;
import net.smvp.generator.generator.AbstractGenerator;
import net.smvp.factory.client.AopFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class AopFactoryGenerated.
 *
 * @author Nguyen Duc Dung
 * @since 12/9/11, 11:50 AM
 */
public class AopFactoryGenerated extends AbstractGenerator<AopFactoryTemplateData> {
    
    private static final String TEMPLATE_FILE = "AopFactoryGeneratedImpl.ftl";
    private static final String DATA_TEMPLATE = "data";
    
    @Override
    protected Map<String, AopFactoryTemplateData> scan() {
        Map<String, AopFactoryTemplateData> data = new HashMap<String, AopFactoryTemplateData>();

        ClassScanner scanner = new ClassScanner();
        AspectableClassReader aspectableClassReader = new AspectableClassReader();
        scanner.addReader(aspectableClassReader);
        InterceptorReader interceptorReader = new InterceptorReader();
        scanner.addReader(interceptorReader);
        scanner.scan(classType, context);

        AopFactoryTemplateData templateData = new AopFactoryTemplateData(getClassName(), getPackageName());
        templateData.setClassScanModels(aspectableClassReader.getData());
        templateData.setInterceptorClassModels(interceptorReader.getData());
        data.put(DATA_TEMPLATE, templateData);
        return data;
    }

    @Override
    protected String getTemplateFileName() {
        return TEMPLATE_FILE;
    }

    @Override
    protected Class<?> getResourceClass() {
        return AopFactory.class;
    }
}
