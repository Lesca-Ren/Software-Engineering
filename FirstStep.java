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

    public static HashMap<String,Integer> hm = new HashMap<String,Integer>();

    public static node[] res;
    
    public static void main(String args[]) {
        String s=new String(args[0]);
        res = new node[20004];
        for(int i=0;i<=20000;i++){
            res[i] = new node();
        }
        readFile(s);
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
}


