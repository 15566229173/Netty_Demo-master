package com.xj.netty.heartbeatScoket;

import com.xj.netty.APPLog;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//        super.userEventTriggered(ctx, evt);
        //如果对象是空闲状态
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            String eventHitn=null;
            switch (event.state()) {
                case ALL_IDLE://所有状态空闲
                    eventHitn="读写空闲";
                    break;
                case READER_IDLE://读空闲
                    eventHitn="读空闲";
                    break;
                case WRITER_IDLE://写空闲
                    eventHitn="写空闲";
                default:
                    break;
            }
            APPLog.e(ctx.channel().remoteAddress()+"超时事件"+eventHitn);
            ctx.channel().closeFuture();
        }
    }
}
