package testz;

public class A {
	
	static A obj ;
	
	private A() {
		
		System.out.println("Instance created");
		
	}
	
	public static  A getInstance() { 
		if(obj ==null) {
			synchronized (A.class) {
				if(obj ==null){
					 obj = new A();
				}
				
			}
			
			
		}
		return obj;
		
		
	}

}
