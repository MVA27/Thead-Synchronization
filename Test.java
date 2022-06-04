class MThread extends Thread
{
	int start = 0;
	int numberOfDigits = 0;
	int step = 4;
	int current = 0;
	
	MThread(){}

	MThread(int start,int numberOfDigits){
		this.start = this.current = start;
		this.numberOfDigits = numberOfDigits;
	}
	
	public void run(){
		suspend();
		
		for(int i=1 ; i<=numberOfDigits ; i++){
			System.out.println(this.getName() +" -> "+ current);
			current = current + step;	
			suspend();
		}
	}

	public String toString(){
		return this.getName()+" , start : "+start+" , digits : "+numberOfDigits+" , isAlive : "+this.isAlive();
	}
}

class Test {
	
	public static void main(String[] args) {

		int n  = Integer.parseInt(System.console().readLine("Enter Number of Threads : "));
		MThread[] threadArray = new MThread[n];
		
		int d = Integer.parseInt(System.console().readLine("Enter Number of Digits : "));

		System.out.println("\n  Thread    Series ");

		//Input
		for(int i = 0 ; i < n ; i++){
			threadArray[i] = new MThread(i,d);
		}

		//Start
		for(int i = 0 ; i < n ; i++){
			Thread thread = threadArray[i];
			thread.setName("Thread : "+i);
			thread.start();
		}
	
		pause(5000);

		//Resume each thread one by one and pause execution so that other thread dosent resume
		for(int i = 1 ; i <= d ; i++){
			for(MThread thread : threadArray){
				thread.resume();
				pause(1000);
			}
		}
		
		pause(5000);
		
		for(MThread thread : threadArray){
			thread.stop();
		}
  }
  
	public static void pause(int time){
	  	try{
			Thread.sleep(time);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}