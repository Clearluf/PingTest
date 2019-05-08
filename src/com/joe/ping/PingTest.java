package com.joe.ping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PingTest {

	public static void main(String[] args) {            
		// TODO Auto-generated method stub
    	String hostaddress = null;
		try {
			hostaddress = getLocalHostAddress().getHostAddress();
			System.out.println("Hostaddress: "+hostaddress);

			Scanner sc=new Scanner(System.in);
			System.out.println("ping from: ");
			String initIp=sc.nextLine();
			
			Integer lastNum=Integer.valueOf(initIp.split("\\.")[3]);
			
		for(int i=0;i<255-lastNum;i++) {   
			String ip=initIp;
			ip=ipAdd(ip,i);
				if(ping(ip)) {
					if(!ip.equals(hostaddress)) {
						System.out.println("the Ip is: "+ip);
						break;
					}					
				}
				System.out.println("ping "+ip+" false");
				
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
	}
    private static String ipAdd(String initIp,int i) {
		// TODO Auto-generated method stub
    	Integer lastNum=Integer.valueOf(initIp.split("\\.")[3])+i;
    	return initIp=initIp.split("\\.")[0]+"."+initIp.split("\\.")[1]+"."+initIp.split("\\.")[2]+"."+String.valueOf(lastNum);
	
	}
	public static boolean ping(String ipAddress) throws Exception {
        int  timeOut =  3000 ;  //��ʱӦ����3������        
        boolean status = InetAddress.getByName(ipAddress).isReachable(timeOut);     // ������ֵ��trueʱ��˵��host�ǿ��õģ�false�򲻿ɡ�
        return status;
    }
    public static boolean ping(String ipAddress, int pingTimes, int timeOut) {  
        BufferedReader in = null;  
        Runtime r = Runtime.getRuntime();  // ��Ҫִ�е�ping����,��������windows��ʽ������  
        String pingCommand = "ping " + ipAddress + " -n " + pingTimes    + " -w " + timeOut;  
        try {   // ִ�������ȡ���  
            System.out.println(pingCommand);   
            Process p = r.exec(pingCommand);   
            if (p == null) {    
                return false;   
            }
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));   // ���м�����,�������Ƴ���=23ms TTL=62�����Ĵ���  
            int connectedCount = 0;   
            String line = null;   
            while ((line = in.readLine()) != null) {    
                connectedCount += getCheckResult(line);   
            }   // �����������=23ms TTL=62����������,���ֵĴ���=���Դ����򷵻���  
            return connectedCount == pingTimes;  
        } catch (Exception ex) {   
            ex.printStackTrace();   // �����쳣�򷵻ؼ�  
            return false;  
        } finally {   
            try {    
                in.close();   
            } catch (IOException e) {    
                e.printStackTrace();   
            }  
        }
    }
    private static int getCheckResult(String line) {  // System.out.println("����̨����Ľ��Ϊ:"+line);  
        Pattern pattern = Pattern.compile("(\\d+ms)(\\s+)(TTL=\\d+)",    Pattern.CASE_INSENSITIVE);  
        Matcher matcher = pattern.matcher(line);  
        while (matcher.find()) {
            return 1;
        }
        return 0; 
    }
    
    private static InetAddress getLocalHostAddress() throws UnknownHostException {
        Enumeration allNetInterfaces;
        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();

                Enumeration addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    ip = (InetAddress) addresses.nextElement();
                    if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                        if (ip != null && ip instanceof Inet4Address) {
                            return ip;
                        }
                    }
                }
            }
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
        if (jdkSuppliedAddress == null) {
            throw new UnknownHostException("The JDK InetAddress.getLocalHost() method unexpectedly returned null.");
        }
        return jdkSuppliedAddress;
    }

}
