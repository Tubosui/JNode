import java.sql.ResultSet;
import java.sql.SQLException;
/**
*単純なJSON形式に整えるメソッド
*（イベント当日にわざわざクラスを使う必要がないと判断し実装したため、複雑な処理はないが、
*その分粗い感じ） 
*
*/

public class SimpleJSON {
  /**
   * JSON形式に変換するメソッドを定義
   * @param key　JSONのkeyの配列
   * @param rs  　DBのSQLの結果
   * @param isNotString　n番目のvalueをダブルクウォートで囲うかどうか
   * @return [{"key1":"value1"},{"key2":value2},{・・・},{"keyN":"valueN"}] 形式のString
   * @throws Exception 
   */
  public static String getJson (String[] key, ResultSet rs, boolean[] isNotString) throws Exception{
    StringBuilder sb = new StringBuilder();
    try{
      sb.append("[");
      boolean flag = true;
      while (rs.next()) { 
        if (flag){
          sb.append("{");
          flag = false;
        } else {
          sb.append(",{");
        }
        
        for (int i = 0; i<key.length; i++){
          if (i != 0) sb.append(",");
          if (isNotString[i]) sb.append(getJNode(key[i], rs.getString(key[i]))); 
          else sb.append(getJNodeQ(key[i], rs.getString(key[i])));
        }
        
        sb.append("}");
      }
      if(flag) throw new Exception("404");
      sb.append("]");
      
    } catch (SQLException e){
      return "{\"error\":" + "\"" + e.getMessage() + "\"}"; 
    } finally {
      if (rs!=null)
        try {
          rs.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
    }
    return sb.toString();
  }
  
  
  /**
   * JSONの形式　"key":"value"　にするメソッドを定義
   * @param key JSONのkey
   * @param value JSONのvalue
   * @return "key":"value"
   */
  public static String getJNodeQ(String key, String value){
    return "\"" + key + "\":\"" + value + "\"";
  }
  
  /**
   * JSONの形式　"key":value にするメソッド(String以外)
   * @param key　JSONのkey
   * @param value JSONのvalue
   * @return "key":value
   */
  public static String getJNode(String key, String value){
    return "\"" + key + "\":" + value;
  }
}
