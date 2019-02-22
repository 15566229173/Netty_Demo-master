package com.example.thriftdemo.service;


import com.example.thriftdemo.APPLog;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TSimpleJSONProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TFileTransport;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TMemoryInputTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TZlibTransport;

import thrift.generated.PersonService;

public class ThriftService {
    public void start() throws Exception {
        /**
         * thrift支持的服务器模型
         *
         * TSimpleServer 简单的单线程服务模型，常用于测试
         *
         * TThreadPoolServer 多线程服务模型，使用标准阻塞式IO
         *
         * TNonblockingServerSocket 所线程服务模型，使用费阻塞式IO
         *
         * THsHaServer THSHa引入了线程池去处理，器模型吧读写任务放到线程
         * 池去处理；Half-sync/Half-async的处理模式，Half-async是在处理IO
         * 事件上（accept/read/write io）Half-sync用于handler对rpc的同步处理
         *
         */
        TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);

        THsHaServer.Args args = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
        PersonService.Processor<PersonServiceImp> processor = new PersonService.Processor<>(new PersonServiceImp());

        //设定工厂
        args.protocolFactory(new TCompactProtocol.Factory());
        /**
         *设置传输格式拥有
         *TBinaryProtocol 二进制格式 -次使用频率
         *TCompactProtocol 压缩格式  -最多使用
         *TJSONProtocol json格式
         *TSimpleJSONProtocol 提供json只写协议，生成的文件很容易通过脚本语言解析
         */
        args.transportFactory(new TFastFramedTransport.Factory());
        args.processorFactory(new TProcessorFactory(processor));

        //启动server
        //THSHa引入了线程池去处理，器模型吧读写任务放到线程
        //池去处理；Half-sync/Half-async的处理模式，Half-async是在处理IO
        // 事件上（accept/read/write io）Half-sync用于handler对rpc的同步处理
        TServer server = new THsHaServer(args);

        APPLog.e("thrift server started");
        //异步非阻塞开启服务
        server.serve();
    }
}
