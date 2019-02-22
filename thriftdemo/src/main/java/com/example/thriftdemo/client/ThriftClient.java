package com.example.thriftdemo.client;

import com.example.thriftdemo.APPLog;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.io.ByteArrayInputStream;

import thrift.generated.Person;
import thrift.generated.PersonService;

public class ThriftClient {
    public void start() {
        /**
         *数据传输方式
         *TSocket 阻塞式socket
         *TFramedTransport 以frame为单位进行传输，非阻塞在服务中使用
         *TFileTransport 以文件形式进行传输
         *TMemoryInputTransport 将内存用于I/O java实现时内部实际使用了简单的ByteArrayInputStream
         *TZlibTransport 使用zlib进行压缩，与其他的传输方式联合使用。当前无java实现。
         */
        TTransport transport = new TFramedTransport(new TSocket("10.79.10.124", 8899));
        TProtocol protocol=new TCompactProtocol(transport);
        PersonService.Client client=new PersonService.Client(protocol);

        try {
            transport.open();//打开socket
            Person person=client.getPersonByName("张三");
            APPLog.e("ThriftClient is name"+person.getName()+
                    "  age is "+person.getAge()+"  max is "+person.isMax());


            Person per1=new Person();
            per1.setName("夏君");
            per1.setAge(28);
            per1.setMax(true);

            client.savePerson(per1);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            transport.close();
        }
    }
}
