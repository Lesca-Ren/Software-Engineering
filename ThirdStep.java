import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
 
import java.util.Iterator;
import java.util.Map;


public class ThirdStep {

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
            if(this.val-a.val != 0)
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
    

    public static node[] res;
    public static String [] stopwords;
    public static int stopWordsLength;
    
    public static void main(String args[]) {
        String f=new String(args[0]);
        res = new node[20004];
        for(int i=0;i<=20000;i++){
            res[i] = new node();
        }
        // function3(readFile("stopwords.txt"));
        if(f.equals("-f")){
            String s=new String(args[1]);
            function1(readFile(s));
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
        }else if(f.equals("-p")){
            String s=new String(args[2]);
            function4(readFile(s),Integer.valueOf(args[1]));
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
            System.out.println("key:"+res[i].getKey()+" "+"val:"+res[i].getVal()+"num:"+res[i].getNum());
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
            // System.out.println("key:"+stopwords[index]);
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
                // System.out.println("key:"+res[index].getKey()+" "+"val:"+res[index].getVal());
                index++;
            }
        }
        Arrays.sort(res,0,index);
        for(int i=0;i<n;i++){
            System.out.println("key:"+res[i].getKey()+" "+"val:"+res[i].getVal());
        }
    }
}


