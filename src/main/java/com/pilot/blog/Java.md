# Java

## Java 基础

## Java 集合框架

### ArrayList

底层数据结构：Object[]

自动扩容：ensureCapacity容量检查，最终由grow扩容（数组进行扩容时，会将老数组中的元素重新拷贝一份到新的数组中）、1.5倍（int newCapacity = oldCapacity + (oldCapacity >> 1)）
add(int index,E e)  需要先对元素移动 然后插入，线性时间复杂度
remove(E e) 是 add(E e)的逆向，需要将删除点之后的元素向前移动一个位置，需要注意的是为了让GC起作用，必须显式的为最后一个位置赋null值。

### LinkedList

底层数据结构：双向链表 Node<E> first、Node<E> last

add() 两个版本：add(E e) 添加在末尾、add(Index index,E e)添加在制定位置，需先找到具体位置。
双向链表查找时，会先判断index靠近前端还是后端，再朝哪个方向开始查找

> ArrayList和LinkedList都实现了List接口，有以下的不同点：
> 1.ArrayList是基于索引的数据接口，它的底层是数组，他可以以0（1）时间复杂度对元素进行随机访问。与此对应，LinkedList
> 是以元素列表的形式存储它的数据，每一个元素都和它的前一个和后一个元素链接在一起，在这种情况下，查找某个元素的时间复杂杜是0（n）。
> 2.相对于ArrayList，LinkedList的插入，添加，删除操作速度更快，因为当元素被添加到集合任意位置的时候，不需要像数组那样重新计算大小或者更新索引。
> 3.LinkedList比ArrayList更占内存，因为linkedlist位每一个节点存储了两个引用，一个指向前一个元素，一个指向下一个元素。

### HashMap

buckets+entries

##### Java7HashMap

数据结构:  数组+链表

put: put(key,value) 首先会对map做一次查找，包含该元组则直接返回，查找过程类似getEntry()，如果没找到，则会通过addEntry(int hash,K key,V value, int bucketIndex)方法插入新的entry，插入方式为**头插法**。

remove(): remove(Object key)的作用是删除key值对应的entry，该方法的具体逻辑是在removeEntryForKey(Object key)里实现的。removeEntryForKey()方法会首先找到key值对应的entry，然后删除该entry(修改链表的相应引用)。查找过程跟getEntry()过程类似。

##### Java8HashMap

Node(int hash, K key, V value, Node<K,V> next)

**数组+链表+红黑树** 组成。
根据 Java7 HashMap 的介绍，我们知道，查找的时候，根据 hash 值我们能够快速定位到数组的具体下标，但是之后的话，需要顺着链表一个个比较下去才能找到我们需要的，时间复杂度取决于链表的长度，为 O(n)。
为了降低这部分的开销，在 Java8 中，当链表中的元素达到了 8 个时，会将链表转换为红黑树，在这些位置进行查找的时候可以降低时间复杂度为 O(logN)。
当链表中的元素达到了 8 个时，会将链表转换为红黑树，在这些位置进行查找的时候可以降低时间复杂度为 O(logN)。

##### put 过程分析

第一次put时，触发resize() 初始化长度。找到具体的数组下标，如果此位置没有值，那么直接出实话一下Node并防止再这个位置。如果该位置有数据，a：判断该位置的第一个数据和我们要插入的数据，key是不是想等，如果是 取出这个节点。b：如果该节点代表的是红黑树的节点(p instanceof TreeNode)，调用红黑树的插值方法。c：如果是链表，插入到链表的最后面(Java7 是插入到链表的最前面)，插入后如果链表长度为8，触发treeifyBin将链表转化为红黑树。

##### 数组扩容

不管是add时的扩容、new HashMap(int initialCapacity)、还是new HashMap()初始化 都是用的resize() 里面有判断是哪种
resize() 方法用于初始化数组或数组扩容，每次扩容后，容量为原来的 2 倍，并进行数据迁移。

##### get过程分析

计算 key 的 hash 值，根据 hash 值找到对应数组下标: hash & (length-1)，再判断目的地是链表还是红黑树，是链表就开始遍历，是红黑树就用红黑树的方法取数据。

### LinkedHashSet&Map

LinkedHashMap看作采用linked list增强的HashMap。
事实上LinkedHashMap是HashMap的直接子类，二者唯一的区别是LinkedHashMap在HashMap的基础上，采用双向链表(doubly-linked list)的形式将所有entry连接起来，这样是为保证元素的迭代顺序跟插入顺序相同。

## Java 多线程与并发

## Java IO/NIO/AIO

## Java8 特性详解

### Java8-lambda表达式

#### stream & parallelStream

每个Stream都有两种模式: 顺序执行和并行执行（.parallel()）

#### stream中常用的方法

stream(), parallelStream()、
distinct , limit , count , min, max, 
filter() , findAny() , findFirst() , sort , forEach , void map(), 
reduce() 可以将所有值合并成一个 .reduce((sum, cost) -> sum + cost).get();
flatMap() - 将多个Stream连接成一个Stream collect(Collectors.toList()) 
summaryStatistics 获取数字的个数、最小值、最大值、总和以及平均值
peek() 可以只包含一个空方法题，无放回值，可简单打印逻辑

### Java8-optional

isPresent、ifPresent（ifPresent方法接受lambda表达式参数）、map（map方法通过传入的lambda表达式修改Optonal实例默认值）

````java
//ifPresent方法接受lambda表达式作为参数。
//lambda表达式对Optional的值调用consumer进行处理。
name.ifPresent((value) -> {
  System.out.println("The length of the value is: " + value.length());
});
````

### Java8-默认方法

简单说，就是接口可以有实现方法，而且不需要实现类去实现其方法。只需在方法名前面加个default关键字即可。

### Java8-类型注解

在java 8之前，注解只能是在声明的地方所使用，比如类，方法，属性；java 8里面，注解可以应用在任何地方，比如:

创建类实例

```java
new @Interned MyObject();
```

类型映射

```java
myString = (@NonNull String) str;
```

implements 语句中

```java
class UnmodifiableList<T> implements @Readonly List<@Readonly T> { … }
```

throw exception声明

```java
void monitorTemperature() throws @Critical TemperatureException { … }
```

需要注意的是，**类型注解只是语法而不是语义，并不会影响java的编译时间，加载时间，以及运行时间，也就是说，编译成class文件的时候并不包含类型注解**。

类型注解作用：用来支持在Java的程序中做强类型检查。配合插件式的check framework，可以在编译的时候检测出runtime error，以提高代码质量。这就是类型注解的作用了。

### Java8-重复注解

允许在同一申明类型(类，属性，或方法)的多次使用同一个注解。

java8以前是用另一个注解去包含要重复的注解实现的，可读性不好。

### Java8-类型推断优化

Java7之前：

Map<String, String> myMap = new HashMap<String, String>();

Java7: 

Map<String, String> myMap = new HashMap<>(); //注意后面的"<>"

java8里面泛型的目标类型推断主要2个：

1.支持通过方法上下文推断泛型目标类型

2.支持在方法调用链路当中，泛型类型推断传递到最后一个方法

根据JEP101的特性，我们在调用上面方法的时候可以这样写

```java
//通过方法赋值的目标参数来自动推断泛型的类型
List<String> l = List.nil();
//而不是显示的指定类型
//List<String> l = List.<String>nil();
//通过前面方法参数类型推断泛型的类型
List.cons(42, List.nil());
//而不是显示的指定类型
//List.cons(42, List.<Integer>nil());
```

### Java8-JRE精简

更小的java环境、更好的优化性能和启动时间、消除未使用代码、打包速度加快

### Java8-移除Permgen

> PermGen space
>
> PermGen space的全称是Permanent Generation space,是指内存的永久保存区域，说说为什么会内存益出: 这一部分用于存放Class和Meta的信息,Class在被 Load的时候被放入PermGen space区域，它和和存放Instance的Heap区域不同,所以如果你的APP会LOAD很多CLASS的话,就很可能出现PermGen space错误。

JDK8 HotSpot JVM 将移除永久区，使用本地内存来存储类元数据信息并称之为: 元空间(Metaspace)

java8中metaspace总结如下:
1.PermGen，这部分内存空间将全部移除。
2.Metaspace 内存分配模型，大部分类元数据都在本地内存中分配。
3.Metaspace 容量，默认情况下，类元数据只受可用的本地内存限制
新参数(MaxMetaspaceSize)用于限制本地内存分配给类元数据的大小。如果没有指定这个参数，元空间会在运行时根据需要动态调整。
4.Metaspace 垃圾回收，对于僵死的类及类加载器的垃圾回收将在元数据使用达到“MaxMetaspaceSize”参数的设定值时进行。
5.Java 堆内存的影响，一些杂项数据已经移到Java堆空间中。升级到JDK8之后，会发现Java堆 空间有所增长。
6.Metaspace 监控，元空间的使用情况可以从HotSpot1.8的详细GC日志输出中得到。

### Java8-StampedLock

#### synchronized

在java5之前，实现同步主要是使用synchronized。

```java
synchronized(this)
	// do operation
}
```
#### Lock

```java
rwlock.writeLock().lock();
try {
	// do operation
} finally {
	rwlock.writeLock().unlock();
}
```
Lock是一个接口，核心方法是lock()，unlock()，tryLock()，实现类有ReentrantLock, ReentrantReadWriteLock.ReadLock, ReentrantReadWriteLock.WriteLock；

#### 总结

- synchronized是在JVM层面上实现的，不但可以通过一些监控工具监控synchronized的锁定，而且在代码执行时出现异常，JVM会自动释放锁定；

- ReentrantLock、ReentrantReadWriteLock,、StampedLock都是对象层面的锁定，要保证锁定一定会被释放，就必须将unLock()放到finally{}中；

  StampedLock 对吞吐量有巨大的改进，特别是在读线程越来越多的场景下

  StampedLock 可以说是Lock的一个很好的补充，吞吐量以及性能上的提升足以打动很多人了，但并不是说要替代之前Lock的东西，毕竟他还是有些应用场景的，起码API比StampedLock容易入手。

### Java8-LocalDate/LocalDateTime

> Java8之前的Date有哪些槽点?
>
> 槽点1：最开始的时候既要承载日期信息、又要做日期之间转化，还要做不同日期格式显示，指责繁杂。后来JDK1.1后，这3个职责分开了：使用Calendar类实现日期和时间字段之间转换；使用DateFormat类来格式化和分析日期字符串；而Date只用来承载日期和时间信息。
>
> 槽点2：坑爹的year和month
>
> Date date = new Date(2012,1,1);
> System.out.println(date);
> 输出Thu Feb 01 00:00:00 CST 3912
>
> 观察输出结果，year是2012+1900，而month，月份参数我不是给了1吗? 怎么输出二月(Feb)了?
>
> 应该曾有人告诉你，如果你要设置日期，应该使用 java.util.Calendar，像这样…
>
> Calendar calendar = Calendar.getInstance();
> calendar.set(2013, 8, 2);
>
> 这样写又不对了，calendar的month也是从0开始的，表达8月份应该用7这个数字，要么就干脆用枚举
>
> calendar.set(2013, Calendar.AUGUST, 2);
>
> 注意上面的代码，Calendar年份的传值不需要减去1900(当然月份的定义和Date还是一样)，这种不一致真是让人抓狂！
>
> 槽点3：java.util.Date与java.util.Calendar中的所有属性都是可变的，对Calendar对象进行加减影响到原内存中对象。Localdate则避免了这个问题。
>
> 槽点4：SimpleDateTimeFormat是非线程安全的。

Java8时间和日期

LocalDate日期、LocalTime时间、LocalDateTime日期时间

该包的API提供了大量相关的方法，这些方法一般有一致的方法前缀:

- of: 静态工厂方法。
- parse: 静态工厂方法，关注于解析。
- get: 获取某些东西的值。
- is: 检查某些东西的是否是true。
- with: 不可变的setter等价物。
- plus: 加一些量到某个对象。
- minus: 从某个对象减去一些量。
- to: 转换到另一个类型。
- at: 把这个对象与另一个对象组合起来，例如:  date.atTime(time)。

### Java8-JavaFx 2.0

> JavaFX主要致力于丰富客户端开发，以弥补swing的缺陷，主要提供图形库与media库

### Java8-其他更新：字符串，base64

#### 处理数值

Java8添加了对无符号数的额外支持

#### 处理文件

File工具类新增方法

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



# 算法

数据结构基础
常见排序算法
算法思想
一些领域算法

# 开发

开发之网络协议
开发之常见重构技巧
开发之随手记

# Spring

Spring 基础
Spring Boot 入门
Spring Boot 进阶

# 架构

架构基础
分布式系统
微服务系统与设计
系统设计和架构案例

# 方法论

## 开发原则,流程,协议

### 软件开发中的原则 - SOLID

> 在软件开发中，前人对软件系统的设计和开发总结了一些原则和模式， 不管用什么语言做开发，都将对我们系统设计和开发提供指导意义。

#### 开发原则SOLILD

##### S - 单一职责SRP

**定义**

> 一个对象应该只包含单一的职责，并且该职责被完整地封装在一个类中。(Every object should have a single responsibility, and that responsibility should be entirely encapsulated by the class.)，即又定义有且仅有一个原因使类变更。

**原则分析**
- 一个类(或者大到模块，小到方法)承担的职责越多，它被复用的可能性越小，而且如果一个类承担的职责过多，就相当于将这些职责耦合在一起，当其中一个职责变化时，可能会影响其他职责的运作。
- 类的职责主要包括两个方面: 数据职责和行为职责，数据职责通过其属性来体现，而行为职责通过其方法来体现。
- 单一职责原则是实现高内聚、低耦合的指导方针，在很多代码重构手法中都能找到它的存在，它是最简单但又最难运用的原则，需要设计人员发现类的不同职责并将其分离，而发现类的多重职责需要设计人员具有较强的分析设计能力和相关重构经验。

**优点**

- 降低类的复杂性，类的职责清晰明确。比如数据职责和行为职责清晰明确。
- 提高类的可读性和维护性，
- 变更引起的风险减低，变更是必不可少的，如果接口的单一职责做得好，一个接口修改只对相应的类有影响，对其他接口无影响，这对系统的扩展性、维护性都有非常大的帮助。

> 注意: 单一职责原则提出了一个编写程序的标准，用“职责”或“变化原因”来衡量接口或类设计得是否合理，但是“职责”和“变化原因”都是没有具体标准的，一个类到底要负责那些职责? 这些职责怎么细化? 细化后是否都要有一个接口或类? 这些都需从实际的情况考虑。因项目而异，因环境而异。

**例子**

SpringMVC 中Entity,DAO,Service,Controller, Util等的分离。

##### O - 开放封闭原则OCP

> > Open - ClosedPrinciple ,OCP, 对扩展开放，对修改关闭(设计模式的核心原则)

**定义**

>一个软件实体(如类、模块和函数)应该对扩展开放，对修改关闭.  意思是,在一个系统或者模块中,对于扩展是开放的,对于修改是关闭的,一个好的系统是在不修改源代码的情况下,可以扩展你的功能. 而实现开闭原则的关键就是抽象化.

**原因分析**

- 当软件实体因需求要变化时, 尽量通过扩展已有软件实体，可以提供新的行为，以满足对软件的新的需求，而不是修改已有的代码，使变化中的软件有一定的适应性和灵活性 。已有软件模块，特别是最重要的抽象层模块不能再修改，这使变化中的软件系统有一定的稳定性和延续性。

- 实现开闭原则的关键就是抽象化 :在"开-闭"原则中,不允许修改的是抽象的类或者接口,允许扩展的是具体的实现类,抽象类和接口在"开-闭"原则中扮演着极其重要的角色..即要预知可能变化的需求.又预见所有可能已知的扩展..所以在这里"抽象化"是关键!
- 可变性的封闭原则:找到系统的可变因素,将它封装起来. 这是对"开-闭"原则最好的实现. 不要把你的可变因素放在多个类中,或者散落在程序的各个角落. 你应该将可变的因素,封套起来..并且切忌不要把所用的可变因素封套在一起. 最好的解决办法是,分块封套你的可变因素!避免超大类,超长类,超长方法的出现!!给你的程序增加艺术气息,将程序艺术化是我们的目标!

##### L - 里氏替换原则LSP

> Liskov Substitution Principle ,LSP: 任何基类可以出现的地方,子类也可以出现；这一思想表现为对继承机制的约束规范,只有子类能够替换其基类时,才能够保证系统在运行期内识别子类,这是保证继承复用的基础。

**定义**

所有引用基类(父类)的地方必须能透明地使用其子类的对象。即子类能够必须能够替换基类能够从出现的地方。子类也能在基类 的基础上新增行为。

**原因分析**

- 讲的是基类和子类的关系，只有这种关系存在时，里氏代换原则才存在。正方形是长方形是理解里氏代换原则的经典例子。
- 里氏代换原则可以通俗表述为: 在软件中如果能够使用基类对象，那么一定能够使用其子类对象。把基类都替换成它的子类，程序将不会产生任何错误和异常，反过来则不成立，如果一个软件实体使用的是一个子类的话，那么它不一定能够使用基类。
- 里氏代换原则是实现开闭原则的重要方式之一，由于使用基类对象的地方都可以使用子类对象，因此在程序中尽量使用基类类型来对对象进行定义，而在运行时再确定其子类类型，用子类对象来替换父类对象。

##### I - 接口隔离法则

**定义**

客户端不应该依赖那些它不需要的接口。

另一种定义方法: 一旦一个接口太大，则需要将它分割成一些更细小的接口，使用该接口的客户端仅需知道与之相关的方法即可。 注意，在该定义中的接口指的是所定义的方法。例如外面调用某个类的public方法。这个方法对外就是接口。

**原因分析**

- 接口隔离原则是指使用多个专门的接口，而不使用单一的总接口。每一个接口应该承担一种相对独立的角色，不多不少，不干不该干的事，该干的事都要干。
  - 一个接口就只代表一个角色，每个角色都有它特定的一个接口，此时这个原则可以叫做“角色隔离原则”。
  - 接口仅仅提供客户端需要的行为，即所需的方法，客户端不需要的行为则隐藏起来，应当为客户端提供尽可能小的单独的接口，而不要提供大的总接口。
- 使用接口隔离原则拆分接口时，首先必须满足单一职责原则，将一组相关的操作定义在一个接口中，且在满足高内聚的前提下，接口中的方法越少越好。
- 可以在进行系统设计时采用定制服务的方式，即为不同的客户端提供宽窄不同的接口，只提供用户需要的行为，而隐藏用户不需要的行为。

##### D - 依赖倒置原则DIP

> Dependency-Inversion Principle 要依赖抽象,而不要依赖具体的实现, 具体而言就是高层模块不依赖于底层模块,二者共同依赖于抽象。抽象不依赖于具体,具体依赖于抽象。

**定义**

高层模块不应该依赖低层模块，它们都应该依赖抽象。抽象不应该依赖于细节，细节应该依赖于抽象。简单的说，依赖倒置原则要求客户端依赖于抽象耦合。原则表述:

1)抽象不应当依赖于细节；细节应当依赖于抽象；

2)要针对接口编程，不针对实现编程。

**原则分析**

- 如果说开闭原则是面向对象设计的目标,依赖倒转原则是到达面向设计"开闭"原则的手段..如果要达到最好的"开闭"原则,就要尽量的遵守依赖倒转原则. 可以说依赖倒转原则是对"抽象化"的最好规范! 我个人感觉,依赖倒转原则也是里氏代换原则的补充..你理解了里氏代换原则,再来理解依赖倒转原则应该是很容易的。
- 依赖倒转原则的常用实现方式之一是在代码中使用抽象类，而将具体类放在配置文件中。
- 类之间的耦合: 零耦合关系，具体耦合关系，抽象耦合关系。依赖倒转原则要求客户端依赖于抽象耦合，以抽象方式耦合是依赖倒转原则的关键。

**例子**

```java
/** 
 * 依赖注入是依赖AbstractSource抽象注入的，而不是具体 
 * DatabaseSource 
 * 
 */  
abstract class AbstractStransformer {  
    private AbstractSource source;   
    /** 
     * 构造注入(Constructor Injection): 通过构造函数注入实例变量。 
     */  
    public void AbstractStransformer(AbstractSource source){  
        this.source = source;           
    }  
    /**      
     * 设值注入(Setter Injection): 通过Setter方法注入实例变量。 
     * @param source : the sourceto set        
     */       
    public void setSource(AbstractSource source) {            
        this.source = source;             
    }  
    /** 
     * 接口注入(Interface Injection): 通过接口方法注入实例变量。 
     * @param source 
     */  
    public void transform(AbstractSource source ) {    
        source.getSource();  
        System.out.println("Stransforming ...");    
    }      
}
```
#### 其他原则

##### 合成/聚合复用原则

**定义**

经常又叫做合成复用原则(Composite ReusePrinciple或CRP)，尽量使用对象组合，而不是继承来达到复用的目的。

就是在一个新的对象里面使用一些已有的对象，使之成为新对象的一部分；新对象通过向这些对象的委派达到复用已有功能的目的。简而言之，要尽量使用合成/聚合，尽量不要使用继承。

原因分析：

- 继承复用: 实现简单，易于扩展。破坏系统的封装性；

- 组合/聚合复用: 耦合度相对较低，选择性地调用成员对象的操作；

组合/聚合可以使系统更加灵活，类与类之间的耦合度降低，一个类的变化对其他类造成的影响相对较少，因此一般首选使用组合/聚合来实现复用；其次才考虑继承，在使用继承时，需要严格遵循里氏代换原则，有效使用继承会有助于对问题的理解，降低复杂度，而滥用继承反而会增加系统构建和维护的难度以及系统的复杂度，因此需要慎重使用继承复用。

##### 迪米特法则

> > Law of Demeter，LoD: 系统中的类,尽量不要与其他类互相作用,减少类之间的耦合度

**定义**

简单地说，也就是，一个对象应当对其它对象有尽可能少的了解。一个类应该对自己需要耦合或调用的类知道得最少，你(被耦合或调用的类)的内部是如何复杂都和我没关系，那是你的事情，我就知道你提供的public方法，我就调用这么多，其他的一概不关心。

**例子**

外观模式Facade(结构型)

迪米特法则与设计模式Facade模式、Mediator模式

### 分布式理论 - CAP

> CAP理论是分布式系统、特别是分布式存储领域中被讨论的最多的理论。其中C代表一致性 (Consistency)，A代表可用性 (Availability)，P代表分区容错性 (Partition tolerance)。CAP理论告诉我们C、A、P三者不能同时满足，最多只能满足其中两个。

#### CAP三选二

- `一致性 (Consistency)`: 一个写操作返回成功，那么之后的读请求都必须读到这个新数据；如果返回失败，那么所有读操作都不能读到这个数据。所有节点访问同一份最新的数据。
- `可用性 (Availability)`: 对数据更新具备高可用性，请求能够及时处理，不会一直等待，即使出现节点失效。
- `分区容错性 (Partition tolerance)`: 能容忍网络分区，在网络断开的情况下，被分隔的节点仍能正常对外提供服务。

#### 对CAP理论对理解

理解CAP理论最简单的方式是想象两个副本处于分区两侧，即两个副本之间的网络断开，不能通信。

- 如果允许其中一个副本更新，则会导致数据不一致，即丧失了C性质。
- 如果为了保证一致性，将分区某一侧的副本设置为不可用，那么又丧失了A性质。
- 除非两个副本可以互相通信，才能既保证C又保证A，这又会导致丧失P性质。

一般来说使用网络通信的分布式系统，无法舍弃P性质，那么就只能在一致性和可用性上做一个艰难的选择。

**无法舍弃P性质，只能AP和CP**

AP模式：

1.eureka服务注册与发现中心集群

在服务B向Eureka2注册成功后，此时，Eureka2还没向Eureka3复制成功就挂掉了，此时，在Eureka的服务注册与发现中心集群中造成了数据不一致。当服务A通过服务注册于发现中心集群通过Eureka3来拿服务B的地址时，就无法拿到。

2.mysql数据集群与redis集群，由于mysql和redis的数据复制都是采用的异步复制，所以mysql数据集群与redis集群都属于AP类型，在集群中获取数据时，会存在数据不一致的情况

CP模式：

1.zookeeper服务注册与发现中心集群

在集群中，包含一个Leader节点，其余全部为Follower 节点。Leader节点负责读和写操作，Follower 节点只负责读操作。当客户端向集群发出写请求时，写请求会转发到Leader节点，Leader写操作完成后，采用广播的形式，向其余Follower 节点复制数据，Follower节点也写成功，返回给客户端成功

### 分布式理论 - BASE

> BASE是“Basically Available, Soft state, Eventually consistent(基本可用、软状态、最终一致性)”的首字母缩写。其中的软状态和最终一致性这两种技巧擅于对付存在分区的场合，并因此提高了可用性。

#### 什么是BASE

> eBay 的架构师 Dan Pritchett 源于对大规模分布式系统的实践总结，在 ACM 上发表文章提出 BASE 理论，BASE 理论是对 CAP 理论的延伸，核心思想是即使无法做到强一致性（Strong Consistency，CAP 的一致性就是强一致性），但应用可以采用适合的方式达到最终一致性（Eventual Consitency）。

### 事务理论 - ACID

> 事务的四个基本特性。

#### 什么是ACID

一个事务有四个基本特性，也就是我们常说的（ACID）：

1. **Atomicity（原子性）**：事务是一个不可分割的整体，事务内所有操作要么全做成功，要么全失败。
2. **Consistency（一致性）**：事务执行前后，数据从一个状态到另一个状态必须是一致的（A向B转账，不能出现A扣了钱，B却没收到）。
3. **Isolation（隔离性）**： 多个并发事务之间相互隔离，不能互相干扰。
4. **Durablity（持久性）**：事务完成后，对数据库的更改是永久保存的，不能回滚。

#### ACID靠什么保证的呢？

以MySQL为例：

**A原子性**由undo log日志保证，它记录了需要回滚的日志信息，事务回滚时撤销已经执行成功的sql

**C一致性**一般由代码层面来保证

**I隔离性**由MVCC来保证

**D持久性**由内存+redo log来保证，mysql修改数据同时在内存和redo log记录这次操作，事务提交的时候通过redo log刷盘，宕机的时候可以从redo log恢复

> undo log
>
> innoDB把这些为了回滚而记录的这些东西称之为undo log

> MVCC 
>
> MVCC(Mutil-Version Concurrency Control)，就是多版本并发控制。MVCC 是一种并发控制的方法，一般在数据库管理系统中，实现对数据库的并发访问。详情见 MySQL - MySQL innoDB的MVCC实现机制

### 康威定律 - 微服务基础

- 定律一: 组织沟通方式会通过系统设计表达出来，就是说架构的布局和组织结构会有相似。
- 定律二: 时间再多一件事情也不可能做的完美，但总有时间做完一件事情。一口气吃不成胖子，先搞定能搞定的。
- 定律三: 线型系统和线型组织架构间有潜在的异质同态特性。种瓜得瓜，做独立自治的子系统减少沟通成本。
- 定律四: 大的系统组织总是比小系统更倾向于分解。合久必分，分而治之。

### 开发流程详解

#### 项目研发流程规范

> > 谈谈我常见的敏捷开发流程的理解:
>>
> > 比较适合大一点的公司和团队，需求，UI，测试，产品经理和开发独立；
>> 采用前后端分离, 前端采用前端开发框架提供页面，后端提供数据接口等；
> > 有完善的CI&CD环境；
>> 完善的代码分支，权限控制和分配；
> > Scrum敏捷开发，每个Sprint为2-4周，周期根据User Story量进行调整；
>> 这个研发流程中最好再 加入不同的部门及角色负责相应流程的标注；以及各个阶段，各个角色所需要有的产出；
> 

![img](https://www.pdai.tech/_images/dev_workflow.png)

### 开源协议详解

略

### 代码规范

#### 编码规范

**命名风格**

3.【强制】类名使用 `UpperCamelCase` 风格，必须遵从驼峰形式，但以下情形例外: `DO / BO / DTO / VO / AO`
 **正例** : `MarcoPolo / UserDO / XmlService / TcpUdpDeal / TaPromotion`
 **反例** :  `macroPolo / UserDo / XMLService / TCPUDPDeal / TAPromotion`

6.【强制】抽象类命名使用 `Abstract` 或 `Base` 开头；异常类命名使用 `Exception` 结尾；测试类命名以它要测试的类的名称开始，以 `Test` 结尾。

9.【强制】包名统一使用小写，点分隔符之间有且仅有一个自然语义的英语单词。包名统一使用单数形式，但是类名如果有复数含义，类名可以使用复数形式。
 **正例** : 应用工具类包名为 `com.alibaba.open.util`、类名为 `MessageUtils`(此规则参考 spring 的框架结构)

10.【强制】杜绝完全不规范的缩写，避免望文不知义。
 **反例** :  `AbstractClass` “缩写”命名成 `AbsClass`；`condition` “缩写”命名成 `condi`，此类随意缩写严重降低了代码的可阅读性。

11.【推荐】如果使用到了设计模式，建议在类名中体现出具体模式。
**说明** : 将设计模式体现在名字中，有利于阅读者快速理解架构设计思想。

```java
public class OrderFactory;
public class LoginProxy;
public class ResourceObserver;
```
15.【参考】各层命名规约:

`Service/DAO` 层方法命名规约

1. 获取单个对象的方法用 `get` 做前缀。
2. 获取多个对象的方法用 `list` 做前缀。
3. 获取统计值的方法用 `count` 做前缀。
4. 插入的方法用 `save`(推荐)或 `insert` 做前缀。
5. 删除的方法用 `remove`(推荐)或 `delete` 做前缀。
6. 修改的方法用 `update` 做前缀。

领域模型命名规约

1. 数据对象: `xxxDO`，`xxx` 即为数据表名。
2. 数据传输对象: `xxxDTO`，`xxx` 为业务领域相关的名称。
3. 展示对象: `xxxVO`，`xxx` 一般为网页名称。
4. `POJO` 是 `DO/DTO/BO/VO` 的统称，禁止命名成 `xxxPOJO`。

**常量定义**

1.【强制】不允许任何魔法值(即未经定义的常量)直接出现在代码中。

反例：

```java
 String key = "Id#taobao_" + tradeId;  
 cache.put(key, value);
```

**代码格式**

6.【强制】单行字符数限制不超过 120 个，超出需要换行，换行时遵循如下原则:

1. 第二行相对第一行缩进 4 个空格，从第三行开始，不再继续缩进，参考示例。

2. 运算符与下文一起换行。

3. 方法调用的点符号与下文一起换行。

4. 在多个参数超长，在逗号后换行。

5. 在括号前不要换行，见反例。
    **正例** :

   ```java
    StringBuffer sb = new StringBuffer();
    //超过 120 个字符的情况下，换行缩进 4 个空格，并且方法前的点符号一起换行
    sb.append("zi").append("xin")...
        .append("huang")...
        .append("huang")...
        .append("huang");
   ```

   **反例** :

   ```java
    StringBuffer sb = new StringBuffer();
    //超过 120 个字符的情况下，不要在括号前换行
    sb.append("zi").append("xin")...append
    ("huang");
    //参数很多的方法调用可能超过 120 个字符，不要在逗号前换行
    method(args1, args2, args3, ...
    , argsX); 
   ```

**OOP规约**

6.【强制】`Object` 的 `equals` 方法容易抛空指针异常，应使用常量或确定有值的对象来调用 `equals`。 

**正例** : `"test".equals(object);`

**反例** :  `object.equals("test");` 

**说明** :  推荐使用 `java.util.Objects#equals` (JDK7 引入的工具类)

7.【强制】所有的相同类型的包装类对象之间值的比较，全部使用 `equals` 方法比较。 

8.关于基本数据类型与包装数据类型的使用标准如下:

​	1.【强制】所有的 `POJO` 类属性必须使用包装数据类型。

​	2.【强制】RPC 方法的返回值和参数必须使用包装数据类型。

​	3.【推荐】所有的局部变量使用基本数据类型。
​	**说明** :  `POJO` 类属性没有初值是提醒使用者在需要使用时，必须自己显式地进行赋值，任何 NPE 问题，或者入库检查，都由使用者来保证。
​	**正例** : 数据库的查询结果可能是 `null`，因为自动拆箱，用基本数据类型接收有 NPE 风险。
​	**反例** :  比如显示成交总额涨跌情况，即正负 x%，x 为基本数据类型，调用的 RPC 服务，调用不成功时，返回的是默认值，页面显示: 0%，这是不合理的，应该显示成中划线-。所以包装数据类型的 `null` 值，能够表示额外的信息，如: 远程调用失败，异常退出。

9.【强制】定义 `DO/DTO/VO` 等 `POJO` 类时，不要设定任何属性默认值。
 **反例** :  `POJO` 类的 `gmtCreate` 默认值为 `new Date()`;但是这个属性在数据提取时并没有置入具 体值，在更新其它字段时又附带更新了此字段，导致创建时间被修改成当前时间。

10.【强制】序列化类新增属性时，请不要修改 `serialVersionUID` 字段，避免反序列失败；如 果完全不兼容升级，避免反序列化混乱，那么请修改 `serialVersionUID` 值。
**说明** :  注意 `serialVersionUID` 不一致会抛出序列化运行时异常。

11.【强制】构造方法里面禁止加入任何业务逻辑，如果有初始化逻辑，请放在 `init` 方法中

12.【强制】`POJO` 类必须写 `toString` 方法。使用 IDE 的中工具: `source> generate toString` 时，如果继承了另一个 `POJO` 类，注意在前面加一下 `super.toString`。
**说明** :  在方法执行抛出异常时，可以直接调用 `POJO` 的 `toString()`方法打印其属性值，便于排查问题。

13.【推荐】使用索引访问用 `String` 的 `split` 方法得到的数组时，需做最后一个分隔符后有无内容的检查，否则会有抛 `IndexOutOfBoundsException` 的风险。
**说明** :

```java
String str = "a,b,c,,";
String[] ary = str.split(",");
//预期大于 3，结果是 3
System.out.println(ary.length);
```

19.【推荐】慎用 `Object` 的 `clone` 方法来拷贝对象。
**说明** : 对象的 `clone` 方法默认是浅拷贝，若想实现深拷贝需要重写 `clone` 方法实现属性对象的拷贝。

**集合处理**

1.【强制】关于 `hashCode` 和 `equals` 的处理，遵循如下规则:

- 只要重写 `equals`，就必须重写 `hashCode。`

- 因为 `Set` 存储的是不重复的对象，依据 `hashCode` 和 `equals` 进行判断，所以 `Set` 存储的对象必须重写这两个方法。

- 如果自定义对象做为 `Map` 的键，那么必须重写 `hashCode` 和 `equals`。
   **说明** :  `String` 重写了 `hashCode` 和 `equals` 方法，所以我们可以非常愉快地使用 `String` 对象作为 `key` 来使用。

2.【强制】`ArrayList` 的 `subList` 结果不可强转成 `ArrayList`，否则会抛出 `ClassCastException` 异常: `java.util.RandomAccessSubList cannot be cast to java.util.ArrayList` ;
**说明** :  `subList` 返回的是 `ArrayList` 的内部类 `SubList`，并不是 `ArrayList` ，而是 `ArrayList` 的一个视图，对于 `SubList` 子列表的所有操作最终会反映到原列表上。

3.【强制】 在 `subList` 场景中，高度注意对原集合元素个数的修改，会导致子列表的遍历、增 加、删除均产生 `ConcurrentModificationException` 异常。

4.【强制】使用集合转数组的方法，必须使用集合的 `toArray(T[] array)`，传入的是类型完全一样的数组，大小就是 `list.size()`。
**说明** :  使用 `toArray` 带参方法，入参分配的数组空间不够大时，`toArray` 方法内部将重新分配内存空间，并返回新数组地址；如果数组元素大于实际所需，下标为`[ list.size() ]`的数组元素将被置为 `null`，其它数组元素保持原值，因此最好将方法入参数组大小定义与集合元素个数一致。

**正例** :

```java
List<String> list = new ArrayList<String>(2);
list.add("guan");
list.add("bao");
String[] array = new String[list.size()];
array = list.toArray(array);
```

**反例** :  直接使用 `toArray` 无参方法存在问题，此方法返回值只能是 `Object[]`类，若强转其它类型数组将出现 `ClassCastException` 错误。

5.【强制】使用工具类 `Arrays.asList()`把数组转换成集合时，不能使用其修改集合相关的方 法，它的 `add/remove/clear` 方法会抛出 `UnsupportedOperationException` 异常。
**说明** :  `asList` 的返回对象是一个 `Arrays` 内部类，并没有实现集合的修改方法。`Arrays.asList` 体现的是适配器模式，只是转换接口，后台的数据仍是数组。

```java
String[] str = new String[] { "a", "b" };
List list = Arrays.asList(str);
```

第一种情况: `list.add("c");` 运行时异常。
第二种情况: `str[0] = "gujin";` 那么 list.get(0)也会随之修改。

6.【强制】泛型通配符 `<? extends T>` 来接收返回的数据，此写法的泛型集合不能使用 `add` 方法，而 `<? super T>` 不能使用 `get` 方法，做为接口调用赋值时易出错。
**说明** :  扩展说一下 `PECS(Producer Extends Consumer Super)` 原则: 1)频繁往外读取内容 的，适合用上界 `Extends`。2)经常往里插入的，适合用下界 `Super`。

7.【强制】不要在 `foreach` 循环里进行元素的 `remove/add` `操作。remove` 元素请使用 `Iterator` 方式，如果并发操作，需要对 `Iterator` 对象加锁。
**正例** :

```java
Iterator<String> it = a.iterator();
while (it.hasNext()) {
  String temp = it.next();
  if (删除元素的条件) {
    it.remove();
  }
}
```

反例 :

```java
List<String> a = new ArrayList<String>();
a.add("1");
a.add("2");
for (String temp : a) {
	if ("1".equals(temp)) {
		a.remove(temp);
	}
}
```

**说明** :  以上代码的执行结果肯定会出乎大家的意料，那么试一下把“1”换成“2”，会是同样的结果吗? 

8.【强制】 在 JDK7 版本及以上，`Comparator` 要满足如下三个条件，不然 `Arrays.sort`，`Collections.sort` 会报 `IllegalArgumentException` 异常。

**说明** :

1. x，y 的比较结果和 y，x 的比较结果相反。
2. x>y，y>z，则 x>z。
3. x=y，则 x，z 比较结果和 y，z 比较结果相同。

**反例** :  下例中没有处理相等的情况，实际使用中可能会出现异常:

```java
new Comparator<Student>() {
  @Override
  public int compare(Student o1, Student o2) {
    return o1.getId() > o2.getId() ? 1 : -1;
  }
};
```

10.【推荐】使用 `entrySet` 遍历 `Map` 类集合 KV，而不是 `keySet` 方式进行遍历。
**说明** :  `keySet` 其实是遍历了 2 次，一次是转为 `Iterator` 对象，另一次是从 `hashMap` 中取出 `key` 所对应的 `value`。而 `entrySet` 只是遍历了一次就把 `key` 和 `value` 都放到了 `entry` 中，效率更高。如果是 JDK8，使用 `Map.foreach` 方法。
 **正例** : `values()` 返回的是 V 值集合，是一个 `list` 集合对象；`keySet()` 返回的是 K 值集合，是一个 `Set` 集合对象；`entrySet()` 返回的是 K-V 值组合集合。

11.【推荐】高度注意 Map 类集合 K/V 能不能存储 null 值的情况，如下表格:

| 集合类            | Key           | Value         | Super       | 说明       |
| :---------------- | :------------ | :------------ | :---------- | :--------- |
| HashTable         | 不允许为 null | 不允许为 null | Dictionary  | 线程安全   |
| ConcurrentHashMap | 不允许为 null | 不允许为 null | AbstractMap | 分段锁技术 |
| TreeMap           | 不允许为 null | 允许为 null   | AbstractMap | 线程不安全 |
| HashMap           | 允许为 null   | 允许为 null   | AbstractMap | 线程不安全 |

**反例** :  由于 `HashMap` 的干扰，很多人认为 `ConcurrentHashMap` 是可以置入 `null` 值，而事实上，存储 `null` 值时会抛出 NPE 异常。

12.【参考】合理利用好集合的有序性(sort)和稳定性(order)，避免集合的无序性(unsort)和不稳定性(unorder)带来的负面影响。
**说明** :  有序性是指遍历的结果是按某种比较规则依次排列的。稳定性指集合每次遍历的元素次 序是一定的。如:  `ArrayList` 是 order/unsort；`HashMap` 是 unorder/unsort；`TreeSet` 是 order/sort。

13.【参考】利用 `Set` 元素唯一的特性，可以快速对一个集合进行去重操作，避免使用 `List` 的 `contains` 方法进行遍历、对比、去重操作。

**并发处理**

3.【强制】线程资源必须通过线程池提供，不允许在应用中自行显式创建线程。
**说明** :  使用线程池的好处是减少在创建和销毁线程上所花的时间以及系统资源的开销，解决资源不足的问题。如果不使用线程池，有可能造成系统创建大量同类线程而导致消耗完内存或者“过度切换”的问题。

4.【强制】线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。
**说明** :  `Executors` 返回的线程池对象的弊端如下:

1. `FixedThreadPool` 和 `SingleThreadPool`:
    允许的请求队列长度为 Integer.MAX_VALUE，可能会堆积大量的请求，从而导致 OOM。
2. `CachedThreadPool` 和 `ScheduledThreadPool`:
    允许的创建线程数量为 `Integer.MAX_VALUE`，可能会创建大量的线程，从而导致 OOM。

6.【强制】高并发时，同步调用应该去考量锁的性能损耗。能用无锁数据结构，就不要用锁；能锁区块，就不要锁整个方法体；能用对象锁，就不要用类锁。
**说明** :  尽可能使加锁的代码块工作量尽可能的小，避免在锁代码块中调用 RPC 方法。

7.【强制】对多个资源、数据库表、对象同时加锁时，需要保持一致的加锁顺序，否则可能会造成死锁。
**说明** :  线程一需要对表 A、B、C 依次全部加锁后才可以进行更新操作，那么线程二的加锁顺序也必须是 A、B、C，否则可能出现死锁。

8.【强制】并发修改同一记录时，避免更新丢失，需要加锁。要么在应用层加锁，要么在缓存加锁，要么在数据库层使用乐观锁，使用 version 作为更新依据。
**说明** :  如果每次访问冲突概率小于 20%，推荐使用乐观锁，否则使用悲观锁。乐观锁的重试次数不得小于 3 次。

9.【强制】多线程并行处理定时任务时，`Timer` 运行多个 `TimeTask` 时，只要其中之一没有捕获抛出的异常，其它任务便会自动终止运行，使用 `ScheduledExecutorService` 则没有这个问题。

10.【推荐】使用 `CountDownLatch` 进行异步转同步操作，每个线程退出前必须调用 `countDown` 方法，线程执行代码注意 catch 异常，确保 `countDown` 方法可以执行，避免主线程无法执行至 `await` 方法，直到超时才返回结果。
**说明** :  注意，子线程抛出异常堆栈，不能在主线程 try-catch 到。

11.【推荐】避免 `Random` 实例被多线程使用，虽然共享该实例是线程安全的，但会因竞争同一 seed 导致的性能下降。
**说明** :  `Random` 实例包括 `java.util.Random` 的实例或者 `Math.random()` 的方式。
 **正例** : 在 JDK7 之后，可以直接使用 API `ThreadLocalRandom`，而在 JDK7 之前，需要编码保证每个线程持有一个实例。

12.【推荐】在并发场景下，通过双重检查锁(double-checked locking)实现延迟初始化的优化问题隐患(可参考 The "Double-Checked Locking is Broken" Declaration)，推荐问题解决方案中较为简单一种(适用于 JDK5 及以上版本)，将目标属性声明为 `volatile` 型。
 **反例** :

```java
class Foo {
    private Helper helper = null;
    public Helper getHelper() {
        if (helper == null) synchronized(this) {
            if (helper == null)
                helper = new Helper();
        }
        return helper;
    }
    // other functions and members...
}
```

13.【参考】volatile 解决多线程内存不可见问题。对于一写多读，是可以解决变量同步问题，但是如果多写，同样无法解决线程安全问题。如果是 count++操作，使用如下类实现: `AtomicInteger count = new AtomicInteger(); count.addAndGet(1);` 如果是 JDK8，推荐使用 `LongAdder` 对象，比 `AtomicLong` 性能更好(减少乐观锁的重试次数)。

14.【参考】  在容量不够进行 resize 时由于高并发可能出现死链，导致 CPU 飙升，在开发过程中可以使用其它数据结构或加锁来规避此风险。

15.【参考】`ThreadLocal` 无法解决共享对象的更新问题，`ThreadLocal` 对象建议使用 `static` 修饰。这个变量是针对一个线程内所有操作共有的，所以设置为静态变量，所有此类实例共享此静态变量，也就是说在类第一次被使用时装载，只分配一块存储空间，所有此类的对象(只要是这个线程内定义的)都可以操控这个变量。

**控制语句**

7.【参考】下列情形，需要进行参数校验:

1. 调用频次低的方法。
2. 执行时间开销很大的方法。此情形中，参数校验时间几乎可以忽略不计，但如果因为参 数错误导致中间执行回退，或者错误，那得不偿失。
3. 需要极高稳定性和可用性的方法。
4. 对外提供的开放接口，不管是 RPC/API/HTTP 接口。
5. 敏感权限入口。

8.【参考】下列情形，不需要进行参数校验:

1. 极有可能被循环调用的方法。但在方法说明里必须注明外部参数检查要求。
2. 底层调用频度比较高的方法。毕竟是像纯净水过滤的最后一道，参数错误不太可能到底 层才会暴露问题。一般 DAO 层与 Service 层都在同一个应用中，部署在同一台服务器中，所以 DAO 的参数校验，可以省略。
3. 被声明成 private 只会被自己代码所调用的方法，如果能够确定调用方法的代码传入参数已经做过检查或者肯定不会有问题，此时可以不校验参数。

**注释规约**

略

**其他**

1.【强制】在使用正则表达式时，利用好其预编译功能，可以有效加快正则匹配速度。 

 说明: 不要在方法体内定义: `Pattern pattern = Pattern.compile(规则);`

3.【强制】后台输送给页面的变量必须加$!{var}——中间的感叹号。
**说明** : 如果 `var=null` 或者不存在，那么${var}会直接显示在页面上。

4.【强制】注意 `Math.random()` 这个方法返回是 double 类型，注意取值的范围 0≤x<1(能够取到零值，注意除零异常)，如果想获取整数类型的随机数，不要将 x 放大 10 的若干倍然后取整，直接使用 Random 对象的 nextInt 或者 nextLong 方法。

5.【强制】获取当前毫秒数 `System.currentTimeMillis()`; 而不是 `new Date().getTime()`;
**说明** :  如果想获取更加精确的纳秒级时间值，使用 System.nanoTime()的方式。在 JDK8 中，针对统计时间等场景，推荐使用 Instant 类。

#### 异常日志

**异常处理**

1.【强制】对大段代码进行 try-catch，这是不负责任的表现。catch 时请分清稳定代码和非稳定代码，稳定代码指的是无论如何不会出错的代码。对于非稳定代码的 catch 尽可能进行区分异常类型，再做对应的异常处理。

5.【强制】有 try 块放到了事务代码中，catch 异常后，如果需要回滚事务，一定要注意手动回滚事务。

6.【强制】finally 块必须对资源对象、流对象进行关闭，有异常也要做 try-catch。
**说明** : 如果 JDK7 及以上，可以使用 try-with-resources 方式。

7.【强制】不能在 finally 块中使用 return，finally 块中的 return 返回后方法结束执行，不会再执行 try 块中的 return 语句。

9.【推荐】方法的返回值可以为 null，不强制返回空集合，或者空对象等，必须添加注释充分说明什么情况下会返回 null 值。调用方需要进行 null 判断防止 NPE 问题。
**说明** :  本手册明确防止 NPE 是调用者的责任。即使被调用方法返回空集合或者空对象，对调用者来说，也并非高枕无忧，必须考虑到远程调用失败、序列化失败、运行时异常等场景返回 null 的情况。

10.【推荐】防止 NPE，是程序员的基本修养，注意 NPE 产生的场景: 

1) 返回类型为基本数据类型，return 包装数据类型的对象时，自动拆箱有可能产生 NPE。

​    反例 : public int f() { return Integer 对象}， 如果为 null，自动解箱抛 NPE。

2) 数据库的查询结果可能为 null。

3) 集合里的元素即使 isNotEmpty，取出的数据元素也可能为 null。

4) 远程调用返回对象时，一律要求进行空指针判断，防止 NPE。

5) 对于 Session 中获取的数据，建议 NPE 检查，避免空指针。

6) 级联调用 obj.getA().getB().getC()；一连串调用，易产生 NPE。

正例 : 使用 JDK8 的 Optional 类来防止 NPE 问题。

11.【推荐】定义时区分 unchecked / checked 异常，避免直接抛出 `new RuntimeException()`，更不允许抛出 Exception 或者 Throwable，应使用有业务含义的自定义异常。推荐业界已定义过的自定义异常，如: DAOException / ServiceException 等。

12.【参考】在代码中使用“抛异常”还是“返回错误码”，对于公司外的 http/api 开放接口必须使用“错误码”；而应用内部推荐异常抛出；跨应用间 RPC 调用优先考虑使用 Result 方式，封装 isSuccess()方法、“错误码”、“错误简短信息”。
**说明** :  关于 RPC 方法返回方式使用 Result 方式的理由:
 1)使用抛异常返回方式，调用方如果没有捕获到就会产生运行时错误。
 2)如果不加栈信息，只是 new 自定义异常，加入自己的理解的 error message，对于调用端解决问题的帮助不会太多。如果加了栈信息，在频繁调用出错的情况下，数据序列化和传输的性能损耗也是问题。

**日志规约**

3.【强制】应用中的扩展日志(如打点、临时监控、访问日志等)命名方式: appName_logType_logName.log。logType:日志类型，推荐分类有 stats/desc/monitor/visit 等；logName:日志描述。这种命名的好处: 通过文件名就可知道日志文件属于什么应用，什么类型，什么目的，也有利于归类查找。
 **正例** : mppserver 应用中单独监控时区转换异常，如:  mppserver_monitor_timeZoneConvert.log
**说明** :  推荐对日志进行分类，如将错误日志和业务日志分开存放，便于开发人员查看，也便于 通过日志对系统进行及时监控。

5.【强制】避免重复打印日志，浪费磁盘空间，务必在 log4j.xml 中设置 additivity=false。
 **正例** : `<logger name="com.taobao.dubbo.config" additivity="false">`

#### MySQL数据库

**建表规约**

1.【强制】表达是与否概念的字段，必须使用 is_xxx 的方式命名，数据类型是 unsigned tinyint( 1 表示是，0 表示否)。
**说明** :  任何字段如果为非负数，必须是 unsigned。
 **正例** : 表达逻辑删除的字段名 is_deleted，1 表示删除，0 表示未删除。

6.【强制】小数类型为 decimal，禁止使用 float 和 double。
**说明** :  float 和 double 在存储的时候，存在精度损失的问题，很可能在值的比较时，得到不正确的结果。如果存储的数据范围超过 decimal 的范围，建议将数据拆成整数和小数分开存储。

7.【强制】如果存储的字符串长度几乎相等，使用 char 定长字符串类型。

8.【强制】varchar 是可变长字符串，不预先分配存储空间，长度不要超过 5000，如果存储长度大于此值，定义字段类型为 text，独立出来一张表，用主键来对应，避免影响其它字段索引效率。

9.【强制】表必备三字段: id, gmt_create, gmt_modified。**说明** :  其中 id 必为主键，类型为 unsigned bigint、单表时自增、步长为 1。gmt_create, gmt_modified 的类型均为 date_time 类型。

10.【推荐】表的命名最好是加上“业务名称_表的作用”。
**正例** : tiger_task / tiger_reader / mpp_config

**索引规约**

1.【强制】业务上具有唯一特性的字段，即使是多个字段的组合，也必须建成唯一索引。 

**说明** : 不要以为唯一索引影响了 insert 速度，这个速度损耗可以忽略，但提高查找速度是明显的；另外，即使在应用层做了非常完善的校验控制，只要没有唯一索引，根据墨菲定律，必然有脏数据产生。

2.【强制】 超过三个表禁止 join。需要 join 的字段，数据类型必须绝对一致；多表关联查询时，保证被关联的字段需要有索引。
**说明** : 即使双表 join 也要注意表索引、SQL 性能。

3.【强制】在 varchar 字段上建立索引时，必须指定索引长度，没必要对全字段建立索引，根据实际文本区分度决定索引长度即可。

**说明** :  索引的长度与区分度是一对矛盾体，一般对字符串类型数据，长度为 20 的索引，区分度会高达 90%以上，可以使用 count(distinct left(列名, 索引长度))/count(*)的区分度来确定。

4.【强制】页面搜索严禁左模糊或者全模糊，如果需要请走搜索引擎来解决。
**说明** : 索引文件具有 B-Tree 的最左前缀匹配特性，如果左边的值未确定，那么无法使用此索引。

5.【推荐】如果有 order by 的场景，请注意利用索引的有序性。order by 最后的字段是组合索引的一部分，并且放在索引组合顺序的最后，避免出现 file_sort 的情况，影响查询性能。
 **正例** : where a=? and b=? order by c; 索引: a_b_c
 **反例** :  索引中有范围查找，那么索引有序性无法利用，如: WHERE a>10 ORDER BY b; 索引 a_b 无法排序。

6.【推荐】利用覆盖索引来进行查询操作，避免回表。
**说明** :  如果一本书需要知道第 11 章是什么标题，会翻开第 11 章对应的那一页吗? 目录浏览一下就好，这个目录就是起到覆盖索引的作用。
 **正例** : 能够建立索引的种类: 主键索引、唯一索引、普通索引，而覆盖索引是一种查询的一种 效果，用 explain 的结果，extra 列会出现: using index。

7.【推荐】利用延迟关联或者子查询优化超多分页场景。
**说明** :  MySQL 并不是跳过 offset 行，而是取 offset+N 行，然后返回放弃前 offset 行，返回 N 行，那当 offset 特别大的时候，效率就非常的低下，要么控制返回的总页数，要么对超过特定阈值的页数进行 SQL 改写。
 **正例** : 先快速定位需要获取的 id 段，然后再关联: SELECT a.* FROM 表 1 a, (select id from 表 1 where 条件 LIMIT 100000,20 ) b where a.id=b.id

8.【推荐】SQL 性能优化的目标: 至少要达到 range 级别，要求是 ref 级别，如果可以是 consts 最好。
**说明** :

​	1)consts 单表中最多只有一个匹配行(主键或者唯一索引)，在优化阶段即可读取到数据。

​	2)ref 指的是使用普通的索引(normal index)。

​	3)range 对索引进行范围检索。
**反例** :  explain 表的结果，type=index，索引物理文件全扫描，速度非常慢，这个 index 级别比较 range 还低，与全表扫描是小巫见大巫。

9.【推荐】建组合索引的时候，区分度最高的在最左边。
 **正例** : 如果 where a=? and b=? ，a 列的几乎接近于唯一值，那么只需要单建 idx_a 索引即可。
**说明** :  存在非等号和等号混合判断条件时，在建索引时，请把等号条件的列前置。如: where a>? and b=? 那么即使 a 的区分度更高，也必须把 b 放在索引的最前列。

10.【推荐】防止因字段类型不同造成的隐式转换，导致索引失效。

11.【参考】创建索引时避免有如下极端误解:
	1)宁滥勿缺。误认为一个查询就需要建一个索引。
	2)宁缺勿滥。误认为索引会消耗空间、严重拖慢更新和新增速度。
	3)抵制惟一索引。误认为业务的惟一性一律需要在应用层通过“先查后插”方式解决。

**SQL语句**

1.【强制】不要使用 count(列名)或 count(常量)来替代 count(*)，count(*)是 SQL92 定义的标准统计行数的语法，跟数据库无关，跟 NULL 和非 NULL 无关。
**说明** :  count(*)会统计值为 NULL 的行，而 count(列名)不会统计此列为 NULL 值的行。

2.【强制】count(distinct col) 计算该列除 NULL 之外的不重复行数，注意 count(distinct col1, col2) 如果其中一列全为 NULL，那么即使另一列有不同的值，也返回为 0。

4.【强制】使用 ISNULL()来判断是否为 NULL 值。注意: NULL 与任何值的直接比较都为 NULL。
**说明** :

```java
1) NULL<>NULL 的返回结果是 NULL，而不是 false。  
2) NULL=NULL 的返回结果是 NULL，而不是 true。  
3) NULL<>1 的返回结果是 NULL，而不是 true。
```

6.【强制】不得使用外键与级联，一切外键概念必须在应用层解决。
**说明** :  (概念解释)学生表中的 student_id 是主键，那么成绩表中的 student_id 则为外键。如果更新学生表中的 student_id，同时触发成绩表中的 student_id 更新，则为级联更新。外键与级联更新适用于单机低并发，不适合分布式、高并发集群；级联更新是强阻塞，存在数据库更新风暴的风险；外键影响数据库的插入速度。

10.【参考】如果有全球化需要，所有的字符存储与表示，均以 utf-8 编码，注意字符统计函数的区别。
**说明** :
SELECT LENGTH("轻松工作")； 返回为 12
SELECT CHARACTER_LENGTH("轻松工作")； 返回为 4
如果要使用表情，那么使用 utfmb4 来进行存储，注意它与 utf-8 编码的区别。

**ORM映射**

2.【强制】POJO 类的布尔属性不能加 is，而数据库字段必须加 is_，要求在 resultMap 中进行字段与属性之间的映射。
**说明** :  参见定义 POJO 类以及数据库字段定义规定，在`<resultMap>`中增加映射，是必须的。在 MyBatis Generator 生成的代码中，需要进行对应的修改。

4.【强制】sql.xml 配置参数使用: #{}，#param# 不要使用${} 此种方式容易出现 SQL 注入。

6.【强制】不允许直接拿 HashMap 与 Hashtable 作为查询结果集的输出。
**说明** : resultClass=”Hashtable”，会置入字段名和属性值，但是值的类型不可控。

**应用分层**

略

**二方库依赖**

略

**服务器**

3.【推荐】给 JVM 设置-XX:+HeapDumpOnOutOfMemoryError 参数，让 JVM 碰到 OOM 场景时输出 dump 信息。

**说明** :  OOM 的发生是有概率的，甚至有规律地相隔数月才出现一例，出现时的现场信息对查错 非常有价值。

#### 安全规约

4.【强制】用户请求传入的任何参数必须做有效性验证。

**说明** :  忽略参数校验可能导致:

- page size 过大导致内存溢出
- 恶意 order by 导致数据库慢查询
- 任意重定向
- SQL 注入
- 反序列化注入
- 正则输入源串拒绝服务 ReDoS

**说明** :  Java 代码用正则来验证客户端的输入，有些正则写法验证普通用户输入没有问题， 但是如果攻击人员使用的是特殊构造的字符串来验证，有可能导致死循环的结果。

7.【强制】在使用平台资源，譬如短信、邮件、电话、下单、支付，必须实现正确的防重放限制， 如数量限制、疲劳度控制、验证码校验，避免被滥刷、资损。 说明: 如注册时发送验证码到手机，如果没有限制次数和频率，那么可以利用此功能骚扰到其 它用户，并造成短信平台资源浪费。

8.【推荐】发贴、评论、发送即时消息等用户生成内容的场景必须实现防刷、文本内容违禁词过 滤等风控策略。



