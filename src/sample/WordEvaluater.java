package sample;

import java.util.HashSet;
import java.util.Set;


public class WordEvaluater {

    public static double evaluateSimilarytyByChars(String s1,String s2){

        //K = c/(a+b-c)

        Set<String> nx = new HashSet<String>();
        Set<String> ny = new HashSet<String>();

        for (int i=0; i < s1.length()-1; i++) {
            char x1 = s1.charAt(i);
            char x2 = s1.charAt(i+1);
            String tmp = "" + x1 + x2;
            nx.add(tmp);
        }
        for (int j=0; j < s2.length()-1; j++) {
            char y1 = s2.charAt(j);
            char y2 = s2.charAt(j+1);
            String tmp = "" + y1 + y2;
            ny.add(tmp);
        }

        Set<String> intersection = new HashSet<String>(nx);
        intersection.retainAll(ny);
        double totcombigrams = intersection.size();

        return (2*totcombigrams) / (nx.size()+ny.size());
    }


    public static double evaluateSimilarytyByWords(String s1, String s2){

        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();

        String [] s1Words = s1.split("\\s");
        String [] s2Words = s2.split("\\s");

        for(String s : s1Words){
            set1.add(s);
        }

        for(String s : s2Words){
            set2.add(s);
        }


        Set<String> intersection = new HashSet<String>(set1);
        intersection.retainAll(set2);

        double totcombigrams = intersection.size();

        return (2*totcombigrams) / (set1.size()+set2.size());
    }

    public static double evaluateEqualsSkip(String s1,String s2){
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();

        String [] s1Words = s1.split("\\s");
        String [] s2Words = s2.split("\\s");

        for(String s : s1Words){
            set1.add(s);
        }

        for(String s : s2Words){
            set2.add(s);
        }


        Set<String> set1NotEquals = new HashSet<String>(){
            @Override
            public String toString() {
                return super.toString().replaceAll("\\[","").replaceAll("\\]","").replaceAll("\\s","");
            }
        };
        Set<String> set2NotEquals = new HashSet<String>(){
            @Override
            public String toString() {
                return super.toString().replaceAll("\\[","").replaceAll("\\]","").replaceAll("\\s","");
            }
        };
        for (String s: set1) {
            if(!set2.contains(s)){
                set1NotEquals.add(s);
            }
        }

        for (String s: set2) {
            if(!set1.contains(s)){
                set2NotEquals.add(s);
            }
        }



        System.out.println(set1NotEquals);
        System.out.println(set2NotEquals);

        return evaluateSimilarytyByChars(set1NotEquals.toString(),set2NotEquals.toString());

    }

    public static double evaluateStrings(String s1,String s2){
        double byChars = evaluateSimilarytyByChars(s1,s2);
        double byWords = evaluateSimilarytyByWords(s1,s2);
        System.out.println("by chars : "+byChars+" "+"by words: "+byWords);
        return 0.4*byChars + 0.6 * byWords;
    }

}



