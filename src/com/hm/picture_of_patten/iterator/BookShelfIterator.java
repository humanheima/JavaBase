package com.hm.picture_of_patten.iterator;

/**
 * Created by dumingwei on 2022/3/21.
 * <p>
 * Desc: 为什么要有迭代器这个类呢？直接在 BookShelf 里面提供便利能力不就行了吗？
 * 自己的理解：这样做到职责分离。更通用，如果从前向后遍历的功能写在 BookShelf 里面，
 * 那么我有需求需要从后向前遍历怎么办？我还得修改 BookShelf 类。 但是这种通过迭代器将将遍历的功能抽象出来
 * 就可以不需要改动 BookShelf 类 ，定义一个迭代器从后向前遍历的就可以了。
 *
 * 对修改关闭，对扩展开放。
 */
public class BookShelfIterator implements Iterator {


    private BookShelf bookShelf;
    private int index;

    public BookShelfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        if (index < bookShelf.getLength()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object next() {
        Book book = bookShelf.getBookAt(index);
        index++;
        return book;
    }

}
