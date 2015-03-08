import java.util.List;

/**
 * JSONのkeyとvalueを保持するクラス
 * @author kazuma
 * @param <T> JSONのvalue
 */
public class JNode<T>{
  private String key;   //キー
  private T val;        //値
  private JNode<?> next;//次の要素
  
  /**
   * keyとvalueをセットするコンストラクタ
   * @param key　String型のkey
   * @param val　value(型はString,Integer,JNodeHandler)
   */
  JNode(String key, T val){
    this.key = key;
    this.val = val;
  }
  
  /**
   * toString
   * JSONのフォーマットにして返す(key:value)
   */
  @Override
  public String toString(){
    //keyの部分
    String k = "\"" + this.key + "\":";
    
    /** valueの型によって処理をかえる **/
    //Stringの場合、ダブルクウォートで囲って返す
    if (this.val instanceof String) return k + "\"" + this.val + "\"";
    
    //JNodeHandlerの時、toStringメソッドを呼び出す
    if (this.val instanceof JNodeHandler) return k + this.val.toString();
    
    //Listの時、[value1, value2, value3, .... , valueN]にして返す
    if (this.val instanceof List){
      StringBuffer sb = new StringBuffer();
      sb.append(k +"[");
      
      /**Listの要素の型によって処理を変える**/
      Object o = ((List) this.val).get(0);
      int index = o instanceof String? 0 : 1;//フラグ(booleanにしなかったのは他に型が増えた時のため)
      for(int i = 0; i<((List) this.val).size(); i++){
        if(i != 0) sb.append(",");
        
        switch(index){
        //Stringのとき
        case 0 : sb.append("\"" + ((List) this.val).get(i) + "\"");
                 break;
        //String以外の時
        case 1 : sb.append(((List) this.val).get(i).toString());
        }
      }
      sb.append("]");
      return sb.toString();
    }
    //String, JNodeHandler,List以外のとき(Integerなど)
    else  return k + this.val;
  }
  
  /**
   * 次の要素を取得するメソッド(getter)
   * @return JNode(次の要素)
   */
  public JNode<?> getNext() {
    return next;
  }

  /**
   * 渡されたJNodeを次の要素としてセットするメソッド(setter)
   * @param next JNode(次の要素)
   */
  public void setNext(JNode<T> next) {
    this.next = next;
  }
  
}