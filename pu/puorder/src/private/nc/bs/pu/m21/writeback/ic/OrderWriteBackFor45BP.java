/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-15 ����10:35:37
 */
package nc.bs.pu.m21.writeback.ic;

import java.util.HashSet;
import java.util.Set;

import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.bs.pu.m21.writeback.OrderWBStoreArrvTolerRule;
import nc.bs.pu.m21.writeback.OrderWritebackTolerRule;
import nc.bs.pu.m21.writeback.ReceivePlanTolerRule;
import nc.bs.pu.m21.writeback.ic.rule.AccStoreNumCalcRule;
import nc.bs.pu.m21.writeback.ic.rule.AccStoreNumChkRule;
import nc.bs.pu.m21.writeback.ic.rule.AutoCloseProcRule;
import nc.bs.pu.m21.writeback.ic.rule.ReceivePlanStoreCalRule;
import nc.bs.pu.m21.writeback.ic.rule.ReceivePlanStoreChkRule;
import nc.bs.pu.m21.writeback.ic.rule.StoreCloseChkRule;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.pubitf.pu.m21.ic.m45.IOrderWriteBackPara;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.m21.pub.OrderVOUtil;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>Ϊ�ɹ���ⵥ��д�ɹ����������BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-15 ����10:35:37
 */
public class OrderWriteBackFor45BP {

  public void writeback(IOrderWriteBackPara[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    String[] bids = OrderVOUtil.getInsance().getBIDs(vos);
    OrderViewVO[] views =
        new ViewQuery<OrderViewVO>(OrderViewVO.class).query(bids);

    if (ArrayUtils.isEmpty(views)) {
      return;
    }

    // ����
    OrderViewVO[] orgViews = new OrderViewVO[views.length];
    for (int i = 0; i < views.length; ++i) {
      orgViews[i] = (OrderViewVO) views[i].clone();
    }

    // �����ƻ�(������д���ۼ��������������)
    OrderReceivePlanVO[] rpVOs = this.getRPVOs(vos);
    if (!ArrayUtils.isEmpty(rpVOs)) {
      AroundProcesser<OrderReceivePlanVO> rpPrecosser =
          new AroundProcesser<OrderReceivePlanVO>(
              OrderPluginPoint.RECEIVE_PLAN_WRITEBACK_45);
      this.addRPRule(rpPrecosser, vos, views);

      rpPrecosser.before(rpVOs);
      String[] rpNames =
          new String[] {
            OrderReceivePlanVO.NACCUMSTORENUM,
            OrderReceivePlanVO.NBACKSTORENUM, OrderItemVO.NACCUMARRVNUM
          };
      VOUpdate<OrderReceivePlanVO> rpUpdate =
          new VOUpdate<OrderReceivePlanVO>();
      rpUpdate.update(rpVOs, rpNames);
      rpPrecosser.after(rpVOs);
    }

    CompareAroundProcesser<OrderViewVO> processer =
        new CompareAroundProcesser<OrderViewVO>(OrderPluginPoint.WRITEBACK_45);
    this.addRule(processer, vos, orgViews);
    processer.before(views, orgViews);

    String[] wbNames =
        new String[] {
          OrderItemVO.NACCUMSTORENUM, OrderItemVO.NBACKSTORENUM,
          OrderItemVO.NACCUMARRVNUM
        };
    ViewUpdate<OrderViewVO> bo = new ViewUpdate<OrderViewVO>();
    views = bo.update(views, OrderItemVO.class, wbNames);
    processer.after(views, orgViews);

  }

  private void addRPRule(AroundProcesser<OrderReceivePlanVO> processer,
      IOrderWriteBackPara[] vos, OrderViewVO[] views) {
    processer.addBeforeRule(new ReceivePlanStoreCalRule(vos, views));
    processer.addBeforeRule(new ReceivePlanStoreChkRule(vos, views));
    processer.addAfterRule(new ReceivePlanTolerRule(MaterialVO.INTOLERANCE,
        PUSysParamUtil.getPO03(views[0].getPk_org()),
        OrderReceivePlanVO.NACCUMSTORENUM, UFBoolean.valueOf(vos[0]
            .isNumUserConfirm())));
  }

  private void addRule(CompareAroundProcesser<OrderViewVO> processer,
      IOrderWriteBackPara[] vos, OrderViewVO[] orgViews) {
    processer.addBeforeRule(new AccStoreNumCalcRule(vos));// �ۼ������������
    processer.addBeforeRule(new StoreCloseChkRule(orgViews, vos));// ���رռ��

    processer.addBeforeFinalRule(new AccStoreNumChkRule(vos));// �ۼ�����������

    // �ۼ���������ݲ���
    processer.addAfterRule(new OrderWritebackTolerRule(MaterialVO.INTOLERANCE,
        PUSysParamUtil.getPO03(orgViews[0].getPk_org()),
        OrderItemVO.NACCUMSTORENUM,
        UFBoolean.valueOf(vos[0].isNumUserConfirm())));
    processer.addAfterRule(new OrderWBStoreArrvTolerRule(false, vos[0]
        .isNumUserConfirm()));

    processer.addAfterRule(new AutoCloseProcRule(vos));// �Զ��ر�(��)����
  }

  /**
   * ���������������õ������ƻ�VO
   * <p>
   * <b>����˵��</b>
   * 
   * @param views
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-26 ����04:11:38
   */
  private OrderReceivePlanVO[] getRPVOs(IOrderWriteBackPara[] wbVos) {
    if (ArrayUtils.isEmpty(wbVos)) {
      return null;
    }
    Set<String> set = new HashSet<String>();
    for (IOrderWriteBackPara vo : wbVos) {
      if (vo != null && vo.getBBID() != null) {
        set.add(vo.getBBID());
      }
    }

    if (set.isEmpty()) {
      return null;
    }

    String[] pkOrderBBs = set.toArray(new String[0]);
    VOQuery<OrderReceivePlanVO> query =
        new VOQuery<OrderReceivePlanVO>(OrderReceivePlanVO.class);
    OrderReceivePlanVO[] rpVOs = query.query(pkOrderBBs);
    return rpVOs;
  }

}
