package Tests;

import org.junit.jupiter.api.Test;

public class WordEvaluater {

    @Test
    public void getElementById_example_script(){

//        System.out.println(sample.WordEvaluater.evaluateSimilarytyByChars("Supreme®/LACOSTE Crewneck","Supreme®/LACOSTE Shoulder Bag"));
//        System.out.println(sample.WordEvaluater.evaluateSimilarytyByWords("Supreme®/LACOSTE Crewneck","Supreme®/LACOSTE Shoulder Bag"));
        System.out.println(sample.WordEvaluater.evaluateStrings("Supreme®/LACOSTE Velour Crusher","Supreme®/LACOSTE Velour Polo"));
        System.out.println(sample.WordEvaluater.evaluateEqualsSkip("Supreme®/LACOSTE Velour Crusher","Supreme®/LACOSTE Velour Polo"));

    }

}
