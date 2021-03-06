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
> ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/jvm/java-jvm-debug.png)
>
> - 调试排错 - JVM 调优参数
>  - 本文对JVM涉及的常见的调优参数和垃圾回收参数进行阐述
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

  此处声明了一个字段m，类型为I, I即是int类型。关于字节码的类型对应如下：

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
  javac -g:vars TestCode.java  (-g:vars  生成LocalVariableTable本地变量表)
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

  - 不发生异常时: return 1（由于finally的变量表和try{}中不属于同一个变量表，所以不会影响try中的值）
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

![image-20220113111857395](/Users/wz/Library/Application Support/typora-user-images/image-20220113111857395.png)

#### 运行时数据区

##### 一、程序计数器

程序计数寄存器（**Program Counter Register**），Register 的命名源于 CPU 的寄存器，寄存器存储指令相关的线程信息，CPU 只有把数据装载到寄存器才能够运行。

程序计数器是一块较小的内存空间，可以看作是当前线程所执行的字节码的**行号指示器**。

- 1.1 作用

  PC 寄存器用来存储指向下一条指令的地址，即将要执行的指令代码。由执行引擎读取下一条指令。

  <img src="https://tva1.sinaimg.cn/large/0082zybply1gc5kmznm1sj31m50u0wph.jpg" alt="jvm-pc-counter" style="zoom:67%;" />

  （分析：进入class文件所在目录，执行 `javap -v xx.class` 反解析（或者通过 IDEA 插件 `Jclasslib` 直接查看，上图），可以看到当前类对应的Code区（汇编指令）、本地变量表、异常表和代码行偏移量映射表、常量池等信息。）

- 1.2 概述

  > 通过下面两个问题，理解下PC计数器

  - **使用PC寄存器存储字节码指令地址有什么用呢？为什么使用PC寄存器记录当前线程的执行地址呢？**

  因为CPU需要不停的切换各个线程，这时候切换回来以后，就得知道接着从哪开始继续执行。JVM的字节码解释器就需要通过改变PC寄存器的值来明确下一条应该执行什么样的字节码指令。

  - **PC寄存器为什么会被设定为线程私有的？**

  多线程在一个特定的时间段内只会执行其中某一个线程方法，CPU会不停的做任务切换，这样必然会导致经常中断或恢复。为了能够准确的记录各个线程正在执行的当前字节码指令地址，所以为每个线程都分配了一个PC寄存器，每个线程都独立计算，不会互相影响。

  > 相关总结如下：

  - 它是一块很小的内存空间，几乎可以忽略不计。也是运行速度最快的存储区域
  - 在 JVM 规范中，每个线程都有它自己的程序计数器，是线程私有的，生命周期与线程的生命周期一致
  - 任何时间一个线程都只有一个方法在执行，也就是所谓的**当前方法**。如果当前线程正在执行的是 Java 方法，程序计数器记录的是 JVM 字节码指令地址，如果是执行 native 方法，则是未指定值（undefined）
  - 它是程序控制流的指示器，分支、循环、跳转、异常处理、线程恢复等基础功能都需要依赖这个计数器来完成
  - 字节码解释器工作时就是通过改变这个计数器的值来选取下一条需要执行的字节码指令
  - **它是唯一一个在 JVM 规范中没有规定任何 `OutOfMemoryError` 情况的区域**

##### 二、虚拟机栈

- 2.1 概述

  > Java 虚拟机栈(Java Virtual Machine Stacks)，早期也叫 Java 栈。每个线程在创建的时候都会创建一个虚拟机栈，其内部保存一个个的栈帧(Stack Frame），对应着一次次 Java 方法调用，是线程私有的，生命周期和线程一致。

  **作用**：主管 Java 程序的运行，它保存方法的局部变量、部分结果，并参与方法的调用和返回。

  **特点**：

  - 栈是一种快速有效的分配存储方式，访问速度仅次于程序计数器
  - JVM 直接对虚拟机栈的操作只有两个：每个方法执行，伴随着**入栈**（进栈/压栈），方法执行结束**出栈**
  - **栈不存在垃圾回收问题**

  **栈中可能出现的异常**：

  Java 虚拟机规范允许 **Java虚拟机栈的大小是动态的或者是固定不变的**

  - 如果采用固定大小的 Java 虚拟机栈，那每个线程的 Java 虚拟机栈容量可以在线程创建的时候独立选定。如果线程请求分配的栈容量超过 Java 虚拟机栈允许的最大容量，Java 虚拟机将会抛出一个 **StackOverflowError** 异常
  - 如果 Java 虚拟机栈可以动态扩展，并且在尝试扩展的时候无法申请到足够的内存，或者在创建新的线程时没有足够的内存去创建对应的虚拟机栈，那 Java 虚拟机将会抛出一个**OutOfMemoryError**异常

  可以通过参数`-Xss`来设置线程的最大栈空间，栈的大小直接决定了函数调用的最大可达深度。

  官方提供的参考工具，可查一些参数和操作：https://docs.oracle.com/javase/8/docs/technotes/tools/windows/java.html#BGBCIEFC

- 2.2 栈的存储单位

  栈中存储什么？

  - 每个线程都有自己的栈，栈中的数据都是以**栈帧（Stack Frame）**的格式存在
  - 在这个线程上正在执行的每个方法都各自有对应的一个栈帧
  - 栈帧是一个内存区块，是一个数据集，维系着方法执行过程中的各种数据信息

- 2.3 栈运行原理

  - JVM 直接对 Java 栈的操作只有两个，对栈帧的**压栈**和**出栈**，遵循“先进后出/后进先出”原则
  - 在一条活动线程中，一个时间点上，只会有一个活动的栈帧。即只有当前正在执行的方法的栈帧（**栈顶栈帧**）是有效的，这个栈帧被称为**当前栈帧**（Current Frame），与当前栈帧对应的方法就是**当前方法**（Current Method），定义这个方法的类就是**当前类**（Current Class）
  - 执行引擎运行的所有字节码指令只针对当前栈帧进行操作
  - 如果在该方法中调用了其他方法，对应的新的栈帧会被创建出来，放在栈的顶端，称为新的当前栈帧
  - 不同线程中所包含的栈帧是不允许存在相互引用的，即不可能在一个栈帧中引用另外一个线程的栈帧
  - 如果当前方法调用了其他方法，方法返回之际，当前栈帧会传回此方法的执行结果给前一个栈帧，接着，虚拟机会丢弃当前栈帧，使得前一个栈帧重新成为当前栈帧
  - Java 方法有两种返回函数的方式，**一种是正常的函数返回，使用 return 指令，另一种是抛出异常，不管用哪种方式，都会导致栈帧被弹出**

  IDEA 在 debug 时候，可以在 debug 窗口看到 Frames 中各种方法的压栈和出栈情况

  <img src="https://tva1.sinaimg.cn/large/0082zybply1gc9lezaxrbj319v0u0k4w.jpg" alt="img" style="zoom:67%;" />

- 2.4 栈帧的内部结构
  
  每个**栈帧**（Stack Frame）中存储着：
  
  - 局部变量表（Local Variables）
  - 操作数栈（Operand Stack）(或称为表达式栈)
  - 动态链接（Dynamic Linking）：指向运行时常量池的方法引用
  - 方法返回地址（Return Address）：方法正常退出或异常退出的地址
  - 一些附加信息
  
  ![jvm-stack-frame](https://tva1.sinaimg.cn/large/0082zybply1gc8tjehg8bj318m0lbtbu.jpg)
  
  继续深抛栈帧中的五部分~~
  
  - 2.4.1. 局部变量表
    
    局部变量表也被称为局部变量数组或者本地变量表
    
    是一组变量值存储空间，**主要用于存储方法参数和定义在方法体内的局部变量**，包括编译器可知的各种 Java 虚拟机**基本数据类型**（boolean、byte、char、short、int、float、long、double）、**对象引用**（reference类型，它并不等同于对象本身，可能是一个指向对象起始地址的引用指针，也可能是指向一个代表对象的句柄或其他与此相关的位置）和 **returnAddress** 类型（指向了一条字节码指令的地址，已被异常表取代）
    
    由于局部变量表是建立在线程的栈上，是线程的私有数据，因此**不存在数据安全问题**
    
    **局部变量表所需要的容量大小是编译期确定下来的**，并保存在方法的 Code 属性的 `maximum local variables` 数据项中。在方法运行期间是不会改变局部变量表的大小的
    
    方法嵌套调用的次数由栈的大小决定。一般来说，**栈越大，方法嵌套调用次数越多**。对一个函数而言，它的参数和局部变量越多，使得局部变量表膨胀，它的栈帧就越大，以满足方法调用所需传递的信息增大的需求。进而函数调用就会占用更多的栈空间，导致其嵌套调用次数就会减少。
    
    **局部变量表中的变量只在当前方法调用中有效**。在方法执行时，虚拟机通过使用局部变量表完成参数值到参数变量列表的传递过程。当方法调用结束后，随着方法栈帧的销毁，局部变量表也会随之销毁。
    
    参数值的存放总是在局部变量数组的 index0 开始，到数组长度 -1 的索引结束
    
    - 槽 Slot
    
      - 局部变量表最基本的存储单元是 Slot（变量槽）
      - 在局部变量表中，32 位以内的类型只占用一个 Slot(包括returnAddress类型)，64 位的类型（long和double）占用两个连续的 Slot
        - byte、short、char 在存储前被转换为int，boolean也被转换为int，0 表示 false，非 0 表示 true
        - long 和 double 则占据两个 Slot
      - JVM 会为局部变量表中的每一个 Slot 都分配一个访问索引，通过这个索引即可成功访问到局部变量表中指定的局部变量值，索引值的范围从 0 开始到局部变量表最大的 Slot 数量
      - 当一个实例方法被调用的时候，它的方法参数和方法体内部定义的局部变量将会**按照顺序被复制**到局部变量表中的每一个 Slot 上
      - **如果需要访问局部变量表中一个 64bit 的局部变量值时，只需要使用前一个索引即可**。（比如：访问 long 或 double 类型变量，不允许采用任何方式单独访问其中的某一个 Slot）
      - 如果当前帧是由构造方法或实例方法创建的，那么该对象引用 this 将会存放在 index 为 0 的 Slot 处，其余的参数按照参数表顺序继续排列（这里就引出一个问题：静态方法中为什么不可以引用 this，就是因为this 变量不存在于当前方法的局部变量表中）
      - **栈帧中的局部变量表中的槽位是可以重用的**，如果一个局部变量过了其作用域，那么在其作用域之后申明的新的局部变量就很有可能会复用过期局部变量的槽位，从而**达到节省资源的目的**。（下图中，this、a、b、c 理论上应该有 4 个变量，c 复用了 b 的槽）
    
      ![img](https://tva1.sinaimg.cn/large/0082zybply1gc9s12g5wlj31li0owdm9.jpg)
    
      - 在栈帧中，与性能调优关系最为密切的就是局部变量表。在方法执行时，虚拟机使用局部变量表完成方法的传递
      - **局部变量表中的变量也是重要的垃圾回收根节点，只要被局部变量表中直接或间接引用的对象都不会被回收**
    
  - 2.4.2. 操作数栈
    
    每个独立的栈帧中除了包含局部变量表之外，还包含一个**后进先出**（Last-In-First-Out）的操作数栈，也可以称为**表达式栈**（Expression Stack）
    
    **操作数栈，在方法执行过程中，根据字节码指令，往操作数栈中写入数据或提取数据，即入栈（push）、出栈（pop）**
    
    某些字节码指令将值压入操作数栈，其余的字节码指令将操作数取出栈。使用它们后再把结果压入栈。比如，执行复制、交换、求和等操作
    
    - 概述
    
      操作数栈，**主要用于保存计算过程的中间结果，同时作为计算过程中变量临时的存储空间**
    
      操作数栈就是 JVM 执行引擎的一个工作区，当一个方法刚开始执行的时候，一个新的栈帧也会随之被创建出来，**此时这个方法的操作数栈是空的**
    
      每一个操作数栈都会拥有一个明确的栈深度用于存储数值，其所需的最大深度在编译期就定义好了，保存在方法的 Code 属性的 `max_stack` 数据项中
    
      栈中的任何一个元素都可以是任意的 Java 数据类型
    
      - 32bit 的类型占用一个栈单位深度
      - 64bit 的类型占用两个栈单位深度
    
      操作数栈并非采用访问索引的方式来进行数据访问的，而是只能通过标准的入栈和出栈操作来完成一次数据访问
    
      **如果被调用的方法带有返回值的话，其返回值将会被压入当前栈帧的操作数栈中**，并更新 PC 寄存器中下一条需要执行的字节码指令
    
      操作数栈中元素的数据类型必须与字节码指令的序列严格匹配，这由编译器在编译期间进行验证，同时在类加载过程中的类检验阶段的数据流分析阶段要再次验证
    
      另外，我们说**Java虚拟机的解释引擎是基于栈的执行引擎**，其中的栈指的就是操作数栈
    
    - 栈顶缓存（Top-of-stack-Cashing）
    
      HotSpot 的执行引擎采用的并非是基于寄存器的架构，但这并不代表 HotSpot VM 的实现并没有间接利用到寄存器资源。寄存器是物理 CPU 中的组成部分之一，它同时也是 CPU 中非常重要的高速存储资源。一般来说，寄存器的读/写速度非常迅速，甚至可以比内存的读/写速度快上几十倍不止，不过寄存器资源却非常有限，不同平台下的CPU 寄存器数量是不同和不规律的。寄存器主要用于缓存本地机器指令、数值和下一条需要被执行的指令地址等数据。
    
      基于栈式架构的虚拟机所使用的零地址指令更加紧凑，但完成一项操作的时候必然需要使用更多的入栈和出栈指令，这同时也就意味着将需要更多的指令分派（instruction dispatch）次数和内存读/写次数。由于操作数是存储在内存中的，因此频繁的执行内存读/写操作必然会影响执行速度。为了解决这个问题，HotSpot JVM 设计者们提出了栈顶缓存技术，**将栈顶元素全部缓存在物理 CPU 的寄存器中，以此降低对内存的读/写次数，提升执行引擎的执行效率**
    
  - 2.4.3. 动态链接（指向运行时常量池的方法引用）
    
    - **每一个栈帧内部都包含一个指向运行时常量池中该栈帧所属方法的引用**。包含这个引用的目的就是为了支持当前方法的代码能够实现动态链接(Dynamic Linking)。
    - 在 Java 源文件被编译到字节码文件中时，所有的变量和方法引用都作为**符号引用**（Symbolic Reference）保存在 Class 文件的常量池中。比如：描述一个方法调用了另外的其他方法时，就是通过常量池中指向方法的符号引用来表示的，那么**动态链接的作用就是为了将这些符号引用转换为调用方法的直接引用**
    
    ![jvm-dynamic-linking](https://tva1.sinaimg.cn/large/0082zybply1gca4k4gndgj31d20o2td0.jpg)
    
    - JVM 是如何执行方法调用的
    
      方法调用不同于方法执行，方法调用阶段的唯一任务就是确定被调用方法的版本（即调用哪一个方法），暂时还不涉及方法内部的具体运行过程。Class 文件的编译过程中不包括传统编译器中的连接步骤，一切方法调用在 Class文件里面存储的都是**符号引用**，而不是方法在实际运行时内存布局中的入口地址（**直接引用**）。也就是需要在类加载阶段，甚至到运行期才能确定目标方法的直接引用。
    
      > 【这一块内容，除了方法调用，还包括解析、分派（静态分派、动态分派、单分派与多分派），这里先不介绍，后续再挖】
    
      在 JVM 中，将符号引用转换为调用方法的直接引用与方法的绑定机制有关
    
      - **静态链接**：当一个字节码文件被装载进 JVM 内部时，如果被调用的**目标方法在编译期可知**，且运行期保持不变时。这种情况下将调用方法的符号引用转换为直接引用的过程称之为静态链接
      - **动态链接**：如果被调用的方法在编译期无法被确定下来，也就是说，只能在程序运行期将调用方法的符号引用转换为直接引用，由于这种引用转换过程具备动态性，因此也就被称之为动态链接
    
      对应的方法的绑定机制为：早期绑定（Early Binding）和晚期绑定（Late Binding）。**绑定是一个字段、方法或者类在符号引用被替换为直接引用的过程，这仅仅发生一次**。
    
      - 早期绑定：**早期绑定就是指被调用的目标方法如果在编译期可知，且运行期保持不变时**，即可将这个方法与所属的类型进行绑定，这样一来，由于明确了被调用的目标方法究竟是哪一个，因此也就可以使用静态链接的方式将符号引用转换为直接引用。
      - 晚期绑定：如果被调用的方法在编译器无法被确定下来，只能够在程序运行期根据实际的类型绑定相关的方法，这种绑定方式就被称为晚期绑定。
    
    - 虚方法和非虚方法
    
      - 如果方法在编译器就确定了具体的调用版本，这个版本在运行时是不可变的。这样的方法称为非虚方法，比如静态方法、私有方法、final 方法、实例构造器、父类方法都是非虚方法
      - 其他方法称为虚方法
    
    - 虚方法表
    
      在面向对象编程中，会频繁的使用到动态分派，如果每次动态分派都要重新在类的方法元数据中搜索合适的目标有可能会影响到执行效率。为了提高性能，JVM 采用在类的方法区建立一个虚方法表（virtual method table），使用索引表来代替查找。非虚方法不会出现在表中。
    
      每个类中都有一个虚方法表，表中存放着各个方法的实际入口。
    
      虚方法表会在类加载的连接阶段被创建并开始初始化，类的变量初始值准备完成之后，JVM 会把该类的方法表也初始化完毕。
    
  - 2.4.4. 方法返回地址（return address）
  
    用来存放调用该方法的 PC 寄存器的值。
  
    一个方法的结束，有两种方式
  
    - 正常执行完成
    - 出现未处理的异常，非正常退出
  
    无论通过哪种方式退出，在方法退出后都返回到该方法被调用的位置。方法正常退出时，调用者的 PC 计数器的值作为返回地址，即调用该方法的指令的下一条指令的地址。而通过异常退出的，返回地址是要通过异常表来确定的，栈帧中一般不会保存这部分信息。
  
    当一个方法开始执行后，只有两种方式可以退出这个方法：
  
    1. 执行引擎遇到任意一个方法返回的字节码指令，会有返回值传递给上层的方法调用者，简称**正常完成出口**
  
       一个方法的正常调用完成之后究竟需要使用哪一个返回指令还需要根据方法返回值的实际数据类型而定
  
       在字节码指令中，返回指令包含 ireturn(当返回值是 boolean、byte、char、short 和 int 类型时使用)、lreturn、freturn、dreturn 以及 areturn，另外还有一个 return 指令供声明为 void 的方法、实例初始化方法、类和接口的初始化方法使用。
  
    2. 在方法执行的过程中遇到了异常，并且这个异常没有在方法内进行处理，也就是只要在本方法的异常表中没有搜索到匹配的异常处理器，就会导致方法退出。简称**异常完成出口**
  
       方法执行过程中抛出异常时的异常处理，存储在一个异常处理表，方便在发生异常的时候找到处理异常的代码。
  
    本质上，**方法的退出就是当前栈帧出栈的过程**。此时，需要恢复上层方法的局部变量表、操作数栈、将返回值压入调用者栈帧的操作数栈、设置PC寄存器值等，让调用者方法继续执行下去。
  
    正常完成出口和异常完成出口的区别在于：**通过异常完成出口退出的不会给他的上层调用者产生任何的返回值**
  
  - 2.4.5. 附加信息
  
    栈帧中还允许携带与 Java 虚拟机实现相关的一些附加信息。例如，对程序调试提供支持的信息，但这些信息取决于具体的虚拟机实现。

##### 三、本地方法栈

简单的讲，一个 Native Method 就是一个 Java 调用非 Java 代码的接口。我们知道的 Unsafe 类就有很多本地方法。

> 为什么要使用本地方法（Native Method）?

- 3.1 本地方法接口

  Java 使用起来非常方便，然而有些层次的任务用 Java 实现起来也不容易，或者我们对程序的效率很在意时，问题就来了

  - 与 Java 环境外交互：有时 Java 应用需要与 Java 外面的环境交互，这就是本地方法存在的原因。
  - 与操作系统交互：JVM 支持 Java 语言本身和运行时库，但是有时仍需要依赖一些底层系统的支持。通过本地方法，我们可以实现用 Java 与实现了 jre 的底层系统交互， JVM 的一些部分就是 C 语言写的。
  - Sun's Java：Sun的解释器就是C实现的，这使得它能像一些普通的C一样与外部交互。jre大部分都是用 Java 实现的，它也通过一些本地方法与外界交互。比如，类 `java.lang.Thread` 的 `setPriority()` 的方法是用Java 实现的，但它实现调用的是该类的本地方法 `setPrioruty()`，该方法是C实现的，并被植入 JVM 内部。

- 3.2 本地方法栈（Native Method Stack）

  - Java 虚拟机栈用于管理 Java 方法的调用，而本地方法栈用于管理本地方法的调用
  - 本地方法栈也是线程私有的
  - 允许线程固定或者可动态扩展的内存大小
    - 如果线程请求分配的栈容量超过本地方法栈允许的最大容量，Java 虚拟机将会抛出一个 `StackOverflowError` 异常
    - 如果本地方法栈可以动态扩展，并且在尝试扩展的时候无法申请到足够的内存，或者在创建新的线程时没有足够的内存去创建对应的本地方法栈，那么 Java虚拟机将会抛出一个`OutofMemoryError`异常
  - 本地方法是使用 C 语言实现的
  - 它的具体做法是 `Native Method Stack` 中登记 native 方法，在 `Execution Engine` 执行时加载本地方法库当某个线程调用一个本地方法时，它就进入了一个全新的并且不再受虚拟机限制的世界。它和虚拟机拥有同样的权限。
  - 本地方法可以通过本地方法接口来访问虚拟机内部的运行时数据区，它甚至可以直接使用本地处理器中的寄存器，直接从本地内存的堆中分配任意数量的内存
  - 并不是所有 JVM 都支持本地方法。因为 Java 虚拟机规范并没有明确要求本地方法栈的使用语言、具体实现方式、数据结构等。如果 JVM 产品不打算支持 native 方法，也可以无需实现本地方法栈
  - 在 Hotspot JVM 中，直接将本地方法栈和虚拟机栈合二为一

  ------

  > **栈是运行时的单位，而堆是存储的单位**。
  >
  > 栈解决程序的运行问题，即程序如何执行，或者说如何处理数据。堆解决的是数据存储的问题，即数据怎么放、放在哪。

##### 四、堆内存

- 4.1 内存划分
  
  对于大多数应用，Java 堆是 Java 虚拟机管理的内存中最大的一块，被所有线程共享。此内存区域的唯一目的就是存放对象实例，几乎所有的对象实例以及数据都在这里分配内存。
  
  为了进行高效的垃圾回收，虚拟机把堆内存**逻辑上**划分成三块区域（分代的唯一理由就是优化 GC 性能）：
  
  - 新生代（年轻代）：新对象和没达到一定年龄的对象都在新生代
  - 老年代（养老区）：被长时间使用的对象，老年代的内存空间应该要比年轻代更大
  - 元空间（JDK1.8 之前叫永久代）：像一些方法中的操作临时对象等，JDK1.8 之前是占用 JVM 内存，JDK1.8 之后直接使用物理内存
  
  ![JDK7](https://tva1.sinaimg.cn/large/00831rSTly1gdbr7ek6pfj30ci0560t4.jpg)
  
  Java 虚拟机规范规定，Java 堆可以是处于物理上不连续的内存空间中，只要逻辑上是连续的即可，像磁盘空间一样。实现时，既可以是固定大小，也可以是可扩展的，主流虚拟机都是可扩展的（通过 `-Xmx` 和 `-Xms` 控制），如果堆中没有完成实例分配，并且堆无法再扩展时，就会抛出 `OutOfMemoryError` 异常。
  
  - 年轻代 (Young Generation)
  
    年轻代是所有新对象创建的地方。当填充年轻代时，执行垃圾收集。这种垃圾收集称为 **Minor GC**。年轻一代被分为三个部分——伊甸园（**Eden Memory**）和两个幸存区（**Survivor Memory**，被称为from/to或s0/s1），默认比例是`8:1:1`
  
    - 大多数新创建的对象都位于 Eden 内存空间中
    - 当 Eden 空间被对象填充时，执行**Minor GC**，并将所有幸存者对象移动到一个幸存者空间中
    - Minor GC 检查幸存者对象，并将它们移动到另一个幸存者空间。所以每次，一个幸存者空间总是空的
    - 经过多次 GC 循环后存活下来的对象被移动到老年代。通常，这是通过设置年轻一代对象的年龄阈值来实现的，然后他们才有资格提升到老一代
  
  - 老年代(Old Generation)
  
    旧的一代内存包含那些经过许多轮小型 GC 后仍然存活的对象。通常，垃圾收集是在老年代内存满时执行的。老年代垃圾收集称为 主GC（Major GC），通常需要更长的时间。
  
    大对象直接进入老年代（大对象是指需要大量连续内存空间的对象）。这样做的目的是避免在 Eden 区和两个Survivor 区之间发生大量的内存拷贝
  
    <img src="https://tva1.sinaimg.cn/large/007S8ZIlly1gg06065oa9j31kw0u0q69.jpg" alt="img" style="zoom:67%;" />
  
  - 元空间
  
    不管是 JDK8 之前的永久代，还是 JDK8 及以后的元空间，都可以看作是 Java 虚拟机规范中方法区的实现。
  
    虽然 Java 虚拟机规范把方法区描述为堆的一个逻辑部分，但是它却有一个别名叫 Non-Heap（非堆），目的应该是与 Java 堆区分开。
  
    所以元空间放在后边的方法区再说。
  
- 4.2 设置堆内存大小和 OOM
  
  Java 堆用于存储 Java 对象实例，那么堆的大小在 JVM 启动的时候就确定了，我们可以通过 `-Xmx` 和 `-Xms` 来设定
  
  - `-Xms` 用来表示堆的起始内存，等价于 `-XX:InitialHeapSize`
  - `-Xmx` 用来表示堆的最大内存，等价于 `-XX:MaxHeapSize`
  
  如果堆的内存大小超过 `-Xmx` 设定的最大内存， 就会抛出 `OutOfMemoryError` 异常。
  
  我们通常会将 `-Xmx` 和 `-Xms` 两个参数配置为相同的值，其目的是为了能够在垃圾回收机制清理完堆区后不再需要重新分隔计算堆的大小，从而提高性能
  
  - 默认情况下，初始堆内存大小为：电脑内存大小/64
  - 默认情况下，最大堆内存大小为：电脑内存大小/4
  
  可以通过代码获取到我们的设置值，当然也可以模拟 OOM：
  
  ````java
  public static void main(String[] args) {
  
    //返回 JVM 堆大小
    long initalMemory = Runtime.getRuntime().totalMemory() / 1024 /1024;
    //返回 JVM 堆的最大内存
    long maxMemory = Runtime.getRuntime().maxMemory() / 1024 /1024;
  
    System.out.println("-Xms : "+initalMemory + "M");
    System.out.println("-Xmx : "+maxMemory + "M");
  
    System.out.println("系统内存大小：" + initalMemory * 64 / 1024 + "G");
    System.out.println("系统内存大小：" + maxMemory * 4 / 1024 + "G");
  }
  ````
  
  - 查看 JVM 堆内存分配
  
    1. 在默认不配置 JVM 堆内存大小的情况下，JVM 根据默认值来配置当前内存大小
  
    2. 默认情况下新生代和老年代的比例是 1:2，可以通过 `–XX:NewRatio` 来配置
  
       - 新生代中的 **Eden**:**From Survivor**:**To Survivor** 的比例是 **8:1:1**，可以通过 `-XX:SurvivorRatio` 来配置
  
    3. 若在 JDK 7 中开启了 `-XX:+UseAdaptiveSizePolicy`，JVM 会动态调整 JVM 堆中各个区域的大小以及进入老年代的年龄
  
       此时 `–XX:NewRatio` 和 `-XX:SurvivorRatio`  将会失效，而 JDK 8 是默认开启`-XX:+UseAdaptiveSizePolicy`
  
       在 JDK 8中，**不要随意关闭**`-XX:+UseAdaptiveSizePolicy`，除非对堆内存的划分有明确的规划
  
    每次 GC 后都会重新计算 Eden、From Survivor、To Survivor 的大小
  
    计算依据是**GC过程**中统计的**GC时间**、**吞吐量**、**内存占用量**
  
    ````java
    java -XX:+PrintFlagsFinal -version | grep HeapSize
        uintx ErgoHeapSizeLimit                         = 0                                   {product}
        uintx HeapSizePerGCThread                       = 87241520                            {product}
        uintx InitialHeapSize                          := 134217728                           {product}
        uintx LargePageHeapSizeThreshold                = 134217728                           {product}
        uintx MaxHeapSize                              := 2147483648                          {product}
    java version "1.8.0_211"
    Java(TM) SE Runtime Environment (build 1.8.0_211-b12)
    Java HotSpot(TM) 64-Bit Server VM (build 25.211-b12, mixed mode)
    ````
  
    ````sh
    $ jmap -heap 进程号
    ````
  
- 4.3 对象在堆中的生命周期

  1. 在 JVM 内存模型的堆中，堆被划分为新生代和老年代
     - 新生代又被进一步划分为 **Eden区** 和 **Survivor区**，Survivor 区由 **From Survivor** 和 **To Survivor** 组成
  2. 当创建一个对象时，对象会被优先分配到新生代的 Eden 区
     - 此时 JVM 会给对象定义一个**对象年轻计数器**（`-XX:MaxTenuringThreshold`）
  3. 当 Eden 空间不足时，JVM 将执行新生代的垃圾回收（Minor GC）
     - JVM 会把存活的对象转移到 Survivor 中，并且对象年龄 +1
  4. 对象在 Survivor 中同样也会经历 Minor GC，每经历一次 Minor GC，对象年龄都会+1

  如果分配的对象超过了`-XX:PetenureSizeThreshold`，对象会**直接被分配到老年代**

- 4.4 对象的分配过程

  为对象分配内存是一件非常严谨和复杂的任务，JVM 的设计者们不仅需要考虑内存如何分配、在哪里分配等问题，并且由于内存分配算法和内存回收算法密切相关，所以还需要考虑 GC 执行完内存回收后是否会在内存空间中产生内存碎片。

  1. new 的对象先放在伊甸园区，此区有大小限制
  2. 当伊甸园的空间填满时，程序又需要创建对象，JVM 的垃圾回收器将对伊甸园区进行垃圾回收（Minor GC），将伊甸园区中的不再被其他对象所引用的对象进行销毁。再加载新的对象放到伊甸园区
  3. 然后将伊甸园中的剩余对象移动到幸存者 0 区
  4. 如果再次触发垃圾回收，此时上次幸存下来的放到幸存者 0 区，如果没有回收，就会放到幸存者 1 区
  5. 如果再次经历垃圾回收，此时会重新放回幸存者 0 区，接着再去幸存者 1 区
  6. 什么时候才会去养老区呢？ 默认是 15 次回收标记
  7. 在养老区，相对悠闲。当养老区内存不足时，再次触发 Major GC，进行养老区的内存清理
  8. 若养老区执行了 Major GC  之后发现依然无法进行对象的保存，就会产生 OOM 异常

- 4.5 GC 垃圾回收简介
  
  - Minor GC、Major GC、Full GC
  
    JVM 在进行 GC 时，并非每次都对堆内存（新生代、老年代；方法区）区域一起回收的，大部分时候回收的都是指新生代。
  
    针对 HotSpot VM 的实现，它里面的 GC 按照回收区域又分为两大类：部分收集（Partial GC），整堆收集（Full  GC）
  
    - 部分收集：不是完整收集整个 Java 堆的垃圾收集。其中又分为：
      - 新生代收集（Minor GC/Young GC）：只是新生代的垃圾收集
      - 老年代收集（Major GC/Old GC）：只是老年代的垃圾收集
        - 目前，只有 CMS GC 会有单独收集老年代的行为
        - 很多时候 Major GC 会和 Full GC  混合使用，需要具体分辨是老年代回收还是整堆回收
      - 混合收集（Mixed GC）：收集整个新生代以及部分老年代的垃圾收集
        - 目前只有 G1 GC 会有这种行为
    - 整堆收集（Full GC）：收集整个 Java 堆和方法区的垃圾
  
- 4.6 TLAB
  
  - 什么是 TLAB （Thread Local Allocation Buffer）?
  
    - 从内存模型而不是垃圾回收的角度，对 Eden 区域继续进行划分，JVM 为每个线程分配了一个私有缓存区域，它包含在 Eden 空间内
    - 多线程同时分配内存时，使用 TLAB 可以避免一系列的非线程安全问题，同时还能提升内存分配的吞吐量，因此我们可以将这种内存分配方式称为**快速分配策略**
    - OpenJDK 衍生出来的 JVM 大都提供了 TLAB 设计
  
  - 为什么要有 TLAB ?
  
    - 堆区是线程共享的，任何线程都可以访问到堆区中的共享数据
    - 由于对象实例的创建在 JVM 中非常频繁，因此在并发环境下从堆区中划分内存空间是线程不安全的
    - 为避免多个线程操作同一地址，需要使用加锁等机制，进而影响分配速度
  
    尽管不是所有的对象实例都能够在 TLAB 中成功分配内存，但 JVM 确实是将 TLAB 作为内存分配的首选。
  
    在程序中，可以通过 `-XX:UseTLAB` 设置是否开启 TLAB 空间。
  
    默认情况下，TLAB 空间的内存非常小，仅占有整个 Eden 空间的 1%，我们可以通过 `-XX:TLABWasteTargetPercent` 设置 TLAB 空间所占用 Eden 空间的百分比大小。
  
    一旦对象在 TLAB 空间分配内存失败时，JVM 就会尝试着通过使用加锁机制确保数据操作的原子性，从而直接在 Eden 空间中分配内存。
  
- 4.7 堆是分配对象存储的唯一选择吗
  
  > 随着 JIT 编译期的发展和逃逸分析技术的逐渐成熟，栈上分配、标量替换优化技术将会导致一些微妙的变化，所有的对象都分配到堆上也渐渐变得不那么“绝对”了。 ——《深入理解 Java 虚拟机》
  
  - 逃逸分析
    
    **逃逸分析(Escape Analysis)是目前 Java 虚拟机中比较前沿的优化技术**。这是一种可以有效减少 Java 程序中同步负载和内存堆分配压力的跨函数全局数据流分析算法。通过逃逸分析，Java Hotspot 编译器能够分析出一个新的对象的引用的使用范围从而决定是否要将这个对象分配到堆上。
    
    逃逸分析的基本行为就是分析对象动态作用域：
    
    - 当一个对象在方法中被定义后，对象只在方法内部使用，则认为没有发生逃逸。
    - 当一个对象在方法中被定义后，它被外部方法所引用，则认为发生逃逸。例如作为调用参数传递到其他地方中，称为方法逃逸。
    
    例如：
    
    ````java
    public static StringBuffer craeteStringBuffer(String s1, String s2) {
       StringBuffer sb = new StringBuffer();
       sb.append(s1);
       sb.append(s2);
       return sb;
    }
    ````
    
    `StringBuffer sb`是一个方法内部变量，上述代码中直接将sb返回，这样这个 StringBuffer 有可能被其他方法所改变，这样它的作用域就不只是在方法内部，虽然它是一个局部变量，称其逃逸到了方法外部。甚至还有可能被外部线程访问到，譬如赋值给类变量或可以在其他线程中访问的实例变量，称为线程逃逸。
    
    上述代码如果想要 `StringBuffer sb`不逃出方法，可以这样写：
    
    ````java
    public static String createStringBuffer(String s1, String s2) {
       StringBuffer sb = new StringBuffer();
       sb.append(s1);
       sb.append(s2);
       return sb.toString();
    }
    ````
    
    不直接返回 StringBuffer，那么 StringBuffer 将不会逃逸出方法。
    
    **参数设置：**
    
    - 在 JDK 6u23 版本之后，HotSpot 中默认就已经开启了逃逸分析
    - 如果使用较早版本，可以通过`-XX"+DoEscapeAnalysis`显式开启
    
    开发中使用局部变量，就不要在方法外定义。
    
    使用逃逸分析，编译器可以对代码做优化：
    
    - **栈上分配**：将堆分配转化为栈分配。如果一个对象在子程序中被分配，要使指向该对象的指针永远不会逃逸，对象可能是栈分配的候选，而不是堆分配
    - **同步省略**：如果一个对象被发现只能从一个线程被访问到，那么对于这个对象的操作可以不考虑同步
    - **分离对象或标量替换**：有的对象可能不需要作为一个连续的内存结构存在也可以被访问到，那么对象的部分（或全部）可以不存储在内存，而存储在 CPU 寄存器
    
    JIT 编译器在编译期间根据逃逸分析的结果，发现如果一个对象并没有逃逸出方法的话，就可能被优化成栈上分配。分配完成后，继续在调用栈内执行，最后线程结束，栈空间被回收，局部变量对象也被回收。这样就无需进行垃圾回收了。
    
    常见栈上分配的场景：成员变量赋值、方法返回值、实例引用传递
    
    #### **代码优化之同步省略（消除）**
    
    - 线程同步的代价是相当高的，同步的后果是降低并发性和性能
    - 在动态编译同步块的时候，JIT 编译器可以借助逃逸分析来判断同步块所使用的锁对象是否能够被一个线程访问而没有被发布到其他线程。如果没有，那么 JIT 编译器在编译这个同步块的时候就会取消对这个代码的同步。这样就能大大提高并发性和性能。这个取消同步的过程就叫做**同步省略，也叫锁消除**。
    
    ```java
    public void keep() {
      Object keeper = new Object();
      synchronized(keeper) {
        System.out.println(keeper);
      }
    }
    ```
    
    如上代码，代码中对 keeper 这个对象进行加锁，但是 keeper 对象的生命周期只在 `keep()`方法中，并不会被其他线程所访问到，所以在 JIT编译阶段就会被优化掉。优化成：
    
    ```java
    public void keep() {
      Object keeper = new Object();
      System.out.println(keeper);
    }
    ```
    
    #### **代码优化之标量替换**
    
    **标量**（Scalar）是指一个无法再分解成更小的数据的数据。Java 中的原始数据类型就是标量。
    
    相对的，那些的还可以分解的数据叫做**聚合量**（Aggregate），Java 中的对象就是聚合量，因为其还可以分解成其他聚合量和标量。
    
    在 JIT 阶段，通过逃逸分析确定该对象不会被外部访问，并且对象可以被进一步分解时，JVM 不会创建该对象，而会将该对象成员变量分解若干个被这个方法使用的成员变量所代替。这些代替的成员变量在栈帧或寄存器上分配空间。这个过程就是**标量替换**。
    
    通过 `-XX:+EliminateAllocations` 可以开启标量替换，`-XX:+PrintEliminateAllocations` 查看标量替换情况。
    
    ````java
    public static void main(String[] args) {
       alloc();
    }
    
    private static void alloc() {
       Point point = new Point（1,2）;
       System.out.println("point.x="+point.x+"; point.y="+point.y);
    }
    class Point{
        private int x;
        private int y;
    }
    ````
    
    以上代码中，point 对象并没有逃逸出 `alloc()` 方法，并且 point 对象是可以拆解成标量的。那么，JIT 就不会直接创建 Point 对象，而是直接使用两个标量 int x ，int y 来替代 Point 对象。
    
    ````java
    private static void alloc() {
       int x = 1;
       int y = 2;
       System.out.println("point.x="+x+"; point.y="+y);
    }
    ````
    
    #### 代码优化之栈上分配
    
    我们通过 JVM 内存分配可以知道 JAVA 中的对象都是在堆上进行分配，当对象没有被引用的时候，需要依靠 GC 进行回收内存，如果对象数量较多的时候，会给 GC 带来较大压力，也间接影响了应用的性能。为了减少临时对象在堆内分配的数量，JVM 通过逃逸分析确定该对象不会被外部访问。那就通过标量替换将该对象分解在栈上分配内存，这样该对象所占用的内存空间就可以随栈帧出栈而销毁，就减轻了垃圾回收的压力。
    
    **总结：**
    
    关于逃逸分析的论文在1999年就已经发表了，但直到JDK 1.6才有实现，而且这项技术到如今也并不是十分成熟的。
    
    **其根本原因就是无法保证逃逸分析的性能消耗一定能高于他的消耗。虽然经过逃逸分析可以做标量替换、栈上分配、和锁消除。但是逃逸分析自身也是需要进行一系列复杂的分析的，这其实也是一个相对耗时的过程。**
    
    一个极端的例子，就是经过逃逸分析之后，发现没有一个对象是不逃逸的。那这个逃逸分析的过程就白白浪费掉了。
    
    虽然这项技术并不十分成熟，但是他也是即时编译器优化技术中一个十分重要的手段。

##### 五、方法区

- 5.1 解惑

  你是否也有看不同的参考资料，有的内存结构图有方法区，有的又是永久代，元数据区，一脸懵逼的时候？

  - **方法区（method area）**只是 **JVM 规范**中定义的一个**概念**，用于存储类信息、常量池、静态变量、JIT编译后的代码等数据，并没有规定如何去实现它，不同的厂商有不同的实现。而**永久代（PermGen）**是 **Hotspot** 虚拟机特有的概念， Java8 的时候又被**元空间**取代了，永久代和元空间都可以理解为方法区的落地实现。
  - 永久代物理是堆的一部分，和新生代，老年代地址是连续的（受垃圾回收器管理），而元空间存在于本地内存（我们常说的堆外内存，不受垃圾回收器管理），这样就不受 JVM 限制了，也比较难发生OOM（都会有溢出异常）
  - Java7 中我们通过`-XX:PermSize` 和 `-xx:MaxPermSize` 来设置永久代参数，Java8 之后，随着永久代的取消，这些参数也就随之失效了，改为通过`-XX:MetaspaceSize` 和 `-XX:MaxMetaspaceSize` 用来设置元空间参数
  - 存储内容不同，元空间存储类的元信息，静态变量和常量池等并入堆中。相当于永久代的数据被分到了堆和元空间中
  - 如果方法区域中的内存不能用于满足分配请求，则 Java 虚拟机抛出 `OutOfMemoryError`
  - JVM 规范说方法区在逻辑上是堆的一部分，但目前实际上是与 Java 堆分开的（Non-Heap）

  所以对于方法区，Java8 之后的变化：

  - 移除了永久代（PermGen），替换为元空间（Metaspace）；
  - 永久代中的 class metadata 转移到了 native memory（本地内存，而不是虚拟机）；
  - 永久代中的 interned Strings 和 class static variables 转移到了 Java heap；
  - 永久代参数 （PermSize MaxPermSize） -> 元空间参数（MetaspaceSize MaxMetaspaceSize）

- 5.2 设置方法区内存的大小

  JDK8 及以后：

  - 元数据区大小可以使用参数 `-XX:MetaspaceSize` 和 `-XX:MaxMetaspaceSize` 指定，替代上述原有的两个参数
  - 默认值依赖于平台。Windows 下，`-XX:MetaspaceSize` 是 21M，`-XX:MaxMetaspacaSize` 的值是 -1，即没有限制
  - 与永久代不同，如果不指定大小，默认情况下，虚拟机会耗尽所有的可用系统内存。如果元数据发生溢出，虚拟机一样会抛出异常 `OutOfMemoryError:Metaspace`
  - `-XX:MetaspaceSize` ：设置初始的元空间大小。对于一个 64 位的服务器端 JVM 来说，其默认的 `-XX:MetaspaceSize` 的值为20.75MB，这就是初始的高水位线，一旦触及这个水位线，Full GC 将会被触发并卸载没用的类（即这些类对应的类加载器不再存活），然后这个高水位线将会重置，新的高水位线的值取决于 GC 后释放了多少元空间。如果释放的空间不足，那么在不超过 `MaxMetaspaceSize`时，适当提高该值。如果释放空间过多，则适当降低该值
  - 如果初始化的高水位线设置过低，上述高水位线调整情况会发生很多次，通过垃圾回收的日志可观察到 Full GC 多次调用。为了避免频繁 GC，建议将 `-XX:MetaspaceSize` 设置为一个相对较高的值。

- 5.3 方法区内部结构
  
  方法区用于存储已被虚拟机加载的类型信息、常量、静态变量、即时编译器编译后的代码缓存等。
  
  - 类型信息
  
    对每个加载的类型（类 class、接口 interface、枚举 enum、注解 annotation），JVM 必须在方法区中存储以下类型信息
  
    - 这个类型的完整有效名称（全名=包名.类名）
    - 这个类型直接父类的完整有效名（对于 interface或是 java.lang.Object，都没有父类）
    - 这个类型的修饰符（public，abstract，final 的某个子集）
    - 这个类型直接接口的一个有序列表
  
  - 域（Field）信息
  
    JVM 必须在方法区中保存类型的所有域的相关信息以及域的声明顺序
  
    域的相关信息包括：域名称、域类型、域修饰符（public、private、protected、static、final、volatile、transient 的某个子集）
  
  - 方法（Method）信息
  
    JVM 必须保存所有方法的
  
    - 方法名称
    - 方法的返回类型
    - 方法参数的数量和类型
    - 方法的修饰符（public，private，protected，static，final，synchronized，native，abstract 的一个子集）
    - 方法的字符码（bytecodes）、操作数栈、局部变量表及大小（abstract 和 native 方法除外）
    - 异常表（abstract 和 native 方法除外）
      - 每个异常处理的开始位置、结束位置、代码处理在程序计数器中的偏移地址、被捕获的异常类的常量池索引
  
    **栈、堆、方法区的交互关系**
  
    <img src="https://static01.imgkr.com/temp/db050d0052a44605a13043a0bec204f0.png" alt="img" style="zoom:75%;" />
  
- 5.4 运行时常量池
  
  运行时常量池（Runtime Constant Pool）是方法区的一部分，理解运行时常量池的话，我们先来说说字节码文件（Class 文件）中的常量池（常量池表）
  
  - 常量池
    
    一个有效的字节码文件中除了包含类的版本信息、字段、方法以及接口等描述信息外，还包含一项信息那就是常量池表（Constant Pool Table），包含各种字面量和对类型、域和方法的符号引用。
    
    - 为什么需要常量池？
    
      一个 Java 源文件中的类、接口，编译后产生一个字节码文件。而 Java 中的字节码需要数据支持，通常这种数据会很大以至于不能直接存到字节码里，换另一种方式，可以存到常量池，这个字节码包含了指向常量池的引用。在动态链接的时候用到的就是运行时常量池。
    
      如下，我们通过 jclasslib 查看一个只有 Main 方法的简单类，字节码中的 #2 指向的就是 Constant Pool
    
      ![img](https://tva1.sinaimg.cn/large/007S8ZIlly1gg9i91ze2gj320i0riahe.jpg)
    
      常量池可以看作是一张表，虚拟机指令根据这张常量表找到要执行的类名、方法名、参数类型、字面量等类型。
    
  - 运行时常量池
  
    在加载类和结构到虚拟机后，就会创建对应的运行时常量池
  
    常量池表（Constant Pool Table）是 Class 文件的一部分，用于存储编译期生成的各种字面量和符号引用，**这部分内容将在类加载后存放到方法区的运行时常量池中**
  
    JVM 为每个已加载的类型（类或接口）都维护一个常量池。池中的数据项像数组项一样，是通过索引访问的
  
    运行时常量池中包含各种不同的常量，包括编译器就已经明确的数值字面量，也包括到运行期解析后才能够获得的方法或字段引用。此时不再是常量池中的符号地址了，这里换为真实地址
  
    - 运行时常量池，相对于 Class 文件常量池的另一个重要特征是：**动态性**，Java 语言并不要求常量一定只有编译期间才能产生，运行期间也可以将新的常量放入池中，String 类的 `intern()` 方法就是这样的
  
    当创建类或接口的运行时常量池时，如果构造运行时常量池所需的内存空间超过了方法区所能提供的最大值，则 JVM 会抛出 OutOfMemoryError 异常
  
- 5.5 方法区在 JDK6、7、8中的演进细节
  
  只有 HotSpot 才有永久代的概念
  
  | jdk1.6及之前 | 有永久代，静态变量存放在永久代上                             |
  | ------------ | ------------------------------------------------------------ |
  | jdk1.7       | 有永久代，但已经逐步“去永久代”，字符串常量池、静态变量移除，保存在堆中 |
  | jdk1.8及之后 | 取消永久代，类型信息、字段、方法、常量保存在本地内存的元空间，但字符串常量池、静态变量仍在堆中 |
  
  - 移除永久代原因
  
    http://openjdk.java.net/jeps/122
  
    <img src="https://tva1.sinaimg.cn/large/007S8ZIlly1gg04ve34c2j30z00u0dp7.jpg" alt="img" style="zoom:67%;" />
  
    - 为永久代设置空间大小是很难确定的。
  
      在某些场景下，如果动态加载类过多，容易产生 Perm 区的 OOM。如果某个实际 Web 工程中，因为功能点比较多，在运行过程中，要不断动态加载很多类，经常出现 OOM。而元空间和永久代最大的区别在于，元空间不在虚拟机中，而是使用本地内存，所以默认情况下，元空间的大小仅受本地内存限制
  
    - 对永久代进行调优较困难
  
- 5.6 方法区的垃圾回收

  方法区的垃圾收集主要回收两部分内容：**常量池中废弃的常量和不再使用的类型**。

  先来说说方法区内常量池之中主要存放的两大类常量：字面量和符号引用。字面量比较接近 Java 语言层次的常量概念，如文本字符串、被声明为 final 的常量值等。而符号引用则属于编译原理方面的概念，包括下面三类常量：

  - 类和接口的全限定名
  - 字段的名称和描述符
  - 方法的名称和描述符

  HotSpot 虚拟机对常量池的回收策略是很明确的，只要常量池中的常量没有被任何地方引用，就可以被回收

  判定一个类型是否属于“不再被使用的类”，需要同时满足三个条件：

  - 该类所有的实例都已经被回收，也就是 Java 堆中不存在该类及其任何派生子类的实例
  - 加载该类的类加载器已经被回收，这个条件除非是经过精心设计的可替换类加载器的场景，如 OSGi、JSP 的重加载等，否则通常很难达成
  - 该类对应的 java.lang.Class 对象没有在任何地方被引用，无法在任何地方通过反射访问该类的方法

  Java 虚拟机被允许堆满足上述三个条件的无用类进行回收，这里说的仅仅是“被允许”，而并不是和对象一样，不使用了就必然会回收。是否对类进行回收，HotSpot 虚拟机提供了 `-Xnoclassgc` 参数进行控制，还可以使用 `-verbose:class` 以及 `-XX:+TraceClassLoading` 、`-XX:+TraceClassUnLoading` 查看类加载和卸载信息。

  在大量使用反射、动态代理、CGLib 等 ByteCode 框架、动态生成 JSP 以及 OSGi 这类频繁自定义 ClassLoader 的场景都需要虚拟机具备类卸载的功能，以保证永久代不会溢出。

##### Java8中的JVM元空间是不是方法区？

严格来说，不是。

1. 首先，方法区是JVM规范的一个概念定义，并不是一个具体的实现，每一个JVM的实现都可以有各自的实现；
2. 然后，在Java官方的HotSpot 虚拟机中，Java8版本以后，是用元空间来实现的方法区；在Java8之前的版本，则是用永久代实现的方法区；
3. 也就是说，“元空间” 和 “方法区”，一个是HotSpot 的具体实现技术，一个是JVM规范的抽象定义；

所以，并不能说“JVM的元空间是方法区”，但是可以说在Java8以后的HotSpot 中“元空间用来实现了方法区”。

然后多说一句，这个元空间是使用本地内存（Native Memory）实现的，也就是说它的内存是不在虚拟机内的，所以可以理论上物理机器还有多个内存就可以分配，而不用再受限于JVM本身分配的内存了。

### JVM 基础 - Java 内存模型引入（Java Memory Model）

JVM 基础 - Java 内存模型引入

> 很多人都无法区分Java内存模型和JVM内存结构，以及Java内存模型与物理内存之间的关系。本文从堆栈角度引入JMM，然后介绍JMM和物理内存之间的关系, 为后面`JMM详解`, `JVM 内存结构详解`, `Java 对象模型详解`等铺垫。@pdai

#### JMM引入

- 从堆栈说起

  JVM内部使用的Java内存模型在线程栈和堆之间划分内存。 此图从逻辑角度说明了Java内存模型：

  ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/jvm/java-memory-model-1.png)

- 堆栈里面放了什么? 

  线程堆栈还包含正在执行的每个方法的所有局部变量(调用堆栈上的所有方法)。 线程只能访问它自己的线程堆栈。 由线程创建的局部变量对于创建它的线程以外的所有其他线程是不可见的。 即使两个线程正在执行完全相同的代码，两个线程仍将在每个自己的线程堆栈中创建该代码的局部变量。 因此，每个线程都有自己的每个局部变量的版本。

  基本类型的所有局部变量(boolean，byte，short，char，int，long，float，double)完全存储在线程堆栈中，因此对其他线程不可见。 一个线程可以将一个基本类型变量的副本传递给另一个线程，但它不能共享原始局部变量本身。

  堆包含了在Java应用程序中创建的所有对象，无论创建该对象的线程是什么。 这包括基本类型的包装类(例如Byte，Integer，Long等)。 无论是创建对象并将其分配给局部变量，还是创建为另一个对象的成员变量，该对象仍然存储在堆上。

  ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/jvm/java-memory-model-2.png)

  局部变量可以是基本类型，在这种情况下，它完全保留在线程堆栈上。

  局部变量也可以是对象的引用。 在这种情况下，引用(局部变量)存储在线程堆栈中，但是对象本身存储在堆(Heap)上。

  对象的成员变量与对象本身一起存储在堆上。 当成员变量是基本类型时，以及它是对象的引用时都是如此。

  静态类变量也与类定义一起存储在堆上。

- 线程栈如何访问堆上对象?

  所有具有对象引用的线程都可以访问堆上的对象。 当一个线程有权访问一个对象时，它也可以访问该对象的成员变量。 如果两个线程同时在同一个对象上调用一个方法，它们都可以访问该对象的成员变量，但每个线程都有自己的局部变量副本。

  ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/jvm/java-memory-model-3.png)

  两个线程有一组局部变量。 其中一个局部变量(局部变量2)指向堆上的共享对象(对象3)。 两个线程各自对同一对象具有不同的引用。 它们的引用是局部变量，因此存储在每个线程的线程堆栈中(在每个线程堆栈上)。 但是，这两个不同的引用指向堆上的同一个对象。

  注意共享对象(对象3)如何将对象2和对象4作为成员变量引用(由对象3到对象2和对象4的箭头所示)。 通过对象3中的这些成员变量引用，两个线程可以访问对象2和对象4.

  该图还显示了一个局部变量，该变量指向堆上的两个不同对象。 在这种情况下，引用指向两个不同的对象(对象1和对象5)，而不是同一个对象。 理论上，如果两个线程都引用了两个对象，则两个线程都可以访问对象1和对象5。 但是在上图中，每个线程只引用了两个对象中的一个。

- 线程栈访问堆示例

  那么，什么样的Java代码可以导致上面的内存图? 好吧，代码就像下面的代码一样简单：

  ````java
  public class MyRunnable implements Runnable() {
  
      public void run() {
          methodOne();
      }
  
      public void methodOne() {
          int localVariable1 = 45;
  
          MySharedObject localVariable2 =
              MySharedObject.sharedInstance;
  
          //... do more with local variables.
  
          methodTwo();
      }
  
      public void methodTwo() {
          Integer localVariable1 = new Integer(99);
  
          //... do more with local variable.
      }
  }
  
  public class MySharedObject {
  
      //static variable pointing to instance of MySharedObject
  
      public static final MySharedObject sharedInstance =
          new MySharedObject();
  
  
      //member variables pointing to two objects on the heap
  
      public Integer object2 = new Integer(22);
      public Integer object4 = new Integer(44);
  
      public long member1 = 12345;
      public long member1 = 67890;
  }
  ````

  如果两个线程正在执行run()方法，则前面显示的图表将是结果。 run()方法调用methodOne()，methodOne()调用methodTwo()。

  methodOne()声明一个局部基本类型变量(类型为int的localVariable1)和一个局部变量，它是一个对象引用(localVariable2)。

  执行methodOne()的每个线程将在各自的线程堆栈上创建自己的localVariable1和localVariable2副本。 localVariable1变量将完全相互分离，只存在于每个线程的线程堆栈中。 一个线程无法看到另一个线程对其localVariable1副本所做的更改。

  执行methodOne()的每个线程也将创建自己的localVariable2副本。 但是，localVariable2的两个不同副本最终都指向堆上的同一个对象。 代码将localVariable2设置为指向静态变量引用的对象。 静态变量只有一个副本，此副本存储在堆上。 因此，localVariable2的两个副本最终都指向静态变量指向的MySharedObject的同一个实例。 MySharedObject实例也存储在堆上。 它对应于上图中的对象3。

  注意MySharedObject类还包含两个成员变量。 成员变量本身与对象一起存储在堆上。 两个成员变量指向另外两个Integer对象。 这些Integer对象对应于上图中的Object 2和Object 4。

  另请注意methodTwo()如何创建名为localVariable1的局部变量。 此局部变量是对Integer对象的对象引用。 该方法将localVariable1引用设置为指向新的Integer实例。 localVariable1引用将存储在执行methodTwo()的每个线程的一个副本中。 实例化的两个Integer对象将存储在堆上，但由于该方法每次执行该方法时都会创建一个新的Integer对象，因此执行此方法的两个线程将创建单独的Integer实例。 在methodTwo()中创建的Integer对象对应于上图中的Object 1和Object 5。

  另请注意类型为long的MySharedObject类中的两个成员变量，它们是基本类型。 由于这些变量是成员变量，因此它们仍与对象一起存储在堆上。 只有局部变量存储在线程堆栈中。

#### JMM与硬件内存结构关系

- 硬件内存结构简介

  现代硬件内存架构与内部Java内存模型略有不同。 了解硬件内存架构也很重要，以了解Java内存模型如何与其一起工作。 本节介绍了常见的硬件内存架构，后面的部分将介绍Java内存模型如何与其配合使用。

  这是现代计算机硬件架构的简化图：

  ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/jvm/java-memory-model-4.png)

  现代计算机通常有2个或更多CPU。 其中一些CPU也可能有多个内核。 关键是，在具有2个或更多CPU的现代计算机上，可以同时运行多个线程。 每个CPU都能够在任何给定时间运行一个线程。 这意味着如果您的Java应用程序是多线程的，线程真的在可能同时运行.

  每个CPU基本上都包含一组在CPU内存中的寄存器。 CPU可以在这些寄存器上执行的操作比在主存储器中对变量执行的操作快得多。 这是因为CPU可以比访问主存储器更快地访问这些寄存器。

  每个CPU还可以具有CPU高速缓存存储器层。 事实上，大多数现代CPU都有一些大小的缓存存储层。 CPU可以比主存储器更快地访问其高速缓存存储器，但通常不会像访问其内部寄存器那样快。 因此，CPU高速缓存存储器介于内部寄存器和主存储器的速度之间。 某些CPU可能有多个缓存层(级别1和级别2)，但要了解Java内存模型如何与内存交互，这一点并不重要。 重要的是要知道CPU可以有某种缓存存储层。

  计算机还包含主存储区(RAM)。 所有CPU都可以访问主内存。 主存储区通常比CPU的高速缓存存储器大得多。同时访问速度也就较慢.

  通常，当CPU需要访问主存储器时，它会将部分主存储器读入其CPU缓存。 它甚至可以将部分缓存读入其内部寄存器，然后对其执行操作。 当CPU需要将结果写回主存储器时，它会将值从其内部寄存器刷新到高速缓冲存储器，并在某些时候将值刷新回主存储器。

- JMM与硬件内存连接 - 引入

  如前所述，Java内存模型和硬件内存架构是不同的。 硬件内存架构不区分线程堆栈和堆。 在硬件上，线程堆栈和堆都位于主存储器中。 线程堆栈和堆的一部分有时可能存在于CPU高速缓存和内部CPU寄存器中。 这在图中说明：

  ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/jvm/java-memory-model-5.png)

  当对象和变量可以存储在计算机的各种不同存储区域中时，可能会出现某些问题。 两个主要问题是：

  - Visibility of thread updates (writes) to shared variables.
  - Race conditions when reading, checking and writing shared variables. 以下各节将解释这两个问题。

- JMM与硬件内存连接 - 对象共享后的可见性

  如果两个或多个线程共享一个对象，而没有正确使用volatile声明或同步，则一个线程对共享对象的更新可能对其他线程不可见。

  想象一下，共享对象最初存储在主存储器中。 然后，在CPU上运行的线程将共享对象读入其CPU缓存中。 它在那里对共享对象进行了更改。 只要CPU缓存尚未刷新回主内存，共享对象的更改版本对于在其他CPU上运行的线程是不可见的。 这样，每个线程最终都可能拥有自己的共享对象副本，每个副本都位于不同的CPU缓存中。

  下图描绘了该情况。 在左CPU上运行的一个线程将共享对象复制到其CPU缓存中，并将其count变量更改为2.对于在右边的CPU上运行的其他线程，此更改不可见，因为计数更新尚未刷新回主内存中.

  ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/jvm/java-memory-model-6.png)

  要解决此问题，您可以使用Java的volatile关键字。 volatile关键字可以确保直接从主内存读取给定变量，并在更新时始终写回主内存。

- JMM与硬件内存连接 - 竞态条件

  如果两个或多个线程共享一个对象，并且多个线程更新该共享对象中的变量，则可能会出现竞态。

  想象一下，如果线程A将共享对象的变量计数读入其CPU缓存中。 想象一下，线程B也做同样的事情，但是进入不同的CPU缓存。 现在，线程A将一个添加到count，而线程B执行相同的操作。 现在var1已经增加了两次，每个CPU缓存一次。

  如果这些增量是按先后顺序执行的，则变量计数将增加两次并将原始值+ 2写回主存储器。

  但是，两个增量同时执行而没有适当的同步。 无论线程A和B中哪一个将其更新后的计数版本写回主存储器，更新的值将仅比原始值高1，尽管有两个增量。

  该图说明了如上所述的竞争条件问题的发生：

  ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/jvm/java-memory-model-7.png)

  要解决此问题，您可以使用Java synchronized块。 同步块保证在任何给定时间只有一个线程可以进入代码的给定关键部分。 同步块还保证在同步块内访问的所有变量都将从主存储器中读入，当线程退出同步块时，所有更新的变量将再次刷新回主存储器，无论变量是不是声明为volatile

### JVM 基础 - Java 内存模型详解

> 本文主要转载自 Info 上[深入理解Java内存模型  (opens new window)](https://www.infoq.cn/article/java_memory_model/), 作者程晓明。这篇文章对JMM讲的很清楚了，大致分三部分：重排序与顺序一致性；三个同步原语（lock，volatile，final）的内存语义，重排序规则及在处理器中的实现；java 内存模型的设计，及其与处理器内存模型和顺序一致性内存模型的关系。@pdai

#### 基础

- 并发编程模型的分类

  线程之间的通信机制有两种：共享内存和消息传递。

  在共享内存的并发模型里，线程之间共享程序的公共状态，线程之间通过写 - 读内存中的公共状态来隐式进行通信。在消息传递的并发模型里，线程之间没有公共状态，线程之间必须通过明确的发送消息来显式进行通信。

  Java 的并发采用的是共享内存模型，Java 线程之间的通信总是隐式进行，整个通信过程对程序员完全透明。

- Java 内存模型的抽象

  从抽象的角度来看，JMM 定义了线程和主内存之间的抽象关系：线程之间的共享变量存储在主内存（main memory）中，每个线程都有一个私有的本地内存（local memory），本地内存中存储了该线程以读 / 写共享变量的副本。本地内存是 JMM 的一个抽象概念，并不真实存在。

  ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/jvm/java-jmm-1.png)

  从上图来看，线程 A 与线程 B 之间如要通信的话，必须要经历下面 2 个步骤：

  - 首先，线程 A 把本地内存 A 中更新过的共享变量刷新到主内存中去。
  - 然后，线程 B 到主内存中去读取线程 A 之前已更新过的共享变量。

- 重排序

  在执行程序时为了提高性能，编译器和处理器常常会对指令做重排序。重排序分三种类型：

  - 编译器优化的重排序。编译器在不改变单线程程序语义的前提下，可以重新安排语句的执行顺序。
  - 指令级并行的重排序。现代处理器采用了指令级并行技术（Instruction-Level Parallelism， ILP）来将多条指令重叠执行。如果不存在数据依赖性，处理器可以改变语句对应机器指令的执行顺序。
  - 内存系统的重排序。由于处理器使用缓存和读 / 写缓冲区，这使得加载和存储操作看上去可能是在乱序执行。

  从 java 源代码到最终实际执行的指令序列，会分别经历下面三种重排序：

  ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/jvm/java-jmm-3.png)

- 处理器重排序与内存屏障指令

  略

- happens-before

  略

#### 重排序

- 数据依赖性

  如果两个操作访问同一个变量，且这两个操作中有一个为写操作，此时这两个操作之间就存在数据依赖性。数据依赖分下列三种类型：

  | 名称   | 代码示例     | 说明                           |
  | ------ | ------------ | ------------------------------ |
  | 写后读 | a = 1;b = a; | 写一个变量之后，再读这个位置。 |
  | 写后写 | a = 1;a = 2; | 写一个变量之后，再写这个变量。 |
  | 读后写 | a = b;b = 1; | 读一个变量之后，再写这个变量。 |

  上面三种情况，只要重排序两个操作的执行顺序，程序的执行结果将会被改变。

- as-if-serial 语义

  as-if-serial 语义的意思指：不管怎么重排序（编译器和处理器为了提高并行度），（单线程）程序的执行结果不能被改变。编译器，runtime 和处理器都必须遵守 as-if-serial 语义。

- 程序顺序规则

  略

- 重排序对多线程的影响

  在单线程程序中，对存在控制依赖的操作重排序，不会改变执行结果（这也是 as-if-serial 语义允许对存在控制依赖的操作做重排序的原因）；但在多线程程序中，对存在控制依赖的操作重排序，可能会改变程序的执行结果。

#### 顺序一致性

- 数据竞争与顺序一致性保证

  当程序未正确同步时，就会存在数据竞争。java 内存模型规范对数据竞争的定义如下：

  - 在一个线程中写一个变量，
  - 在另一个线程读同一个变量，
  - 而且写和读没有通过同步来排序。

  当代码中包含数据竞争时，程序的执行往往产生违反直觉的结果（前一章的示例正是如此）。如果一个多线程程序能正确同步，这个程序将是一个没有数据竞争的程序。

  JMM 对正确同步的多线程程序的内存一致性做了如下保证：

  - 如果程序是正确同步的，程序的执行将具有顺序一致性（sequentially consistent）-- 即程序的执行结果与该程序在顺序一致性内存模型中的执行结果相同（马上我们将会看到，这对于程序员来说是一个极强的保证）。这里的同步是指广义上的同步，包括对常用同步原语（lock，volatile 和 final）的正确使用。

- 顺序一致性内存模型

  顺序一致性内存模型是一个被计算机科学家理想化了的理论参考模型。

  - 一个线程中的所有操作必须按照程序的顺序来执行。
  - （不管程序是否同步）所有线程都只能看到一个单一的操作执行顺序。在顺序一致性内存模型中，每个操作都必须原子执行且立刻对所有线程可见

- 同步程序的顺序一致性效果

  在顺序一致性模型中，所有操作完全按程序的顺序串行执行。而在 JMM 中，临界区内的代码可以重排序（但 JMM 不允许临界区内的代码“逸出”到临界区之外，那样会破坏监视器的语义）

- 未同步程序的执行特性

  对于未同步或未正确同步的多线程程序，JMM 只提供最小安全性：线程执行时读取到的值，要么是之前某个线程写入的值，要么是默认值（0，null，false），JMM 保证线程读操作读取到的值不会无中生有（out of thin air）的冒出来

#### 总结

- 处理器内存模型

  顺序一致性内存模型是一个理论参考模型，JMM 和处理器内存模型在设计时通常会把顺序一致性内存模型作为参照。JMM 和处理器内存模型在设计时会对顺序一致性模型做一些放松，因为如果完全按照顺序一致性模型来实现处理器和 JMM，那么很多的处理器和编译器优化都要被禁止，这对执行性能将会有很大的影响。

  根据对不同类型读 / 写操作组合的执行顺序的放松，可以把常见处理器的内存模型划分为下面几种类型：

  - 放松程序中写 - 读操作的顺序，由此产生了 total store ordering 内存模型（简称为 TSO）。
  - 在前面 1 的基础上，继续放松程序中写 - 写操作的顺序，由此产生了 partial store order 内存模型（简称为 PSO）。
  - 在前面 1 和 2 的基础上，继续放松程序中读 - 写和读 - 读操作的顺序，由此产生了 relaxed memory order 内存模型（简称为 RMO）和 PowerPC 内存模型。

  注意，这里处理器对读 / 写操作的放松，是以两个操作之间不存在数据依赖性为前提的（因为处理器要遵守 as-if-serial 语义，处理器不会对存在数据依赖性的两个内存操作做重排序）。

- JMM，处理器内存模型与顺序一致性内存模型之间的关系

  JMM 是一个语言级的内存模型，处理器内存模型是硬件级的内存模型，顺序一致性内存模型是一个理论参考模型。

- JMM 的设计

  从 JMM 设计者的角度来说，在设计 JMM 时，需要考虑两个关键因素：

  - 程序员对内存模型的使用。程序员希望内存模型易于理解，易于编程。程序员希望基于一个强内存模型来编写代码。
  - 编译器和处理器对内存模型的实现。编译器和处理器希望内存模型对它们的束缚越少越好，这样它们就可以做尽可能多的优化来提高性能。编译器和处理器希望实现一个弱内存模型。

  由于这两个因素互相矛盾，所以 JSR-133 专家组在设计 JMM 时的核心目标就是找到一个好的平衡点：一方面要为程序员提供足够强的内存可见性保证；另一方面，对编译器和处理器的限制要尽可能的放松。下面让我们看看 JSR-133 是如何实现这一目标的。

  为了具体说明，请看前面提到过的计算圆面积的示例代码：

  ```java
  double pi  = 3.14;    //A
  double r   = 1.0;     //B
  double area = pi * r * r; //C
  ```

  上面计算圆的面积的示例代码存在三个 happens- before 关系：

  - A happens- before B；
  - B happens- before C；
  - A happens- before C；

  由于 A happens- before B，happens- before 的定义会要求：A 操作执行的结果要对 B 可见，且 A 操作的执行顺序排在 B 操作之前。 但是从程序语义的角度来说，对 A 和 B 做重排序即不会改变程序的执行结果，也还能提高程序的执行性能（允许这种重排序减少了对编译器和处理器优化的束缚）。也就是说，上面这 3 个 happens- before 关系中，虽然 2 和 3 是必需要的，但 1 是不必要的。因此，JMM 把 happens- before 要求禁止的重排序分为了下面两类：

  - 会改变程序执行结果的重排序。
  - 不会改变程序执行结果的重排序。

  JMM 对这两种不同性质的重排序，采取了不同的策略：

  - 对于会改变程序执行结果的重排序，JMM 要求编译器和处理器必须禁止这种重排序。
  - 对于不会改变程序执行结果的重排序，JMM 对编译器和处理器不作要求（JMM 允许这种重排序）。

  下面是 JMM 的设计示意图：

  ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/jvm/java-jmm-x03.png)

  从上图可以看出两点：

  - JMM 向程序员提供的 happens- before 规则能满足程序员的需求。JMM 的 happens- before 规则不但简单易懂，而且也向程序员提供了足够强的内存可见性保证（有些内存可见性保证其实并不一定真实存在，比如上面的 A happens- before B）。
  - JMM 对编译器和处理器的束缚已经尽可能的少。从上面的分析我们可以看出，JMM 其实是在遵循一个基本原则：只要不改变程序的执行结果（指的是单线程程序和正确同步的多线程程序），编译器和处理器怎么优化都行。比如，如果编译器经过细致的分析后，认定一个锁只会被单个线程访问，那么这个锁可以被消除。再比如，如果编译器经过细致的分析后，认定一个 volatile 变量仅仅只会被单个线程访问，那么编译器可以把这个 volatile 变量当作一个普通变量来对待。这些优化既不会改变程序的执行结果，又能提高程序的执行效率。

- JMM 的内存可见性保证

  Java 程序的内存可见性保证按程序类型可以分为下列三类：

  - 单线程程序。单线程程序不会出现内存可见性问题。编译器，runtime 和处理器会共同确保单线程程序的执行结果与该程序在顺序一致性模型中的执行结果相同。
  - 正确同步的多线程程序。正确同步的多线程程序的执行将具有顺序一致性（程序的执行结果与该程序在顺序一致性内存模型中的执行结果相同）。这是 JMM 关注的重点，JMM 通过限制编译器和处理器的重排序来为程序员提供内存可见性保证。
  - 未同步 / 未正确同步的多线程程序。JMM 为它们提供了最小安全性保障：线程执行时读取到的值，要么是之前某个线程写入的值，要么是默认值（0，null，false）

- JSR-133 对旧内存模型的修补

  略

### JVM基础 - Java垃圾回收基础

> 垃圾收集主要是针对堆和方法区进行；程序计数器、虚拟机栈和本地方法栈这三个区域属于线程私有的，只存在于线程的生命周期内，线程结束之后也会消失，因此不需要对这三个区域进行垃圾回收。@pdai

#### 判断一个对象是否可被回收

- 引用计数算法

  给对象添加一个引用计数器，当对象增加一个引用时计数器加 1，引用失效时计数器减 1。引用计数为 0 的对象可被回收。

  两个对象出现循环引用的情况下，此时引用计数器永远不为 0，导致无法对它们进行回收。

  正因为循环引用的存在，因此 Java 虚拟机不使用引用计数算法。

  ```java
  public class ReferenceCountingGC {
  
      public Object instance = null;
  
      public static void main(String[] args) {
          ReferenceCountingGC objectA = new ReferenceCountingGC();
          ReferenceCountingGC objectB = new ReferenceCountingGC();
          objectA.instance = objectB;
          objectB.instance = objectA;
      }
  }
  ```

- 可达性分析算法

  通过 GC Roots 作为起始点进行搜索，能够到达到的对象都是存活的，不可达的对象可被回收。

  ![image](https://www.pdai.tech/_images/pics/0635cbe8.png)

  Java 虚拟机使用该算法来判断对象是否可被回收，在 Java 中 GC Roots 一般包含以下内容:

  - 虚拟机栈中引用的对象
  - 本地方法栈中引用的对象
  - 方法区中类静态属性引用的对象
  - 方法区中的常量引用的对象

- 方法区的回收

  因为方法区主要存放永久代对象，而永久代对象的回收率比新生代低很多，因此在方法区上进行回收性价比不高。

  主要是对常量池的回收和对类的卸载。

  在大量使用反射、动态代理、CGLib 等 ByteCode 框架、动态生成 JSP 以及 OSGi 这类频繁自定义 ClassLoader 的场景都需要虚拟机具备类卸载功能，以保证不会出现内存溢出。

  类的卸载条件很多，需要满足以下三个条件，并且满足了也不一定会被卸载:

  - 该类所有的实例都已经被回收，也就是堆中不存在该类的任何实例。
  - 加载该类的 ClassLoader 已经被回收。
  - 该类对应的 Class 对象没有在任何地方被引用，也就无法在任何地方通过反射访问该类方法。

  可以通过 -Xnoclassgc 参数来控制是否对类进行卸载。

- finalize()

  finalize() 类似 C++ 的析构函数，用来做关闭外部资源等工作。但是 try-finally 等方式可以做的更好，并且该方法运行代价高昂，不确定性大，无法保证各个对象的调用顺序，因此最好不要使用。

#### 引用类型

无论是通过引用计算算法判断对象的引用数量，还是通过可达性分析算法判断对象是否可达，判定对象是否可被回收都与引用有关。

Java 具有四种强度不同的引用类型。

- 强引用

  被强引用关联的对象不会被回收。

  使用 new 一个新对象的方式来创建强引用。

  ````java
  Object obj = new Object();
  ````

- 软引用

  被软引用关联的对象只有在内存不够的情况下才会被回收。

  使用 SoftReference 类来创建软引用。

  ````java
  Object obj = new Object();
  SoftReference<Object> sf = new SoftReference<Object>(obj);
  obj = null;  // 使对象只被软引用关联
  ````

- 弱引用

  被弱引用关联的对象一定会被回收，也就是说它只能存活到下一次垃圾回收发生之前。

  使用 WeakReference 类来实现弱引用。

  ````java
  Object obj = new Object();
  WeakReference<Object> wf = new WeakReference<Object>(obj);
  obj = null;
  ````

- 虚引用

  又称为幽灵引用或者幻影引用。一个对象是否有虚引用的存在，完全不会对其生存时间构成影响，也无法通过虚引用取得一个对象。

  为一个对象设置虚引用关联的唯一目的就是能在这个对象被回收时收到一个系统通知。

  使用 PhantomReference 来实现虚引用。

  ````java
  Object obj = new Object();
  PhantomReference<Object> pf = new PhantomReference<Object>(obj);
  obj = null;
  ````

#### 垃圾回收算法

- 标记 - 清除

  ![image](https://www.pdai.tech/_images/pics/a4248c4b-6c1d-4fb8-a557-86da92d3a294.jpg)

  将存活的对象进行标记，然后清理掉未被标记的对象。

  不足:

  - 标记和清除过程效率都不高；
  - 会产生大量不连续的内存碎片，导致无法给大对象分配内存。

- 标记 - 整理

  ![image](https://www.pdai.tech/_images/pics/902b83ab-8054-4bd2-898f-9a4a0fe52830.jpg)

  让所有存活的对象都向一端移动，然后直接清理掉端边界以外的内存。

- 复制

  ![image](https://www.pdai.tech/_images/pics/e6b733ad-606d-4028-b3e8-83c3a73a3797.jpg)

  将内存划分为大小相等的两块，每次只使用其中一块，当这一块内存用完了就将还存活的对象复制到另一块上面，然后再把使用过的内存空间进行一次清理。

  主要不足是只使用了内存的一半。

  现在的商业虚拟机都采用这种收集算法来回收新生代，但是并不是将新生代划分为大小相等的两块，而是分为一块较大的 Eden 空间和两块较小的 Survivor 空间，每次使用 Eden 空间和其中一块 Survivor。在回收时，将 Eden 和 Survivor 中还存活着的对象一次性复制到另一块 Survivor 空间上，最后清理 Eden 和使用过的那一块 Survivor。

  HotSpot 虚拟机的 Eden 和 Survivor 的大小比例默认为 8:1，保证了内存的利用率达到 90%。如果每次回收有多于 10% 的对象存活，那么一块 Survivor 空间就不够用了，此时需要依赖于老年代进行分配担保，也就是借用老年代的空间存储放不下的对象。

- 分代收集

  现在的商业虚拟机采用分代收集算法，它根据对象存活周期将内存划分为几块，不同块采用适当的收集算法。

  一般将堆分为新生代和老年代。

  - 新生代使用: 复制算法
  - 老年代使用: 标记 - 清除 或者 标记 - 整理 算法

#### 垃圾收集器

![image](https://www.pdai.tech/_images/pics/c625baa0-dde6-449e-93df-c3a67f2f430f.jpg)

以上是 HotSpot 虚拟机中的 7 个垃圾收集器，连线表示垃圾收集器可以配合使用。

- 单线程与多线程: 单线程指的是垃圾收集器只使用一个线程进行收集，而多线程使用多个线程；
- 串行与并行: 串行指的是垃圾收集器与用户程序交替执行，这意味着在执行垃圾收集的时候需要停顿用户程序；并行指的是垃圾收集器和用户程序同时执行。除了 CMS 和 G1 之外，其它垃圾收集器都是以串行的方式执行。

- Serial 收集器

  ![image](https://www.pdai.tech/_images/pics/22fda4ae-4dd5-489d-ab10-9ebfdad22ae0.jpg)

  Serial 翻译为串行，也就是说它以串行的方式执行。

  它是单线程的收集器，只会使用一个线程进行垃圾收集工作。

  它的优点是简单高效，对于单个 CPU 环境来说，由于没有线程交互的开销，因此拥有最高的单线程收集效率。

- ParNew 收集器

  ![image](https://www.pdai.tech/_images/pics/81538cd5-1bcf-4e31-86e5-e198df1e013b.jpg)

  它是 Serial 收集器的多线程版本。

  是 Server 模式下的虚拟机首选新生代收集器，除了性能原因外，主要是因为除了 Serial 收集器，只有它能与 CMS 收集器配合工作。

  默认开启的线程数量与 CPU 数量相同，可以使用 -XX:ParallelGCThreads 参数来设置线程数。

- Parallel Scavenge 收集器

  与 ParNew 一样是多线程收集器。

  其它收集器关注点是尽可能缩短垃圾收集时用户线程的停顿时间，而它的目标是达到一个可控制的吞吐量，它被称为“吞吐量优先”收集器。这里的吞吐量指 CPU 用于运行用户代码的时间占总时间的比值。

- Serial Old 收集器

  ![image](https://www.pdai.tech/_images/pics/08f32fd3-f736-4a67-81ca-295b2a7972f2.jpg)

  是 Serial 收集器的老年代版本，也是给 Client 模式下的虚拟机使用。

- Parallel Old 收集器

  ![image](https://www.pdai.tech/_images/pics/278fe431-af88-4a95-a895-9c3b80117de3.jpg)

  是 Parallel Scavenge 收集器的老年代版本。

  在注重吞吐量以及 CPU 资源敏感的场合，都可以优先考虑 Parallel Scavenge 加 Parallel Old 收集器。

- CMS 收集器

  ![image](https://www.pdai.tech/_images/pics/62e77997-6957-4b68-8d12-bfd609bb2c68.jpg)

  CMS(Concurrent Mark Sweep)，Mark Sweep 指的是标记 - 清除算法。

  分为以下四个流程:

  - 初始标记: 仅仅只是标记一下 GC Roots 能直接关联到的对象，速度很快，需要停顿。
  - 并发标记: 进行 GC Roots Tracing 的过程，它在整个回收过程中耗时最长，不需要停顿。
  - 重新标记: 为了修正并发标记期间因用户程序继续运作而导致标记产生变动的那一部分对象的标记记录，需要停顿。
  - 并发清除: 不需要停顿。

  在整个过程中耗时最长的并发标记和并发清除过程中，收集器线程都可以与用户线程一起工作，不需要进行停顿。

  具有以下缺点:

  - 吞吐量低: 低停顿时间是以牺牲吞吐量为代价的，导致 CPU 利用率不够高。
  - 无法处理浮动垃圾，可能出现 Concurrent Mode Failure。浮动垃圾是指并发清除阶段由于用户线程继续运行而产生的垃圾，这部分垃圾只能到下一次 GC 时才能进行回收。由于浮动垃圾的存在，因此需要预留出一部分内存，意味着 CMS 收集不能像其它收集器那样等待老年代快满的时候再回收。如果预留的内存不够存放浮动垃圾，就会出现 Concurrent Mode Failure，这时虚拟机将临时启用 Serial Old 来替代 CMS。
  - 标记 - 清除算法导致的空间碎片，往往出现老年代空间剩余，但无法找到足够大连续空间来分配当前对象，不得不提前触发一次 Full GC。

- G1 收集器

  G1(Garbage-First)，它是一款面向服务端应用的垃圾收集器，在多 CPU 和大内存的场景下有很好的性能。HotSpot 开发团队赋予它的使命是未来可以替换掉 CMS 收集器。

  堆被分为新生代和老年代，其它收集器进行收集的范围都是整个新生代或者老年代，而 G1 可以直接对新生代和老年代一起回收。

  ![image](https://www.pdai.tech/_images/pics/4cf711a8-7ab2-4152-b85c-d5c226733807.png)

  G1 把堆划分成多个大小相等的独立区域(Region)，新生代和老年代不再物理隔离。

  ![image](https://www.pdai.tech/_images/pics/9bbddeeb-e939-41f0-8e8e-2b1a0aa7e0a7.png)

  通过引入 Region 的概念，从而将原来的一整块内存空间划分成多个的小空间，使得每个小空间可以单独进行垃圾回收。这种划分方法带来了很大的灵活性，使得可预测的停顿时间模型成为可能。通过记录每个 Region 垃圾回收时间以及回收所获得的空间(这两个值是通过过去回收的经验获得)，并维护一个优先列表，每次根据允许的收集时间，优先回收价值最大的 Region。

  每个 Region 都有一个 Remembered Set，用来记录该 Region 对象的引用对象所在的 Region。通过使用 Remembered Set，在做可达性分析的时候就可以避免全堆扫描。

  ![image](https://www.pdai.tech/_images/pics/f99ee771-c56f-47fb-9148-c0036695b5fe.jpg)

  如果不计算维护 Remembered Set 的操作，G1 收集器的运作大致可划分为以下几个步骤:

  - 初始标记
  - 并发标记
  - 最终标记: 为了修正在并发标记期间因用户程序继续运作而导致标记产生变动的那一部分标记记录，虚拟机将这段时间对象变化记录在线程的 Remembered Set Logs 里面，最终标记阶段需要把 Remembered Set Logs 的数据合并到 Remembered Set 中。这阶段需要停顿线程，但是可并行执行。
  - 筛选回收: 首先对各个 Region 中的回收价值和成本进行排序，根据用户所期望的 GC 停顿时间来制定回收计划。此阶段其实也可以做到与用户程序一起并发执行，但是因为只回收一部分 Region，时间是用户可控制的，而且停顿用户线程将大幅度提高收集效率。

  具备如下特点:

  - 空间整合: 整体来看是基于“标记 - 整理”算法实现的收集器，从局部(两个 Region 之间)上来看是基于“复制”算法实现的，这意味着运行期间不会产生内存空间碎片。
  - 可预测的停顿: 能让使用者明确指定在一个长度为 M 毫秒的时间片段内，消耗在 GC 上的时间不得超过 N 毫秒。

#### 内存分配与回收策略

- Minor GC 和 Full GC

  > Major GC通常是跟full GC是等价的，收集整个GC堆。但因为HotSpot VM发展了这么多年，外界对各种名词的解读已经完全混乱了，当有人说“major GC”的时候一定要问清楚他想要指的是上面的full GC还是old GC。

  - Minor GC: 发生在新生代上，因为新生代对象存活时间很短，因此 Minor GC 会频繁执行，执行的速度一般也会比较快。

  - Full GC: 发生在老年代上，老年代对象其存活时间长，因此 Full GC 很少执行，执行速度会比 Minor GC 慢很多。

- 内存分配策略

  - 对象优先在 Eden 分配

    大多数情况下，对象在新生代 Eden 区分配，当 Eden 区空间不够时，发起 Minor GC。

  - 大对象直接进入老年代

    大对象是指需要连续内存空间的对象，最典型的大对象是那种很长的字符串以及数组。

    经常出现大对象会提前触发垃圾收集以获取足够的连续空间分配给大对象。

    -XX:PretenureSizeThreshold，大于此值的对象直接在老年代分配，避免在 Eden 区和 Survivor 区之间的大量内存复制。

  - 长期存活的对象进入老年代

    为对象定义年龄计数器，对象在 Eden 出生并经过 Minor GC 依然存活，将移动到 Survivor 中，年龄就增加 1 岁，增加到一定年龄则移动到老年代中。

    -XX:MaxTenuringThreshold 用来定义年龄的阈值。

  - 动态对象年龄判定

    > 虚拟机并不是永远地要求对象的年龄必须达到 MaxTenuringThreshold 才能晋升老年代，如果在 Survivor 中相同年龄所有对象大小的总和大于 Survivor 空间的一半，则年龄大于或等于该年龄的对象可以直接进入老年代，无需等到 MaxTenuringThreshold 中要求的年龄。

    总体表征就是，年龄从小到大进行累加，当加入某个年龄段后，累加和超过survivor区域*TargetSurvivorRatio的时候，就从这个年龄段网上的年龄的对象进行晋升。

    案例

    MaxTenuringThreshold为15

    年龄1的对象占用了33%

    年龄2的对象占用33%

    年龄3的对象占用34%。

    年龄1的占用了33%，年龄2的占用了33%，累加和超过默认的TargetSurvivorRatio（50%），年龄2和年龄3的对象都要晋升。

  - 空间分配担保

    在发生 Minor GC 之前，虚拟机先检查老年代最大可用的连续空间是否大于新生代所有对象总空间，如果条件成立的话，那么 Minor GC 可以确认是安全的。

    如果不成立的话虚拟机会查看 HandlePromotionFailure 设置值是否允许担保失败，如果允许那么就会继续检查老年代最大可用的连续空间是否大于历次晋升到老年代对象的平均大小，如果大于，将尝试着进行一次 Minor GC；如果小于，或者 HandlePromotionFailure 设置不允许冒险，那么就要进行一次 Full GC。

- Full GC 的触发条件

  对于 Minor GC，其触发条件非常简单，当 Eden 空间满时，就将触发一次 Minor GC。而 Full GC 则相对复杂，有以下条件:

  - 调用 System.gc()

    只是建议虚拟机执行 Full GC，但是虚拟机不一定真正去执行。不建议使用这种方式，而是让虚拟机管理内存。

  - 老年代空间不足

    老年代空间不足的常见场景为前文所讲的大对象直接进入老年代、长期存活的对象进入老年代等。

    为了避免以上原因引起的 Full GC，应当尽量不要创建过大的对象以及数组。除此之外，可以通过 -Xmn 虚拟机参数调大新生代的大小，让对象尽量在新生代被回收掉，不进入老年代。还可以通过 -XX:MaxTenuringThreshold 调大对象进入老年代的年龄，让对象在新生代多存活一段时间。

  - 空间分配担保失败

    使用复制算法的 Minor GC 需要老年代的内存空间作担保，如果担保失败会执行一次 Full GC。具体内容请参考上面的第五小节。

  - JDK 1.7 及以前的永久代空间不足

    在 JDK 1.7 及以前，HotSpot 虚拟机中的方法区是用永久代实现的，永久代中存放的为一些 Class 的信息、常量、静态变量等数据。

    当系统中要加载的类、反射的类和调用的方法较多时，永久代可能会被占满，在未配置为采用 CMS GC 的情况下也会执行 Full GC。如果经过 Full GC 仍然回收不了，那么虚拟机会抛出 java.lang.OutOfMemoryError。

    为避免以上原因引起的 Full GC，可采用的方法为增大永久代空间或转为使用 CMS GC。

  - Concurrent Mode Failure

    执行 CMS GC 的过程中同时有对象要放入老年代，而此时老年代空间不足(可能是 GC 过程中浮动垃圾过多导致暂时性的空间不足)，便会报 Concurrent Mode Failure 错误，并触发 Full GC。

### JVM基础 - Java 垃圾回收器G1详解

#### 概述

G1垃圾回收器是在Java7 update 4之后引入的一个新的垃圾回收器。G1是一个分代的，增量的，并行与并发的标记-复制垃圾回收器。它的设计目标是为了适应现在不断扩大的内存和不断增加的处理器数量，进一步降低暂停时间（pause time），同时兼顾良好的吞吐量。G1回收器和CMS比起来，有以下不同：

- G1垃圾回收器是**compacting**的，因此其回收得到的空间是连续的。这避免了CMS回收器因为不连续空间所造成的问题。如需要更大的堆空间，更多的floating garbage。连续空间意味着G1垃圾回收器可以不必采用空闲链表的内存分配方式，而可以直接采用bump-the-pointer的方式；
- G1回收器的内存与CMS回收器要求的内存模型有极大的不同。G1将内存划分一个个固定大小的region，每个region可以是年轻代、老年代的一个。**内存的回收是以region作为基本单位的**；
- G1还有一个及其重要的特性：**软实时**（soft real-time）。所谓的实时垃圾回收，是指在要求的时间内完成垃圾回收。“软实时”则是指，用户可以指定垃圾回收时间的限时，G1会努力在这个时限内完成垃圾回收，但是G1并不担保每次都能在这个时限内完成垃圾回收。通过设定一个合理的目标，可以让达到90%以上的垃圾回收时间都在这个时限内。

#### G1的内存模型

- 分区概念

  G1分区示意图

  ![img](https://tva1.sinaimg.cn/large/008i3skNly1gyhv49vt50j30gd08474o.jpg)

  <img src="https://pic4.zhimg.com/80/v2-c33552a6d2320566351b75b801f48673_1440w.jpg" alt="img" style="zoom: 67%;" />

  - 分区Region

    G1采用了分区(Region)的思路，将整个堆空间分成若干个大小相等的内存区域，每次分配对象空间将逐段地使用内存。因此，在堆的使用上，G1并不要求对象的存储一定是物理上连续的，只要逻辑上连续即可；每个分区也不会确定地为某个代服务，可以按需在年轻代和老年代之间切换。启动时可以通过参数-XX:G1HeapRegionSize=n可指定分区大小(1MB~32MB，且必须是2的幂)，默认将整堆划分为2048个分区。

  - 卡片Card

    在每个分区内部又被分成了若干个大小为512 Byte卡片(Card)，标识堆内存最小可用粒度所有分区的卡片将会记录在全局卡片表(Global Card Table)中，分配的对象会占用物理上连续的若干个卡片，当查找对分区内对象的引用时便可通过记录卡片来查找该引用对象(见RSet)。每次对内存的回收，都是对指定分区的卡片进行处理。

  - 堆Heap

    G1同样可以通过-Xms/-Xmx来指定堆空间大小。当发生年轻代收集或混合收集时，通过计算GC与应用的耗费时间比，自动调整堆空间大小。如果GC频率太高，则通过增加堆尺寸，来减少GC频率，相应地GC占用的时间也随之降低；目标参数-XX:GCTimeRatio即为GC与应用的耗费时间比，G1默认为9，而CMS默认为99，因为CMS的设计原则是耗费在GC上的时间尽可能的少。另外，当空间不足，如对象空间分配或转移失败时，G1会首先尝试增加堆空间，如果扩容失败，则发起担保的Full GC。Full GC后，堆尺寸计算结果也会调整堆空间。

- 分代模型

  ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/java/java-jvm-gc-g1-2.jpeg)

  - 分代垃圾收集

    分代垃圾收集可以将关注点集中在最近被分配的对象上，而无需整堆扫描，避免长命对象的拷贝，同时独立收集有助于降低响应时间。虽然分区使得内存分配不再要求紧凑的内存空间，但G1依然使用了分代的思想。与其他垃圾收集器类似，G1将内存在逻辑上划分为年轻代和老年代，其中年轻代又划分为Eden空间和Survivor空间。但年轻代空间并不是固定不变的，当现有年轻代分区占满时，JVM会分配新的空闲分区加入到年轻代空间。

    整个年轻代内存会在初始空间`-XX:G1NewSizePercent`(默认整堆5%)与最大空间(默认60%)之间动态变化，且由参数目标暂停时间`-XX:MaxGCPauseMillis`(默认200ms)、需要扩缩容的大小以`-XX:G1MaxNewSizePercent`及分区的已记忆集合(RSet)计算得到。当然，G1依然可以设置固定的年轻代大小(参数-XX:NewRatio、-Xmn)，但同时暂停目标将失去意义。

  - 本地分配缓冲 Local allocation buffer (Lab)

    值得注意的是，由于分区的思想，每个线程均可以"认领"某个分区用于线程本地的内存分配，而不需要顾及分区是否连续。因此，每个应用线程和GC线程都会独立的使用分区，进而减少同步时间，提升GC效率，这个分区称为本地分配缓冲区(Lab)。

    其中，应用线程可以独占一个本地缓冲区(TLAB)来创建的对象，而大部分都会落入Eden区域(巨型对象或分配失败除外)，因此TLAB的分区属于Eden空间；而每次垃圾收集时，每个GC线程同样可以独占一个本地缓冲区(GCLAB)用来转移对象，每次回收会将对象复制到Suvivor空间或老年代空间；对于从Eden/Survivor空间晋升(Promotion)到Survivor/老年代空间的对象，同样有GC独占的本地缓冲区进行操作，该部分称为晋升本地缓冲区(PLAB)。

- 分区模型

  G1对内存的使用以分区(Region)为单位，而对对象的分配则以卡片(Card)为单位。

  ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/java/java-jvm-gc-g1-3.jpeg)

  - 巨形对象Humongous Region

    一个大小达到甚至超过分区大小一半的对象称为巨型对象(Humongous Object)。当线程为巨型分配空间时，不能简单在TLAB进行分配，因为巨型对象的移动成本很高，而且有可能一个分区不能容纳巨型对象。因此，巨型对象会直接在老年代分配，所占用的连续空间称为巨型分区(Humongous Region)。G1内部做了一个优化，一旦发现没有引用指向巨型对象，则可直接在年轻代收集周期中被回收。

    巨型对象会独占一个、或多个连续分区，其中第一个分区被标记为开始巨型(StartsHumongous)，相邻连续分区被标记为连续巨型(ContinuesHumongous)。由于无法享受Lab带来的优化，并且确定一片连续的内存空间需要扫描整堆，因此确定巨型对象开始位置的成本非常高，如果可以，应用程序应避免生成巨型对象。

  - 已记忆集合Remember Set (RSet)

    在串行和并行收集器中，GC通过整堆扫描，来确定对象是否处于可达路径中。然而G1为了避免STW式的整堆扫描，在每个分区记录了一个已记忆集合(RSet)，内部类似一个反向指针，记录引用分区内对象的卡片索引。当要回收该分区时，通过扫描分区的RSet，来确定引用本分区内的对象是否存活，进而确定本分区内的对象存活情况。

    事实上，并非所有的引用都需要记录在RSet中，如果一个分区确定需要扫描，那么无需RSet也可以无遗漏的得到引用关系。那么引用源自本分区的对象，当然不用落入RSet中；同时，G1 GC每次都会对年轻代进行整体收集，因此引用源自年轻代的对象，也不需要在RSet中记录。最后只有老年代的分区可能会有RSet记录，这些分区称为拥有RSet分区(an RSet’s owning region)。

  - Per Region Table (PRT)

    RSet在内部使用Per Region Table(PRT)记录分区的引用情况。由于RSet的记录要占用分区的空间，如果一个分区非常"受欢迎"，那么RSet占用的空间会上升，从而降低分区的可用空间。G1应对这个问题采用了改变RSet的密度的方式，在PRT中将会以三种模式记录引用：

    - 稀少：直接记录引用对象的卡片索引
    - 细粒度：记录引用对象的分区索引
    - 粗粒度：只记录引用情况，每个分区对应一个比特位

    由上可知，粗粒度的PRT只是记录了引用数量，需要通过整堆扫描才能找出所有引用，因此扫描速度也是最慢的。

- 收集集合 (CSet)

  CSet收集示意图

  ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/java/java-jvm-gc-g1-4.jpeg)

  收集集合(CSet)代表每次GC暂停时回收的一系列目标分区。在任意一次收集暂停中，CSet所有分区都会被释放，内部存活的对象都会被转移到分配的空闲分区中。因此无论是年轻代收集，还是混合收集，工作的机制都是一致的。年轻代收集CSet只容纳年轻代分区，而混合收集会通过启发式算法，在老年代候选回收分区中，筛选出回收收益最高的分区添加到CSet中。

  候选老年代分区的CSet准入条件，可以通过活跃度阈值-XX:G1MixedGCLiveThresholdPercent(默认85%)进行设置，从而拦截那些回收开销巨大的对象；同时，每次混合收集可以包含候选老年代分区，可根据CSet对堆的总大小占比-XX:G1OldCSetRegionThresholdPercent(默认10%)设置数量上限。

  由上述可知，G1的收集都是根据CSet进行操作的，年轻代收集与混合收集没有明显的不同，最大的区别在于两种收集的触发条件。

  - 年轻代收集集合 CSet of Young Collection

    应用线程不断活动后，年轻代空间会被逐渐填满。当JVM分配对象到Eden区域失败(Eden区已满)时，便会触发一次STW式的年轻代收集。在年轻代收集中，Eden分区存活的对象将被拷贝到Survivor分区；原有Survivor分区存活的对象，将根据任期阈值(tenuring threshold)分别晋升到PLAB中，新的survivor分区和老年代分区。而原有的年轻代分区将被整体回收掉。

    同时，年轻代收集还负责维护对象的年龄(存活次数)，辅助判断老化(tenuring)对象晋升的时候是到Survivor分区还是到老年代分区。年轻代收集首先先将晋升对象尺寸总和、对象年龄信息维护到年龄表中，再根据年龄表、Survivor尺寸、Survivor填充容量-XX:TargetSurvivorRatio(默认50%)、最大任期阈值-XX:MaxTenuringThreshold(默认15)，计算出一个恰当的任期阈值，凡是超过任期阈值的对象都会被晋升到老年代。

  - 混合收集集合 CSet of Mixed Collection

    年轻代收集不断活动后，老年代的空间也会被逐渐填充。当老年代占用空间超过整堆比IHOP阈值-XX:InitiatingHeapOccupancyPercent(默认45%)时，G1就会启动一次混合垃圾收集周期。为了满足暂停目标，G1可能不能一口气将所有的候选分区收集掉，因此G1可能会产生连续多次的混合收集与应用线程交替执行，每次STW的混合收集与年轻代收集过程相类似。

    为了确定包含到年轻代收集集合CSet的老年代分区，JVM通过参数混合周期的最大总次数-XX:G1MixedGCCountTarget(默认8)、堆废物百分比-XX:G1HeapWastePercent(默认5%)。通过候选老年代分区总数与混合周期最大总次数，确定每次包含到CSet的最小分区数量；根据堆废物百分比，当收集达到参数时，不再启动新的混合收集。而每次添加到CSet的分区，则通过计算得到的GC效率进行安排。

  - 并发标记算法（三色标记法）

    CMS和G1在并发标记时使用的是同一个算法：三色标记法，使用白灰黑三种颜色标记对象。白色是未标记；灰色自身被标记，引用的对象未标记；黑色自身与引用对象都已标记。

    <img src="https://tva1.sinaimg.cn/large/008i3skNly1gyhx00fnknj30se0fejrs.jpg" alt="image-20220118164016616" style="zoom: 50%;" />

    GC 开始前所有对象都是白色，GC 一开始所有根能够直达的对象被压到栈中，待搜索，此时颜色是灰色。然后灰色对象依次从栈中取出搜索子对象，子对象也会被涂为灰色，入栈。当其所有的子对象都涂为灰色之后该对象被涂为黑色。当 GC 结束之后灰色对象将全部没了，剩下黑色的为存活对象，白色的为垃圾。

    ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/java/java-jvm-gc-g1-5-1.gif)

  - 漏标问题

    在remark过程中，黑色指向了白色，如果不对黑色重新扫描，则会漏标。会把白色D对象当作没有新引用指向从而回收掉。

    ![image-20220118220555535](https://tva1.sinaimg.cn/large/008i3skNly1gyi6eu6m09j30u00xpgn0.jpg)

    并发标记过程中，Mutator删除了所有从灰色到白色的引用，会产生漏标。此时白色对象应该被回收

    产生漏标问题的条件有两个：

    - 黑色对象指向了白色对象
    - 灰色对象指向白色对象的引用消失

    所以要解决漏标问题，打破两个条件之一即可：

    - **跟踪黑指向白的增加** incremental update：增量更新，关注引用的增加，把黑色重新标记为灰色，下次重新扫描属性。CMS采用该方法。
    - **记录灰指向白的消失** SATB snapshot at the beginning：关注引用的删除，当灰–>白消失时，要把这个 引用 推到GC的堆栈，保证白还能被GC扫描到。G1采用该方法。

    **为什么G1采用SATB而不用incremental update**？

    因为采用incremental update把黑色重新标记为灰色后，之前扫描过的还要再扫描一遍，效率太低。G1有RSet与SATB相配合。Card Table里记录了RSet，RSet里记录了其他对象指向自己的引用，这样就不需要再扫描其他区域，只要扫描RSet就可以了。

    也就是说 灰色–>白色 引用消失时，如果没有 黑色–>白色，引用会被push到堆栈，下次扫描时拿到这个引用，由于有RSet的存在，不需要扫描整个堆去查找指向白色的引用，效率比较高。SATB配合RSet浑然天成。

#### G1的回收过程

`G1`的内存划分形式，决定了`G1`同时需要管理新生代和老年代。根据回收区域的不同，`G1`分为两种回收模式：

- 只回收部分年轻代的内存：`Yong GC`
- 回收所有年轻代内存和部分老年代内存： `Mixed GC`

**Mixed GC**

按道理来说，应该先说`Yong Gc`，可是`Yong GC`貌似不是`G1`的回收重点，同时也没有什么参数可以控制`Yong GC`，所以这里暂时跳过。不过需要知道一点就是`Yong GC`的回收过程和其他垃圾回收器差不多，也是先标记，再复制。不过整个过程都会`STW`,同时由于`Yong GC`的标记过程和后面`Mixed GC`中的并发标记(`Concurrent Marking`）的第一个阶段,初始标记（`initial marking`）所做的工作相同，因此，`Concurrent Marking`的初始标记阶段总是搭载着`Yong GC`进行。

`Mixed GC`分为两个阶段，第一个阶段是并发标记，第二个阶段是筛选回收。并发标记过程如下：

- 初始标记（initial marking）

  会`Stop The World` ,从标记所有`GC Root`出发可以直接到达的对象。这个过程虽然会暂停，但是它是借用的`Yong GC`的暂停阶段，因此没有额外的，单独的暂停阶段。

- 并发标记（concurrent marking）

  并发阶段。从上一个阶段扫描的对象出发逐个遍历查找，每找到一个对象就将其标记为存活状态。注意：此过程还会扫描`SATB`（并发快照）所记录的引用。

  > 回忆并发快照：它是一个用来解决并发过程中由于用户修改引用关系而导致对象可能被误标的方案。`CMS`使用的是增量更新，这里`G1`使用的是并发快照，在并发标记开始的时候记录所有引用关系。

- 最终标记（final marking,remarking）

  会`STW`，虽然前面的并发标记过程中扫描了`SATB`，但是毕竟上一个阶段依然是并发过程，因此需要在并发标记完成后，再次暂停所有用户线程，再次标记`SATB`。同时这个过程也会处理弱引用。

- 清理（cleanup）

  暂停阶段。清理和重置标记状态。用来统计每个`region`中的中被标记为存活的对象的数量，这个阶段如果发现完全没有活对象的region就会将其整体回收到可分配region列表中。

#### G1的活动周期

- G1垃圾收集活动汇总

  G1垃圾收集活动周期图

  ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/java/java-jvm-gc-g1-7.jpeg)

- RSet的维护

  由于不能整堆扫描，又需要计算分区确切的活跃度，因此，G1需要一个增量式的完全标记并发算法，通过维护RSet，得到准确的分区引用信息。在G1中，RSet的维护主要来源两个方面：写栅栏(Write Barrier)和并发优化线程(Concurrence Refinement Threads)

  - 栅栏Barrier

    栅栏代码示意

    ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/java/java-jvm-gc-g1-8.jpeg)

    我们首先介绍一下栅栏(Barrier)的概念。栅栏是指在原生代码片段中，当某些语句被执行时，栅栏代码也会被执行。而G1主要在赋值语句中，使用写前栅栏(Pre-Write Barrrier)和写后栅栏(Post-Write Barrrier)。事实上，写栅栏的指令序列开销非常昂贵，应用吞吐量也会根据栅栏复杂度而降低。

    **写前栅栏 Pre-Write Barrrier**

    即将执行一段赋值语句时，等式左侧对象将修改引用到另一个对象，那么等式左侧对象原先引用的对象所在分区将因此丧失一个引用，那么JVM就需要在赋值语句生效之前，记录丧失引用的对象。JVM并不会立即维护RSet，而是通过批量处理，在将来RSet更新(见SATB)。

    **写后栅栏 Post-Write Barrrier**

    当执行一段赋值语句后，等式右侧对象获取了左侧对象的引用，那么等式右侧对象所在分区的RSet也应该得到更新。同样为了降低开销，写后栅栏发生后，RSet也不会立即更新，同样只是记录此次更新日志，在将来批量处理(见Concurrence Refinement Threads)。

  - 起始快照算法Snapshot at the beginning (SATB)

    Taiichi Tuasa贡献的增量式完全并发标记算法起始快照算法(SATB)，主要针对标记-清除垃圾收集器的并发标记阶段，非常适合G1的分区块的堆结构，同时解决了CMS的主要烦恼：重新标记暂停时间长带来的潜在风险。

    SATB会创建一个对象图，相当于堆的逻辑快照，从而确保并发标记阶段所有的垃圾对象都能通过快照被鉴别出来。当赋值语句发生时，应用将会改变了它的对象图，那么JVM需要记录被覆盖的对象。因此写前栅栏会在引用变更前，将值记录在SATB日志或缓冲区中。每个线程都会独占一个SATB缓冲区，初始有256条记录空间。当空间用尽时，线程会分配新的SATB缓冲区继续使用，而原有的缓冲去则加入全局列表中。最终在并发标记阶段，并发标记线程(Concurrent Marking Threads)在标记的同时，还会定期检查和处理全局缓冲区列表的记录，然后根据标记位图分片的标记位，扫描引用字段来更新RSet。此过程又称为并发标记/SATB写前栅栏。

  - 并发优化线程Concurrence Refinement Threads

    G1中使用基于Urs Hölzle的快速写栅栏，将栅栏开销缩减到2个额外的指令。栅栏将会更新一个card table type的结构来跟踪代间引用。

    当赋值语句发生后，写后栅栏会先通过G1的过滤技术判断是否是跨分区的引用更新，并将跨分区更新对象的卡片加入缓冲区序列，即更新日志缓冲区或脏卡片队列。与SATB类似，一旦日志缓冲区用尽，则分配一个新的日志缓冲区，并将原来的缓冲区加入全局列表中。

    并发优化线程(Concurrence Refinement Threads)，只专注扫描日志缓冲区记录的卡片来维护更新RSet，线程最大数目可通过`-XX:G1ConcRefinementThreads`(默认等于`-XX:ParellelGCThreads`)设置。并发优化线程永远是活跃的，一旦发现全局列表有记录存在，就开始并发处理。如果记录增长很快或者来不及处理，那么通过阈值`-X:G1ConcRefinementGreenZone/-XX:G1ConcRefinementYellowZone/-XX:G1ConcRefinementRedZone`，G1会用分层的方式调度，使更多的线程处理全局列表。如果并发优化线程也不能跟上缓冲区数量，则Mutator线程(Java应用线程)会挂起应用并被加进来帮助处理，直到全部处理完。因此，必须避免此类场景出现。

- 并发标记周期 Concurrent Marking Cycle

  并发标记周期是G1中非常重要的阶段，这个阶段将会为混合收集周期识别垃圾最多的老年代分区。整个周期完成根标记、识别所有(可能)存活对象，并计算每个分区的活跃度，从而确定GC效率等级。

  当达到IHOP阈值`-XX:InitiatingHeapOccupancyPercent`(老年代占整堆比，默认45%)时，便会触发并发标记周期。整个并发标记周期将由初始标记(Initial Mark)、根分区扫描(Root Region Scanning)、并发标记(Concurrent Marking)、重新标记(Remark)、清除(Cleanup)几个阶段组成。其中，初始标记(随年轻代收集一起活动)、重新标记、清除是STW的，而并发标记如果来不及标记存活对象，则可能在并发标记过程中，G1又触发了几次年轻代收集。

  - 并发标记线程 Concurrent Marking Threads

    并发标记位图过程

    ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/java/java-jvm-gc-g1-9.png)

    要标记存活的对象，每个分区都需要创建位图(Bitmap)信息来存储标记数据，来确定标记周期内被分配的对象。G1采用了两个位图Previous Bitmap、Next Bitmap，来存储标记数据，Previous位图存储上次的标记数据，Next位图在标记周期内不断变化更新，同时Previous位图的标记数据也越来越过时，当标记周期结束后Next位图便替换Previous位图，成为上次标记的位图。同时，每个分区通过顶部开始标记(TAMS)，来记录已标记过的内存范围。同样的，G1使用了两个顶部开始标记Previous TAMS(PTAMS)、Next TAMS(NTAMS)，记录已标记的范围。

    在并发标记阶段，G1会根据参数`-XX:ConcGCThreads`(默认GC线程数的1/4，即`-XX:ParallelGCThreads/4`)，分配并发标记线程(Concurrent Marking Threads)，进行标记活动。每个并发线程一次只扫描一个分区，并通过"手指"指针的方式优化获取分区。并发标记线程是爆发式的，在给定的时间段拼命干活，然后休息一段时间，再拼命干活。

    每个并发标记周期，在初始标记STW的最后，G1会分配一个空的Next位图和一个指向分区顶部(Top)的NTAMS标记。Previous位图记录的上次标记数据，上次的标记位置，即PTAMS，在PTAMS与分区底部(Bottom)的范围内，所有的存活对象都已被标记。那么，在PTAMS与Top之间的对象都将是隐式存活(Implicitly Live)对象。在并发标记阶段，Next位图吸收了Previous位图的标记数据，同时每个分区都会有新的对象分配，则Top与NTAMS分离，前往更高的地址空间。在并发标记的一次标记中，并发标记线程将找出NTAMS与PTAMS之间的所有存活对象，将标记数据存储在Next位图中。同时，在NTAMS与Top之间的对象即成为已标记对象。如此不断地更新Next位图信息，并在清除阶段与Previous位图交换角色。

  - 初始标记 Initial Mark

    初始标记(Initial Mark)负责标记所有能被直接可达的根对象(原生栈对象、全局对象、JNI对象)，根是对象图的起点，因此初始标记需要将Mutator线程(Java应用线程)暂停掉，也就是需要一个STW的时间段。事实上，当达到IHOP阈值时，G1并不会立即发起并发标记周期，而是等待下一次年轻代收集，利用年轻代收集的STW时间段，完成初始标记，这种方式称为借道(Piggybacking)。在初始标记暂停中，分区的NTAMS都被设置到分区顶部Top，初始标记是并发执行，直到所有的分区处理完。

  - 根分区扫描 Root Region Scanning

    在初始标记暂停结束后，年轻代收集也完成的对象复制到Survivor的工作，应用线程开始活跃起来。此时为了保证标记算法的正确性，所有新复制到Survivor分区的对象，都需要被扫描并标记成根，这个过程称为根分区扫描(Root Region Scanning)，同时扫描的Suvivor分区也被称为根分区(Root Region)。根分区扫描必须在下一次年轻代垃圾收集启动前完成(并发标记的过程中，可能会被若干次年轻代垃圾收集打断)，因为每次GC会产生新的存活对象集合。

  - 并发标记 Concurrent Marking

    和应用线程并发执行，并发标记线程在并发标记阶段启动，由参数`-XX:ConcGCThreads`(默认GC线程数的1/4，即`-XX:ParallelGCThreads/4`)控制启动数量，每个线程每次只扫描一个分区，从而标记出存活对象图。在这一阶段会处理Previous/Next标记位图，扫描标记对象的引用字段。同时，并发标记线程还会定期检查和处理STAB全局缓冲区列表的记录，更新对象引用信息。参数`-XX:+ClassUnloadingWithConcurrentMark`会开启一个优化，如果一个类不可达(不是对象不可达)，则在重新标记阶段，这个类就会被直接卸载。所有的标记任务必须在堆满前就完成扫描，如果并发标记耗时很长，那么有可能在并发标记过程中，又经历了几次年轻代收集。如果堆满前没有完成标记任务，则会触发担保机制，经历一次长时间的串行Full GC。

  - 存活数据计算 Live Data Accounting

    存活数据计算(Live Data Accounting)是标记操作的附加产物，只要一个对象被标记，同时会被计算字节数，并计入分区空间。只有NTAMS以下的对象会被标记和计算，在标记周期的最后，Next位图将被清空，等待下次标记周期。

  - 重新标记 Remark

    重新标记(Remark)是最后一个标记阶段。在该阶段中，G1需要一个暂停的时间，去处理剩下的SATB日志缓冲区和所有更新，找出所有未被访问的存活对象，同时安全完成存活数据计算。这个阶段也是并行执行的，通过参数-XX:ParallelGCThread可设置GC暂停时可用的GC线程数。同时，引用处理也是重新标记阶段的一部分，所有重度使用引用对象(弱引用、软引用、虚引用、最终引用)的应用都会在引用处理上产生开销。

  - 清除 Cleanup

    紧挨着重新标记阶段的清除(Clean)阶段也是STW的。Previous/Next标记位图、以及PTAMS/NTAMS，都会在清除阶段交换角色。清除阶段主要执行以下操作：

    - **RSet梳理**，启发式算法会根据活跃度和RSet尺寸对分区定义不同等级，同时RSet数理也有助于发现无用的引用。参数`-XX:+PrintAdaptiveSizePolicy`可以开启打印启发式算法决策细节；
    - **整理堆分区**，为混合收集周期识别回收收益高(基于释放空间和暂停目标)的老年代分区集合；
    - **识别所有空闲分区**，即发现无存活对象的分区。该分区可在清除阶段直接回收，无需等待下次收集周期。

- 年轻代收集/混合收集周期

  年轻代收集和混合收集周期，是G1回收空间的主要活动。当应用运行开始时，堆内存可用空间还比较大，只会在年轻代满时，触发年轻代收集；随着老年代内存增长，当到达IHOP阈值`-XX:InitiatingHeapOccupancyPercent`(老年代占整堆比，默认45%)时，G1开始着手准备收集老年代空间。首先经历并发标记周期，识别出高收益的老年代分区，前文已述。但随后G1并不会马上开始一次混合收集，而是让应用线程先运行一段时间，等待触发一次年轻代收集。在这次STW中，G1将保准整理混合收集周期。接着再次让应用线程运行，当接下来的几次年轻代收集时，将会有老年代分区加入到CSet中，即触发混合收集，这些连续多次的混合收集称为混合收集周期(Mixed Collection Cycle)。

  - GC工作线程数

    GC工作线程数 `-XX:ParallelGCThreads`

    JVM可以通过参数`-XX:ParallelGCThreads`进行指定GC工作的线程数量。参数`-XX:ParallelGCThreads`默认值并不是固定的，而是根据当前的CPU资源进行计算。如果用户没有指定，且CPU小于等于8，则默认与CPU核数相等；若CPU大于8，则默认JVM会经过计算得到一个小于CPU核数的线程数；当然也可以人工指定与CPU核数相等。

  - 年轻代收集 Young Collection

    每次收集过程中，既有并行执行的活动，也有串行执行的活动，但都可以是多线程的。在并行执行的任务中，如果某个任务过重，会导致其他线程在等待某项任务的处理，需要对这些地方进行优化。

    **并行活动**

    - `外部根分区扫描 Ext Root Scanning`：此活动对堆外的根(JVM系统目录、VM数据结构、JNI线程句柄、硬件寄存器、全局变量、线程对栈根)进行扫描，发现那些没有加入到暂停收集集合CSet中的对象。如果系统目录(单根)拥有大量加载的类，最终可能其他并行活动结束后，该活动依然没有结束而带来的等待时间。
    - `更新已记忆集合 Update RS`：并发优化线程会对脏卡片的分区进行扫描更新日志缓冲区来更新RSet，但只会处理全局缓冲列表。作为补充，所有被记录但是还没有被优化线程处理的剩余缓冲区，会在该阶段处理，变成已处理缓冲区(Processed Buffers)。为了限制花在更新RSet的时间，可以设置暂停占用百分比-XX:G1RSetUpdatingPauseTimePercent(默认10%，即-XX:MaxGCPauseMills/10)。值得注意的是，如果更新日志缓冲区更新的任务不降低，单纯地减少RSet的更新时间，会导致暂停中被处理的缓冲区减少，将日志缓冲区更新工作推到并发优化线程上，从而增加对Java应用线程资源的争夺。
    - `RSet扫描 Scan RS`：在收集当前CSet之前，考虑到分区外的引用，必须扫描CSet分区的RSet。如果RSet发生粗化，则会增加RSet的扫描时间。开启诊断模式-XX:UnlockDiagnosticVMOptions后，通过参数-XX:+G1SummarizeRSetStats可以确定并发优化线程是否能够及时处理更新日志缓冲区，并提供更多的信息，来帮助为RSet粗化总数提供窗口。参数-XX：G1SummarizeRSetStatsPeriod=n可设置RSet的统计周期，即经历多少此GC后进行一次统计
    - `代码根扫描 Code Root Scanning`：对代码根集合进行扫描，扫描JVM编译后代码Native Method的引用信息(nmethod扫描)，进行RSet扫描。事实上，只有CSet分区中的RSet有强代码根时，才会做nmethod扫描，查找对CSet的引用。
    - `转移和回收 Object Copy`：通过选定的CSet以及CSet分区完整的引用集，将执行暂停时间的主要部分：CSet分区存活对象的转移、CSet分区空间的回收。通过工作窃取机制来负载均衡地选定复制对象的线程，并且复制和扫描对象被转移的存活对象将拷贝到每个GC线程分配缓冲区GCLAB。G1会通过计算，预测分区复制所花费的时间，从而调整年轻代的尺寸。
    - `终止 Termination`：完成上述任务后，如果任务队列已空，则工作线程会发起终止要求。如果还有其他线程继续工作，空闲的线程会通过工作窃取机制尝试帮助其他线程处理。而单独执行根分区扫描的线程，如果任务过重，最终会晚于终止。
    - `GC外部的并行活动 GC Worker Other`：该部分并非GC的活动，而是JVM的活动导致占用了GC暂停时间(例如JNI编译)。

    **串行活动**

    - `代码根更新 Code Root Fixup`：根据转移对象更新代码根。
    - `代码根清理 Code Root Purge`：清理代码根集合表。
    - `清除全局卡片标记 Clear CT`：在任意收集周期会扫描CSet与RSet记录的PRT，扫描时会在全局卡片表中进行标记，防止重复扫描。在收集周期的最后将会清除全局卡片表中的已扫描标志。
    - `选择下次收集集合 Choose CSet`：该部分主要用于并发标记周期后的年轻代收集、以及混合收集中，在这些收集过程中，由于有老年代候选分区的加入，往往需要对下次收集的范围做出界定；但单纯的年轻代收集中，所有收集的分区都会被收集，不存在选择。
    - `引用处理 Ref Proc`：主要针对软引用、弱引用、虚引用、final引用、JNI引用。当Ref Proc占用时间过多时，可选择使用参数`-XX:ParallelRefProcEnabled`激活多线程引用处理。G1希望应用能小心使用软引用，因为软引用会一直占据内存空间直到空间耗尽时被Full GC回收掉；即使未发生Full GC，软引用对内存的占用，也会导致GC次数的增加。
    - `引用排队 Ref Enq`：此项活动可能会导致RSet的更新，此时会通过记录日志，将关联的卡片标记为脏卡片。
    - `卡片重新脏化 Redirty Cards`：重新脏化卡片。
    - `回收空闲巨型分区 Humongous Reclaim`：G1做了一个优化：通过查看所有根对象以及年轻代分区的RSet，如果确定RSet中巨型对象没有任何引用，则说明G1发现了一个不可达的巨型对象，该对象分区会被回收。
    - `释放分区 Free CSet`：回收CSet分区的所有空间，并加入到空闲分区中。
    - `其他活动 Other`：GC中可能还会经历其他耗时很小的活动，如修复JNI句柄等。

- 并发标记周期后的年轻代收集 Young Collection Following Concurrent Marking Cycle

  当G1发起并发标记周期之后，并不会马上开始混合收集。G1会先等待下一次年轻代收集，然后在该收集阶段中，确定下次混合收集的CSet(Choose CSet)。

  - 混合收集周期 Mixed Collection Cycle

    单次的混合收集与年轻代收集并无二致。根据暂停目标，老年代的分区可能不能一次暂停收集中被处理完，G1会发起连续多次的混合收集，称为混合收集周期(Mixed Collection Cycle)。G1会计算每次加入到CSet中的分区数量、混合收集进行次数，并且在上次的年轻代收集、以及接下来的混合收集中，G1会确定下次加入CSet的分区集(Choose CSet)，并且确定是否结束混合收集周期。

  - 转移失败的担保机制 Full GC

    转移失败(Evacuation Failure)是指当G1无法在堆空间中申请新的分区时，G1便会触发担保机制，执行一次STW式的、单线程的Full GC。Full GC会对整堆做标记清除和压缩，最后将只包含纯粹的存活对象。参数-XX:G1ReservePercent(默认10%)可以保留空间，来应对晋升模式下的异常情况，最大占用整堆50%，更大也无意义。

    G1在以下场景中会触发Full GC，同时会在日志中记录to-space-exhausted以及Evacuation Failure：

    - 从年轻代分区拷贝存活对象时，无法找到可用的空闲分区
    - 从老年代分区转移存活对象时，无法找到可用的空闲分区
    - 分配巨型对象时在老年代无法找到足够的连续分区

    由于G1的应用场合往往堆内存都比较大，所以Full GC的收集代价非常昂贵，应该避免Full GC的发生。

#### 总结

G1是一款非常优秀的垃圾收集器，不仅适合堆内存大的应用，同时也简化了调优的工作。通过主要的参数初始和最大堆空间、以及最大容忍的GC暂停目标，就能得到不错的性能；同时，我们也看到G1对内存空间的浪费较高，但通过**首先收集尽可能多的垃圾**(Garbage First)的设计原则，可以及时发现过期对象，从而让内存占用处于合理的水平。

虽然G1也有类似CMS的收集动作：初始标记、并发标记、重新标记、清除、转移回收，并且也以一个串行收集器做担保机制，但单纯地以类似前三种的过程描述显得并不是很妥当。

- G1的设计原则是"**首先收集尽可能多的垃圾**(Garbage First)"。因此，G1并不会等内存耗尽(串行、并行)或者快耗尽(CMS)的时候开始垃圾收集，而是在内部采用了启发式算法，在老年代找出具有高收集收益的分区进行收集。同时G1可以根据用户设置的暂停时间目标自动调整年轻代和总堆大小，暂停目标越短年轻代空间越小、总空间就越大；
- G1采用内存分区(Region)的思路，将内存划分为一个个相等大小的内存分区，回收时则以分区为单位进行回收，存活的对象复制到另一个空闲分区中。由于都是以相等大小的分区为单位进行操作，因此G1天然就是一种压缩方案(局部压缩)；
- G1虽然也是分代收集器，但整个内存分区不存在物理上的年轻代与老年代的区别，也不需要完全独立的survivor(to space)堆做复制准备。G1只有逻辑上的分代概念，或者说每个分区都可能随G1的运行在不同代之间前后切换；
- G1的收集都是STW的，但年轻代和老年代的收集界限比较模糊，采用了混合(mixed)收集的方式。即每次收集既可能只收集年轻代分区(年轻代收集)，也可能在收集年轻代的同时，包含部分老年代分区(混合收集)，这样即使堆内存很大时，也可以限制收集范围，从而降低停顿。

### 调试排错 - JVM调优参数

#### JVM参数

- -Xms

堆最小值

- -Xmx

堆最大堆值。-Xms与-Xmx 的单位默认字节都是以k、m做单位的。

通常这两个配置参数相等，避免每次空间不足，动态扩容带来的影响。

- -Xmn

新生代大小

- -Xss

每个线程池的堆栈大小。在jdk5以上的版本，每个线程堆栈大小为1m，jdk5以前的版本是每个线程池大小为256k。一般在相同物理内存下，如果减少－xss值会产生更大的线程数，但不同的操作系统对进程内线程数是有限制的，是不能无限生成。

- -XX:NewRatio

设置新生代与老年代比值，-XX:NewRatio=4 表示新生代与老年代所占比例为1:4 ，新生代占比整个堆的五分之一。如果设置了-Xmn的情况下，该参数是不需要在设置的。

- -XX:PermSize

设置持久代初始值，默认是物理内存的六十四分之一

- -XX:MaxPermSize

设置持久代最大值，默认是物理内存的四分之一

- -XX:MaxTenuringThreshold

新生代中对象存活次数，默认15。(若对象在eden区，经历一次MinorGC后还活着，则被移动到Survior区，年龄加1。以后，对象每次经历MinorGC，年龄都加1。达到阀值，则移入老年代)

- -XX:SurvivorRatio

Eden区与Subrvivor区大小的比值，如果设置为8，两个Subrvivor区与一个Eden区的比值为2:8，一个Survivor区占整个新生代的十分之一

- -XX:+UseFastAccessorMethods

原始类型快速优化

- -XX:+AggressiveOpts

编译速度加快

- -XX:PretenureSizeThreshold

对象超过多大值时直接在老年代中分配

```text
说明: 
整个堆大小的计算公式: JVM 堆大小 ＝ 年轻代大小＋年老代大小＋持久代大小。
增大新生代大小就会减少对应的年老代大小，设置-Xmn值对系统性能影响较大，所以如果设置新生代大小的调整，则需要严格的测试调整。而新生代是用来存放新创建的对象，大小是随着堆大小增大和减少而有相应的变化，默认值是保持堆大小的十五分之一，-Xmn参数就是设置新生代的大小，也可以通过-XX:NewRatio来设置新生代与年老代的比例，java 官方推荐配置为3:8。

新生代的特点就是内存中的对象更新速度快，在短时间内容易产生大量的无用对象，如果在这个参数时就需要考虑垃圾回收器设置参数也需要调整。推荐使用: 复制清除算法和并行收集器进行垃圾回收，而新生代的垃圾回收叫做初级回收。
```

```text
StackOverflowError和OutOfMemoryException。当线程中的请求的栈的深度大于最大可用深度，就会抛出前者；若内存空间不够，无法创建新的线程，则会抛出后者。栈的大小直接决定了函数的调用最大深度，栈越大，函数嵌套可调用次数就越多。
```

**经验** :

1. Xmn用于设置新生代的大小。过小会增加Minor GC频率，过大会减小老年代的大小。一般设为整个堆空间的1/4或1/3.
2. XX:SurvivorRatio用于设置新生代中survivor空间(from/to)和eden空间的大小比例； XX:TargetSurvivorRatio表示，当经历Minor GC后，survivor空间占有量(百分比)超过它的时候，就会压缩进入老年代(当然，如果survivor空间不够，则直接进入老年代)。默认值为50%。
3. 为了性能考虑，一开始尽量将新生代对象留在新生代，避免新生的大对象直接进入老年代。因为新生对象大部分都是短期的，这就造成了老年代的内存浪费，并且回收代价也高(Full GC发生在老年代和方法区Perm).
4. 当Xms=Xmx，可以使得堆相对稳定，避免不停震荡
5. 一般来说，MaxPermSize设为64MB可以满足绝大多数的应用了。若依然出现方法区溢出，则可以设为128MB。若128MB还不能满足需求，那么就应该考虑程序优化了，减少**动态类**的产生。

#### 垃圾回收

**垃圾回收算法** :

- 引用计数法: 会有循环引用的问题，古老的方法；
- Mark-Sweep: 标记清除。根可达判断，最大的问题是空间碎片(清除垃圾之后剩下不连续的内存空间)；
- Copying: 复制算法。对于短命对象来说有用，否则需要复制大量的对象，效率低。**如Java的新生代堆空间中就是使用了它(survivor空间的from和to区)；**
- Mark-Compact: 标记整理。对于老年对象来说有用，无需复制，不会产生内存碎片

**GC考虑的指标**

- 吞吐量: 应用耗时和实际耗时的比值；
- 停顿时间: 垃圾回收的时候，由于Stop the World，应用程序的所有线程会挂起，造成应用停顿。

```text
吞吐量和停顿时间是互斥的。
对于后端服务(比如后台计算任务)，吞吐量优先考虑(并行垃圾回收)；
对于前端应用，RT响应时间优先考虑，减少垃圾收集时的停顿时间，适用场景是Web系统(并发垃圾回收)
```

**回收器的JVM参数**

- -XX:+UseSerialGC

串行垃圾回收，现在基本很少使用。

- -XX:+UseParNewGC

新生代使用并行，老年代使用串行；

- -XX:+UseConcMarkSweepGC

新生代使用并行，老年代使用CMS(一般都是使用这种方式)，CMS是Concurrent Mark Sweep的缩写，并发标记清除，一看就是老年代的算法，所以，它可以作为老年代的垃圾回收器。CMS不是独占式的，它关注停顿时间

- -XX:ParallelGCThreads

指定并行的垃圾回收线程的数量，最好等于CPU数量

- -XX:+DisableExplicitGC

禁用System.gc()，因为它会触发Full GC，这是很浪费性能的，JVM会在需要GC的时候自己触发GC。

- -XX:CMSFullGCsBeforeCompaction

在多少次GC后进行内存压缩，这个是因为并行收集器不对内存空间进行压缩的，所以运行一段时间后会产生很多碎片，使得运行效率降低。

- -XX:+CMSParallelRemarkEnabled

降低标记停顿

- -XX:+UseCMSCompactAtFullCollection

在每一次Full GC时对老年代区域碎片整理，因为CMS是不会移动内存的，因此会非常容易出现碎片导致内存不够用的

- -XX:+UseCmsInitiatingOccupancyOnly

使用手动触发或者自定义触发cms 收集，同时也会禁止hostspot 自行触发CMS GC

- -XX:CMSInitiatingOccupancyFraction

使用CMS作为垃圾回收，使用70%后开始CMS收集

- -XX:CMSInitiatingPermOccupancyFraction

设置perm gen使用达到多少％比时触发垃圾回收，默认是92%

- -XX:+CMSIncrementalMode

设置为增量模式

- -XX:+CmsClassUnloadingEnabled

CMS是不会默认对永久代进行垃圾回收的，设置此参数则是开启

- -XX:+PrintGCDetails

开启详细GC日志模式，日志的格式是和所使用的算法有关

- -XX:+PrintGCDateStamps

将时间和日期也加入到GC日志中

### 调试排错 - Java OOM分析

> 本文以两个简单的例子(`堆内存溢出`和`MetaSpace (元数据) 内存溢出`）解释Java 内存溢出的分析过程。@pdai

#### Java 堆内存溢出

在 Java 堆中只要不断的创建对象，并且 `GC-Roots` 到对象之间存在引用链，这样 `JVM` 就不会回收对象。

只要将`-Xms(最小堆)`,`-Xmx(最大堆)` 设置为一样禁止自动扩展堆内存。

当使用一个 `while(true)` 循环来不断创建对象就会发生 `OutOfMemory`，还可以使用 `-XX:+HeapDumpOutofMemoryErorr` 当发生 OOM 时会自动 dump 堆栈到文件中。

伪代码:

````java
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(10) ;
        while (true){
            list.add("1") ;
        }
    }
````

当出现 OOM 时可以通过工具来分析 `GC-Roots` [引用链  (opens new window)](https://github.com/crossoverJie/Java-Interview/blob/master/MD/GarbageCollection.md#可达性分析算法) ，查看对象和 `GC-Roots` 是如何进行关联的，是否存在对象的生命周期过长，或者是这些对象确实改存在的，那就要考虑将堆内存调大了。

````java
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at java.util.Arrays.copyOf(Arrays.java:3210)
	at java.util.Arrays.copyOf(Arrays.java:3181)
	at java.util.ArrayList.grow(ArrayList.java:261)
	at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:235)
	at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:227)
	at java.util.ArrayList.add(ArrayList.java:458)
	at com.crossoverjie.oom.HeapOOM.main(HeapOOM.java:18)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at com.intellij.rt.execution.application.AppMain.main(AppMain.java:147)

Process finished with exit code 1
````

`java.lang.OutOfMemoryError: Java heap space`表示堆内存溢出。

#### MetaSpace (元数据) 内存溢出

> `JDK8` 中将永久代移除，使用 `MetaSpace` 来保存类加载之后的类信息，字符串常量池也被移动到 Java 堆。

`PermSize` 和 `MaxPermSize` 已经不能使用了，在 JDK8 中配置这两个参数将会发出警告。

JDK 8 中将类信息移到到了本地堆内存(Native Heap)中，将原有的永久代移动到了本地堆中成为 `MetaSpace` ,如果不指定该区域的大小，JVM 将会动态的调整。

可以使用 `-XX:MaxMetaspaceSize=10M` 来限制最大元数据。这样当不停的创建类时将会占满该区域并出现 `OOM`。

````java
public static void main(String[] args) {
  while (true){
    Enhancer  enhancer = new Enhancer() ;
    enhancer.setSuperclass(HeapOOM.class);
    enhancer.setUseCache(false) ;
    enhancer.setCallback(new MethodInterceptor() {
      @Override
      public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return methodProxy.invoke(o,objects) ;
      }
    });
    enhancer.create() ;

  }
}
````

使用 `cglib` 不停的创建新类，最终会抛出:

````java
Caused by: java.lang.reflect.InvocationTargetException
	at sun.reflect.GeneratedMethodAccessor1.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at net.sf.cglib.core.ReflectUtils.defineClass(ReflectUtils.java:459)
	at net.sf.cglib.core.AbstractClassGenerator.generate(AbstractClassGenerator.java:336)
	... 11 more
Caused by: java.lang.OutOfMemoryError: Metaspace
	at java.lang.ClassLoader.defineClass1(Native Method)
	at java.lang.ClassLoader.defineClass(ClassLoader.java:763)
	... 16 more
````

注意: 这里的 OOM 伴随的是 `java.lang.OutOfMemoryError: Metaspace` 也就是元数据溢出。

### 调试排错 - Java 线程Dump分析

> > Thread Dump是非常有用的诊断Java应用问题的工具。

#### Thread Dump介绍

- 什么是Thread Dump

  Thread Dump是非常有用的诊断Java应用问题的工具。每一个Java虚拟机都有及时生成所有线程在某一点状态的thread-dump的能力，虽然各个 Java虚拟机打印的thread dump略有不同，但是 大多都提供了当前活动线程的快照，及JVM中所有Java线程的堆栈跟踪信息，堆栈信息一般包含完整的类名及所执行的方法，如果可能的话还有源代码的行数。

- Thread Dump特点

  - 能在各种操作系统下使用；
  - 能在各种Java应用服务器下使用；
  - 能在生产环境下使用而不影响系统的性能；
  - 能将问题直接定位到应用程序的代码行上；

- Thread Dump抓取

  - 操作系统命令获取ThreadDump

    ````sh
    ps –ef | grep java
    kill -3 <pid>
    ````

  > 一定要谨慎, 一步不慎就可能让服务器进程被杀死。kill -9 命令会杀死进程。

  - JVM 自带的工具获取线程堆栈

    ````sh
    jps 或 ps –ef | grep java （获取PID）
    jstack [-l ] <pid> | tee -a jstack.log（获取ThreadDump）
    ````

#### Thread Dump分析

- Thread Dump信息

  - 头部信息：时间，JVM信息

    ````sh
    2011-11-02 19:05:06  
    Full thread dump Java HotSpot(TM) Server VM (16.3-b01 mixed mode): 
    ````

  - 线程INFO信息块

    ````sh
    1. "Timer-0" daemon prio=10 tid=0xac190c00 nid=0xaef in Object.wait() [0xae77d000] 
    # 线程名称：Timer-0；线程类型：daemon；优先级: 10，默认是5；
    # JVM线程id：tid=0xac190c00，JVM内部线程的唯一标识（通过java.lang.Thread.getId()获取，通常用自增方式实现）。
    # 对应系统线程id（NativeThread ID）：nid=0xaef，和top命令查看的线程pid对应，不过一个是10进制，一个是16进制。（通过命令：top -H -p pid，可以查看该进程的所有线程信息）
    # 线程状态：in Object.wait()；
    # 起始栈地址：[0xae77d000]，对象的内存地址，通过JVM内存查看工具，能够看出线程是在哪儿个对象上等待；
    2.  java.lang.Thread.State: TIMED_WAITING (on object monitor)
    3.  at java.lang.Object.wait(Native Method)
    4.  -waiting on <0xb3885f60> (a java.util.TaskQueue)     # 继续wait 
    5.  at java.util.TimerThread.mainLoop(Timer.java:509)
    6.  -locked <0xb3885f60> (a java.util.TaskQueue)         # 已经locked
    7.  at java.util.TimerThread.run(Timer.java:462)
    Java thread statck trace：是上面2-7行的信息。到目前为止这是最重要的数据，Java stack trace提供了大部分信息来精确定位问题根源。
    ````

  - Java thread statck trace详解

    **堆栈信息应该逆向解读**：程序先执行的是第7行，然后是第6行，依次类推。

    ````sh
    - locked <0xb3885f60> (a java.util.ArrayList)
    - waiting on <0xb3885f60> (a java.util.ArrayList) 
    ````

    **也就是说对象先上锁，锁住对象0xb3885f60，然后释放该对象锁，进入waiting状态**。为啥会出现这样的情况呢？看看下面的java代码示例，就会明白：

    ````java
    synchronized(obj) {  
       .........  
       obj.wait();  
       .........  
    }
    ````

    如上，线程的执行过程，先用 `synchronized` 获得了这个对象的 Monitor（对应于 `locked <0xb3885f60>` ）。当执行到 `obj.wait()`，线程即放弃了 Monitor的所有权，进入 “wait set”队列（对应于 `waiting on <0xb3885f60>` ）。

    **在堆栈的第一行信息中，进一步标明了线程在代码级的状态**，例如：

    ```bash
    java.lang.Thread.State: TIMED_WAITING (parking)
    ```

    解释如下：

    ```bash
    |blocked|
    
    > This thread tried to enter asynchronized block, but the lock was taken by another thread. This thread isblocked until the lock gets released.
    
    |blocked (on thin lock)|
    
    > This is the same state asblocked, but the lock in question is a thin lock.
    
    |waiting|
    
    > This thread calledObject.wait() on an object. The thread will remain there until some otherthread sends a notification to that object.
    
    |sleeping|
    
    > This thread calledjava.lang.Thread.sleep().
    
    |parked|
    
    > This thread calledjava.util.concurrent.locks.LockSupport.park().
    
    |suspended|
    
    > The thread's execution wassuspended by java.lang.Thread.suspend() or a JVMTI agent call.
    ```

- Thread状态分析

  线程的状态是一个很重要的东西，因此thread dump中会显示这些状态，通过对这些状态的分析，能够得出线程的运行状况，进而发现可能存在的问题。**线程的状态在Thread.State这个枚举类型中定义**：

  ````java
  public enum State   
  {  
         /** 
          * Thread state for a thread which has not yet started. 
          */  
         NEW,  
           
         /** 
          * Thread state for a runnable thread.  A thread in the runnable 
          * state is executing in the Java virtual machine but it may 
          * be waiting for other resources from the operating system 
          * such as processor. 
          */  
         RUNNABLE,  
           
         /** 
          * Thread state for a thread blocked waiting for a monitor lock. 
          * A thread in the blocked state is waiting for a monitor lock 
          * to enter a synchronized block/method or  
          * reenter a synchronized block/method after calling 
          * {@link Object#wait() Object.wait}. 
          */  
         BLOCKED,  
       
         /** 
          * Thread state for a waiting thread. 
          * A thread is in the waiting state due to calling one of the  
          * following methods: 
          * <ul> 
          *   <li>{@link Object#wait() Object.wait} with no timeout</li> 
          *   <li>{@link #join() Thread.join} with no timeout</li> 
          *   <li>{@link LockSupport#park() LockSupport.park}</li> 
          * </ul> 
          *  
          * <p>A thread in the waiting state is waiting for another thread to 
          * perform a particular action.   
          * 
          * For example, a thread that has called <tt>Object.wait()</tt> 
          * on an object is waiting for another thread to call  
          * <tt>Object.notify()</tt> or <tt>Object.notifyAll()</tt> on  
          * that object. A thread that has called <tt>Thread.join()</tt>  
          * is waiting for a specified thread to terminate. 
          */  
         WAITING,  
           
         /** 
          * Thread state for a waiting thread with a specified waiting time. 
          * A thread is in the timed waiting state due to calling one of  
          * the following methods with a specified positive waiting time: 
          * <ul> 
          *   <li>{@link #sleep Thread.sleep}</li> 
          *   <li>{@link Object#wait(long) Object.wait} with timeout</li> 
          *   <li>{@link #join(long) Thread.join} with timeout</li> 
          *   <li>{@link LockSupport#parkNanos LockSupport.parkNanos}</li>  
          *   <li>{@link LockSupport#parkUntil LockSupport.parkUntil}</li> 
          * </ul> 
          */  
         TIMED_WAITING,  
    
         /** 
          * Thread state for a terminated thread. 
          * The thread has completed execution. 
          */  
         TERMINATED;  
  }
  ````

  - NEW：

  每一个线程，在堆内存中都有一个对应的Thread对象。Thread t = new Thread();当刚刚在堆内存中创建Thread对象，还没有调用t.start()方法之前，线程就处在NEW状态。在这个状态上，线程与普通的java对象没有什么区别，就仅仅是一个堆内存中的对象。

  - RUNNABLE：

  该状态表示线程具备所有运行条件，在运行队列中准备操作系统的调度，或者正在运行。 这个状态的线程比较正常，但如果线程长时间停留在在这个状态就不正常了，这说明线程运行的时间很长（存在性能问题），或者是线程一直得不得执行的机会（存在线程饥饿的问题）。

  - BLOCKED：

  线程正在等待获取java对象的监视器(也叫内置锁)，即线程正在等待进入由synchronized保护的方法或者代码块。synchronized用来保证原子性，任意时刻最多只能由一个线程进入该临界区域，其他线程只能排队等待。

  - WAITING：

  处在该线程的状态，正在等待某个事件的发生，只有特定的条件满足，才能获得执行机会。而产生这个特定的事件，通常都是另一个线程。也就是说，如果不发生特定的事件，那么处在该状态的线程一直等待，不能获取执行的机会。比如：

  A线程调用了obj对象的obj.wait()方法，如果没有线程调用obj.notify或obj.notifyAll，那么A线程就没有办法恢复运行； 如果A线程调用了LockSupport.park()，没有别的线程调用LockSupport.unpark(A)，那么A没有办法恢复运行。 TIMED_WAITING：

  J.U.C中很多与线程相关类，都提供了限时版本和不限时版本的API。TIMED_WAITING意味着线程调用了限时版本的API，正在等待时间流逝。当等待时间过去后，线程一样可以恢复运行。如果线程进入了WAITING状态，一定要特定的事件发生才能恢复运行；而处在TIMED_WAITING的线程，如果特定的事件发生或者是时间流逝完毕，都会恢复运行。

  - TERMINATED：

  线程执行完毕，执行完run方法正常返回，或者抛出了运行时异常而结束，线程都会停留在这个状态。这个时候线程只剩下Thread对象了，没有什么用了。

- 关键状态分析

  - **Wait on condition**：The thread is either sleeping or waiting to be notified by another thread.

    该状态说明它在等待另一个条件的发生，来把自己唤醒，或者干脆它是调用了 sleep(n)。

    此时线程状态大致为以下几种：

    ````sh
    java.lang.Thread.State: WAITING (parking)：一直等那个条件发生；
    java.lang.Thread.State: TIMED_WAITING (parking或sleeping)：定时的，那个条件不到来，也将定时唤醒自己。
    ````

  - **Waiting for Monitor Entry 和 in Object.wait()**：The thread is waiting to get the lock for an object (some other thread may be holding the lock). This happens if two or more threads try to execute synchronized code. Note that the lock is always for an object and not for individual methods.

  在多线程的JAVA程序中，实现线程之间的同步，就要说说 Monitor。**Monitor是Java中用以实现线程之间的互斥与协作的主要手段，它可以看成是对象或者Class的锁。每一个对象都有，也仅有一个 Monitor** 。下面这个图，描述了线程和 Monitor之间关系，以及线程的状态转换图：

  ![image](https://www.pdai.tech/_images/jvm/java-jvm-debug-1.png)

  如上图，每个Monitor在某个时刻，只能被一个线程拥有，**该线程就是 “ActiveThread”，而其它线程都是 “Waiting Thread”，分别在两个队列“Entry Set”和“Wait Set”里等候**。在“Entry Set”中等待的线程状态是“Waiting for monitor entry”，而在“Wait Set”中等待的线程状态是“in Object.wait()”。

  先看“Entry Set”里面的线程。我们称被 synchronized保护起来的代码段为临界区。**当一个线程申请进入临界区时，它就进入了“Entry Set”队列**。对应的 code就像：

  ````java
  synchronized(obj) {
     .........
  }
  ````

  这时有两种可能性：

  - 该 monitor不被其它线程拥有， Entry Set里面也没有其它等待线程。本线程即成为相应类或者对象的 Monitor的 Owner，执行临界区的代码。
  - 该 monitor被其它线程拥有，本线程在 Entry Set队列中等待。

  在第一种情况下，线程将处于 “Runnable”的状态，而第二种情况下，线程 DUMP会显示处于 “waiting for monitor entry”。如下：

  ````java
  "Thread-0" prio=10 tid=0x08222eb0 nid=0x9 waiting for monitor entry [0xf927b000..0xf927bdb8] 
  at testthread.WaitThread.run(WaitThread.java:39) 
  - waiting to lock <0xef63bf08> (a java.lang.Object) 
  - locked <0xef63beb8> (a java.util.ArrayList) 
  at java.lang.Thread.run(Thread.java:595) 
  ````

  **临界区的设置，是为了保证其内部的代码执行的原子性和完整性**。但是因为临界区在任何时间只允许线程串行通过，这和我们多线程的程序的初衷是相反的。**如果在多线程的程序中，大量使用 synchronized，或者不适当的使用了它，会造成大量线程在临界区的入口等待，造成系统的性能大幅下降**。如果在线程 DUMP中发现了这个情况，应该审查源码，改进程序。

  再看“Wait Set”里面的线程。**当线程获得了 Monitor，进入了临界区之后，如果发现线程继续运行的条件没有满足，它则调用对象（一般就是被 synchronized 的对象）的 wait() 方法，放弃 Monitor，进入 “Wait Set”队列。只有当别的线程在该对象上调用了 notify() 或者 notifyAll()，“Wait Set”队列中线程才得到机会去竞争**，但是只有一个线程获得对象的Monitor，恢复到运行态。在 “Wait Set”中的线程， DUMP中表现为： in Object.wait()。如下：

  ````java
  "Thread-1" prio=10 tid=0x08223250 nid=0xa in Object.wait() [0xef47a000..0xef47aa38] 
   at java.lang.Object.wait(Native Method) 
   - waiting on <0xef63beb8> (a java.util.ArrayList) 
   at java.lang.Object.wait(Object.java:474) 
   at testthread.MyWaitThread.run(MyWaitThread.java:40) 
   - locked <0xef63beb8> (a java.util.ArrayList) 
   at java.lang.Thread.run(Thread.java:595) 
  综上，一般CPU很忙时，则关注runnable的线程，CPU很闲时，则关注waiting for monitor entry的线程。
  ````

  - **JDK 5.0 的 Lock**

  上面提到如果 synchronized和 monitor机制运用不当，可能会造成多线程程序的性能问题。在 JDK 5.0中，引入了 Lock机制，从而使开发者能更灵活的开发高性能的并发多线程程序，可以替代以往 JDK中的 synchronized和 Monitor的 机制。但是，**要注意的是，因为 Lock类只是一个普通类，JVM无从得知 Lock对象的占用情况，所以在线程 DUMP中，也不会包含关于 Lock的信息**， 关于死锁等问题，就不如用 synchronized的编程方式容易识别。

- 关键状态示例

  - **显示BLOCKED状态**

    ````java
    package jstack;  
    
    public class BlockedState  
    {  
        private static Object object = new Object();  
        
        public static void main(String[] args)  
        {  
            Runnable task = new Runnable() {  
    
                @Override  
                public void run()  
                {  
                    synchronized (object)  
                    {  
                        long begin = System.currentTimeMillis();  
      
                        long end = System.currentTimeMillis();  
    
                        // 让线程运行5分钟,会一直持有object的监视器  
                        while ((end - begin) <= 5 * 60 * 1000)  
                        {  
      
                        }  
                    }  
                }  
            };  
    
            new Thread(task, "t1").start();  
            new Thread(task, "t2").start();  
        }  
    }
    ````

    先获取object的线程会执行5分钟，**这5分钟内会一直持有object的监视器，另一个线程无法执行处在BLOCKED状态**：

    ````java
    Full thread dump Java HotSpot(TM) Server VM (20.12-b01 mixed mode):  
      
    "DestroyJavaVM" prio=6 tid=0x00856c00 nid=0x1314 waiting on condition [0x00000000]  
    java.lang.Thread.State: RUNNABLE  
    
    "t2" prio=6 tid=0x27d7a800 nid=0x1350 waiting for monitor entry [0x2833f000]  
    java.lang.Thread.State: BLOCKED (on object monitor)  
         at jstack.BlockedState$1.run(BlockedState.java:17)  
         - waiting to lock <0x1cfcdc00> (a java.lang.Object)  
         at java.lang.Thread.run(Thread.java:662)  
    
    "t1" prio=6 tid=0x27d79400 nid=0x1338 runnable [0x282ef000]  
     java.lang.Thread.State: RUNNABLE  
         at jstack.BlockedState$1.run(BlockedState.java:22)  
         - locked <0x1cfcdc00> (a java.lang.Object)  
         at java.lang.Thread.run(Thread.java:662)
    ````

    通过thread dump可以看到：**t2线程确实处在BLOCKED (on object monitor)。waiting for monitor entry 等待进入synchronized保护的区域**。

    - **显示WAITING状态**

      ````java
      package jstack;  
        
      public class WaitingState  
      {  
          private static Object object = new Object();  
      
          public static void main(String[] args)  
          {  
              Runnable task = new Runnable() {  
      
                  @Override  
                  public void run()  
                  {  
                      synchronized (object)  
                      {  
                          long begin = System.currentTimeMillis();  
                          long end = System.currentTimeMillis();  
      
                          // 让线程运行5分钟,会一直持有object的监视器  
                          while ((end - begin) <= 5 * 60 * 1000)  
                          {  
                              try  
                              {  
                                  // 进入等待的同时,会进入释放监视器  
                                  object.wait();  
                              } catch (InterruptedException e)  
                              {  
                                  e.printStackTrace();  
                              }  
                          }  
                      }  
                  }  
              };  
      
              new Thread(task, "t1").start();  
              new Thread(task, "t2").start();  
          }  
      }
      ````

      ````java
      Full thread dump Java HotSpot(TM) Server VM (20.12-b01 mixed mode):  
      
      "DestroyJavaVM" prio=6 tid=0x00856c00 nid=0x1734 waiting on condition [0x00000000]  
      java.lang.Thread.State: RUNNABLE  
      
      "t2" prio=6 tid=0x27d7e000 nid=0x17f4 in Object.wait() [0x2833f000]  
      java.lang.Thread.State: WAITING (on object monitor)  
           at java.lang.Object.wait(Native Method)  
           - waiting on <0x1cfcdc00> (a java.lang.Object)  
           at java.lang.Object.wait(Object.java:485)  
           at jstack.WaitingState$1.run(WaitingState.java:26)  
           - locked <0x1cfcdc00> (a java.lang.Object)  
           at java.lang.Thread.run(Thread.java:662)  
      
      "t1" prio=6 tid=0x27d7d400 nid=0x17f0 in Object.wait() [0x282ef000]  
      java.lang.Thread.State: WAITING (on object monitor)  
           at java.lang.Object.wait(Native Method)  
           - waiting on <0x1cfcdc00> (a java.lang.Object)  
           at java.lang.Object.wait(Object.java:485)  
           at jstack.WaitingState$1.run(WaitingState.java:26)  
           - locked <0x1cfcdc00> (a java.lang.Object)  
           at java.lang.Thread.run(Thread.java:662)
      ````

      可以发现t1和t2都处在WAITING (on object monitor)，进入等待状态的原因是调用了in Object.wait()。通过J.U.C包下的锁和条件队列，也是这个效果，大家可以自己实践下。

    - **显示TIMED_WAITING状态**

      ````java
      package jstack;  
      
      import java.util.concurrent.TimeUnit;  
      import java.util.concurrent.locks.Condition;  
      import java.util.concurrent.locks.Lock;  
      import java.util.concurrent.locks.ReentrantLock;  
        
      public class TimedWaitingState  
      {  
          // java的显示锁,类似java对象内置的监视器  
          private static Lock lock = new ReentrantLock();  
        
          // 锁关联的条件队列(类似于object.wait)  
          private static Condition condition = lock.newCondition();  
      
          public static void main(String[] args)  
          {  
              Runnable task = new Runnable() {  
      
                  @Override  
                  public void run()  
                  {  
                      // 加锁,进入临界区  
                      lock.lock();  
        
                      try  
                      {  
                          condition.await(5, TimeUnit.MINUTES);  
                      } catch (InterruptedException e)  
                      {  
                          e.printStackTrace();  
                      }  
        
                      // 解锁,退出临界区  
                      lock.unlock();  
                  }  
              };  
        
              new Thread(task, "t1").start();  
              new Thread(task, "t2").start();  
          }  
      }
      ````

      ````java
      Full thread dump Java HotSpot(TM) Server VM (20.12-b01 mixed mode):  
      
      "DestroyJavaVM" prio=6 tid=0x00856c00 nid=0x169c waiting on condition [0x00000000]  
      java.lang.Thread.State: RUNNABLE  
      
      "t2" prio=6 tid=0x27d7d800 nid=0xc30 waiting on condition [0x2833f000]  
      java.lang.Thread.State: TIMED_WAITING (parking)  
           at sun.misc.Unsafe.park(Native Method)  
           - parking to wait for  <0x1cfce5b8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)  
           at java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:196)  
           at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2116)  
           at jstack.TimedWaitingState$1.run(TimedWaitingState.java:28)  
           at java.lang.Thread.run(Thread.java:662)  
      
      "t1" prio=6 tid=0x280d0c00 nid=0x16e0 waiting on condition [0x282ef000]  
      java.lang.Thread.State: TIMED_WAITING (parking)  
           at sun.misc.Unsafe.park(Native Method)  
           - parking to wait for  <0x1cfce5b8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)  
           at java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:196)  
           at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2116)  
           at jstack.TimedWaitingState$1.run(TimedWaitingState.java:28)  
           at java.lang.Thread.run(Thread.java:662)  
      ````

      可以看到t1和t2线程都处在java.lang.Thread.State: TIMED_WAITING (parking)，这个parking代表是调用的JUC下的工具类，而不是java默认的监视器。

#### 案例分析

- 问题场景

  - **CPU飙高，load高，响应很慢**
    1. 一个请求过程中多次dump；
    2. 对比多次dump文件的runnable线程，如果执行的方法有比较大变化，说明比较正常。如果在执行同一个方法，就有一些问题了；
  - **查找占用CPU最多的线程**
    1. 使用命令：top -H -p pid（pid为被测系统的进程号），找到导致CPU高的线程ID，对应thread dump信息中线程的nid，只不过一个是十进制，一个是十六进制；
    2. 在thread dump中，根据top命令查找的线程id，查找对应的线程堆栈信息；
  - **CPU使用率不高但是响应很慢**

  进行dump，查看是否有很多thread struck在了i/o、数据库等地方，定位瓶颈原因；

  - **请求无法响应**

  多次dump，对比是否所有的runnable线程都一直在执行相同的方法，如果是的，恭喜你，锁住了！

- 死锁

  死锁经常表现为程序的停顿，或者不再响应用户的请求。从操作系统上观察，对应进程的CPU占用率为零，很快会从top或prstat的输出中消失。

  比如在下面这个示例中，是个较为典型的死锁情况：

  ````java
  "Thread-1" prio=5 tid=0x00acc490 nid=0xe50 waiting for monitor entry [0x02d3f000 
  ..0x02d3fd68] 
  at deadlockthreads.TestThread.run(TestThread.java:31) 
  - waiting to lock <0x22c19f18> (a java.lang.Object) 
  - locked <0x22c19f20> (a java.lang.Object) 
  
  "Thread-0" prio=5 tid=0x00accdb0 nid=0xdec waiting for monitor entry [0x02cff000 
  ..0x02cff9e8] 
  at deadlockthreads.TestThread.run(TestThread.java:31) 
  - waiting to lock <0x22c19f20> (a java.lang.Object) 
  - locked <0x22c19f18> (a java.lang.Object) 
  ````

  在 JAVA 5中加强了对死锁的检测。**线程 Dump中可以直接报告出 Java级别的死锁**，如下所示：

  ````java
  Found one Java-level deadlock: 
  ============================= 
  "Thread-1": 
  waiting to lock monitor 0x0003f334 (object 0x22c19f18, a java.lang.Object), 
  which is held by "Thread-0" 
  
  "Thread-0": 
  waiting to lock monitor 0x0003f314 (object 0x22c19f20, a java.lang.Object), 
  which is held by "Thread-1"
  ````

- 热锁

  热锁，也往往是导致系统性能瓶颈的主要因素。其表现特征为：**由于多个线程对临界区，或者锁的竞争**，可能出现：

  - **频繁的线程的上下文切换**：从操作系统对线程的调度来看，当线程在等待资源而阻塞的时候，操作系统会将之切换出来，放到等待的队列，当线程获得资源之后，调度算法会将这个线程切换进去，放到执行队列中。
  - **大量的系统调用**：因为线程的上下文切换，以及热锁的竞争，或者临界区的频繁的进出，都可能导致大量的系统调用。
  - **大部分CPU开销用在“系统态”**：线程上下文切换，和系统调用，都会导致 CPU在 “系统态 ”运行，换而言之，虽然系统很忙碌，但是CPU用在 “用户态 ”的比例较小，应用程序得不到充分的 CPU资源。
  - **随着CPU数目的增多，系统的性能反而下降**。因为CPU数目多，同时运行的线程就越多，可能就会造成更频繁的线程上下文切换和系统态的CPU开销，从而导致更糟糕的性能。

  上面的描述，都是一个 scalability（可扩展性）很差的系统的表现。从整体的性能指标看，由于线程热锁的存在，程序的响应时间会变长，吞吐量会降低。

  **那么，怎么去了解 “热锁 ”出现在什么地方呢**？

  一个重要的方法是 结合操作系统的各种工具观察系统资源使用状况，以及收集Java线程的DUMP信息，看线程都阻塞在什么方法上，了解原因，才能找到对应的解决方法。

#### JVM重要线程

JVM运行过程中产生的一些比较重要的线程罗列如下：

| 线程名称                        | 解释说明                                                     |
| ------------------------------- | ------------------------------------------------------------ |
| Attach Listener                 | Attach Listener 线程是负责接收到外部的命令，而对该命令进行执行的并把结果返回给发送者。通常我们会用一些命令去要求JVM给我们一些反馈信息，如：java -version、jmap、jstack等等。 如果该线程在JVM启动的时候没有初始化，那么，则会在用户第一次执行JVM命令时，得到启动。 |
| Signal Dispatcher               | 前面提到Attach Listener线程的职责是接收外部JVM命令，当命令接收成功后，会交给signal dispather线程去进行分发到各个不同的模块处理命令，并且返回处理结果。signal dispather线程也是在第一次接收外部JVM命令时，进行初始化工作。 |
| CompilerThread0                 | 用来调用JITing，实时编译装卸class 。 通常，JVM会启动多个线程来处理这部分工作，线程名称后面的数字也会累加，例如：CompilerThread1。 |
| Concurrent Mark-Sweep GC Thread | 并发标记清除垃圾回收器（就是通常所说的CMS GC）线程， 该线程主要针对于老年代垃圾回收。ps：启用该垃圾回收器，需要在JVM启动参数中加上：-XX:+UseConcMarkSweepGC。 |
| DestroyJavaVM                   | 执行main()的线程，在main执行完后调用JNI中的 jni_DestroyJavaVM() 方法唤起DestroyJavaVM 线程，处于等待状态，等待其它线程（Java线程和Native线程）退出时通知它卸载JVM。每个线程退出时，都会判断自己当前是否是整个JVM中最后一个非deamon线程，如果是，则通知DestroyJavaVM 线程卸载JVM。 |
| Finalizer Thread                | 这个线程也是在main线程之后创建的，其优先级为10，主要用于在垃圾收集前，调用对象的finalize()方法；关于Finalizer线程的几点：1) 只有当开始一轮垃圾收集时，才会开始调用finalize()方法；因此并不是所有对象的finalize()方法都会被执行；2) 该线程也是daemon线程，因此如果虚拟机中没有其他非daemon线程，不管该线程有没有执行完finalize()方法，JVM也会退出；3) JVM在垃圾收集时会将失去引用的对象包装成Finalizer对象（Reference的实现），并放入ReferenceQueue，由Finalizer线程来处理；最后将该Finalizer对象的引用置为null，由垃圾收集器来回收；4) JVM为什么要单独用一个线程来执行finalize()方法呢？如果JVM的垃圾收集线程自己来做，很有可能由于在finalize()方法中误操作导致GC线程停止或不可控，这对GC线程来说是一种灾难； |
| Low Memory Detector             | 这个线程是负责对可使用内存进行检测，如果发现可用内存低，分配新的内存空间。 |
| Reference Handler               | JVM在创建main线程后就创建Reference Handler线程，其优先级最高，为10，它主要用于处理引用对象本身（软引用、弱引用、虚引用）的垃圾回收问题 。 |
| VM Thread                       | 这个线程就比较牛b了，是JVM里面的线程母体，根据hotspot源码（vmThread.hpp）里面的注释，它是一个单个的对象（最原始的线程）会产生或触发所有其他的线程，这个单个的VM线程是会被其他线程所使用来做一些VM操作（如：清扫垃圾等）。 |

### 调试排错 - Java 问题排查：Linux命令

#### 文本操作

- 文本查找 - grep

  grep常用命令：

  ````sh
  # 基本使用
  grep yoursearchkeyword f.txt     #文件查找
  grep 'KeyWord otherKeyWord' f.txt cpf.txt #多文件查找, 含空格加引号
  grep 'KeyWord' /home/admin -r -n #目录下查找所有符合关键字的文件
  grep 'keyword' /home/admin -r -n -i # -i 忽略大小写
  grep 'KeyWord' /home/admin -r -n --include *.{vm,java} #指定文件后缀
  grep 'KeyWord' /home/admin -r -n --exclude *.{vm,java} #反匹配
  
  # cat + grep
  cat f.txt | grep -i keyword # 查找所有keyword且不分大小写  
  cat f.txt | grep -c 'KeyWord' # 统计Keyword次数
  
  # seq + grep
  seq 10 | grep 5 -A 3    #上匹配
  seq 10 | grep 5 -B 3    #下匹配
  seq 10 | grep 5 -C 3    #上下匹配，平时用这个就妥了
  ````

- 文本分析 - awk

  awk基本命令：

  ````sh
  # 基本使用
  awk '{print $4,$6}' f.txt
  awk '{print NR,$0}' f.txt cpf.txt    
  awk '{print FNR,$0}' f.txt cpf.txt
  awk '{print FNR,FILENAME,$0}' f.txt cpf.txt
  awk '{print FILENAME,"NR="NR,"FNR="FNR,"$"NF"="$NF}' f.txt cpf.txt
  echo 1:2:3:4 | awk -F: '{print $1,$2,$3,$4}'
  
  # 匹配
  awk '/ldb/ {print}' f.txt   #匹配ldb
  awk '!/ldb/ {print}' f.txt  #不匹配ldb
  awk '/ldb/ && /LISTEN/ {print}' f.txt   #匹配ldb和LISTEN
  awk '$5 ~ /ldb/ {print}' f.txt #第五列匹配ldb
  ````

  内建变量

  ````sh
  `NR`: NR表示从awk开始执行后，按照记录分隔符读取的数据次数，默认的记录分隔符为换行符，因此默认的就是读取的数据行数，NR可以理解为Number of Record的缩写。
  
  `FNR`: 在awk处理多个输入文件的时候，在处理完第一个文件后，NR并不会从1开始，而是继续累加，因此就出现了FNR，每当处理一个新文件的时候，FNR就从1开始计数，FNR可以理解为File Number of Record。
  
  `NF`: NF表示目前的记录被分割的字段的数目，NF可以理解为Number of Field。
  ````

  更多请参考：[Linux awk 命令](https://www.runoob.com/linux/linux-comm-awk.html)

- 文本处理 - sed

  sed常用：

  ````sh
  # 文本打印
  sed -n '3p' xxx.log #只打印第三行
  sed -n '$p' xxx.log #只打印最后一行
  sed -n '3,9p' xxx.log #只查看文件的第3行到第9行
  sed -n -e '3,9p' -e '=' xxx.log #打印3-9行，并显示行号
  sed -n '/root/p' xxx.log #显示包含root的行
  sed -n '/hhh/,/omc/p' xxx.log # 显示包含"hhh"的行到包含"omc"的行之间的行
  
  # 文本替换
  sed -i 's/root/world/g' xxx.log # 用world 替换xxx.log文件中的root; s==search  查找并替换, g==global  全部替换, -i: implace
  
  # 文本插入
  sed '1,4i hahaha' xxx.log # 在文件第一行和第四行的每行下面添加hahaha
  sed -e '1i happy' -e '$a new year' xxx.log  #【界面显示】在文件第一行添加happy,文件结尾添加new year
  sed -i -e '1i happy' -e '$a new year' xxx.log #【真实写入文件】在文件第一行添加happy,文件结尾添加new year
  
  # 文本删除
  sed  '3,9d' xxx.log # 删除第3到第9行,只是不显示而已
  sed '/hhh/,/omc/d' xxx.log # 删除包含"hhh"的行到包含"omc"的行之间的行
  sed '/omc/,10d' xxx.log # 删除包含"omc"的行到第十行的内容
  
  # 与find结合
  find . -name  "*.txt" |xargs   sed -i 's/hhhh/\hHHh/g'
  find . -name  "*.txt" |xargs   sed -i 's#hhhh#hHHh#g'
  find . -name  "*.txt" -exec sed -i 's/hhhh/\hHHh/g' {} \;
  find . -name  "*.txt" |xargs cat
  ````

  更多请参考：[Linux sed 命令 (opens new window)](https://www.runoob.com/linux/linux-comm-sed.html)或者 [Linux sed命令详解](https://www.cnblogs.com/ftl1012/p/9250171.html)

#### 文件操作

- 文件监听 - tail

  最常用的`tail -f filename`

- 文件查找 - find

  ````sh
  sudo -u admin find /home/admin /tmp /usr -name \*.log(多个目录去找)
  find . -iname \*.txt(大小写都匹配)
  find . -type d(当前目录下的所有子目录)
  find /usr -type l(当前目录下所有的符号链接)
  find /usr -type l -name "z*" -ls(符号链接的详细信息 eg:inode,目录)
  find /home/admin -size +250000k(超过250000k的文件，当然+改成-就是小于了)
  find /home/admin f -perm 777 -exec ls -l {} \; (按照权限查询文件)
  find /home/admin -atime -1  1天内访问过的文件
  find /home/admin -ctime -1  1天内状态改变过的文件    
  find /home/admin -mtime -1  1天内修改过的文件
  find /home/admin -amin -1  1分钟内访问过的文件
  find /home/admin -cmin -1  1分钟内状态改变过的文件    
  find /home/admin -mmin -1  1分钟内修改过的文件
  ````

- pgm

  批量查询vm-shopbase满足条件的日志

  ````sh
  pgm -A -f vm-shopbase 'cat /home/admin/shopbase/logs/shopbase.log.2017-01-17|grep 2069861630'
  ````

#### 查看网络和进程

- 查看所有网络接口的属性

  ````sh
  [root@pdai.tech ~]# ifconfig
  eth0: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
          inet 172.31.165.194  netmask 255.255.240.0  broadcast 172.31.175.255
          ether 00:16:3e:08:c1:ea  txqueuelen 1000  (Ethernet)
          RX packets 21213152  bytes 2812084823 (2.6 GiB)
          RX errors 0  dropped 0  overruns 0  frame 0
          TX packets 25264438  bytes 46566724676 (43.3 GiB)
          TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0
  
  lo: flags=73<UP,LOOPBACK,RUNNING>  mtu 65536
          inet 127.0.0.1  netmask 255.0.0.0
          loop  txqueuelen 1000  (Local Loopback)
          RX packets 502  bytes 86350 (84.3 KiB)
          RX errors 0  dropped 0  overruns 0  frame 0
          TX packets 502  bytes 86350 (84.3 KiB)
          TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0
  ````

- 查看防火墙设置

  ````sh
  [root@pdai.tech ~]# iptables -L
  Chain INPUT (policy ACCEPT)
  target     prot opt source               destination
  
  Chain FORWARD (policy ACCEPT)
  target     prot opt source               destination
  
  Chain OUTPUT (policy ACCEPT)
  target     prot opt source               destination
  ````

- 查看路由表

  ````sh
  [root@pdai.tech ~]# route -n
  Kernel IP routing table
  Destination     Gateway         Genmask         Flags Metric Ref    Use Iface
  0.0.0.0         172.31.175.253  0.0.0.0         UG    0      0        0 eth0
  169.254.0.0     0.0.0.0         255.255.0.0     U     1002   0        0 eth0
  172.31.160.0    0.0.0.0         255.255.240.0   U     0      0        0 eth0
  ````

- netstat

  ````sh
  [root@pdai.tech ~]# netstat -lntp
  Active Internet connections (only servers)
  Proto Recv-Q Send-Q Local Address           Foreign Address         State       PID/Program name  
  tcp        0      0 0.0.0.0:443             0.0.0.0:*               LISTEN      970/nginx: master p
  tcp        0      0 0.0.0.0:9999            0.0.0.0:*               LISTEN      1249/java         
  tcp        0      0 0.0.0.0:80              0.0.0.0:*               LISTEN      970/nginx: master p
  tcp        0      0 0.0.0.0:22              0.0.0.0:*               LISTEN      1547/sshd         
  tcp6       0      0 :::3306                 :::*                    LISTEN      1894/mysqld       
  ````

  查看所有已经建立的连接

  ````sh
  [root@pdai.tech ~]# netstat -antp
  Active Internet connections (servers and established)
  Proto Recv-Q Send-Q Local Address           Foreign Address         State       PID/Program name
  tcp        0      0 0.0.0.0:443             0.0.0.0:*               LISTEN      970/nginx: master p
  tcp        0      0 0.0.0.0:9999            0.0.0.0:*               LISTEN      1249/java
  tcp        0      0 0.0.0.0:80              0.0.0.0:*               LISTEN      970/nginx: master p
  tcp        0      0 0.0.0.0:22              0.0.0.0:*               LISTEN      1547/sshd
  tcp        0      0 172.31.165.194:53874    100.100.30.25:80        ESTABLISHED 18041/AliYunDun
  tcp        0     64 172.31.165.194:22       xxx.194.1.200:2649      ESTABLISHED 32516/sshd: root@pt
  tcp6       0      0 :::3306                 :::*                    LISTEN      1894/m
  ````

  查看当前连接

  ````sh
  [root@pdai.tech ~]# netstat -nat|awk  '{print $6}'|sort|uniq -c|sort -rn
        5 LISTEN
        2 ESTABLISHED
        1 Foreign
        1 established)
  ````

  查看网络统计信息进程

  ````sh
  [root@pdai.tech ~]# netstat -s
  Ip:
      21017132 total packets received
      0 forwarded
      0 incoming packets discarded
      21017131 incoming packets delivered
      25114367 requests sent out
      324 dropped because of missing route
  Icmp:
      18088 ICMP messages received
      692 input ICMP message failed.
      ICMP input histogram:
          destination unreachable: 4241
          timeout in transit: 19
          echo requests: 13791
          echo replies: 4
          timestamp request: 33
      13825 ICMP messages sent
      0 ICMP messages failed
      ICMP output histogram:
          destination unreachable: 1
          echo replies: 13791
          timestamp replies: 33
  IcmpMsg:
          InType0: 4
          InType3: 4241
          InType8: 13791
          InType11: 19
          InType13: 33
          OutType0: 13791
          OutType3: 1
          OutType14: 33
  Tcp:
      12210 active connections openings
      208820 passive connection openings
      54198 failed connection attempts
      9805 connection resets received
  ...
  ````

  netstat 请参考这篇文章: [Linux netstat命令详解](https://www.cnblogs.com/ftl1012/p/netstat.html)

- 查看所有进程

  ````sh
  [root@pdai.tech ~]# ps -ef | grep java
  root      1249     1  0 Nov04 ?        00:58:05 java -jar /opt/tech_doc/bin/tech_arch-0.0.1-RELEASE.jar --server.port=9999
  root     32718 32518  0 08:36 pts/0    00:00:00 grep --color=auto java
  ````

- top

  top除了看一些基本信息之外，剩下的就是配合来查询vm的各种问题了

  ````sh
  # top -H -p pid
  top - 08:37:51 up 45 days, 18:45,  1 user,  load average: 0.01, 0.03, 0.05
  Threads:  28 total,   0 running,  28 sleeping,   0 stopped,   0 zombie
  %Cpu(s):  0.7 us,  0.7 sy,  0.0 ni, 98.6 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
  KiB Mem :  1882088 total,    74608 free,   202228 used,  1605252 buff/cache
  KiB Swap:  2097148 total,  1835392 free,   261756 used.  1502036 avail Mem
  
    PID USER      PR  NI    VIRT    RES    SHR S %CPU %MEM     TIME+ COMMAND
   1347 root      20   0 2553808 113752   1024 S  0.3  6.0  48:46.74 VM Periodic Tas
   1249 root      20   0 2553808 113752   1024 S  0.0  6.0   0:00.00 java
   1289 root      20   0 2553808 113752   1024 S  0.0  6.0   0:03.74 java
  ...
  ````

#### 查看磁盘和内存相关

- 查看内存使用 - free -m

  ````sh
  [root@pdai.tech ~]# free -m
                total        used        free      shared  buff/cache   available
  Mem:           1837         196         824           0         816        1469
  Swap:          2047         255        1792
  ````

- 查看各分区使用情况

  ````sh
  [root@pdai.tech ~]# df -h
  Filesystem      Size  Used Avail Use% Mounted on
  devtmpfs        909M     0  909M   0% /dev
  tmpfs           919M     0  919M   0% /dev/shm
  tmpfs           919M  452K  919M   1% /run
  tmpfs           919M     0  919M   0% /sys/fs/cgroup
  /dev/vda1        40G   15G   23G  40% /
  tmpfs           184M     0  184M   0% /run/user/0
  ````

- 查看指定目录的大小

  ````sh
  [root@pdai.tech ~]# du -sh
  803M
  ````

- 查看内存总量

  ````sh
  [root@pdai.tech ~]# grep MemTotal /proc/meminfo
  MemTotal:        1882088 kB
  ````

- 查看空闲内存量

  ````sh
  [root@pdai.tech ~]# grep MemFree /proc/meminfo
  MemFree:           74120 kB
  ````

- 查看系统负载磁盘和分区

  ````sh
  [root@pdai.tech ~]# grep MemFree /proc/meminfo
  MemFree:           74120 kB
  ````

- 查看系统负载磁盘和分区

  ````sh
  [root@pdai.tech ~]# cat /proc/loadavg
  0.01 0.04 0.05 2/174 32751
  ````

- 查看挂接的分区状态

  ````sh
  [root@pdai.tech ~]# mount | column -t
  sysfs       on  /sys                             type  sysfs       (rw,nosuid,nodev,noexec,relatime)
  proc        on  /proc                            type  proc        (rw,nosuid,nodev,noexec,relatime)
  devtmpfs    on  /dev                             type  devtmpfs    (rw,nosuid,size=930732k,nr_inodes=232683,mode=755)
  securityfs  on  /sys/kernel/security             type  securityfs  (rw,nosuid,nodev,noexec,relatime)
  ...
  ````

- 查看所有分区

  ````sh
  [root@pdai.tech ~]# fdisk -l
  
  Disk /dev/vda: 42.9 GB, 42949672960 bytes, 83886080 sectors
  Units = sectors of 1 * 512 = 512 bytes
  Sector size (logical/physical): 512 bytes / 512 bytes
  I/O size (minimum/optimal): 512 bytes / 512 bytes
  Disk label type: dos
  Disk identifier: 0x0008d73a
  
     Device Boot      Start         End      Blocks   Id  System
  /dev/vda1   *        2048    83884031    41940992   83  Linux
  ````

- 查看所有交换分区

  ````sh
  [root@pdai.tech ~]# swapon -s
  Filename                                Type            Size    Used    Priority
  /etc/swap                               file    2097148 261756  -2
  ````

- 查看硬盘大小

  ````sh
  [root@pdai.tech ~]# fdisk -l |grep Disk
  Disk /dev/vda: 42.9 GB, 42949672960 bytes, 83886080 sectors
  Disk label type: dos
  Disk identifier: 0x0008d73a
  ````

#### 查看用户和组相关

- 查看活动用户

  ````sh
  [root@pdai.tech ~]# w
   08:47:20 up 45 days, 18:54,  1 user,  load average: 0.01, 0.03, 0.05
  USER     TTY      FROM             LOGIN@   IDLE   JCPU   PCPU WHAT
  root     pts/0    xxx.194.1.200    08:32    0.00s  0.32s  0.32s -bash
  ````

- 查看指定用户信息

  ````sh
  [root@pdai.tech ~]# id
  uid=0(root) gid=0(root) groups=0(root)
  ````

- 查看用户登录日志

  ````sh
  [root@pdai.tech ~]# last
  root     pts/0        xxx.194.1.200    Fri Dec 20 08:32   still logged in
  root     pts/0        xxx.73.164.60     Thu Dec 19 21:47 - 00:28  (02:41)
  ...
  ````

- 查看系统所有用户

  ````sh
  [root@pdai.tech ~]# cut -d: -f1 /etc/passwd
  root
  ...
  ````

- 查看系统所有组

  ````sh
  cut -d: -f1 /etc/group
  ````

- 查看服务，模块和包相关

  ````sh
  # 查看当前用户的计划任务服务
  crontab -l 
  
  # 列出所有系统服务
  chkconfig –list 
  
  # 列出所有启动的系统服务程序
  chkconfig –list | grep on 
  
  # 查看所有安装的软件包
  rpm -qa 
  
  # 列出加载的内核模块
  lsmod 
  ````

- 查看系统，设备，环境信息

  ````sh
  # 常用
  env # 查看环境变量资源
  uptime # 查看系统运行时间、用户数、负载
  lsusb -tv # 列出所有USB设备的linux系统信息命令
  lspci -tv # 列出所有PCI设备
  head -n 1 /etc/issue # 查看操作系统版本，是数字1不是字母L
  uname -a # 查看内核/操作系统/CPU信息的linux系统信息命令
  
  # /proc/
  cat /proc/cpuinfo ：查看CPU相关参数的linux系统命令
  cat /proc/partitions ：查看linux硬盘和分区信息的系统信息命令
  cat /proc/meminfo ：查看linux系统内存信息的linux系统命令
  cat /proc/version ：查看版本，类似uname -r
  cat /proc/ioports ：查看设备io端口
  cat /proc/interrupts ：查看中断
  cat /proc/pci ：查看pci设备的信息
  cat /proc/swaps ：查看所有swap分区的信息
  cat /proc/cpuinfo |grep "model name" && cat /proc/cpuinfo |grep "physical id"
  ````

#### tsar

tsar是淘宝开源的的采集工具。很好用, 将历史收集到的数据持久化在磁盘上，所以我们快速来查询历史的系统数据。当然实时的应用情况也是可以查询的啦。

````sh
tsar  ##可以查看最近一天的各项指标
tsar --live ##可以查看实时指标，默认五秒一刷
tsar -d 20161218 ##指定查看某天的数据，貌似最多只能看四个月的数据
tsar --mem
tsar --load
tsar --cpu ##当然这个也可以和-d参数配合来查询某天的单个指标的情况 
````

### 调试排错 - Java 问题排查：工具单

> Java 在线问题排查主要分两篇：本文是第二篇，通过java调试/排查工具进行问题定位。

#### Java 调试入门工具

- jps

  > jps是jdk提供的一个查看当前java进程的小工具， 可以看做是JavaVirtual Machine Process Status Tool的缩写。

  jps常用命令

  ````sh
  jps # 显示进程的ID 和 类的名称
  jps –l # 输出输出完全的包名，应用主类名，jar的完全路径名 
  jps –v # 输出jvm参数
  jps –q # 显示java进程号
  jps -m # main 方法
  jps -l xxx.xxx.xx.xx # 远程查看 
  ````

  jps参数

  ````sh
  -q：仅输出VM标识符，不包括classname,jar name,arguments in main method 
  -m：输出main method的参数 
  -l：输出完全的包名，应用主类名，jar的完全路径名 
  -v：输出jvm参数 
  -V：输出通过flag文件传递到JVM中的参数(.hotspotrc文件或-XX:Flags=所指定的文件 
  -Joption：传递参数到vm,例如:-J-Xms512m
  ````

  > java程序在启动以后，会在java.io.tmpdir指定的目录下，就是临时文件夹里，生成一个类似于hsperfdata_User的文件夹，这个文件夹里（在Linux中为/tmp/hsperfdata_{userName}/），有几个文件，名字就是java进程的pid，因此列出当前运行的java进程，只是把这个目录里的文件名列一下而已。 至于系统的参数什么，就可以解析这几个文件获得。

  更多请参考 [jps - Java Virtual Machine Process Status Tool](https://docs.oracle.com/javase/1.5.0/docs/tooldocs/share/jps.html)

- jstack

  > jstack是jdk自带的线程堆栈分析工具，使用该命令可以查看或导出 Java 应用程序中线程堆栈信息。

  jstack常用命令:

  ````sh
  # 基本
  jstack 2815
  
  # java和native c/c++框架的所有栈信息
  jstack -m 2815
  
  # 额外的锁信息列表，查看是否死锁
  jstack -l 2815
  ````

  jstack参数：

  ````sh
  -l 长列表. 打印关于锁的附加信息,例如属于java.util.concurrent 的 ownable synchronizers列表.
  
  -F 当’jstack [-l] pid’没有相应的时候强制打印栈信息
  
  -m 打印java和native c/c++框架的所有栈信息.
  
  -h | -help 打印帮助信息
  ````

  更多请参考: [jvm 性能调优工具之 jstack](https://www.jianshu.com/p/025cb069cb69)

- jinfo

  > jinfo 是 JDK 自带的命令，可以用来查看正在运行的 java 应用程序的扩展参数，包括Java System属性和JVM命令行参数；也可以动态的修改正在运行的 JVM 一些参数。当系统崩溃时，jinfo可以从core文件里面知道崩溃的Java应用程序的配置信息

  ````sh
  # 输出当前 jvm 进程的全部参数和系统属性
  jinfo 2815
  
  # 输出所有的参数
  jinfo -flags 2815
  
  # 查看指定的 jvm 参数的值
  jinfo -flag PrintGC 2815
  
  # 开启/关闭指定的JVM参数
  jinfo -flag +PrintGC 2815
  
  # 设置flag的参数
  jinfo -flag name=value 2815
  
  # 输出当前 jvm 进行的全部的系统属性
  jinfo -sysprops 2815
  ````

  jinfo参数：

  ````sh
  no option 输出全部的参数和系统属性
  -flag name 输出对应名称的参数
  -flag [+|-]name 开启或者关闭对应名称的参数
  -flag name=value 设定对应名称的参数
  -flags 输出全部的参数
  -sysprops 输出系统属性
  ````

  更多请参考：[jvm 性能调优工具之 jinfo](https://www.jianshu.com/p/8d8aef212b25)

- jmap

  > 命令jmap是一个多功能的命令。它可以生成 java 程序的 dump 文件， 也可以查看堆内对象示例的统计信息、查看 ClassLoader 的信息以及 finalizer 队列。

  两个用途

  ````sh
  # 查看堆的情况
  jmap -heap 2815
  
  # dump
  jmap -dump:live,format=b,file=/tmp/heap2.bin 2815
  jmap -dump:format=b,file=/tmp/heap3.bin 2815
  
  # 查看堆的占用
  jmap -histo 2815 | head -10
  ````

  jmap参数

  ````sh
  no option： 查看进程的内存映像信息,类似 Solaris pmap 命令。
  -heap： 显示Java堆详细信息
  -histo[:live]： 显示堆中对象的统计信息
  -clstats：打印类加载器信息
  -finalizerinfo： 显示在F-Queue队列等待Finalizer线程执行finalizer方法的对象
  -dump:<dump-options>：生成堆转储快照
  -F： 当-dump没有响应时，使用-dump或者-histo参数. 在这个模式下,live子参数无效.
  -help：打印帮助信息
  -J<flag>：指定传递给运行jmap的JVM的参数
  ````

  更多请参考：[jvm 性能调优工具之 jmap (opens new window)](https://www.jianshu.com/p/a4ad53179df3)和 [jmap - Memory Map](https://docs.oracle.com/javase/1.5.0/docs/tooldocs/share/jmap.html)

- jstat

  > Jstat是JDK自带的一个轻量级小工具。主要利用JVM内建的指令对Java应用程序的资源和性能进行实时的命令行的监控，包括了对Heap size和垃圾回收状况的监控。

  jstat参数众多，但是使用一个就够了（-gcutil GC统计汇总）

  ````sh
  jstat -gcutil 2815 1000 
  ````

- jdb

  > jdb可以用来预发debug,假设你预发的java_home是/opt/java/，远程调试端口是8000.那么

  ````sh
  jdb -attach 8000
  ````

  出现以上代表jdb启动成功。后续可以进行设置断点进行调试。

  具体参数可见oracle官方说明[jdb - The Java Debugger](http://docs.oracle.com/javase/7/docs/technotes/tools/windows/jdb.html)

- CHLSDB

  CHLSDB感觉很多情况下可以看到更好玩的东西，不详细叙述了。 查询资料听说jstack和jmap等工具就是基于它的。

  ````sh
  java -classpath /opt/taobao/java/lib/sa-jdi.jar sun.jvm.hotspot.CLHSDB
  ````

#### Java 调试进阶工具

- btrace

  首当其冲的要说的是btrace。真是生产环境&预发的排查问题大杀器。 简介什么的就不说了。直接上代码干

  - 查看当前谁调用了ArrayList的add方法，同时只打印当前ArrayList的size大于500的线程调用栈

    ````java
    @OnMethod(clazz = "java.util.ArrayList", method="add", location = @Location(value = Kind.CALL, clazz = "/./", method = "/./"))
    public static void m(@ProbeClassName String probeClass, @ProbeMethodName String probeMethod, @TargetInstance Object instance, @TargetMethodOrField String method) {
    
        if(getInt(field("java.util.ArrayList", "size"), instance) > 479){
            println("check who ArrayList.add method:" + probeClass + "#" + probeMethod  + ", method:" + method + ", size:" + getInt(field("java.util.ArrayList", "size"), instance));
            jstack();
            println();
            println("===========================");
            println();
        }
    }
    ````

  - 监控当前服务方法被调用时返回的值以及请求的参数

    ````java
    @OnMethod(clazz = "com.taobao.sellerhome.transfer.biz.impl.C2CApplyerServiceImpl", method="nav", location = @Location(value = Kind.RETURN))
    public static void mt(long userId, int current, int relation, String check, String redirectUrl, @Return AnyType result) {
    
        println("parameter# userId:" + userId + ", current:" + current + ", relation:" + relation + ", check:" + check + ", redirectUrl:" + redirectUrl + ", result:" + result);
    }
    ````

    btrace 具体可以参考这里：https://github.com/btraceio/btrace

    注意:

    - 经过观察，1.3.9的release输出不稳定，要多触发几次才能看到正确的结果
    - 正则表达式匹配trace类时范围一定要控制，否则极有可能出现跑满CPU导致应用卡死的情况
    - 由于是字节码注入的原理，想要应用恢复到正常情况，需要重启应用。

- Greys

  Greys是@杜琨的大作吧。说几个挺棒的功能(部分功能和btrace重合):

  - `sc -df xxx`: 输出当前类的详情,包括源码位置和classloader结构
  - `trace class method`: 打印出当前方法调用的耗时情况，细分到每个方法, 对排查方法性能时很有帮助。

- Arthas

  > Arthas是基于Greys。

  具体请参考：[调试排错 - Java应用在线调试Arthas](https://www.pdai.tech/md/java/jvm/java-jvm-agent-arthas.html)

- javOSize

  就说一个功能:

  - `classes`：通过修改了字节码，改变了类的内容，即时生效。 所以可以做到快速的在某个地方打个日志看看输出，缺点是对代码的侵入性太大。但是如果自己知道自己在干嘛，的确是不错的玩意儿。

  其他功能Greys和btrace都能很轻易做的到，不说了。

  更多请参考：[官网](http://www.javosize.com/)

- JProfiler

  之前判断许多问题要通过JProfiler，但是现在Greys和btrace基本都能搞定了。再加上出问题的基本上都是生产环境(网络隔离)，所以基本不怎么使用了，但是还是要标记一下。

  更多请参考：[官网](https://www.ej-technologies.com/products/jprofiler/overview.html)

#### 其它工具

- dmesg

  如果发现自己的java进程悄无声息的消失了，几乎没有留下任何线索，那么dmesg一发，很有可能有你想要的。

  sudo dmesg|grep -i kill|less 去找关键字oom_killer。找到的结果类似如下:

  ````sh
  [6710782.021013] java invoked oom-killer: gfp_mask=0xd0, order=0, oom_adj=0, oom_scoe_adj=0
  [6710782.070639] [<ffffffff81118898>] ? oom_kill_process+0x68/0x140 
  [6710782.257588] Task in /LXC011175068174 killed as a result of limit of /LXC011175068174 
  [6710784.698347] Memory cgroup out of memory: Kill process 215701 (java) score 854 or sacrifice child 
  [6710784.707978] Killed process 215701, UID 679, (java) total-vm:11017300kB, anon-rss:7152432kB, file-rss:1232kB
  ````

  以上表明，对应的java进程被系统的OOM Killer给干掉了，得分为854. 解释一下OOM killer（Out-Of-Memory killer），该机制会监控机器的内存资源消耗。当机器内存耗尽前，该机制会扫描所有的进程（按照一定规则计算，内存占用，时间等），挑选出得分最高的进程，然后杀死，从而保护机器。

  dmesg日志时间转换公式: log实际时间=格林威治1970-01-01+(当前时间秒数-系统启动至今的秒数+dmesg打印的log时间)秒数：

  date -d "1970-01-01 UTC `echo "$(date +%s)-$(cat /proc/uptime|cut -f 1 -d' ')+12288812.926194"|bc` seconds" 剩下的，就是看看为什么内存这么大，触发了OOM-Killer了。

### 调试排错 - 9种常见的CMS GC问题分析与解决

略

### 调试排错 - Java 动态调试技术原理



### 调试排错 - Java 应用在线调试 Arthas



### 调试排错 - 使用IDEA本地调试和远程调试

#### Debug开篇

如下是在IDEA中启动Debug模式，进入断点后的界面，我这里是Windows，可能和Mac的图标等会有些不一样。就简单说下图中标注的8个地方：

- ⑤ 服务按钮：可以在这里关闭/启动服务，设置断点等。

- ⑧ Watches：查看变量，可以将Variables区中的变量拖到Watches中查看

  ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/java/java-debug-idea-1.png)

#### 智能步入

> 想想，一行代码里有好几个方法，怎么只选择某一个方法进入。之前提到过使用Step Into (Alt + F7) 或者 Force Step Into (Alt + Shift + F7)进入到方法内部，但这两个操作会根据方法调用顺序依次进入，这比较麻烦。

那么智能步入就很方便了，智能步入，这个功能在Run里可以看到，Smart Step Into (Shift + F7)，如下图

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/java/java-debug-idea-19.png)

按Shift + F7，会自动定位到当前断点行，并列出需要进入的方法，如图5.2，点击方法进入方法内部。

如果只有一个方法，则直接进入，类似Force Step Into。

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/java/java-debug-idea-20.png)

#### 断点条件

> > 通过设置断点条件，在满足条件时，才停在断点处，否则直接运行。

- 在断点上右键直接**设置当前断点的条件**，如下图设置exist为true时断点才生效。

  ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/java/java-debug-idea-21.png)

- **点击View Breakpoints (Ctrl + Shift + F8)，查看所有断点**。

  - Java Line Breakpoints 显示了所有的断点，在右边勾选Condition，设置断点的条件。

  - 勾选Log message to console，则会将当前断点行输出到控制台，如图6.3

  - 勾选Evaluate and log，可以在执行这行代码是计算表达式的值，并将结果输出到控制台。

    ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/java/java-debug-idea-22.png)

#### 多线程调试

> 一般情况下我们调试的时候是在一个线程中的，一步一步往下走。但有时候你会发现在Debug的时候，想发起另外一个请求都无法进行了？

那是因为IDEA在Debug时默认阻塞级别是ALL，会阻塞其它线程，只有在当前调试线程走完时才会走其它线程。可以在View Breakpoints里选择Thread，如图7.1，然后点击Make Default设置为默认选项。

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/java/java-debug-idea-30.png)

切换线程，在下图中Frames的下拉列表里，可以切换当前的线程，如下我这里有两个Debug的线程，切换另外一个则进入另一个Debug的线程。

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/java/java-debug-idea-31.png)

#### 断点回退

> 在调试的时候，想要重新走一下流程而不用再次发起一个请求？

- 首先认识下这个**方法调用栈**，如图首先请求进入DemoController的insertDemo方法，然后调用insert方法，其它的invoke我们且先不管，最上面的方法是当前断点所在的方法。

  ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/java/java-debug-idea-32.png)

- 断点回退

  所谓的断点回退，其实就是回退到上一个方法调用的开始处，在IDEA里测试无法一行一行地回退或回到到上一个断点处，而是回到上一个方法。

  回退的方式有两种，一种是Drop Frame按钮，按调用的方法逐步回退，包括三方类库的其它方法

  ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/java/java-debug-idea-33.png)

  取消Show All Frames按钮会显示三方类库的方法

  ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/java/java-debug-idea-34.png)

第二种方式，在调用栈方法上选择要回退的方法，右键选择Drop Frame，回退到该方法的上一个方法调用处，此时再按F9(Resume Program)，可以看到程序进入到该方法的断点处了。

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/java/java-debug-idea-35.png)

但有一点需要注意，断点回退只能重新走一下流程，之前的某些参数/数据的状态已经改变了的是无法回退到之前的状态的，如对象、集合、更新了数据库数据等等。

#### 中断Debug

我也没发现可以直接中断请求的方式(除了关闭服务)，但可以通过Force Return，即强制返回来避免后续的流程，如图

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/java/java-debug-idea-36.png)

#### 远程调试

- 使用特定JVM参数运行服务端代码

  要让远程服务器运行的代码支持远程调试，则启动的时候必须加上特定的JVM参数，这些参数是：

  ````sh
  -Xdebug -Xrunjdwp:transport=dt_socket,suspend=n,server=y,address=${debug_port}
  ````

  其中的`${debug_port}`是用户自定义的，为debug端口，本例以5555端口为例。

  本人在这里踩过一个坑，必须要说一下。在使用公司内部的自动化部署平台NDP进行应用部署时，该平台号称支持远程调试，只需要在某个配置页面配置一下调试端口号（没有填写任何IP相关的信息），并且重新发布一下应用即可。事实上也可以发现，上述JVM参数中唯一可变的就是${debug_port}。但是实际在本地连接时发现却始终连不上5555 的调试端口，仔细排查才发现，下面截取了NDP发布的应用所有JVM参数列表中与远程调试相关的JVM启动参数如下：

  ```bash
  -Xdebug -Xrunjdwp:transport=dt_socket,suspend=n,server=y,address=127.0.0.1:5555
  ```

  将address设置为127.0.0.1:5555，表示将调试端口限制为本地访问，远程无法访问，这个应该是NDP平台的一个bug，我们在自己设置JVM的启动参数时也需要格外注意。

  如果只是临时调试，在端口号前面不要加上限制访问的IP地址，调试完成之后，将上述JVM参数去除掉之后重新发布下，防范开放远程调试端口可能带来的安全风险。

- 本地连接远程服务器debug端口

  打开Intellij IDEA，在顶部靠右的地方选择”Edit Configurations…”，进去之后点击+号，选择”Remote”，按照下图的只是填写红框内的内容，其中Name填写名称，这里为remote webserver，host为远程代码运行的机器的ip/hostname，port为上一步指定的debug_port，本例是5555。然后点击Apply，最后点击OK即可

  ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/java/java-debug-idea-41.png)

  现在在上一步选择”Edit Configurations…”的下拉框的位置选择上一步创建的remote webserver，然后点击右边的debug按钮(长的像臭虫那个)，看控制台日志，如果出现类似“Connected to the target VM, address: ‘xx.xx.xx.xx:5555’, transport: ‘socket’”的字样，就表示连接成功过了。我这里实际显示的内容如下：

  ````sh
  Connected to the target VM, address: '10.185.0.192:15555', transport: 'socket'
  ````

- 设置断点，开始调试

  远程debug模式已经开启，现在可以在需要调试的代码中打断点了，比如：

  ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/java/java-debug-idea-42.png)

  如图中所示，如果断点内有√，则表示选取的断点正确。

  现在在本地发送一个到远程服务器的请求，看本地控制台的bug界面，划到debugger这个标签，可以看到当前远程服务的内部状态（各种变量）已经全部显示出来了，并且在刚才设置了断点的地方，也显示了该行的变量值。

  ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/java/java-debug-idea-43.png)
