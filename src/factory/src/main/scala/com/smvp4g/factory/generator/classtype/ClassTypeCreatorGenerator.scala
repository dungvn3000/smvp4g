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

package com.smvp4g.factory.generator.classtype

import com.smvp4g.factory.client.classtype.ClassTypeCreator
import com.smvp4g.factory.scan.reader.ClassReflectionReader
import com.smvp4g.generator.AbstractGenerator
import com.smvp4g.generator.scan.ClassScanner
import collection.mutable.HashMap
import collection.JavaConverters._

/**
 * The Class ClassTypeCreatorGenerator.
 *
 * @author Nguyen Duc Dung
 * @since 5/22/12, 7:47 PM
 *
 */

class ClassTypeCreatorGenerator extends AbstractGenerator[ClassTypeTemplateData] {
  protected def templateFileName = "ClassTypeCreatorGenerated.ftl"

  protected def resourceClass = classOf[ClassTypeCreator]

  protected def scan = {
    val classReflectionReader = new ClassReflectionReader
    val scanner = new ClassScanner
    scanner.readers += classReflectionReader

    context.getTypeOracle.getPackages.foreach(jPackage => {
      jPackage.getTypes.foreach(jClassType => {
        scanner.scan(jClassType, context)
      })
    })

    val dataTemplateMap = new HashMap[String, ClassTypeTemplateData]
    val data = new ClassTypeTemplateData(className, packageName)
    data.classScanModels = classReflectionReader.data.asJava
    dataTemplateMap.put("data", data)
    dataTemplateMap.toMap
  }
}
