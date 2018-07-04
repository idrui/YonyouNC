package nc.ui.pu.m27.match.editor.list.afteredit;

import nc.ui.pu.m27.match.util.MatchRelaCalc;
import nc.ui.pu.pub.editor.list.listener.IListHeadAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.list.ListHeadAfterEditEvent;
import nc.vo.pu.m27.entity.MatchMaterialVO;
import nc.vo.pub.BusinessRuntimeException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * �����㵥�۵ı༭���¼�(�����޷�Ʊ����) <br>
 * ֧�ֵ��ۺͽ�����Ϊ��Ľ��㣬�����ݹ�Ӧ����ֻ���ݹ�������Ӧ��
 *
 * @since 6.0
 * @version 2011-2-6 ����10:30:15
 * @author zhaoyha
 */
public class StockSettlePrice implements IListHeadAfterEditEventListener {

  @Override
  public void afterEdit(ListHeadAfterEditEvent event) {
    try {
      this.check(event);
    }
    finally {
      // ��������
      new MatchRelaCalc(event).calc(event.getKey());
    }
  }

  private void check(ListHeadAfterEditEvent event) {
    // ֧�ֵ��ۺͽ�����Ϊ��Ľ��㣬�����ݹ�Ӧ����ֻ���ݹ�������Ӧ��
    UFDouble newprice = (UFDouble) event.getValue();
    // if (MathTool.compareTo(newprice, UFDouble.ZERO_DBL) <= 0) {
    // event.getBillListPanel().getHeadBillModel().setValueAt(
    // event.getOldValue(), event.getRow(), MatchMaterialVO.NPRICE);
    // throw new BusinessRuntimeException("��ⵥ���㵥�۲���С�ڵ�����");
    // }
    if (MathTool.compareTo(newprice, UFDouble.ZERO_DBL) < 0) {
      event.getBillListPanel().getHeadBillModel().setValueAt(
          event.getOldValue(), event.getRow(), MatchMaterialVO.NPRICE);
      throw new BusinessRuntimeException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0028")/*@res "��ⵥ���㵥�۲���С����"*/);
    }
  }

}