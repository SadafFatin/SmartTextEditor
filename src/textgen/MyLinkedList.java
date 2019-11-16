package textgen;

import javax.xml.bind.Element;
import java.util.AbstractList;
import java.util.ArrayList;


/**
 * A class that implements a doubly linked list
 *
 * @param <E> The type of the elements stored in the list
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MyLinkedList<E> extends AbstractList<E> {
    LLNode<E> head;
    LLNode<E> tail;
    int size;

    /**
     * Create a new empty LinkedList
     */
    public MyLinkedList() {
        // TODO: Implement this method

        this.head = new LLNode<>(null);
        this.tail = new LLNode<>(null);
        this.head.prev = null;
        this.tail.next = null;
        this.head.next = this.tail;
        this.tail.prev = this.head;

    }

    /**
     * Appends an element to the end of the list
     *
     * @param element The element to add
     */
    public boolean add(E element) {
        // TODO: Implement this method

        if (element == null) {
            throw new NullPointerException();
        }

        LLNode<E> newNode = new LLNode<>(element);
        LLNode<E> currentNode = this.head;

        while (currentNode.next != this.tail) {
            currentNode = currentNode.next;
        }

        currentNode.next = newNode;
        newNode.prev = currentNode;
        newNode.next = this.tail;
        this.tail.prev = newNode;

        this.size++;
        return true;
    }

    /**
     * Get the element at position index
     *
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public E get(int index) {
        // TODO: Implement this method.
        if (size() <= 0 || index >= size()) {
            throwIndexOutOfBound();
            return null;
        } else {
            LLNode<E> currentNode = this.head.next;
            int i = 0;
            while (currentNode != this.tail) {

                if (i == index) {
                    return currentNode.data;
                }
                currentNode = currentNode.next;
                i++;
            }
            throwIndexOutOfBound();
            return null;
        }

    }

    private void throwIndexOutOfBound() {
        ArrayList emptyList = new ArrayList();
        emptyList.get(1);
    }

    /**
     * Add an element to the list at the specified index
     *
     * @param index   where the element should be added
     * @param element The element to add
     */
    public void add(int index, E element) {
        // TODO: Implement this method
        if (index == this.size()) {
            add(element);
            return;
        } else if (index < 0 || index > this.size() ) {
            throwIndexOutOfBound();
        } else if (element == null) {
            throw new NullPointerException();
        } else {
            LLNode<E> newNode = new LLNode<>(element);
            LLNode<E> currentNode = this.head.next;
            int i = 0;
            while (currentNode != this.tail) {

                if (i == index) {
                    newNode.next = currentNode;
                    newNode.prev = currentNode.prev;
                    currentNode.prev.next = newNode;
                    currentNode.prev = newNode;
                    size++;
                    return;
                }
                currentNode = currentNode.next;
                i++;
            }

        }
    }


    /**
     * Return the size of the list
     */
    public int size() {
        // TODO: Implement this method
		/*LLNode<E> currentNode = this.head.next;
		size = 0;
		while(currentNode!=this.tail){
			size++;
			currentNode = currentNode.next;
		}*/

        return size;
    }

    /**
     * Remove a node at the specified index and return its data element.
     *
     * @param index The index of the element to remove
     * @return The data element removed
     * @throws IndexOutOfBoundsException If index is outside the bounds of the list
     */
    public E remove(int index) {
        // TODO: Implement this method.
        if (size() <= 0 || index >= size() || index<0) {
            throwIndexOutOfBound();
            return null;
        } else {
            LLNode<E> currentNode = this.head.next;
            int i = 0;
            while (currentNode != this.tail) {

                if (i == index) {
                    LLNode<E> prevNode = currentNode.prev;
                    LLNode<E> nextNode = currentNode.next;
                    prevNode.next = nextNode;
                    nextNode.prev = prevNode;
                    currentNode.prev = null;
                    currentNode.next = null;
                    size--;
                    return currentNode.data;
                }
                currentNode = currentNode.next;
                i++;
            }
            throwIndexOutOfBound();
            return null;
        }

    }

    /**
     * Set an index position in the list to a new element
     *
     * @param index   The index of the element to change
     * @param element The new element
     * @return The element that was replaced
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public E set(int index, E element) {
        // TODO: Implement this method

        if (size() <= 0 || index >= size()  || index<0) {
            throwIndexOutOfBound();
            return null;
        } else if (element == null) {
            throw new NullPointerException();
        } else {
            LLNode<E> currentNode = this.head.next;
            int i = 0;
            while (currentNode != this.tail) {
                if (i == index) {
                    E temp = currentNode.data;
                    currentNode.data = element;
                    return temp;
                }
                currentNode = currentNode.next;
                i++;
            }
        }
        return null;
    }
}

class LLNode<E> {
    LLNode<E> prev;
    LLNode<E> next;
    E data;

    // TODO: Add any other methods you think are useful here
    // E.g. you might want to add another constructor

    public LLNode(E e) {
        this.data = e;
        this.prev = null;
        this.next = null;
    }

}
