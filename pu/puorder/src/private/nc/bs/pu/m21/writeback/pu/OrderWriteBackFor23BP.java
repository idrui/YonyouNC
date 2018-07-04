/**
 * $�ļ�˵��$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-17 ����09:02:28
 */
package nc.bs.pu.m21.writeback.pu;

import java.util.HashSet;
import java.util.Set;

import nc.bs.pu.m21.writeback.OrderWBStoreArrvTolerRule;
import nc.bs.pu.m21.writeback.OrderWritebackTolerRule;
import nc.bs.pu.m21.writeback.ReceivePlanTolerRule;
import nc.bs.pu.m21.writeback.pu.rule.AccArrNumChkRule;
import nc.bs.pu.m21.writeback.pu.rule.AccArriveNumCalcRule;
import nc.bs.pu.m21.writeback.pu.rule.ArriveCloseChkRule;
import nc.bs.pu.m21.writeback.pu.rule.AutoArriveCloseRule;
import nc.bs.pu.m21.writeback.pu.rule.ReceivePlanArrCalRule;
import nc.bs.pu.m21.writeback.pu.rule.ReceivePlanArrChkRule;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.pubitf.pu.m21.ic.m45.IOrderWriteBackPara;
import nc.pubitf.pu.m21.pu.m23.IOrderWriteBackParaFor23;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.m21.pub.OrderVOUtil;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>Ϊ�ɹ���������д�ɹ����������BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-1-17 ����09:02:28
 */
public class OrderWriteBackFor23BP {
  private UFBoolean isUserConfirm;

  public OrderWriteBackFor23BP(UFBoolean isUserConfirm) {
    this.isUserConfirm = isUserConfirm;
  }

  public void writeback(IOrderWriteBackParaFor23[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    String[] bids = OrderVOUtil.getInsance().getBIDs(vos);
    OrderViewVO[] views =
        new ViewQuery<OrderViewVO>(OrderViewVO.class).query(bids);

    if (ArrayUtils.isEmpty(views)) {
      return;
    }

    OrderViewVO[] orgViews = new OrderViewVO[views.length];
    for (int i = 0; i < views.length; ++i) {
      orgViews[i] = (OrderViewVO) views[i].clone();
    }

    // �����ƻ�(������д���ۼƵ�������������)
    OrderReceivePlanVO[] rpVOs = this.getRPVOs(vos);
    if (!ArrayUtils.isEmpty(rpVOs)) {
      AroundProcesser<OrderReceivePlanVO> rpPrecosser =
          new AroundProcesser<OrderReceivePlanVO>(null);
      this.addRPRule(rpPrecosser, vos, views);
      rpPrecosser.before(rpVOs);
      String[] rpNames =
          new String[] {
            OrderReceivePlanVO.NACCUMARRVNUM, OrderReceivePlanVO.NBACKARRVNUM,
            OrderReceivePlanVO.NACCUMWASTNUM
          };
      VOUpdate<OrderReceivePlanVO> rpUpdate =
          new VOUpdate<OrderReceivePlanVO>();
      rpUpdate.update(rpVOs, rpNames);
      rpPrecosser.after(rpVOs);
    }

    CompareAroundProcesser<OrderViewVO> processer =
        new CompareAroundProcesser<OrderViewVO>(null);
    this.addRule(processer, vos, orgViews);
    processer.before(views, orgViews);
    processer.after(views, orgViews);
  }

  private void addRPRule(AroundProcesser<OrderReceivePlanVO> processer,
      IOrderWriteBackParaFor23[] vos, OrderViewVO[] views) {
    processer.addBeforeRule(new ReceivePlanArrCalRule(vos, views));
    processer.addBeforeRule(new ReceivePlanArrChkRule(vos, views));
    processer.addAfterRule(new ReceivePlanTolerRule(MaterialVO.INTOLERANCE,
        PUSysParamUtil.getPO02(views[0].getPk_org()),
        OrderReceivePlanVO.NACCUMARRVNUM, this.isUserConfirm));
  }

  private void addRule(CompareAroundProcesser<OrderViewVO> processer,
      IOrderWriteBackParaFor23[] vos, OrderViewVO[] orgViews) {
    processer.addBeforeRule(new AccArriveNumCalcRule(vos));// �����ۼƵ���(��)��;������
    processer.addBeforeRule(new ArriveCloseChkRule(orgViews, vos));// �����رռ��
    processer.addBeforeRule(new AccArrNumChkRule(vos));
    processer.addAfterRule(new AutoArriveCloseRule(vos));// �Զ��ر�(��)����
    // �ۼƵ����������
    processer.addAfterRule(new OrderWritebackTolerRule(MaterialVO.INTOLERANCE,
        PUSysParamUtil.getPO02(orgViews[0].getPk_org()),
        OrderItemVO.NACCUMARRVNUM, this.isUserConfirm));
    processer.addAfterRule(new OrderWBStoreArrvTolerRule(true, ValueUtils
        .getBoolean(this.isUserConfirm)));
  }

  /**
   * ��������������
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
