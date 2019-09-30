package hw3.hash;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;


public class SimpleOomage implements Oomage {
    private static final double WIDTH = 0.01;
    private static final boolean USE_PERFECT_HASH = true;
    protected int red;
    protected int green;
    protected int blue;

    public SimpleOomage(int r, int g, int b) {
        if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
            throw new IllegalArgumentException();
        }
        if ((r % 5 != 0) || (g % 5 != 0) || (b % 5 != 0)) {
            throw new IllegalArgumentException("red/green/blue values must all be multiples of 5!");
        }
        red = r;
        green = g;
        blue = b;
    }

    public static SimpleOomage randomSimpleOomage() {
        int red = StdRandom.uniform(0, 51) * 5;
        int green = StdRandom.uniform(0, 51) * 5;
        int blue = StdRandom.uniform(0, 51) * 5;
        return new SimpleOomage(red, green, blue);
    }

    public static void main(String[] args) {
        System.out.println("Drawing 4 random simple Oomages.");
        randomSimpleOomage().draw(0.25, 0.25, 1);
        randomSimpleOomage().draw(0.75, 0.75, 1);
        randomSimpleOomage().draw(0.25, 0.75, 1);
        randomSimpleOomage().draw(0.75, 0.25, 1);
    }

    @Override
    public boolean equals(Object o) {
        // TODO: Write this method.
        // 这也是.equals的底层源码实现！
        if (o == this) return true;
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;

        // type Object, not of type SimpleOomage, so you will need to do a cast
        /*(这里已经确认了是SimpleOomage类 + 且Java不允许casting使用this.getClass()：
         type checking发生在static，而.getClass只有runtime才执行！）*/
        SimpleOomage that = (SimpleOomage) o;
        return (this.red == that.red) && (this.green == that.green) && (this.blue == that.blue);
    }

    /* Java要求 hashcode和equals保持consistency： necessary to override the hashCode method
     * whenever the equals method is overridden, so as to maintain the general contract
     * for the hashCode method, which states that equal objects must have equal hash codes.”*/
    @Override
    public int hashCode() {
        if (!USE_PERFECT_HASH) {
            return red + green + blue;
        } else {
            // TODO: Write a perfect hash function for Simple Oomages.

            /* make your hashCodes work well for any number of buckets,
             * you should ensure that it is not always a multiple of any number.
             * 但这里因为3属性都是5的倍数，所以
             *  */
            return red / 5 * (int) Math.pow(31, 2) + green / 5 * 31 + blue / 5;
        }
    }

    @Override
    public void draw(double x, double y, double scalingFactor) {
        StdDraw.setPenColor(new Color(red, green, blue));
        StdDraw.filledSquare(x, y, WIDTH * scalingFactor);
    }

    public String toString() {
        return "R: " + red + ", G: " + green + ", B: " + blue;
    }
} 
