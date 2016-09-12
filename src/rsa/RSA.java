/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsa;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

/**
 *
 * @author Leon
 */
public class RSA {
    static BigInteger p,q,n,phi,e,d;
    SecureRandom r;
    
    public RSA(){
        r = new SecureRandom();
        p = BigInteger.probablePrime(31, r);
        q = BigInteger.probablePrime(31, r);
        n = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(15, r);
        while(phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0){
            e.add(BigInteger.ONE);
        }
        d = e.modInverse(phi);
    }
    
    public static String encode(byte [] text){
        String s = "";
        for(byte b : text){
            s += Byte.toString(b);
        }
        return s;
    }
    
    public static byte [] encrypted(byte [] text){
        return new BigInteger(text).modPow(e, n).toByteArray();
    }
    
    public static byte [] decrypted(byte [] text){
        return new BigInteger(text).modPow(d, n).toByteArray();
    }
    
    public static void main(String[] args){
        RSA rsa = new RSA();
        System.out.println("Enter input string");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        byte [] encrypted = rsa.encrypted(input.getBytes());
        System.out.println("Encrypted String " + new String(encrypted));
        byte [] decrypted = rsa.decrypted(encrypted);
        System.out.println("Before decryption code" + encode(decrypted));
        System.out.println("After complete decryption " + new String(decrypted));
        
    }
    
}

/*
Test Case 1 : 
Enter input string
poiu
Encrypted String �P�`�W�
Before decryption code112111105117
After complete decryption poiu

Test Case 2 :
Enter input string
werty
Encrypted String ��珟9
Before decryption code119101114116121
After complete decryption werty

Test Case 3 : 
Enter input string
2345
Encrypted String �KS��,
Before decryption code50515253
After complete decryption 2345
*/
