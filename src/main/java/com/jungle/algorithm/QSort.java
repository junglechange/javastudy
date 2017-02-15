package com.jungle.algorithm;

/**
 * Created by Administrator on 2017/1/30 0030.
 */
public class  QSort <T extends Comparable<T>> {
    public static void main(String[] args){
        QSort<Integer> sort = new QSort<>();
        Integer[] source = new Integer[]{2,7,2,2,3,4,4,2,1,1};
        sort.qSort(source,0,source.length-1);
        for(Integer num:source){
            System.out.println(num);
        }

    }
    public void qSort(T[] source,int start, int end){
        int k = divide(source,start,end);
        if (k>=0){
            qSort(source,start,k-1);
            qSort(source,k+1,end);
        }
    }
    public int divide(T[] source,int start, int end){
        if(start >= end){
            return -1;
        }
        T pivot = source[start];
        int forwardPoint = start;
        int backPoint = end;
        while (forwardPoint<backPoint){
            while (backPoint>forwardPoint){
                if(source[backPoint].compareTo(pivot)<0){
                    break;
                }
                --backPoint;
            }
            source[forwardPoint] = source[backPoint];
            while (backPoint>forwardPoint){
                if(source[forwardPoint].compareTo(pivot)>=0){
                    break;
                }
                ++forwardPoint;
            }
            source[backPoint] = source[forwardPoint];
        }
        source[forwardPoint]=pivot;
        return forwardPoint;
    }
}
