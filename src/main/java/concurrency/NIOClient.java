package concurrency;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class NIOClient {

    private String url;

    private Integer port;

    private volatile Boolean start = true;

    private  Selector selector;

    private  SocketChannel socketChannel;

    public NIOClient(String url,Integer port){
        this.url = url;
        this.port = port;
        //创建选择器
        try {
            selector = Selector.open();
            //打开通道
            socketChannel = SocketChannel.open();
            //如果为 true，则此通道将被置于阻塞模式；
            // 如果为 false，则此通道将被置于非阻塞模式
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        start = false;
    }

    public void connect(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    doConnect();
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                //循环遍历selector
                while(start){
                    try {
                        //阻塞,只有当至少一个注册的事件发生的时候才会继续
                        selector.select();
                        //获取当前有哪些事件可以使用
                        Set<SelectionKey> keys = selector.selectedKeys();
                        //转换为迭代器
                        Iterator<SelectionKey> it = keys.iterator();
                        SelectionKey key = null;
                        while(it.hasNext()){
                            key = it.next();
                            it.remove();
                            try {
                                handleInput(key);
                            } catch (IOException e) {
                                e.printStackTrace();
                                if(key!=null){
                                    key.cancel();
                                    if(key.channel()!=null){
                                        key.channel().close();
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                //selector关闭后会自动释放里面管理的资源
                if(selector!=null){
                    try {
                        selector.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    //具体的事件处理方法
    private void handleInput(SelectionKey key) throws IOException{
        if(key.isValid()){
            //获得关心当前事件的channel
            SocketChannel sc = (SocketChannel)key.channel();
            //连接事件
            if(key.isConnectable()){
                if(sc.finishConnect()){
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }
                else{System.exit(1);}
            }
            //有数据可读事件
            if(key.isReadable()){
                //创建ByteBuffer，并开辟一个1M的缓冲区
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                //读取请求码流，返回读取到的字节数
                int readBytes = sc.read(buffer);
                //读取到字节，对字节进行编解码
                if(readBytes>0){
                    //将缓冲区当前的limit设置为position,position=0，
                    // 用于后续对缓冲区的读取操作
                    buffer.flip();
                    //根据缓冲区可读字节数创建字节数组
                    byte[] bytes = new byte[buffer.remaining()];
                    //将缓冲区可读字节数组复制到新建的数组中
                    buffer.get(bytes);
                    String result = new String(bytes,"UTF-8");
                    System.out.println("accept message:"+result);
                    //doWrite(socketChannel,"哈哈哈");
                }else if(readBytes<0){
                    key.cancel();
                    sc.close();
                }
            }
        }
    }

    //发送消息
    private void doWrite(SocketChannel channel,String request)
            throws IOException {
        //将消息编码为字节数组
        byte[] bytes = request.getBytes();
        //根据数组容量创建ByteBuffer
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        //将字节数组复制到缓冲区
        writeBuffer.put(bytes);
        //flip操作
        writeBuffer.flip();
        //发送缓冲区的字节数组
        channel.write(writeBuffer);
    }

    private  void doConnect() throws IOException {
        /*如果此通道处于非阻塞模式，
        则调用此方法将启动非阻塞连接操作。
        如果立即建立连接，就像本地连接可能发生的那样，则此方法返回true。
        否则，此方法返回false，
        稍后必须通过调用finishConnect方法完成连接操作。*/
        if(socketChannel.connect(new InetSocketAddress("127.0.0.1",8081))){}
        else{
            //连接还未完成，所以注册连接就绪事件，向selector表示关注这个事件
            socketChannel.register(selector,SelectionKey.OP_CONNECT);
        }
    }

    //写数据对外暴露的API
    public void sendMsg(String msg) throws Exception{
        doWrite(socketChannel,msg);
    }


    public static void main(String[] args) throws Exception {
        NIOClient nioClient = new NIOClient("127.0.0.1",8081);
        nioClient.connect();
        while (true){
            Scanner scanner = new Scanner(System.in);
            String text = scanner.next();
            System.out.println(text);
            if("quit".equals(text)){
                break;
            }
            nioClient.sendMsg(text);
        }
    }

}
