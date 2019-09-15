public class Main {

    public static void main(String[] args) {
	// write your code here
        Array arr = new Array();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        arr.add(5);
        arr.add(6);
        arr.add(7);
        arr.add(8);
        arr.add(9);
        arr.add(10);
        System.out.println(arr);
        arr.add(11);
        System.out.println(arr);
        arr.removeFirst();
        arr.removeFirst();
        arr.removeFirst();
        System.out.println(arr);
        arr.removeFirst();
        arr.removeFirst();
        arr.removeFirst();
        arr.removeFirst();
        System.out.println(arr);
    }
}
