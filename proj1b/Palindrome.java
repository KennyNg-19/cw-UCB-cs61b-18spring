

public class Palindrome {

    /*
    * Given a String, wordToDeque should return a Deque where the characters appear
    * in the same order as in the String. For example, if the word is “persiflage”,
    * then the returned Deque should have ‘p’ at the front, followed by ‘e’
    * */
    public Deque<Character> wordToDeque(String word){

        Deque<Character> d = new LinkedListDeque<>();

        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    // private helper method for isPalindrome to work.
    private String dequeToWord(Deque<Character> deque){
        String s = "";

        for(Character c: deque){ // 需要让interface实现iterable<T>
            s += c;
        }

        return s;
    }

    public boolean isPalindrome(String word){

        /* 先判定，是不是odd长/null！*/
        if(word == null || word.length() == 1 || word.length() == 0) return true;

        Deque<Character> d = wordToDeque(word);
        /* recerusion */
        if(d.removeFirst() == d.removeLast()){ // auto-unboxing to char
            return isPalindrome(dequeToWord(d));
        }
        return false;
    }


    /* overload: */
    public boolean isPalindrome(String word, CharacterComparator cc){

        /* 先判定，是不是odd长*/
        if(word == null || word.length() == 1 || word.length() == 0) return true;

        cc = new OffByOne();
        Deque<Character> d = wordToDeque(word);
        /* recerusion */
        if(cc.equalChars(d.removeFirst(), d.removeLast())){ // auto-unboxing to char
            return isPalindrome(dequeToWord(d), cc);
        }

        return false;
    }

}
