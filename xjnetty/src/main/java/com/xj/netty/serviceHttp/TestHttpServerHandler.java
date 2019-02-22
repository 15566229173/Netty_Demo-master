package com.xj.netty.serviceHttp;

import android.util.Log;

import java.net.URI;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

//定义自己的处理器
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    private String TAG = "TestHttpServerHandler";

    //读取客户端发送过来的请求 并返回客户端
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (msg instanceof HttpRequest) {
            //获取数据请求信息
            HttpRequest httpRequest = (HttpRequest) msg;

            Log.e("httpRequest-name", httpRequest.method().name());
//获取请求的uri
            URI uri = new URI(httpRequest.uri());
            Log.e("httpRequest-uri", uri.getPath());
            if ("/favicon.ico".equals(uri.getPath())) {
                Log.e("TestHttpServerHandler", "请求图片");
                return;
            }
            ByteBuf content = Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_0, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            Log.e("TestHttpServerHandler", "返回了内容");
            ctx.writeAndFlush(response);
//            关闭客户端与服务器连接管道
//            ctx.channel().close();
        }
    }

    //第三执行channelActive 通道处于活跃状态
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Log.e(TAG, "channelActive");
        super.channelActive(ctx);
    }

    //第二个执行注册
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        Log.e(TAG, "channelRegistered");
        super.channelRegistered(ctx);
    }

    //请求过来第一个执行的是handlerAdded
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Log.e(TAG, "handlerAdded");
        super.handlerAdded(ctx);
    }

    //第四请求完成 管道里面没有新的请求调用channelInactive表明管道处于不活跃状态
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Log.e(TAG, "channelInactive");
        super.channelInactive(ctx);
    }

    //最后标记管道处于未注册状态
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        Log.e(TAG, "channelUnregistered");
        super.channelUnregistered(ctx);
    }
}
