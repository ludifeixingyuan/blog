# 数据结构基础

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

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/alg/alg-tree-0.png)

### 树

树是一种数据结构，它是n(n>=0)个节点的有限集。n=0时称为空树。n>0时，有限集的元素构成一个具有层次感的数据结构。

### 树的相关概念

树有许多相关的术语与概念，在学习树的结构之前，我们要熟悉这些概念。

- `子树`: 除了根节点外，每个子节点都可以分为多个不相交的子树。(图二)
- `孩子与双亲`: 若一个结点有子树，那么该结点称为子树根的"双亲"，子树的根是该结点的"孩子"。在图一中，B、H是A的孩子，A是B、H的双亲。
- `兄弟`: 具有相同双亲的节点互为兄弟，例如B与H互为兄弟。
- `节点的度`: 一个节点拥有子树的数目。例如A的度为2，B的度为1，C的度为3.
- `叶子`: 没有子树，也即是度为0的节点。
- `分支节点`: 除了叶子节点之外的节点，也即是度不为0的节点。
- `内部节点`: 除了根节点之外的分支节点。
- `层次`: 根节点为第一层，其余节点的层次等于其双亲节点的层次加1.
- `树的高度`: 也称为树的深度，树中节点的最大层次。
- `有序树`: 树中节点各子树之间的次序是重要的，不可以随意交换位置。
- `无序树`: 树种节点各子树之间的次序是不重要的。可以随意交换位置。
- `森林`: 0或多棵互不相交的树的集合。例如图二中的两棵树为森林。

### 二叉树、完全二叉树、满二叉树

- 二叉树: 最多有两棵子树的树被称为二叉树

- 斜树: 所有节点都只有左子树的二叉树叫做左斜树，所有节点都只有右子树的二叉树叫做右斜树。(本质就是链表)

  

  ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/alg/alg-tree-4.png)

- 满二叉树: 二叉树中所有非叶子结点的度都是2，且叶子结点都在同一层次上

  ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/alg/alg-tree-5.png)

- 完全二叉树: 如果一个二叉树与满二叉树前m个节点的结构相同，这样的二叉树被称为完全二叉树

  ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/alg/alg-tree-6.png)

### 二叉查找树 - BST（Binary Search Tree）

二叉查找树(Binary Search Tree)是指一棵空树或者具有下列性质的二叉树:

- 若任意节点的左子树不空，则左子树上所有节点的值均小于它的根节点的值；
- 若任意节点的右子树不空，则右子树上所有节点的值均大于它的根节点的值；
- 任意节点的左、右子树也分别为二叉查找树；
- 没有键值相等的节点。

二叉查找树相比于其他数据结构的优势在于查找、插入的时间复杂度较低为 O ( log ⁡ n ) 。二叉查找树是基础性数据结构，用于构建更为抽象的数据结构，如集合、多重集、关联数组等。

![image-20220128125304062](https://tva1.sinaimg.cn/large/008i3skNly1gytamo2wmaj30is0fqq3j.jpg)

### 平衡二叉树 - AVL

含有相同节点的二叉查找树可以有不同的形态，而二叉查找树的平均查找长度与树的深度有关，所以需要找出一个查找平均长度最小的一棵，那就是平衡二叉树，具有以下性质:

- 要么是棵空树，要么其根节点左右子树的深度之差的绝对值不超过1；
- 其左右子树也都是平衡二叉树；
- 二叉树节点的平衡因子定义为该节点的左子树的深度减去右子树的深度。则平衡二叉树的所有节点的平衡因子只可能是-1,0,1。

<img src="https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/alg/alg-tree-balance-tree-1.jpg" alt="img"  />

### 红黑树

红黑树也是一种自平衡的二叉查找树。

- 每个结点要么是红的要么是黑的。(红或黑)
- 根结点是黑的。  (根黑)
- 每个叶结点(叶结点即指树尾端NIL指针或NULL结点)都是黑的。 (叶黑)
- 如果一个结点是红的，那么它的两个儿子都是黑的。 (红子黑)
- 对于任意结点而言，其到叶结点树尾端NIL指针的每条路径都包含相同数目的黑结点。(路径下黑相同)

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/alg/alg-tree-14.png)

### 哈弗曼树

哈夫曼又称最优二叉树。是一种带权路径长度最短的二叉树，一般可以按下面步骤构建:

- 将所有左，右子树都为空的作为根节点。
- 在森林中选出两棵根节点的权值最小的树作为一棵新树的左，右子树，且置新树的附加根节点的权值为其左，右子树上根节点的权值之和。注意，左子树的权值应小于右子树的权值。
- 从森林中删除这两棵树，同时把新树加入到森林中。
- 重复2，3步骤，直到森林中只有一棵树为止，此树便是哈夫曼树。

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/alg/alg-tree-13.png)

### B树

B树(英语: B-tree)是一种自平衡的树，能够保持数据有序。这种数据结构能够让查找数据、顺序访问、插入数据及删除的动作，都在对数时间内完成。B树，概括来说是一种自平衡的m阶树，与自平衡二叉查找树不同，B树适用于读写相对大的数据块的存储系统，例如磁盘。

- 根结点至少有两个子女。
- 每个中间节点都包含k-1个元素和k个孩子，其中 m/2 <= k <= m
- 每一个叶子节点都包含k-1个元素，其中 m/2 <= k <= m
- 所有的叶子结点都位于同一层。
- 每个节点中的元素从小到大排列，节点当中k-1个元素正好是k个孩子包含的元素的值域分划。

B-Tree中的每个节点根据实际情况可以包含大量的关键字信息和分支，如下图所示为一个3阶的B-Tree:

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/alg/alg-tree-15.png)

### B+树

B+ 树是一种树数据结构，通常用于关系型数据库(如Mysql)和操作系统的文件系统中。B+ 树的特点是能够保持数据稳定有序，其插入与修改拥有较稳定的对数时间复杂度。B+ 树元素自底向上插入，这与二叉树恰好相反。

在B树基础上，为叶子结点增加链表指针(B树+叶子有序链表)，所有关键字都在叶子结点 中出现，非叶子结点作为叶子结点的索引；B+树总是到叶子结点才命中。

b+树的非叶子节点不保存数据，只保存子树的临界值(最大或者最小)，所以同样大小的节点，b+树相对于b树能够有更多的分支，使得这棵树更加矮胖，查询时做的IO操作次数也更少。

将上一节中的B-Tree优化，由于B+Tree的非叶子节点只存储键值信息，假设每个磁盘块能存储4个键值及指针信息，则变成B+Tree后其结构如下图所示:

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/alg/alg-tree-16.png)

### R树

R树是用来做空间数据存储的树状数据结构。例如给地理位置，矩形和多边形这类多维数据建立索引。

R树的核心思想是聚合距离相近的节点并在树结构的上一层将其表示为这些节点的最小外接矩形(MBR)，这个最小外接矩形就成为上一层的一个节点。因为所有节点都在它们的最小外接矩形中，所以跟某个矩形不相交的查询就一定跟这个矩形中的所有节点都不相交。叶子节点上的每个矩形都代表一个对象，节点都是对象的聚合，并且越往上层聚合的对象就越多。也可以把每一层看做是对数据集的近似，叶子节点层是最细粒度的近似，与数据集相似度100%，越往上层越粗糙。

![image-20220128132247084](https://tva1.sinaimg.cn/large/008i3skNly1gytbhjo0tlj30pw0liabm.jpg)

### 总结

我们知道，实际应用当中，我们经常使用的是`查找`和`排序`操作，这在我们的各种管理系统、数据库系统、操作系统等当中，十分常用。

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

> 针对大量数据，如果在内存中作业优先考虑红黑树(map,set之类多为RB-tree实现)，如果在硬盘中作业优先考虑B系列树(B+, B, B*)

## 树 - 二叉搜索树(BST)

> 本文主要介绍 二叉树中最基本的二叉查找树（Binary Search Tree），（又：二叉搜索树，二叉排序树）它或者是一棵空树，或者是具有下列性质的二叉树： 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值； 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值； 它的左、右子树也分别为二叉排序树。

### 定义

在二叉查找树中:

- 若任意节点的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
- 任意节点的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
- 任意节点的左、右子树也分别为二叉查找树。
- 没有键值相等的节点。

![image-20220128133357926](https://tva1.sinaimg.cn/large/008i3skNly1gytbt6s4q0j30ng0f8q3k.jpg)

### BST的实现

#### 节点

BSTree是二叉树，它保护了二叉树的根节点mRoot；mRoot是BSTNode类型，而BSTNode是二叉查找树的节点，它是BSTree的内部类。BSTNode包含二叉查找树的几个基本信息:

- key -- 它是关键字，是用来对二叉查找树的节点进行排序的。
- left -- 它指向当前节点的左孩子。
- right -- 它指向当前节点的右孩子。
- parent -- 它指向当前节点的父结点。

````java
public class BSTree<T extends Comparable<T>> {

    private BSTNode<T> mRoot;    // 根结点

    public class BSTNode<T extends Comparable<T>> {
        T key;                // 关键字(键值)
        BSTNode<T> left;      // 左孩子
        BSTNode<T> right;     // 右孩子
        BSTNode<T> parent;    // 父结点

        public BSTNode(T key, BSTNode<T> parent, BSTNode<T> left, BSTNode<T> right) {
            this.key = key;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }

        ......
}
````

#### 遍历

前序遍历、中序遍历、后序遍历

**前序遍历**

- 访问根节点
- 遍历左子树
- 遍历右子树

````java
private void preOrder(BSTNode<T> tree) {
    if(tree != null) {
        System.out.print(tree.key+" ");
        preOrder(tree.left);
        preOrder(tree.right);
    }
}

public void preOrder() {
    preOrder(mRoot);
}
````

**中序遍历**

- 中序遍历左子树
- 访问根节点
- 中序遍历右子树

````java
private void inOrder(BSTNode<T> tree) {
    if(tree != null) {
        inOrder(tree.left);
        System.out.print(tree.key+" ");
        inOrder(tree.right);
    }
}

public void inOrder() {
    inOrder(mRoot);
}
````

**后序遍历**

- 后序遍历左子树
- 后序遍历右子树
- 访问根节点

````java
private void postOrder(BSTNode<T> tree) {
    if(tree != null)
    {
        postOrder(tree.left);
        postOrder(tree.right);
        System.out.print(tree.key+" ");
    }
}

public void postOrder() {
    postOrder(mRoot);
}
````

看看下面这颗树的各种遍历方式:

![image-20220128135157416](/Users/wz/Library/Application Support/typora-user-images/image-20220128135157416.png)

对于上面的二叉树而言，

- 前序遍历结果:  8 3 1 6 4 7 10 14 13
- 中序遍历结果:  1 3 4 6 7 8 10 13 14
- 后序遍历结果:  1 4 7 6 3 13 14 10 8

#### 查找

- 递归版本的代码

  ````java
  /*
   * (递归实现)查找"二叉树x"中键值为key的节点
   */
  private BSTNode<T> search(BSTNode<T> x, T key) {
      if (x==null)
          return x;
  
      int cmp = key.compareTo(x.key);
      if (cmp < 0)
          return search(x.left, key);
      else if (cmp > 0)
          return search(x.right, key);
      else
          return x;
  }
  
  public BSTNode<T> search(T key) {
      return search(mRoot, key);
  }
  ````

- 非递归版本的代码

  ````java
  /*
   * (非递归实现)查找"二叉树x"中键值为key的节点
   */
  private BSTNode<T> iterativeSearch(BSTNode<T> x, T key) {
      while (x!=null) {
          int cmp = key.compareTo(x.key);
  
          if (cmp < 0) 
              x = x.left;
          else if (cmp > 0) 
              x = x.right;
          else
              return x;
      }
  
      return x;
  }
  
  public BSTNode<T> iterativeSearch(T key) {
      return iterativeSearch(mRoot, key);
  }
  ````

#### 最大值和最小值

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/alg/alg-tree-11.png)

- 查找最大结点

  ````java
  /* 
   * 查找最大结点: 返回tree为根结点的二叉树的最大结点。
   */
  private BSTNode<T> maximum(BSTNode<T> tree) {
      if (tree == null)
          return null;
  
      while(tree.right != null)
          tree = tree.right;
      return tree;
  }
  
  public T maximum() {
      BSTNode<T> p = maximum(mRoot);
      if (p != null)
          return p.key;
  
      return null;
  }
  ````

- 查找最小结点

  ````java
  /* 
   * 查找最小结点: 返回tree为根结点的二叉树的最小结点。
   */
  private BSTNode<T> minimum(BSTNode<T> tree) {
      if (tree == null)
          return null;
  
      while(tree.left != null)
          tree = tree.left;
      return tree;
  }
  
  public T minimum() {
      BSTNode<T> p = minimum(mRoot);
      if (p != null)
          return p.key;
  
      return null;
  }
  ````

#### 前驱和后继

节点的前驱: 是该节点的左子树中的最大节点。 节点的后继: 是该节点的右子树中的最小节点。

- 查找前驱节点

  ````java
  /* 
   * 找结点(x)的前驱结点。即，查找"二叉树中数据值小于该结点"的"最大结点"。
   */
  public BSTNode<T> predecessor(BSTNode<T> x) {
      // 如果x存在左孩子，则"x的前驱结点"为 "以其左孩子为根的子树的最大结点"。
      if (x.left != null)
          return maximum(x.left);
  
      // 如果x没有左孩子。则x有以下两种可能: 
      // (01) x是"一个右孩子"，则"x的前驱结点"为 "它的父结点"。
      // (01) x是"一个左孩子"，则查找"x的最低的父结点，并且该父结点要具有右孩子"，找到的这个"最低的父结点"就是"x的前驱结点"。
      BSTNode<T> y = x.parent;
      while ((y!=null) && (x==y.left)) {
          x = y;
          y = y.parent;
      }
  
      return y;
  }
  ````

- 查找后继节点

  ````java
  /* 
   * 找结点(x)的后继结点。即，查找"二叉树中数据值大于该结点"的"最小结点"。
   */
  public BSTNode<T> successor(BSTNode<T> x) {
      // 如果x存在右孩子，则"x的后继结点"为 "以其右孩子为根的子树的最小结点"。
      if (x.right != null)
          return minimum(x.right);
  
      // 如果x没有右孩子。则x有以下两种可能: 
      // (01) x是"一个左孩子"，则"x的后继结点"为 "它的父结点"。
      // (02) x是"一个右孩子"，则查找"x的最低的父结点，并且该父结点要具有左孩子"，找到的这个"最低的父结点"就是"x的后继结点"。
      BSTNode<T> y = x.parent;
      while ((y!=null) && (x==y.right)) {
          x = y;
          y = y.parent;
      }
  
      return y;
  }
  ````

#### 插入

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/alg/alg-tree-8.png)

````java
/* 
 * 将结点插入到二叉树中。
 * 先找到父结点应该是哪个，再判断应该放父结点左边还是右边
 *
 * 参数说明: 
 *     tree 二叉树的
 *     z 插入的结点
 */
private void insert(BSTree<T> bst, BSTNode<T> z) {
    int cmp;
    // 上一轮的结点
    BSTNode<T> y = null;
    // 临时根结点
    BSTNode<T> x = bst.mRoot;

    // 查找z的插入位置
    while (x != null) {
        y = x;
        cmp = z.key.compareTo(x.key);
        if (cmp < 0)
            x = x.left;
        else
            x = x.right;
    }

    z.parent = y;
    if (y==null)
        bst.mRoot = z;
    else {
        cmp = z.key.compareTo(y.key);
        if (cmp < 0)
            y.left = z;
        else
            y.right = z;
    }
}

/* 
 * 新建结点(key)，并将其插入到二叉树中
 *
 * 参数说明: 
 *     tree 二叉树的根结点
 *     key 插入结点的键值
 */
public void insert(T key) {
    BSTNode<T> z=new BSTNode<T>(key,null,null,null);

    // 如果新建结点失败，则返回。
    if (z != null)
        insert(this, z);
}
````

#### 删除

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/alg/alg-tree-10.png)

````java
/* 
 * 删除结点(z)，并返回被删除的结点
 *
 * 参数说明: 
 *     bst 二叉树
 *     z 删除的结点
 */
private BSTNode<T> remove(BSTree<T> bst, BSTNode<T> z) {
    BSTNode<T> x=null;
    BSTNode<T> y=null;

    if ((z.left == null) || (z.right == null) )
        y = z;
    else
        y = successor(z);

    if (y.left != null)
        x = y.left;
    else
        x = y.right;

    if (x != null)
        x.parent = y.parent;

    if (y.parent == null)
        bst.mRoot = x;
    else if (y == y.parent.left)
        y.parent.left = x;
    else
        y.parent.right = x;

    if (y != z) 
        z.key = y.key;

    return y;
}

/* 
 * 删除结点(z)，并返回被删除的结点
 *
 * 参数说明: 
 *     tree 二叉树的根结点
 *     z 删除的结点
 */
public void remove(T key) {
    BSTNode<T> z, node; 

    if ((z = search(mRoot, key)) != null)
        if ( (node = remove(this, z)) != null)
            node = null;
}
````

#### 打印

````java
/*
 * 打印"二叉查找树"
 *
 * key        -- 节点的键值 
 * direction  --  0，表示该节点是根节点;
 *               -1，表示该节点是它的父结点的左孩子;
 *                1，表示该节点是它的父结点的右孩子。
 */
private void print(BSTNode<T> tree, T key, int direction) {

    if(tree != null) {

        if(direction==0)    // tree是根节点
            System.out.printf("%2d is root\n", tree.key);
        else                // tree是分支节点
            System.out.printf("%2d is %2d's %6s child\n", tree.key, key, direction==1?"right" : "left");

        print(tree.left, tree.key, -1);
        print(tree.right,tree.key,  1);
    }
}

public void print() {
    if (mRoot != null)
        print(mRoot, mRoot.key, 0);
}
````

#### 销毁

````java
/*
 * 销毁二叉树
 */
private void destroy(BSTNode<T> tree) {
    if (tree==null)
        return ;

    if (tree.left != null)
        destroy(tree.left);
    if (tree.right != null)
        destroy(tree.right);

    tree=null;
}

public void clear() {
    destroy(mRoot);
    mRoot = null;
}
````

### 测试程序

略

### 代码和测试代码

#### 代码实现

略

#### 测试代码

略

#### 测试结果

略

### BST相关题目

二叉查找树(BST): 根节点大于等于左子树所有节点，小于等于右子树所有节点。

二叉查找树中序遍历有序。

- [669. Trim a Binary Search Tree (Medium)](https://leetcode.com/problems/trim-a-binary-search-tree/description/)**修剪二叉查找树**

  ````java
  class Solution {
      public TreeNode trimBST(TreeNode root, int low, int high) {
          if(root == null) {
              return null;
          }
          if(root.val > high){
              return trimBST(root.left,low,high);
          }
          if(root.val < low){
              return trimBST(root.right,low,high);
          }
          root.left = trimBST(root.left,low,high);
          root.right = trimBST(root.right,low,high);
          return root;
      }
  }
  ````

- [230. Kth Smallest Element in a BST (Medium)](https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/)**寻找二叉查找树的第 k 个元素**

  

- [538.Convert BST to Greater Tree (Medium)](https://leetcode.com/problems/convert-bst-to-greater-tree/description/)**把二叉查找树每个节点的值都加上比它大的节点的值**

  

- [235. Lowest Common Ancestor of a Binary Search Tree (Easy)](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/)**二叉查找树的最近公共祖先**

  ````java
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
      if (root.val > p.val && root.val > q.val) return lowestCommonAncestor(root.left, p, q);
      if (root.val < p.val && root.val < q.val) return lowestCommonAncestor(root.right, p, q);
      return root;
  }
  ````

- [236. Lowest Common Ancestor of a Binary Tree (Medium) ](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/)**二叉树的最近公共祖先**

  

- [108. Convert Sorted Array to Binary Search Tree (Easy)](https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/)**从有序数组中构造二叉查找树**

  ````java
  public TreeNode sortedArrayToBST(int[] nums) {
      return toBST(nums, 0, nums.length - 1);
  }
  
  private TreeNode toBST(int[] nums, int sIdx, int eIdx){
      if (sIdx > eIdx) return null;
      int mIdx = (sIdx + eIdx) / 2;
      TreeNode root = new TreeNode(nums[mIdx]);
      root.left =  toBST(nums, sIdx, mIdx - 1);
      root.right = toBST(nums, mIdx + 1, eIdx);
      return root;
  }
  ````

- [109. Convert Sorted List to Binary Search Tree (Medium)](https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/description/)**根据有序链表构造平衡的二叉查找树**

  

- [653. Two Sum IV - Input is a BST (Easy)](https://leetcode.com/problems/two-sum-iv-input-is-a-bst/description/)**在二叉查找树中寻找两个节点，使它们的和为一个给定值**

  ````java
  public class Solution {
      public boolean findTarget(TreeNode root, int k) {
          Set < Integer > set = new HashSet();
          return find(root, k, set);
      }
      public boolean find(TreeNode root, int k, Set < Integer > set) {
          if (root == null)
              return false;
          if (set.contains(k - root.val))
            // set 用contains，map才用containsKey
              return true;
        	// set用add，map采用put
          set.add(root.val);
          return find(root.left, k, set) || find(root.right, k, set);
      }
  }
  ````

- [530. Minimum Absolute Difference in BST (Easy)](https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/)**在二叉查找树中查找两个节点之差的最小绝对值**

  ````java
  // 差值
  private int minDiff = Integer.MAX_VALUE;
  // 上一个中点
  private TreeNode preNode = null;
  
  public int getMinimumDifference(TreeNode root) {
      inOrder(root);
      return minDiff;
  }
  
  // 利用二叉查找树的中序遍历为有序的性质，计算中序遍历中临近的两个节点之差的绝对值，取最小值。
  private void inOrder(TreeNode node) {
      if (node == null) return;
      inOrder(node.left);
      if (preNode != null) minDiff = Math.min(minDiff, node.val - preNode.val);
      preNode = node;
      inOrder(node.right);
  }
  ````

- [501. Find Mode in Binary Search Tree (Easy)](https://leetcode.com/problems/find-mode-in-binary-search-tree/description/)**寻找二叉查找树中出现次数最多的值**

  ````java
  private int curCnt = 1;
  private int maxCnt = 1;
  // 上一次的中间结点
  private TreeNode preNode = null;
  
  public int[] findMode(TreeNode root) {
      List<Integer> maxCntNums = new ArrayList<>();
      inOrder(root, maxCntNums);
      int[] ret = new int[maxCntNums.size()];
      int idx = 0;
      for (int num : maxCntNums) {
          ret[idx++] = num;
      }
      return ret;
  }
  
  private void inOrder(TreeNode node, List<Integer> nums) {
      if (node == null) return;
      inOrder(node.left, nums);
  	  // preNode ==null的情况 会进入下面的if (curCnt == maxCnt) 所以也会添加进nums
      if (preNode != null) {
          if (preNode.val == node.val) curCnt++;
          else curCnt = 1;
      }
      if (curCnt > maxCnt) {
          maxCnt = curCnt;
          nums.clear();
          nums.add(node.val);
      } else if (curCnt == maxCnt) {
          nums.add(node.val);
      }
      preNode = node;
      inOrder(node.right, nums);
  }
  ````

## 树 - 平衡二叉树(AVL)

> 平衡二叉树（Balanced Binary Tree）具有以下性质：它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。

### 什么树AVL树

AVL树是高度平衡的二叉树。它的特点是: AVL树中任何节点的两个子树的高度最大差别为1。

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/alg/alg-tree-avl-1.jpg)

#### 节点

**定义结点**

AVLTree是AVL树对应的类，而AVLTreeNode是AVL树节点，它是AVLTree的内部类。AVLTree包含了AVL树的根节点，AVL树的基本操作也定义在AVL树中。AVLTreeNode包括的几个组成对象:

- key -- 是关键字，是用来对AVL树的节点进行排序的。
- left -- 是左孩子。
- right -- 是右孩子。
- height -- 是高度。

````java
public class AVLTree<T extends Comparable<T>> {
    private AVLTreeNode<T> mRoot;    // 根结点

    // AVL树的节点(内部类)
    class AVLTreeNode<T extends Comparable<T>> {
        T key;                // 关键字(键值)
        int height;         // 高度
        AVLTreeNode<T> left;    // 左孩子
        AVLTreeNode<T> right;    // 右孩子

        public AVLTreeNode(T key, AVLTreeNode<T> left, AVLTreeNode<T> right) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.height = 0;
        }
    }
    
    ......
}
````

**树的高度**

维基百科上的定义: 树的高度为最大层次。即空的二叉树的高度是0，非空树的高度等于它的最大层次(根的层次为1，根的子节点为第2层，依次类推)。

````java
/*
 * 获取树的高度
 */
private int height(AVLTreeNode<T> tree) {
    if (tree != null)
        return tree.height;

    return 0;
}

public int height() {
    return height(mRoot);
}
````

**比较大小**

````java
/*
 * 比较两个值的大小
 */
private int max(int a, int b) {
    return a>b ? a : b;
}
````

#### 旋转

如果在AVL树中进行插入或删除节点后，可能导致AVL树失去平衡。这种失去平衡的可以概括为4种姿态: LL(左左)，LR(左右)，RR(右右)和RL(右左)。下面给出它们的示意图:

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/alg/alg-tree-avl-2.jpg)

上图中的4棵树都是"失去平衡的AVL树"，从左往右的情况依次是: LL、LR、RL、RR。除了上面的情况之外，还有其它的失去平衡的AVL树，如下图:

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/alg/alg-tree-avl-3.jpg)

上面的两张图都是为了便于理解，而列举的关于"失去平衡的AVL树"的例子。总的来说，AVL树失去平衡时的情况一定是LL、LR、RL、RR这4种之一，它们都由各自的定义:

(1) LL: LeftLeft，也称为"左左"。插入或删除一个节点后，根节点的左子树的左子树还有非空子节点，导致"根的左子树的高度"比"根的右子树的高度"大2，导致AVL树失去了平衡。 例如，在上面LL情况中，由于"根节点(8)的左子树(4)的左子树(2)还有非空子节点"，而"根节点(8)的右子树(12)没有子节点"；导致"根节点(8)的左子树(4)高度"比"根节点(8)的右子树(12)"高2。

(2) LR: LeftRight，也称为"左右"。插入或删除一个节点后，根节点的左子树的右子树还有非空子节点，导致"根的左子树的高度"比"根的右子树的高度"大2，导致AVL树失去了平衡。 例如，在上面LR情况中，由于"根节点(8)的左子树(4)的左子树(6)还有非空子节点"，而"根节点(8)的右子树(12)没有子节点"；导致"根节点(8)的左子树(4)高度"比"根节点(8)的右子树(12)"高2。

(3) RL: RightLeft，称为"右左"。插入或删除一个节点后，根节点的右子树的左子树还有非空子节点，导致"根的右子树的高度"比"根的左子树的高度"大2，导致AVL树失去了平衡。 例如，在上面RL情况中，由于"根节点(8)的右子树(12)的左子树(10)还有非空子节点"，而"根节点(8)的左子树(4)没有子节点"；导致"根节点(8)的右子树(12)高度"比"根节点(8)的左子树(4)"高2。

(4) RR: RightRight，称为"右右"。插入或删除一个节点后，根节点的右子树的右子树还有非空子节点，导致"根的右子树的高度"比"根的左子树的高度"大2，导致AVL树失去了平衡。 例如，在上面RR情况中，由于"根节点(8)的右子树(12)的右子树(14)还有非空子节点"，而"根节点(8)的左子树(4)没有子节点"；导致"根节点(8)的右子树(12)高度"比"根节点(8)的左子树(4)"高2。

如果在AVL树中进行插入或删除节点后，可能导致AVL树失去平衡。AVL失去平衡之后，可以通过旋转使其恢复平衡，下面分别介绍"LL(左左)，LR(左右)，RR(右右)和RL(右左)"这4种情况对应的旋转方法。

**LL的旋转**

LL失去平衡的情况，可以通过一次旋转让AVL树恢复平衡。如下图:

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/alg/alg-tree-avl-4.jpg)

图中左边是旋转之前的树，右边是旋转之后的树。从中可以发现，旋转之后的树又变成了AVL树，而且该旋转只需要一次即可完成。 对于LL旋转，你可以这样理解为: LL旋转是围绕"失去平衡的AVL根节点"进行的，也就是节点k2；而且由于是LL情况，即左左情况，就用手抓着"左孩子，即k1"使劲摇。将k1变成根节点，k2变成k1的右子树，"k1的右子树"变成"k2的左子树"。

````java
/*
 * LL: 左左对应的情况(左单旋转)。
 *
 * 返回值: 旋转后的根节点
 */
private AVLTreeNode<T> leftLeftRotation(AVLTreeNode<T> k2) {
    AVLTreeNode<T> k1;

    k1 = k2.left;
    k2.left = k1.right;
    k1.right = k2;

    k2.height = max( height(k2.left), height(k2.right)) + 1;
    k1.height = max( height(k1.left), k2.height) + 1;

    return k1;
}
````

**RR的旋转**

理解了LL之后，RR就相当容易理解了。RR是与LL对称的情况！RR恢复平衡的旋转方法如下:

<img src="https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/alg/alg-tree-avl-5.jpg" alt="img" style="zoom:75%;" />

图中左边是旋转之前的树，右边是旋转之后的树。RR旋转也只需要一次即可完成。

````java
/*
 * RR: 右右对应的情况(右单旋转)。
 *
 * 返回值: 旋转后的根节点
 */
private AVLTreeNode<T> rightRightRotation(AVLTreeNode<T> k1) {
    AVLTreeNode<T> k2;

    k2 = k1.right;
    k1.right = k2.left;
    k2.left = k1;

    k1.height = max( height(k1.left), height(k1.right)) + 1;
    k2.height = max( height(k2.right), k1.height) + 1;

    return k2;
}
````

**LR的旋转**

LR失去平衡的情况，需要经过两次旋转才能让AVL树恢复平衡。如下图:

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/alg/alg-tree-avl-6.jpg)

第一次旋转是围绕"k1"进行的"RR旋转"，第二次是围绕"k3"进行的"LL旋转"。

````java
/*
 * LR: 左右对应的情况(左双旋转)。
 *
 * 返回值: 旋转后的根节点
 */
private AVLTreeNode<T> leftRightRotation(AVLTreeNode<T> k3) {
    k3.left = rightRightRotation(k3.left);

    return leftLeftRotation(k3);
}
````

**RL的旋转**

RL是与LR的对称情况！RL恢复平衡的旋转方法如下:

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/alg/alg-tree-avl-7.jpg)

第一次旋转是围绕"k3"进行的"LL旋转"，第二次是围绕"k1"进行的"RR旋转"。

````java
/*
 * RL: 右左对应的情况(右双旋转)。
 *
 * 返回值: 旋转后的根节点
 */
private AVLTreeNode<T> rightLeftRotation(AVLTreeNode<T> k1) {
    k1.right = leftLeftRotation(k1.right);

    return rightRightRotation(k1);
}
````

####  插入

插入节点的代码

````java
/* 
 * 将结点插入到AVL树中，并返回根节点
 *
 * 参数说明: 
 *     tree AVL树的根结点
 *     key 插入的结点的键值
 * 返回值: 
 *     根节点
 */
private AVLTreeNode<T> insert(AVLTreeNode<T> tree, T key) {
    if (tree == null) {
        // 新建节点
        tree = new AVLTreeNode<T>(key, null, null);
        if (tree==null) {
            System.out.println("ERROR: create avltree node failed!");
            return null;
        }
    } else {
        int cmp = key.compareTo(tree.key);

           if (cmp < 0) {    // 应该将key插入到"tree的左子树"的情况
            tree.left = insert(tree.left, key);
            // 插入节点后，若AVL树失去平衡，则进行相应的调节。
            if (height(tree.left) - height(tree.right) == 2) {
                if (key.compareTo(tree.left.key) < 0)
                    tree = leftLeftRotation(tree);
                else
                    tree = leftRightRotation(tree);
            }
        } else if (cmp > 0) {    // 应该将key插入到"tree的右子树"的情况
            tree.right = insert(tree.right, key);
            // 插入节点后，若AVL树失去平衡，则进行相应的调节。
            if (height(tree.right) - height(tree.left) == 2) {
                if (key.compareTo(tree.right.key) > 0)
                    tree = rightRightRotation(tree);
                else
                    tree = rightLeftRotation(tree);
            }
        } else {    // cmp==0
            System.out.println("添加失败: 不允许添加相同的节点！");
        }
    }

    tree.height = max( height(tree.left), height(tree.right)) + 1;

    return tree;
}

public void insert(T key) {
    mRoot = insert(mRoot, key);
}
````

#### 删除

删除节点的代码

````java
/* 
 * 删除结点(z)，返回根节点
 *
 * 参数说明: 
 *     tree AVL树的根结点
 *     z 待删除的结点
 * 返回值: 
 *     根节点
 */
private AVLTreeNode<T> remove(AVLTreeNode<T> tree, AVLTreeNode<T> z) {
    // 根为空 或者 没有要删除的节点，直接返回null。
    if (tree==null || z==null)
        return null;

    int cmp = z.key.compareTo(tree.key);
    if (cmp < 0) {        // 待删除的节点在"tree的左子树"中
        tree.left = remove(tree.left, z);
        // 删除节点后，若AVL树失去平衡，则进行相应的调节。
        if (height(tree.right) - height(tree.left) == 2) {
            AVLTreeNode<T> r =  tree.right;
            if (height(r.left) > height(r.right))
                tree = rightLeftRotation(tree);
            else
                tree = rightRightRotation(tree);
        }
    } else if (cmp > 0) {    // 待删除的节点在"tree的右子树"中
        tree.right = remove(tree.right, z);
        // 删除节点后，若AVL树失去平衡，则进行相应的调节。
        if (height(tree.left) - height(tree.right) == 2) {
            AVLTreeNode<T> l =  tree.left;
            if (height(l.right) > height(l.left))
                tree = leftRightRotation(tree);
            else
                tree = leftLeftRotation(tree);
        }
    } else {    // tree是对应要删除的节点。
        // tree的左右孩子都非空
        if ((tree.left!=null) && (tree.right!=null)) {
            if (height(tree.left) > height(tree.right)) {
                // 如果tree的左子树比右子树高；
                // 则(01)找出tree的左子树中的最大节点
                //   (02)将该最大节点的值赋值给tree。
                //   (03)删除该最大节点。
                // 这类似于用"tree的左子树中最大节点"做"tree"的替身；
                // 采用这种方式的好处是: 删除"tree的左子树中最大节点"之后，AVL树仍然是平衡的。
                AVLTreeNode<T> max = maximum(tree.left);
                tree.key = max.key;
                tree.left = remove(tree.left, max);
            } else {
                // 如果tree的左子树不比右子树高(即它们相等，或右子树比左子树高1)
                // 则(01)找出tree的右子树中的最小节点
                //   (02)将该最小节点的值赋值给tree。
                //   (03)删除该最小节点。
                // 这类似于用"tree的右子树中最小节点"做"tree"的替身；
                // 采用这种方式的好处是: 删除"tree的右子树中最小节点"之后，AVL树仍然是平衡的。
                AVLTreeNode<T> min = maximum(tree.right);
                tree.key = min.key;
                tree.right = remove(tree.right, min);
            }
        } else {
            AVLTreeNode<T> tmp = tree;
            tree = (tree.left!=null) ? tree.left : tree.right;
            tmp = null;
        }
    }

    return tree;
}

public void remove(T key) {
    AVLTreeNode<T> z; 

    if ((z = search(mRoot, key)) != null)
        mRoot = remove(mRoot, z);
}
````

### AVL树测试

略

### 完整实现和测试的代码

#### AVL完整实现代码

略

#### AVL完整测试代码

略

#### 测试结果

略

## 树 - 红黑树(R-B Tree)

### 为什么药有红黑树

我们在上一篇博客认识到了平衡二叉树(AVLTree)，了解到AVL树的性质，其实平衡二叉树最大的作用就是查找,AVL树的查找、插入和删除在平均和最坏情况下都是O(logn)。AVL树的效率就是高在这个地方。如果在AVL树中插入或删除节点后，使得高度之差大于1。此时，AVL树的平衡状态就被破坏，它就不再是一棵二叉树；为了让它重新维持在一个平衡状态，就需要对其进行旋转处理, 那么创建一颗平衡二叉树的成本其实不小. 这个时候就有人开始思考，并且提出了红黑树的理论，那么红黑树到底比AVL树好在哪里?

红黑树与AVL树的比较:

- 1.AVL树的时间复杂度虽然优于红黑树，但是对于现在的计算机，cpu太快，可以忽略性能差异
- 2.红黑树的插入删除比AVL树更便于控制操作
- 3.红黑树整体性能略优于AVL树(红黑树旋转情况少于AVL树)

> `红黑树的性质`: 红黑树是一棵二叉搜索树，它在每个节点增加了一个存储位记录节点的颜色，可以是RED,也可以是BLACK；通过任意一条从根到叶子简单路径上颜色的约束，红黑树保证最长路径不超过最短路径的二倍，因而近似平衡。

具体性质如下:

- 每个节点颜色不是黑色，就是红色
- 根节点是黑色的
- 如果一个节点是红色，那么它的两个子节点就是黑色的(没有连续的红节点)
- 对于每个节点，从该节点到其后代叶节点的简单路径上，均包含相同数目的黑色节点

## 树 - 哈夫曼树(Huffman Tree)

略

## 树 - 前缀树(Trie Tree)

略

## 图 - 基础和Overview

### 定义

图(Graph)是由顶点的有穷非空集合和顶点之间边的集合组成，通常表示为: G(V,E)，其中，G表示一个图，V是图G中顶点的集合，E是图G中边的集合。

和线性表，树的差异:

- 线性表中我们把数据元素叫元素，树中将数据元素叫结点，在图中数据元素，我们则称之为顶点(Vertex)。
- 线性表可以没有元素，称为空表；树中可以没有节点，称为空树；但是，在图中不允许没有顶点(有穷非空性)。
- 线性表中的各元素是线性关系，树中的各元素是层次关系，而图中各顶点的关系是用边来表示(边集可以为空)。

### 相关术语

- 顶点的度

顶点Vi的度(Degree)是指在图中与Vi相关联的边的条数。对于有向图来说，有入度(In-degree)和出度(Out-degree)之分，有向图顶点的度等于该顶点的入度和出度之和。

- 邻接

若无向图中的两个顶点V1和V2存在一条边(V1,V2)，则称顶点V1和V2邻接(Adjacent)；

若有向图中存在一条边<V3,V2>，则称顶点V3与顶点V2邻接，且是V3邻接到V2或V2邻接直V3；

- 路径

在无向图中，若从顶点Vi出发有一组边可到达顶点Vj，则称顶点Vi到顶点Vj的顶点序列为从顶点Vi到顶点Vj的路径(Path)。

- 连通

若从Vi到Vj有路径可通，则称顶点Vi和顶点Vj是连通(Connected)的。

- 权(Weight)

有些图的边或弧具有与它相关的数字，这种与图的边或弧相关的数叫做权(Weight)。

### 类型

#### 无向图

如果图中任意两个顶点之间的边都是无向边(简而言之就是没有方向的边)，则称该图为无向图(Undirected graphs)。

无向图中的边使用小括号“()”表示; 比如 (V1,V2);

#### 有向图

如果图中任意两个顶点之间的边都是有向边(简而言之就是有方向的边)，则称该图为有向图(Directed graphs)。

有向图中的边使用尖括号“<>”表示; 比如/<V1,V2>

#### 完全图

- `无向完全图`: 在无向图中，如果任意两个顶点之间都存在边，则称该图为无向完全图。(含有n个顶点的无向完全图有(n×(n-1))/2条边)
- `有向完全图`: 在有向图中，如果任意两个顶点之间都存在方向互为相反的两条弧，则称该图为有向完全图。(含有n个顶点的有向完全图有n×(n-1)条边)

### 邻接矩阵表示法

图的邻接矩阵(Adjacency Matrix)存储方式是用两个数组来表示图。一个一维数组存储图中顶点信息，一个二维数组(称为邻接矩阵)存储图中的边或弧的信息。

- 无向图:

  ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/alg/alg-graph-store-1.png)

- 有向图:

  ![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/alg/alg-graph-store-2.png)

  > 不足: 由于存在n个顶点的图需要n*n个数组元素进行存储，当图为稀疏图时，使用邻接矩阵存储方法将会出现大量0元素，这会造成极大的空间浪费。这时，可以考虑使用邻接表表示法来存储图中的数据

### 邻接表表示法

首先，回忆我们在线性表时谈到，顺序存储结构就存在预先分配内存可能造成存储空间浪费的问题，于是引出了链式存储的结构。同样的，我们也可以考虑对边或弧使用链式存储的方式来避免空间浪费的问题。

邻接表由表头节点和表节点两部分组成，图中每个顶点均对应一个存储在数组中的表头节点。如果这个表头节点所对应的顶点存在邻接节点，则把邻接节点依次存放于表头节点所指向的单向链表中。

- `无向图`

下图所示的就是一个无向图的邻接表结构。

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/alg/alg-graph-store-3.jpg)

从上图中我们知道，顶点表的各个结点由data和firstedge两个域表示，data是数据域，存储顶点的信息，firstedge是指针域，指向边表的第一个结点，即此顶点的第一个邻接点。边表结点由adjvex和next两个域组成。adjvex是邻接点域，存储某顶点的邻接点在顶点表中的下标，next则存储指向边表中下一个结点的指针。例如: v1顶点与v0、v2互为邻接点，则在v1的边表中，adjvex分别为v0的0和v2的2。

PS: 对于无向图来说，使用邻接表进行存储也会出现数据冗余的现象。例如上图中，顶点V0所指向的链表中存在一个指向顶点V3的同事，顶点V3所指向的链表中也会存在一个指向V0的顶点。

- `有向图`

若是有向图，邻接表结构是类似的，但要注意的是有向图由于有方向的。因此，有向图的邻接表分为出边表和入边表(又称逆邻接表)，出边表的表节点存放的是从表头节点出发的有向边所指的尾节点；入边表的表节点存放的则是指向表头节点的某个顶点，如下图所示。

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/alg/alg-graph-store-4.jpg)

- `带权图`

对于带权值的网图，可以在边表结点定义中再增加一个weight的数据域，存储权值信息即可，如下图所示。

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/alg/alg-graph-store-5.jpg)

### 图相关题目

**二分图**

如果可以用两种颜色对图中的节点进行着色，并且保证相邻的节点颜色不同，那么这个图就是二分图。

**拓扑排序**

常用于在具有先序关系的任务规划中。

**并查集**

并查集可以动态地连通两个点，并且可以非常快速地判断两个点是否连通。

- [785. Is Graph Bipartite? (Medium)](https://leetcode.com/problems/is-graph-bipartite/description/)**判断是否为二分图**
- [207. Course Schedule (Medium)](https://leetcode.com/problems/course-schedule/description/)**课程安排的合法性**
- [210. Course Schedule II (Medium)](https://leetcode.com/problems/course-schedule-ii/description/)**课程安排的顺序**
- [684. Redundant Connection (Medium)](https://leetcode.com/problems/redundant-connection/description/)**冗余连接**

## 图 - 遍历(BFS & DFS)

略

## 图 - 最小生成树(Prim & Kruskal)

略

## 图 - 最短路径(Dijkstra & Frolyd)

略

## 图 - 拓扑排序(Topological sort)

略

## 图 - AOE & 关键路径

略

# 常见排序算法

## 常见排序算法知识体系详解

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/alg/alg-sort-overview-1.png)

> **A. 常见排序概要**：重点理解几个排序之间的对比，时间和空间复杂度，以及应用。PS：越简单越要提高认知效率，做到战略上藐视战术上重视。
>
> **B. 常见排序详解**：具体分析各种排序及其复杂度，查漏补缺；在综合复杂度及稳定性情况下，通常`希尔`, `快排`和 `归并`需要重点掌握。

- 排序 - 冒泡排序(Bubble Sort)

  它是一种较简单的排序算法。它会遍历若干次要排序的数列，每次遍历时，它都会从前往后依次的比较相邻两个数的大小；如果前者比后者大，则交换它们的位置。这样，一次遍历之后，最大的元素就在数列的末尾！ 采用相同的方法再次遍历时，第二大的元素就被排列在最大元素之前。重复此操作，直到整个数列都有序为止

- 排序 - 快速排序(Quick Sort)

  它的基本思想是: 选择一个基准数，通过一趟排序将要排序的数据分割成独立的两部分；其中一部分的所有数据都比另外一部分的所有数据都要小。然后，再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。

- 排序 - 插入排序(Insertion Sort)

  直接插入排序(Straight Insertion Sort)的基本思想是: 把n个待排序的元素看成为一个有序表和一个无序表。开始时有序表中只包含1个元素，无序表中包含有n-1个元素，排序过程中每次从无序表中取出第一个元素，将它插入到有序表中的适当位置，使之成为新的有序表，重复n-1次可完成排序过程。

- 排序 - Shell排序(Shell Sort)

  希尔排序实质上是一种分组插入方法。它的基本思想是: 对于n个待排序的数列，取一个小于n的整数gap(gap被称为步长)将待排序元素分成若干个组子序列，所有距离为gap的倍数的记录放在同一个组中；然后，对各组内的元素进行直接插入排序。 这一趟排序完成之后，每一个组的元素都是有序的。然后减小gap的值，并重复执行上述的分组和排序。重复这样的操作，当gap=1时，整个数列就是有序的。

- 排序 - Shell排序(Shell Sort)

  希尔排序实质上是一种分组插入方法。它的基本思想是: 对于n个待排序的数列，取一个小于n的整数gap(gap被称为步长)将待排序元素分成若干个组子序列，所有距离为gap的倍数的记录放在同一个组中；然后，对各组内的元素进行直接插入排序。 这一趟排序完成之后，每一个组的元素都是有序的。然后减小gap的值，并重复执行上述的分组和排序。重复这样的操作，当gap=1时，整个数列就是有序的。

- 排序 - 选择排序(Selection sort)

  它的基本思想是: 首先在未排序的数列中找到最小(or最大)元素，然后将其存放到数列的起始位置；接着，再从剩余未排序的元素中继续寻找最小(or最大)元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。

- 排序 - 堆排序(Heap Sort)

  堆排序是指利用堆这种数据结构所设计的一种排序算法。堆是一个近似完全二叉树的结构，并同时满足堆积的性质：即子结点的键值或索引总是小于（或者大于）它的父节点。

- 排序 - 归并排序(Merge Sort)

  将两个的有序数列合并成一个有序数列，我们称之为"归并"。归并排序(Merge Sort)就是利用归并思想对数列进行排序。

- 排序 - 桶排序(Bucket Sort)

  桶排序(Bucket Sort)的原理很简单，将数组分到有限数量的桶子里。每个桶子再个别排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排序）

- 排序 - 基数排序(Radix Sort)

  它的基本思想是: 将整数按位数切割成不同的数字，然后按每个位数分别比较。具体做法是: 将所有待比较数值统一为同样的数位长度，数位较短的数前面补零。然后，从最低位开始，依次进行一次排序。这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列

**学习推荐**

- 学习排序 - [动画展示排序](https://www.cs.usfca.edu/~galles/visualization/ComparisonSort.html)

不同情况下排序选择

在不同的情形下，排序速度前三名也不尽相同:

| 排序场景      | 排序效率                         |
| ------------- | -------------------------------- |
| Random        | 希尔>快排>归并                   |
| Few unique    | 快排>希尔>归并                   |
| Reversed      | 快排>希尔>归并                   |
| Almost sorted | 插入排序>基数排序>快排>希尔>归并 |

总结来看: 快速排序和希尔排序在排序速度上表现是比较优秀的,而归并排序稍微次之.

## 排序 - 冒泡排序(Bubble Sort)

> 它是一种较简单的排序算法。它会遍历若干次要排序的数列，每次遍历时，它都会从前往后依次的比较相邻两个数的大小；如果前者比后者大，则交换它们的位置。这样，一次遍历之后，最大的元素就在数列的末尾！ 采用相同的方法再次遍历时，第二大的元素就被排列在最大元素之前。重复此操作，直到整个数列都有序为止！

### 冒泡排序的实现

下面以数列{20,40,30,10,60,50}为例，演示它的冒泡排序过程(如下图)。

![img](https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/alg/alg-sort-bubble-1.jpg)

我们先分析第1趟排序

- 当i=5,j=0时，a[0]<a[1]。此时，不做任何处理！
- 当i=5,j=1时，a[1]>a[2]。此时，交换a[1]和a[2]的值；交换之后，a[1]=30，a[2]=40。
- 当i=5,j=2时，a[2]>a[3]。此时，交换a[2]和a[3]的值；交换之后，a[2]=10，a[3]=40。
- 当i=5,j=3时，a[3]<a[4]。此时，不做任何处理！
- 当i=5,j=4时，a[4]>a[5]。此时，交换a[4]和a[5]的值；交换之后，a[4]=50，a[3]=60。

于是，第1趟排序完之后，数列{20,40,30,10,60,50}变成了{20,30,10,40,50,60}。此时，数列末尾的值最大。

根据这种方法:

- 第2趟排序完之后，数列中a[5...6]是有序的。
- 第3趟排序完之后，数列中a[4...6]是有序的。
- 第4趟排序完之后，数列中a[3...6]是有序的。
- 第5趟排序完之后，数列中a[1...6]是有序的。整个数列也就是有序的了。

### 复杂度和稳定性

冒泡排序的时间复杂度是O(N2)。 假设被排序的数列中有N个数。遍历一趟的时间复杂度是O(N)，需要遍历多少次呢? N-1次！因此，冒泡排序的时间复杂度是O(N2)。

冒泡排序是稳定的算法，它满足稳定算法的定义。 算法稳定性 -- 假设在数列中存在a[i]=a[j]，若在排序之前，a[i]在a[j]前面；并且排序之后，a[i]仍然在a[j]前面。则这个排序算法是稳定的！

### 代码实现

````java
/**
 * 冒泡排序: Java
 *
 * @author skywang
 * @date 2014/03/11
 */

public class BubbleSort {

    /*
     * 冒泡排序
     *
     * 参数说明: 
     *     a -- 待排序的数组
     *     n -- 数组的长度
     */
    public static void bubbleSort1(int[] a, int n) {
        int i,j;

        for (i=n-1; i>0; i--) {
            // 将a[0...i]中最大的数据放在末尾
            for (j=0; j<i; j++) {
                if (a[j] > a[j+1]) {
                    // 交换a[j]和a[j+1]
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                }
            }
        }
    }

    /*
     * 冒泡排序(改进版)
     *
     * 参数说明: 
     *     a -- 待排序的数组
     *     n -- 数组的长度
     */
    public static void bubbleSort2(int[] a, int n) {
        int i,j;
        int flag;                 // 标记

        for (i=n-1; i>0; i--) {
            flag = 0;            // 初始化标记为0
            // 将a[0...i]中最大的数据放在末尾
            for (j=0; j<i; j++) {
                if (a[j] > a[j+1]) {
                    // 交换a[j]和a[j+1]
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    flag = 1;    // 若发生交换，则设标记为1
                }
            }

            if (flag==0)
                break;            // 若没发生交换，则说明数列已有序。
        }
    }

    public static void main(String[] args) {
        int i;
        int[] a = {20,40,30,10,60,50};

        System.out.printf("before sort:");
        for (i=0; i<a.length; i++)
            System.out.printf("%d ", a[i]);
        System.out.printf("\n");

        bubbleSort1(a, a.length);
        //bubbleSort2(a, a.length);

        System.out.printf("after  sort:");
        for (i=0; i<a.length; i++)
            System.out.printf("%d ", a[i]);
        System.out.printf("\n");
    }
}
````

## 排序 - 快速排序(Quick Sort)



## 排序 - 插入排序(Insertion Sort)



## 排序 - Shell排序(Shell Sort)



## 排序 - 选择排序(Selection sort)



## 排序 - 堆排序(Heap Sort)



## 排序 - 归并排序(Merge Sort)



## 排序 - 桶排序(Bucket Sort)



## 排序 - 基数排序(Radix Sort)

