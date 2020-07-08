package socketWeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 服务器端
 * */
public class SocketServer {
    private int server_port=8765;
    private ServerSocket serverSocket;
    PrintWriter printWriter;
    BufferedReader bufferedReader;
    Socket socket;
    public SocketServer(){
        try {
        	while (true){ 
            serverSocket=new ServerSocket(server_port);
            System.out.println("服务器已启动！");
            
            socket=serverSocket.accept();
            System.out.println("有用户接入了！");
            
            //printWriter=new PrintWriter(socket.getOutputStream());
            bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream(),"GBK"));
         	
                String str=bufferedReader.readLine();

                if(str==null){
                	continue;
                }
                Date date=new Date();
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time= simpleDateFormat.format(date);
                //String value = new String(str.getBytes("utf-8"),"gb2312");
                System.out.println(time+"客户端："+str);
                socket.close();
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        finally {
            try {
            	printWriter.close();
                bufferedReader.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }*/
    }

    public static void main(String[] args) {
        new SocketServer();
    }
}