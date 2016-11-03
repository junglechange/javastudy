package com.jungle.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by Administrator on 2016/10/26 0026.
 */
public class NIOServer {
    private Selector selector;

    public static void main(String[] args) {
        NIOServer server = new NIOServer();
        try{
            server.initServer(8000);
            server.listen();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void initServer(int port) throws Exception {
        // ���һ��ServerSocketͨ��
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        // ����ͨ��Ϊ������
        serverChannel.configureBlocking(false);
        // ����ͨ����Ӧ��ServerSocket�󶨵�port�˿�
        serverChannel.socket().bind(new InetSocketAddress(port));
        // ���һ��ͨ��������
        this.selector = Selector.open();

        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

    }

    public void listen() throws Exception {
        System.out.println("server start");
        while (true) {
            selector.select();
            Iterator<SelectionKey> itr = selector.selectedKeys().iterator();
            while (itr.hasNext()) {
                SelectionKey key = itr.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    SocketChannel channel = serverSocketChannel.accept();
                    channel.configureBlocking(false);
                    channel.write(ByteBuffer.wrap(new String("hello sf").getBytes()));
                    channel.register(selector,SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    // �������ɶ�ȡ��Ϣ:�õ��¼������Socketͨ��
                    SocketChannel channel = (SocketChannel) key.channel();
                    // ������ȡ�Ļ�����
                    ByteBuffer buffer = ByteBuffer.allocate(10);
                    channel.read(buffer);
                    byte[] data = buffer.array();
                    String msg = new String(data).trim();
                    System.out.println("server-receive:"+msg);
                    channel.write(ByteBuffer.wrap(new String("receive").getBytes()));// ����Ϣ���͸�ͻ���
                }
            }
        }
    }
}
