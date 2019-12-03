package codes.normal;

import java.util.ArrayList;
import java.util.List;

public class Zuhe {

    private static List<List<String>> list = new ArrayList<>();

    private static void zuhe(String str){
        List<String> res ;
        for(int i=1;i<=str.length();i++){
            res = new ArrayList<>();
            digui(str,res,i,0);
        }

    }

    private static void digui(String str,List<String> res,int index,int current){
        if(res.size() == index){
            list.add(new ArrayList<>(res));
            return;
        }
        for(int i=current;i<str.length();i++){
            res.add(str.charAt(i)+"");
            digui(str,res,index,i+1);
            res.remove(res.size()-1);
        }
    }

    public static void main(String[] args) {
        zuhe("abcd");
        System.out.println(list);
    }

}
