package nc.vo.pu.pub.util;

import java.io.File;

import nc.bs.framework.common.RuntimeEnv;
import nc.vo.jcom.xml.XMLUtil;
import nc.vo.pu.pub.enumeration.PuNodeCode;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * 采购组读取XML配置文件工具类
 * 
 * @since 6.0
 * @version 2011-9-13 上午11:57:32
 * @author zhaoyha
 */
public class XMLConfigReader {

  /** 查询结果配置文件全路径 **/
  public static final String FILE_QUERY_RESULT_CONFIG =
      XMLConfigReader.NCHOME_PATH + "modules" + File.separatorChar + "pu"
          + File.separatorChar + "config" + File.separatorChar
          + "query_result_config.xml";

  public static final int MAX_QUERY_ROW = 5000;

  /** NCHOME的全路径 **/
  public static final String NCHOME_PATH = RuntimeEnv.getInstance().getNCHome()
      + File.separatorChar;

  /** 元素的code属性名 **/
  public static final String XML_ELEM_ATT_CODE = "code";

  /** 元素的value属性名 **/
  public static final String XML_ELEM_ATT_VALUE = "value";

  /** 功能节点号－元素名称 **/
  public static final String XML_ELEM_NODECODE = "nodecode";

  /** 行数限制－元素名称 **/
  public static final String XML_ELEM_QUERY_LIMIT_ROW = "query_limit_row";

  /** 配置文件根节点 **/
  public static final String XML_ELEM_ROOT = "root";

  /**
   * 根据功能节点号，得到查询结果最大的行数限制
   * 
   * @param ncode
   * @return 最大限制行数
   */
  public int getQueryRowLimitBy(PuNodeCode ncode) {
    Document doc = this.getXMLDoc(XMLConfigReader.FILE_QUERY_RESULT_CONFIG);
    if (null == doc) {
      return XMLConfigReader.MAX_QUERY_ROW;
    }
    try {
      Element root =
          (Element) doc.getElementsByTagName(XMLConfigReader.XML_ELEM_ROOT)
              .item(0);
      NodeList nlst =
          root.getElementsByTagName(XMLConfigReader.XML_ELEM_NODECODE);
      int nlistlen = nlst.getLength();
      for (int i = 0; i < nlistlen; i++) {
        Element node = (Element) nlst.item(i);
        String funcode = node.getAttribute(XMLConfigReader.XML_ELEM_ATT_CODE);
        if (!ncode.code().equals(funcode)) {
          continue;
        }
        Element rowlmNode =
            (Element) node.getElementsByTagName(
                XMLConfigReader.XML_ELEM_QUERY_LIMIT_ROW).item(0);
        Integer value =
            Integer.valueOf(rowlmNode
                .getAttribute(XMLConfigReader.XML_ELEM_ATT_CODE));
        return value.intValue();
      }
    }
    catch (Exception e) {
      // ignore
    }
    return XMLConfigReader.MAX_QUERY_ROW;
  }

  private Document getXMLDoc(String file) {
    if (!RuntimeEnv.getInstance().isRunningInServer()) {
      ExceptionUtils
          .wrappBusinessException("Please don't use this class in UI!");
    }
    Document doc = null;
    try {
      doc = XMLUtil.getDocument(file);
    }
    catch (Exception e) {
      // ignore异常，调用者处理读取不到配置文件时的默认值
      return doc;
    }
    return doc;
  }

}
