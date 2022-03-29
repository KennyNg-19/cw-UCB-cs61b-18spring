# cw-UCB-cs61b-17spring
Data Structures in Java

Summary of rules in Java MAINly:

1. 2 common approaches to implement a sequence ds: **Array** provided by java and **Linked nodes**.
2. BST: extended the idea of <u>linked nodes</u> to implement a tree data structure. 
3. Hashmap.resize(int cap)的cap不能设置为DEFAULT_SIZE*2(常量式地翻倍)，而是buckets.当前length, 是变量:(因为根据定义是这样扩容的，test case是也是根据后者来定义的)
4. 抽象的结构上，Binary min-heaps are basically just <u>abstract</u> binary Complete trees (but *not* BST), but <u>abstract</u> tree's implementation is still **Array**；既然是tree ADT, swim/bubble方法和sink方法不一样，前者是<u>向上</u>比，node和**唯一的**parent比较，而sink是<u>向下</u>，因为**binary**的性质，会有俩node所以是parent和left right node中min的swap，多了一次取min
