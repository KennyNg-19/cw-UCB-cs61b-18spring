import com.sun.corba.se.spi.ior.ObjectKey;

public class ArrayDeque<T> { //任何引用类型的容器，都可以加！ 不光是collection！

    private T[] items; // for array of generic,  only way: Object first + casting later
    private int size;
    private int nextIndex;
    private int lastIndex;
    private final int FACTOR = 2;
    private static final int MIN_INITIAL_CAPACITY = 8;

    public ArrayDeque(){
        items = (T[]) new Object[MIN_INITIAL_CAPACITY];
        size = 0;
    }

    public int minusOne(int index){
        return index-1;
    }

    public int plusOne(int index){
        return index+1;
    }



    public void addFirst(T i){

    }

    /* 处理3个pointer， 注意箭头的方向！ */
    public void addLast(T i){

    }

    public T removeFirst(){

    }

    public T removeLast(){

    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    /* 从左往右 */
    public void printDeque(){

    }
    /* iteration 版*/
    public T get(int index){

    }

}
