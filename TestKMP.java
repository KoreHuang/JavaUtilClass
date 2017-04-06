package test;


import org.junit.Test;

public class TestKMP {
        public static int [] getKMPNext(String src){
            int[] tmpNext =new int[src.length()+1];
            int[] next=new int[tmpNext.length];
            int j=0,k=-1;
            tmpNext[j]=k;
            while (j<src.length()){
             if(k==-1||src.charAt(k)==src.charAt(j)){
                 tmpNext[++j]=++k;
             }else {
                 k=tmpNext[k];
             }
            }
            j=1;
            while(j<src.length()){
                k=tmpNext[j];
                if(src.charAt(k)==src.charAt(j))
                    next[j]=next[k];
                else
                    next[j]=tmpNext[j];
                j++;
            }
            System.arraycopy(tmpNext,1,next,1,next.length-1);
            return next;
        }

        @Test
        public void testGetKMPNext(){
            String src="abcabcaaacaaacbbbabcdefg";
            int []next=getKMPNext(src);
            for (int i:next){
                System.out.println(i);
            }
        }



}
