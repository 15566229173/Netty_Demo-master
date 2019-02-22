package com.xj.netty.socketTest.client;

import android.util.Log;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Date;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyClientHandler extends SimpleChannelInboundHandler<String> {
    private String TAG="MyClientHandler";
    //客户端或者服务端数据接收数据函数
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Log.e(TAG,ctx.channel().remoteAddress()+"   ;   "+msg);
        ctx.writeAndFlush("from client:"+System.currentTimeMillis());
    }

    //通道建立处于活动状态可以向服务器发送数据
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        Log.e(TAG,"这里有执行");
        ctx.writeAndFlush("来自客户端的问候");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
