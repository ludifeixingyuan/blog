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

  - 每个线程都有自己的栈，栈中的数据都是以**栈帧（Stack Frame）的格式存在**
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
  
    著作权归https://pdai.tech所有。 链接：https://www.pdai.tech/md/java/jvm/java-jvm-struct.html
  
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
    
    **逃逸分析(Escape Analysis)是目前 Java 虚拟机中比较前沿的优化技术**。这是一种可以有效减少 Java 程序中同步负载和内存堆分配压力的跨函数全局数据流分析算法**。通过逃逸分析，Java Hotspot 编译器能够分析出一个新的对象的引用的使用范围从而决定是否要将这个对象分配到堆上。
    
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

### JVM 基础 - Java 内存模型引入

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















