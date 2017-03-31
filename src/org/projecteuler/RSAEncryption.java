package org.projecteuler;

/**
 * Created by lmahabaleshwara on 3/30/17.
 */
public class RSAEncryption {

    /************************ RSA encryption -  Problem 182 ***********************

     * The RSA encryption is based on the following procedure:

     Generate two distinct primes p and q.
     Compute n=pq and φ=(p-1)(q-1).
     Find an integer e, 1<e<φ, such that gcd(e,φ)=1.

     A message in this system is a number in the interval [0,n-1].
     A text to be encrypted is then somehow converted to messages (numbers in the interval [0,n-1]).
     To encrypt the text, for each message, m, c=me mod n is calculated.

     To decrypt the text, the following procedure is needed: calculate d such that ed=1 mod φ, then for each encrypted message, c, calculate m=cd mod n.

     There exist values of e and m such that me mod n=m.
     We call messages m for which me mod n=m unconcealed messages.

     An issue when choosing e is that there should not be too many unconcealed messages.
     For instance, let p=19 and q=37.
     Then n=19*37=703 and φ=18*36=648.
     If we choose e=181, then, although gcd(181,648)=1 it turns out that all possible messages
     m (0≤m≤n-1) are unconcealed when calculating me mod n.
     For any valid choice of e there exist some unconcealed messages.
     It's important that the number of unconcealed messages is at a minimum.

     Choose p=1009 and q=3643.
     Find the sum of all values of e, 1<e<φ(1009,3643) and gcd(e,φ)=1, so that the number of unconcealed messages for this value of e is at a minimum.
     */

    /** References :
     * https://www.youtube.com/watch?v=Z8M2BTscoD4 (Good video to understand RSA Logic)
     * https://www.utdallas.edu/~muratk/courses/crypto09s_files/rsa.pdf
     * http://www.hit.bme.hu/~buttyan/courses/BSc_Coding_Tech/asymmetric-enc.pdf
     * http://www.di-mgt.com.au/rsa_alg.html
     * https://www.youtube.com/watch?v=O-4_oS3G7MI
     * http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.99.2838&rep=rep1&type=pdf
     */

    // Initialization of Given Data

    private static final long P = 1009;
    private static final long Q = 3643;
    private static final long TOTIENT = (P - 1) * (Q - 1);

    private static void computeSumOfRSAPublicExponent(){
        // Find an integer e, 1<e<φ, such that gcd(e,φ)=1.
        long e =2l; // e is greater than 2 and lesser than TOTIENT-1
        long sum = 0l;
        long elapsedTime = 0l;
        long startTime = System.currentTimeMillis();
        long endTime = 0l;
        long gcdValue_One = 0l;
        long gcdValue_Two = 0l;

        /** NOTES : (viii) Message concealing  --- page 290 in this (handbook of applied cryptography)link http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.99.2838&rep=rep1&type=pdf
         A plaintext message m, 0 ≤ m ≤ n − 1, in the RSA public-key encryption scheme is said
         to be unconcealed if it encrypts to itself; that is, me ≡ m (mod n). There are always some
         messages which are unconcealed (for example m = 0, m = 1, and m = n − 1). In fact,
         the number of unconcealed messages is exactly
         [1 + gcd(e − 1, p − 1)] · [1 + gcd(e − 1, q − 1)].
         Since e − 1, p − 1 and q − 1 are all even, the number of unconcealed messages is always at
         least 9. If p and q are random primes, and if e is chosen at random (or if e is chosen to be
         a small number such as e = 3 or e = 216 + 1 = 65537), then the proportion of messages
         which are unconcealed by RSA encryption will, in general, be negligibly small, and hence
         unconcealed messages do not pose a threat to the security of RSA encryption in practice. **/

        // (1 + gcd (e-1),(p-1)) * (1 + gcd (e-1), (q-1)) == 9 or (gcd (e-1), (p-1)) +1 == 3 && (gcd (e-1), (q-1)) +1 == 3

        while(e < TOTIENT){

            if(gcd (e,TOTIENT) == 1){  //gcd (e, TOTIENT ) == 1  Given data in the Problem
                gcdValue_One = gcd((e-1),(P-1));
                gcdValue_Two = gcd((e-1),(Q -1));

                if((1 + gcdValue_One) == 3 && (1 + gcdValue_Two) == 3){
                    sum += e;
                }
            }
            e++;
        }
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;

        System.out.println("Time taken to Compute Sum Of RSA PublicExponent : " + elapsedTime+"ms");
        System.out.println("Sum of public exponent(e) is : "+sum);
    }

    /**
     * Computes Greatest Common Divisor (GCD) of given two variables
     * @param a
     * @param b
     * @return gcd value
     */
    private static long gcd(long a, long b) {
        long t;
        while(b != 0){
            t = a;
            a = b;
            b = t%b;
        }
        return a;
    }

    public static void main(String[] args) {
        computeSumOfRSAPublicExponent();
    }

    /**
     *   ***************************** OUTPUT ***********************************
     *   Time taken to Compute Sum Of RSA PublicExponent : 795ms
     *   Sum of public exponent(e) is : 399788195976
     */
}
