//Team - Sebastian Dittgen, Denis Duman
//APCS1 pd10
//HW43 This or That or Fourteen Other Things
//2015 - 12 - 08

//skeleton file for class Hexadecimal
public class Hexadecimal {

    private int _decNum;
    private String _hexNum;
    private final static String HEXDIGITS = "0123456789ABCDEF";

    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _hexNum to "0"
      =====================================*/
    public Hexadecimal() { 
        _decNum = 0;
      	_hexNum = "0";
     }


    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _hexNum to equiv string of bits
      =====================================*/
    public Hexadecimal( int n ) {
	    _decNum = n;
	    _hexNum = decToHex(n);
    }


    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative Hexadecimal number
      post: sets _hexNum to input, _decNum to decimal equiv
      =====================================*/
    public Hexadecimal( String s ) {
	    _decNum = hexToDec(s);
	    _hexNum = s;
    }

    public String getHex(){
		return _hexNum;
	}

	public int getDec(){		
		return _decNum;
	}
	
    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String of 1's and 0's representing value of this Object
      =====================================*/
    public String toString() { 
	    return _hexNum;
    }


    /*=====================================
      String decToHex(int) -- converts base-10 input to Hexadecimal
      pre:  n >= 0
      post: returns String of bits
      eg  decTohex(0) -> "0"
      decTohex(1) -> "1"
      decTohex(2) -> "10"
      decTohex(3) -> "11"
      decTohex(14) -> "1110"
      =====================================*/
    public static String decToHex( int n ) {
	    int decNum = n; 
	    int remainder = 0;
	    String rethex = "";
	while (decNum > 15) {
	    remainder = decNum%16;
	    rethex = HEXDIGITS.substring(remainder, remainder + 1) + rethex;
	    decNum /= 16;
	}
	return rethex;
    }


    /*=====================================
      String decToHexR(int) -- converts base-10 input to Hexadecimal, recursively
      pre:  n >= 0
      post: returns String of bits
      eg  decToHexR(0) -> "0"
      decTohexR(1) -> "1"
      decTohexR(2) -> "10"
      decTohexR(3) -> "11"
      decTohexR(14) -> "1110"
      =====================================*/
    public static String decToHexR( int n ) { 
      if (n == 0) {
        return "";
      }
      else {
        return decToHexR(n / 16) + HEXDIGITS.substring(n % 16, n % 16 + 1);
      }
    }


    /*=====================================
      String hexToDec(String) -- converts base-10 input to Hexadecimal
      pre:  s represents non-negative Hexadecimal number
      post: returns String of bits
      eg  
      hexToDec("0") -> 0
      hexToDec("1") -> 1
      hexToDec("10") -> 2
      hexToDec("11") -> 3
      hexToDec("1110") -> 14
      =====================================*/
      

    public static int hexToDec( String s   ) {
	    int recDec = 0, dec;
	    char hex;
      for (int i = 0; i < s.length(); i++) {
        hex = s.charAt(i);
        dec = HEXDIGITS.indexOf(hex);
        recDec = 16*recDec + dec;
      }
      return recDec;
    }


    /*=====================================
      String hexToDecR(String) -- converts base-10 input to Hexadecimal, recursively
      pre:  s represents non-negative Hexadecimal number
      post: returns String of bits
      eg  
      hexToDecR("0") -> 0
      hexToDecR("1") -> 1
      hexToDecR("10") -> 2
      hexToDecR("11") -> 3
      hexToDecR("1110") -> 14
      =====================================*/
    public static int hexToDecR( String s ) { 
      if (s.length() == 0) {
        return 0;
      }
      else {
        return HEXDIGITS.indexOf(s.substring(0,1))*(int)Math.pow(16, s.length()-1)
          + hexToDecR(s.substring(1));
      }
    }
    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Hexadecimal
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal Hexadecimal values
      =============================================*/
    public boolean equals(Object o){//Returns a boolean value indicating equality between this Hexadecimal object and another.
		boolean boo = this == o;//Check for aliasing.
		if (!boo) return (o instanceof Hexadecimal && this.compareTo((Hexadecimal)o) == 0);//If object is Hexadecimal, check if values are equal.
		return boo;//Return false if not the same object type.
	}

    /*=============================================
      int compareTo(Object) -- tells which of two Hexadecimal objects is greater
      pre:  other is instance of class Hexadecimal
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
      =============================================*/
    public int compareTo(Hexadecimal b){//Compares this Hexadecimal object to another Hexadecimal object.
		if (this.getDec() == b.getDec()) return 0;//Return 0 if this object has a Hexadecimal value equal to the other object's Hexadecimal value.
		if (this.getDec() < b.getDec()) return -1;//Return -1 if this object has a Hexadecimal value less than the other object's Hexadecimal value.
		if (this.getDec() > b.getDec()) return 1;//Return 1 if this object has a Hexadecimal value greater than the other object's Hexadecimal value.
		return -999;//Return -999 in case of failure.
	}
	
	public int compareTo(Object o){//Compares this Hexadecimal object to another object.
		if (this == o) return 0;//Check for aliasing.
		if (o instanceof Hexadecimal) return compareTo((Hexadecimal)o);//Compares if object is Hexadecimal.
	  else throw new ClassCastException("\nMy first error message! compareTo() input not a hex.");//Return if not the same object type.	
	}

    //main method for testing
    public static void main( String[] args ) {


	System.out.println();
	System.out.println( "Testing ..." );
	Hexadecimal b1 = new Hexadecimal(5);
	Hexadecimal b2 = new Hexadecimal(19);
	Hexadecimal b3 = b1;
	Hexadecimal b4 = new Hexadecimal(14);
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
