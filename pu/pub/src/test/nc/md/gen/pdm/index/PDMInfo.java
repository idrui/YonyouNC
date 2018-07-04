/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-25 上午11:37:11
 */
package nc.md.gen.pdm.index;

import java.util.Map;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>解析后的PDM文件信息
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-7-25 上午11:37:11
 */
public class PDMInfo {
  private Map<String, TabIndex[]> indexes;

  private Map<String, Map<String, String>> tableColumIDMap;

  public Map<String, TabIndex[]> getIndexes() {
    return this.indexes;
  }

  public Map<String, Map<String, String>> getTableColumIDMap() {
    return this.tableColumIDMap;
  }

  public void setIndexes(Map<String, TabIndex[]> indexes1) {
    this.indexes = indexes1;
  }

  public void setTableColumIDMap(
      Map<String, Map<String, String>> tableColumIDMap1) {
    this.tableColumIDMap = tableColumIDMap1;
  }

}
