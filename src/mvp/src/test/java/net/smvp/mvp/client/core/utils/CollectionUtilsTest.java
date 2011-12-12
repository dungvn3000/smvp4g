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

package net.smvp.mvp.client.core.utils;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * The Class CollectionUtilsTest.
 *
 * @author dungvn3000
 * @since 7/26/11, 7:19 PM
 */
@Test
public class CollectionUtilsTest {

    public void testIsEmpty() {
        assertEquals(CollectionsUtils.isEmpty(null), true);
        assertEquals(CollectionsUtils.isEmpty(Collections.emptyList()), true);

        List<String> testLists = new ArrayList<String>();
        testLists.add("test");
        assertEquals(CollectionsUtils.isEmpty(testLists), false);
    }

    public void testGetRandomSubList() {
        //Init a list
        List<Integer> testList = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            testList.add(i);
        }

        CollectionsUtils.randomSubList(testList, 6);

        assertEquals(testList.size(), 6);
        boolean hasDifferent = false;
        for (int i = 0 ; i < 6 ; i ++) {
            if (testList.get(i) != i) {
                hasDifferent = true;
                break;
            }
        }

        assertEquals(hasDifferent, true);
    }

    public void testScrambleList() {
        //Init a list
        List<Integer> testList = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            testList.add(i);
        }

        CollectionsUtils.scrambleList(testList);

        boolean hasDifferent = false;
        for (int i = 0 ; i < testList.size() ; i ++) {
            if (testList.get(i) != i) {
                hasDifferent = true;
                break;
            }
        }
        
        assertEquals(hasDifferent, true);
    }
}
