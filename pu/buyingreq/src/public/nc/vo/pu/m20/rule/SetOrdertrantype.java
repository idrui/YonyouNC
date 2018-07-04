/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-2 下午04:41:32
 */
package nc.vo.pu.m20.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.pf.PfBillItfDefUtil;
import nc.itf.scmpub.reference.uap.pf.TransTypeMapping;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.pubitf.scmf.pu.ordertranstype.pu.IQueryOrdertranstypeForPu;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SCBillType;

/**
 * 根据请购单表体的物料或者对应的物料分类+采购组织匹配物料订单类型设置中找到相应的采购订单交易类型，<br>
 * 其中匹配时物料及物料分类按照明细优先的规则进行。<br>
 * 如果匹配物料订单类型设置未匹配到对应值，则匹配上下游单据接口关系定义获取默认值。<br>
 * 如果表头“委外”勾选，则匹配上下游单据接口关系定义获取默认值，并且手工编辑时只能参照委外订单的交易类型。
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
 * @author GGR
 * @time 2010-2-2 下午04:41:32
 */
public class SetOrdertrantype {

  /**
   * 方法功能描述：查找订单类型。
   * <p>
   * <b>参数说明</b>
   * 
   * @param keyValue <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 下午04:28:34
   */
  public void setOrdertrantype(IKeyValue keyValue) {
    int[] rows = this.getRows(keyValue);

    this.setOrdertrantype(keyValue, rows);
  }

  /**
   * 根据请购单表体的物料或者对应的物料分类+采购组织匹配物料订单类型设置中找到相应的采购订单交易类型，<br>
   * 其中匹配时物料及物料分类按照明细优先的规则进行。<br>
   * 如果匹配物料订单类型设置未匹配到对应值，则匹配上下游单据接口关系定义获取默认值。<br>
   * 如果表头“委外”勾选，则匹配上下游单据接口关系定义获取默认值，并且手工编辑时只能参照委外订单的交易类型。
   * <p>
   * <b>参数说明</b>
   * 
   * @param keyValue 请购单
   * @param rows 当然选择行
   * @since 6.0
   * @author GGR
   * @time 2010-2-2 下午04:43:52
   */
  public void setOrdertrantype(IKeyValue keyValue, int[] rows) {
    Object obsctype = keyValue.getHeadValue(PraybillHeaderVO.BSCTYPE);
    boolean bsctype = false;
    if (obsctype != null && obsctype instanceof UFBoolean) {
      bsctype = ((UFBoolean) obsctype).booleanValue();
    }
    else if (obsctype != null && obsctype instanceof Boolean) {
      bsctype = ((Boolean) obsctype).booleanValue();
    }

    String pk_group = (String) keyValue.getHeadValue(PraybillHeaderVO.PK_GROUP);
    String pk_stockorg =
        (String) keyValue.getHeadValue(PraybillHeaderVO.PK_ORG);

    if (bsctype) {
      // 设置订单类型只能参照委外订单的交易类型
      String billType = SCBillType.Order.getCode();

      // 匹配上下游单据接口关系定义
      String transtype = this.getDestTransType(billType, pk_group, keyValue);
      for (int i = 0, len = rows.length; i < len; i++) {
        keyValue.setBodyValue(rows[i], PraybillItemVO.CORDERTRANTYPECODE,
            transtype);
      }
    }
    else {
      // 设置订单交易类型参照过滤
      String billType = POBillType.Order.getCode();

      // 取得物料订单类型设置,明细优先
      Map<String, String> transtypes =
          this.getTranstype(pk_group, keyValue, rows);
      // 匹配上下游单据接口关系定义
      String transtype = this.getDestTransType(billType, pk_group, keyValue);

      for (int i = 0, len = rows.length; i < len; i++) {
        Object pk_material =
            keyValue.getBodyValue(rows[i], PraybillItemVO.PK_SRCMATERIAL);
        Object pk_org =
            keyValue.getBodyValue(rows[i], PraybillItemVO.PK_PURCHASEORG);
        if (null == pk_material || null == pk_org) {
          continue;
        }
        String pk = pk_org.toString() + pk_stockorg + pk_material.toString();
        // 如果物料订单类型取得值则设置上，未取到值则匹配上下游关系定义设置
        if (null != transtypes.get(pk)) {
          keyValue.setBodyValue(rows[i], PraybillItemVO.CORDERTRANTYPECODE,
              transtypes.get(pk));
        }
        else {
          keyValue.setBodyValue(rows[i], PraybillItemVO.CORDERTRANTYPECODE,
              transtype);
        }
      }

      // 直运请购单，表体订单类型如果寻出来的是非直运订单类型，则清空
      this.procDirectOrderTransType(keyValue, rows);

    }
  }

  /**
   * 匹配上下游单据接口关系定义查找订单类型。
   * <p>
   * <b>参数说明</b>
   * 
   * @param billType 下游单据类型
   * @param pk_group 集团
   * @param card
   * @param row
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-14 上午10:40:32
   */
  private String getDestTransType(String billType, String pk_group,
      IKeyValue keyValue) {
    Object prayType = keyValue.getHeadValue(PraybillHeaderVO.CTRANTYPEID);
    Object prayTypeCode = keyValue.getHeadValue(PraybillHeaderVO.VTRANTYPECODE);

    if (null == prayType) {
      return null;
    }
    TransTypeMapping mapping = new TransTypeMapping();
    mapping.setSrcBillType(POBillType.PrayBill.getCode());
    mapping.setSrcTransType(prayType.toString());
    mapping.setSrcTransTypeCode(prayTypeCode.toString());
    mapping.setDestBillType(billType);

    // 取得订单类型
    PfBillItfDefUtil.queryTransTypeMapping(pk_group, mapping);

    return mapping.getDestTransType();
  }

  private int[] getRows(IKeyValue keyValue) {
    int[] rows = new int[keyValue.getItemCount()];
    for (int i = 0; i < keyValue.getItemCount(); i++) {
      rows[i] = i;
    }
    return rows;
  }

  /**
   * 根据物料订单类型设置查找订单类型。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_group
   * @param card
   * @param rows
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-14 上午10:40:49
   */
  private Map<String, String> getTranstype(String pk_group, IKeyValue keyValue,
      int[] rows) {
    // 根据请购单表体的物料或者对应的物料分类+采购组织匹配物料订单类型设置中找到相应的采购订单交易类型，
    // 其中匹配时物料及物料分类按照明细优先的规则进行

    ArrayList<String> materials = new ArrayList<String>();
    ArrayList<String> orgs = new ArrayList<String>();
    ArrayList<String> stockorgs = new ArrayList<String>();
    String stockorg = (String) keyValue.getHeadValue(PraybillHeaderVO.PK_ORG);
    for (int i = 0, len = rows.length; i < len; i++) {
      Object pk_srcmaterial =
          keyValue.getBodyValue(rows[i], PraybillItemVO.PK_SRCMATERIAL);
      Object pk_org =
          keyValue.getBodyValue(rows[i], PraybillItemVO.PK_PURCHASEORG);
      if (null == pk_srcmaterial || null == pk_org) {
        continue;
      }

      materials.add(pk_srcmaterial.toString());
      orgs.add(pk_org.toString());
      stockorgs.add(stockorg);
    }

    if (orgs.size() == 0) {
      return new HashMap<String, String>();
    }
    IQueryOrdertranstypeForPu service =
        NCLocator.getInstance().lookup(IQueryOrdertranstypeForPu.class);
    return service.getTranstypeIdByOrg(pk_group,
        orgs.toArray(new String[orgs.size()]),
        materials.toArray(new String[materials.size()]),
        stockorgs.toArray(new String[stockorgs.size()]));
  }

  /**
   * 处理直运请购单时表体订单类型，表体订单类型如果寻出来的是非直运订单类型，则清空
   * 
   * @param keyValue 请购单
   * @param rows 当前选择行
   */
  private void procDirectOrderTransType(IKeyValue keyValue, int[] rows) {

    // 如果是直运请购单
    Object bdirecttransit =
        keyValue.getHeadValue(PraybillHeaderVO.BDIRECTTRANSIT);
    if (bdirecttransit.toString() != null
        && UFBoolean.TRUE.equals(UFBoolean.valueOf(bdirecttransit.toString()))) {
      // 得到赋值之后物料的订单类型集合
      Set<String> set = new HashSet<String>();
      for (int i = 0, len = rows.length; i < len; i++) {
        String cordertrantypecode =
            (String) keyValue.getBodyValue(rows[i],
                PraybillItemVO.CORDERTRANTYPECODE);
        if (cordertrantypecode != null) {
          set.add(cordertrantypecode);
        }
      }

      if (set.isEmpty()) {
        return;
      }
      IPoTransTypeQuery service =
          NCLocator.getInstance().lookup(IPoTransTypeQuery.class);
      Map<String, PoTransTypeVO> poTransTypeVOMap = null;
      try {
        poTransTypeVOMap =
            service.queryAttrByIDs(set.toArray(new String[set.size()]));
        // 判断寻出来的订单类型是否是直运订单类型，如果不是，则清空
        for (int i = 0, len = rows.length; i < len; i++) {
          String cordertrantypecode =
              (String) keyValue.getBodyValue(rows[i],
                  PraybillItemVO.CORDERTRANTYPECODE);
          if (cordertrantypecode == null) {
            continue;
          }
          if (poTransTypeVOMap.get(cordertrantypecode) != null
              && UFBoolean.TRUE.equals(poTransTypeVOMap.get(cordertrantypecode)
                  .getBdirect())) {
            continue;
          }

          keyValue.setBodyValue(rows[i], PraybillItemVO.CORDERTRANTYPECODE,
              null);
        }

      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }

    }
  }
}
