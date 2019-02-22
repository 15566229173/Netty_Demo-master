package com.example.thriftdemo.service;

import com.example.thriftdemo.APPLog;

import org.apache.thrift.TException;

import thrift.generated.DataException;
import thrift.generated.Person;
import thrift.generated.PersonService;

public class PersonServiceImp implements PersonService.Iface {
    @Override
    public Person getPersonByName(String name) throws DataException, TException {
        APPLog.e("getPersonByName is "+name);
        Person person=new Person();
        person.setName("夏君");
        person.setAge(28);
        person.setMax(true);
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        APPLog.e("savePerson is name"+person.getName()+
        "  age is "+person.getAge()+"  max is "+person.isMax());

    }
}
