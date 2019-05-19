package com.joe.ping;

import java.util.Scanner;

public class testThread {

	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		String segment;
		System.out.println("enter the network segment:");
		segment=s.nextLine();
		PingThread t1=new PingThread();
		t1.theStartIp=segment+".1";
		t1.theEnd=30;
		PingThread t2=new PingThread();
		t2.theStartIp=segment+".31";
		t2.theEnd=60;
		PingThread t3=new PingThread();
		t3.theStartIp=segment+".61";
		t3.theEnd=90;
		PingThread t4=new PingThread();
		t4.theStartIp=segment+".91";
		t4.theEnd=120;
		PingThread t5=new PingThread();
		t5.theStartIp=segment+".121";
		t5.theEnd=150;
		PingThread t6=new PingThread();
		t6.theStartIp=segment+".151";
		t6.theEnd=180;		
		PingThread t7=new PingThread();
		t7.theStartIp=segment+".181";
		t7.theEnd=210;		
		PingThread t8=new PingThread();
		t8.theStartIp=segment+".211";
		t8.theEnd=255;
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		
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
