package com.xj.netty.serviceHttp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * netty服务器
 */
public class TestServer {
    public void start() throws InterruptedException {
        //不断从客户端获取连接，但不做处理传给worker处理,都是死循环
        EventLoopGroup bossGroup=new NioEventLoopGroup();//nio线程组
        EventLoopGroup workerGroup=new NioEventLoopGroup();
        try {
            //服务器启动：提供服务器启动相关方法
            ServerBootstrap serverBootstrap=new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)//使用NioServerSocketChannel管道
                    .childHandler(new TestServerInitializer());//消息到来后自己编写的请求处理器
            //为服务器绑定端口
            ChannelFuture channelFuture=serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭管道
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
