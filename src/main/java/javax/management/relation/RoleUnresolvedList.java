/*
 * Copyright (c) 2000, 2008, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package javax.management.relation;

import com.sun.jmx.mbeanserver.Util;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A RoleUnresolvedList represents a list of RoleUnresolved objects,
 * representing roles not retrieved from a relation due to a problem
 * encountered when trying to access (read or write) the roles.
 *
 * @since 1.5
 */
/* We cannot extend ArrayListTest<RoleUnresolved> because our legacy
   add(RoleUnresolved) method would then override add(E) in ArrayListTest<E>,
   and our return value is void whereas ArrayListTest.add(E)'s is boolean.
   Likewise for set(int,RoleUnresolved).  Grrr.  We cannot use covariance
   to override the most important methods and have them return
   RoleUnresolved, either, because that would break subclasses that
   override those methods in turn (using the original return type
   of Object).  Finally, we cannot implement Iterable<RoleUnresolved>
   so you could write
       for (RoleUnresolved r : roleUnresolvedList)
   because ArrayListTest<> implements Iterable<> and the same class cannot
   implement two versions of a generic interface.  Instead we provide
   the asList() method so you can write
       for (RoleUnresolved r : roleUnresolvedList.asList())
*/
public class RoleUnresolvedList extends ArrayList<Object> {

    private transient boolean typeSafe;
    private transient boolean tainted;

    /* Serial version */
    private static final long serialVersionUID = 4054902803091433324L;

    //
    // Constructors
    //

    /**
     * Constructs an empty RoleUnresolvedList.
     */
    public RoleUnresolvedList() {
        super();
    }

    /**
     * Constructs an empty RoleUnresolvedList with the initial capacity
     * specified.
     *
     * @param initialCapacity  initial capacity
     */
    public RoleUnresolvedList(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Constructs a {@code RoleUnresolvedList} containing the elements of the
     * {@code List} specified, in the order in which they are returned by
     * the {@code List}'s iterator. The {@code RoleUnresolvedList} instance has
     * an initial capacity of 110% of the size of the {@code List}
     * specified.
     *
     * @param list the {@code List} that defines the initial contents of
     * the new {@code RoleUnresolvedList}.
     *
     * @exception IllegalArgumentException if the {@code list} parameter
     * is {@code null} or if the {@code list} parameter contains any
     * non-RoleUnresolved objects.
     *
     * @see ArrayListTest#ArrayList(java.util.Collection)
     */
    public RoleUnresolvedList(List<RoleUnresolved> list)
        throws IllegalArgumentException {
        // Check for null parameter
        //
        if (list == null)
            throw new IllegalArgumentException("Null parameter");

        // Check for non-RoleUnresolved objects
        //
        checkTypeSafe(list);

        // Build the List<RoleUnresolved>
        //
        super.addAll(list);
    }

    /**
     * Return a view of this list as a {@code List<RoleUnresolved>}.
     * Changes to the returned value are reflected by changes
     * to the original {@code RoleUnresolvedList} and vice versa.
     *
     * @return a {@code List<RoleUnresolved>} whose contents
     * reflect the contents of this {@code RoleUnresolvedList}.
     *
     * <p>If this method has ever been called on a given
     * {@code RoleUnresolvedList} instance, a subsequent attempt to add
     * an object to that instance which is not a {@code RoleUnresolved}
     * will fail with an {@code IllegalArgumentException}. For compatibility
     * reasons, a {@code RoleUnresolvedList} on which this method has never
     * been called does allow objects other than {@code RoleUnresolved}s to
     * be added.</p>
     *
     * @throws IllegalArgumentException if this {@code RoleUnresolvedList}
     * contains an element that is not a {@code RoleUnresolved}.
     *
     * @since 1.6
     */
    @SuppressWarnings("unchecked")
    public List<RoleUnresolved> asList() {
        if (!typeSafe) {
            if (tainted)
                checkTypeSafe(this);
            typeSafe = true;
        }
        return Util.cast(this);
    }

    //
    // Accessors
    //

    /**
     * Adds the RoleUnresolved specified as the last element of the list.
     *
     * @param role - the unresolved role to be added.
     *
     * @exception IllegalArgumentException  if the unresolved role is null.
     */
    public void add(RoleUnresolved role)
        throws IllegalArgumentException {

        if (role == null) {
            String excMsg = "Invalid parameter";
            throw new IllegalArgumentException(excMsg);
        }
        super.add(role);
    }

    /**
     * Inserts the unresolved role specified as an element at the position
     * specified.
     * Elements with an index greater than or equal to the current position are
     * shifted up.
     *
     * @param index - The position in the list where the new
     * RoleUnresolved object is to be inserted.
     * @param role - The RoleUnresolved object to be inserted.
     *
     * @exception IllegalArgumentException  if the unresolved role is null.
     * @exception IndexOutOfBoundsException if index is out of range
     * (<code>index &lt; 0 || index &gt; size()</code>).
     */
    public void add(int index,
                    RoleUnresolved role)
        throws IllegalArgumentException,
               IndexOutOfBoundsException {

        if (role == null) {
            String excMsg = "Invalid parameter";
            throw new IllegalArgumentException(excMsg);
        }

        super.add(index, role);
    }

    /**
     * Sets the element at the position specified to be the unresolved role
     * specified.
     * The previous element at that position is discarded.
     *
     * @param index - The position specified.
     * @param role - The value to which the unresolved role element
     * should be set.
     *
     * @exception IllegalArgumentException   if the unresolved role is null.
     * @exception IndexOutOfBoundsException if index is out of range
     * (<code>index &lt; 0 || index &gt;= size()</code>).
     */
     public void set(int index,
                     RoleUnresolved role)
         throws IllegalArgumentException,
                IndexOutOfBoundsException {

        if (role == null) {
            String excMsg = "Invalid parameter";
            throw new IllegalArgumentException(excMsg);
        }

        super.set(index, role);
     }

    /**
     * Appends all the elements in the RoleUnresolvedList specified to the end
     * of the list, in the order in which they are returned by the Iterator of
     * the RoleUnresolvedList specified.
     *
     * @param roleList - Elements to be inserted into the list
     * (can be null).
     *
     * @return true if this list changed as a result of the call.
     *
     * @exception IndexOutOfBoundsException  if accessing with an index
     * outside of the list.
     */
    public boolean addAll(RoleUnresolvedList roleList)
        throws IndexOutOfBoundsException {

        if (roleList == null) {
            return true;
        }

        return (super.addAll(roleList));
    }

    /**
     * Inserts all of the elements in the RoleUnresolvedList specified into
     * this list, starting at the specified position, in the order in which
     * they are returned by the Iterator of the RoleUnresolvedList specified.
     *
     * @param index - Position at which to insert the first element from the
     * RoleUnresolvedList specified.
     * @param roleList - Elements to be inserted into the list.
     *
     * @return true if this list changed as a result of the call.
     *
     * @exception IllegalArgumentException  if the role is null.
     * @exception IndexOutOfBoundsException if index is out of range
     * (<code>index &lt; 0 || index &gt; size()</code>).
     */
    public boolean addAll(int index,
                          RoleUnresolvedList roleList)
        throws IllegalArgumentException,
               IndexOutOfBoundsException {

        if (roleList == null) {
            String excMsg = "Invalid parameter";
            throw new IllegalArgumentException(excMsg);
        }

        return (super.addAll(index, roleList));
    }

    /*
     * Override all of the methods from ArrayListTest<Object> that might add
     * a non-RoleUnresolved to the List, and disallow that if asList has
     * ever been called on this instance.
     */

    @Override
    public boolean add(Object o) {
        if (!tainted)
            tainted = isTainted(o);
        if (typeSafe)
            checkTypeSafe(o);
        return super.add(o);
    }

    @Override
    public void add(int index, Object element) {
        if (!tainted)
            tainted = isTainted(element);
        if (typeSafe)
            checkTypeSafe(element);
        super.add(index, element);
    }

    @Override
    public boolean addAll(Collection<?> c) {
        if (!tainted)
            tainted = isTainted(c);
        if (typeSafe)
            checkTypeSafe(c);
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<?> c) {
        if (!tainted)
            tainted = isTainted(c);
        if (typeSafe)
            checkTypeSafe(c);
        return super.addAll(index, c);
    }

    @Override
    public Object set(int index, Object element) {
        if (!tainted)
            tainted = isTainted(element);
        if (typeSafe)
            checkTypeSafe(element);
        return super.set(index, element);
    }

    /**
     * IllegalArgumentException if o is a non-RoleUnresolved object.
     */
    private static void checkTypeSafe(Object o) {
        try {
            o = (RoleUnresolved) o;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * IllegalArgumentException if c contains any non-RoleUnresolved objects.
     */
    private static void checkTypeSafe(Collection<?> c) {
        try {
            RoleUnresolved r;
            for (Object o : c)
                r = (RoleUnresolved) o;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Returns true if o is a non-RoleUnresolved object.
     */
    private static boolean isTainted(Object o) {
        try {
            checkTypeSafe(o);
        } catch (IllegalArgumentException e) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if c contains any non-RoleUnresolved objects.
     */
    private static boolean isTainted(Collection<?> c) {
        try {
            checkTypeSafe(c);
        } catch (IllegalArgumentException e) {
            return true;
        }
        return false;
    }
}