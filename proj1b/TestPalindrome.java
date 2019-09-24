import org.junit.Test;

import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }


    @Test
    public void testPalindrome() {
        assertTrue(palindrome.isPalindrome(null));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("aba"));
        assertTrue(palindrome.isPalindrome("abba"));
        assertTrue(palindrome.isPalindrome("AbA"));

        assertFalse(palindrome.isPalindrome("abA"));
        assertFalse(palindrome.isPalindrome("abbA"));
    }

    @Test
    public void testIsPalindromeOffByOne() {
        OffByOne offByOne = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", offByOne));
        assertTrue(palindrome.isPalindrome("645", offByOne));
        assertTrue(palindrome.isPalindrome(null, offByOne));
        assertFalse(palindrome.isPalindrome("2222", offByOne));
    }

    @Test
    public void testIsPalindromeOffByN() {

        assertTrue(palindrome.isPalindrome("binding", new OffByN(5)));
        assertTrue(palindrome.isPalindrome("acdb", new OffByN(1)));
        assertTrue(palindrome.isPalindrome(null, new OffByN(0)));
        assertTrue(palindrome.isPalindrome("", new OffByN(0)));

    }


}
