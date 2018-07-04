package nc.ui.pu.m27.match.editor.list.afteredit;

import nc.ui.pu.m27.match.util.MatchRelaCalc;
import nc.ui.pu.pub.editor.list.listener.IListHeadAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.list.ListHeadAfterEditEvent;
import nc.vo.pu.m27.entity.MatchMaterialVO;
import nc.vo.pub.BusinessRuntimeException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * ��Ȿ�ν�����ı༭���¼�(�����޷�Ʊ����)<br>
 * ֧�ֵ��ۺͽ�����Ϊ��Ľ��㣬�����ݹ�Ӧ����ֻ���ݹ�������Ӧ��
 *
 * @since 6.0
 * @version 2011-2-6 ����10:30:15
 * @author zhaoyha
 */
public class StockSettleMny implements IListHeadAfterEditEventListener {

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
    UFDouble newmny = (UFDouble) event.getValue();
    UFDouble stlnum =
        (UFDouble) event.getBillListPanel().getHeadBillModel().getValueAt(
            event.getRow(), MatchMaterialVO.NCURSTOCKSETTLENUM);
    // ֧�ֵ��ۺͽ�����Ϊ��Ľ��㣬�����ݹ�Ӧ����ֻ���ݹ�������Ӧ��
    // if (MathTool.compareTo(newmny, UFDouble.ZERO_DBL) == 0) {
    // event.getBillListPanel().getHeadBillModel().setValueAt(
    // event.getOldValue(), event.getRow(), MatchMaterialVO.NCURSEETLEMNY);
    // throw new BusinessRuntimeException("��ⵥ���ν�����ܵ�����");
    // }
    // else
    if (MathTool.isDiffSign(newmny, stlnum)) {
      event.getBillListPanel().getHeadBillModel().setValueAt(
          event.getOldValue(), event.getRow(), MatchMaterialVO.NCURSEETLEMNY);
      throw new BusinessRuntimeException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0027")/*@res "��ⵥ���ν��������������뱾�ν�������һ�£�"*/);
    }
  }

}