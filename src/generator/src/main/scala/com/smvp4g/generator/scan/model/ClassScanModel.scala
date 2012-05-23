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

package com.smvp4g.generator.scan.model

import reflect.BeanProperty

/**
 * The Class ClassScanModel.
 *
 * @author Nguyen Duc Dung
 * @since 5/23/12, 12:18 AM
 *
 */

class ClassScanModel {
  @BeanProperty var className: String = _
  @BeanProperty var simpleClassName: String = _
  @BeanProperty var isClassLiteral: Boolean = _
  @BeanProperty var isAspectable: Boolean = _
}
