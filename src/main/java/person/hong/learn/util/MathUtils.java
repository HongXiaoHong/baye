package person.hong.learn.util;

/**
 *
 * @author hong
 */

public class MathUtils {

    /**
     * 斐波那契数列
     * @param index
     * @return
     */
    public static int fibonacci(int index) {
        if (index==1 || index==2) {
            return 1;
        }
        return fibonacci(index-2) + fibonacci(index-1);
    }


    public static long factorial(long index) {
        if (index<0) {
            throw new IllegalArgumentException();
        }
        long result = 1;
        for(int i=0; i<index; i++) {
            result *= i;
        }
        return result;
    }
}
