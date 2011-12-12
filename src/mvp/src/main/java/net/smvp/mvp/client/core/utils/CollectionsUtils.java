/*
 * Copyright (C) 2009 - 2012 SMVP.NET
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

import java.util.*;

/**
 * The Class using instead of Apache Common CollectionsUtils..
 *
 * @author dungvn3000
 * @since 7/22/11, 2:12 PM
 */
public final class CollectionsUtils {

    private CollectionsUtils() {
        // Hide it.
    }

    /**
     * Check a collection is empty or not.
     *
     * @param collection
     * @return true if this collection is null or empty.
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * Using like isEmpty but it check a collection is not empty or not.
     *
     * @param collection
     * @return false if this collection is null or empty.
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * Using like subList() but it take randoms elements.
     *
     * @param list
     * @param sizeOfSubList
     */
    public static <E> void randomSubList(List<E> list, int sizeOfSubList) {
        List<E> subList = Collections.emptyList();
        if (isNotEmpty(list) && list.size() > sizeOfSubList) {
            subList = new ArrayList<E>(sizeOfSubList);
            Random generator = new Random();
            for (int i = 0; i < sizeOfSubList; i++) {
                int random = generator.nextInt(list.size());
                subList.add(list.get(random));
                list.remove(random);
            }
        }
        list.clear();
        list.addAll(subList);
    }

    /**
     * Scramble oder of elements in a list.
     *
     * @param list
     */
    public static <E> void scrambleList(List<E> list) {
        if (isNotEmpty(list)) {
            Random generator = new Random();
            for (int i = 0; i < list.size(); i++) {
                int random = generator.nextInt(list.size());
                if (random != i) {
                    //Util of vs.(swap)
                    E objTemp = list.get(i);
                    list.set(i, list.get(random));
                    list.set(random, objTemp);
                }
            }
        }
    }
}
