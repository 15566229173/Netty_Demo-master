package com.xj.netty.googleProtocolBuffer;

import com.google.protobuf.InvalidProtocolBufferException;
import com.xj.netty.APPLog;

/**
 * 测试使用protcol对一个类对象进行编解码
 */
public class PtotcolBufferTest {
    public void start(){
        //构建数据
       DataInfo.Student student= DataInfo.Student.newBuilder()
                .setName("夏君")
                .setAddress("com.xj")
                .setAge(28).build();

        //构建byte序列化数组
        byte[] studentByteArray=student.toByteArray();

        //反序列化byte[]数组
        try {
            DataInfo.Student student1=DataInfo.Student.parseFrom(studentByteArray);
            APPLog.e(student1);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }
}
