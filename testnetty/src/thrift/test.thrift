namespace java thrift.generated

//基本数据类型通过typedef定义类型转换成自己熟悉的语言类型表达方式
typedef i16 short
typedef i32 int
typedef i64 long
typedef bool boolean
typedef string String

//定义传输的中间类
struct Person{
1:optional String name,
2:optional int age,
3:optional boolean max
}
//定义异常数据
exception DataException{
1:optional String message,
2:optional String callStack,
//thrift不支持日期类型，所以日期用字符串表示。
3:optional String date
}

//service关键字代表服务器端的调用接口
service PersonService{
Person getPersonByName(1:required String name)throws(1:DataException dateException),

void savePerson(1:required Person person)throws(1:DataException dataException)
}