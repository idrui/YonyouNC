/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2009-12-30 上午10:17:57
 */
package nc.bs.pu.m21.maintain.rule.save;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.pub.rule.NumAndOrigmnySum;
import nc.vo.pu.pub.rule.WeightVolumePieceSum;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pu.pub.util.ApproveFlowUtil;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.util.AuditInfoUtils;

import org.apache.commons.lang.ObjectUtils;

/**
 * @description
 *              采购订单必要项值填充处理
 * @scene
 *        采购订单保存修改
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午1:47:34
 * @author luojw
 */
public class NecessaryFillRule implements ICompareRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos, OrderVO[] originVOs) {
    this.setInfo(vos);
    Map<String, OrderVO> map = AggVOUtil.createVOMap(originVOs);
    for (OrderVO vo : vos) {
      if (VOStatus.NEW == vo.getHVO().getStatus()) {
        this.setDefaultValueForNew(vo);
      }
      else {
        this.setDefaultValueForUpdate(vo, map);
      }
      // 计算价税合计
      this.setTotalNumAndOrigmny(vo);
      this.setWeightVolumePiece(vo);
    }
  }

  private void setDefaultValueForNew(OrderVO vo) {

    OrderHeaderVO header = vo.getHVO();
    UFDate dbilldate = header.getDbilldate();
    String pk_supplier = header.getPk_supplier();
    String orgcurrencyid = header.getCorigcurrencyid();

    // 最新版本
    header.setBislatest(UFBoolean.TRUE);
    // 版本号
    header.setNversion(Integer.valueOf(1));

    OrderItemVO[] items = vo.getBVO();
    for (OrderItemVO item : items) {
      // 单据行的激活状态
      item.setFisactive((Integer) EnumActive.ACTIVE.value());
      // 冗余字段的处理
      item.setDbilldate(dbilldate);
      item.setPk_supplier(pk_supplier);
      item.setCorigcurrencyid(orgcurrencyid);
      item.setPk_group(header.getPk_group());
      item.setPk_org(header.getPk_org());
      item.setPk_org_v(header.getPk_org_v());
    }
  }

  private void setDefaultValueForUpdate(OrderVO vo, Map<String, OrderVO> map) {
    OrderHeaderVO header = vo.getHVO();
    UFDate dbilldate = header.getDbilldate();
    String pk_supplier = header.getPk_supplier();
    String orgcurrencyid = header.getCorigcurrencyid();

    boolean change = false;
    if (map != null) {
      OrderVO orgVO = map.get(header.getPk_order());
      if (orgVO != null && orgVO.getHVO() != null) {
        OrderHeaderVO orgHead = orgVO.getHVO();
        if (!ObjectUtils.equals(dbilldate, orgHead.getDbilldate())
            || !ObjectUtils.equals(pk_supplier, orgHead.getPk_supplier())
            || !ObjectUtils.equals(orgcurrencyid, orgHead.getCorigcurrencyid())) {
          change = true;
        }
      }
    }

    OrderItemVO[] items = vo.getBVO();
    for (OrderItemVO item : items) {
      if (item.getFisactive() == null) {
        // 单据行的激活状态
        item.setFisactive((Integer) EnumActive.ACTIVE.value());
      }
      if (VOStatus.UNCHANGED == item.getStatus() && change) {
        item.setStatus(VOStatus.UPDATED);
      }
      // 冗余字段的处理
      item.setDbilldate(dbilldate);
      item.setPk_supplier(pk_supplier);
      item.setCorigcurrencyid(orgcurrencyid);
      item.setPk_group(header.getPk_group());
      item.setPk_org(header.getPk_org());
      item.setPk_org_v(header.getPk_org_v());
    }
  }

  private void setInfo(OrderVO[] vos) {
    if (vos[0].getHVO().getStatus() == VOStatus.NEW) {
      AuditInfoUtils.setAddAuditInfo(vos);
      ApproveFlowUtil.setBillMakeInfo(vos);
    }
    else {
      AuditInfoUtils.setUpdateAuditInfo(vos);
    }
  }

  private void setTotalNumAndOrigmny(OrderVO vo) {
    // if (vo.getHVO().getNtotalastnum() != null
    // && vo.getHVO().getNtotalorigmny() != null) {
    // return;
    // }
    BillHelper<OrderVO> bill = new BillHelper<OrderVO>(vo);
    NumAndOrigmnySum sum = new NumAndOrigmnySum(bill);
    sum.setBlargessField(OrderItemVO.BLARGESS);
    sum.sum();
  }

  private void setWeightVolumePiece(OrderVO vo) {
    BillHelper<OrderVO> bill = new BillHelper<OrderVO>(vo);
    WeightVolumePieceSum calc = new WeightVolumePieceSum(bill);
    calc.sum();
  }

}
