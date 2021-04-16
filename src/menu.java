import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.util.Scanner;

import javax.crypto.Cipher;

public class menu {

	
	public static double n;
	public static double e;
	public static double phi;
	public static double d;
	public static double c;

	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		
		System.out.println("1 - Key Generation");
		System.out.println("2 - RSA Encryption & Decryption Algorithm");
		System.out.println("3 - RSA Completed Algorithm");
		
		Scanner scanchoice = new Scanner(System.in);

		int choiceentry = scanchoice.nextInt();

		System.out.println();

		if (choiceentry != 4) {

		    if (choiceentry < 1 || choiceentry > 5) {

		        System.out.println("Enter \"1\", \"2\", \"3\" ,\"4\", \"5\"");
		        choiceentry = scanchoice.nextInt();

		    }

		    else if(choiceentry == 1) {
		    	KeyGenerator();
		    	
		    }
		    else if(choiceentry == 2) {
		    	EncryptionAlgo();

		        
		    }
		    else if(choiceentry == 3) {
		    	myCipher();

		    }
		

		}

	}
	

	public static void myCipher() throws Exception {
			
			
		
		// TODO Auto-generated method stub
	    Scanner myObj = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("ALICE - Enter The Message you would like to send to Bob");

	    String UserInput = myObj.nextLine();  // Read user input
	  
	    
		
		 Signature sign = Signature.getInstance("SHA256withRSA");
		 
	     
	      //Creating KeyPair generator object
	      KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		    

	      //Initializing the key pair generator
	      keyPairGen.initialize(2048);
	      
	      //Generating the pair of keys
	      KeyPair pair = keyPairGen.generateKeyPair(); 
		    

		
	      //Creating a Cipher object
	      Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		

	      System.out.println("ALICE :" + pair);

	      //Initializing a Cipher object
	      cipher.init(Cipher.ENCRYPT_MODE, pair.getPublic());
		  
	      //Adding data to the cipher
	      
	      byte[] input = UserInput.getBytes();	  
	      cipher.update(input);
		  
	      //encrypting the data
	      byte[] cipherText = cipher.doFinal();	
	      
	      System.out.println("Encryption Being Sent To Bob: ");

	      System.out.println(new String(cipherText, "UTF8"));
	      
	      
	      //BOB - Using the same cipher to initialise the decryption
	      cipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());
	      
	      //Decrypting the text
	      System.out.println("BOB - Recieves the cipher text and decrypts with the Key Pair");

	      byte[] decipheredText = cipher.doFinal(cipherText);

	      System.out.println("Decryption: ");
	      System.out.println(new String(decipheredText));
	      
	      //Showing the Cipher Pairs are the same as the One Alice Used.
	      System.out.println("BOB :" + pair);



	      
	      System.exit(0);
		
		}



	
	public static void PhiEquation(double p, double q) {
		
		//Phi to ensure x^n mod phi = 1 
		phi = (p-1)*(q-1);
		System.out.println("PHI = "+ phi);
		
	}
	
	//Checking if phi and e have a share a commmon factor

	public static double findFactor(double x, double y) {
		
		//Checking if phi and e have a share a commmon factor
		
		
		if (y == 0) {
			return x;
		}


		return findFactor(y, x % y);
	}
	//Checks that the number entered by the user are prime. As this is what's required to create our encryption key.
	public static boolean checkPrime(double p) throws Exception {

		
		   boolean flag = false;
		    for (double i = 2; i <= p / 2; ++i) {
		      // condition for non-prime number
		      if (p % i == 0) {
		    	  //Changes the boolean condition
		        flag = true;
		        break;
		      }
		    }

		    if (!flag)
		      System.out.println(p + " is a prime number.");
		    
		    else {
		      System.out.println(p + " is not a prime number.");
//		      main(null);
		    }
			return flag;
	}
	
	public static void PQEquation(double p, double q) throws Exception{
//		User Input for P & Q which will give us the value of n and k which will be displayed and available to the public
		// P & Q are only known to Bob who will be recieving the message from Alice
		
		System.out.print("\nEnter Value of P: ");
		Scanner UserP = new Scanner(System.in);
		System.out.print("\nEnter Value of Q: ");
		Scanner UserQ = new Scanner(System.in);
		
		double pp = UserP.nextInt();
		double qq = UserQ.nextInt();
		
	}
	
	//Key Generator
	public static void KeyGenerator() throws Exception {

		System.out.println( "Implement a cryptorandom key generator and the algorithm for modular exponentiation");
		
//		User Input for P & Q which will give us the value of n and k which will be displayed and available to the public
		// P & Q are only known to Bob who will be recieving the message from Alice
		
		System.out.print("\nEnter Value of P: ");
		Scanner UserP = new Scanner(System.in);
		System.out.print("\nEnter Value of Q: ");
		Scanner UserQ = new Scanner(System.in);

		double p = UserP.nextInt();
		double q = UserQ.nextInt();

		
		
		//Checking the numbers of p and q are both prime to allow for the calculation of N
		checkPrime(p);
		checkPrime(q);
		
//Our first public key is created
		n = p*q;
		

		
		boolean isItPrime = true; 
		System.out.println("N = " + n );
		
		PhiEquation(p,q);

		
		//e needs to be > 2 and phi must not share a common factor with e
		e = 2;
		System.out.println("E = "+ e);


		double g = findFactor(e,phi);
		
		if (g == e) {
			e += 1;
			for (int i = 2; i <= e/2; i++) {
				if((e % i) == 0) {
					isItPrime = false;
					e+=1;
				}
				System.out.println("E: "+ e);
			}
			

		}
		
		System.out.println("Our Public Keys N: " + n );
		System.out.println("Our Public Keys E: " + e );


		
		return; 
		
	}



	public static void EncryptionAlgo() {
		
		//Takes the input values 
		
		System.out.print("Enter Value of P: ");
		Scanner UserP = new Scanner(System.in);
		System.out.print("Enter Value of Q: ");
		Scanner UserQ = new Scanner(System.in);
		
		
		double p = UserP.nextInt();
		double q = UserQ.nextInt();

		

// Encryption Algorithm Alice wants to send a message
		System.out.print("\nWhat is the value of your message: ");
		Scanner message = new Scanner(System.in);
		int M = message.nextInt();

		System.out.print("\nWhat is the value of E: ");
		Scanner UserE  = new Scanner(System.in);
		int E = UserE.nextInt();
		
		//Prime number k which are both public keys 
		double PublicK = 2;

		
		//Phi to ensure x^n mod phi = 1 
		phi = (p-1)*(q-1);
		System.out.print("\nWhat is the value of PHI: " + phi);
		double MM = M;
		
		double e = E;
		double temp;
		
		System.out.print("\nWhat is the value of E: " + e);

		boolean isItPrime = true; 
		//Checks that e and phi have not got a common factor

		double g = findFactor(e,phi);
		
		//If there's a common factor, it will increment e and check if e is prime so we ensure we are meeting requirements for RSA
		
		if (g == e) {
			e += 1;
			for (int i = 2; i <= e/2; i++) {
				if((e % i) == 0) {
					isItPrime = false;
					
					e+=1; //Incremental of e 
				}
				System.out.println("\nE: "+ e);
			}
			

		}
		

		double n = p * q;
		

		
		double d = (1 + (PublicK*phi))/e;

		//Performs the Encryption of M^e mod n = c;

		double m = Math.pow(c, d);
		double mm = m%n;
		
		System.out.println("\nOriginal Message: " + mm);

		
		MM = Math.pow(M,e);
		c = MM % n;
		
		System.out.println("\nDecryption Section");

		System.out.println("\nEncrypted Data: " + c);
		
	


		
	}
	

	
	
	

}
