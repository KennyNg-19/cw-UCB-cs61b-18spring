import java.util.Formatter;

/**
 * Scheme-like pairs that can be used to form a list of integers.
 *
 * @author P. N. Hilfinger, with some modifications by Josh Hug and melaniecebula
 * [Do not modify this file.]
 */
public class IntList {
    /**
     * First element of list.
     */
    private int first;
    /**
     * Remaining elements of list.
     */
    private IntList rest; // "pointer"

    /**
     * A List with first FIRST0 and rest REST0.
     */
    public IntList(int first0, IntList rest0) {
        first = first0;
        rest = rest0;
    }

    /**
     * A List with null rest, and first = 0.
     */
    public IntList() {
        /* NOTE: public IntList () { }  would also work. */
        this(0, null);
    }

    /**
     * Returns a list equal to L with all elements squared. Destructive.
     */
    public static void dSquareList(IntList L) {

        while (L != null) {
            L.first = L.first * L.first;
            L = L.rest;
        }
    }

    /**
     * Returns a list equal to L with all elements squared. Non-destructive.
     * 非递归写法
     */
    public static IntList squareListIterative(IntList L) {
        if (L == null) {
            return null;
        }

        // 1. build head part
        IntList res = new IntList(L.first * L.first, null);

        // 2. build rest part
        IntList ptr = res; // temp ptr, 也可以不要
        L = L.rest;
        while (L != null) {
            ptr.rest = new IntList(L.first * L.first, null);
            L = L.rest;
            ptr = ptr.rest;
        }
        return res;
    }

    /**
     * Returns a list equal to L with all elements squared. Non-destructive.
     * 递归写法
     */
    public static IntList squareListRecursive(IntList L) {
        if (L == null) {
            return null;
        }
        return new IntList(L.first * L.first, squareListRecursive(L.rest));
    }

    /** DO NOT MODIFY ANYTHING ABOVE THIS LINE! */


    /**
     * Returns a list consisting of the elements of A followed by the
     * *  elements of B.  May modify items of A. Don't use 'new'.
     */

    public static IntList dcatenate(IntList A, IntList B) {
        //TODO:  fill in method

        if (A == null){
            return B;
        }

        // get the end node, of which rest is NULL
        IntList A_reference = A;
        while (A_reference.rest != null) {
            // 因为这一步是在destruct A，所以不能直接用A 而是reference做指针
            A_reference = A_reference.rest;
        }
        A_reference.rest = B;
        return A;
    }


    /**
     * Returns a list consisting of the elements of A followed by the
     * * elements of B.  May NOT modify items of A.  Use 'new'.
     */
    public static IntList catenate(IntList A, IntList B) {
        //TODO:  fill in method
        if (A == null)
            return B;

        // A.first 是value 非引用....外层不断(A.first1, ( A.first2, (A.first3...
        // 直到递归到了base case, null也是value，非引用
        return new IntList(A.first, catenate(A.rest, B));
    }

    /**
     * Returns the reverse of the given IntList.
     * This method is destructive. If given null
     * as an input, returns null.
     */
    public static IntList reverse(IntList A) {
        if (A == null || A.rest == null) return A;

        // 递归解法: 很难理解....
        /*IntList reversed = reverse(A.rest);
        A.rest.rest = A;
        A.rest = null;
        return reversed;*/

        // iteration解法
        IntList reversed = null; // 已经reversed部分！
        IntList rest2reverse = A; // 如 1 -> 2 -> 3 -> null
        while (rest2reverse != null) {  // 1 -> 2 -> 3 -> null | 2 -> 3 -> null
            IntList restList = rest2reverse.rest; // 2 -> 3 -> null | 3 -> null


            /* 很难理解中间这2步
             * 因为一般的思想是，想着怎么用rest2reverse.first 在constructor里面！
             * 但这里，无需那么做*/

            // rest2reverse 这里是在 临时存储new reversed！！！
            // 取出自己的first 加到 已经reversed的 前面，完成一次reverse(字面上是，将rest 换成 已经reversed部分)
            rest2reverse.rest = reversed; // (reverse 是已经reversed的)
            reversed = rest2reverse; // 赋值new reversed

            rest2reverse = restList; // 2 -> 3 -> null | 3 -> null
        }
        return reversed;

    }


    /**
     * DO NOT MODIFY ANYTHING BELOW THIS LINE! Many of the concepts below here
     * will be introduced later in the course or feature some form of advanced
     * trickery which we implemented to help make your life a little easier for
     * the lab.
     */

    @Override
    public int hashCode() {
        return first;
    }

    /**
     * Returns a new IntList containing the ints in ARGS. You are not
     * expected to read or understand this method.
     */
    public static IntList of(Integer... args) {
        IntList result, p;

        if (args.length > 0) {
            result = new IntList(args[0], null);
        } else {
            return null;
        }

        int k;
        for (k = 1, p = result; k < args.length; k += 1, p = p.rest) {
            p.rest = new IntList(args[k], null);
        }
        return result;
    }

    /**
     * Returns true iff X is an IntList containing the same sequence of ints
     * as THIS. Cannot handle IntLists with cycles. You are not expected to
     * read or understand this method.
     */
    public boolean equals(Object x) {
        if (!(x instanceof IntList)) {
            return false;
        }
        IntList L = (IntList) x;
        IntList p;

        for (p = this; p != null && L != null; p = p.rest, L = L.rest) {
            if (p.first != L.first) {
                return false;
            }
        }
        if (p != null || L != null) {
            return false;
        }
        return true;
    }

    /**
     * If a cycle exists in the IntList, this method
     * returns an integer equal to the item number of the location where the
     * cycle is detected.
     * <p>
     * If there is no cycle, the number 0 is returned instead. This is a
     * utility method for lab2. You are not expected to read, understand, or
     * even use this method. The point of this method is so that if you convert
     * an IntList into a String and that IntList has a loop, your computer
     * don't get stuck in an infinite loop.
     */

    private int detectCycles(IntList A) {
        IntList tortoise = A;
        IntList hare = A;

        if (A == null)
            return 0;

        int cnt = 0;


        while (true) {
            cnt++;
            if (hare.rest != null)
                hare = hare.rest.rest;
            else
                return 0;

            tortoise = tortoise.rest;

            if (tortoise == null || hare == null)
                return 0;

            if (hare == tortoise)
                return cnt;
        }
    }

    @Override
    /** Outputs the IntList as a String. You are not expected to read
     * or understand this method. */
    public String toString() {
        Formatter out = new Formatter();
        String sep;
        sep = "(";
        int cycleLocation = detectCycles(this);
        int cnt = 0;

        for (IntList p = this; p != null; p = p.rest) {
            out.format("%s%d", sep, p.first);
            sep = ", ";

            cnt++;
            if ((cnt > cycleLocation) && (cycleLocation > 0)) {
                out.format("... (cycle exists) ...");
                break;
            }
        }
        out.format(")");
        return out.toString();
    }
}

