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


public class NumArray {
    
    private int[] nums;
    private int[] BITree;
    
    public NumArray(int[] nums) {
        int len = nums.length;
        this.nums = nums;
        this.BITree = new int[len + 1];
        for( int i = 0; i < len; i++){
            initBITree(i, nums[i]);
        }
        
    }
    
    public void initBITree(int i, int val){
        
        int idx = i + 1;
        while( idx < this.BITree.length){
            this.BITree[idx] += val;
            idx += idx & (-idx);
        }
        
    }
    
    public void update(int i, int val) {
        
        int len = this.nums.length;
        int diff = val - this.nums[i];
        this.nums[i] = val;/
        initBITree(i, diff);
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