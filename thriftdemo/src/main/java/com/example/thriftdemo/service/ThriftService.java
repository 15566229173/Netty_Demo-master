package com.example.thriftdemo.service;


import com.example.thriftdemo.APPLog;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TSimpleJSONProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

import thrift.generated.PersonService;

public class ThriftService {
    public void start() throws Exception {
        //非阻塞socket
        TNonblockingServerSocket socket=new TNonblockingServerSocket(8899);
        THsHaServer.Args args=new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
        PersonService.Processor<PersonServiceImp> processor=new PersonService.Processor<>(new PersonServiceImp());

        //设定工厂
        args.protocolFactory(new TCompactProtocol.Factory());
        //设置传输格式拥有
//        TBinaryProtocol 二进制格式
//        TCompactProtocol 压缩格式
//        TJSONProtocol json格式
//        TSimpleJSONProtocol 提供json只写协议，生成的文件很容易通过脚本语言解析

        args.transportFactory(new TFastFramedTransport.Factory());
        args.processorFactory(new TProcessorFactory(processor));

        //启动server
        TServer server=new THsHaServer(args);

        APPLog.e("thrift server started");
        //异步非阻塞开启服务
        server.serve();
    }
}
