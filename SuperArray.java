/*****************************
Team Something: Sebastian Dittgen, Gabriel Marks
APCS1 pd10
HW42 -- Array of Titanium
2015-12-04

* class SuperArray --  A wrapper class for an array. 
* Maintains functionality:
* access value at index
* overwrite value at index
* Adds functionality to std Java array:
* resizability
* ability to print meaningfully

PHASE II additions:
* public void add(int newVal) {} //add item after last item
* public void add(int index, int newVal) {} //insert item at index, shift existing elements to the right
* public void remove(int index) {} //remove item at index, shift elements to the left to fill newly empty slot
* public int size() {} //returns number of menainful elements in _data
*****************************/

public class SuperArray implements ListInt{

    //~~~~~INSTANCE VARS~~~~~
    //underlying container, or "core" of this data structure:
    private int[] _data;

    //position of last meaningful value
    private int _lastPos;

    //size of this instance of SuperArray
    private int _size;

		
    //~~~~~METHODS~~~~~
    //default constructor â€“ initializes 10-item array
    public SuperArray() {
    	_data = new int[10];
    	_lastPos = -1;
    	_size = 0;
    }
		
    //output array in [a,b,c] format, eg
    // {1,2,3}.toString() -> "[1,2,3]"
    public String toString() {
	    if(_size == 0) return "[]";
	    String ret = "[" + _data[0];
	    for(int i = 1; i <= _lastPos; i++) {
	        ret += "," + _data[i];
	    }
	    return ret + "]";
    }
    
    //throws an error and stops execution if index too big
	private void checkIndex(int index) {
	    if(index > _lastPos) throw new ArrayIndexOutOfBoundsException();
    }	
    
    //double capacity of this SuperArray
    private void expand() {
    	int[] newArr = new int[_data.length*2];
    	for(int i=0; i<=_lastPos; i++){
    	    newArr[i]=_data[i];
    	}
    	_data = newArr;
    }
		
    //accessor -- return value at specified index
    public int get( int index ) {
        checkIndex(index);
    	return _data[index];
    }
		
    //mutator -- set value at index to newVal, 
    //           return old value at index
    public int set( int index, int newVal ) {
        checkIndex(index);
	    int oldVal = _data[index];
	    _data[index] = newVal;
	    return oldVal;
    }

    //adds item after last item
    public void add(int newVal) {
        add(_lastPos+1, newVal);
    }
    
    //adds item at index, shifts existing elements to the right
    public void add(int index, int newVal) {
        if (_size == _data.length) {
            expand();
        }
        for(int i = _size; i>index; i--) {
            _data[i] = _data[i-1];
        }
        _data[index] = newVal;
        _lastPos += 1;
        _size += 1;
    }
    
    //removes specified item and shifts array, changes size and lastpos accordingly
    public void remove(int index) {
        checkIndex(index);
        for(int i = index; i < _lastPos;i++) {
            _data[i] = _data[i+1];
        }
        _data[_lastPos] = 0; //make sure all unused data is 0 to make adding stuff easier
        _lastPos -= 1;
        _size -= 1;
    }
    
    public int size() {
        return _size;
    }
    
    //linear search function, returns index of first occurence of x, -1 if x is not there
    public int linSearch(Comparable x) {
	for (int i = 0; i < _size; i++) {
	    if (_data[i].compareTo(x)==0) {
		return i;
	    }
	}
	return -1;
    }

    //is it sorted?
    public boolean isSorted() {
	for (int i = 0; i < _size - 1; i ++) {
	    if (_data[i].compareTo(_data[i+1])>0) {
		return false;
	    }
	}
	return true;
    }

    //main method for testing
    public static void main( String[] args ) {
	//*****INSERT ADEQUATE TEST CALLS HERE*****
    	SuperArray a = new SuperArray();
    	System.out.println(a._size);
    	System.out.println(a._lastPos);
    	System.out.println(a);
    	a.expand();
    	System.out.println(a);
    	//System.out.println(a.get(14)); should error
    	a._lastPos = 10; // we can do this because in SuperArray.java
    	//System.out.println(a.get(14)); //should error
    	System.out.println(a.get(7));
    	System.out.println(a);
    	System.out.println(a._lastPos);
    	a._data = new int[]{1,2,3,4,5,6,7,8};
    	a._lastPos = 4;
    	a._size = 5;
    	System.out.println(a);
    	a.remove(2);
    	System.out.println(a);
    	a.remove(3); //test for removing last element
    	System.out.println(a);
    	a.add(1,3);
    	System.out.println(a);
    	a.add(4,5);
    	System.out.println(a);
    	a.add(6);
    	System.out.println(a);
    	System.out.println(a.size());
	ListInt b = new SuperArray();
	b.add(5);//[5]
	b.add(6);//[5,6]
	b.add(1,7);//[5,7,6]
	b.remove(0);//[7,6]
	System.out.println(b.size()); //2
	System.out.println(b.get(1)); //6
	System.out.println(b.set(1,8)); //6, [7,8]
	System.out.println(b);
    }//end main
		
}//end class
