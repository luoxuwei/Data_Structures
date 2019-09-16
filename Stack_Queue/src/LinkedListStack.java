public class LinkedListStack<E> implements IStack<E> {
    private LinkedList<E> linkedList;

    public LinkedListStack() {
        linkedList = new LinkedList<>();
    }

    @Override
    public void push(E e) {
        linkedList.addFirst(e);
    }

    @Override
    public E pop() {
        return linkedList.removeFirst();
    }

    @Override
    public E peek() {
        return linkedList.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack [ ");
        res.append(linkedList);
        res.append("]");
        return res.toString();
    }

    public static void main(String[] args) {
        // write your code here
        LinkedListStack<Integer> stack = new LinkedListStack<>();

        for(int i = 0 ; i < 10 ; i ++){
            stack.push(i);
            System.out.println(stack);
            if (i%3==2) {
                stack.pop();
                System.out.println(stack);
            }
        }

    }
}
