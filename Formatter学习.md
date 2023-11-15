
**整理一下关于 Formatter 的用法**

实例化一个Formatter

```java
StringBuilder builder = new StringBuilder();
//builder 用来存储格式化后的字符串
Formatter formatter = new Formatter(builder, Locale.CHINA);
```

format 方法，第一个参数是格式字符串，用来格式化后面的一个或多个参数。后面是一个可变参数列表。

```java
public Formatter format(String format, Object ... args) {
    return format(l, format, args);
}
```

格式字符串包含固定文本能够嵌入一个或多个格式说明符。例如：

```java
  Calendar calendar = Calendar.getInstance();
  String s = String.format("Duke's Birthday: %1$tm %1$te,%1$tY", calendar);
```

第一个参数是格式字符串，包含三个格式说明符，`%1$tm %1$te,%1$tY`，用来表明后 面的参数 calendar 该怎样被处理以及该插入到输出文本的什么位置。格式化字符串剩余 的部分是固定的文本包括`Duke's Birthday: 和其他的空格以及标点符号。`

**String 的format方法内调用的就是Formatter 的format方法。**

```java
//内部调用Formatter的format方法
public static String format(String format, Object... args) {
    return new Formatter().format(format, args).toString();
}
```

**通用类型，字符类型和数字类型的格式说明符语法如下：**

```
%[argument_index$][flags][width][.precision]conversion
```

- argument_index 选项是一个十进制整数，用来指示要被格式化的参数在参数列表中的位置。第一个参数用`“1$”`表示，第二个参数用`“2$”`表示，以此类推。也可以使用另外一种方式表示参数位置，就是使用‘<’,表示使用前一个格式化说明符指向的参数。


```
//下面两行代码表示都使用第一个参数。c
 Calendar c = ...;
 String s1 = String.format("Duke's Birthday: %1$tm %1$te,%1$tY", c);
 String s2 = String.format("Duke's Birthday: %1$tm %<te,%<tY", c);
```

- flags 选项是一组用来修改输出格式的字符集，flags 合法的取值依赖conversion选项。
- width 选项是一个非负的整数，表示要写入输出的最少字符数。
- precision 选项是一个非负的整数，通常用来限制字符数，具体的行为依赖conversion选项。
- 必选项conversion 是一个字符指示参数应该怎样被格式化。**合法的conversion字符取值依赖于要被格式化的参数的数据类型**。

**用来格式化日期和时间类型的格式说明符语法如下，和上面的语法相比少了精度选项。**

```
%[argument_index$][flags][width]conversion
```

- argument_index ，flags，width 选项和上面的定义一样。
- 必选项conversion 是一个两个字符的序列，第一个字符必须是't' or 'T'，第二个字符用来格式化参数。

**没有参数对应的格式说明符具有以下语法：**

```
%[flags][width]conversion
```

- flags，width 选项和上面的定义一样。
- 必选项conversion是一个字符用来指示内容应该如何被插入到输出中。



```
//没有参数对应格式[argument_index$]，默认每一个百分号后面的格式化字符串对应一个参数。
//%tm 格式化 calendar1，%te 格式化 calendar2，%tR 格式化 calendar3
Calendar calendar1 = Calendar.getInstance();
Calendar calendar2 = Calendar.getInstance();
Calendar calendar3 = Calendar.getInstance();
String.format("Duke's Birthday: %tm,%te,%tR", calendar1,calendar2,calendar3)
```


##Conversions 的分类

>1：General 可用于任何参数类型
2：Character 可用于代表Unicode字符的基本类型：char, Character, byte, Byte, short,and Short；
2.1 当 isValidCodePoint(int) returns true的时候(比如65 代表'A',97 代表'a')，
这类conversion也可用于int and Integer类型
3:Numeric
3.1:Integral 整型的，可用于java的整型，byte, Byte, short, Short, int and Integer, long, Long, and BigInteger
3.2：Floating Point浮点型 用于java 的浮点型 float, Float, double, Double, and BigDecimal
4：Date/Time 可用于java中能够编码date或者time的类型：long, Long, Calendar, and Date
5：Percent ：产生一个文本'%' ('\u0025')
6:Line Separator 换行


**下表总结了一些转换字符集的用法**

|Conversion|参数分类|描述|
|-------------|
|'b','B'|general|如果要被格式化的参数是null,输出结果是false,如果参数是一个boolean或者Boolean类型的数据，输出true或者false，其他情况返回true|
|'h','H'|general|如果要被格式化的参数是null,输出结果是null,其他情况输出Integer.toHexString(arg.hashCode())|
|'s','S'|general|如果要被格式化的参数是null,输出null,如果参数实现了Formattable接口（如果要自定义Formatter的‘s’转换说明符的功能，需要实现Formattable接口），那么会调用arg.formatTo方法，除此之外，返回结果是arg.toString()|
|'c','C'|character|结果是一个Unicode字符|
|'d'|integral|结果是一个十进制整数|
|'o'|integral|结果是一个8进制整数|
|'x','X'|integral|结果是一个16进制整数|
|'e','E'|floating point|结果是一个用科学计数法表示的十进制数字|
|'f'|floating point|结果是一个十进制数字|
|'g','G'|floating point|根据参数的精度和四舍五入后的值来选择使用科学计数法或者十进制格式化|
|'a','A'|floating point|结果是一个十进制数字|
|'f'|floating point|结果是一个用基数和指数表示的十六进制浮点数|
|'t','T'|date/time|日期和时间转换字符集的第一个字符，下面细说|
|'%'|percent|结果是一个%|
|'n'|line separator|结果是一个换行符|

#### Date/Time 类型的Conversions，开始第一个字符是't'或者 'T'，然后才能选择下表中的一个。

|Conversions|描述|
|--------|----------------|
|'H'|24小时制，结果被格式化成两位数，如果必要以0开头，比如07，（范围00-23）|
|'I'|12小时制，结果被格式化成两位数，如果必要以0开头，比如07，（范围01-12）|
|'k'|24小时制，范围（0-23）|
|'l'|12小时制，范围（1-12）|
|'M'|一小时内的分钟数，会被格式化为两位数，必要的话以0开头，范围（00-59）|
|'S'|一分钟内的秒数，会被格式化为两位数，必要的话以0开头，（范围00-60,60是一个特殊的值，要求支持描述跳变）|
|'L'|一秒内的毫秒数，结果被格式化成3位数字，必要的话以0开头范围（000-999）|
|'N'|一秒内的纳秒数，会被格式化成9位数字，必要的话以0开头，范围（000000000-999999999）|
|'p'|特定语言环境下，早上或者下午的小写标记，‘am’或者‘pm’，可以使用转化前缀‘T’，输出大写的‘AM’,或者‘PM’|
|'z'|RFC 822样式的数字时区不同于GMT，例如 -0800。 该值将根据夏令时进行调整。因为对于long, Long, and Date，java虚拟机实例使用默认的时区。|
|'Z'|表示时区缩写的字符串。该值将根据夏令时进行调整。因为对于long, Long, and Date，java虚拟机实例使用默认的时区。Formatter的语言环境会替代要被各式化的参数的语言环境。|
|'s'|从UTC（协调世界时）1970年1月1日00:00:00开始的开始的秒数，范围Long.MIN_VALUE/1000到Long.MAX_VALUE/1000.|
|'Q'|从UTC（协调世界时）1970年1月1日00:00:00开始的开始的毫秒数，范围从 Long.MIN_VALUE/1000到Long.MAX_VALUE/1000.精度受操作系统或者硬件影响。|

#### 下列的conversion 字符用来格式化日期

|Conversions|描述|
|--------|----------------|
|'B'|特定语言环境下的月份的全名，例如"January", "February"|
|'b'|特定语言环境下的月份的简称，例如"Jan", "Feb".|
|'h'|特定语言环境下的月份的简称，例如"Jan", "Feb".|
|'A'|特定语言环境下的一周的某一天的全名，例如"Sunday", "Monday"|
|'a'|特定语言环境下的一周的某一天的简称，例如"Sun", "Mon"|
|'C'|四位的年数除以100，格式化成两位数，如果有必要的话，以0开头。范围（00 - 99）|
|'Y'|年，被格式化成至少4位整数，必要的话，以0开头。例如0092就是格林尼治时间的公元92年。|
|'y'|年的最后两位，如果必要，以0开头（范围00-99）|
|'j'|一年的某一天，被格式化成3位数，范围（0-366）|
|'m'|月，被格式化成两位数，必要的话以0开头，范围（01,13）|
|'d'|一个月中的某一天，被格式化成两位，如果必要的话，以0开头，范围（01,31）|
|'e'|一个月中的某一天，被格式化成两位 范围（1 - 31）|

#### 下面的conversion 字符串是格式化date/time的组合。
|Conversions|描述|
|--------|----------------|
|'R'|把时间格式化成24小时的格式，和"%tH:%tM" 效果一样|
|'T'|把时间格式化成24小时的格式，和 "%tH:%tM:%tS" 效果一样|
|'r'|把时间格式化成24小时的格式， 和"%tI:%tM:%tS %Tp"效果一样早晨或下午标记（'％Tp'）是依赖于区域设置的。|
|'F'|被格式化成 ISO 8601完整格式的日期 等价于 "%tY-%tm-%td".|
|'D'|日期格式化，和 "%tm/%td/%ty"效果一样，注意这里y是小写的|
|'c'|日期格式化，和 "%ta %tb %td %tT %tZ %tY"效果一样，例如 "Sun Jul 20 16:17:00 EDT 1969"|


**注意**

```
'B', 'H', 'S', 'C', 'X', 'E', 'G', 'A', and 'T'
```
这几个converion 大写小写都可以，输出结果也会对应输出大小写。

#### 下表是可用的flags。y 表示对应的参数类型可用，no 表示对应的参数类型不可用。
|Flag|General|Character|Integral|Float Point|Date/Time|描述|
|-|-|-|-|-|-|
|'-'|y|y|y|y|y|输出结果左对齐|
|'#'|y1|no|y3|y|no|输出结果和conversion相关的变换格式|
|'+'|no|no|y4|y|no|输出结果包括一个符号|
|' '（空格）|no|no|y4|y|no|如果输出结果是一个正数，结果前面会有一个空格|
|'0'|no|no|y|y|no|如果输出结果的宽度小于指定的宽度，用0来补齐|
|','（逗号）|no|no|y2|y5|no|输出结果包括特定语言环境下的组分隔符，类似这样，格式化100000，输出100,000|
|'('|no|no|y4|y5|no|输出结果会把负值用括号包起来，类似这样，格式化-100，输出(100)|

#### 注意
1. y1 ，结果取决于Formattable的定义。(可以通过实现Formattable接口自定义conversion.)
2. y2后面的conversion 必须是‘d’.
3. y3后面的conversion 只能是 ‘o’,'x','X'。
4. y4后面的conversion 可以使用于BigInteger的'd', 'o', 'x', and 'X' ，或者是用于 byte, Byte, short, Short, int and Integer, long, and Long类型的‘d’。
5. y5后面的conversion 只能是 'e', 'E', 'f', 'g'。

### Width

width 是输出结果最少的字符数。不能用于换行符。

### Precision(精度)

对于通用类型Conversions ，精度是输出结果最少的字符数。
**对于浮点型Conversions** ：'e', 'E', and 'f'，精度表示小数点后保留几位小数；如果conversion是 'g' or 'G'，精度表示四舍五入以后，结果的总位数；如果conversion是“a”或“A”，则不能指定精度。

**光说不练假把式一个个试试看结果。**

```
 StringBuilder builder = new StringBuilder();
 Formatter formatter = new Formatter(builder, Locale.CHINA);

 formatter.format("%1$b %2$b %3$b", null, true, 124);
 Log.e(tag, builder.toString());//输出结果MainActivity: false true true

builder.delete(0, builder.length());
formatter.format("%1h %2h %3h", null, 64);
Log.e(tag, builder.toString());//输出结果 MainActivity: null 40

builder.delete(0, builder.length());
formatter.format("%1$s , %2$s", null, 23);
Log.e(tag, builder.toString());//输出MainActivity: null , 23

builder.delete(0, builder.length());
formatter.format("%1$c %2$c %3$c", 'c',65,97);
Log.e(tag, builder.toString());//输出MainActivity: c A a

builder.delete(0, builder.length());
formatter.format("十进制：%1$d 八进制：%1$#o 十六进制：%1$#x", 127);
Log.e(tag, builder.toString());//输出MainActivity: 十进制：127 八进制：0177 十六进制：0x7f

/*如果使用‘#’这个flag,八进制的输出结果前面会有一个0，十六进制输出结果前面有一个0x对
于八进制和十六进制，如果 要被各式化的参数是一个负数，那么结果会加上一个2 的n次方的
值从而成为一个无符号数， n就是对应数据类型的位数，下面的例子中127和-127都是32位的。
*/

builder.delete(0, builder.length());
formatter.format("十进制：%1$d 八进制：%1$#o 十六进制：%1$#x", -127);
Log.e(tag, builder.toString());//输出十进制：-127 八进制：037777777601 十六进制：0xffffff81

 builder.delete(0, builder.length());
 formatter.format("%1$d %2$d %1$+d %2$+d %1$ d %2$ d %1$08d %2$08d %1$,8d %2$,8d %1$(8d %2$(8d", 12700,-12700);
  Log.e(tag, builder.toString());
/*输出MainActivity: 12700 -12700 +12700 -12700  12700 -12700 00012700 -0012700   12,700  -12,700    12700  (12700)*/

  /**-------------BigInteger 转化成八进制或者十六进制-----------------------*/
  builder.delete(0, builder.length());
  BigInteger bigInteger2 = new BigInteger("-64", 10);
  BigInteger bigInteger1 = new BigInteger("64", 10);
  formatter.format("%1$o %2$o,%1$+#x %2$+#x,%1$010x %2$010x", bigInteger1, bigInteger2);
  Log.e(tag, builder.toString());
  //输出MainActivity: 100 -100,+0x40 -0x40,0000000040 -000000040
  /**---------------------------------------------------------*/
 
 /**--------------float, Float, double and Double,BigDecimal----------------------*/

 //关于flag 'g',默认精度是6.要被各式化的数字的绝对值 m;
 //如果0.0001<=m<=10的precision次方，就把结果格式化成十进制格式 
 //如果m<0.0001 or m>10的precision次方，就把结果用科学计数法表示。
 
builder.delete(0, builder.length());
float a = 1220000;
float b = 100;
formatter.format("%1$e %1$.4e,%1$g %1$.4g,%1$.3f", a);
Log.e(tag, builder.toString());
builder.delete(0, builder.length());
formatter.format("%1$e %1$.4e,%1$g %1$.4g,%1$.3f", b);
Log.e(tag, builder.toString());
builder.delete(0, builder.length());
formatter.format("%1$e %1$.4e,%1$g %1$.4g,%1$.3f", -1220000f);
Log.e(tag, builder.toString());
builder.delete(0, builder.length());
formatter.format("%1$e %1$.4e,%1$g %1$.4g,%1$.3f", -100f);
Log.e(tag, builder.toString());
  /**
MainActivity: 1.220000e+06 1.2200e+06,1.22000e+06 1.220e+06,1220000.000
MainActivity: 1.000000e+02 1.0000e+02,100.000 100.0,100.000
MainActivity: -1.220000e+06 -1.2200e+06,-1.22000e+06 -1.220e+06,-1220000.000
MainActivity: -1.000000e+02 -1.0000e+02,-100.000 -100.0,-100.000*/

//关于BigDecimal
		BigDecimal bigDecimal1 = new BigDecimal("1220000");
        BigDecimal bigDecimal2 = new BigDecimal("-1220000");
        BigDecimal bigDecimal3 = new BigDecimal("0");
        builder.delete(0, builder.length());
        formatter.format("%1$e %1$.4e,%1$g %1$.4g,%1$.3f", bigDecimal1);
        Log.e(tag, builder.toString());
        builder.delete(0, builder.length());
        formatter.format("%1$e %1$.4e,%1$g %1$.4g,%1$.3f", bigDecimal2);
        Log.e(tag, builder.toString());
        builder.delete(0, builder.length());
        formatter.format("%1$e %1$.4e,%1$g %1$.4g,%1$.3f", bigDecimal3);
        Log.e(tag, builder.toString());

//输出
/**MainActivity: 1.220000e+06 1.2200e+06,1.22000e+06 1.220e+06,1220000.000
   MainActivity: -1.220000e+06 -1.2200e+06,-1.22000e+06 -1.220e+06,-1220000.000
   MainActivity: 0.000000e+00 0.0000e+00,0.00000 0.000,0.000*/


  /*-------------------------------------------------------------*/
```

### Date/Time

conversion 第一个字符必须是 ‘t’or 'T'

```
		Date date = new Date();
        long time = date.getTime();

        builder.delete(0, builder.length());
        formatter.format("%1$tH:%1$tM:%1$tS %1$tp", time);
        Log.e(tag, builder.toString());//MainActivity: 13:37:37 下午

        builder.delete(0, builder.length());
        formatter.format("%1$tR", time);
        Log.e(tag, builder.toString());//13:37

        builder.delete(0, builder.length());
        formatter.format("%1$tT", time);
        Log.e(tag, builder.toString());//13:37:37

        builder.delete(0, builder.length());
        formatter.format("%1$tr", time);
        Log.e(tag, builder.toString());//01:37:37 下午

        builder.delete(0, builder.length());
        formatter.format("%1$tY-%1$tm-%1$td %1$tB %1$ta", date);
        Log.e(tag, builder.toString());//2017-01-14 一月 周六

        builder.delete(0, builder.length());
        formatter.format("%1$tD", date);
        Log.e(tag, builder.toString());//01/14/17

        builder.delete(0, builder.length());
        formatter.format("%1$tF", date);
        Log.e(tag, builder.toString());//2017-01-14

        builder.delete(0, builder.length());
        formatter.format("%1$tc", date);
        Log.e(tag, builder.toString());//周六 1月 14 13:37:37 GMT+08:00 2017
```

### 测试百分号

```java
public void testPercent() {
    //输出 12%100
    String output = String.format("%1$d%%100", 12);
    System.out.println(output);
}
```

### 测试换行

```java
public void testLineSeparator() {
    /*
     * Hello world
     * good afternoon
     * */
    String output = String.format("%1$s%ngood afternoon", "Hello world");
    System.out.println(output);
}
```

参考链接：
【1】https://developer.android.google.cn/reference/java/util/Formatter.html