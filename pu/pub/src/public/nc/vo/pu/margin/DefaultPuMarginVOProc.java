package nc.vo.pu.margin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.margin.MarginContextFactory;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.MapList;

/**
 * ����β���(����)��������/�ֵ�VO�ӹ��ӿ�Ĭ��ʵ��
 * 
 * @since 6.0
 * @version 2012-4-21 ����10:55:38
 * @author zhaoyha
 */
public class DefaultPuMarginVOProc implements IPuMarginVOProcess {
  private String curSrcBIDFieldName;

  private String curSrcHIDFieldName;

  private String srcIDFieldName;

  /**
   * ����һ��β���(����)��������/�ֵ�VO�ӹ��ӿ�Ĭ��ʵ��
   * 
   * @param curSrcBIDFieldName ��ǰ���ݵ���ԴBID�ֶ�����
   * @param curSrcHIDFieldName ��ǰ���ݵ���ԴHID�ֶ�����
   * @param srcIDFieldName ��Դ�����ж��յ���ǰ����BID�ϵ��ֶ����ƣ�һ��Ϊ��Դ�������������ƣ�
   */
  public DefaultPuMarginVOProc(String curSrcBIDFieldName,
      String curSrcHIDFieldName, String srcIDFieldName) {
    super();
    this.curSrcBIDFieldName = curSrcBIDFieldName;
    this.curSrcHIDFieldName = curSrcHIDFieldName;
    this.srcIDFieldName = srcIDFieldName;
  }

  /**
   * ��ǰ���ݵ���ԴBID�ֶ�����
   * 
   * @return
   */
  public String getCurSrcBIDFieldName() {
    return this.curSrcBIDFieldName;
  }

  /**
   * ��ǰ���ݵ���ԴHID�ֶ�����
   * 
   * @return
   */
  public String getCurSrcHIDFieldName() {
    return this.curSrcHIDFieldName;
  }

  /**
   * ��Դ���ݵĶ��յ���ǰ�����ϵ�ID�ֶ����ƣ�һ�����������ƣ�����һ��������ָ�������簴����ָ������ʱ���õ���
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
