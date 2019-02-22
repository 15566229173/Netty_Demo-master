package com.xj.netty.socketTest;

import android.util.Log;

import java.util.UUID;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.SimpleChannelInboundHandler;
//SimpleChannelInboundHandler<String>这里的泛型很重要代表了你接收请求数据的类型
public class MyServerHandler extends SimpleChannelInboundHandler<String> {
    private String TAG="MyServerHandler";
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Log.e(TAG,ctx.channel().remoteAddress()+"   ;   "+msg);
        ctx.channel().writeAndFlush("from server"+UUID.randomUUID());

    }
    //当连接出现异常的情况调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        //出现异常一般需要关闭连接
        ctx.close();
    }
}
