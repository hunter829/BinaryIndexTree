# BinaryIndexTree
Learning a new data structure BIT
```java
package com.company;
/**
 * Created by hongruzh on 5/22/17.
 */
public class BinaryIndexTree {

        private int[] nums;
        private int[] BITree;
        public BinaryIndexTree(int[] nums) {
            int len = nums.length;
            this.nums = new int[len];
            this.BITree = new int[len + 1];
            for( int i = 0; i < len; i++){
                update(i, nums[i]);
            }
        }
        public void update(int i, int val) {
            int diff = val - this.nums[i];
            this.nums[i] = val;
            int idx = i + 1;

            while( idx < this.BITree.length){
                this.BITree[idx] += diff;
                idx += idx & (-idx);
            }

        }
        public int getSum(int i){
            int sum = 0;
            i = i + 1;
            while(i > 0){
                sum += this.BITree[i];
                i -= i & (-i);
            }

            return sum;
        }
        public int sumRange(int i, int j) {

            //return this.BITree[j];
            return getSum(j) - getSum(i - 1);

        }
    }

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
}

```

![BITUpdate12.png](/Users/hongruzh/Desktop/BinaryIndexTree/BITUpdate12.png)

一. lowerbits(x) 函数
lowerbits 函数代表在一个 在二进制表达式当中，最右边1所对应的值
比如对于X =  1234 1234的二进制是0100 1101 0010 lowbits(1234) = 2

Lowbit(x)=x&-x;（为什么这样写呢？因为计算机内部采用补码表示，-x是x按位取反，尾数+1的结果）
计算机中负数是按照补码来存的，补码的计算形式就是 正数的反码+1

二.解释 God mad this formula 

index+（index&(-index)）
index-（index&(-index)）

树状数组的值，在logn的时间复杂度里面求和并且进行节点更新操作, 有一点可以确定的是binary index tree是根据index来的构建的树，无论怎么样，binary index tree的结构都是不变的，比如说给你一个index为11的节点，他的父亲节点噎死固定的，可以通过数学公式计算出来。

对于某一个index为确定的某一个数，按照公式index - index&(-index) 就能求出来 index所在位置的的父亲节点

如果我们要update 某一个数的的时候，这个数后面所有的sum都要受到影响，那么如何去找到后面这些受到影响的数呢
这个时候 我们就用公式 index + index&(-index) 然后去更新这些index的和就可以了