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

package com.smvp4g.mvp.generator.factory

import com.smvp4g.generator.AbstractGenerator
import com.smvp4g.generator.scan.ClassScanner
import com.smvp4g.mvp.client.core.factory.ClientFactoryImpl
import com.smvp4g.mvp.scan.analyzer.{PresenterAnalyzer, TokenAnalyzer}
import com.smvp4g.mvp.scan.reader.{ModuleReader, PresenterReader}
import collection.mutable.HashMap
import collection.JavaConverters._
/**
 * The Class ClientFactoryGenerator.
 *
 * @author Nguyen Duc Dung
 * @since 5/22/12, 9:58 PM
 *
 */

class ClientFactoryGenerator extends AbstractGenerator[ClientFactoryTemplateData] {
  protected def templateFileName = "ClientFactoryImplGenerated.ftl"

  protected def resourceClass = classOf[ClientFactoryImpl]

  protected def scan = {
    val presenterReader = new PresenterReader
    val moduleReader = new ModuleReader
    val tokenAnalyzer = new TokenAnalyzer(presenterReader, moduleReader)
    val presenterAnalyzer = new PresenterAnalyzer(presenterReader, moduleReader)

    val scanner = new ClassScanner
    scanner.readers += presenterReader
    scanner.readers += moduleReader
    scanner.analyzers += tokenAnalyzer
    scanner.analyzers += presenterAnalyzer

    context.getTypeOracle.getPackages.foreach(jPackage => {
      jPackage.getTypes.foreach(jClassType => {
        scanner.scan(jClassType, context)
      })
    })

    scanner.analyzeResult(context)
    val dataTemplateMap = new HashMap[String, ClientFactoryTemplateData]
    val data = new ClientFactoryTemplateData(className, packageName)
    data.presenterScanModels = presenterReader.data.asJava
    dataTemplateMap.put("data", data)
    dataTemplateMap.toMap
  }
}
