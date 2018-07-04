/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-23 下午09:43:34
 */
package nc.md.gen.pdm.index;

import java.io.PrintWriter;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import nc.vo.jcom.lang.StringUtil;
import nc.vo.jcom.xml.XMLUtil;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-7-23 下午09:43:34
 */
public class IndexFileManager {
  public static final String INDEX_FILE = "tableindex.xml";

  public static final String INDEX_FILE_PATH = "nc/md/gen/pdm/index/";

  public static final String INDEX_NODE_TEMPLET_FILE = "indextemplate.xml";

  public static TabIndex[] loadIndexFile() {
    URL fileUrl =
        IndexFileManager.class.getClassLoader().getResource(
            IndexFileManager.INDEX_FILE_PATH + IndexFileManager.INDEX_FILE);
    if (null == fileUrl || StringUtil.isEmptyWithTrim(fileUrl.getFile())) {
      String sPath = IndexFileManager.INDEX_FILE;
      String sPathTo = IndexFileManager.INDEX_FILE_PATH;
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0", "04004000-0103", null, new String[]{sPath,sPathTo})/*请将索引配置文件（{0}）放到{1}下*/);
    }
    List<TabIndex> indices = new ArrayList<TabIndex>();
    try {
      Document doc = XMLUtil.getDocument(fileUrl);
      NodeList nlist = doc.getElementsByTagName(TabIndex.NODE_TABLE);
      for (int i = 0; i < nlist.getLength(); i++) {
        Node tabNode = nlist.item(i);
        String table =
            XMLUtil.getAtrributeValueOf(tabNode, TabIndex.TABLE_NAME);
        NodeList inxList = tabNode.getChildNodes();
        for (int j = 0; j < inxList.getLength(); j++) {
          Node inx = inxList.item(j);
          if (Node.ELEMENT_NODE == inx.getNodeType()
              && TabIndex.NODE_INDEX.equals(inx.getNodeName())) {
            TabIndex index = IndexFileManager.createTabIndex(table, inx);
            if (null != index) {
              indices.add(index);
            }
          }
        }
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return indices.toArray(new TabIndex[indices.size()]);
  }

  public static void saveToIndexFile(TabIndex[] indices) {
    try {
      MapList<String, TabIndex> inxMap =
          IndexFileManager.getTableIndexMap(indices);
      if (null == inxMap) {
        return;
      }
      Document doc = XMLUtil.newDocument();
      Element root = XMLUtil.createLeafElement(doc, "root", null);
      doc.appendChild(root);
      for (String table : inxMap.keySet()) {
        Node tabNode =
            IndexFileManager.createIndexNodes(doc, table, inxMap.get(table));
        if (null != tabNode) {
          root.appendChild(tabNode);
        }
      }
      Writer pw = new PrintWriter("f:/temp/oldpdmindex.xml", "UTF-8");
      XMLUtil.printDOMTree(pw, doc, 0, "UTF-8");
      pw.close();
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private static Node createIndexNodes(Document doc, String table,
      List<TabIndex> tabInxList) {
    if (CollectionUtils.isEmpty(tabInxList)
        || StringUtil.isEmptyWithTrim(table)) {
      return null;
    }
    Element tabNode = XMLUtil.createLeafElement(doc, TabIndex.NODE_TABLE, null);
    tabNode.setAttribute(TabIndex.TABLE_NAME, table);
    boolean hasIndex = false;
    for (TabIndex tindex : tabInxList) {
      if (StringUtil.isEmpty(tindex.getName())) {
        continue;
      }
      Element inxNode =
          XMLUtil.createLeafElement(doc, TabIndex.NODE_INDEX, null);
      inxNode.setAttribute(TabIndex.INDEX_NAME, tindex.getName());
      UFBoolean cluster = ValueUtils.getUFBoolean(tindex.getCluster());
      UFBoolean unique = ValueUtils.getUFBoolean(tindex.getUnique());
      inxNode.setAttribute(TabIndex.INDEX_CLUSTER, cluster.toString());
      inxNode.setAttribute(TabIndex.INDEX_UNIQUE, unique.toString());
      String[] columns = tindex.getColumns();
      if (ArrayUtils.isEmpty(columns)) {
        continue;
      }
      for (String col : columns) {
        Element colNode =
            XMLUtil.createLeafElement(doc, TabIndex.INDEX_COLUMN, col);
        inxNode.appendChild(colNode);
      }
      tabNode.appendChild(inxNode);
      hasIndex = true;
    }
    if (hasIndex) {
      return tabNode;
    }
    return null;
  }

  private static TabIndex createTabIndex(String table, Node inxNode) {
    String name = XMLUtil.getAtrributeValueOf(inxNode, TabIndex.INDEX_NAME);
    if (StringUtil.isEmptyWithTrim(name) || StringUtil.isEmptyWithTrim(table)) {
      return null;
    }
    String sCluster =
        XMLUtil.getAtrributeValueOf(inxNode, TabIndex.INDEX_CLUSTER);
    UFBoolean cluster = ValueUtils.getUFBoolean(sCluster);
    String sUniquer =
        XMLUtil.getAtrributeValueOf(inxNode, TabIndex.INDEX_UNIQUE);
    UFBoolean unique = ValueUtils.getUFBoolean(sUniquer);
    NodeList colNodes = inxNode.getChildNodes();
    List<String> colList = new ArrayList<String>();
    for (int i = 0; i < colNodes.getLength(); i++) {
      Node column = colNodes.item(i);
      String value =
          null != column.getFirstChild() ? column.getFirstChild()
              .getNodeValue() : null;
      if (TabIndex.INDEX_COLUMN.equals(column.getNodeName())
          && !StringUtil.isEmptyWithTrim(value)) {
        colList.add(value);
      }
    }
    if (0 == colList.size()) {
      return null;
    }
    String[] columns = colList.toArray(new String[colList.size()]);
    return new TabIndex(table, name, columns, cluster, unique);
  }

  private static MapList<String, TabIndex> getTableIndexMap(TabIndex[] indices) {
    if (ArrayUtils.isEmpty(indices)) {
      return null;
    }
    MapList<String, TabIndex> retMap = new MapList<String, TabIndex>();
    for (TabIndex tabIndex : indices) {
      retMap.put(tabIndex.getTable(), tabIndex);
    }
    return retMap;
  }
}
