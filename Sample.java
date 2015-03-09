import java.util.*;

/**
 * JSONに変換されるかどうか試す
 * @author kazuma
 */
public class Sample {
  public static void main(String[] args){
    //単純なJSON
    JNodeHandler jnh0 = new JNodeHandler();
    jnh0.add("あ行", "あいうえお");
    jnh0.add("か行", "かきくけこ");
    jnh0.add("さ行", "さしすせそ");
    System.out.println(jnh0.toString());
    
    //オブジェクトが入れ子になっているパターン
    JNodeHandler jnh1 = new JNodeHandler();
    jnh1.add("ア行","アイウエオ");
    jnh1.add("カ行","カキクケコ");
    jnh1.add("サ行","サシスセソ");
    
    JNodeHandler jnh2 = new JNodeHandler();
    jnh2.add("ひらがな", jnh0);
    jnh2.add("カタカナ", jnh1);
    
    System.out.println(jnh2.toString());
    
    //様々な値が入っているパターン
    JNodeHandler jnh3 = new JNodeHandler();
    jnh3.add("文字数", 26);
    List<String> rom = new ArrayList<String>();
    String[] roman = {"A","B","C","D","E","F","G","H"};
    for(int i = 0; i < roman.length; i++) rom.add(roman[i]);
    jnh3.add("文字", rom);
    
    JNodeHandler jnh4 = new JNodeHandler();
    jnh4.add("文字数", 50);
    jnh4.add("文字", jnh2);
    
    JNodeHandler jnh5 = new JNodeHandler();
    jnh5.add("日本語", jnh4);
    jnh5.add("英語(仮)", jnh3);
    System.out.println(jnh5.toString());
    
  }

}
