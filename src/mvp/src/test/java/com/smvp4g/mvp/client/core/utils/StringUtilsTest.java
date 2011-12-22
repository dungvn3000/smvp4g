/*
 * Copyright (C) 2009 - 2011 SBCSOFT.NET
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

package com.smvp4g.mvp.client.core.utils;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * The Class StringUtilsTest.
 *
 * @author dungvn3000
 * @since 7/22/11, 3:09 PM
 */
@Test
public class StringUtilsTest {

    public void testIsEmpty() {
        assertEquals(StringUtils.isEmpty(null), true);
        assertEquals(StringUtils.isEmpty(""), true);
        assertEquals(StringUtils.isEmpty(" "), false);
    }

    public void testIsBlank() {
        assertEquals(StringUtils.isBlank(null), true);
        assertEquals(StringUtils.isBlank(""), true);
        assertEquals(StringUtils.isBlank(" "), true);
        assertEquals(StringUtils.isBlank("  "), true);
        assertEquals(StringUtils.isBlank(" 1 ") , false);
    }

    public void testGetFirstWord() {
        assertEquals(StringUtils.getHistoryName("TestWord"), "test");
        assertEquals(StringUtils.getHistoryName(" TestWord "), "test");
        assertEquals(StringUtils.getHistoryName("TWord"), "t");
        assertEquals(StringUtils.getHistoryName("TTTWord"), "tTT");
        assertEquals(StringUtils.getHistoryName("Word"), null);
        assertEquals(StringUtils.getHistoryName("TestPlaceWord" ), "testPlace");
    }

}
