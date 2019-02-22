package com.xj.netty.googleProtocolBuffer.client;

import android.util.Log;

import com.xj.netty.APPLog;
import com.xj.netty.googleProtocolBuffer.PersonData;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyClientHandler extends SimpleChannelInboundHandler<PersonData.MyMessage> {
    private String TAG="MyClientHandler";
    //客户端或者服务端数据接收数据函数
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonData.MyMessage msg) throws Exception {
        Log.e(TAG,ctx.channel().remoteAddress()+"   ;   "+msg);
//        ctx.writeAndFlush("from client:"+System.currentTimeMillis());
    }

    //通道建立处于活动状态可以向服务器发送数据
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        Log.e(TAG,"这里有执行");
        int rd= (int) ((Math.random()*10000)/3);
        rd=rd<1?1:rd;
        rd=rd>3?3:rd;
        APPLog.e("rd="+rd);
        PersonData.MyMessage message=null;
        if (rd==1){
            message=PersonData.MyMessage.newBuilder()
                    .setDataType(PersonData.MyMessage.DataType.PersonType)
                    .setPerson(
                            PersonData.Person.newBuilder().setName("夏君")
                                    .setAddress("com.xj.jia")
                                    .setAge(27)
                                    .setHeight("175").build()
                    ).build();
        }else if (rd==2){
            message=PersonData.MyMessage.newBuilder()
                    .setDataType(PersonData.MyMessage.DataType.GogType)
                    .setDog(
                            PersonData.Dog.newBuilder()
                                    .setAge(28)
                                    .setName("dogname").build()
                    ).build();
        }else {
            message=PersonData.MyMessage.newBuilder()
                    .setDataType(PersonData.MyMessage.DataType.CatType)
                    .setCat(
                            PersonData.Cat.newBuilder()
                                    .setName("毛呀")
                                    .setCity("重庆").build()
                    ).build();
        }

        ctx.channel().writeAndFlush(message);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
