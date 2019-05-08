package com.joe.ping;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class demo {

    public demo() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        InetAddress ip;
        try {
        	printAddresses();
            // ����IP�����ô�
            // System.out.println("Current IP address : " +
            // InetAddress.getLocalHost().getHostAddress());
            // ��һ��׼ȷ��IP�÷�
            // ���Ա�����ƪ��http://www.cnblogs.com/zrui-xyu/p/5039551.html
            System.out.println("get LocalHost Address : " + getLocalHostAddress().getHostAddress());

            // ��ȷ��IP�÷�
            System.out.println("get LocalHost LAN Address : " + getLocalHostLANAddress().getHostAddress());


        } catch (UnknownHostException e) {

            e.printStackTrace();

        }
    }

    // ��ȷ��IP�÷�����������site-local��ַ
    private static InetAddress getLocalHostLANAddress() throws UnknownHostException {
        try {
            InetAddress candidateAddress = null;
            // �������е�����ӿ�
            for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements();) {
                NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
                // �����еĽӿ����ٱ���IP
                for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements();) {
                    InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
                    if (!inetAddr.isLoopbackAddress()) {// �ų�loopback���͵�ַ
                        if (inetAddr.isSiteLocalAddress()) {
                            // �����site-local��ַ����������
                            return inetAddr;
                        } else if (candidateAddress == null) {
                            // site-local���͵ĵ�ַδ�����֣��ȼ�¼��ѡ��ַ
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }
            if (candidateAddress != null) {
                return candidateAddress;
            }
            // ���û�з��� non-loopback��ַ.ֻ�������ѡ�ķ���
            InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
            if (jdkSuppliedAddress == null) {
                throw new UnknownHostException("The JDK InetAddress.getLocalHost() method unexpectedly returned null.");
            }
            return jdkSuppliedAddress;
        } catch (Exception e) {
            UnknownHostException unknownHostException = new UnknownHostException(
                    "Failed to determine LAN address: " + e);
            unknownHostException.initCause(e);
            throw unknownHostException;
        }
    }

    //������ƪ��http://www.cnblogs.com/zrui-xyu/p/5039551.html
    //ʵ���ϵĴ����ǲ�׼��
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

    // ��������ӿ�
    public static void printAddresses() {  
        try {  
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();  
            while (allNetInterfaces.hasMoreElements()) {  
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();  

                // ȥ���ػ��ӿڣ��ӽӿڣ�δ���кͽӿ�
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {  
                    continue;  
                }

                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {  
                    InetAddress ip = addresses.nextElement(); 
                    if (ip != null) {

                        System.out.println("ip = " + ip.getHostAddress());
                        // ipv4
                        if (ip instanceof Inet4Address) {
                            System.out.println("ipv4 = " + ip.getHostAddress());

                            if (ip.getHostAddress().startsWith("192") || ip.getHostAddress().startsWith("10")  
                                    || ip.getHostAddress().startsWith("172") || ip.getHostAddress().startsWith("169")) {  
                                // ���� 
                            }
                        }
                    }
                }
            }
        } catch (SocketException e) {
            System.err.println("Error when getting host ip address"+ e.getMessage());
        }  
    }

}