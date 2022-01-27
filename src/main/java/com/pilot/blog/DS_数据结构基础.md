## 数据结构基础知识体系详解

### 知识体系

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/alg/alg-overview-x.png)

> 避免孤立的学习知识点，要关联学习。比如实际应用当中，我们经常使用的是`查找`和`排序`操作，这在我们的各种管理系统、数据库系统、操作系统等当中，十分常用，我们通过这个线索将知识点串联起来：

`数组`的下标寻址十分迅速，但计算机的内存是有限的，故数组的长度也是有限的，实际应用当中的数据往往十分庞大；而且无序数组的查找最坏情况需要遍历整个数组；后来人们提出了二分查找，二分查找要求数组的构造一定有序，二分法查找解决了普通数组查找复杂度过高的问题。任何一种数组无法解决的问题就是插入、删除操作比较复杂，因此，在一个增删查改比较频繁的数据结构中，数组不会被优先考虑

`普通链表`由于它的结构特点被证明根本不适合进行查找

`哈希表`是数组和链表的折中，同时它的设计依赖散列函数的设计，数组不能无限长、链表也不适合查找，所以也适合大规模的查找

`二叉查找树`因为可能退化成链表，同样不适合进行查找

`AVL树`是为了解决可能退化成链表问题，但是AVL树的旋转过程非常麻烦，因此插入和删除很慢，也就是构建AVL树比较麻烦

`红黑树`是平衡二叉树和AVL树的折中，因此是比较合适的。集合类中的Map、关联数组具有较高的查询效率，它们的底层实现就是红黑树。

`多路查找树` 是大规模数据存储中，实现索引查询这样一个实际背景下，树节点存储的元素数量是有限的(如果元素数量非常多的话，查找就退化成节点内部的线性查找了)，这样导致二叉查找树结构由于树的深度过大而造成磁盘I/O读写过于频繁，进而导致查询效率低下。

`B树`与自平衡二叉查找树不同，B树适用于读写相对大的数据块的存储系统，例如磁盘。它的应用是文件系统及部分非关系型数据库索引。

`B+树`在B树基础上，为叶子结点增加链表指针(B树+叶子有序链表)，所有关键字都在叶子结点 中出现，非叶子结点作为叶子结点的索引；B+树总是到叶子结点才命中。通常用于关系型数据库(如Mysql)和操作系统的文件系统中。

`B*树`是B+树的变体，在B+树的非根和非叶子结点再增加指向兄弟的指针, 在B+树基础上，为非叶子结点也增加链表指针，将结点的最低利用率从1/2提高到2/3。

`R树`是用来做空间数据存储的树状数据结构。例如给地理位置，矩形和多边形这类多维数据建立索引。

`Trie树`是自然语言处理中最常用的数据结构，很多字符串处理任务都会用到。Trie树本身是一种有限状态自动机，还有很多变体。什么模式匹配、正则表达式，都与这有关。

### OverView

> 线性表释义：n个具有相同特征的数据元素的有限序列

- 线性表 - 数组和矩阵
  - 数组是一种连续存储线性结构，元素类型相同，大小相等，数组是多维的，通过使用整型索引值来访问他们的元素，数组尺寸不能改变
- 线性表 - 链表
  - n个节点离散分配，彼此通过指针相连，每个节点只有一个前驱节点，每个节点只有一个后续节点，首节点没有前驱节点，尾节点没有后续节点。确定一个链表我们只需要头指针，通过头指针就可以把整个链表都能推出来
- 线性表(散列) - 哈希表
  - 散列表（Hash table，也叫哈希表），是根据关键码值(Key value)而直接进行访问的数据结构。也就是说，它通过把关键码值映射到表中一个位置来访问记录，以加快查找的速度。这个映射函数叫做散列函数，存放记录的数组叫做散列表。
- 线性表 - 栈和队列
  - 数组和链表都是线性存储结构的基础，栈和队列都是线性存储结构的应用

> **C. 数据结构之 逻辑结构：树**：然后理解数据结构中逻辑结构之树：二叉搜索树(BST)，平衡二叉树(AVL)，红黑树(R-B Tree)，哈夫曼树，前缀树(Trie)等。

- 树 - 基础和Overview
  - 树在数据结构中至关重要，这里展示树的整体知识体系结构和几种常见树类型
- 树 - 二叉搜索树(BST)
  - 本文主要介绍 二叉树中最基本的二叉查找树（Binary Search Tree），（又：二叉搜索树，二叉排序树）它或者是一棵空树，或者是具有下列性质的二叉树： 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值； 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值； 它的左、右子树也分别为二叉排序树。
- 树 - 平衡二叉树(AVL)
  - 平衡二叉树（Balanced Binary Tree）具有以下性质：它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。平衡二叉树的常用实现方法有红黑树、AVL、替罪羊树、Treap、伸展树等。 最小二叉平衡树的节点的公式如下 F(n)=F(n-1)+F(n-2)+1 这个类似于一个递归的数列，可以参考Fibonacci数列，1是根节点，F(n-1)是左子树的节点数量，F(n-2)是右子树的节点数量。
- 树 - 红黑树(R-B Tree)
  - 红黑树（Red Black Tree） 是一种自平衡二叉查找树，是在计算机科学中用到的一种数据结构，典型的用途是实现关联数组，是平衡二叉树和AVL树的折中。
- 树 - 哈夫曼树
  - 哈夫曼又称最优二叉树, 是一种带权路径长度最短的二叉树。
- 树 - 前缀树(Trie)
  - Trie，又称字典树、单词查找树或键树，是一种树形结构，是一种哈希树的变种。典型应用是用于统计，排序和保存大量的字符串（但不仅限于字符串），所以经常被搜索引擎系统用于文本词频统计。它的优点是：利用字符串的公共前缀来减少查询时间，最大限度地减少无谓的字符串比较，查询效率比哈希树高。

> **D. 数据结构之 逻辑结构：图**：最后理解数据结构中逻辑结构之图：图基础，图的遍历，最小生成树(Prim & Kruskal)，最短路径(Dijkstra & Frolyd)，拓扑排序(Topological sort)，AOE & 关键路径等。

- 图 - 基础和Overview
  - 图(Graph)是由顶点和连接顶点的边构成的离散结构。在计算机科学中，图是最灵活的数据结构之一，很多问题都可以使用图模型进行建模求解。例如: 生态环境中不同物种的相互竞争、人与人之间的社交与关系网络、化学上用图区分结构不同但分子式相同的同分异构体、分析计算机网络的拓扑结构确定两台计算机是否可以通信、找到两个城市之间的最短路径等等。
- 图 - 遍历(BFS & DFS)
  - 图的深度优先搜索(Depth First Search)，和树的先序遍历比较类似; 广度优先搜索算法(Breadth First Search)，又称为"宽度优先搜索"或"横向优先搜索"
- 图 - 最小生成树(Prim & Kruskal)
  - Kruskal算法是从最小权重边着手，将森林里的树逐渐合并；prim算法是从顶点出发，在根结点的基础上建起一棵树
- 图 - 最短路径(Dijkstra & Frolyd)
  - 最短路径有着广泛的应用，比如地图两点间距离计算，公交查询系统，路由选择等
- 图 - 拓扑排序(Topological sort)
  - 拓扑排序主要用来解决有向图中的依赖解析(dependency resolution)问题
- 图 - AOE & 关键路径
  - 关键路径在项目管理计算工期等方面有广泛等应用，提升工期就是所见缩减所有关键路径上的工期，并且在实现时需要应用到之前拓扑排序的算法(前提: 有向无环图，有依赖关系)

## 线性表 - 数据和矩阵

数组的优点:

- 存取速度快

数组的缺点:

- 事先必须知道数组的长度
- 插入删除元素很慢
- 空间通常是有限制的
- 需要大块连续的内存块
- 插入删除元素的效率很低

JDK中关于ArrayList的实现，请参考:《Java - ArrayList 源码解析》

相关题目

1. [283. Move Zeroes (Easy)](https://leetcode.com/problems/move-zeroes/description/)**把数组中的 0 移到末尾**
2. [566. Reshape the Matrix (Easy)](https://leetcode.com/problems/reshape-the-matrix/description/)**改变矩阵维度**
3. [485. Max Consecutive Ones (Easy)](https://leetcode.com/problems/max-consecutive-ones/description/)**找出数组中最长的连续 1**
4. [240. Search a 2D Matrix II (Medium)](https://leetcode.com/problems/search-a-2d-matrix-ii/description/)**有序矩阵查找**
5. [378. Kth Smallest Element in a Sorted Matrix ((Medium))](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/)**有序矩阵中第K小的元素**
6. [645. Set Mismatch (Easy)](https://leetcode.com/problems/set-mismatch/description/) **错误的集合**
7. [667. Beautiful Arrangement II (Medium)](https://leetcode.com/problems/beautiful-arrangement-ii/description/)**数组相邻差值的个数**
8. [697. Degree of an Array (Easy)](https://leetcode.com/problems/degree-of-an-array/description/)**数组的度**
9. [766. Toeplitz Matrix (Easy)](https://leetcode.com/problems/toeplitz-matrix/description/)**对角元素相等的矩阵**
10. [565. Array Nesting (Medium)](https://leetcode.com/problems/array-nesting/description/)**嵌套数组**
11. [769. Max Chunks To Make Sorted (Medium)](https://leetcode.com/problems/max-chunks-to-make-sorted/description/)**分隔数组**



> TIP: 
>
> 二维矩阵都需要算出 length & [0].length
>
> 一维数组：[1,2,3,4]
>
> 二维数组：{{1,2},{3,4}}
>
> 二维数组i,j 与 当前位置个数index的关系 index = i * j，商和余数 即是j与j

## 线性表 - 链表

> n个节点离散分配，彼此通过指针相连，每个节点只有一个前驱节点，每个节点只有一个后续节点，首节点没有前驱节点，尾节点没有后续节点。确定一个链表我们只需要头指针，通过头指针就可以把整个链表都能推出来。

### 优缺点

链表优点

- 空间没有限制
- 插入删除元素很快

链表缺点 存取速度很慢

一个链表节点对象就创建完成了，但理解链表本身并不难，但做相关的操作却并非易事，其算法包括且不限于:

- 插入节点
- 遍历
- 查找
- 清空
- 销毁
- 求长度
- 排序
- 删除节点
- 去重

JDK中关于链表的实现，请参考:[《Java - LinkedList 源码解析》](https://www.pdai.tech/md/java/collection/java-collection-LinkedList.html)

### 链表相关题目

1. [160. Intersection of Two Linked Lists (Easy)](https://leetcode.com/problems/intersection-of-two-linked-lists/description/)**找出两个链表的交点**

   ````java
   public class Solution {
       public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
           if (headA == null || headB == null) {
               return null;
           }
           // 定义两个指针, 第一轮让两个到达末尾的节点指向另一个链表的头部, 最后如果相遇则为交点(在第一轮移动中恰好抹除了长度差)。两个指针等于移动了相同的距离, 有交点就返回, 无交点就是各走了两条指针的长度 
           // 在这里第一轮体现在pA和pB第一次到达尾部会移向另一链表的表头, 而第二轮体现在如果pA或pB相交就返回交点, 不相交最后就是null==null
           ListNode pA = headA, pB = headB;
           while (pA != pB) {
               pA = pA == null ? headB : pA.next;
               pB = pB == null ? headA : pB.next;
           }
           return pA;
       }
   }
   ````

2. [206. Reverse Linked List (Easy)](https://leetcode.com/problems/reverse-linked-list/description/)**链表反转**

   ````java
   public ListNode reverseList(ListNode head) {
     ListNode pre = null;
     ListNode curr = head;
     while(curr != null){
       ListNode next = curr.next;
       curr.next = pre;
       pre = curr;
       curr = next;
     }
     return pre;
   }
   ````

3. [21. Merge Two Sorted Lists (Easy)](https://leetcode.com/problems/merge-two-sorted-lists/description/)**归并两个有序的链表**

   如果两个链表有一个为空，递归结束。

   ````java
   class Solution {
       public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
           if (l1 == null) {
               return l2;
           } else if (l2 == null) {
               return l1;
           } else if (l1.val < l2.val) {
               l1.next = mergeTwoLists(l1.next, l2);
               return l1;
           } else {
               l2.next = mergeTwoLists(l1, l2.next);
               return l2;
           }
       }
   }
   ````

4. [83. Remove Duplicates from Sorted List (Easy)](https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/)**从有序链表中删除重复节点**

   开辟一个临时的链表变量

   ````java
   public ListNode deleteDuplicates(ListNode head) {
     ListNode cur = head;
     while(cur.next != null){
       if(cur.val == cur.next.val){
         cur.next = cur.next.next;
       }else{
         cur = cur.next;
       }
     }
     return head;
   }
   ````

5. [19. Remove Nth Node From End of List (Medium)](https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/)**删除链表的倒数第 n 个节点**

6. [24. Swap Nodes in Pairs (Medium)](https://leetcode.com/problems/swap-nodes-in-pairs/description/)**交换链表中的相邻结点**

7. [445. Add Two Numbers II (Medium)](https://leetcode.com/problems/add-two-numbers-ii/description/)**链表求和**

8. [234. Palindrome Linked List (Easy)](https://leetcode.com/problems/palindrome-linked-list/description/)**回文链表**

   方法1. 将值复制到数组中后用双指针法

   ````java
   class Solution {
       public boolean isPalindrome(ListNode head) {
           List<Integer> vals = new ArrayList<Integer>();
   
           // 将链表的值复制到数组中
           ListNode currentNode = head;
           while (currentNode != null) {
               vals.add(currentNode.val);
               currentNode = currentNode.next;
           }
   
           // 使用双指针判断是否回文
           int front = 0;
           int back = vals.size() - 1;
           while (front < back) {
               if (!vals.get(front).equals(vals.get(back))) {
                   return false;
               }
               front++;
               back--;
           }
           return true;
       }
   }
   ````

9. [725. Split Linked List in Parts(Medium)](https://leetcode.com/problems/split-linked-list-in-parts/description/)**分隔链表**

10. [328. Odd Even Linked List (Medium)](https://leetcode.com/problems/odd-even-linked-list/description/)**链表元素按奇偶聚集**

## 线性表(散列) - 哈希表

> 散列表（Hash table，也叫哈希表），是根据关键码值(Key value)而直接进行访问的数据结构。也就是说，它通过把关键码值映射到表中一个位置来访问记录，以加快查找的速度。这个映射函数叫做散列函数，存放记录的数组叫做散列表。

哈希表使用 O(N) 空间复杂度存储数据，并且以 O(1) 时间复杂度求解问题。

### 哈希表相关题目

1. [1. Two Sum (Easy)](https://leetcode.com/problems/two-sum/description/)**数组中两个数的和为给定值**

   ````java
   public int[] twoSum(int[] nums, int target) {
       HashMap<Integer, Integer> indexForNum = new HashMap<>();
       for (int i = 0; i < nums.length; i++) {
           if (indexForNum.containsKey(target - nums[i])) {
               return new int[]{indexForNum.get(target - nums[i]), i};
           } else {
               indexForNum.put(nums[i], i);
           }
       }
       return null;
   }
   ````

2. [217. Contains Duplicate (Easy)](https://leetcode.com/problems/contains-duplicate/description/)**判断数组是否含有重复元素**

   ````java
   public boolean containsDuplicate(int[] nums) {
       Set<Integer> set = new HashSet<>();
       for (int num : nums) {
           set.add(num);
       }
       return set.size() < nums.length;
   }
   ````

3. [594. Longest Harmonious Subsequence (Easy)](https://leetcode.com/problems/longest-harmonious-subsequence/description/)**最长和谐序列**

   Map.get(Obj) 不能是返回null的

   ````java
   public int findLHS(int[] nums) {
       Map<Integer, Integer> countForNum = new HashMap<>();
       for (int num : nums) {
           countForNum.put(num, countForNum.getOrDefault(num, 0) + 1);
       }
       int longest = 0;
       for (int num : countForNum.keySet()) {
           if (countForNum.containsKey(num + 1)) {
               longest = Math.max(longest, countForNum.get(num + 1) + countForNum.get(num));
           }
       }
       return longest;
   }
   ````

4. [128. Longest Consecutive Sequence (Hard)](https://leetcode.com/problems/longest-consecutive-sequence/description/)**最长连续序列**

## 线性表 - 栈和队列

> 数组和链表都是线性存储结构的基础，栈和队列都是线性存储结构的应用。
>
> 栈的顺序为先进后出，队列的顺序有先进先出

### 栈

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/alg/alg-stack-1.png)

栈的实现

- 使用数组实现的叫`静态栈`
- 使用链表实现的叫`动态栈`

### 队列

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/alg/alg-queue-1.png)

队列的实现

- 使用数组实现的叫`静态队列`
- 使用链表实现的叫`动态队列`

JDK中实现[《Java - Stack & Queue 源码解析》](https://www.pdai.tech/md/java/collection/java-collection-Queue&Stack.html)

### 栈和队列相关题目

1. [232. Implement Queue using Stacks (Easy)](https://leetcode.com/problems/implement-queue-using-stacks/description/)**用栈实现队列**

   栈的顺序为后进先出，而队列的顺序为先进先出。使用两个栈实现队列，一个元素需要经过两个栈才能出队列，在经过第一个栈时元素顺序被反转，经过第二个栈时再次被反转，此时就是先进先出顺序。

   ````java
   class MyQueue {
   
       private Stack<Integer> in;
       private Stack<Integer> out;
   
       public MyQueue() {
           in = new Stack<>();
           out = new Stack<>();
       }
       
       public void push(int x) {
           in.push(x);
       }
       
       public int pop() {
           in2out();
           return out.pop();
       }
       
       public int peek() {
           in2out();
           return out.peek();
       }
       
       public boolean empty() {
           return in.isEmpty() && out.isEmpty();
       }
   
       private void in2out(){
           if(out.isEmpty()){
               while(!in.isEmpty()){
                   out.push(in.pop());
               }
           }
       }
   }
   ````

2. [225. Implement Stack using Queues (Easy)](https://leetcode.com/problems/implement-stack-using-queues/description/)**用队列实现栈**

   在将一个元素 x 插入队列时，为了维护原来的后进先出顺序，需要让 x 插入队列首部。而队列的默认插入顺序是队列尾部，因此在将 x 插入队列尾部之后，需要让除了 x 之外的所有元素出队列，再入队列。

   ```java
   class MyStack {
     private Queue<Integer> queue ;
     public MyStack() {
         queue = new LinkedList<>(); 
     }
   
     public void push(int x) {
         queue.add(x);
         int cnt = queue.size();
         while(cnt > 1){
             queue.add(queue.poll());
             cnt --;
         }
     }
   
     public int pop() {
         return queue.remove();
     }
   
     public int top() {
         return queue.peek();
     }
   
     public boolean empty() {
         return queue.isEmpty();
     }
   }
   ```

3. [155. Min Stack (Easy)](https://leetcode.com/problems/min-stack/description/)**最小值栈**

   ![fig1](https://assets.leetcode-cn.com/solution-static/155/155_fig1.gif)

   ````java
   class MinStack {
       private Stack<Integer> dataStack;
       private Stack<Integer> minStack;
       private Integer min;
   
       public MinStack() {
           dataStack = new Stack<>();
           minStack = new Stack<>();
           min = Integer.MAX_VALUE;
       }
       
       public void push(int val) {
           dataStack.push(val);
           min = Math.min(min,val);
           minStack.push(min);
       }
       
       public void pop() {
           dataStack.pop();
           minStack.pop();
           min = minStack.isEmpty() ? Integer.MAX_VALUE : minStack.peek();
       }
       
       public int top() {
           return dataStack.peek();
       }
       
       public int getMin() {
           return minStack.isEmpty() ? Integer.MAX_VALUE : minStack.peek();
       }
   }
   ````

4. [20. Valid Parentheses (Easy)](https://leetcode.com/problems/valid-parentheses/description/)**用栈实现括号匹配**

   最后返回 stack.isEmpty 是为了处理 `((`这种情况，一开始做题我直接返回了 true.错了

   ````java
   class Solution {
       public boolean isValid(String s) {
           int n = s.length();
           if (n % 2 == 1) {
               return false;
           }
           
           Stack<Character> stack = new Stack<>();
           for (char c : s.toCharArray()) {
               if (c == '(' || c == '[' || c == '{') {
                   stack.push(c);
               } else {
                   if (stack.isEmpty()) {
                       return false;
                   }
                   char ch = stack.pop();
                   Boolean flag1 = c == ')' && ch != '(';
                   Boolean flag2 = c == ']' && ch != '[';
                   Boolean flag3 = c == '}' && ch != '{';
                   if (flag1 || flag2 || flag3) {
                       return false;
                   }
               }
           }
           return stack.isEmpty();
       }
   }
   ````

5. [739. Daily Temperatures (Medium)](https://leetcode.com/problems/daily-temperatures/description/)**数组中元素与下一个比它大的元素之间的距离**

   

6. [503. Next Greater Element II (Medium)](https://leetcode.com/problems/next-greater-element-ii/description/)**循环数组中比当前元素大的下一个元素**

   

## 树 - 基础和Overview



## 树 - 二叉搜索树(BST)



## 树 - 平衡二叉树(AVL)



## 树 - 红黑树(R-B Tree)



## 树 - 哈夫曼树(Huffman Tree)



## 树 - 前缀树(Trie Tree)



## 图 - 基础和Overview



## 图 - 遍历(BFS & DFS)



## 图 - 最小生成树(Prim & Kruskal)



## 图 - 最短路径(Dijkstra & Frolyd)



## 图 - 拓扑排序(Topological sort)



## 图 - AOE & 关键路径

