package com.xj.netty.doubleScoket.Client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MyChatClient {
    //在这里说明 服务器可以使用handler 或者childHandler
    // 区别在于handler是接收到了事件后交给group里面的第一个参数如bossGroup进行处理
//    childHandler 接收到了事件后交由group的第二个参数workerGroup进行处理，自己本身不处理。
    public void start()   {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new MyChatClientInitializer());
//            ChannelFuture channelFuture = bootstrap.connect("10.79.10.146", 8899).sync();
            ChannelFuture channelFuture = bootstrap.connect("10.79.10.124", 8899).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
