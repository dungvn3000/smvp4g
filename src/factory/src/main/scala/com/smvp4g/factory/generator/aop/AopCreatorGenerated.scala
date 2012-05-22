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

package com.smvp4g.factory.generator.aop

import com.smvp4g.aop.scan.reader.{InterceptorReader, AspectableClassReader}
import com.smvp4g.factory.client.aop.AopCreator
import com.smvp4g.generator.AbstractGenerator
import collection.mutable.HashMap
import com.smvp4g.generator.scan.ClassScanner

/**
 * The Class AopCreatorGenerated.
 *
 * @author Nguyen Duc Dung
 * @since 5/22/12, 7:39 PM
 *
 */

class AopCreatorGenerated extends AbstractGenerator[AopCreatorTemplateData] {

  protected def templateFileName = "AopCreatorGenerated.ftl"

  protected def resourceClass = classOf[AopCreator]

  protected def scan = {
    val scanner = new ClassScanner
    val aspectableClassReader = new AspectableClassReader
    val interceptorReader = new InterceptorReader
    scanner.readers += aspectableClassReader
    scanner.readers += interceptorReader
    scanner.scan(classType, context)
    val data = new HashMap[String, AopCreatorTemplateData]
    val templateData = new AopCreatorTemplateData(className, packageName)
    templateData.classScanModels = aspectableClassReader.data
    templateData.interceptorClassModels = interceptorReader.data
    data.put("data", templateData)
    data.toMap
  }
}
