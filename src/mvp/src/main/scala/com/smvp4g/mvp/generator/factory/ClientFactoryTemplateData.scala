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

import com.smvp4g.generator.AbstractTemplateData
import com.smvp4g.mvp.scan.model.PresenterScanModel
import reflect.BeanProperty

/**
 * The Class ClientFactoryTemplateData.
 *
 * @author Nguyen Duc Dung
 * @since 5/22/12, 9:55 PM
 *
 */

class ClientFactoryTemplateData(generateClassName: String, generatePackageName: String) extends
AbstractTemplateData(generateClassName, generatePackageName) {
  @BeanProperty var presenterScanModels: java.util.List[PresenterScanModel] = _
}
