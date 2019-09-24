// package com.nickwong.code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


public class ReadTxt {
    public static class S implements Comparable<S>{
        public int index;
        public int cnt;
        public double value;
    
        public S(int index,int cnt,double value){
            this.index=index;
            this.cnt=cnt;
            this.value=value;
        }
    
       /**
         * @param value the value to set
         */
        public void setIndex(int index) {
            this.index = index;
        }
    
        /**
         * @param cnt the cnt to set
         */
        public void setCnt(int cnt) {
            this.cnt = cnt;
        }
    
        public void setValue(Double value) {
            this.value = value;
        }
    
        /**
         * @return the index
         */
        public int getIndex() {
            return index;
        }
    
        public int getCnt(){
            return cnt;
        }
    
       /**
         * @return the value
         */
        public double getValue() {
            return value;
        }
        @Override
        public int compareTo(S a) {
            if(this.value-a.value != 0)
                return this.cnt-a.cnt; 
            else 
                return this.index-a.index;
            } 
    }
    
    public static int count;
    public static S [] x;
    
    public static void main(String args[]) {
        x=new S[600];
        String s=new String(args[0]);
        // System.out.println(s);
        readFile(s);
    }

    public static void readFile(String args) {
        count=0;
        String pathname = args; 
        for(int i=1;i<=100;i++){
            x[i]=new S(i,0,0);
        }
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader) 
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
                int [] y=new int[600];
                for(int i=1;i<=26;i++){
                    y[i]=0;
                }
                char [] c;int len=line.length();
                c=line.toCharArray();
                for(int i=0;i<len;i++){
                    if((c[i]>='a'&& c[i]<='z') || (c[i]>='A' && c[i]<='Z')){
                        count++;
                        switch (c[i]) {
                            case 'a':
                                y[1]++;
                                break;
                            case 'b':
                                y[2]++;
                                break;
                            case 'c':
                                y[3]++;
                                break;
                            case 'd':
                                y[4]++;
                                break;
                            case 'e':
                                y[5]++;
                                break;
                            case 'f':
                                y[6]++;
                                break;
                            case 'g':
                                y[7]++;
                                break;
                            case 'h':
                                y[8]++;
                                break;
                            case 'i':
                                y[9]++;
                                break;
                            case 'j':
                                y[10]++;
                                break;
                            case 'k':
                                y[11]++;
                                break;
                            case 'l':
                                y[12]++;
                                break;
                            case 'm':
                                y[13]++;
                                break;
                            case 'n':
                                y[14]++;
                                break;
                            case 'o':
                                y[15]++;
                                break;
                            case 'p':
                                y[16]++;
                                break;
                            case 'q':
                                y[17]++;
                                break;
                            case 'r':
                                y[18]++;
                                break;
                            case 's':
                                y[19]++;
                                break;
                            case 't':
                                y[20]++;
                                break;
                            case 'u':
                                y[21]++;
                                break;
                            case 'v':
                                y[22]++;
                                break;
                            case 'w':
                                y[23]++;
                                break;
                            case 'x':
                                y[24]++;
                                break;
                            case 'y':
                                y[25]++;
                                break;
                            case 'z':
                                y[26]++;
                                break;
                            case 'A':
                                y[1]++;
                                break;
                            case 'B':
                                y[2]++;
                                break;
                            case 'C':
                                y[3]++;
                                break;
                            case 'D':
                                y[4]++;
                                break;
                            case 'E':
                                y[5]++;
                                break;
                            case 'F':
                                y[6]++;
                                break;
                            case 'G':
                                y[7]++;
                                break;
                            case 'H':
                                y[8]++;
                                break;
                            case 'I':
                                y[9]++;
                                break;
                            case 'J':
                                y[10]++;
                                break;
                            case 'K':
                                y[11]++;
                                break;
                            case 'L':
                                y[12]++;
                                break;
                            case 'M':
                                y[13]++;
                                break;
                            case 'N':
                                y[14]++;
                                break;
                            case 'O':
                                y[15]++;
                                break;
                            case 'P':
                                y[16]++;
                                break;
                            case 'Q':
                                y[17]++;
                                break;
                            case 'R':
                                y[18]++;
                                break;
                            case 'S':
                                y[19]++;
                                break;
                            case 'T':
                                y[20]++;
                                break;
                            case 'U':
                                y[21]++;
                                break;
                            case 'V':
                                y[22]++;
                                break;
                            case 'W':
                                y[23]++;
                                break;
                            case 'X':
                                y[24]++;
                                break;
                            case 'Y':
                                y[25]++;
                                break;
                            case 'Z':
                                y[26]++;
                                break;
                            
                        }
                    }
                }
                for(int i=1;i<=26;i++){
                    x[i].setCnt(y[i]);
                }
            }
            for(int i=1;i<=26;i++){
                x[i].setValue(100*x[i].getCnt()*1.0/count);;
                // System.out.println(x[i].getIndex()+"     "+x[i].getValue());
            }
            Arrays.sort(x, 1, 26);

            for(int i=1;i<=26;i++){
                System.out.printf("%c ", x[i].getIndex()+'a'-1);
                System.out.printf("%.2f%%\n",x[i].getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


