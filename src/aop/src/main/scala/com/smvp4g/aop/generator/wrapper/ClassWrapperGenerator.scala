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

package com.smvp4g.aop.generator.wrapper

import com.smvp4g.aop.client.wrapper.ClassWrapper
import com.smvp4g.aop.scan.reader.AspectMethodReader
import com.smvp4g.generator.AbstractGenerator
import collection.mutable.HashMap
import com.smvp4g.generator.scan.ClassScanner

/**
 * The Class ClassWrapperGenerator.
 *
 * @author Nguyen Duc Dung
 * @since 5/22/12, 3:49 PM
 *
 */

class ClassWrapperGenerator extends AbstractGenerator[ClassWrapperTemplateData] {
  protected def templateFileName = "ClassWrapperImplGenerated.ftl"

  protected def resourceClass = classOf[ClassWrapper]

  override val filePrefix = "ClassWrapperGenerated"

  protected def scan = {
    val dataTemplateMap = new HashMap[String, ClassWrapperTemplateData]
    val scanner = new ClassScanner
    val aspectMethodReader = new AspectMethodReader
    scanner.readers += aspectMethodReader
    scanner.scan(classType, context)
    val dataTemplate = new ClassWrapperTemplateData(className, packageName)
    dataTemplate.realClassName = classType.getQualifiedSourceName
    dataTemplate.methodScanModels = aspectMethodReader.data
    dataTemplateMap.put("data", dataTemplate)
    dataTemplateMap.toMap
  }
}
