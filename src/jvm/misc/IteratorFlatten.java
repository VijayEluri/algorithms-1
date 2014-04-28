package misc;

import java.util.Iterator;
import java.util.LinkedList;

class FlatIterator implements Iterator {
    Iterator curI;
    LinkedList<Iterator> stack;

    public FlatIterator(Iterator i) {
        curI = i;
        stack = new LinkedList<Iterator>();
    }

    @Override
    public boolean hasNext() {
        if (curI.hasNext()) {
            return true;
        }
        if (!stack.isEmpty()) {
            curI = stack.pop();
            return hasNext();
        } else {
            return false;
        }

    }

    @Override
    public Object next() {
        final Object next = curI.next();
        if (next instanceof Iterator) {
            stack.push(curI);
            curI = (Iterator) next;
            return next();
        } else {
            return next;
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not required");
    }
}
