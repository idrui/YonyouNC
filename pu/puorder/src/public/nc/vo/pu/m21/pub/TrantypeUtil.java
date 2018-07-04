/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-1 下午02:01:41
 */
package nc.vo.pu.m21.pub;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>交易类型工具类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-8-1 下午02:01:41
 */
public class TrantypeUtil {

  public static TrantypeUtil instance = new TrantypeUtil();

  private TrantypeUtil() {
    // 单实例
  }

  public static TrantypeUtil getInstance() {
    return TrantypeUtil.instance;
  }

  /**
   * 方法功能描述：得到交易类型对应的“存在到货计划”属性
   * <p>
   * <b>参数说明</b>
   * 
   * @param transtypeCode
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-1 下午02:08:03
   */
  public Map<String, UFBoolean> getBReceivePlanMap(String[] transtypeCode) {
    return this.getBooleanAttribute(transtypeCode, PoTransTypeVO.BRECEIVEPLAN);
  }

  /**
   * 是否直运采购
   * 
   * @param transtypeCode
   * @return
   */
  public UFBoolean getDirectPurchaseAttr(String transtypeCode) {
    Map<String, UFBoolean> value = this.getBooleanAttribute(new String[] {
      transtypeCode
    }, PoTransTypeVO.BDIRECT);
    return value.get(transtypeCode);
  }

  /**
   * 是否直运采购
   * 
   * @param transtypeCode
   * @return
   */
  public Map<String, UFBoolean> getDirectPurchaseAttr(String[] transtypeCode) {
    return this.getBooleanAttribute(transtypeCode, PoTransTypeVO.BDIRECT);
  }

  /**
   * 方法功能描述：根据一个业务类型的状态VO，及当前在途状态，得到下一状态 不存在下一状态，则返回入库为下一状态
   * <p>
   * <b>参数说明</b>
   * 
   * @param iCurStatus
   *          当前状态
   * @param bQueryForArrStore
   * @return 下一状态 如果未找到上一状态，则返回审批状态 0 审批 1 输出 2 确认 3 发货 4 装运 5 报关 6 出关 7 到货 8 入库
   *         <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-8 上午09:22:13
   */
  public Integer getNextStatus(int status, PoTransTypeVO transtypeVO) {

    int[] iAllStatus = this.setStatus(transtypeVO);
    int iCurStatus = status;

    // 循环查找上一状态
    int iLen = iAllStatus.length;
    for (int i = iCurStatus + 1; i < iLen; i++) {
      if (iAllStatus[i] == 1) {
        return Integer.valueOf(i);
      }
    }

    return null;
  }

  /**
   * 方法功能描述：根据一个业务类型的状态VO，及当前在途状态，得到下一状态
   * <p>
   * <b>参数说明</b>
   * 
   * @param iCurStatus
   *          当前状态
   * @param bQueryForArrStore
   * @return 下一状态 如果未找到上一状态，则返回审批状态 0 审批 1 输出 2 确认 3 发货 4 装运 5 报关 6 出关 7 到货 8 入库
   *         <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-8 上午09:22:13
   */
  public Integer getUpStatus(OnwayStatus status, PoTransTypeVO transtypeVO) {

    int[] iAllStatus = this.setStatus(transtypeVO);
    int iCurStatus = status.toInt();

    // 循环查找上一状态
    // int iLen = iAllStatus.length ;
    for (int i = iCurStatus - 1; i >= 0; i--) {
      if (iAllStatus[i] == 1) {
        return Integer.valueOf(i);
      }
    }

    return null;
  }

  /**
   * 查询交易类型中布尔属性的字段值
   * 
   * @param transtypeIds
   * @param booleanAttrKey
   * @return
   */
  private Map<String, UFBoolean> getBooleanAttribute(String[] transtypeIds,
      String booleanAttrKey) {
    Map<String, UFBoolean> valeMap = new HashMap<String, UFBoolean>();
    try {
      IPoTransTypeQuery query =
          NCLocator.getInstance().lookup(IPoTransTypeQuery.class);
      Map<String, PoTransTypeVO> transtypeMap =
          query.queryAttrByTypes(transtypeIds, new String[] {
            booleanAttrKey
          });

      if (null == transtypeMap || transtypeMap.isEmpty()) {
        return valeMap;
      }

      for (Map.Entry<String, PoTransTypeVO> entry : transtypeMap.entrySet()) {
        valeMap.put(entry.getKey(), (UFBoolean) entry.getValue()
            .getAttributeValue(booleanAttrKey));
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    return valeMap;
  }

  /**
   * 方法功能描述：设置交易类型的各个状态
   * <p>
   * <b>参数说明</b>
   * 
   * @param transtypeVO
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-8 上午09:58:33
   */
  private int[] setStatus(PoTransTypeVO transtypeVO) {
    int[] iAllStatus = new int[] {
      1, 0, 0, 0, 0, 0, 0, 0, 0
    };

    // 输出
    if (transtypeVO.getBoutput().booleanValue()) {
      iAllStatus[1] = 1;
    }
    // 确认
    if (transtypeVO.getBconfirm().booleanValue()) {
      iAllStatus[2] = 1;
    }
    // 发货
    if (transtypeVO.getBconsign().booleanValue()) {
      iAllStatus[3] = 1;
    }
    // 装运
    if (transtypeVO.getBload().booleanValue()) {
      iAllStatus[4] = 1;
    }
    // 报关
    if (transtypeVO.getBcustom().booleanValue()) {
      iAllStatus[5] = 1;
    }
    // 出关
    if (transtypeVO.getBoutcustom().booleanValue()) {
      iAllStatus[6] = 1;
    }
    // 到货
    if (transtypeVO.getBarrive().booleanValue()) {
      iAllStatus[7] = 1;
    }
    // 入库
    if (transtypeVO.getBstore().booleanValue()) {
      iAllStatus[8] = 1;
    }
    return iAllStatus;
  }
}
