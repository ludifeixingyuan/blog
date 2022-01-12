## JVM相关

### JVM 相关知识体系

#### 知识体系

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/jvm/jvm-overview.png)

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/jvm/java-jvm-overview.png)

#### 学习要点

> **A. Java进阶 - JVM相关 知识体系**： 首先按照上述`学习思路`，理解总体知识点在全局上与知识体系之间的对应关系。
>
> - JVM 相关知识体系
>
> **B. Java进阶 - JVM相关 类加载**： 然后理解类字节码和类的加载机制。
>
> - JVM基础 - 类字节码详解
>   - 源代码通过编译器编译为字节码，再通过类加载子系统进行加载到JVM中运行
> - JVM基础 - Java 类加载机制
>   - 这篇文章将带你深入理解Java 类加载机制
>
> **C. Java进阶 - JVM相关 内存结构**： 因为类字节码是加载到JVM内存结构中的，所以紧接着理解JVM内存结构。
>
> - JVM基础 - JVM内存结构
>   - 本文主要对JVM 内存结构进行讲解，注意不要和Java内存模型混淆了
>
> **D. Java进阶 - JVM相关 JMM**： 然后通过理解JVM与硬件之间的联系，理解Java 通过其内存模型保证数据线程安全等，这是JVM在并发上底层的支持。
>
> - JVM基础 - Java 内存模型引入
>   - 很多人都Java内存模型和JVM内存结构，以及Java内存模型与物理内存之间的关系。本文从堆栈角度引入JMM，然后介绍JMM和物理内存之间的关系, 为后面`JMM详解`, `JVM 内存结构详解`, `Java 对象模型详解`等铺垫。
>
> - JVM基础 - Java 内存模型详解
>   - 本文主要转载自 Info 上[深入理解Java内存模型  (opens new window)](https://www.infoq.cn/article/java_memory_model/), 作者程晓明。这篇文章对JMM讲的很清楚了，大致分三部分：重排序与顺序一致性；三个同步原语（lock，volatile，final）的内存语义，重排序规则及在处理器中的实现；java 内存模型的设计，及其与处理器内存模型和顺序一致性内存模型的关系
>
> **E. Java进阶 - JVM相关 GC**： 再者理解下Java GC机制，如何回收内存等。
>
> - JVM基础 - Java 垃圾收集
>   - 垃圾收集主要是针对堆和方法区进行
>
> **F. Java进阶 - JVM相关 排错调优**： 最后围绕着调试和排错，分析理解JVM调优参数，动态字节码技术及动态在线调试的原理；学会使用常用的调工具和在线动态调试工具等。
>
> 著作权归https://pdai.tech所有。 链接：https://www.pdai.tech/md/java/jvm/java-jvm-x-overview.html
>
> ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/jvm/java-jvm-debug.png)
>
> - 调试排错 - JVM 调优参数
>   - 本文对JVM涉及的常见的调优参数和垃圾回收参数进行阐述
> - 调试排错 - Java OOM 分析
>   - 本文以两个简单的例子(`堆内存溢出`和`MetaSpace (元数据) 内存溢出`）解释Java 内存溢出的分析过程
> - 调试排错 - Java问题排查：Linux命令
>   - Java 在线问题排查主要分两篇：本文是第一篇，通过linux常用命令排查
> - 调试排错 - Java问题排查：工具单
>   - Java 在线问题排查主要分两篇：本文是第二篇，通过java调试/排查工具进行问题定位
> - 调试排错 - 9种常见的CMS GC问题分析与解决
>   - 本文整理自[美团技术团队  (opens new window)](https://tech.meituan.com/2020/11/12/java-9-cms-gc.html), 这篇文章将可以帮助你构建CMS GC相关问题解决的知识体系，分享给你。
> - 调试排错 - Java动态调试技术原理
>   - 本文转载自 美团技术团队胡健的[Java 动态调试技术原理及实践  (opens new window)](https://tech.meituan.com/2019/11/07/java-dynamic-debugging-technology.html), 通过学习java agent方式进行动态调试了解目前很多大厂开源的一些基于此的调试工具。
> - 调试排错 - Java应用在线调试Arthas
>   - 本文主要介绍Alibaba开源的Java诊断工具，开源到现在已经1.7万个点赞了，深受开发者喜爱。具体解决在线问题，比如：
>   - 这个类从哪个 jar 包加载的? 为什么会报各种类相关的 Exception?
>   - 我改的代码为什么没有执行到? 难道是我没 commit? 分支搞错了?
>   - 遇到问题无法在线上 debug，难道只能通过加日志再重新发布吗?
>   - 线上遇到某个用户的数据处理有问题，但线上同样无法 debug，线下无法重现！
>   - 是否有一个全局视角来查看系统的运行状况?
>   - 有什么办法可以监控到JVM的实时运行状态?
> - 调试排错 - 使用IDEA本地调试和远程调试
>   - Debug用来追踪代码的运行流程，通常在程序运行过程中出现异常，启用Debug模式可以分析定位异常发生的位置，以及在运行过程中参数的变化；并且在实际的排错过程中，还会用到Remote Debug。IDEA 相比 Eclipse/STS效率更高，本文主要介绍基于IDEA的Debug和Remote Debug的技巧。

### JVM 基础 - 类字节码详解

> 源代码通过编译器编译为字节码，再通过类加载子系统进行加载到JVM中运行。

#### 多语言编译为字节码在JVM运行

计算机是不能直接运行java代码的，必须要先运行java虚拟机，再由java虚拟机运行编译后的java代码。这个编译后的java代码，就是本文要介绍的java字节码。

<img src="https://tva1.sinaimg.cn/large/008i3skNly1gyavaqqxejj31660j8wgn.jpg" alt="image-20220112142227723" style="zoom:50%;" />

#### Java字节码文件

- Class文件的结构属性

  在理解之前先从整体看下java字节码文件包含了哪些类型的数据：

  <img src="https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/jvm/java-jvm-class-2.png" alt="img" style="zoom: 67%;" />

- 从一个例子开始

  下面以一个简单的例子来逐步讲解字节码。

  ````java
  //Main.java
  public class Main {
      
      private int m;
      
      public int inc() {
          return m + 1;
      }
  }
  ````

  通过命令(javac Main.java), 可以在当前所在路径下生成一个Main.class文件。

  以文本的形式打开生成的class文件，内容如下:

  ````sh
  cafe babe 0000 0034 0013 0a00 0400 0f09
  0003 0010 0700 1107 0012 0100 016d 0100
  0149 0100 063c 696e 6974 3e01 0003 2829
  5601 0004 436f 6465 0100 0f4c 696e 654e
  756d 6265 7254 6162 6c65 0100 0369 6e63
  0100 0328 2949 0100 0a53 6f75 7263 6546
  696c 6501 0009 4d61 696e 2e6a 6176 610c
  0007 0008 0c00 0500 0601 0010 636f 6d2f
  7268 7974 686d 372f 4d61 696e 0100 106a
  6176 612f 6c61 6e67 2f4f 626a 6563 7400
  2100 0300 0400 0000 0100 0200 0500 0600
  0000 0200 0100 0700 0800 0100 0900 0000
  1d00 0100 0100 0000 052a b700 01b1 0000
  0001 000a 0000 0006 0001 0000 0003 0001
  000b 000c 0001 0009 0000 001f 0002 0001
  0000 0007 2ab4 0002 0460 ac00 0000 0100
  0a00 0000 0600 0100 0000 0800 0100 0d00
  0000 0200 0e
  ````

  - 文件开头的4个字节("cafe babe")称之为 `魔数`，唯有以"cafe babe"开头的class文件方可被虚拟机所接受，这4个字节就是字节码文件的身份识别。

  - 0000是编译器jdk版本的次版本号0，0034转化为十进制是52,是主版本号，java的版本号从45开始，除1.0和1.1都是使用45.x外,以后每升一个大版本，版本号加一。也就是说，编译生成该class文件的jdk版本为1.8.0。

  通过java -version命令稍加验证, 可得结果。

  ````java
  Java(TM) SE Runtime Environment (build 1.8.0_131-b11)
  Java HotSpot(TM) 64-Bit Server VM (build 25.131-b11, mixed mode)
  ````

  继续往下是常量池... 知道是这么分析的就可以了，然后我们通过工具反编译字节码文件继续去看。

- 反编译字节码文件

  > 使用到java内置的一个反编译工具javap可以反编译字节码文件, 用法: `javap <options> <classes>`

  其中`<options>`选项包括:

  ````sh
    -help  --help  -?        输出此用法消息
    -version                 版本信息
    -v  -verbose             输出附加信息
    -l                       输出行号和本地变量表
    -public                  仅显示公共类和成员
    -protected               显示受保护的/公共类和成员
    -package                 显示程序包/受保护的/公共类
                             和成员 (默认)
    -p  -private             显示所有类和成员
    -c                       对代码进行反汇编
    -s                       输出内部类型签名
    -sysinfo                 显示正在处理的类的
                             系统信息 (路径, 大小, 日期, MD5 散列)
    -constants               显示最终常量
    -classpath <path>        指定查找用户类文件的位置
    -cp <path>               指定查找用户类文件的位置
    -bootclasspath <path>    覆盖引导类文件的位置
  ````

  输入命令`javap -verbose -p Main.class`查看输出内容:

  ````sh
  Classfile /E:/JavaCode/TestProj/out/production/TestProj/com/rhythm7/Main.class
    Last modified 2018-4-7; size 362 bytes
    MD5 checksum 4aed8540b098992663b7ba08c65312de
    Compiled from "Main.java"
  public class com.rhythm7.Main
    minor version: 0
    major version: 52
    flags: ACC_PUBLIC, ACC_SUPER
  Constant pool:
     #1 = Methodref          #4.#18         // java/lang/Object."<init>":()V
     #2 = Fieldref           #3.#19         // com/rhythm7/Main.m:I
     #3 = Class              #20            // com/rhythm7/Main
     #4 = Class              #21            // java/lang/Object
     #5 = Utf8               m
     #6 = Utf8               I
     #7 = Utf8               <init>
     #8 = Utf8               ()V
     #9 = Utf8               Code
    #10 = Utf8               LineNumberTable
    #11 = Utf8               LocalVariableTable
    #12 = Utf8               this
    #13 = Utf8               Lcom/rhythm7/Main;
    #14 = Utf8               inc
    #15 = Utf8               ()I
    #16 = Utf8               SourceFile
    #17 = Utf8               Main.java
    #18 = NameAndType        #7:#8          // "<init>":()V
    #19 = NameAndType        #5:#6          // m:I
    #20 = Utf8               com/rhythm7/Main
    #21 = Utf8               java/lang/Object
  {
    private int m;
      descriptor: I
      flags: ACC_PRIVATE
  
    public com.rhythm7.Main();
      descriptor: ()V
      flags: ACC_PUBLIC
      Code:
        stack=1, locals=1, args_size=1
           0: aload_0
           1: invokespecial #1                  // Method java/lang/Object."<init>":()V
           4: return
        LineNumberTable:
          line 3: 0
        LocalVariableTable:
          Start  Length  Slot  Name   Signature
              0       5     0  this   Lcom/rhythm7/Main;
  
    public int inc();
      descriptor: ()I
      flags: ACC_PUBLIC
      Code:
        stack=2, locals=1, args_size=1
           0: aload_0
           1: getfield      #2                  // Field m:I
           4: iconst_1
           5: iadd
           6: ireturn
        LineNumberTable:
          line 8: 0
        LocalVariableTable:
          Start  Length  Slot  Name   Signature
              0       7     0  this   Lcom/rhythm7/Main;
  }
  SourceFile: "Main.java"
  ````

- 字节码文件信息

  开头的7行信息包括:Class文件当前所在位置，最后修改时间，文件大小，MD5值，编译自哪个文件，类的全限定名，jdk次版本号，主版本号。

  然后紧接着的是该类的访问标志：ACC_PUBLIC, ACC_SUPER，访问标志的含义如下:

  | 标志名称       | 标志值 | 含义                                                         |
  | -------------- | ------ | ------------------------------------------------------------ |
  | ACC_PUBLIC     | 0x0001 | 是否为Public类型                                             |
  | ACC_FINAL      | 0x0010 | 是否被声明为final，只有类可以设置                            |
  | ACC_SUPER      | 0x0020 | 是否允许使用invokespecial字节码指令的新语义．                |
  | ACC_INTERFACE  | 0x0200 | 标志这是一个接口                                             |
  | ACC_ABSTRACT   | 0x0400 | 是否为abstract类型，对于接口或者抽象类来说，次标志值为真，其他类型为假 |
  | ACC_SYNTHETIC  | 0x1000 | 标志这个类并非由用户代码产生                                 |
  | ACC_ANNOTATION | 0x2000 | 标志这是一个注解                                             |
  | ACC_ENUM       | 0x4000 | 标志这是一个枚举                                             |

- 常量池

  `Constant pool`意为常量池。

  常量池可以理解成Class文件中的资源仓库。主要存放的是两大类常量：字面量(Literal)和符号引用(Symbolic References)。字面量类似于java中的常量概念，如文本字符串，final常量等，而符号引用则属于编译原理方面的概念，包括以下三种:

  - 类和接口的全限定名(Fully Qualified Name)
  - 字段的名称和描述符号(Descriptor)
  - 方法的名称和描述符

  不同于C/C++, JVM是在加载Class文件的时候才进行的动态链接，也就是说这些字段和方法符号引用只有在运行期转换后才能获得真正的内存入口地址。当虚拟机运行时，需要从常量池获得对应的符号引用，再在类创建或运行时解析并翻译到具体的内存地址中。 直接通过反编译文件来查看字节码内容：

  ````java
  #1 = Methodref          #4.#18         // java/lang/Object."<init>":()V
  #4 = Class              #21            // java/lang/Object
  #7 = Utf8               <init>
  #8 = Utf8               ()V
  #18 = NameAndType        #7:#8          // "<init>":()V
  #21 = Utf8               java/lang/Object
  ````

  **第一个常量**是一个方法定义，指向了第4和第18个常量。以此类推查看第4和第18个常量。最后可以拼接成第一个常量右侧的注释内容:java/lang/Object."<init>":()V

  这段可以理解为该类的实例构造器的声明，由于Main类没有重写构造方法，所以调用的是父类的构造方法。此处也说明了Main类的直接父类是Object。 该方法默认返回值是V, 也就是void，无返回值。

  **第二个常量**同理可得:

  ````java
  #2 = Fieldref           #3.#19         // com/rhythm7/Main.m:I
  #3 = Class              #20            // com/rhythm7/Main
  #5 = Utf8               m
  #6 = Utf8               I
  #19 = NameAndType        #5:#6          // m:I
  #20 = Utf8               com/rhythm7/Main
  ````

  复制代码此处声明了一个字段m，类型为I, I即是int类型。关于字节码的类型对应如下：

  | 标识字符 | 含义                                       |
  | -------- | ------------------------------------------ |
  | B        | 基本类型byte                               |
  | C        | 基本类型char                               |
  | D        | 基本类型double                             |
  | F        | 基本类型float                              |
  | I        | 基本类型int                                |
  | J        | 基本类型long                               |
  | S        | 基本类型short                              |
  | Z        | 基本类型boolean                            |
  | V        | 特殊类型void                               |
  | L        | 对象类型，以分号结尾，如Ljava/lang/Object; |

  对于数组类型，每一位使用一个前置的`[`字符来描述，如定义一个`java.lang.String[][]`类型的维数组，将被记录为`[[Ljava/lang/String;`

- 方法表集合

  在常量池之后的是对类内部的方法描述，在字节码中以表的集合形式表现，暂且不管字节码文件的16进制文件内容如何，我们直接看反编译后的内容。

  ````java
  private int m;
    descriptor: I
    flags: ACC_PRIVATE
  ````

  此处声明了一个私有变量m，类型为int，返回值为int

  ````java
  public com.rhythm7.Main();
     descriptor: ()V
     flags: ACC_PUBLIC
     Code:
       stack=1, locals=1, args_size=1
          0: aload_0
          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
          4: return
       LineNumberTable:
         line 3: 0
       LocalVariableTable:
         Start  Length  Slot  Name   Signature
             0       5     0  this   Lcom/rhythm7/Main;
  ````

  著作权归https://pdai.tech所有。 链接：https://www.pdai.tech/md/java/jvm/java-jvm-class.html

  这里是构造方法：Main()，返回值为void, 公开方法。

  code内的主要属性为:

  - **stack**: 最大操作数栈，JVM运行时会根据这个值来分配栈帧(Frame)中的操作栈深度,此处为1
  - **locals**: 局部变量所需的存储空间，单位为Slot, Slot是虚拟机为局部变量分配内存时所使用的最小单位，为4个字节大小。方法参数(包括实例方法中的隐藏参数this)，显示异常处理器的参数(try catch中的catch块所定义的异常)，方法体中定义的局部变量都需要使用局部变量表来存放。值得一提的是，locals的大小并不一定等于所有局部变量所占的Slot之和，因为局部变量中的Slot是可以重用的。
  - **args_size**: 方法参数的个数，这里是1，因为每个实例方法都会有一个隐藏参数this
  - **attribute_info**: 方法体内容，0,1,4为字节码"行号"，该段代码的意思是将第一个引用类型本地变量推送至栈顶，然后执行该类型的实例方法，也就是常量池存放的第一个变量，也就是注释里的"java/lang/Object."<init>":()V", 然后执行返回语句，结束方法。
  - **LineNumberTable**: 该属性的作用是描述源码行号与字节码行号(字节码偏移量)之间的对应关系。可以使用 -g:none 或-g:lines选项来取消或要求生成这项信息，如果选择不生成LineNumberTable，当程序运行异常时将无法获取到发生异常的源码行号，也无法按照源码的行数来调试程序。
  - **LocalVariableTable**: 该属性的作用是描述帧栈中局部变量与源码中定义的变量之间的关系。可以使用 -g:none 或 -g:vars来取消或生成这项信息，如果没有生成这项信息，那么当别人引用这个方法时，将无法获取到参数名称，取而代之的是arg0, arg1这样的占位符。 start 表示该局部变量在哪一行开始可见，length表示可见行数，Slot代表所在帧栈位置，Name是变量名称，然后是类型签名。

  同理可以分析Main类中的另一个方法"inc()":

  方法体内的内容是：将this入栈，获取字段#2并置于栈顶, 将int类型的1入栈，将栈内顶部的两个数值相加，返回一个int类型的值。

- 类名

  最后很显然是源码文件：

  ````java
  SourceFile: "Main.java"
  ````

#### 再看两个示例

- 分析try-catch-finally

  通过以上一个最简单的例子，可以大致了解源码被编译成字节码后是什么样子的。 下面利用所学的知识点来分析一些Java问题:

  ````java
  public class TestCode {
      public int foo() {
          int x;
          try {
              x = 1;
              return x;
          } catch (Exception e) {
              x = 2;
              return x;
          } finally {
              x = 3;
          }
      }
  }
  ````

  试问当不发生异常和发生异常的情况下，foo()的返回值分别是多少。

  ````java
  javac TestCode.java
  javap -verbose TestCode.class
  ````

  查看字节码的foo方法内容:

  ````java
  public int foo();
      descriptor: ()I
      flags: ACC_PUBLIC
      Code:
        stack=1, locals=5, args_size=1
           0: iconst_1 //int型1入栈 ->栈顶=1（将常量1推入操作数栈顶）
           1: istore_1 //将栈顶的int型数值存入第二个局部变量 ->局部2=1
           2: iload_1 //将第二个int型局部变量推送至栈顶 ->栈顶=1
           3: istore_2 //!!将栈顶int型数值存入第三个局部变量 ->局部3=1
           
           4: iconst_3 //int型3入栈 ->栈顶=3
           5: istore_1 //将栈顶的int型数值存入第二个局部变量 ->局部2=3
           6: iload_2 //!!将第三个int型局部变量推送至栈顶 ->栈顶=1
           7: ireturn //从当前方法返回栈顶int数值 ->1
           
           8: astore_2 // ->局部3=Exception
           9: iconst_2 // ->栈顶=2
          10: istore_1 // ->局部2=2
          11: iload_1 //->栈顶=2
          12: istore_3 //!! ->局部4=2
          
          13: iconst_3 // ->栈顶=3
          14: istore_1 // ->局部1=3
          15: iload_3 //!! ->栈顶=2
          16: ireturn // -> 2
          
          17: astore        4 //将栈顶引用型数值存入第五个局部变量=any
          19: iconst_3 //将int型数值3入栈 -> 栈顶3
          20: istore_1 //将栈顶第一个int数值存入第二个局部变量 -> 局部2=3
          21: aload         4 //将局部第五个局部变量(引用型)推送至栈顶
          23: athrow //将栈顶的异常抛出
        Exception table:
           from    to  target type
               0     4     8   Class java/lang/Exception //0到4行对应的异常，对应#8中储存的异常
               0     4    17   any //Exeption之外的其他异常
               8    13    17   any
              17    19    17   any
  ````

  在字节码的4,5，以及13,14中执行的是同一个操作，就是将int型的3入操作数栈顶，并存入第二个局部变量。这正是我们源码在finally语句块中内容。也就是说，JVM在处理异常时，会在每个可能的分支都将finally语句重复执行一遍。

  通过一步步分析字节码，可以得出最后的运行结果是：

  - 不发生异常时: return 1
  - 发生异常时: return 2
  - 发生非Exception及其子类的异常，抛出异常，不返回值

  > 以上例子来自于《深入理解Java虚拟机 JVM高级特性与最佳实践》, 关于虚拟机字节码指令表，也可以在《深入理解Java虚拟机 JVM高级特性与最佳实践-附录B》中获取。

- ~~kotlin 函数扩展的实现~~

### JVM 基础 - Java 类加载机制

JVM 基础 - Java 类加载机制

- 类的生命周期

  其中类加载的过程包括了`加载`、`验证`、`准备`、`解析`、`初始化`五个阶段。在这五个阶段中，`加载`、`验证`、`准备`和`初始化`这四个阶段发生的顺序是确定的，而`解析`阶段则不一定，它在某些情况下可以在初始化阶段之后开始，这是为了支持Java语言的运行时绑定(也成为动态绑定或晚期绑定)。另外注意这里的几个阶段是按顺序开始，而不是按顺序进行或完成，因为这些阶段通常都是互相交叉地混合进行的，通常在一个阶段执行的过程中调用或激活另一个阶段。

  ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/jvm/java_jvm_classload_2.png)

  - 类的加载: 查找并加载类的二进制数据

    加载时类加载过程的第一个阶段，在加载阶段，虚拟机需要完成以下三件事情:

    - 通过一个类的全限定名来获取其定义的二进制字节流。
    - 将这个字节流所代表的静态存储结构转化为方法区的运行时数据结构。
    - 在Java堆中生成一个代表这个类的java.lang.Class对象，作为对方法区中这些数据的访问入口。

    ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/jvm/java_jvm_classload_1.png)

    相对于类加载的其他阶段而言，加载阶段(准确地说，是加载阶段获取类的二进制字节流的动作)是可控性最强的阶段，因为开发人员既可以使用系统提供的类加载器来完成加载，也可以自定义自己的类加载器来完成加载。

    加载阶段完成后，虚拟机外部的 二进制字节流就按照虚拟机所需的格式存储在方法区之中，而且在Java堆中也创建一个`java.lang.Class`类的对象，这样便可以通过该对象访问方法区中的这些数据。

    类加载器并不需要等到某个类被“首次主动使用”时再加载它，JVM规范允许类加载器在预料某个类将要被使用时就预先加载它，如果在预先加载的过程中遇到了.class文件缺失或存在错误，类加载器必须在程序首次主动使用该类时才报告错误(LinkageError错误)如果这个类一直没有被程序主动使用，那么类加载器就不会报告错误。

    > 加载.class文件的方式

    - 从本地系统中直接加载
    - 通过网络下载.class文件
    - 从zip，jar等归档文件中加载.class文件
    - 从专有数据库中提取.class文件
    - 将Java源文件动态编译为.class文件

  - 连接

    - 验证: 确保被加载的类的正确性

      验证是连接阶段的第一步，这一阶段的目的是为了确保Class文件的字节流中包含的信息符合当前虚拟机的要求，并且不会危害虚拟机自身的安全。验证阶段大致会完成4个阶段的检验动作:

      - `文件格式验证`: 验证字节流是否符合Class文件格式的规范；例如: 是否以`0xCAFEBABE`开头、主次版本号是否在当前虚拟机的处理范围之内、常量池中的常量是否有不被支持的类型。
      - `元数据验证`: 对字节码描述的信息进行语义分析(注意: 对比`javac`编译阶段的语义分析)，以保证其描述的信息符合Java语言规范的要求；例如: 这个类是否有父类，除了`java.lang.Object`之外。
      - `字节码验证`: 通过数据流和控制流分析，确定程序语义是合法的、符合逻辑的。
      - `符号引用验证`: 确保解析动作能正确执行。

      > 验证阶段是非常重要的，但不是必须的，它对程序运行期没有影响，如果所引用的类经过反复验证，那么可以考虑采用`-Xverifynone`参数来关闭大部分的类验证措施，以缩短虚拟机类加载的时间。

    - 准备: 为类的静态变量分配内存，并将其初始化为默认值

      准备阶段是正式为类变量分配内存并设置类变量初始值的阶段，**这些内存都将在方法区中分配**。对于该阶段有以下几点需要注意:

      - 这时候进行内存分配的仅包括类变量(`static`)，而不包括实例变量，实例变量会在对象实例化时随着对象一块分配在Java堆中。

      - 这里所设置的初始值通常情况下是数据类型默认的零值(如`0`、`0L`、`null`、`false`等)，而不是被在Java代码中被显式地赋予的值。

        假设一个类变量的定义为: `public static int value = 3`；那么变量value在准备阶段过后的初始值为`0`，而不是`3`，因为这时候尚未开始执行任何Java方法，而把value赋值为3的`put static`指令是在程序编译后，存放于类构造器`<clinit>()`方法之中的，所以把value赋值为3的动作将在初始化阶段才会执行。

      > 这里还需要注意如下几点

      - 对基本数据类型来说，对于类变量(static)和全局变量，如果不显式地对其赋值而直接使用，则系统会为其赋予默认的零值，而对于局部变量来说，在使用前必须显式地为其赋值，否则编译时不通过。
      - 对于同时被`static`和`final`修饰的常量，必须在声明的时候就为其显式地赋值，否则编译时不通过；而只被final修饰的常量则既可以在声明时显式地为其赋值，也可以在类初始化时显式地为其赋值，总之，在使用前必须为其显式地赋值，系统不会为其赋予默认零值。
      - 对于引用数据类型`reference`来说，如数组引用、对象引用等，如果没有对其进行显式地赋值而直接使用，系统都会为其赋予默认的零值，即`null`。
      - 如果在数组初始化时没有对数组中的各元素赋值，那么其中的元素将根据对应的数据类型而被赋予默认的零值。
      - 如果类字段的字段属性表中存在ConstantValue属性，即同时被final和static修饰，那么在准备阶段变量value就会被初始化为ConstValue属性所指定的值。假设上面的类变量value被定义为: `public static final int value = 3；`编译时Javac将会为value生成ConstantValue属性，在准备阶段虚拟机就会根据ConstantValue的设置将value赋值为3。我们可以理解为`static final`常量在编译期就将其结果放入了调用它的类的常量池中

    - 解析: 把类中的符号引用转换为直接引用

      解析阶段是虚拟机将常量池内的符号引用替换为直接引用的过程，解析动作主要针对`类`或`接口`、`字段`、`类方法`、`接口方法`、`方法类型`、`方法句柄`和`调用点`限定符7类符号引用进行。

      `符号引用`就是一组符号来描述目标，可以是任何字面量。

      `直接引用`就是直接指向目标的指针、相对偏移量或一个间接定位到目标的句柄。

  - 初始化

    初始化，为类的静态变量赋予正确的初始值，JVM负责对类进行初始化，主要对类变量进行初始化。在Java中对类变量进行初始值设定有两种方式:

    - 声明类变量是指定初始值
    - 使用静态代码块为类变量指定初始值

    **JVM初始化步骤**

    - 假如这个类还没有被加载和连接，则程序先加载并连接该类
    - 假如该类的直接父类还没有被初始化，则先初始化其直接父类
    - 假如类中有初始化语句，则系统依次执行这些初始化语句

    **类初始化时机**: 只有当对类的主动使用的时候才会导致类的初始化，类的主动使用包括以下六种:

    - 创建类的实例，也就是new的方式
    - 访问某个类或接口的静态变量，或者对该静态变量赋值
    - 调用类的静态方法
    - 反射(如Class.forName("com.pdai.jvm.Test"))
    - 初始化某个类的子类，则其父类也会被初始化
    - Java虚拟机启动时被标明为启动类的类(Java Test)，直接使用java.exe命令来运行某个主类

  - 使用

    类访问方法区内的数据结构的接口， 对象是Heap区的数据。

  - 卸载

    **Java虚拟机将结束生命周期的几种情况**

    - 执行了System.exit()方法
    - 程序正常执行结束
    - 程序在执行过程中遇到了异常或错误而异常终止
    - 由于操作系统出现错误而导致Java虚拟机进程终止

- 类加载器， JVM类加载机制

  - 类加载器的层次

    ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/jvm/java_jvm_classload_3.png)

    > 注意: 这里父类加载器并不是通过继承关系来实现的，而是采用组合实现的。

    > **站在Java开发人员的角度来看，类加载器可以大致划分为以下三类** :
    >
    > `启动类加载器`: Bootstrap ClassLoader，负责加载存放在JDK\jre\lib(JDK代表JDK的安装目录，下同)下，或被-Xbootclasspath参数指定的路径中的，并且能被虚拟机识别的类库(如rt.jar，所有的java.*开头的类均被Bootstrap ClassLoader加载)。启动类加载器是无法被Java程序直接引用的。
    >
    > `扩展类加载器`: Extension ClassLoader，该加载器由`sun.misc.Launcher$ExtClassLoader`实现，它负责加载JDK\jre\lib\ext目录中，或者由java.ext.dirs系统变量指定的路径中的所有类库(如javax.*开头的类)，开发者可以直接使用扩展类加载器。
    >
    > `应用程序类加载器`: Application ClassLoader，该类加载器由`sun.misc.Launcher$AppClassLoader`来实现，它负责加载用户类路径(ClassPath)所指定的类，开发者可以直接使用该类加载器，如果应用程序中没有自定义过自己的类加载器，一般情况下这个就是程序中默认的类加载器。
    >
    > 应用程序都是由这三种类加载器互相配合进行加载的，如果有必要，我们还可以加入自定义的类加载器。因为JVM自带的ClassLoader只是懂得从本地文件系统加载标准的java class文件，因此如果编写了自己的ClassLoader，便可以做到如下几点:
    >
    > - 在执行非置信代码之前，自动验证数字签名。
    >
    > - 动态地创建符合用户特定需要的定制化构建类。
    > - 从特定的场所取得java class，例如数据库中和网络中。

  - 寻找类加载器

    寻找类加载器小例子如下:

    ````java
    package com.pdai.jvm.classloader;
    public class ClassLoaderTest {
         public static void main(String[] args) {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            System.out.println(loader);
            System.out.println(loader.getParent());
            System.out.println(loader.getParent().getParent());
        }
    }
    ````

    结果如下:

    ````java
    sun.misc.Launcher$AppClassLoader@64fef26a
    sun.misc.Launcher$ExtClassLoader@1ddd40f3
    null
    ````

    从上面的结果可以看出，并没有获取到`ExtClassLoader`的父Loader，原因是`BootstrapLoader`(引导类加载器)是用C语言实现的，找不到一个确定的返回父Loader的方式，于是就返回`null`。

  - 类的加载

    类加载有三种方式:

    1、命令行启动应用时候由JVM初始化加载

    2、通过Class.forName()方法动态加载

    3、通过ClassLoader.loadClass()方法动态加载

    ````java
    public class LoaderTest {
        public static void main(String[] args) throws ClassNotFoundException {
            ClassLoader loader = HelloWorld.class.getClassLoader();
            System.out.println(loader);
            //使用ClassLoader.loadClass()来加载类，不会执行初始化块
            loader.loadClass("com.qudian.qpark.user.server.service.Test2");
            //使用Class.forName()来加载类，默认会执行初始化块
            Class.forName("com.qudian.qpark.user.server.service.Test2");
            //使用Class.forName()来加载类，并指定ClassLoader，初始化时不执行静态块
            Class.forName("com.qudian.qpark.user.server.service.Test2", false, loader);
        }
    }
    
    public class Test2 { 
            static { 
                    System.out.println("静态初始化块执行了！"); 
            } 
    }
    ````

    ````html
    sun.misc.Launcher$AppClassLoader@18b4aac2
    静态初始化块执行了！
    ````

    > Class.forName()和ClassLoader.loadClass()区别?
    >
    > - Class.forName(): 将类的.class文件加载到jvm中之外，还会对类进行解释，执行类中的static块；
    > - ClassLoader.loadClass(): 只干一件事情，就是将.class文件加载到jvm中，不会执行static中的内容,只有在newInstance才会去执行static块。
    > - Class.forName(name, initialize, loader)带参函数也可控制是否加载static块。并且只有调用了newInstance()方法采用调用构造函数，创建类的对象 。

- JVM类加载机制

  `全盘负责`，当一个类加载器负责加载某个Class时，该Class所依赖的和引用的其他Class也将由该类加载器负责载入，除非显示使用另外一个类加载器来载入

  `父类委托`，先让父类加载器试图加载该类，只有在父类加载器无法加载该类时才尝试从自己的类路径中加载该类

  `缓存机制`，缓存机制将会保证所有加载过的Class都会被缓存，当程序中需要使用某个Class时，类加载器先从缓存区寻找该Class，只有缓存区不存在，系统才会读取该类对应的二进制数据，并将其转换成Class对象，存入缓存区。这就是为什么修改了Class后，必须重启JVM，程序的修改才会生效

  `双亲委派机制`, 如果一个类加载器收到了类加载的请求，它首先不会自己去尝试加载这个类，而是把请求委托给父加载器去完成，依次向上，因此，所有的类加载请求最终都应该被传递到顶层的启动类加载器中，只有当父加载器在它的搜索范围中没有找到所需的类时，即无法完成该加载，子加载器才会尝试自己去加载该类

  **双亲委派机制过程？**

  当AppClassLoader加载一个class时，它首先不会自己去尝试加载这个类，而是把类加载请求委派给父类加载器ExtClassLoader去完成。

  当ExtClassLoader加载一个class时，它首先也不会自己去尝试加载这个类，而是把类加载请求委派给BootStrapClassLoader去完成。

  如果BootStrapClassLoader加载失败(例如在$JAVA_HOME/jre/lib里未查找到该class)，会使用ExtClassLoader来尝试加载；

  若ExtClassLoader也加载失败，则会使用AppClassLoader来加载，如果AppClassLoader也加载失败，则会报出异常ClassNotFoundException。

  **双亲委派代码实现**

  ````java
  public Class<?> loadClass(String name)throws ClassNotFoundException {
    return loadClass(name, false);
  }
  protected synchronized Class<?> loadClass(String name, boolean resolve)throws ClassNotFoundException {
    // 首先判断该类型是否已经被加载
    Class c = findLoadedClass(name);
    if (c == null) {
      //如果没有被加载，就委托给父类加载或者委派给启动类加载器加载
      try {
        if (parent != null) {
          //如果存在父类加载器，就委派给父类加载器加载
          c = parent.loadClass(name, false);
        } else {
          //如果不存在父类加载器，就检查是否是由启动类加载器加载的类，通过调用本地方法native Class findBootstrapClass(String name)
          c = findBootstrapClass0(name);
        }
      } catch (ClassNotFoundException e) {
        // 如果父类加载器和启动类加载器都不能完成加载任务，才调用自身的加载功能
        c = findClass(name);
      }
    }
    if (resolve) {
      resolveClass(c);
    }
    return c;
  }
  ````

  **双亲委派优势**

  - 系统类防止内存中出现多份同样的字节码
  - 保证Java程序安全稳定运行

- 自定义类加载器

通常情况下，我们都是直接使用系统类加载器。但是，有的时候，我们也需要自定义类加载器。比如应用是通过网络来传输 Java 类的字节码，为保证安全性，这些字节码经过了加密处理，这时系统类加载器就无法对其进行加载，这样则需要自定义类加载器来实现。自定义类加载器一般都是继承自 ClassLoader 类，从上面对 loadClass 方法来分析来看，我们只需要重写 findClass 方法即可。

下面我们通过一个示例来演示自定义类加载器的流程:

````java
package com.pdai.jvm.classloader;
import java.io.*;

public class MyClassLoader extends ClassLoader {

    private String root;

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = loadClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] loadClassData(String className) {
        String fileName = root + File.separatorChar
                + className.replace('.', File.separatorChar) + ".class";
        try {
            InputStream ins = new FileInputStream(fileName);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length = 0;
            while ((length = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public static void main(String[] args)  {

        MyClassLoader classLoader = new MyClassLoader();
        classLoader.setRoot("D:\\temp");

        Class<?> testClass = null;
        try {
            testClass = classLoader.loadClass("com.pdai.jvm.classloader.Test2");
            Object object = testClass.newInstance();
            System.out.println(object.getClass().getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
````

自定义类加载器的核心在于对字节码文件的获取，如果是加密的字节码则需要在该类中对文件进行解密。由于这里只是演示，我并未对class文件进行加密，因此没有解密的过程。

**这里有几点需要注意** :

1、这里传递的文件名需要是类的全限定性名称，即`com.pdai.jvm.classloader.Test2`格式的，因为 defineClass 方法是按这种格式进行处理的。

2、最好不要重写loadClass方法，因为这样容易破坏双亲委托模式。

3、这类Test 类本身可以被 AppClassLoader 类加载，因此我们不能把com/pdai/jvm/classloader/Test2.class 放在类路径下。否则，由于双亲委托机制的存在，会直接导致该类由 AppClassLoader 加载，而不会通过我们自定义类加载器来加载。

### JVM 基础 - JVM 内存结构

JVM 基础 - JVM 内存结构

- 运行时数据区
- 一、程序计数器
  - 1.1 作用
  - 1.2 概述
- 二、虚拟机栈
  - 2.1 概述
  - 2.2 栈的存储单位
  - 2.3 栈运行原理
  - 2.4 栈帧的内部结构
    - 2.4.1. 局部变量表
      - 槽 Slot
    - 2.4.2. 操作数栈
      - 概述
      - 栈顶缓存（Top-of-stack-Cashing）
    - 2.4.3. 动态链接（指向运行时常量池的方法引用）
      - JVM 是如何执行方法调用的
      - 虚方法和非虚方法
      - 虚方法表
    - 2.4.4. 方法返回地址（return address）
    - 2.4.5. 附加信息
- 三、本地方法栈
  - 3.1 本地方法接口
  - 3.2 本地方法栈（Native Method Stack）
- 四、堆内存
  - 4.1 内存划分
    - 年轻代 (Young Generation)
    - 老年代(Old Generation)
    - 元空间
  - 4.2 设置堆内存大小和 OOM
    - 查看 JVM 堆内存分配
  - 4.3 对象在堆中的生命周期
  - 4.4 对象的分配过程
  - 4.5 GC 垃圾回收简介
    - Minor GC、Major GC、Full GC
  - 4.6 TLAB
    - 什么是 TLAB （Thread Local Allocation Buffer）?
    - 为什么要有 TLAB ?
  - 4.7 堆是分配对象存储的唯一选择吗
    - 逃逸分析
      - 代码优化之同步省略（消除）
      - 代码优化之标量替换
      - 代码优化之栈上分配
- 五、方法区
  - 5.1 解惑
  - 5.2 设置方法区内存的大小
  - 5.3 方法区内部结构
    - 类型信息
    - 域（Field）信息
    - 方法（Method）信息
  - 5.4 运行时常量池
    - 常量池
      - 为什么需要常量池？
    - 运行时常量池
  - 5.5 方法区在 JDK6、7、8中的演进细节
    - 移除永久代原因
  - 5.6 方法区的垃圾回收
