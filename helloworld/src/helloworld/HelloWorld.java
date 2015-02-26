package helloworld;

public class HelloWorld {
	
	HelloWorld() {
	    System.out.println("hello constructor");
	}
	
	int getValue(int i) {
		int j=i++;
		System.out.println("The value of i is:"+j+" "+i+ " "+(--i));
		return --j;
	}
	
	static {
	    System.out.println("hai");
	}
	
    public static void main(String args[]) {
    	HelloWorld hw = new HelloWorld();
    	int h=hw.getValue(10);
    	for(int i=0;i<h;i++) {
    	    System.out.println("Main block:"+i+" "+h--);
    	}
    }
    
    {
    	System.out.println("just a block");
    }
}
