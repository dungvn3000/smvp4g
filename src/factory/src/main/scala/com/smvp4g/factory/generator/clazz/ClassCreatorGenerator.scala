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

package com.smvp4g.factory.generator.clazz

import com.smvp4g.factory.client.clazz.ClassCreator
import com.smvp4g.factory.scan.analyzer.ClassCreatorAnalyzer
import com.smvp4g.factory.scan.reader.ClassReflectionReader
import com.smvp4g.generator.AbstractGenerator
import com.smvp4g.generator.scan.ClassScanner
import collection.mutable.HashMap
import collection.JavaConverters._

/**
 * The Class ClassCreatorGenerator.
 *
 * @author Nguyen Duc Dung
 * @since 5/22/12, 9:32 PM
 *
 */

class ClassCreatorGenerator extends AbstractGenerator[ClassCreatorTemplateData] {
  protected def templateFileName = "ClassCreatorGenerated.ftl"

  protected def resourceClass = classOf[ClassCreator]

  protected def scan = {
    val classReflectionReader = new ClassReflectionReader
    val creatorAnalyzer = new ClassCreatorAnalyzer
    creatorAnalyzer.reader = classReflectionReader
    val scanner = new ClassScanner
    scanner.analyzers += creatorAnalyzer
    scanner.readers += classReflectionReader
    context.getTypeOracle.getPackages.foreach(jPackage => {
      jPackage.getTypes.foreach(jClassType => {
        scanner.scan(jClassType, context)
      })
    })
    scanner.analyzeResult(context)
    val dataTemplateMap = new HashMap[String, ClassCreatorTemplateData]
    val data = new ClassCreatorTemplateData(className, packageName)
    data.classScanModels = classReflectionReader.data.asJava
    dataTemplateMap.put("data", data)
    dataTemplateMap.toMap
  }
}
