
/**
 * JNodeのラッパークラス
 * @author kazuma
 * 片方向リストっぽい感じ
 */
public class JNodeHandler {
  private JNode<?> first; //最初の要素
  private JNode<?> last;  //最後の要素
  
  JNodeHandler(){
  }
  
  /**
   * JNodeを追加するメソッド
   * @param key JSONのキーなる文字列
   * @param val JSONのバリューとなる文字列
   */
  <T> void add(String key, T val){
    //JNodeを生成して追加
    JNode<T> n = new JNode<T>(key, val);
    if(first == null){
      this.first = n;
      this.last = n;
    } else {
      this.last.setNext(n);
      this.last = this.last.getNext();
    }
  }
  
  /**
   * toStringメソッド
   * JSON形式にして返す
   */
  @Override
  public String toString(){
    //空の場合は空文字を返す
    if (this.first == null) return "";
    
    //最初の要素を取得
    JNode<?> n = this.first;
    StringBuffer sb = new StringBuffer();
    sb.append("{");
    boolean flag = true;//最初だけ前に「,」をつけないようにするためのフラグ
    while(n != null){
      if (flag) flag = false;
      else sb.append(",");
      //JNodeクラスのtoString()メソッドを呼び出し、追加
      sb.append(n.toString());
      //次の要素へ(nullになるまで)
      n = n.getNext();
    }
    sb.append("}");
    return sb.toString();
  }

}
