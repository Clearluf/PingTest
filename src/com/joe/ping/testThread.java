package com.joe.ping;

public class testThread {

	public static void main(String[] args) {
		PingThread t1=new PingThread();
		t1.theStartIp="192.168.3.1";
		t1.theEnd=60;
		PingThread t2=new PingThread();
		t2.theStartIp="192.168.3.61";
		t2.theEnd=120;
		PingThread t3=new PingThread();
		t3.theStartIp="192.168.3.121";
		t3.theEnd=180;
		PingThread t4=new PingThread();
		t4.theStartIp="192.168.3.181";
		t4.theEnd=255;
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		
//		int threadNum;
//		Scanner s=new Scanner(System.in);
//		threadNum=s.nextInt();
//		for(int i=0;i<threadNum;i++) {
//			String ThreadName="t"+i;
//			PingThread t=new PingThread();
//			t.setName(ThreadName);
//			System.out.println(ThreadName+" ping from:");
//			t.theStartIp=s.nextLine();
//			System.out.println(ThreadName+" ping to:");
//			t.theEnd=s.nextInt();
//			
//		}
		
	}
}
