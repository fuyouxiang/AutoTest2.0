package socketWeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
/**
 * 客户端
 * */
public class SocketClient{
	
	//引用socket
    Socket socket;
    //服务端ip
    String address="10.16.21.20";
    //服务端端口
    int portNumber=9998;
    PrintWriter printWriter;
    BufferedReader bufferedReader;
    
    //socket连接
    public SocketClient(){
        try {
            socket=new Socket(address,portNumber);
            bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter=new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public void chat(){
        System.out.println("请输入要发送的报文号：");
        Scanner scanner=new Scanner(System.in);
        String str="";


        while(true){
            str=scanner.nextLine();
            if(str.equals("311")){
            	str="MBD010000003380092755-M00FE-875706"+"        "+"0030000001000052e817d0002c01c904f800059b873b9db52d7062001070052078311^^^^^^^<?xml version=\"1.0\" encoding=\"GB2312\"?><SZFS_SYNC>"+"\n"
            		+"<REQ_CMD>311</REQ_CMD>"+"\n"
            		+"<BANK_NO>007</BANK_NO>"+"\n"
            		+"<DATA>"+"\n"
            		+"<UNIT_NO>0102021001</UNIT_NO>"+"\n"
            		+"<PN_NO>1112222002</PN_NO>"+"\n"
            		+"<PAID_DATE>20200317</PAID_DATE>"+"\n"
            		+"</DATA>"+"\n"
            		+"</SZFS_SYNC>";
            }
                printWriter.println(str);
                printWriter.flush();
                System.out.println(getCurTime()+"客户端："+str);
                
                if(str.equals("bye")){
                    break;
                }
                
                /*
            String server_str= null;
            try {
                server_str = bufferedReader.readLine();
                System.out.println(getCurTime()+"服务器："+server_str);
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
        scanner.close();
        close();
    }
    
    //记录时间
    public static String getCurTime(){
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
    
    //socket连接关闭
    public void close(){
        try {
            printWriter.close();
            bufferedReader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //主方法
    public static void main(String[] args) {
    	SocketClient socketClient=new SocketClient();
        socketClient.chat();
    }
}
