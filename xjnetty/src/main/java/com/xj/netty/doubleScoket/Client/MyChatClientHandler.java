package com.xj.netty.doubleScoket.Client;

import android.util.Log;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyChatClientHandler extends SimpleChannelInboundHandler<String> {
    private String TAG="MyChatClientHandler";
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Log.e(TAG,msg);
    }
}
