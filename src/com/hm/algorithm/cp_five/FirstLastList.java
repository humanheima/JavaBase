package com.hm.algorithm.cp_five;

// firstLastList.java
// demonstrates list with first and last references
// to run this program: C>java FirstLastApp
////////////////////////////////////////////////////////////////
class LinkFirstLast {
    public long dData;                 // data item
    public LinkFirstLast next;                  // next link in list

    // -------------------------------------------------------------
    public LinkFirstLast(long d)                // constructor
    {
        dData = d;
    }

    // -------------------------------------------------------------
    public void displayLink()          // display this link
    {
        System.out.print(dData + " ");
    }
// -------------------------------------------------------------
}  // end class Link

////////////////////////////////////////////////////////////////
class FirstLastList {
    private LinkFirstLast first;               // ref to first link
    private LinkFirstLast last;                // ref to last link

    // -------------------------------------------------------------
    public FirstLastList()            // constructor
    {
        first = null;                  // no links on list yet
        last = null;
    }

    // -------------------------------------------------------------
    public boolean isEmpty()          // true if no links
    {
        return first == null;
    }

    // -------------------------------------------------------------
    public void insertFirst(long dd)  // insert at front of list
    {
        LinkFirstLast newLink = new LinkFirstLast(dd);   // make new link

        if (isEmpty()) {
            // if empty list,
            last = newLink;             // newLink <-- last
        }
        newLink.next = first;          // newLink --> old first
        first = newLink;               // first --> newLink
    }

    // -------------------------------------------------------------
    public void insertLast(long dd)   // insert at end of list
    {
        LinkFirstLast newLink = new LinkFirstLast(dd);   // make new link
        if (isEmpty())                // if empty list,
            first = newLink;            // first --> newLink
        else
            last.next = newLink;        // old last --> newLink
        last = newLink;                // newLink <-- last
    }

    // -------------------------------------------------------------
    public long deleteFirst()         // delete first link
    {                              // (assumes non-empty list)
        long temp = first.dData;
        if (first.next == null)         // if only one item
            last = null;                // null <-- last
        first = first.next;            // first --> old next
        return temp;
    }

    // -------------------------------------------------------------
    public void displayList() {
        System.out.print("List (first-->last): ");
        LinkFirstLast current = first;          // start at beginning
        while (current != null)         // until end of list,
        {
            current.displayLink();      // print data
            current = current.next;     // move to next link
        }
        System.out.println("");
    }
// -------------------------------------------------------------
}  // end class FirstLastList

////////////////////////////////////////////////////////////////
class FirstLastApp {
    public static void main(String[] args) {                              // make a new list
        FirstLastList theList = new FirstLastList();

        theList.insertFirst(22);       // insert at front
        theList.insertFirst(44);
        theList.insertFirst(66);

        theList.insertLast(11);        // insert at rear
        theList.insertLast(33);
        theList.insertLast(55);

        theList.displayList();         // display the list

        theList.deleteFirst();         // delete first two items
        theList.deleteFirst();

        theList.displayList();         // display again
    }  // end main()
}  // end class FirstLastApp
////////////////////////////////////////////////////////////////
