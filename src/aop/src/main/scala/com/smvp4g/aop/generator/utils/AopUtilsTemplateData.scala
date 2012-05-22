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

import com.smvp4g.aop.scan.model.ClassWrapperModel
import com.smvp4g.generator.AbstractTemplateData

/**
 * The Class AopUtilsTemplateData.
 *
 * @author Nguyen Duc Dung
 * @since 5/22/12, 3:25 PM
 *
 */

class AopUtilsTemplateData(generateClassName: String, generatePackageName: String) extends
AbstractTemplateData(generateClassName, generatePackageName) {
  var classWrapperPrefix: String = _
  var classWrapperModels: List[ClassWrapperModel] = _
}
