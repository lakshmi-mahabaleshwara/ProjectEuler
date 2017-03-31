package org.projecteuler;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lmahabaleshwara on 3/28/17.
 */
public class LychrelNumbers {

    /**
     * ********************* Lychrel Numbers -  Problem 55 ***********************
     * If we take 47, reverse and add, 47 + 74 = 121, which is palindromic.

     Not all numbers produce palindromes so quickly. For example,

     349 + 943 = 1292,
     1292 + 2921 = 4213
     4213 + 3124 = 7337

     That is, 349 took three iterations to arrive at a palindrome.

     Although no one has proved it yet, it is thought that some numbers, like 196, never produce a palindrome.
     A number that never forms a palindrome through the reverse and add process is called a Lychrel number.
     Due to the theoretical nature of these numbers, and for the purpose of this problem,
     we shall assume that a number is Lychrel until proven otherwise. In addition you are given that for every number
     below ten-thousand, it will either (i) become a palindrome in less than fifty iterations, or, (ii) no one,
     with all the computing power that exists, has managed so far to map it to a palindrome. In fact, 10677 is the
     first number to be shown to require over fifty iterations before producing a palindrome: 4668731596684224866951378664
     (53 iterations, 28-digits).

     Surprisingly, there are palindromic numbers that are themselves Lychrel numbers; the first example is 4994.

     How many Lychrel numbers are there below ten-thousand?
     */

    /**
     * *********References : **********************
     * http://mathworld.wolfram.com/196-Algorithm.html
     * https://en.wikipedia.org/wiki/Lychrel_number
     */

    // Initialization of Given Data
    private static final int MAX_NUMBER = 10000;
    private static final int MAX_ITERATION = 50;

    /** Computes the Lychrel Number
     * As per Wikipedia -  About 80% of all numbers under 10,000 resolve into a palindrome in four or fewer steps.
     * About 90% resolve in seven steps or fewer.*/

    /****************************** SOLUTION - 1 USING BRUTE FORCE ****************************************/

    private static void computeLychrelNumber(){

        List<BigInteger> lychrelNumbersList = new ArrayList<BigInteger>();
        long elapsedTime = 0l;
        long startTime = System.currentTimeMillis();
        long endTime = 0l;

        // Starting iteration from 10, since 0 to 9 will be Palindrome by default since its single digit
        for(int i=10; i <= MAX_NUMBER; i++){
            BigInteger value = BigInteger.valueOf(i);
            for(int j=1; j <= MAX_ITERATION; j++){
                value = value.add((reverse(value)));
                if(isPalindrome(value)){
                    break;
                }
                if(j == MAX_ITERATION){
                  lychrelNumbersList.add(BigInteger.valueOf(i));
                }
            }
        }
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;

        System.out.println("******************** SOLUTION 1 BY BRUTE FORCE ***************************");
        System.out.println("Time taken to Compute Lychrel Numbers : " + elapsedTime+"ms");
        System.out.println("Lychrel Numbers size : " + lychrelNumbersList.size());
        System.out.print("Lychrel Numbers are : ");
        for(int i=0; i < lychrelNumbersList.size(); i++){
            System.out.print(lychrelNumbersList.get(i) + " ");
        }
    }

    /****************************** SOLUTION - 2 USING HASH MAP ****************************************/

    private static void computeLychrelNumberByHashMap(){
        Map<BigInteger,Boolean> map = new HashMap<BigInteger, Boolean>();
        List<BigInteger> lychrelNumbersList = new ArrayList<BigInteger>();
        long elapsedTime = 0l;
        long startTime = System.currentTimeMillis();
        long endTime = 0l;

        // Starting iteration from 10, since 0 to 9 will be Palindrome by default since its single digit
        for(int i=10; i <= MAX_NUMBER; i++){
            BigInteger value = BigInteger.valueOf(i);
            /**
             * Lets take the example of number 122, flow would be
             * 122 + 221 = 343
             * So when the iteration number (i) comes 221, it will be
             * 221 + 122 = 343 --> which is similar to above procedure.
             * Here if the number is already computed, then store the result in map.
             * Before going to j = 1, if number is already computed, then we know the result is stored in map
             * and skip going through iteration again.
             * This solution reduces the time at the end of iteration by half from Solution - 1.
             */
            if(!map.containsKey(value)){
                for(int j=1; j <= MAX_ITERATION; j++){
                    BigInteger temp = value;
                    value = value.add((reverse(value)));
                    if(isPalindrome(value)){
                        map.put(temp,true);
                        break;
                    }
                    if(j == MAX_ITERATION){
                        lychrelNumbersList.add(BigInteger.valueOf(i));
                    }
                }
            }
        }
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;

        System.out.println("\n\n******************** SOLUTION 2 BY HASH MAP ***************************");
        System.out.println("Time taken to Compute Lychrel Numbers By map Solution : " + elapsedTime+"ms");
        System.out.println("Lychrel Numbers size By map Solution: " + lychrelNumbersList.size());
        System.out.print("Lychrel Numbers are By map Solution : ");
        for(int i=0; i < lychrelNumbersList.size(); i++){
            System.out.print(lychrelNumbersList.get(i) + " ");
        }

    }

    /**
     * Computes the reverse of a given number
     * @param num
     * @return Reversed Number
     */
    private static BigInteger reverse(BigInteger num) {
        StringBuilder sb = new StringBuilder(num.toString()).reverse();
        return new BigInteger(sb.toString());
    }

    /**
     * Computes if the number is Palindrome
     * @param num
     * @return <true> if given number is Palindrome / <false> If it is not Palindrome.
     */
    private static boolean isPalindrome(BigInteger num) {
        return num.equals(reverse(num));
    }

    public static void main(String[] args) {
        computeLychrelNumber();
        computeLychrelNumberByHashMap();
    }

    /** ****************************** OUTPUT **************************************
     *
     * ******************** SOLUTION 1 BY BRUTE FORCE ***************************
     * Time taken to Compute Lychrel Numbers : 111ms
     * Lychrel Numbers size : 249
     * Lychrel Numbers are : 196 295 394 493 592 689 691 788 790 879 887 978 986 1495 1497 1585 1587 1675 1677 1765 1767
     * 1855 1857 1945 1947 1997 2494 2496 2584 2586 2674 2676 2764 2766 2854 2856 2944 2946 2996 3493 3495 3583 3585 3673
     * 3675 3763 3765 3853 3855 3943 3945 3995 4079 4169 4259 4349 4439 4492 4494 4529 4582 4584 4619 4672 4674 4709 4762
     * 4764 4799 4852 4854 4889 4942 4944 4979 4994 5078 5168 5258 5348 5438 5491 5493 5528 5581 5583 5618 5671 5673 5708
     * 5761 5763 5798 5851 5853 5888 5941 5943 5978 5993 6077 6167 6257 6347 6437 6490 6492 6527 6580 6582 6617 6670 6672
     * 6707 6760 6762 6797 6850 6852 6887 6940 6942 6977 6992 7059 7076 7149 7166 7239 7256 7329 7346 7419 7436 7491 7509
     * 7526 7581 7599 7616 7671 7689 7706 7761 7779 7796 7851 7869 7886 7941 7959 7976 7991 8058 8075 8079 8089 8148 8165
     * 8169 8179 8238 8255 8259 8269 8328 8345 8349 8359 8418 8435 8439 8449 8490 8508 8525 8529 8539 8580 8598 8615 8619
     * 8629 8670 8688 8705 8709 8719 8760 8778 8795 8799 8809 8850 8868 8885 8889 8899 8940 8958 8975 8979 8989 8990 9057
     * 9074 9078 9088 9147 9164 9168 9178 9237 9254 9258 9268 9327 9344 9348 9358 9417 9434 9438 9448 9507 9524 9528 9538
     * 9597 9614 9618 9628 9687 9704 9708 9718 9777 9794 9798 9808 9867 9884 9888 9898 9957 9974 9978 9988 9999

     * ******************** SOLUTION 2 BY HASH MAP ***************************
     * Time taken to Compute Lychrel Numbers By map Solution : 42ms
     * Lychrel Numbers size By map Solution: 249
     * Lychrel Numbers are By map Solution : 196 295 394 493 592 689 691 788 790 879 887 978 986 1495 1497 1585 1587 1675
     * 1677 1765 1767 1855 1857 1945 1947 1997 2494 2496 2584 2586 2674 2676 2764 2766 2854 2856 2944 2946 2996 3493 3495
     * 3583 3585 3673 3675 3763 3765 3853 3855 3943 3945 3995 4079 4169 4259 4349 4439 4492 4494 4529 4582 4584 4619 4672
     * 4674 4709 4762 4764 4799 4852 4854 4889 4942 4944 4979 4994 5078 5168 5258 5348 5438 5491 5493 5528 5581 5583 5618
     * 5671 5673 5708 5761 5763 5798 5851 5853 5888 5941 5943 5978 5993 6077 6167 6257 6347 6437 6490 6492 6527 6580 6582
     * 6617 6670 6672 6707 6760 6762 6797 6850 6852 6887 6940 6942 6977 6992 7059 7076 7149 7166 7239 7256 7329 7346 7419
     * 7436 7491 7509 7526 7581 7599 7616 7671 7689 7706 7761 7779 7796 7851 7869 7886 7941 7959 7976 7991 8058 8075 8079
     * 8089 8148 8165 8169 8179 8238 8255 8259 8269 8328 8345 8349 8359 8418 8435 8439 8449 8490 8508 8525 8529 8539 8580
     * 8598 8615 8619 8629 8670 8688 8705 8709 8719 8760 8778 8795 8799 8809 8850 8868 8885 8889 8899 8940 8958 8975 8979
     * 8989 8990 9057 9074 9078 9088 9147 9164 9168 9178 9237 9254 9258 9268 9327 9344 9348 9358 9417 9434 9438 9448 9507
     * 9524 9528 9538 9597 9614 9618 9628 9687 9704 9708 9718 9777 9794 9798 9808 9867 9884 9888 9898 9957 9974 9978 9988 9999
     *
     */
}
