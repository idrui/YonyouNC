/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-6 下午02:07:23
 */
package nc.bs.pu.m21.writeback.ct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.TempTable;
import nc.itf.pu.reference.ct.Z2CTServices;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.pu.m21.ct.mz2.IOrderWriteBackParaForZ2;
import nc.vo.ct.entity.CtWriteBackForOrderVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.JavaType;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

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
 * @author wuxla
 * @time 2010-7-6 下午02:07:23
 */
public class OrderWriteBackForZ2FromPOBP {

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param wbVos
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-6 下午02:07:58
   */
  public void writeback(IOrderWriteBackParaForZ2[] wbVos) {

    if (!SysInitGroupQuery.isCTEnabled()) {
      return;
    }

    OrderViewVO[] viewVOs = this.getOrderViewVOs(wbVos);
    if (ArrayUtils.isEmpty(viewVOs)) {
      return;
    }

    Map<String, IOrderWriteBackParaForZ2> wbVoMap = this.getParaMap(wbVos);

    CtWriteBackForOrderVO[] writebackVOs =
        this.getWriteBackVOAndSetViewValue(viewVOs, wbVoMap);
    if (ArrayUtils.isEmpty(writebackVOs)) {
      return;
    }

    try {
      Z2CTServices.rewriteBackZ2(writebackVOs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    this.updateOrder(viewVOs);
  }

  private OrderViewVO[] getOrderViewVOs(IOrderWriteBackParaForZ2[] wbVos) {
    SqlBuilder sql = new SqlBuilder();
    if (1 == wbVos.length) {
      sql.append("select pk_order_b from po_order_b where ");
      sql.append("(");
      sql.append("csourcebid", wbVos[0].getSourceBID());
      sql.append(" or ");
      sql.append(OrderItemVO.CPRICEAUDIT_BID, wbVos[0].getSourceBID());
      sql.append(")");
      sql.append(" and ");
      sql.append("pk_supplier", wbVos[0].getPkSupplier());
      sql.append(" and ");
      sql.append("pk_material", wbVos[0].getPkMaterial());
      sql.append(" and ");
      sql.append("corigcurrencyid", wbVos[0].getCorigcurrencyid());
      sql.append(" and dr = 0");
    }
    else {
      List<List<Object>> data = new ArrayList<List<Object>>();
      for (IOrderWriteBackParaForZ2 wbVo : wbVos) {
        List<Object> temp = new ArrayList<Object>();
        temp.add(wbVo.getSourceBID());
        temp.add(wbVo.getPkSupplier());
        temp.add(wbVo.getPkMaterial());
        temp.add(wbVo.getCorigcurrencyid());
        data.add(temp);
      }

      String tempTableName =
          new TempTable().getTempTable("tmp_puct_001", new String[] {
            "csourcebid", "pk_supplier", "pk_material", "corigcurrencyid"
          }, new String[] {
            "char(20)", "char(20)", "char(20)", "char(20)"
          }, new JavaType[] {
            JavaType.String, JavaType.String, JavaType.String, JavaType.String
          }, data);

      if (StringUtil.isEmpty(tempTableName)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0139")/*
                                                                     * @res
                                                                     * "生成临时表失败"
                                                                     */);
      }

      sql.append("select b.pk_order_b from po_order_b b inner join "
          + tempTableName + " t on ");
      sql.append("(");
      sql.append("b.csourcebid = t.csourcebid  ");
      sql.append(" or ");
      sql.append("b." + OrderItemVO.CPRICEAUDIT_BID + " = t.csourcebid ");
      sql.append(")");
      sql.append(" and ");
      sql.append("b.pk_supplier = t.pk_supplier and ");
      sql.append("b.pk_material = t.pk_material and ");
      sql.append("b.corigcurrencyid = t.corigcurrencyid ");
      sql.append("where b.dr = 0");
    }

    DataAccessUtils utils = new DataAccessUtils();
    // 执行sql，查询表头id
    IRowSet rowset = utils.query(sql.toString());
    ViewQuery<OrderViewVO> query =
        new ViewQuery<OrderViewVO>(OrderViewVO.class);
    return query.query(rowset.toOneDimensionStringArray());

  }

  private Map<String, IOrderWriteBackParaForZ2> getParaMap(
      IOrderWriteBackParaForZ2[] wbVos) {
    Map<String, IOrderWriteBackParaForZ2> map =
        new HashMap<String, IOrderWriteBackParaForZ2>();

    for (IOrderWriteBackParaForZ2 wbVo : wbVos) {
      map.put(wbVo.getSourceBID(), wbVo);
    }

    return map;
  }

  private CtWriteBackForOrderVO[] getWriteBackVOAndSetViewValue(
      OrderViewVO[] viewVOs, Map<String, IOrderWriteBackParaForZ2> wbVoMap) {
    List<CtWriteBackForOrderVO> ctPuWirteBackList =
        new ArrayList<CtWriteBackForOrderVO>();
    for (OrderViewVO viewVO : viewVOs) {
      if (viewVO.getCcontractid() != null) {
        continue;
      }

      IOrderWriteBackParaForZ2 wbVo = wbVoMap.get(viewVO.getCsourcebid());
      String cpriceaudit_bid =
          (String) viewVO.getAttributeValue(OrderItemVO.CPRICEAUDIT_BID);
      if (null == wbVo && cpriceaudit_bid != null) {
        wbVo = wbVoMap.get(cpriceaudit_bid);
      }
      if (null == wbVo) {
        continue;
      }
      CtWriteBackForOrderVO ctPuWriteBackVO = new CtWriteBackForOrderVO();
      ctPuWriteBackVO.setPk_ctpu(wbVo.getCntHID());
      ctPuWriteBackVO.setPk_ctpu_b(wbVo.getCntBID());
      ctPuWriteBackVO.setNum(viewVO.getNnum());
      ctPuWriteBackVO.setPriceNum(viewVO.getNorigtaxmny());
      ctPuWriteBackVO.setcRowNum(viewVO.getCrowno());
      ctPuWirteBackList.add(ctPuWriteBackVO);

      viewVO.setCcontractid(wbVo.getCntHID());
      viewVO.setCcontractrowid(wbVo.getCntBID());
      viewVO.setVcontractcode(wbVo.getCntCode());
    }

    if (ctPuWirteBackList.size() > 0) {
      return ctPuWirteBackList
          .toArray(new CtWriteBackForOrderVO[ctPuWirteBackList.size()]);
    }

    return null;
  }

  private void updateOrder(OrderViewVO[] viewVOs) {
    ViewUpdate<OrderViewVO> update = new ViewUpdate<OrderViewVO>();
    update.update(viewVOs, OrderItemVO.class, new String[] {
      OrderItemVO.CCONTRACTID, OrderItemVO.CCONTRACTROWID,
      OrderItemVO.VCONTRACTCODE
    });
  }
}
