package com.gklijs.adventofcode.utils;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.RandomAccess;

public class GapList<E> extends AbstractList<E> implements List<E>, RandomAccess, java.io.Serializable {

    /*
     *                 Sun Public License Notice
     *
     * The contents of this file are subject to the Sun Public License
     * Version 1.0 (the "License"). You may not use this file except in
     * compliance with the License. A copy of the License is available at
     * http://www.sun.com/
     *
     * The Original Code is NetBeans. The Initial Developer of the Original
     * Code is Sun Microsystems, Inc. Portions Copyright 1997-2000 Sun
     * Microsystems, Inc. All Rights Reserved.
     */

    /**
     * List implementation that stores items in an array
     * with a gap.
     *
     * @author Miloslav Metelka
     * @version 1.00
     */

    private static final Object[] EMPTY_ELEMENT_ARRAY = new Object[0];

    /**
     * The array buffer into which the elements are stored.
     * <p>
     * <p>
     * The elements are stored in the whole array except
     * the indexes starting at gapStart
     * till gapStart + gapLength - 1.
     */
    private transient Object[] elementData;

    /**
     * The start of the gap in the elementData array.
     */
    private int gapStart;

    /**
     * Length of the gap in the elementData array starting at gapStart.
     */
    private int gapLength;

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the list.
     * @throws IllegalArgumentException if the specified initial capacity
     *                                  is negative
     */
    public GapList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " // NOI18N
                + initialCapacity);
        }
        this.elementData = new Object[initialCapacity];
        this.gapLength = initialCapacity;
    }

    /**
     * Constructs an empty list.
     */
    public GapList() {
        elementData = EMPTY_ELEMENT_ARRAY;
    }

    /**
     * Constructs a list containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.  The GapList instance has an initial capacity of
     * 110% the size of the specified collection.
     *
     * @param c the collection whose elements are to be placed into this list.
     * @throws NullPointerException if the specified collection is null.
     */
    public GapList(Collection c) {
        int size = c.size();
        // Allow 10% room for growth
        elementData = new Object[
            (int) Math.min((size * 110L) / 100, Integer.MAX_VALUE)];
        c.toArray(elementData);
        this.gapStart = size;
        this.gapLength = elementData.length - size;
    }

    /**
     * Trims the capacity of this GapList instance to be the
     * list's current size.  An application can use this operation to minimize
     * the storage of an GapList instance.
     */
    public void trimToSize() {
        modCount++;
        if (gapLength > 0) {
            int newLength = elementData.length - gapLength;
            Object[] newElementData = new Object[newLength];
            copyAllData(newElementData);
            elementData = newElementData;
            // Leave gapStart as is
            gapLength = 0;
        }
    }

    /**
     * Increases the capacity of this GapList instance, if
     * necessary, to ensure  that it can hold at least the number of elements
     * specified by the minimum capacity argument.
     *
     * @param minCapacity the desired minimum capacity.
     */
    private void ensureCapacity(int minCapacity) {
        modCount++; // expected to always increment modCount - see add() operations
        int oldCapacity = elementData.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = (oldCapacity * 3) / 2 + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            int gapEnd = gapStart + gapLength;
            int afterGapLength = (oldCapacity - gapEnd);
            // Must ensure the gap will not be logically moved
            // (would have to call movedAbove/BeforeGapUpdate() methods)
            int newGapEnd = newCapacity - afterGapLength;
            Object[] newElementData = new Object[newCapacity];
            System.arraycopy(elementData, 0, newElementData, 0, gapStart);
            System.arraycopy(elementData, gapEnd, newElementData, newGapEnd, afterGapLength);
            elementData = newElementData;
            gapLength = newGapEnd - gapStart;
        }
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list.
     */
    public int size() {
        return elementData.length - gapLength;
    }

    /**
     * Tests if this list has no elements.
     *
     * @return true if this list has no elements;
     * false otherwise.
     */
    public boolean isEmpty() {
        return (elementData.length == gapLength);
    }

    /**
     * Returns true if this list contains the specified element.
     *
     * @param elem element whose presence in this List is to be tested.
     * @return true if the specified element is present;
     * false otherwise.
     */
    public boolean contains(Object elem) {
        return indexOf(elem) >= 0;
    }

    /**
     * Searches for the first occurence of the given argument, testing
     * for equality using the equals method.
     *
     * @param elem an object.
     * @return the index of the first occurrence of the argument in this
     * list; returns -1 if the object is not found.
     * @see Object#equals(Object)
     */
    public int indexOf(Object elem) {
        if (elem == null) {
            int i = 0;
            while (i < gapStart) {
                if (elementData[i] == null) {
                    return i;
                }
                i++;
            }
            i += gapLength;
            int elementDataLength = elementData.length;
            while (i < elementDataLength) {
                if (elementData[i] == null) {
                    return i;
                }
                i++;
            }
        } else { // elem not null
            int i = 0;
            while (i < gapStart) {
                if (elem.equals(elementData[i])) {
                    return i;
                }
                i++;
            }
            i += gapLength;
            int elementDataLength = elementData.length;
            while (i < elementDataLength) {
                if (elem.equals(elementData[i])) {
                    return i;
                }
                i++;
            }
        }

        return -1;
    }

    private int lastIndex() {
        int i = elementData.length - 1;
        int gapEnd = gapStart + gapLength;
        while (i >= gapEnd) {
            if (elementData[i] == null) {
                return i;
            }
            i--;
        }
        i -= gapLength;
        while (i >= 0) {
            if (elementData[i] == null) {
                return i;
            }
            i--;
        }
        return -1;
    }

    private int lastIndexOfNotNull(Object elem) {
        int i = elementData.length - 1;
        int gapEnd = gapStart + gapLength;
        while (i >= gapEnd) {
            if (elem.equals(elementData[i])) {
                return i;
            }
            i--;
        }
        i -= gapLength;
        while (i >= 0) {
            if (elem.equals(elementData[i])) {
                return i;
            }
            i--;
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified object in
     * this list.
     *
     * @param elem the desired element.
     * @return the index of the last occurrence of the specified object in
     * this list; returns -1 if the object is not found.
     */
    public int lastIndexOf(Object elem) {
        if (elem == null) {
            return lastIndex();
        } else { // elem not null
            return lastIndexOfNotNull(elem);
        }
    }

    /**
     * Returns an array containing all of the elements in this list
     * in the correct order.
     *
     * @return an array containing all of the elements in this list
     * in the correct order.
     */
    public Object[] toArray() {
        int size = size();
        Object[] result = new Object[size];
        copyAllData(result);
        return result;
    }

    /**
     * Returns an array containing all of the elements in this list in the
     * correct order; the runtime type of the returned array is that of the
     * specified array.  If the list fits in the specified array, it is
     * returned therein.  Otherwise, a new array is allocated with the runtime
     * type of the specified array and the size of this list.
     * <p>
     * <p>
     * If the list fits in the specified array with room to spare (i.e., the
     * array has more elements than the list), the element in the array
     * immediately following the end of the collection is set to
     * null.  This is useful in determining the length of the list
     * only if the caller knows that the list does not contain any
     * null elements.
     *
     * @param a the array into which the elements of the list are to
     *          be stored, if it is big enough; otherwise, a new array of the
     *          same runtime type is allocated for this purpose.
     * @return an array containing the elements of the list.
     * @throws ArrayStoreException if the runtime type of a is not a supertype
     *                             of the runtime type of every element in this list.
     */
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        int size = size();
        if (a.length < size) {
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        }
        copyAllData(a);
        if (a.length > size)
            a[size] = null;

        return a;
    }

    // Positional Access Operations

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of element to return.
     * @return the element at the specified position in this list.
     * @throws IndexOutOfBoundsException if index is out of range (index
     *                                   < 0 || index >= size()).
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        // rangeCheck(index) not necessary - would fail with AIOOBE anyway
        return (E) elementData[(index < gapStart) ? index : (index + gapLength)];
    }

    /**
     * Replaces the element at the specified position in this list with
     * the specified element.
     *
     * @param index   index of element to replace.
     * @param element element to be stored at the specified position.
     * @return the element previously at the specified position.
     * @throws IndexOutOfBoundsException if index out of range
     *                                   (index < 0 || index >= size()).
     */
    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        // rangeCheck(index) not necessary - would fail with AIOOBE anyway
        if (index >= gapStart) {
            index += gapLength;
        }
        Object oldValue = elementData[index];
        elementData[index] = element;
        return (E) oldValue;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param e element to be appended to this list.
     * @return true (as per the general contract of Collection.add).
     */
    public boolean add(E e) {
        add(size(), e);
        return true;
    }

    /**
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     *
     * @param index   index at which the specified element is to be inserted.
     * @param element element to be inserted.
     * @throws IndexOutOfBoundsException if index is out of range
     *                                   (index < 0 || index > size()).
     */
    public void add(int index, E element) {
        int size = size();
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(
                "Index: " + index + ", Size: " + size); // NOI18N
        }

        ensureCapacity(size + 1);  // Increments modCount!!
        moveGap(index);

        elementData[gapStart++] = element;
        gapLength--;
    }

    /**
     * Appends all of the elements in the specified Collection to the end of
     * this list, in the order that they are returned by the
     * specified Collection's Iterator.  The behavior of this operation is
     * undefined if the specified Collection is modified while the operation
     * is in progress.  (This implies that the behavior of this call is
     * undefined if the specified Collection is this list, and this
     * list is nonempty.)
     *
     * @param c the elements to be inserted into this list.
     * @return true if this list changed as a result of the call.
     * @throws NullPointerException if the specified collection is null.
     */
    public boolean addAll(Collection c) {
        return addAll(size(), c);
    }

    /**
     * Inserts all of the elements in the specified Collection into this
     * list, starting at the specified position.  Shifts the element
     * currently at that position (if any) and any subsequent elements to
     * the right (increases their indices).  The new elements will appear
     * in the list in the order that they are returned by the
     * specified Collection's iterator.
     *
     * @param index index at which to insert first element
     *              from the specified collection.
     * @param c     elements to be inserted into this list.
     * @return true if this list changed as a result of the call.
     * @throws IndexOutOfBoundsException if index out of range (index
     *                                   < 0 || index > size()).
     * @throws NullPointerException      if the specified Collection is null.
     */
    public boolean addAll(int index, Collection c) {
        return addArray(index, c.toArray());
    }

    /*
     * Inserts all elements from the given array into this list, starting
     * at the given index.
     *
     * @param index index at which to insert first element from the array.
     * @param elements array of elements to insert.
     */
    public boolean addArray(int index, Object[] elements) {
        return addArray(index, elements, 0, elements.length);
    }

    /**
     * Inserts elements from the given array into this list, starting
     * at the given index.
     *
     * @param index    index at which to insert first element.
     * @param elements array of elements from which to insert elements.
     * @param off      offset in the elements pointing to first element to copy.
     * @param len      number of elements to copy from the elements array.
     */
    public boolean addArray(int index, Object[] elements, int off, int len) {
        int size = size();
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(
                "Index: " + index + ", Size: " + size); // NOI18N
        }

        ensureCapacity(size + len);  // Increments modCount

        moveGap(index);
        System.arraycopy(elements, off, elementData, gapStart, len);
        gapStart += len;
        gapLength -= len;

        return (len != 0);
    }

    /**
     * Removes all of the elements from this list.  The list will
     * be empty after this call returns.
     */
    public void clear() {
        removeRange(0, size());
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).
     *
     * @param index the index of the element to removed.
     * @return the element that was removed from the list.
     * @throws IndexOutOfBoundsException if index out of range (index
     *                                   < 0 || index >= size()).
     */
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        int size = size();
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException(
                "remove(): Index: " + index + ", Size: " + size); // NOI18N
        }

        modCount++;
        moveGap(index + 1); // if previous were adds() - this should be no-op
        Object oldValue = elementData[index];
        elementData[index] = null;
        gapStart--;
        gapLength++;

        return (E) oldValue;
    }

    /**
     * Removes elements at the given index.
     *
     * @param index index of the first element to be removed.
     * @param count number of elements to remove.
     */
    public void remove(int index, int count) {
        int toIndex = index + count;
        if (index < 0 || toIndex < index || toIndex > size()) {
            throw new IndexOutOfBoundsException("index=" + index // NOI18N
                + ", count=" + count + ", size()=" + size()); // NOI18N
        }
        removeRange(index, toIndex);
    }

    /**
     * Removes from this List all of the elements whose index is between
     * fromIndex, inclusive and toIndex, exclusive.  Shifts any succeeding
     * elements to the left (reduces their index).
     * This call shortens the list by (toIndex - fromIndex) elements.
     * (If toIndex==fromIndex, this operation has no effect.)
     *
     * @param fromIndex index of first element to be removed.
     * @param toIndex   index after last element to be removed.
     */
    protected void removeRange(int fromIndex, int toIndex) {
        modCount++;
        if (fromIndex == toIndex) {
            return;
        }

        int removeCount = toIndex - fromIndex;
        if (fromIndex >= gapStart) { // completely over gap
            // Move gap to the start of the removed area
            // (this should be the minimum necessary count of elements moved)
            moveGap(fromIndex);

            // Allow GC of removed items
            fromIndex += gapLength; // begining of abandoned area
            toIndex += gapLength;
            while (fromIndex < toIndex) {
                elementData[fromIndex] = null;
                fromIndex++;
            }
        } else { // completely below gap or spans the gap
            if (toIndex <= gapStart) {
                // Move gap to the end of the removed area
                // (this should be the minimum necessary count of elements moved)
                moveGap(toIndex);
                gapStart = fromIndex;
            } else { // spans gap: gapStart > fromIndex but gapStart - fromIndex < removeCount
                // Allow GC of removed items
                for (int clearIndex = fromIndex; clearIndex < gapStart; clearIndex++) {
                    elementData[clearIndex] = null;
                }

                fromIndex = gapStart + gapLength; // part above the gap
                gapStart = toIndex - removeCount; // original value of fromIndex
                toIndex += gapLength;
            }

            // Allow GC of removed items
            while (fromIndex < toIndex) {
                elementData[fromIndex++] = null;
            }
        }

        gapLength += removeCount;
    }

    private void moveGap(int index) {
        if (index == gapStart) {
            return; // do nothing
        }

        if (index < gapStart) { // move gap down
            int moveSize = gapStart - index;
            System.arraycopy(elementData, index, elementData,
                gapStart + gapLength - moveSize, moveSize);
            clearEmpty(index, Math.min(moveSize, gapLength));
            gapStart = index;
        } else { // above gap
            int gapEnd = gapStart + gapLength;
            int moveSize = index - gapStart;
            System.arraycopy(elementData, gapEnd, elementData, gapStart, moveSize);
            if (index < gapEnd) {
                clearEmpty(gapEnd, moveSize);
            } else {
                clearEmpty(index, gapLength);
            }
            gapStart += moveSize;
        }
    }

    private void copyAllData(Object[] toArray) {
        if (gapLength != 0) {
            int gapEnd = gapStart + gapLength;
            System.arraycopy(elementData, 0, toArray, 0, gapStart);
            System.arraycopy(elementData, gapEnd, toArray, gapStart,
                elementData.length - gapEnd);
        } else { // no gap => single copy of everything
            System.arraycopy(elementData, 0, toArray, 0, elementData.length);
        }
    }

    private void clearEmpty(int index, int length) {
        while (--length >= 0) {
            elementData[index++] = null; // allow GC
        }
    }

    /**
     * Save the state of the GapList instance to a stream (that
     * is, serialize it).
     *
     * @serialData The length of the array backing the GapList
     * instance is emitted (int), followed by all of its elements
     * (each an Object) in the proper order.
     */
    private void writeObject(java.io.ObjectOutputStream s)
        throws java.io.IOException {
        // Write out element count, and any hidden stuff
        s.defaultWriteObject();

        // Write out array length
        s.writeInt(elementData.length);

        // Write out all elements in the proper order.
        int i = 0;
        while (i < gapStart) {
            s.writeObject(elementData[i]);
            i++;
        }
        i += gapLength;
        int elementDataLength = elementData.length;
        while (i < elementDataLength) {
            s.writeObject(elementData[i]);
            i++;
        }
    }

    /**
     * Reconstitute the GapList instance from a stream (that is,
     * deserialize it).
     */
    private void readObject(java.io.ObjectInputStream s)
        throws java.io.IOException, ClassNotFoundException {
        // Read in size, and any hidden stuff
        s.defaultReadObject();

        // Read in array length and allocate array
        int arrayLength = s.readInt();
        elementData = new Object[arrayLength];

        // Read in all elements in the proper order.
        int i = 0;
        while (i < gapStart) {
            elementData[i] = s.readObject();
            i++;
        }
        i += gapLength;
        int elementDataLength = elementData.length;
        while (i < elementDataLength) {
            elementData[i] = s.readObject();
            i++;
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof List)) {
            return false;
        }

        final int expectedModCount = modCount;
        // ArrayList can be subclassed and given arbitrary behavior, but we can
        // still deal with the common case where o is ArrayList precisely
        boolean equal = equalsRange((List<?>) o, elementData.length - gapLength);
        checkForComodification(expectedModCount);
        return equal;
    }

    private boolean equalsRange(List<?> other, int to) {
        final Object[] es = elementData;
        if (to > es.length) {
            throw new ConcurrentModificationException();
        }
        Iterator<?> oit = other.iterator();
        for (int i = 0; i < to; i++) {
            if (!oit.hasNext() || !Objects.equals(es[i], oit.next())) {
                return false;
            }
        }
        return !oit.hasNext();
    }

    private void checkForComodification(final int expectedModCount) {
        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        int expectedModCount = modCount;
        int hash = hashCodeRange(elementData.length - gapLength);
        checkForComodification(expectedModCount);
        return hash;
    }

    private int hashCodeRange(int to) {
        final Object[] es = elementData;
        if (to > es.length) {
            throw new ConcurrentModificationException();
        }
        int hashCode = 1;
        for (int i = 0; i < to; i++) {
            Object e = es[i];
            hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
        }
        return hashCode;
    }
}
