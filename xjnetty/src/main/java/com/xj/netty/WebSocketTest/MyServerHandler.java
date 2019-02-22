package com.xj.netty.WebSocketTest;

import com.xj.netty.APPLog;

import org.w3c.dom.Text;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleStateEvent;

public class MyServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        APPLog.e("收到消息"+msg.text());
        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器时间"+System.currentTimeMillis()));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        APPLog.e("handlerAdded-"+ctx.channel().id().asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        APPLog.e("handlerRemoved-"+ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        APPLog.e("exceptionCaught-"+ctx.channel().id().asLongText());
        ctx.close();
    }
}
