package com.jungle.algorithm;

/**
 * Created by Administrator on 2017/1/21 0021.
 */
public class BSearch {
    public static void main(String[] args){
        Integer[] sourceDatas = new Integer[]{1,3,4,5};
        Integer target = 2;
        int find = bSearch(sourceDatas,target);
        System.out.println(find);
    }

    public static  <T extends Comparable<T>> int bSearch(T[] data,T target){
        int findIndex = -1;
        int start = 0;
        int end = data.length-1;
        while (end>=start){
            int middle = (start+end)/2;
            T current = data[middle];
            if(current.compareTo(target)>0){
                end = middle -1;
            }else if (current.compareTo(target)<0){
                start = middle+1;
            }else{
                findIndex = middle;
                break;
            }
        }
        return findIndex;
    }
}
