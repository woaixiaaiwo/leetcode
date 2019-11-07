package concurrency;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Copyright (C), 上海秦苍信息科技有限公司
 * <p>
 * NIO服务端
 *
 * @author wub
 * @version NIOServer, v1.0 2019/11/6 20:29
 */
public class NIOServer {

    private static Selector selector;

    private static List<SocketChannel> PUSH_LIST = new ArrayList<>();

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        SocketAddress local = new InetSocketAddress("127.0.0.1",8081);
        serverSocketChannel.bind(local);
        serverSocketChannel.configureBlocking(false);
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        for(SocketChannel socketChannel:PUSH_LIST){
                            doWrite(socketChannel,"你好呀");
                        }
                        Thread.sleep(10000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }

            }
        });
        while(true){
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = selectionKeys.iterator();
            SelectionKey key = null;
            while(it.hasNext()){
                key = it.next();
                it.remove();
                try{
                    handleInput(key);
                }catch(Exception e){
                    if(key != null){
                        key.cancel();
                        if(key.channel() != null){
                            key.channel().close();
                        }
                    }
                }
            }
        }
    }

    private static void handleInput(SelectionKey key) throws IOException{
        if(key.isValid()){
            //处理新接入的请求消息
            if(key.isAcceptable()){
                //获得关心当前事件的channel
                ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
                //通过ServerSocketChannel的accept创建SocketChannel实例
                //完成该操作意味着完成TCP三次握手，TCP物理链路正式建立
                SocketChannel sc = ssc.accept();
                System.out.println("======socket channel 建立连接" );
                //设置为非阻塞的
                sc.configureBlocking(false);
                //连接已经完成了，可以开始关心读事件了
                sc.register(selector,SelectionKey.OP_READ);
                PUSH_LIST.add(sc);
            }
            //读消息
            if(key.isReadable()){
                System.out.println("======socket channel 数据准备完成，" +
                        "可以去读==读取=======");
                SocketChannel sc = (SocketChannel) key.channel();
                //创建ByteBuffer，并开辟一个1M的缓冲区
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                //读取请求码流，返回读取到的字节数
                int readBytes = sc.read(buffer);
                //读取到字节，对字节进行编解码
                if(readBytes>0){
                    //将缓冲区当前的limit设置为position=0，
                    // 用于后续对缓冲区的读取操作
                    buffer.flip();
                    //根据缓冲区可读字节数创建字节数组
                    byte[] bytes = new byte[buffer.remaining()];
                    //将缓冲区可读字节数组复制到新建的数组中
                    buffer.get(bytes);
                    String message = new String(bytes,"UTF-8");
                    System.out.println("服务器收到消息：" + message);
                    //处理数据
                    String result = message ;
                    //发送应答消息
                    doWrite(sc,result);
                }
                //链路已经关闭，释放资源
                else if(readBytes<0){
                    key.cancel();
                    sc.close();
                }
            }

        }
    }
    //发送应答消息
    private static void doWrite(SocketChannel channel,String response)
            throws IOException {
        //将消息编码为字节数组
        byte[] bytes = response.getBytes();
        //根据数组容量创建ByteBuffer
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        //将字节数组复制到缓冲区
        writeBuffer.put(bytes);
        //flip操作
        writeBuffer.flip();
        //发送缓冲区的字节数组
        channel.write(writeBuffer);
    }


}
