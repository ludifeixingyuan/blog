# 设计模式

## 7个原则

单一职责原则【SINGLE RESPONSIBILITY PRINCIPLE】: 一个类负责一项职责。

里氏替换原则【LISKOV SUBSTITUTION PRINCIPLE】: 继承与派生的规则。

依赖倒置原则【DEPENDENCE INVERSION PRINCIPLE】: 高层模块不应该依赖低层模块，二者都应该依赖其抽象；抽象不应该依赖细节；细节应该依赖抽象。即针对接口编程，不要针对实现编程。

接口隔离原则【INTERFACE SEGREGATION PRINCIPLE】: 建立单一接口，不要建立庞大臃肿的接口，尽量细化接口，接口中的方法尽量少。

迪米特法则【LOW OF DEMETER】: 低耦合，高内聚。

开闭原则【OPEN CLOSE PRINCIPLE】: 一个软件实体如类、模块和函数应该对扩展开放，对修改关闭。

组合/聚合复用原则【Composition/Aggregation Reuse Principle(CARP) 】: 尽量使用组合和聚合少使用继承的关系来达到复用的原则。

## 24大设计模式

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/design-pattern.png)

> 创建型（6种）：~~单例模式(Singleton pattern)~~、~~简单工厂(Simple Factory)~~、~~工厂方法(Factory Method)~~、~~抽象工厂(Abstract Factory)~~、建造者(Builder)、原型模式(Prototype)
>
> 结构型（7种）：~~外观(Facade)~~、适配器(Adapter)、桥接(Bridge)、组合(Composite)、装饰(Decorator)、享元(Flyweight)、~~代理(Proxy)~~
>
> 行为型（11种）：~~责任链(Chain Of Responsibility)~~、~~策略(Strategy)~~、~~模板方法(Template Method)~~、命令模式(Command)、观察者(Observer)、访问者(Visitor)、状态(State)、解释器(Interpreter)、迭代器(Iterator)、中介者(Mediator)、备忘录(Memento)

> **第一步：创建型设计模式**

- 创建型 - 单例模式(Singleton pattern)

  - 单例模式(Singleton pattern): 确保一个类只有一个实例，并提供该实例的全局访问点, 本文介绍6中常用的实现方式

    1)懒汉式-线程不安全

    2)饿汉式-线程安全

  ````java
  private static Singleton uniqueInstance = new Singleton()
  ````

  ​		3)懒汉式-线程安全（synchronized）

  ​		4)懒汉式-双重校验锁

  ​		5)静态内部类实现

  > ​	当 Singleton 类加载时，静态内部类 SingletonHolder 没有被加载进内存。只有当调用 `getUniqueInstance()` 方法从而触发 `SingletonHolder.INSTANCE` 时 SingletonHolder 才会被加载，此时初始化 INSTANCE 实例。
  >
  > 这种方式不仅具有延迟初始化的好处，而且由虚拟机提供了对线程安全的支持。

  ````java
  public class Singleton {
      private Singleton() {
      }
      private static class SingletonHolder {
          private static final Singleton INSTANCE = new Singleton();
      }
      public static Singleton getUniqueInstance() {
          return SingletonHolder.INSTANCE;
      }
  }
  ````

  6)枚举实现

  ```` java
  public enum Singleton {
      uniqueInstance;
  }
  ````

- 创建型 - 简单工厂(Simple Factory)

  - 简单工厂(Simple Factory)，它把实例化的操作单独放到一个类中，这个类就成为简单工厂类，让简单工厂类来决定应该用哪个具体子类来实例化，这样做能把客户类和具体子类的实现解耦，客户类不再需要知道有哪些子类以及应当实例化哪个子类

    ````java
    public interface Product {
    }
    ````

    ````java
    public class ConcreteProduct implements Product {
    }
    ````

    ````java
    public class SimpleFactory {
        public Product createProduct(int type) {
            if (type == 1) {
                return new ConcreteProduct1();
            } else if (type == 2) {
                return new ConcreteProduct2();
            }
            return new ConcreteProduct();
        }
    }
    ````

- 创建型 - 工厂方法(Factory Method)

  - 工厂方法(Factory Method)，它定义了一个创建对象的接口，但由子类决定要实例化哪个类。工厂方法把实例化操作推迟到子类

    ````java
    public abstract class Factory {
        abstract public Product factoryMethod();
        public void doSomething() {
            Product product = factoryMethod();
            // do something with the product
        }
    }
    ````

    ````java
    public class ConcreteFactory extends Factory {
        public Product factoryMethod() {
            return new ConcreteProduct();
        }
    }
    ````

    ````java
    public class ConcreteFactory1 extends Factory {
        public Product factoryMethod() {
            return new ConcreteProduct1();
        }
    }
    ````

- 创建型 - 抽象工厂(Abstract Factory)

  - 抽象工厂(Abstract Factory)，抽象工厂模式创建的是对象家族，也就是很多对象而不是一个对象，并且这些对象是相关的，也就是说必须一起创建出来。而工厂方法模式只是用于创建一个对象，这和抽象工厂模式有很大不同

    ````java
    public class AbstractProductA {
    }
    ````

    ````java
    public class AbstractProductB {
    }
    ````

    ````java
    public class ProductA1 extends AbstractProductA {
    }
    ````

    ````java
    public class ProductA2 extends AbstractProductA {
    }
    ````

    ````java
    public class ProductB1 extends AbstractProductB {
    }
    ````

    ````java
    public class ProductB2 extends AbstractProductB {
    }
    ````

    ````java
    public abstract class AbstractFactory {
        abstract AbstractProductA createProductA();
        abstract AbstractProductB createProductB();
    }
    ````

    ````java
    public class ConcreteFactory1 extends AbstractFactory {
        AbstractProductA createProductA() {
            return new ProductA1();
        }
    
        AbstractProductB createProductB() {
            return new ProductB1();
        }
    }
    ````

    ````java
    public class ConcreteFactory2 extends AbstractFactory {
        AbstractProductA createProductA() {
            return new ProductA2();
        }
    
        AbstractProductB createProductB() {
            return new ProductB2();
        }
    }
    ````

    ````java
    public class Client {
        public static void main(String[] args) {
            AbstractFactory abstractFactory = new ConcreteFactory1();
            AbstractProductA productA = abstractFactory.createProductA();
            AbstractProductB productB = abstractFactory.createProductB();
            // do something with productA and productB
        }
    }
    ````

- 创建型 - 生成器(Builder)

  - 生成器(Builder)，封装一个对象的构造过程，并允许按步骤构造

    以下是一个简易的 StringBuilder 实现，参考了 JDK 1.8 源码。

    ````java
    public class AbstractStringBuilder {
        protected char[] value;
    
        protected int count;
    
        public AbstractStringBuilder(int capacity) {
            count = 0;
            value = new char[capacity];
        }
    
        public AbstractStringBuilder append(char c) {
            ensureCapacityInternal(count + 1);
            value[count++] = c;
            return this;
        }
    
        private void ensureCapacityInternal(int minimumCapacity) {
            // overflow-conscious code
            if (minimumCapacity - value.length > 0)
                expandCapacity(minimumCapacity);
        }
    
        void expandCapacity(int minimumCapacity) {
            int newCapacity = value.length * 2 + 2;
            if (newCapacity - minimumCapacity < 0)
                newCapacity = minimumCapacity;
            if (newCapacity < 0) {
                if (minimumCapacity < 0) // overflow
                    throw new OutOfMemoryError();
                newCapacity = Integer.MAX_VALUE;
            }
            value = Arrays.copyOf(value, newCapacity);
        }
    }
    ````

    ````java
    public class StringBuilder extends AbstractStringBuilder {
        public StringBuilder() {
            super(16);
        }
    
        @Override
        public String toString() {
            // Create a copy, don't share the array
            return new String(value, 0, count);
        }
    }
    ````

    ````java
    public class Client {
        public static void main(String[] args) {
            StringBuilder sb = new StringBuilder();
            final int count = 26;
            for (int i = 0; i < count; i++) {
                sb.append((char) ('a' + i));
            }
            System.out.println(sb.toString());
        }
    }
    ````

- 创建型 - 原型模式(Prototype)

  - 原型模式(Prototype)，使用原型实例指定要创建对象的类型，通过复制这个原型来创建新对象

    ````java
    public abstract class Prototype {
        abstract Prototype myClone();
    }
    ````

    ````java
    public class ConcretePrototype extends Prototype {
    
        private String filed;
    
        public ConcretePrototype(String filed) {
            this.filed = filed;
        }
    
        @Override
        Prototype myClone() {
            return new ConcretePrototype(filed);
        }
    
        @Override
        public String toString() {
            return filed;
        }
    }
    ````

    ````java
    public class Client {
        public static void main(String[] args) {
            Prototype prototype = new ConcretePrototype("abc");
            Prototype clone = prototype.myClone();
            System.out.println(clone.toString());
        }
    }
    ````

> **第二步：结构型设计模式**

- 结构型 - 外观(Facade)

  - 外观模式(Facade pattern)，它提供了一个统一的接口，用来访问子系统中的一群接口，从而让子系统更容易使用

    观看电影需要操作很多电器，使用外观模式实现一键看电影功能。

    ````java
    public class SubSystem {
        public void turnOnTV() {
            System.out.println("turnOnTV()");
        }
    
        public void setCD(String cd) {
            System.out.println("setCD( " + cd + " )");
        }
    
        public void starWatching(){
            System.out.println("starWatching()");
        }
    }
    ````

    ````java
    public class Facade {
        private SubSystem subSystem = new SubSystem();
    
        public void watchMovie() {
            subSystem.turnOnTV();
            subSystem.setCD("a movie");
            subSystem.starWatching();
        }
    }
    ````

    ````java
    public class Client {
        public static void main(String[] args) {
            Facade facade = new Facade();
            facade.watchMovie();
        }
    }
    ````

- 结构型 - 适配器(Adapter)

  - 适配器模式(Adapter pattern): 将一个类的接口, 转换成客户期望的另一个接口。 适配器让原本接口不兼容的类可以合作无间。 对象适配器使用组合, 类适配器使用多重继承

    鸭子(Duck)和火鸡(Turkey)拥有不同的叫声，Duck 的叫声调用 quack() 方法，而 Turkey 调用 gobble() 方法。 要求将 Turkey 的 gobble() 方法适配成 Duck 的 quack() 方法，从而让火鸡冒充鸭子！

    ````java
    public interface Duck {
        void quack();
    }
    ````

    ````java
    public interface Turkey {
        void gobble();
    }
    ````

    ````java
    public class WildTurkey implements Turkey {
        @Override
        public void gobble() {
            System.out.println("gobble!");
        }
    }
    ````

    ````java
    public class TurkeyAdapter implements Duck {
        Turkey turkey;
    
        public TurkeyAdapter(Turkey turkey) {
            this.turkey = turkey;
        }
    
        @Override
        public void quack() {
            turkey.gobble();
        }
    }
    ````

    ````java
    public class Client {
        public static void main(String[] args) {
            Turkey turkey = new WildTurkey();
            Duck duck = new TurkeyAdapter(turkey);
            duck.quack();
        }
    }
    ````

- 结构型 - 桥接(Bridge)

  - 桥接模式(Bridge pattern): 使用桥接模式通过将实现和抽象放在两个不同的类层次中而使它们可以独立改变

    RemoteControl 表示遥控器，指代 Abstraction。

    TV 表示电视，指代 Implementor。

    桥接模式将遥控器和电视分离开来，从而可以独立改变遥控器或者电视的实现。

    ````java 
    public abstract class TV {
        public abstract void on();
    
        public abstract void off();
    
        public abstract void tuneChannel();
    }
    ````

    ````java 
    public class Sony extends TV {
        @Override
        public void on() {
            System.out.println("Sony.on()");
        }
    
        @Override
        public void off() {
            System.out.println("Sony.off()");
        }
    
        @Override
        public void tuneChannel() {
            System.out.println("Sony.tuneChannel()");
        }
    }
    ````

    ````java 
    public class RCA extends TV {
        @Override
        public void on() {
            System.out.println("RCA.on()");
        }
    
        @Override
        public void off() {
            System.out.println("RCA.off()");
        }
    
        @Override
        public void tuneChannel() {
            System.out.println("RCA.tuneChannel()");
        }
    }
    ````

    ````java 
    public abstract class RemoteControl {
        protected TV tv;
    
        public RemoteControl(TV tv) {
            this.tv = tv;
        }
    
        public abstract void on();
    
        public abstract void off();
    
        public abstract void tuneChannel();
    }
    ````

    ````java 
    public class ConcreteRemoteControl1 extends RemoteControl {
        public ConcreteRemoteControl1(TV tv) {
            super(tv);
        }
    
        @Override
        public void on() {
            System.out.println("ConcreteRemoteControl1.on()");
            tv.on();
        }
    
        @Override
        public void off() {
            System.out.println("ConcreteRemoteControl1.off()");
            tv.off();
        }
    
        @Override
        public void tuneChannel() {
            System.out.println("ConcreteRemoteControl1.tuneChannel()");
            tv.tuneChannel();
        }
    }
    ````

    ````java 
    public class ConcreteRemoteControl2 extends RemoteControl {
        public ConcreteRemoteControl2(TV tv) {
            super(tv);
        }
    
        @Override
        public void on() {
            System.out.println("ConcreteRemoteControl2.on()");
            tv.on();
        }
    
        @Override
        public void off() {
            System.out.println("ConcreteRemoteControl2.off()");
            tv.off();
        }
    
        @Override
        public void tuneChannel() {
            System.out.println("ConcreteRemoteControl2.tuneChannel()");
            tv.tuneChannel();
        }
    }
    ````

    ````java 
    public class Client {
        public static void main(String[] args) {
            RemoteControl remoteControl1 = new ConcreteRemoteControl1(new RCA());
            remoteControl1.on();
            remoteControl1.off();
            remoteControl1.tuneChannel();
        }
    }
    ````

- 结构型 - 组合(Composite)

  - 组合模式(composite pattern): 允许你将对象组合成树形结构来表现"整体/部分"层次结构. 组合能让客户以一致的方式处理个别对象以及对象组合

    ````java
    public abstract class Component {
        protected String name;
    
        public Component(String name) {
            this.name = name;
        }
    
        public void print() {
            print(0);
        }
    
        abstract void print(int level);
    
        abstract public void add(Component component);
    
        abstract public void remove(Component component);
    }
    ````

    ````java
    public class Composite extends Component {
    
        private List<Component> child;
    
        public Composite(String name) {
            super(name);
            child = new ArrayList<>();
        }
    
        @Override
        void print(int level) {
            for (int i = 0; i < level; i++) {
                System.out.print("--");
            }
            System.out.println("Composite:" + name);
            for (Component component : child) {
                component.print(level + 1);
            }
        }
    
        @Override
        public void add(Component component) {
            child.add(component);
        }
    
        @Override
        public void remove(Component component) {
            child.remove(component);
        }
    }
    ````

    ````java
    public class Leaf extends Component {
        public Leaf(String name) {
            super(name);
        }
    
        @Override
        void print(int level) {
            for (int i = 0; i < level; i++) {
                System.out.print("--");
            }
            System.out.println("left:" + name);
        }
    
        @Override
        public void add(Component component) {
            throw new UnsupportedOperationException(); // 牺牲透明性换取单一职责原则，这样就不用考虑是叶子节点还是组合节点
        }
    
        @Override
        public void remove(Component component) {
            throw new UnsupportedOperationException();
        }
    }
    ````

    ````java
    public class Client {
        public static void main(String[] args) {
            Composite root = new Composite("root");
            Component node1 = new Leaf("1");
            Component node2 = new Composite("2");
            Component node3 = new Leaf("3");
            root.add(node1);
            root.add(node2);
            root.add(node3);
            Component node21 = new Leaf("21");
            Component node22 = new Composite("22");
            node2.add(node21);
            node2.add(node22);
            Component node221 = new Leaf("221");
            node22.add(node221);
            root.print();
        }
    }
    ````

    ````java
    Composite:root
    --left:1
    --Composite:2
    ----left:21
    ----Composite:22
    ------left:221
    --left:3
    ````

- 结构型 - 装饰(Decorator)

  - 装饰者模式(decorator pattern): 动态地将责任附加到对象上, 若要扩展功能, 装饰者提供了比继承更有弹性的替代方案

    设计不同种类的饮料，饮料可以添加配料，比如可以添加牛奶，并且支持动态添加新配料。每增加一种配料，该饮料的价格就会增加，要求计算一种饮料的价格。

    ````java
    public interface Beverage {
        double cost();
    }
    ````

    ````java
    public class DarkRoast implements Beverage {
        @Override
        public double cost() {
            return 1;
        }
    }
    ````

    ````java
    public class HouseBlend implements Beverage {
        @Override
        public double cost() {
            return 1;
        }
    }
    ````

    ````java
    public abstract class CondimentDecorator implements Beverage {
        protected Beverage beverage;
    }
    ````

    ````java
    public class Mocha extends CondimentDecorator {
    
        public Mocha(Beverage beverage) {
            this.beverage = beverage;
        }
    
        @Override
        public double cost() {
            return 1 + beverage.cost();
        }
    }
    ````

    ````java
    public class Client {
        public static void main(String[] args) {
            Beverage beverage = new HouseBlend();
            beverage = new Mocha(beverage);
            beverage = new Milk(beverage);
            System.out.println(beverage.cost());
        }
    }
    ````

    ````java
    输出结果：3.0
    ````

- 结构型 - 享元(Flyweight)

  - 享元模式(Flyweight Pattern): 利用共享的方式来支持大量细粒度的对象，这些对象一部分内部状态是相同的。 它让某个类的一个实例能用来提供许多"虚拟实例"

    ````java
    public interface Flyweight {
        void doOperation(String extrinsicState);
    }
    ````

    ````java
    public class ConcreteFlyweight implements Flyweight {
    
        private String intrinsicState;
    
        public ConcreteFlyweight(String intrinsicState) {
            this.intrinsicState = intrinsicState;
        }
    
        @Override
        public void doOperation(String extrinsicState) {
            System.out.println("Object address: " + System.identityHashCode(this));
            System.out.println("IntrinsicState: " + intrinsicState);
            System.out.println("ExtrinsicState: " + extrinsicState);
        }
    }
    ````

    ````java
    public class FlyweightFactory {
    
        private HashMap<String, Flyweight> flyweights = new HashMap<>();
    
        Flyweight getFlyweight(String intrinsicState) {
            if (!flyweights.containsKey(intrinsicState)) {
                Flyweight flyweight = new ConcreteFlyweight(intrinsicState);
                flyweights.put(intrinsicState, flyweight);
            }
            return flyweights.get(intrinsicState);
        }
    }
    ````

    ````java
    public class Client {
        public static void main(String[] args) {
            FlyweightFactory factory = new FlyweightFactory();
            Flyweight flyweight1 = factory.getFlyweight("aa");
            Flyweight flyweight2 = factory.getFlyweight("aa");
            flyweight1.doOperation("x");
            flyweight2.doOperation("y");
        }
    }
    ````

    ````java
    Object address: 1163157884
    IntrinsicState: aa
    ExtrinsicState: x
    Object address: 1163157884
    IntrinsicState: aa
    ExtrinsicState: y
    ````

- 结构型 - 代理(Proxy)

  - 代理模式(Proxy pattern): 为另一个对象提供一个替身或占位符以控制对这个对象的访问

    代理有以下四类:

    - 远程代理(Remote Proxy): 控制对远程对象(不同地址空间)的访问，它负责将请求及其参数进行编码，并向不同地址空间中的对象发送已经编码的请求。

    - 虚拟代理(Virtual Proxy): 根据需要创建开销很大的对象，它可以缓存实体的附加信息，以便延迟对它的访问，例如在网站加载一个很大图片时，不能马上完成，可以用虚拟代理缓存图片的大小信息，然后生成一张临时图片代替原始图片。
    - 保护代理(Protection Proxy): 按权限控制对象的访问，它负责检查调用者是否具有实现一个请求所必须的访问权限。
    - 智能代理(Smart Reference): 取代了简单的指针，它在访问对象时执行一些附加操作: 记录对象的引用次数；当第一次引用一个持久化对象时，将它装入内存；在访问一个实际对象前，检查是否已经锁定了它，以确保其它对象不能改变它。

    以下是一个虚拟代理的实现，模拟了图片延迟加载的情况下使用与图片大小相等的临时内容去替换原始图片，直到图片加载完成才将图片显示出来。

    ````JAVA
    public interface Image {
        void showImage();
    }
    ````

    ````java
    public class HighResolutionImage implements Image {
    
        private URL imageURL;
        private long startTime;
        private int height;
        private int width;
    
        public int getHeight() {
            return height;
        }
    
        public int getWidth() {
            return width;
        }
    
        public HighResolutionImage(URL imageURL) {
            this.imageURL = imageURL;
            this.startTime = System.currentTimeMillis();
            this.width = 600;
            this.height = 600;
        }
    
        public boolean isLoad() {
            // 模拟图片加载，延迟 3s 加载完成
            long endTime = System.currentTimeMillis();
            return endTime - startTime > 3000;
        }
    
        @Override
        public void showImage() {
            System.out.println("Real Image: " + imageURL);
        }
    }
    ````

    ````java
    public class ImageProxy implements Image {
        private HighResolutionImage highResolutionImage;
    
        public ImageProxy(HighResolutionImage highResolutionImage) {
            this.highResolutionImage = highResolutionImage;
        }
    
        @Override
        public void showImage() {
            while (!highResolutionImage.isLoad()) {
                try {
                    System.out.println("Temp Image: " + highResolutionImage.getWidth() + " " + highResolutionImage.getHeight());
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            highResolutionImage.showImage();
        }
    }
    ````

    ````java
    public class ImageViewer {
        public static void main(String[] args) throws Exception {
            String image = "http://image.jpg";
            URL url = new URL(image);
            HighResolutionImage highResolutionImage = new HighResolutionImage(url);
            ImageProxy imageProxy = new ImageProxy(highResolutionImage);
            imageProxy.showImage();
        }
    }
    ````

> **第三步：行为型设计模式**

- 行为型 - 责任链(Chain Of Responsibility)

  - 责任链模式(Chain of responsibility pattern): 通过责任链模式, 你可以为某个请求创建一个对象链. 每个对象依序检查此请求并对其进行处理或者将它传给链中的下一个对象

    ````java
    public abstract class Handler {
        protected Handler successor;
    
        public Handler(Handler successor) {
            this.successor = successor;
        }
    
        protected abstract void handleRequest(Request request);
    }
    ````

    ````java
    public class ConcreteHandler1 extends Handler {
        public ConcreteHandler1(Handler successor) {
            super(successor);
        }
    
        @Override
        protected void handleRequest(Request request) {
            if (request.getType() == RequestType.type1) {
                System.out.println(request.getName() + " is handle by ConcreteHandler1");
                return;
            }
            if (successor != null) {
                successor.handleRequest(request);
            }
        }
    }
    ````

    ````java
    public class ConcreteHandler2 extends Handler{
        public ConcreteHandler2(Handler successor) {
            super(successor);
        }
    
        @Override
        protected void handleRequest(Request request) {
            if (request.getType() == RequestType.type2) {
                System.out.println(request.getName() + " is handle by ConcreteHandler2");
                return;
            }
            if (successor != null) {
                successor.handleRequest(request);
            }
        }
    }
    ````

    ````java
    public class Request {
        private RequestType type;
        private String name;
    
        public Request(RequestType type, String name) {
            this.type = type;
            this.name = name;
        }
    
        public RequestType getType() {
            return type;
        }
    
        public String getName() {
            return name;
        }
    }
    ````

    ````java
    public enum RequestType {
        type1, type2
    }
    ````

    ````java
    public class Client {
        public static void main(String[] args) {
            Handler handler1 = new ConcreteHandler1(null);
            Handler handler2 = new ConcreteHandler2(handler1);
            Request request1 = new Request(RequestType.type1, "request1");
            handler2.handleRequest(request1);
            Request request2 = new Request(RequestType.type2, "request2");
            handler2.handleRequest(request2);
        }
    }
    ````

    ````java
    request1 is handle by ConcreteHandler1
    request2 is handle by ConcreteHandler2

- 行为型 - 策略(Strategy)

  - 策略模式(strategy pattern): 定义了算法族, 分别封闭起来, 让它们之间可以互相替换, 此模式让算法的变化独立于使用算法的客户

    设计一个鸭子，它可以动态地改变叫声。这里的算法族是鸭子的叫声行为。

    ````java
    public interface QuackBehavior {
        void quack();
    }
    ````

    ````java
    public class Quack implements QuackBehavior {
        @Override
        public void quack() {
            System.out.println("quack!");
        }
    }
    ````

    ````java
    public class Squeak implements QuackBehavior{
        @Override
        public void quack() {
            System.out.println("squeak!");
        }
    }
    ````

    ````java
    public class Duck {
        private QuackBehavior quackBehavior;
    
        public void performQuack() {
            if (quackBehavior != null) {
                quackBehavior.quack();
            }
        }
    
        public void setQuackBehavior(QuackBehavior quackBehavior) {
            this.quackBehavior = quackBehavior;
        }
    }
    ````

    ````java
    public class Client {
        public static void main(String[] args) {
            Duck duck = new Duck();
            duck.setQuackBehavior(new Squeak());
            duck.performQuack();
            duck.setQuackBehavior(new Quack());
            duck.performQuack();
        }
    }
    ````

    ````java
    squeak!
    quack!
    ````

- 行为型 - 模板方法(Template Method)

  - 模板方法模式(Template pattern): 在一个方法中定义一个算法的骨架, 而将一些步骤延迟到子类中. 模板方法使得子类可以在不改变算法结构的情况下, 重新定义算法中的某些步骤

    冲咖啡和冲茶都有类似的流程，但是某些步骤会有点不一样，要求复用那些相同步骤的代码。

    ````java
    public abstract class CaffeineBeverage {
    
        final void prepareRecipe() {
            boilWater();
            brew();
            pourInCup();
            addCondiments();
        }
    
        abstract void brew();
    
        abstract void addCondiments();
    
        void boilWater() {
            System.out.println("boilWater");
        }
    
        void pourInCup() {
            System.out.println("pourInCup");
        }
    }
    ````

    ````java
    public class Coffee extends CaffeineBeverage {
        @Override
        void brew() {
            System.out.println("Coffee.brew");
        }
    
        @Override
        void addCondiments() {
            System.out.println("Coffee.addCondiments");
        }
    }
    ````

    ````java
    public class Tea extends CaffeineBeverage {
        @Override
        void brew() {
            System.out.println("Tea.brew");
        }
    
        @Override
        void addCondiments() {
            System.out.println("Tea.addCondiments");
        }
    }
    ````

    ````java
    public class Client {
        public static void main(String[] args) {
            CaffeineBeverage caffeineBeverage = new Coffee();
            caffeineBeverage.prepareRecipe();
            System.out.println("-----------");
            caffeineBeverage = new Tea();
            caffeineBeverage.prepareRecipe();
        }
    }
    ````

    ````tex
    boilWater
    Coffee.brew
    pourInCup
    Coffee.addCondiments
    -----------
    boilWater
    Tea.brew
    pourInCup
    Tea.addCondiments
    ````

- 行为型 - 命令模式(Command)

  - 命令模式(Command pattern): 将"请求"封闭成对象, 以便使用不同的请求,队列或者日志来参数化其他对象. 命令模式也支持可撤销的操作

    设计一个遥控器，可以控制电灯开关。

    ````java
    public interface Command {
        void execute();
    }
    ````

    ````java
    public class LightOnCommand implements Command {
        Light light;
    
        public LightOnCommand(Light light) {
            this.light = light;
        }
    
        @Override
        public void execute() {
            light.on();
        }
    }
    ````

    ````java
    public class LightOffCommand implements Command {
        Light light;
    
        public LightOffCommand(Light light) {
            this.light = light;
        }
    
        @Override
        public void execute() {
            light.off();
        }
    }
    ````

    ````java
    public class Light {
    
        public void on() {
            System.out.println("Light is on!");
        }
    
        public void off() {
            System.out.println("Light is off!");
        }
    }
    ````

    ````java
    /**
     * 遥控器
     */
    public class Invoker {
        private Command[] onCommands;
        private Command[] offCommands;
        private final int slotNum = 7;
    
        public Invoker() {
            this.onCommands = new Command[slotNum];
            this.offCommands = new Command[slotNum];
        }
    
        public void setOnCommand(Command command, int slot) {
            onCommands[slot] = command;
        }
    
        public void setOffCommand(Command command, int slot) {
            offCommands[slot] = command;
        }
    
        public void onButtonWasPushed(int slot) {
            onCommands[slot].execute();
        }
    
        public void offButtonWasPushed(int slot) {
            offCommands[slot].execute();
        }
    }
    ````

    ````java
    public class Client {
        public static void main(String[] args) {
            Invoker invoker = new Invoker();
            Light light = new Light();
            Command lightOnCommand = new LightOnCommand(light);
            Command lightOffCommand = new LightOffCommand(light);
            invoker.setOnCommand(lightOnCommand, 0);
            invoker.setOffCommand(lightOffCommand, 0);
            invoker.onButtonWasPushed(0);
            invoker.offButtonWasPushed(0);
        }
    }
    ````

    ````tex
    Light is on!
    Light is off!
    ````

- 行为型 - 观察者(Observer)

  - 观察者模式(observer pattern): 在对象之间定义一对多的依赖, 这样一来, 当一个对象改变状态, 依赖它的对象都会收到通知, 并自动更新

    定义对象之间的一对多依赖，当一个对象状态改变时，它的所有依赖都会收到通知并且自动更新状态。

    主题(Subject)是被观察的对象，而其所有依赖者(Observer)称为观察者。

    天气数据布告板会在天气信息发生改变时更新其内容，布告板有多个，并且在将来会继续增加。

    ````java
    public interface Subject {
        void resisterObserver(Observer o);
    
        void removeObserver(Observer o);
    
        void notifyObserver();
    }
    ````

    ````java
    public class WeatherData implements Subject {
        private List<Observer> observers;
        private float temperature;
        private float humidity;
        private float pressure;
    
        public WeatherData() {
            observers = new ArrayList<>();
        }
    
        public void setMeasurements(float temperature, float humidity, float pressure) {
            this.temperature = temperature;
            this.humidity = humidity;
            this.pressure = pressure;
            notifyObserver();
        }
    
        @Override
        public void resisterObserver(Observer o) {
            observers.add(o);
        }
    
        @Override
        public void removeObserver(Observer o) {
            int i = observers.indexOf(o);
            if (i >= 0) {
                observers.remove(i);
            }
        }
    
        @Override
        public void notifyObserver() {
            for (Observer o : observers) {
                o.update(temperature, humidity, pressure);
            }
        }
    }
    ````

    ````java
    public interface Observer {
        void update(float temp, float humidity, float pressure);
    }
    ````

    ````java
    public class StatisticsDisplay implements Observer {
    
        public StatisticsDisplay(Subject weatherData) {
            weatherData.resisterObserver(this);
        }
    
        @Override
        public void update(float temp, float humidity, float pressure) {
            System.out.println("StatisticsDisplay.update: " + temp + " " + humidity + " " + pressure);
        }
    }
    ````

    ````java
    public class CurrentConditionsDisplay implements Observer {
    
        public CurrentConditionsDisplay(Subject weatherData) {
            weatherData.resisterObserver(this);
        }
    
        @Override
        public void update(float temp, float humidity, float pressure) {
            System.out.println("CurrentConditionsDisplay.update: " + temp + " " + humidity + " " + pressure);
        }
    }
    ````

    ````java
    public class WeatherStation {
        public static void main(String[] args) {
            WeatherData weatherData = new WeatherData();
            CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
            StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
    
            weatherData.setMeasurements(0, 0, 0);
            weatherData.setMeasurements(1, 1, 1);
        }
    }
    ````

    ````tex
    CurrentConditionsDisplay.update: 0.0 0.0 0.0
    StatisticsDisplay.update: 0.0 0.0 0.0
    CurrentConditionsDisplay.update: 1.0 1.0 1.0
    StatisticsDisplay.update: 1.0 1.0 1.0
    ````

- 行为型 - 访问者(Visitor)

  - 访问者模式(visitor pattern): 当你想要为一个对象的组合增加新的能力, 且封装并不重要时, 就使用访问者模式

    ````java
    public interface Element {
        void accept(Visitor visitor);
    }
    ````

    ````java
    class CustomerGroup {
    
        private List<Customer> customers = new ArrayList<>();
    
        void accept(Visitor visitor) {
            for (Customer customer : customers) {
                customer.accept(visitor);
            }
        }
    
        void addCustomer(Customer customer) {
            customers.add(customer);
        }
    }
    ````

    ````java
    public class Customer implements Element {
    
        private String name;
        private List<Order> orders = new ArrayList<>();
    
        Customer(String name) {
            this.name = name;
        }
    
        String getName() {
            return name;
        }
    
        void addOrder(Order order) {
            orders.add(order);
        }
    
        public void accept(Visitor visitor) {
            visitor.visit(this);
            for (Order order : orders) {
                order.accept(visitor);
            }
        }
    }
    ````

    ````java
    public class Order implements Element {
    
        private String name;
        private List<Item> items = new ArrayList();
    
        Order(String name) {
            this.name = name;
        }
    
        Order(String name, String itemName) {
            this.name = name;
            this.addItem(new Item(itemName));
        }
    
        String getName() {
            return name;
        }
    
        void addItem(Item item) {
            items.add(item);
        }
    
        public void accept(Visitor visitor) {
            visitor.visit(this);
    
            for (Item item : items) {
                item.accept(visitor);
            }
        }
    }
    ````

    ````java
    public class Item implements Element {
    
        private String name;
    
        Item(String name) {
            this.name = name;
        }
    
        String getName() {
            return name;
        }
    
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }
    ````

    ````java
    public interface Visitor {
        void visit(Customer customer);
    
        void visit(Order order);
    
        void visit(Item item);
    }
    ````

    ````java
    public class GeneralReport implements Visitor {
    
        private int customersNo;
        private int ordersNo;
        private int itemsNo;
    
        public void visit(Customer customer) {
            System.out.println(customer.getName());
            customersNo++;
        }
    
        public void visit(Order order) {
            System.out.println(order.getName());
            ordersNo++;
        }
    
        public void visit(Item item) {
            System.out.println(item.getName());
            itemsNo++;
        }
    
        public void displayResults() {
            System.out.println("Number of customers: " + customersNo);
            System.out.println("Number of orders:    " + ordersNo);
            System.out.println("Number of items:     " + itemsNo);
        }
    }
    ````

    ````java
    public class Client {
        public static void main(String[] args) {
            Customer customer1 = new Customer("customer1");
            customer1.addOrder(new Order("order1", "item1"));
            customer1.addOrder(new Order("order2", "item1"));
            customer1.addOrder(new Order("order3", "item1"));
    
            Order order = new Order("order_a");
            order.addItem(new Item("item_a1"));
            order.addItem(new Item("item_a2"));
            order.addItem(new Item("item_a3"));
            Customer customer2 = new Customer("customer2");
            customer2.addOrder(order);
    
            CustomerGroup customers = new CustomerGroup();
            customers.addCustomer(customer1);
            customers.addCustomer(customer2);
    
            GeneralReport visitor = new GeneralReport();
            customers.accept(visitor);
            visitor.displayResults();
        }
    }
    ````

    ````html
    customer1
    order1
    item1
    order2
    item1
    order3
    item1
    customer2
    order_a
    item_a1
    item_a2
    item_a3
    Number of customers: 2
    Number of orders:    4
    Number of items:     6
    ````

- 行为型 - 状态(State)

  - 状态模式(State pattern): 允许对象在内部状态改变时改变它的行为, 对象看起来好象改了它的类

    糖果销售机有多种状态，每种状态下销售机有不同的行为，状态可以发生转移，使得销售机的行为也发生改变。

    ````java
    public interface State {
        /**
         * 投入 25 分钱
         */
        void insertQuarter();
    
        /**
         * 退回 25 分钱
         */
        void ejectQuarter();
    
        /**
         * 转动曲柄
         */
        void turnCrank();
    
        /**
         * 发放糖果
         */
        void dispense();
    }
    ````

    ````java
    public class HasQuarterState implements State {
    
        private GumballMachine gumballMachine;
    
        public HasQuarterState(GumballMachine gumballMachine) {
            this.gumballMachine = gumballMachine;
        }
    
        @Override
        public void insertQuarter() {
            System.out.println("You can't insert another quarter");
        }
    
        @Override
        public void ejectQuarter() {
            System.out.println("Quarter returned");
            gumballMachine.setState(gumballMachine.getNoQuarterState());
        }
    
        @Override
        public void turnCrank() {
            System.out.println("You turned...");
            gumballMachine.setState(gumballMachine.getSoldState());
        }
    
        @Override
        public void dispense() {
            System.out.println("No gumball dispensed");
        }
    }
    ````

    ````java
    public class NoQuarterState implements State {
    
        GumballMachine gumballMachine;
    
        public NoQuarterState(GumballMachine gumballMachine) {
            this.gumballMachine = gumballMachine;
        }
    
        @Override
        public void insertQuarter() {
            System.out.println("You insert a quarter");
            gumballMachine.setState(gumballMachine.getHasQuarterState());
        }
    
        @Override
        public void ejectQuarter() {
            System.out.println("You haven't insert a quarter");
        }
    
        @Override
        public void turnCrank() {
            System.out.println("You turned, but there's no quarter");
        }
    
        @Override
        public void dispense() {
            System.out.println("You need to pay first");
        }
    }
    ````

    ````java
    public class SoldOutState implements State {
    
        GumballMachine gumballMachine;
    
        public SoldOutState(GumballMachine gumballMachine) {
            this.gumballMachine = gumballMachine;
        }
    
        @Override
        public void insertQuarter() {
            System.out.println("You can't insert a quarter, the machine is sold out");
        }
    
        @Override
        public void ejectQuarter() {
            System.out.println("You can't eject, you haven't inserted a quarter yet");
        }
    
        @Override
        public void turnCrank() {
            System.out.println("You turned, but there are no gumballs");
        }
    
        @Override
        public void dispense() {
            System.out.println("No gumball dispensed");
        }
    }
    ````

    ````java
    public class SoldState implements State {
    
        GumballMachine gumballMachine;
    
        public SoldState(GumballMachine gumballMachine) {
            this.gumballMachine = gumballMachine;
        }
    
        @Override
        public void insertQuarter() {
            System.out.println("Please wait, we're already giving you a gumball");
        }
    
        @Override
        public void ejectQuarter() {
            System.out.println("Sorry, you already turned the crank");
        }
    
        @Override
        public void turnCrank() {
            System.out.println("Turning twice doesn't get you another gumball!");
        }
    
        @Override
        public void dispense() {
            gumballMachine.releaseBall();
            if (gumballMachine.getCount() > 0) {
                gumballMachine.setState(gumballMachine.getNoQuarterState());
            } else {
                System.out.println("Oops, out of gumballs");
                gumballMachine.setState(gumballMachine.getSoldOutState());
            }
        }
    }
    ````

    ````java
    public class GumballMachine {
    
        private State soldOutState;
        private State noQuarterState;
        private State hasQuarterState;
        private State soldState;
    
        private State state;
        private int count = 0;
    
        public GumballMachine(int numberGumballs) {
            count = numberGumballs;
            soldOutState = new SoldOutState(this);
            noQuarterState = new NoQuarterState(this);
            hasQuarterState = new HasQuarterState(this);
            soldState = new SoldState(this);
    
            if (numberGumballs > 0) {
                state = noQuarterState;
            } else {
                state = soldOutState;
            }
        }
    
        public void insertQuarter() {
            state.insertQuarter();
        }
    
        public void ejectQuarter() {
            state.ejectQuarter();
        }
    
        public void turnCrank() {
            state.turnCrank();
            state.dispense();
        }
    
        public void setState(State state) {
            this.state = state;
        }
    
        public void releaseBall() {
            System.out.println("A gumball comes rolling out the slot...");
            if (count != 0) {
                count -= 1;
            }
        }
    
        public State getSoldOutState() {
            return soldOutState;
        }
    
        public State getNoQuarterState() {
            return noQuarterState;
        }
    
        public State getHasQuarterState() {
            return hasQuarterState;
        }
    
        public State getSoldState() {
            return soldState;
        }
    
        public int getCount() {
            return count;
        }
    }
    ````

- 行为型 - 解释器(Interpreter)

  - 解释器模式(Interpreter pattern): 使用解释器模式为语言创建解释器，通常由语言的语法和语法分析来定义

    以下是一个规则检验器实现，具有 and 和 or 规则，通过规则可以构建一颗解析树，用来检验一个文本是否满足解析树定义的规则。

    例如一颗解析树为 D And (A Or (B C))，文本 "D A" 满足该解析树定义的规则。

    这里的 Context 指的是 String。

    ````java
    public abstract class Expression {
        public abstract boolean interpret(String str);
    }
    ````

    ````java
    public class TerminalExpression extends Expression {
    
        private String literal = null;
    
        public TerminalExpression(String str) {
            literal = str;
        }
    
        public boolean interpret(String str) {
            StringTokenizer st = new StringTokenizer(str);
            while (st.hasMoreTokens()) {
                String test = st.nextToken();
                if (test.equals(literal)) {
                    return true;
                }
            }
            return false;
        }
    }
    ````

    ````java
    public class AndExpression extends Expression {
    
        private Expression expression1 = null;
        private Expression expression2 = null;
    
        public AndExpression(Expression expression1, Expression expression2) {
            this.expression1 = expression1;
            this.expression2 = expression2;
        }
    
        public boolean interpret(String str) {
            return expression1.interpret(str) && expression2.interpret(str);
        }
    }
    ````

    ````java
    public class OrExpression extends Expression {
        private Expression expression1 = null;
        private Expression expression2 = null;
    
        public OrExpression(Expression expression1, Expression expression2) {
            this.expression1 = expression1;
            this.expression2 = expression2;
        }
    
        public boolean interpret(String str) {
            return expression1.interpret(str) || expression2.interpret(str);
        }
    }
    ````

    ````java
    public class Client {
    
        /**
         * 构建解析树
         */
        public static Expression buildInterpreterTree() {
            // Literal
            Expression terminal1 = new TerminalExpression("A");
            Expression terminal2 = new TerminalExpression("B");
            Expression terminal3 = new TerminalExpression("C");
            Expression terminal4 = new TerminalExpression("D");
            // B C
            Expression alternation1 = new OrExpression(terminal2, terminal3);
            // A Or (B C)
            Expression alternation2 = new OrExpression(terminal1, alternation1);
            // D And (A Or (B C))
            return new AndExpression(terminal4, alternation2);
        }
    
        public static void main(String[] args) {
            Expression define = buildInterpreterTree();
            String context1 = "D A";
            String context2 = "A B";
            System.out.println(define.interpret(context1));
            System.out.println(define.interpret(context2));
        }
    }
    ````

    ````html
    true
    false
    ````

- 行为型 - 迭代器(Iterator)

  - 迭代器模式(iterator pattern): 提供一种方法顺序访问一个聚合对象中的各个元素, 而又不暴露其内部的表示

    ````java
    public interface Aggregate {
        Iterator createIterator();
    }
    ````

    ````java
    public class ConcreteAggregate implements Aggregate {
    
        private Integer[] items;
    
        public ConcreteAggregate() {
            items = new Integer[10];
            for (int i = 0; i < items.length; i++) {
                items[i] = i;
            }
        }
    
        @Override
        public Iterator createIterator() {
            return new ConcreteIterator<Integer>(items);
        }
    }
    ````

    ````java
    public interface Iterator<Item> {
        Item next();
    
        boolean hasNext();
    }
    ````

    ````java
    public class ConcreteIterator<Item> implements Iterator {
    
        private Item[] items;
        private int position = 0;
    
        public ConcreteIterator(Item[] items) {
            this.items = items;
        }
    
        @Override
        public Object next() {
            return items[position++];
        }
    
        @Override
        public boolean hasNext() {
            return position < items.length;
        }
    }
    ````

    ````java
    public class Client {
        public static void main(String[] args) {
            Aggregate aggregate = new ConcreteAggregate();
            Iterator<Integer> iterator = aggregate.createIterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }
    }
    ````

- 行为型 - 中介者(Mediator)

  - 中介者模式(Mediator pattern) : 使用中介者模式来集中相关对象之间复杂的沟通和控制方式

    Alarm(闹钟)、CoffeePot(咖啡壶)、Calendar(日历)、Sprinkler(喷头)是一组相关的对象，在某个对象的事件产生时需要去操作其它对象，形成了下面这种依赖结构:

    ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/pics/82cfda3b-b53b-4c89-9fdb-26dd2db0cd02.jpg)

    使用中介者模式可以将复杂的依赖结构变成星形结构:

    ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/pics/5359cbf5-5a79-4874-9b17-f23c53c2cb80.jpg)

    ````java
    public abstract class Colleague {
        public abstract void onEvent(Mediator mediator);
    }
    ````

    ````java
    public class Alarm extends Colleague {
    
        @Override
        public void onEvent(Mediator mediator) {
            mediator.doEvent("alarm");
        }
    
        public void doAlarm() {
            System.out.println("doAlarm()");
        }
    }
    ````

    ````java
    public class CoffeePot extends Colleague {
        @Override
        public void onEvent(Mediator mediator) {
            mediator.doEvent("coffeePot");
        }
    
        public void doCoffeePot() {
            System.out.println("doCoffeePot()");
        }
    }
    ````

    ````java
    public class Calender extends Colleague {
        @Override
        public void onEvent(Mediator mediator) {
            mediator.doEvent("calender");
        }
    
        public void doCalender() {
            System.out.println("doCalender()");
        }
    }
    ````

    ````java
    public class Sprinkler extends Colleague {
        @Override
        public void onEvent(Mediator mediator) {
            mediator.doEvent("sprinkler");
        }
    
        public void doSprinkler() {
            System.out.println("doSprinkler()");
        }
    }
    ````

    ````java
    public abstract class Mediator {
        public abstract void doEvent(String eventType);
    }
    ````

    ````java
    public class ConcreteMediator extends Mediator {
        private Alarm alarm;
        private CoffeePot coffeePot;
        private Calender calender;
        private Sprinkler sprinkler;
    
        public ConcreteMediator(Alarm alarm, CoffeePot coffeePot, Calender calender, Sprinkler sprinkler) {
            this.alarm = alarm;
            this.coffeePot = coffeePot;
            this.calender = calender;
            this.sprinkler = sprinkler;
        }
    
        @Override
        public void doEvent(String eventType) {
            switch (eventType) {
                case "alarm":
                    doAlarmEvent();
                    break;
                case "coffeePot":
                    doCoffeePotEvent();
                    break;
                case "calender":
                    doCalenderEvent();
                    break;
                default:
                    doSprinklerEvent();
            }
        }
    
        public void doAlarmEvent() {
            alarm.doAlarm();
            coffeePot.doCoffeePot();
            calender.doCalender();
            sprinkler.doSprinkler();
        }
    
        public void doCoffeePotEvent() {
            // ...
        }
    
        public void doCalenderEvent() {
            // ...
        }
    
        public void doSprinklerEvent() {
            // ...
        }
    }
    ````

    ````java
    public class Client {
        public static void main(String[] args) {
            Alarm alarm = new Alarm();
            CoffeePot coffeePot = new CoffeePot();
            Calender calender = new Calender();
            Sprinkler sprinkler = new Sprinkler();
            Mediator mediator = new ConcreteMediator(alarm, coffeePot, calender, sprinkler);
            // 闹钟事件到达，调用中介者就可以操作相关对象
            alarm.onEvent(mediator);
        }
    }
    ````

    ````java
    doAlarm()
    doCoffeePot()
    doCalender()
    doSprinkler()
    ````

- 行为型 - 备忘录(Memento)

  - 备忘录模式(Memento pattern): 当你需要让对象返回之前的状态时(例如, 你的用户请求"撤销"), 你使用备忘录模

    以下实现了一个简单计算器程序，可以输入两个值，然后计算这两个值的和。备忘录模式允许将这两个值存储起来，然后在某个时刻用存储的状态进行恢复。

    ````java
    /**
     * Originator Interface
     */
    public interface Calculator {
    
        // Create Memento
        PreviousCalculationToCareTaker backupLastCalculation();
    
        // setMemento
        void restorePreviousCalculation(PreviousCalculationToCareTaker memento);
    
        int getCalculationResult();
    
        void setFirstNumber(int firstNumber);
    
        void setSecondNumber(int secondNumber);
    }
    ````

    ````java
    /**
     * Originator Implementation
     */
    public class CalculatorImp implements Calculator {
    
        private int firstNumber;
        private int secondNumber;
    
        @Override
        public PreviousCalculationToCareTaker backupLastCalculation() {
            // create a memento object used for restoring two numbers
            return new PreviousCalculationImp(firstNumber, secondNumber);
        }
    
        @Override
        public void restorePreviousCalculation(PreviousCalculationToCareTaker memento) {
            this.firstNumber = ((PreviousCalculationToOriginator) memento).getFirstNumber();
            this.secondNumber = ((PreviousCalculationToOriginator) memento).getSecondNumber();
        }
    
        @Override
        public int getCalculationResult() {
            // result is adding two numbers
            return firstNumber + secondNumber;
        }
    
        @Override
        public void setFirstNumber(int firstNumber) {
            this.firstNumber = firstNumber;
        }
    
        @Override
        public void setSecondNumber(int secondNumber) {
            this.secondNumber = secondNumber;
        }
    }
    ````

    ````java
    /**
     * Memento Interface to Originator
     *
     * This interface allows the originator to restore its state
     */
    public interface PreviousCalculationToOriginator {
        int getFirstNumber();
        int getSecondNumber();
    }
    ````

    ````java
    /**
     *  Memento interface to CalculatorOperator (Caretaker)
     */
    public interface PreviousCalculationToCareTaker {
        // no operations permitted for the caretaker
    }
    ````

    ````java
    /**
     * Memento Object Implementation
     * <p>
     * Note that this object implements both interfaces to Originator and CareTaker
     */
    public class PreviousCalculationImp implements PreviousCalculationToCareTaker,
            PreviousCalculationToOriginator {
    
        private int firstNumber;
        private int secondNumber;
    
        public PreviousCalculationImp(int firstNumber, int secondNumber) {
            this.firstNumber = firstNumber;
            this.secondNumber = secondNumber;
        }
    
        @Override
        public int getFirstNumber() {
            return firstNumber;
        }
    
        @Override
        public int getSecondNumber() {
            return secondNumber;
        }
    }
    ````

    ````java
    /**
     * CareTaker object
     */
    public class Client {
    
        public static void main(String[] args) {
            // program starts
            Calculator calculator = new CalculatorImp();
    
            // assume user enters two numbers
            calculator.setFirstNumber(10);
            calculator.setSecondNumber(100);
    
            // find result
            System.out.println(calculator.getCalculationResult());
    
            // Store result of this calculation in case of error
            PreviousCalculationToCareTaker memento = calculator.backupLastCalculation();
    
            // user enters a number
            calculator.setFirstNumber(17);
    
            // user enters a wrong second number and calculates result
            calculator.setSecondNumber(-290);
    
            // calculate result
            System.out.println(calculator.getCalculationResult());
    
            // user hits CTRL + Z to undo last operation and see last result
            calculator.restorePreviousCalculation(memento);
    
            // result restored
            System.out.println(calculator.getCalculationResult());
        }
    }
    ````

    ````
    110
    -273
    110
    ````

    







