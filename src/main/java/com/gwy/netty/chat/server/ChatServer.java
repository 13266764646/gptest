//package com.gwy.netty.chat.server;
//
//import com.gwy.netty.chat.protocol.IMDecoder;
//import com.gwy.netty.chat.protocol.IMEncode;
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.ChannelPipeline;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//import io.netty.handler.codec.http.HttpObjectAggregator;
//import io.netty.handler.codec.http.HttpServerCodec;
//import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
//import io.netty.handler.stream.ChunkedWriteHandler;
//
//public class ChatServer  {
//    private int port = 8080;
//
//    public void start(int port){
//        //主从线程模型的线程池
//        EventLoopGroup bossGroup = new NioEventLoopGroup();
//        EventLoopGroup workerGroup = new NioEventLoopGroup();
//
//        ServerBootstrap  b = new ServerBootstrap();
//        try {
//            b.group(bossGroup, workerGroup)
//                    .channel(NioServerSocketChannel.class)
//                    .childHandler(new ChannelInitializer<SocketChannel>() {
//                        @Override
//                        protected void initChannel(SocketChannel ch) throws Exception {
//                            ChannelPipeline pipeline = ch.pipeline();
//                            //解析自定义协议
//                            pipeline.addLast(new IMDecoder());//inbound
//                            pipeline.addLast(new IMEncode());//outbound
//                            //专门用来处理直接发送IMMassage对象的控制台
//                            pipeline.addLast(new TerminalServerHandler());//inbound
//
//                            //解析http请求
//                            pipeline.addLast(new HttpServerCodec());//outbound
//                            //主要是将同一个http请求或响应的多个消息对象编程一个fullHttpRequest完整的消息对象
//                            pipeline.addLast(new HttpObjectAggregator(64 * 1024));//inbound
//                            //主要用于处理大数流，比如一个1G大小的文件如果你直接传输肯定会撑爆jvm内存的
//                            //，加上这个handler我们就不用考虑这个问题了
//                            pipeline.addLast(new ChunkedWriteHandler());//inbound outbound
//                            //处理web页面，解析http协议的
//                            pipeline.addLast(new HttpServerHandler());//inbound
//
//                            /**解析webSocket请求**/
//                            pipeline.addLast(new WebSocketServerProtocolHandler("/im"));//inboud
//                            //用来处理websocket通讯协议
//                            pipeline.addLast(new WebsocketServerHandler());//inbound
//                        }
//                    });
//            ChannelFuture f = b.bind(this.port).sync();
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            workerGroup.shutdownGracefully();
//            bossGroup.shutdownGracefully();
//        }
//    }
//
//    public void start(){
//        start(this.port);
//    }
//
//    public static void main(String[] args) {
//        if(args.length > 0){
//            new ChatServer().start(Integer.valueOf(args[0]));
//        }else{
//            new ChatServer().start();
//        }
//    }
//}
