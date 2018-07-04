/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-12 下午05:14:23
 */
package nc.vo.pu.m21.rule;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m21.entity.OrderOnwayItemVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>在途状态校验工具类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-8-12 下午05:14:23
 */
public class OnwayValidationTool {

  /**
   * 方法功能描述：检验用户输入业务数据的有效性（发货）
   * <p>
   * <b>参数说明</b>
   * 
   * @param pvo
   * @param vos
   * @throws Exception
   *           <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-19 上午10:06:26
   */
  public static void chkBusiForSendOut(PoTransTypeVO pvo, OrderOnwayItemVO[] vos)
      throws BusinessException {

    if (null == pvo) {
      return;
    }

    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    StringBuilder retSb = new StringBuilder();
    for (int i = 0; i < vos.length; i++) {
      // [本次发货数量]不能为0或负
      if (ValueUtils.getBoolean(pvo.getBconsignnum())) {
        if (null != vos[i].getNsendoutnum()) {
          UFDouble connum = ValueUtils.getUFDouble(vos[i].getNsendoutnum());
          if (connum.compareTo(UFDouble.ZERO_DBL) <= 0) {
            if (!"".equals(retSb.toString())) {
              retSb.append(", ");
            }
            String crowno = vos[i].getCrowno();
            retSb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
                "4004030_0", "04004030-0307", null, new String[] {
                  crowno
                })/* 第{0}行[本次发货数量]不能为0或负 */);
          }
          // if (connum.compareTo(vos[i].getNnum()) > 0) {
          // if (!"".equals(retStr))
          // retStr += ", ";
          // retStr += "第" + (i + 1) + "行确认数量不能大于订单数量";
          // }
        }
      }
      // 确认日期
      // if (ValueUtils.getBoolean(pvo.getBconfirmdate())) {
      // if (null != vos[i].getDconfirmdate()) {
      // UFDate date = ValueUtils.getUFDate(
      // vos[i].getDconfirmdate());
      // UFDate dateNow = ClientContext.getInstance().getBusiDate();
      // if (date.compareTo(dateNow) > 0) {
      // if (!"".equals(retStr))
      // retStr += ", ";
      // retStr += "第" + (i + 1) + "行确认日期不能大于当前日期";
      // }
      // }
      // }
    }
    if (!"".equals(retSb.toString())) {
      String retStr =
          NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
              "04004030-0226", null, new String[] {
                retSb.toString()
              })/* 选择的{0} */;
      throw new BusinessException(retStr);
    }
  }

  /**
   * 方法功能描述：检查单据号，数量，日期不为空（除发货外的在途）
   * <p>
   * <b>参数说明</b>
   * 
   * @param transvo
   *          交易类型VO
   * @param vos
   *          要校验的VO
   * @param billCode
   *          单据号名
   * @param billDate
   *          单据日期名
   * @param statusName
   *          显示用状态名
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-2 上午11:09:38
   */
  public static void chkEmptyForOnway(PoTransTypeVO transvo,
      OrderOnwayItemVO[] vos, String billCode, String billDate,
      String statusName) throws BusinessException {

    if (null == transvo) {
      return;
    }

    StringBuilder retSb = new StringBuilder();
    for (int i = 0; i < vos.length; i++) {
      String crowno = vos[i].getCrowno();
      // 发货单号
      if (ValueUtils.getBoolean(transvo.getAttributeValue(billCode))) {
        if (StringUtils.isEmpty(vos[i].getVbillcode())) {
          if (!"".equals(retSb.toString())) {
            retSb.append(", ");
          }
          retSb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
              "4004030_0", "04004030-0308", null, new String[] {
                crowno, statusName
              })/* 第{0}行[{1}单号]不能为空 */);
        }
      }
      // 发货日期
      if (ValueUtils.getBoolean(transvo.getAttributeValue(billDate))) {
        if (null == vos[i].getDbilldate()) {
          if (!"".equals(retSb.toString())) {
            retSb.append(", ");
          }
          retSb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
              "4004030_0", "04004030-0309", null, new String[] {
                crowno, statusName
              })/* 第{0}行[{1}日期]不能为空 */);
        }
      }
    }
    if (!"".equals(retSb.toString())) {
      String retStr =
          NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
              "04004030-0226", null, new String[] {
                retSb.toString()
              })/* 选择的{0} */;
      throw new BusinessException(retStr);
    }
  }

  /**
   * 方法功能描述：检查单据号，数量，日期不为空（发货）
   * <p>
   * <b>参数说明</b>
   * 
   * @param transvo
   *          交易类型VO
   * @param vos
   *          要校验的VO
   * @throws Exception
   *           <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-12 下午07:02:42
   */
  public static void chkEmptyForSendOut(PoTransTypeVO transvo,
      OrderOnwayItemVO[] vos) throws BusinessException {

    if (null == transvo) {
      return;
    }

    StringBuilder retSb = new StringBuilder();
    for (int i = 0; i < vos.length; i++) {
      String crowno = vos[i].getCrowno();
      // 发货单号
      if (ValueUtils.getBoolean(transvo.getBconsigncode())) {
        if (StringUtils.isEmpty(vos[i].getVbillcode())) {
          if (!"".equals(retSb.toString())) {
            retSb.append(", ");
          }
          retSb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
              "4004030_0", "04004030-0310", null, new String[] {
                crowno
              })/* 第{0}行[发货单号]不能为空 */);
        }
      }
      // 本次发货数量不能为空
      if (ValueUtils.getBoolean(transvo.getBconsignnum())) {
        if (null == vos[i].getNsendoutnum()) {
          if (!"".equals(retSb.toString())) {
            retSb.append(", ");
          }
          retSb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
              "4004030_0", "04004030-0311", null, new String[] {
                crowno
              })/* 第{0}行[本次发货数量]不能为空 */);
        }
        // else {
        // UFDouble connum = ValueUtils.getUFDouble(
        // vos[i].getNconfirmnum());
        // if (connum.compareTo(vos[i].getNnum()) > 0) {
        // if (!"".equals(retStr))
        // retStr += ", ";
        // retStr += "第" + (i + 1) + "行确认数量不能大于订单数量";
        // }
        // }
      }
      // 发货日期
      if (ValueUtils.getBoolean(transvo.getBconsigndate())) {
        if (null == vos[i].getDbilldate()) {
          if (!"".equals(retSb.toString())) {
            retSb.append(", ");
          }
          retSb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
              "4004030_0", "04004030-0312", null, new String[] {
                crowno
              })/* 第{0}行[发货日期]不能为空 */);
        }
      }
    }
    if (!"".equals(retSb.toString())) {
      String retStr =
          NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
              "04004030-0226", null, new String[] {
                retSb.toString()
              })/* 选择的{0} */;
      throw new BusinessException(retStr);
    }
  }

  /**
   * 方法功能描述：获得交易类型VO
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-12 下午07:02:17
   */
  public static Map<String, PoTransTypeVO> getTransTypeVO(String vtranType[]) {

    HashMap<String, PoTransTypeVO> tranTypeMap = null;
    try {

      tranTypeMap =
          NCLocator.getInstance().lookup(IPoTransTypeQuery.class)
              .queryAttrByTypes(vtranType, null);

    }
    catch (BusinessException ex) {
      // 日志异常
      ExceptionUtils.wrappException(ex);
    }

    return tranTypeMap;
  }

}
