//Sebastian Dittgen
//APCS1 pd10
//HW43 This or That
//2015 - 12 - 08
//skeleton file for class Binary

public class Binary implements Comparable{

    private int _decNum;
    private String _binNum;


    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _binNum to "0"
      =====================================*/
    public Binary() { 
        _decNum = 0;
	_binNum = "0";
    }


    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _binNum to equiv string of bits
      =====================================*/
    public Binary( int n ) {
	_decNum = n;
	_binNum = decToBin(n);
    }


    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative binary number
      post: sets _binNum to input, _decNum to decimal equiv
      =====================================*/
    public Binary( String s ) {
	_decNum = binToDec(s);
	_binNum = s;
    }

    public int getDec() {
	return _decNum;
    }
	
    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String of 1's and 0's representing value of this Object
      =====================================*/
    public String toString() { 
	return _binNum;
    }


    /*=====================================
      String decToBin(int) -- converts base-10 input to binary
      pre:  n >= 0
      post: returns String of bits
      eg  decToBin(0) -> "0"
      decToBin(1) -> "1"
      decToBin(2) -> "10"
      decToBin(3) -> "11"
      decToBin(14) -> "1110"
      =====================================*/
    public static String decToBin( int n ) {
	int decNum = n;
	int remainder = 0;
	String retBin = "";
	while (decNum > 1) {
	    remainder = decNum%2;
	    retBin = remainder+ retBin;
	    decNum /= 2;
	}
	return retBin;
    }


    /*=====================================
      String decToBinR(int) -- converts base-10 input to binary, recursively
      pre:  n >= 0
      post: returns String of bits
      eg  decToBinR(0) -> "0"
      decToBinR(1) -> "1"
      decToBinR(2) -> "10"
      decToBinR(3) -> "11"
      decToBinR(14) -> "1110"
      =====================================*/
    public static String decToBinR( int n ) { 
	String retBin = "";
	if (n > 1) {
	    retBin = decToBinR(n / 2) + n % 2;
	}
	return retBin;
    }


    /*=====================================
      String binToDec(String) -- converts base-10 input to binary
      pre:  s represents non-negative binary number
      post: returns String of bits
      eg  
      binToDec("0") -> 0
      binToDec("1") -> 1
      binToDec("10") -> 2
      binToDec("11") -> 3
      binToDec("1110") -> 14
      =====================================*/
    public static int binToDec( String s ) {
	int retDec = 0;
	int exp = 0;
	for (int i = s.length(); i > 0; i--) {
	    retDec += Math.pow(2,exp)*Integer.parseInt(s.substring(i-1, i));
	    exp ++;
	}
	return retDec;
    }


    /*=====================================
      String binToDecR(String) -- converts base-10 input to binary, recursively
      pre:  s represents non-negative binary number
      post: returns String of bits
      eg  
      binToDecR("0") -> 0
      binToDecR("1") -> 1
      binToDecR("10") -> 2
      binToDecR("11") -> 3
      binToDecR("1110") -> 14
      =====================================*/
    public static int binToDecR( String s ) { 
	int retDec = 0;
	int exp = 0;
	if (s.length() == 0) {
	    retDec += Math.pow(2,exp)*Integer.parseInt(s.substring(s.length()-1, s.length()));
	    exp ++;
	    retDec += binToDecR(s.substring(0,s.length()-1));
	}
	return retDec;
    }
	  
    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Binary
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal binary values
      =============================================*/
    public boolean equals( Object other ) {
    	Binary otherBin = (Binary)(other);
    	return this.compareTo(otherBin) == 0;
    }

    /*=============================================
      int compareTo(Object) -- tells which of two Binary objects is greater
      pre:  other is instance of class Binary
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
      =============================================*/
    public int compareTo( Object other ) {
	if (other instanceof Comparable) {
	    if(other instanceof Binary) {
		if (this._decNum == ((Binary)other).getDec()) {return 0;}
		else if (this._decNum > ((Binary)other).getDec()) {return 1;}
		else {return -1;}
	    }
	    else if (other instanceof Hexadecimal) {
		if (this._decNum == ((Hexadecimal)other).getDec()) {return 0;}
		else if (this._decNum > ((Hexadecimal)other).getDec()) {return 1;}
		else {return -1;}
	    }
	    else {
		if (other instanceof Rational) {
		    if (this._decNum == ((Rational)other).floatValue()) {return 0;}
		    else if (this._decNum > ((Rational)other).floatValue()) {return 1;}
		    else {return -1;}
		}
	    }
	}
	throw new ClassCastException("\n your compareTo() input is not comparable.\n");
    }


    //main method for testing
    public static void main( String[] args ) {


	System.out.println();
	System.out.println( "Testing ..." );
	Binary b1 = new Binary(5);
	Binary b2 = new Binary(5);
	Binary b3 = b1;
	Binary b4 = new Binary(7);
	System.out.println( b1 );
	System.out.println( b2 );
	System.out.println( b3 );       
	System.out.println( b4 );       
	System.out.println( "\n==..." );
	System.out.println( b1 == b2 ); //should be false
	System.out.println( b1 == b3 ); //should be true
	System.out.println( "\n.equals()..." );
	System.out.println( b1.equals(b2) ); //should be true
	System.out.println( b1.equals(b3) ); //should be true
	System.out.println( b3.equals(b1) ); //should be true
	System.out.println( b4.equals(b2) ); //should be false
	System.out.println( b1.equals(b4) ); //should be false
	System.out.println( "\n.compareTo..." );
	System.out.println( b1.compareTo(b2) ); //should be 0
	System.out.println( b1.compareTo(b3) ); //should be 0
	System.out.println( b1.compareTo(b4) ); //should be neg
	System.out.println( b4.compareTo(b1) ); //should be pos
    }//end main()

} //end class
