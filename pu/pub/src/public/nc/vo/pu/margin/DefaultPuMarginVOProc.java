package nc.vo.pu.margin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.margin.MarginContextFactory;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.MapList;

/**
 * 用于尾差处理(倒挤)的上下游/兄弟VO加工接口默认实现
 * 
 * @since 6.0
 * @version 2012-4-21 下午10:55:38
 * @author zhaoyha
 */
public class DefaultPuMarginVOProc implements IPuMarginVOProcess {
  private String curSrcBIDFieldName;

  private String curSrcHIDFieldName;

  private String srcIDFieldName;

  /**
   * 构造一个尾差处理(倒挤)的上下游/兄弟VO加工接口默认实现
   * 
   * @param curSrcBIDFieldName 当前单据的来源BID字段名称
   * @param curSrcHIDFieldName 当前单据的来源HID字段名称
   * @param srcIDFieldName 来源单据行对照到当前单据BID上的字段名称（一般为来源单据行主键名称）
   */
  public DefaultPuMarginVOProc(String curSrcBIDFieldName,
      String curSrcHIDFieldName, String srcIDFieldName) {
    super();
    this.curSrcBIDFieldName = curSrcBIDFieldName;
    this.curSrcHIDFieldName = curSrcHIDFieldName;
    this.srcIDFieldName = srcIDFieldName;
  }

  /**
   * 当前单据的来源BID字段名称
   * 
   * @return
   */
  public String getCurSrcBIDFieldName() {
    return this.curSrcBIDFieldName;
  }

  /**
   * 当前单据的来源HID字段名称
   * 
   * @return
   */
  public String getCurSrcHIDFieldName() {
    return this.curSrcHIDFieldName;
  }

  /**
   * 来源单据的对照到当前单据上的ID字段名称（一般是主键名称，但不一定；必须指定，例如按孙表拆分父表的行时会用到）
   * 
   * @return
   */
  public String getSrcIDFieldName() {
    return this.srcIDFieldName;
  }

  @Override
  public Map<String, CircularlyAccessibleValueObject[]> procCurSiblingVO(
      CircularlyAccessibleValueObject[] curVos,
      CircularlyAccessibleValueObject[] siblingVos) {
    MapList<String, CircularlyAccessibleValueObject> mapList =
        new MapList<String, CircularlyAccessibleValueObject>();
    if (null == siblingVos) {
      return new HashMap<String, CircularlyAccessibleValueObject[]>();
    }
    for (CircularlyAccessibleValueObject siblingVo : siblingVos) {
      String siblingSrcBid =
          (String) siblingVo.getAttributeValue(this.getCurSrcBIDFieldName());
      String siblingSrcHid =
          (String) siblingVo.getAttributeValue(this.getCurSrcHIDFieldName());
      for (CircularlyAccessibleValueObject curVo : curVos) {
        String srcBid =
            (String) curVo.getAttributeValue(this.getCurSrcBIDFieldName());
        String srcHid =
            (String) curVo.getAttributeValue(this.getCurSrcHIDFieldName());
        if (siblingSrcBid.equals(srcBid) && siblingSrcHid.equals(srcHid)) {
          mapList.put(curVo.getAttributeValue(MarginContextFactory.uniqueKey)
              .toString(), siblingVo);
        }
      }
    }
    Map<String, CircularlyAccessibleValueObject[]> map =
        new HashMap<String, CircularlyAccessibleValueObject[]>();
    for (Map.Entry<String, List<CircularlyAccessibleValueObject>> entry : mapList
        .entrySet()) {
      List<CircularlyAccessibleValueObject> value = entry.getValue();
      map.put(entry.getKey(),
          new ListToArrayTool<CircularlyAccessibleValueObject>()
              .convertToArray(value));
    }
    return map;
  }

  @Override
  public Map<String, CircularlyAccessibleValueObject> procSrcVO(
      CircularlyAccessibleValueObject[] curVos,
      CircularlyAccessibleValueObject[] srcVos) {
    Map<String, CircularlyAccessibleValueObject> map =
        new HashMap<String, CircularlyAccessibleValueObject>();
    for (CircularlyAccessibleValueObject srcVo : srcVos) {
      String srcid = (String) srcVo.getAttributeValue(this.getSrcIDFieldName());
      for (CircularlyAccessibleValueObject curVo : curVos) {
        String srcBid =
            (String) curVo.getAttributeValue(this.getCurSrcBIDFieldName());
        String srcHid =
            (String) curVo.getAttributeValue(this.getCurSrcHIDFieldName());
        if (srcid.equals(srcBid) || srcid.equals(srcHid)) {
          map.put(curVo.getAttributeValue(MarginContextFactory.uniqueKey)
              .toString(), srcVo);
        }
      }
    }
    return map;
  }

}
