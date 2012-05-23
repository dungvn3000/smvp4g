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

package com.smvp4g.aop.generator.utils

import com.smvp4g.aop.client.utils.AopUtils
import com.smvp4g.aop.scan.reader.AopUtilsReader
import com.smvp4g.generator.AbstractGenerator
import collection.mutable.HashMap
import com.smvp4g.generator.scan.ClassScanner
import collection.JavaConverters._

/**
 * The Class AopUtilsGenerator.
 *
 * @author Nguyen Duc Dung
 * @since 5/22/12, 3:29 PM
 *
 */

class AopUtilsGenerator extends AbstractGenerator[AopUtilsTemplateData] {

  protected def templateFileName = "AopUtilsImplGenerated.ftl"

  protected def resourceClass = classOf[AopUtils]

  protected def scan = {
    val dataTemplateMap = new HashMap[String, AopUtilsTemplateData]()
    val scanner = new ClassScanner
    val reader = new AopUtilsReader
    scanner.readers += reader
    scanner.scan(classType, context)
    val data = new AopUtilsTemplateData(className, packageName)
    data.classWrapperModels = reader.data.asJava
    data.classWrapperPrefix = "ClassWrapperGenerated"
    dataTemplateMap.put("data", data)
    dataTemplateMap.toMap
  }

}
