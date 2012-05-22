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

package com.smvp4g.generator.scan

import analyzer.Analyzer
import collection.mutable.ListBuffer
import com.google.gwt.core.ext.GeneratorContext
import com.google.gwt.core.ext.typeinfo.JClassType
import reader.Reader

/**
 * The Class ClassScanner.
 *
 * @author Nguyen Duc Dung
 * @since 5/22/12, 2:23 PM
 *
 */

class ClassScanner {

  var readers = new ListBuffer[Reader[_]]
  var analyzers = new ListBuffer[Analyzer]

  def scan(classType: JClassType,context: GeneratorContext) {
    readers.foreach(reader => reader.read(classType, context))
  }

  def analyzeResult(context: GeneratorContext) {
    analyzers.foreach(analyzer => analyzer.analyze(context))
  }

}
