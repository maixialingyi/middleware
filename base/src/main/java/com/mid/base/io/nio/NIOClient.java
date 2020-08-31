package com.mid.base.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class NIOClient {
    private Selector selector = null;
    private SocketChannel socketChannel = null;
    private String nickName = "";
    private Charset charset = Charset.forName("UTF-8");
    private static String USER_EXIST = "系统提示：该昵称已经存在，请换一个昵称";
    private static String USER_CONTENT_SPLIT = "#@#";
    public NIOClient(InetSocketAddress serverAddress){
        try {
            //获取selector
            selector = Selector.open();
            //获取socketChannel
            socketChannel = SocketChannel.open();
            //连接到服务
            socketChannel.connect(serverAddress);
            //设置为非阻塞
            socketChannel.configureBlocking(false);
            //向selector注册感兴趣事件 读数据类型
            socketChannel.register(selector, SelectionKey.OP_READ);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void session(){
        //开辟一个新线程从服务器端读数据
        new Reader().start();
        //开辟一个新线程往服务器端写数据
        new Writer().start();
    }

    private class Reader extends Thread {
        public void run() {
            try {
                //轮询
                while(true) {
                    int readyChannels = selector.select();
                    if(readyChannels == 0) continue;
                    Set<SelectionKey> selectedKeys = selector.selectedKeys();  //可以通过这个方法，知道可用通道的集合
                    Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
                    while(keyIterator.hasNext()) {
                        SelectionKey selectionKey = keyIterator.next();
                        keyIterator.remove();
                        if(selectionKey.isReadable()){//事件类型为读取数据
                            SocketChannel sc = (SocketChannel)selectionKey.channel();
                            ByteBuffer buff = ByteBuffer.allocate(1024);
                            String content = "";
                            while(sc.read(buff) > 0){
                                buff.flip();
                                content += charset.decode(buff);
                            }
                            //继续可以接收读事件
                            selectionKey.interestOps(SelectionKey.OP_READ);
                            System.out.println("Reader 线程读到服务端返回数据："+content);
                        }
                    }
                }
            }
            catch (IOException io){

            }
        }
    }

    private class Writer extends Thread{

        @Override
        public void run() {
            try{
                Scanner scan = new Scanner(System.in);
                String m="-1";
                while(!"t".equalsIgnoreCase(m)){
                    System.out.println("输入发送信息：");
                    m = scan.next();
                    socketChannel.write(charset.encode(m+"--"+String.valueOf(System.currentTimeMillis())));
                }
                //scan.close();
            }catch(Exception e){

            }
        }
    }

    public static void main(String[] args) {
        InetSocketAddress serverAddress = new InetSocketAddress("127.0.0.1", 8081);
        new NIOClient(serverAddress).session();
    }


}
