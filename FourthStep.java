import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
 
import java.util.Iterator;
import java.util.Map;


public class FourthStep {

    public static class node implements Comparable<node>{
        private String key;
        private int val;
        private int num;

       /**
        * @return the key
        */
       public String getKey() {
           return key;
       }
       
       /**
        * @return the val
        */
       public int getVal() {
           return val;
       }

       /**
        * @return the num
        */
       public int getNum() {
           return num;
       }

       /**
        * @param key the key to set
        */
       public void setKey(String key) {
           this.key = key;
       }

       /**
        * @param val the val to set
        */
       public void setVal(int val) {
           this.val = val;
       }

       /**
        * @param num the num to set
        */
       public void setNum(int num) {
           this.num = num;
       }
       @Override
        public int compareTo(node a) {
            if(this.num-a.num != 0){
                return a.num-this.num;
            }
            else if(this.val-a.val != 0)
                return a.val-this.val; 
            else{
                char[] chars1=a.key.toCharArray();
                char[] chars2=this.key.toCharArray();
                int i=0;
                while(i<chars1.length && i<chars2.length){
                    if(chars1[i]<chars2[i]){
                        return 1;
                    }else if(chars1[i]>chars2[i]){
                        return -1;
                    }else{
                        i++;
                    }
                }
                if(i==chars1.length){  //o1到头
                    return 1;
                }
                if(i== chars2.length){ //o2到头
                    return -1;
                }
                return 0;
            }       
        } 
    }

    public static class FileAccept implements FilenameFilter{
        private String extendName;
        public void setExtendName(String s){
            extendName = "."+s;
        }
        public boolean accept(File dir,String name){
            return name.endsWith(extendName);
        }
    }
    
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
    public static node[] res;
    public static String [] stopwords;
    public static String [][] verbs;
    public static int stopWordsLength;
    
    public static void main(String args[]) {
        String f=new String(args[0]);
        // System.out.println(f);
        res = new node[20004];
        for(int i=0;i<=20000;i++){
            res[i] = new node();
        }
        if(f.equals("-f")){
            String s=new String(args[1]);
            function1(readFile(s));
        }else if(f.equals("-c")){
            x=new S[600];
            String s=new String(args[1]);
            readFile0(s);
        }else if(f.equals("-n")){
            String s=new String(args[2]);
            function2(readFile(s),Integer.valueOf(args[1]));
        }else if(f.equals("-d")){
            if(args[1].equals("-s")){
                File dirFile = new File(args[2]);
                listAll(dirFile,0);
            }else{
                File dirFile = new File(args[1]);
                FileAccept fileAccept = new FileAccept();
                fileAccept.setExtendName("txt");
                String fileName [] = dirFile.list(fileAccept);
                for(String name:fileName){
                    // System.out.println(name);
                    readFile(dirFile+"/"+name);
                }
            }
        }else if(f.equals("-x")){
            String s=new String(args[1]);
            // System.out.println(s);
            function3(readFile(s));
            String s1=new String(args[3]);
            // System.out.println(s1);
            function1(readFile(s1));
        }else if(f.equals("-p")){
            String s=new String(args[2]);
            function4(readFile1(s),Integer.valueOf(args[1]));
        }else if(f.equals("-v")){
            String s=new String(args[1]);
            String s1=new String(args[2]);
            function5(readFile(s));
        }
    }

    public static void listAll(File dir,int level){
        System.out.println(getSpace(level)+dir.getName());
        level++;//每调用一次，level加1
        File[] files=dir.listFiles();//获取指定目录下当前的所有文件夹或者文件对象
        for(int x=0;x<files.length;x++){
            if(files[x].isDirectory()){
                listAll(files[x], level);
            }else{
                System.out.println(getSpace(level)+files[x].getName());
            }
        }

    }
    
    public static String getSpace(int level){
        StringBuilder sb = new StringBuilder();
        sb.append("|--");
        for(int x= 0;x<level;x++){
            sb.insert(0,"|  ");
        }
        return sb.toString();
    }
    
    public static void readFile0(String args) {
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
                System.out.println(line);
                int [] y=new int[600];
                for(int i=1;i<=26;i++){
                    y[i]=0;
                }
                char [] c;int len=line.length();
                System.out.println(len);
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
                    x[i].setCnt(x[i].getCnt()+y[i]);
                }
            }
            for(int i=1;i<=26;i++){
                x[i].setValue(100*x[i].getCnt()*1.0/count);;
                // System.out.println(x[i].getIndex()+"     "+x[i].getValue());
            }
            Arrays.sort(x, 1, 26);

            for(int i=1;i<=26;i++){
                System.out.printf("%c ", x[i].getIndex()+'a'-1);
                System.out.printf("%.2f%% %d\n",x[i].getValue(),x[i].getCnt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static HashMap<String,Integer> readFile1(String args) {
        HashMap<String,Integer> hm = new HashMap<String,Integer>();
        String pathname = args; 
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader) 
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                String str = line.toLowerCase();
                String str1;
                int begin=0;
                for(int i=0;i<str.length();i++){
                    if(Character.isLetter(str.charAt(i))){
                        begin=i;
                        str1 = str.substring(begin,i);
                        i++;
                        while((i<str.length()) &&  (Character.isLetter(str.charAt(i)) || Character.isDigit(str.charAt(i))) ){
                            i++;
                        }
                        while(i<str.length() && str.charAt(i)==' ' && Character.isLetter(str.charAt(i+1))){
                            i++;
                            while((i<str.length()) &&  (Character.isLetter(str.charAt(i)) || Character.isDigit(str.charAt(i))) ){
                                i++;
                            }
                        }
                        i--;
                        str1=str.substring(begin,i+1);
                        if(!(hm.containsKey(str1))){
                            hm.put(str1, 1);
                        }else{
                            int value=hm.get(str1)+1;
                            hm.put(str1, value);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hm;
    }

    public static HashMap<String,Integer> readFile(String args) {
        HashMap<String,Integer> hm = new HashMap<String,Integer>();
        String pathname = args; 
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader) 
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                String str = line.toLowerCase();
                String str1;
                int begin=0;
                for(int i=0;i<str.length();i++){
                    if(Character.isLetter(str.charAt(i))){
                        begin=i;
                        str1 = str.substring(begin,i);
                        i++;
                        while((i<str.length()) &&  (Character.isLetter(str.charAt(i)) || Character.isDigit(str.charAt(i))) ){
                            i++;
                        }
                        i--;
                        str1=str.substring(begin,i+1);
                        if(!(hm.containsKey(str1))){
                            hm.put(str1, 1);
                        }else{
                            int value=hm.get(str1)+1;
                            hm.put(str1, value);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hm;
    }
    public static boolean findStopWords(String t){
        // System.out.println(stopWordsLength);
        for(int i = 0;i<stopWordsLength;i++){
            // System.out.println(stopwords[i].equals(t));
            if(stopwords[i].equals(t)){
                return true;
            }
        }
        return false;
    }
    public static void function1(HashMap<String,Integer> hm){
        Iterator iter = hm.entrySet().iterator();
        int index=0;
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            if(!(findStopWords(key.toString()))){
                res[index].setKey(key.toString());
                res[index].setVal(Integer.valueOf(val.toString()));
                String temp = new String(res[index].getKey());
                int cnt=1;
                for(int i=0;i<temp.length();i++){
                    if(temp.charAt(i)==' '){
                        cnt++;
                    }
                }
                res[index].setNum(cnt);
                // System.out.println("key:"+res[index].getKey()+" "+"val:"+res[index].getVal());
                index++;
            }
        }
        Arrays.sort(res,0,index);
        for(int i=0;i<index;i++){
            System.out.println("key:"+res[i].getKey()+" "+"val:"+res[i].getVal()+" num:"+res[i].getNum());
        }
    }

    public static void function2(HashMap<String,Integer> hm,int n){
        Iterator iter = hm.entrySet().iterator();
        int index=0;
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            res[index].setKey(key.toString());
            res[index].setVal(Integer.valueOf(val.toString()));
            // System.out.println("key:"+res[index].getKey()+" "+"val:"+res[index].getVal());
            index++;
        }
        Arrays.sort(res,0,index);
        for(int i=0;i<n;i++){
            System.out.println("key:"+res[i].getKey()+" "+"val:"+res[i].getVal());
        }
    }

    public static void function3(HashMap<String,Integer> hm){
        Iterator iter = hm.entrySet().iterator();
        int index=0;
        stopwords = new String[2000];
        while (iter.hasNext()) {
            stopwords[index] = new String();
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            // System.out.println(key);
            stopwords[index]=key.toString();
            System.out.println("key:"+stopwords[index]);
            index++;
        }
        stopWordsLength = index--;
    }


    public static void function4(HashMap<String,Integer> hm,int n){
        Iterator iter = hm.entrySet().iterator();
        int index=0;
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            if(!(findStopWords(key.toString()))){
                res[index].setKey(key.toString());
                res[index].setVal(Integer.valueOf(val.toString()));
                String temp = new String(res[index].getKey());
                int cnt=1;
                for(int i=0;i<temp.length();i++){
                    if(temp.charAt(i)==' '){
                        cnt++;
                    }
                }
                res[index].setNum(cnt);
                System.out.println("key:"+res[index].getKey()+" "+"val:"+res[index].getVal());
                index++;
            }
        }
        Arrays.sort(res,0,index);
        for(int i=0;i<index;i++){
            if(res[i].getNum() == n){
                System.out.println("key:"+res[i].getKey()+" "+"val:"+res[i].getVal()+" num:"+res[i].getNum());
            }
            
        }
    }

    public static void function5(HashMap<String,Integer> hm){
        Iterator iter = hm.entrySet().iterator();
        int index=0;
        verbs = new String[20][2000];
        // while (iter.hasNext()) {
        //     stopwords[index] = new String();
        //     Map.Entry entry = (Map.Entry) iter.next();
        //     Object key = entry.getKey();
        //     // System.out.println(key);
        //     stopwords[index]=key.toString();
        //     System.out.println("key:"+stopwords[index]);
        //     index++;
        // }
        // stopWordsLength = index--;
    }
}


