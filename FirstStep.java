import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
 
import java.util.Iterator;
import java.util.Map;


public class FirstStep {

    public static class node implements Comparable<node>{
        private String key;
        private int val;

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
    public static HashMap<String,Integer> hm = new HashMap<String,Integer>();

    public static node[] res;
    
    public static void main(String args[]) {
        String f=new String(args[0]);
        res = new node[20004];
        for(int i=0;i<=20000;i++){
            res[i] = new node();
        }
        if(f.equals("-f")){
            String s=new String(args[1]);
            readFile(s);
            function1();
        }else if(f.equals("-n")){
            String s=new String(args[2]);
            readFile(s);
            function2(Integer.valueOf(args[1]));
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
    
    public static void readFile(String args) {
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
    }

    public static void function1(){
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
        for(int i=0;i<index;i++){
            System.out.println("key:"+res[i].getKey()+" "+"val:"+res[i].getVal());
        }
    }

    public static void function2(int n){
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

}


