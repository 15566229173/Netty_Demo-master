package com.example.thriftdemo.client;

import com.example.thriftdemo.APPLog;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import thrift.generated.Person;
import thrift.generated.PersonService;

public class ThriftClient {
    public void start() {
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
