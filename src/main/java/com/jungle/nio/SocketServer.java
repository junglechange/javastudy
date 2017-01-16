package com.jungle.nio;

import com.sun.corba.se.spi.orbutil.fsm.Input;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by yunjiang on 2017/1/16.
 */
public class SocketServer {
    public static void main(String[] args){
        try {
            ServerSocket serverSocket = new ServerSocket(8082);
            while (true){
                Socket socket =serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                IReadScoket readScoket = new ReadInAnotherT(inputStream);
                readScoket.read();
            }
//            Socket socket =serverSocket.accept();
//            InputStream inputStream = socket.getInputStream();
//            IReadScoket readScoket = new ReadInOneT(inputStream);
//            readScoket.read();





        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    interface IReadScoket{
        void read();
    }

    static class ReadInOneT implements IReadScoket{
        private InputStream rdIns = null;
        public ReadInOneT(InputStream inputStream){
            rdIns = inputStream;
        }

        @Override
        public void read() {
            BufferedReader bfr = new BufferedReader(new InputStreamReader(rdIns));
            String re;
            String tName = Thread.currentThread().getName();
            System.out.println(tName);
            try {
                while((re= bfr.readLine())!=null){
                    System.out.println(re);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    rdIns.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class ReadInAnotherT implements IReadScoket{
        private InputStream rdIns;
        private Thread readThread;
        public ReadInAnotherT(InputStream inputStream){
            this.rdIns = inputStream;
            readThread =new Thread(new Runnable() {
                @Override
                public void run() {
                    BufferedReader bfr = new BufferedReader(new InputStreamReader(rdIns));
                    String re;
                    String tName = Thread.currentThread().getName();
                    System.out.println(tName);
                    try {
                        while((re= bfr.readLine())!=null){
                            System.out.println(re);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            rdIns.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        @Override
        public void read(){
            readThread.start();
        }
    }
}
