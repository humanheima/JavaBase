package com.hm.pattern.iterator.dinermergeri;

import java.util.Iterator;

public interface Menu {
    Iterator<MenuItem> createIterator();
}
