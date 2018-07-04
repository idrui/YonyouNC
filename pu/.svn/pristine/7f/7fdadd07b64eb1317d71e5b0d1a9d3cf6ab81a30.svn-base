package nc.md.gen.pdm.index;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nc.bs.logging.Logger;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.jcom.xml.XMLUtil;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@SuppressWarnings("null")
public class PDMFileManager {
  public static final String CLUSTER_OBJ = "c:ClusterObject";

  public static final String CODE = "a:Code";

  public static final String COLUMN = "o:Column";

  public static final String COLUMNS = "c:Columns";

  public static final String ID = "Id";

  public static final String INDEX = "o:Index";

  public static final String INDEX_COLUMN_END_PATTERN = ">[\\s]*</"
      + PDMFileManager.COLUMN + ">";

  public static final String INDEXCOLUMN = "o:IndexColumn";

  public static final String INDEXCOLUMNS = "c:IndexColumns";

  public static final String INDEXES = "c:Indexes";

  public static final String NAME = "a:Name";

  public static final String OTABLE = "o:Table";

  public static final String REF = "Ref";

  public static final String TABLE_CODE_PATTERN = "[\\s]*<a:Code>" + "(\\w+)"
      + "</a:Code>";

  public static final String TABLE_END_PATTERN = "</c:Columns>";

  public static final String TABLE_START_PATTERN = "[\\s]*<o:Table" + "[\\s]+"
      + "Id=\"" + "(\\w+)" + "\">[\\s]*";

  public static final String UNIQUE = "a:Unique";

  public static final String UNIQUE_FLAG = "1";

  public static final String UNIQUE_STRING = "<a:Unique>1</a:Unique>";

  public static final String UNIQUE_TEMPLATE = "<a:Unique>0</a:Unique>";

  public static final String ZYH_INDEX_CODE = "zyh_index_code";

  public static final String ZYH_INDEX_ID = "zyh_index_id";

  public static final String ZYH_INDEX_NAME = "zyh_index_name";

  public static final String ZYH_INDEX_OBJECTID = "zyh_index_objectid";

  public static final String ZYH_INDEXCOLUMN_ID = "zyh_indexcolumn_id";

  public static final String ZYH_INDEXCOLUMN_OBJECTID =
      "zyh_indexcolumn_objectid";

  public static final String ZYH_REFCOLUMN = "zyh_refcolumn";

  private static String indexColumnTempletString = null;

  private static String indexNodeTempletString = null;

  static {
    try {
      URL fileUrl =
          IndexFileManager.class.getClassLoader().getResource(
              IndexFileManager.INDEX_FILE_PATH
                  + IndexFileManager.INDEX_NODE_TEMPLET_FILE);
      if (null == fileUrl || StringUtil.isEmptyWithTrim(fileUrl.getFile())) {
        String sPath = IndexFileManager.INDEX_NODE_TEMPLET_FILE;
        String sPathTo = IndexFileManager.INDEX_FILE_PATH;
        ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0", "04004000-0103", null, new String[]{sPath,sPathTo})/*请将索引配置文件（{0}）放到{1}下*/);
      }
      Document doc = XMLUtil.getDocument(fileUrl.getFile());
      NodeList nodes = doc.getElementsByTagName(PDMFileManager.INDEX);
      if (0 < nodes.getLength()) {
        StringBuffer buffer = new StringBuffer();
        XMLUtil.writeXMLFormatString(buffer, nodes.item(0), 0);
        PDMFileManager.indexNodeTempletString = buffer.toString();
      }
      nodes = doc.getElementsByTagName(PDMFileManager.INDEXCOLUMN);
      if (0 < nodes.getLength()) {
        StringBuffer buffer = new StringBuffer();
        XMLUtil.writeXMLFormatString(buffer, nodes.item(0), 0);
        PDMFileManager.indexColumnTempletString = buffer.toString();
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  public static Map<String, Node> addIndexes(String fileName) {
    if (StringUtil.isEmptyWithTrim(PDMFileManager.indexNodeTempletString)
        || StringUtil.isEmptyWithTrim(PDMFileManager.indexColumnTempletString)) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0", "04004000-0109")/*索引配置文件信息未能正确读取，请检查必须配置文件！*/);
    }
    Map<String, Node> tabIndexesMap = new HashMap<String, Node>();
    PDMInfo pdm = PDMFileManager.readPDM(fileName);
    PDMFileManager.getIndexes(pdm);
    try {
      File file = new File(fileName);
      Document doc = XMLUtil.getDocument(file);
      NodeList olist = doc.getElementsByTagName(PDMFileManager.OTABLE);
      // boolean update = false;
      for (int i = 0; i < olist.getLength(); i++) {
        Node tabNode = olist.item(i);
        // boolean addIndex =
        PDMFileManager.addTabIndexes(doc, tabNode, pdm, tabIndexesMap);
        // if (addIndex) {
        // update = true;
        // }
      }
      // if (update) {
      // Writer pw = null;
      // try {
      // // pw = new PrintWriter("d:/temp/invoice_new.pdm", "UTF-8");
      // // XMLUtil.printDOMTree(pw, doc, 0, "UTF-8");
      // }
      // catch (Exception e) {
      // throw e;
      // }
      // finally {
      // if (null != pw) {
      // pw.flush();
      // pw.close();
      // }
      // }
      // }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return tabIndexesMap;
  }

  public static void addPDMIndexes(String fileName) {
    PDMFileManager.Loging(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0", "04004000-0110", null, new String[]{fileName})/*目标文件：{0}*/);
    if (StringUtil.isEmptyWithTrim(fileName)) {
      return;
    }
    File pdmFile = new File(fileName);
    if (!pdmFile.exists()) {
      return;
    }
    Map<String, Node> tabIndexesMap = PDMFileManager.addIndexes(fileName);
    Scanner scanner = null;
    OutputStreamWriter writer = null;
    try {
      scanner = new Scanner(pdmFile, "UTF-8");
      String tempFile =
          System.getProperty("java.io.tmpdir") + "indexesaddtemp.pdm";
      OutputStream file = new FileOutputStream(tempFile);
      writer = new OutputStreamWriter(file, "UTF-8");
      Pattern pt = Pattern.compile(PDMFileManager.TABLE_START_PATTERN);
      Pattern tabCodept = Pattern.compile(PDMFileManager.TABLE_CODE_PATTERN);
      String tableid = null;
      String tablecode = null;
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        writer.write(line);
        writer.write(System.getProperty("line.separator"));
        if (StringUtil.isEmptyWithTrim(tableid)) {
          Matcher mc = pt.matcher(line);
          if (mc.matches()) {
            tableid = mc.group(1).trim();
          }
        }
        else if (!StringUtil.isEmptyWithTrim(tableid)
            && StringUtil.isEmptyWithTrim(tablecode)) {
          Matcher mc = tabCodept.matcher(line);
          if (mc.matches()) {
            tablecode = mc.group(1).trim();
          }
        }
        else if (PDMFileManager.TABLE_END_PATTERN.equals(line.trim())) {
          Node indexesNode = tabIndexesMap.get(tablecode);
          if (null != indexesNode) {
            StringBuffer buffer = new StringBuffer();
            XMLUtil.writeXMLFormatString(buffer, indexesNode, 0);
            writer.write(System.getProperty("line.separator"));
            String strBuffer = buffer.toString();
            strBuffer =
                strBuffer.replaceAll(PDMFileManager.INDEX_COLUMN_END_PATTERN,
                    "/>");
            writer.write(strBuffer);
            writer.write(System.getProperty("line.separator"));
          }
          tableid = null;
          tablecode = null;
        }
      }
      scanner.close();
      writer.flush();
      writer.close();
      PDMFileManager.copyFile(tempFile, fileName);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    finally {
      if (null != scanner) {
        scanner.close();
      }
      if (null != writer) {
        try {
          writer.close();
        }
        catch (IOException e) {
          ExceptionUtils.wrappException(e);
        }
      }
    }
  }

  public static PDMInfo readPDM(String fileName) {
    PDMInfo pdm = new PDMInfo();
    Map<String, TabIndex[]> tabIndexMap = new HashMap<String, TabIndex[]>();
    Map<String, Map<String, String>> tabColIDMap =
        new HashMap<String, Map<String, String>>();
    try {
      Map<String, String> tabColMap = new HashMap<String, String>();
      File file = new File(fileName);
      Document doc = XMLUtil.getDocument(file);
      Set<String> clusterObj = PDMFileManager.getClusterObj(doc);
      NodeList olist = doc.getElementsByTagName(PDMFileManager.OTABLE);
      for (int i = 0; i < olist.getLength(); i++) {
        Node tabNode = olist.item(i);
        short type = tabNode.getNodeType();
        if (Node.ELEMENT_NODE == type) {
          String table =
              PDMFileManager.procTable(tabNode, tabColMap, clusterObj,
                  tabIndexMap);
          Map<String, String> colIDMap =
              PDMFileManager.createColIDMap(tabColMap);
          if (!StringUtil.isEmptyWithTrim(table)) {
            tabColIDMap.put(table, colIDMap);
          }
        }
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    pdm.setIndexes(tabIndexMap);
    pdm.setTableColumIDMap(tabColIDMap);
    return pdm;
  }

  private static boolean addTabIndexes(Document doc, Node tabNode, PDMInfo pdm,
      Map<String, Node> tabIndexesMap) {
    Map<String, TabIndex[]> tabIndexes = pdm.getIndexes();
    if (MapUtils.isEmpty(tabIndexes)) {
      return false;
    }
    Map<String, Map<String, String>> tabColIDMap = pdm.getTableColumIDMap();
    if (MapUtils.isEmpty(tabColIDMap)) {
      return false;
    }
    Node codeNode = XMLUtil.getChildNodeOf(tabNode, PDMFileManager.CODE);
    String table =
        null == codeNode || null == codeNode.getFirstChild() ? null : codeNode
            .getFirstChild().getNodeValue();
    if (StringUtil.isEmptyWithTrim(table)) {
      return false;
    }
    TabIndex[] indexes = tabIndexes.get(table);
    if (ArrayUtils.isEmpty(indexes)) {
      return false;
    }
    Map<String, String> colIDMap = tabColIDMap.get(table);
    if (MapUtils.isEmpty(colIDMap)) {
      return false;
    }
    boolean addIndexFlag = false;
    Node indexesNode =
        XMLUtil.createLeafElement(doc, PDMFileManager.INDEXES, null);
    for (int i = 0; i < indexes.length; i++) {
      Node inxNode = PDMFileManager.createIndexNode(doc, indexes[i], colIDMap);
      if (null != inxNode) {
        XMLUtil.copyInto(inxNode, indexesNode);
        addIndexFlag = true;
      }
    }
    if (addIndexFlag) {
      tabNode.appendChild(indexesNode);
      tabIndexesMap.put(table, indexesNode);
    }
    return addIndexFlag;
  }

  private static Node convertStringToNode(String nodeString, String nodeName) {
    Node inxND = null;
    try {
      Document indexDoc =
          XMLUtil
              .rebuildDocument("<virtualnode xmlns:a=\"attribute\" xmlns:c=\"collection\" xmlns:o=\"object\">"
                  + nodeString + "</virtualnode>");
      NodeList inxNDs = indexDoc.getElementsByTagName(nodeName);
      if (0 >= inxNDs.getLength()) {
        return null;
      }
      inxND = inxNDs.item(0);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return inxND;
  }

  private static void copyFile(String src, String dest) {
    File srcFile = new File(src);
    if (!srcFile.exists()) {
      return;
    }
    File destFile = new File(dest);
    if (destFile.exists()) {
      if (!destFile.delete()) {
        ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0", "04004000-0111")/*生成目标文件出错*/);
      }
    }
    if (!srcFile.renameTo(destFile)) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0", "04004000-0111")/*生成目标文件出错*/);
    }
  }

  private static Map<String, String> createColIDMap(Map<String, String> idColMap) {
    Map<String, String> retMap = new HashMap<String, String>(idColMap.size());
    for (Entry<String, String> entry : idColMap.entrySet()) {
      retMap.put(entry.getValue(), entry.getKey());
    }
    return retMap;
  }

  private static Node createIndexNode(Document pdmDoc, TabIndex ti,
      Map<String, String> colIDMap) {
    String table = ti.getTable();
    String inxName = ti.getName();
    if (StringUtil.isEmptyWithTrim(table)
        || StringUtil.isEmptyWithTrim(inxName)) {
      return null;
    }
    String indexString =
        PDMFileManager.indexNodeTempletString.replace(
            PDMFileManager.ZYH_INDEX_CODE, inxName);// 索引编码
    indexString = indexString.replace(PDMFileManager.ZYH_INDEX_NAME, inxName);// 索引名称
    String oid = PDMFileManager.generateID();
    indexString =
        indexString.replace(PDMFileManager.ZYH_INDEX_ID, table + "@@@"
            + inxName);// 索引ID
    indexString = indexString.replace(PDMFileManager.ZYH_INDEX_OBJECTID, oid);// 索引对象ID
    UFBoolean unique = ti.getUnique();
    if (null != unique && unique.booleanValue()) {
      indexString =
          indexString.replace(PDMFileManager.UNIQUE_TEMPLATE,
              PDMFileManager.UNIQUE_STRING);
    }
    Node inxND =
        PDMFileManager.convertStringToNode(indexString, PDMFileManager.INDEX);
    if (null == inxND) {
      return inxND;
    }
    String[] cols = ti.getColumns();
    if (ArrayUtils.isEmpty(cols)) {
      return null;
    }
    Node indexColNodes =
        XMLUtil.createLeafElement(pdmDoc, PDMFileManager.INDEXCOLUMNS, null);
    boolean hasCol = false;
    for (String col : cols) {
      if (StringUtil.isEmptyWithTrim(col)) {
        continue;
      }
      String colID = colIDMap.get(col);
      if (StringUtil.isEmptyWithTrim(colID)) {
        continue;
      }
      oid = PDMFileManager.generateID();
      String inxColString =
          PDMFileManager.indexColumnTempletString.replace(
              PDMFileManager.ZYH_INDEXCOLUMN_ID, table + "@@@" + inxName
                  + "@@@" + col);
      inxColString =
          inxColString.replace(PDMFileManager.ZYH_INDEXCOLUMN_OBJECTID, oid);
      inxColString = inxColString.replace(PDMFileManager.ZYH_REFCOLUMN, colID);
      Node inxColND =
          PDMFileManager.convertStringToNode(inxColString,
              PDMFileManager.INDEXCOLUMN);
      if (null == inxColND) {
        continue;
      }
      XMLUtil.copyInto(inxColND, indexColNodes);
      hasCol = true;
    }
    if (!hasCol) {
      return null;
    }

    XMLUtil.copyInto(indexColNodes, inxND);

    return inxND;
  }

  private static String generateID() {
    return UUID.randomUUID().toString();
  }

  private static Set<String> getClusterObj(Document doc) {
    Set<String> clusterSet = new HashSet<String>();
    NodeList olist = doc.getElementsByTagName(PDMFileManager.CLUSTER_OBJ);
    for (int i = 0; i < olist.getLength(); i++) {
      List<Node> children = XMLUtil.getAllChildNodes(olist.item(i));
      for (Node node : children) {
        String objID = XMLUtil.getAtrributeValueOf(node, PDMFileManager.REF);
        if (StringUtil.isEmptyWithTrim(objID)) {
          continue;
        }
        clusterSet.add(objID);
      }
    }
    return clusterSet;
  }

  private static void getIndexes(PDMInfo pdm) {
    TabIndex[] indexes = IndexFileManager.loadIndexFile();
    if (ArrayUtils.isEmpty(indexes)) {
      return;
    }
    MapList<String, TabIndex> tabIndexes = new MapList<String, TabIndex>();
    for (TabIndex tabIndex : indexes) {
      tabIndexes.put(tabIndex.getTable(), tabIndex);
    }
    Map<String, TabIndex[]> tabIndexesMap = new HashMap<String, TabIndex[]>();
    for (String table : tabIndexes.keySet()) {
      List<TabIndex> tis = tabIndexes.get(table);
      tabIndexesMap.put(table, tis.toArray(new TabIndex[tis.size()]));
    }
    pdm.setIndexes(tabIndexesMap);
  }

  private static void Loging(String msg) {
    Logger.error("PDM Index Tool by zhaoyha:" + msg);
  }

  private static void procColums(Node colNodes, Map<String, String> colMap) {
    NodeList childList = colNodes.getChildNodes();
    for (int i = 0; i < childList.getLength(); i++) {
      Node colNode = childList.item(i);
      short type = colNode.getNodeType();
      String colCode = null;
      if (Node.ELEMENT_NODE != type) {
        continue;
      }
      String colID = XMLUtil.getAtrributeValueOf(colNode, PDMFileManager.ID);
      if (StringUtil.isEmptyWithTrim(colID)) {
        continue;
      }
      NodeList attList = colNode.getChildNodes();
      for (int j = 0; j < attList.getLength(); j++) {
        String nodeName = attList.item(j).getNodeName();
        if (PDMFileManager.CODE.equals(nodeName)) {
          colCode = attList.item(j).getFirstChild().getNodeValue();
          break;
        }
      }
      if (null != colCode) {
        colMap.put(colID, colCode);
      }
    }
  }

  private static TabIndex[] procIndexes(String table, Node indexesNode,
      Map<String, String> colMap, Set<String> clusterObj) {
    NodeList children = indexesNode.getChildNodes();
    List<TabIndex> indexes = new ArrayList<TabIndex>();
    for (int i = 0; i < children.getLength(); i++) {
      Node inxNode = children.item(i);
      String nodeName = inxNode.getNodeName();
      if (!PDMFileManager.INDEX.equals(nodeName)) {
        continue;
      }
      String inxObjID = XMLUtil.getAtrributeValueOf(inxNode, PDMFileManager.ID);
      List<Node> allChild = XMLUtil.getAllChildNodes(inxNode);
      String indexName = null;
      List<String> colList = new ArrayList<String>();
      UFBoolean unique = UFBoolean.FALSE;
      for (Node node : allChild) {
        String childName = node.getNodeName();
        if (PDMFileManager.CODE.equals(childName)) {
          indexName = node.getFirstChild().getNodeValue();
        }
        else if (PDMFileManager.COLUMN.equals(childName)) {
          String colID = XMLUtil.getAtrributeValueOf(node, PDMFileManager.REF);
          String col = colMap.get(colID);
          if (!StringUtil.isEmptyWithTrim(col)) {
            colList.add(col);
          }
        }
        else if (PDMFileManager.UNIQUE.equals(childName)) {
          String sUnique = node.getFirstChild().getNodeValue();
          if (PDMFileManager.UNIQUE_FLAG.equals(sUnique)) {
            unique = UFBoolean.TRUE;
          }
        }
      }
      if (StringUtil.isEmptyWithTrim(indexName) || 0 == colList.size()) {
        continue;
      }
      UFBoolean cluster = UFBoolean.FALSE;
      if (clusterObj.contains(inxObjID)) {
        cluster = UFBoolean.TRUE;
      }
      TabIndex ti =
          new TabIndex(table, indexName, colList.toArray(new String[colList
              .size()]), cluster, unique);
      indexes.add(ti);
    }
    return indexes.toArray(new TabIndex[indexes.size()]);
  }

  private static String procTable(Node tabNode, Map<String, String> colMap,
      Set<String> clusterObj, Map<String, TabIndex[]> tabIndexMap) {
    NodeList childList = tabNode.getChildNodes();
    String table = null;
    Node indexesNode = null;
    colMap.clear();
    for (int i = 0; i < childList.getLength(); i++) {
      Node tabChild = childList.item(i);
      short type = tabChild.getNodeType();
      if (Node.ELEMENT_NODE == type) {
        String childName = tabChild.getNodeName();
        if (PDMFileManager.CODE.equals(childName)) {
          table = tabChild.getFirstChild().getNodeValue();// 表名
        }
        else if (PDMFileManager.COLUMNS.equals(childName)) {
          // 列处理
          PDMFileManager.procColums(tabChild, colMap);
        }
        else if (PDMFileManager.INDEXES.equals(childName)) {
          // 处理索引
          indexesNode = tabChild;
        }
      }
    }
    if (null != indexesNode && !StringUtil.isEmptyWithTrim(table)
        && 0 < colMap.size()) {
      // 处理索引
      TabIndex[] indexes =
          PDMFileManager.procIndexes(table, indexesNode, colMap, clusterObj);
      tabIndexMap.put(table, indexes);
    }
    return table;
  }

}
