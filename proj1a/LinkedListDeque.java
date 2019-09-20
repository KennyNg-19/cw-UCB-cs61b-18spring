public class LinkedListDeque<T> {

    private class Node{
        public T item;
        /*从左往右看！*/
        public Node right;
        public Node left;

        public Node(T i, Node r, Node l){
            item = i;
            right = r;
            left = l;
        }
    }

    private Node sentFront; // 相当于 0th，
    private Node sentBack;
    private int size;

    /* empty list，因为T 泛型，默认值就是null, 像intNode.item默认值是int*/
    public LinkedListDeque(){
        sentFront = new Node(null, null, null);
        sentBack = new Node(null, null, null);
        size = 0;
    }

    /* 无所谓item是什么 */
//    public LinkedListDeque(T item){
//        sentFront = new Node(item, null);
//        sentBack = new Node(item, null);
//        size = 0;
//    }

    public void addFirst(T i){

        if(size == 0){
            Node newHead = new Node(i, null, null);
            //初始时，指向同一个
            sentFront.right = newHead;
            sentBack.left = newHead;
        }else {
            // 1. newHead --> oldHead
            Node oldHead = sentFront.right;
            Node newHead = new Node(i, oldHead, null);

            // 2. sentback --> newHead
            sentFront.right = newHead;

            //or 3. newHead <-- oldHead
            oldHead.left = newHead;
        }
        size += 1;
    }

    /* 处理3个pointer， 注意箭头的方向！ */
    public void addLast(T i){
        if(size == 0) {
        // No pointer
        Node newHead = new Node(i, null, null);
        //初始时，指向同一个
        sentFront.right = newHead;
        sentBack.left = newHead;
        }else{
        // 1. oldLast <-- newLast
        Node oldLast = sentBack.left;
        Node newLast = new Node(i, null, oldLast);

        // 2. newLast <-- sentback
        sentBack.left = newLast;

        //3. oldLast --> newLast
        oldLast.right = newLast;
        }
        size += 1;
    }

    public T removeFirst(){
        T item;
        if(size == 0){
            return null;
        }else if(size == 1){
            Node head = sentFront.right; // 同时也是 sentBack.left
            item = head.item;
            sentFront.right = null;
            sentBack.left = null;
            size -= 1;
        }else{
            /* size >= 2 */
            /* get the 2nd and 1st), 要return！*/
            Node head = sentFront.right;
            /* 需要，虽然head node2无需指向sent node，但他指向了head,也要移除*/
            Node newHead = sentFront.right.right;

            /*先记下 head.item*/
            item = head.item;

            /* 消灭俩pointer，才能消灭head*/
            newHead.left = null; // newHead无需指向sent node
            sentFront.right = newHead; // remove reference

            size -= 1;
            /* if it is the only one, AFTER removal, size原来是2 */
            if (size == 1) sentBack.left = newHead;

        }
        return item;
    }

    public T removeLast(){
        T item;
        if(size == 0){
            return null;
        }else if(size == 1){
            Node head = sentBack.left; // 同时也是 sentFront.right
            item = head.item;
            sentFront.right = null;
            sentBack.left = null;
            size -= 1;
        }else {
            /* get the 2nd and 1st), 要return！*/
            Node last = sentBack.left;
            /* 需要，虽然head node2无需指向sent node，但他指向了head,也要移除*/
            Node newLast = sentBack.left.left;

            /*先记下 head.item*/
            item = last.item;

            /* 消灭俩pointer，才能消灭head*/
            newLast.right = null; // newLast无需指向sent node
            sentBack.left = newLast; // remove reference

            size -= 1;
            /* if AFTER removal, it is the only one: */
            if (size == 0) sentFront.right = newLast;
        }
        return item;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    /* 从左往右 */
    public void printDeque(){
        if(size == 0){
            return;
        }
        /* 不打乱原引用，新建指针*/
        Node head = sentFront.right;
        String s = "";

        /* 当right is null，则到底了*/
        while (head != null){
            s += head.item + " ";
            head = head.right;
        }
        System.out.println(s);
    }
        /* iteration 版*/
    public T get(int index){
        if(index >= size()) return null;

        Node node = sentFront.right;
        while (index > 0){
            node = node.right;
            index--;
        }
        return node.item;
    }

    /*Same as get, but uses recursion.
     * Tricky!!! 直接作用在LinkedList上，得用remove截取list了？？？
     * 不行！ 这样会修改this list*/
//    public T getRecursive(int index){
//        if(index >= size()) return null;
//        else if(index == 0){
//            /* index < size, 至少余下一个*/
//            return sentFront.right.item; // sentBack.left这里一样
//        }
//        /* list 从头开始 slicing */
//        removeFirst();
//        return getRecursive(index - 1);
//    }

    /* 看来是要额外的参数了，单index不太行！
    * 可以额外增加helper function！，然后用getRecursive"调用"它
    * 就像那个sorting 递归实现一样，主函数来调用递归的部分！*/

    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }
        return  helper(sentFront.right, index);
    }

    /* helper function for getRecursive method */
    private T helper(Node node, int index) {
        if (index == 0) {
            return node.item;
        } else {
            return helper(node.right, index - 1);
        }
    }

}
