/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-26 上午08:48:18
 */
package nc.impl.pu.m21.action.rule.approve;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.StatusOnWayItemVO;
import nc.vo.pu.m21.pub.TrantypeUtil;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              订单审核时往在途状态表中插入一条数据
 * @scene
 *        采购订单审批
 * @param 无
 * @since 6.3
 * @version 2014-10-20 下午4:50:52
 * @author luojw
 */
public class InsertStatusOnWayRule implements IRule<OrderVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    Map<String, PoTransTypeVO> transtypeMap = this.getTransType(vos);

    Set<StatusOnWayItemVO> statusOnWayItemSet =
        new HashSet<StatusOnWayItemVO>();
    for (OrderVO vo : vos) {
      this.getStatusOnWay(vo, statusOnWayItemSet, transtypeMap);
    }

    // 如果为空就不往po_order_bb表里插入数据。
    if (0 == statusOnWayItemSet.size()) {
      return;
    }

    VOInsert<StatusOnWayItemVO> insertService =
        new VOInsert<StatusOnWayItemVO>();
    insertService.insert(statusOnWayItemSet
        .toArray(new StatusOnWayItemVO[statusOnWayItemSet.size()]));

  }

  /**
   * 方法功能描述：在途状态表
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @param statusOnWayItemSet
   * @param transtypeMap
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-26 上午09:14:36
   */
  private void getStatusOnWay(OrderVO vo,
      Set<StatusOnWayItemVO> statusOnWayItemSet,
      Map<String, PoTransTypeVO> transtypeMap) {
    // 到货计划 wulla
    // 孙聪测过一次和到货计划没关系
    OrderHeaderVO headerVO = vo.getHVO();
    String vtrantypecode = headerVO.getVtrantypecode();
    PoTransTypeVO transTypeVO = transtypeMap.get(vtrantypecode);
    if (transTypeVO == null) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004030_0", "04004030-0234", null, new String[] {
            vtrantypecode
          })/* 采购订单交易类型{0}扩展表没有数据，请检查 */);
      return;
    }
    Integer iapproveaft =
        TrantypeUtil.getInstance().getNextStatus(
            OnwayStatus.STATUS_AUDIT.toInt(), transTypeVO);

    // 如果下一个订单状态为空，不插入数据
    if (iapproveaft == null) {
      return;
    }

    OrderItemVO[] itemVOs = vo.getBVO();
    for (OrderItemVO itemVO : itemVOs) {

      StatusOnWayItemVO statusOnWayItemVO = new StatusOnWayItemVO();
      // 此处是设置在途单据日期
      // statusOnWayItemVO.setDbilldate(headerVO.getDbilldate());
      statusOnWayItemVO.setDplanarrvdate(itemVO.getDplanarrvdate());
      statusOnWayItemVO.setFonwaystatus(iapproveaft);
      statusOnWayItemVO.setIsoperated(UFBoolean.FALSE);
      statusOnWayItemVO.setNonwaynum(itemVO.getNnum());
      // 主数量为最大可操作数量
      statusOnWayItemVO.setNmaxhandlenum(itemVO.getNnum());
      statusOnWayItemVO.setPk_group(itemVO.getPk_group());
      statusOnWayItemVO.setPk_order(itemVO.getPk_order());
      statusOnWayItemVO.setPk_order_b(itemVO.getPk_order_b());
      statusOnWayItemVO.setPk_org(itemVO.getPk_org());
      statusOnWayItemVO.setPk_org_v(itemVO.getPk_org_v());
      // 此处是设置在途单据号
      // statusOnWayItemVO.setVbillcode(vbillcode);

      statusOnWayItemSet.add(statusOnWayItemVO);
    }
  }

  /**
   * 方法功能描述：订单对应的审核下一状态Map
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-26 上午09:00:27
   */
  private Map<String, PoTransTypeVO> getTransType(OrderVO[] vos) {
    Set<String> set = new HashSet<String>();
    for (OrderVO vo : vos) {
      String vtrantypecode = vo.getHVO().getVtrantypecode();
      set.add(vtrantypecode);
    }

    IPoTransTypeQuery service =
        NCLocator.getInstance().lookup(IPoTransTypeQuery.class);
    try {
      // return service.queryAttrByTypes(set.toArray(new String[set.size()]),
      // new String[] {
      // PoTransTypeVO.BOUTPUT, PoTransTypeVO.BCONFIRM,
      // PoTransTypeVO.BCONSIGN, PoTransTypeVO.BLOAD, PoTransTypeVO.BCUSTOM,
      // PoTransTypeVO.BOUTCUSTOM, PoTransTypeVO.BARRIVE,
      // PoTransTypeVO.BSTORE
      // });
      return service
          .queryAttrByTypes(set.toArray(new String[set.size()]), null);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    return null;
  }

}
