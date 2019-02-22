package com.xj.netty.doubleScoket;

import java.util.Iterator;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyChatHandler extends SimpleChannelInboundHandler<String> {
    //保存channel连接的组,保存所有与服务器建立连接的channel对象
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    //服务器收到任意一个客户端发送过来的消息就会调用channelRead0函数
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        receiveMsg(ctx,msg);
    }

    //服务器端与客户端建立好一个新的连接调用handlerAdded函数
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //同期已经连接的通道
        flushAll(channel, "加入");
        //添加连接到集合
        addChannel(channel);
    }

    //服务器与客户端连接中断
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //这个方法也可以不用写因为netty会自动去识别连接是否断掉
//        removeChannel(channel);
        flushAll(channel, "离开");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        flushAll(channel, "上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        flushAll(channel, "下线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }

    private synchronized void addChannel(Channel channel) {
        channelGroup.add(channel);
    }

    private synchronized void removeChannel(Channel channel) {
        channelGroup.remove(channel);

    }

    private synchronized void flushAll(Channel channel, String hitn) {
        channelGroup.writeAndFlush("[服务器]-" + channel.remoteAddress() + hitn + "\n");
    }
    private synchronized void receiveMsg(ChannelHandlerContext ctx, String msg){
        Channel channel=ctx.channel();
        Iterator<Channel> it = channelGroup.iterator();
        while (it.hasNext()){
            Channel ch=it.next();
            if (ch!=channel) {
                ch.writeAndFlush(channel.remoteAddress() + " 发送消息" + msg+"\n");
            }else {
                ch.writeAndFlush( "[自己] 发送消息" + msg+"\n");
            }
        }
    }
}
