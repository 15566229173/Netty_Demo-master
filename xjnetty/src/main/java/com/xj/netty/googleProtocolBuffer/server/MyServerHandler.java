package com.xj.netty.googleProtocolBuffer.server;

import android.util.Log;

import com.xj.netty.APPLog;
import com.xj.netty.googleProtocolBuffer.PersonData;

import java.util.UUID;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

//SimpleChannelInboundHandler<String>这里的泛型很重要代表了你接收请求数据的类型
public class MyServerHandler extends SimpleChannelInboundHandler<PersonData.MyMessage> {
    private String TAG="MyServerHandler";
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonData.MyMessage msg) throws Exception {

//        ctx.channel().writeAndFlush("from server"+UUID.randomUUID());
        PersonData.MyMessage.DataType dataType=msg.getDataType();
        if (dataType==PersonData.MyMessage.DataType.PersonType){
            APPLog.e(ctx.channel().remoteAddress()+"   ;   "+msg.getPerson());
        }else if (dataType==PersonData.MyMessage.DataType.GogType){
            APPLog.e(ctx.channel().remoteAddress()+"   ;   "+msg.getDog());
        }else {
            APPLog.e(ctx.channel().remoteAddress()+"   ;   "+msg.getCat());
        }

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
