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



