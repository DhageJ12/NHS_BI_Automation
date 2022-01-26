package com.tests;

import org.testng.annotations.Test;

public class TEst {

    @Test
    public void primenumber(){
        int start = 2;
        int end = 7;
        for(int i=start;i<=end;i++){
            if(i<=1){
                System.out.println("Not negative number");
            }else
            {
                for(int j=2;j<Math.sqrt(i);j++){
                    if(i%j!=0){
                        System.out.println("Prime number is "+i);
                    }
                }
            }
        }
    }

    @Test
    public void Frequency(){
        int [] a = new int[]{1,2,2,3,4,5,6,6,7};
        int [] a2 = new int[a.length];
        int visited = -1;
        for(int i=0;i<a.length;i++){
            int cnt =1;
            for(int j=i+1;j<a.length;j++){
                if(a[i] == a[j]){
                    cnt++;
                    a2[j]=visited;
                }
            }
            if(a2[i] != visited){
                a2[i]=cnt;
            }
        }
        for(int i=0;i<a2.length;i++){
            if(a2[i] != visited){
                System.out.println(a[i] +" "+a2[i]);
            }
        }
    }
}
