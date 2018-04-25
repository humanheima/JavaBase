// Set obeserver callback interface - Page 266
package com.hm.effective_java.chapter_ten;

public interface SetObserver<E> {
	// Invoked when an element is added to the observable set
	void added(ObservableSet<E> set, E element);
}
