/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-13 ����02:33:57
 */
package nc.ui.pu.est.editor.relacalc;

import nc.ui.pu.est.editor.head.after.EstHeadAfterEditHandler;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.list.ListHeadAfterEditEvent;
import nc.ui.pubapp.util.ListPanelValueUtils;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.util.EstRelationCalcUtil;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����ݹ�����������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-13 ����02:33:57
 */
public class GoodsEstRelationCal extends EstHeadAfterEditHandler implements
    IAppEventHandler<ListHeadAfterEditEvent> {

  @Override
  public void handleAppEvent(ListHeadAfterEditEvent e) {
    // �����ȵ���һ�¸��࣬����׼�������ݺ���ߵ�realAfterEdit
    // �����ȿɵ���ע�⵽editor��Ҳ��ע�⵽�ɹ��ı༭���ܵ�map��
    super.afterEdit(e);
    this.calculateTotalValue(e);
  }

  /**
   * ����ϼ����
   * 
   * @param e
   */
  private void calculateTotalValue(ListHeadAfterEditEvent e) {
    ListPanelValueUtils util = new ListPanelValueUtils(e.getBillListPanel());
    UFDouble estMny =
        util.getHeadUFDoubleValueAt(e.getRow(), GoodsEstVO.NESTMNY);
    UFDouble estTotalMny =
        util.getHeadUFDoubleValueAt(e.getRow(), GoodsEstVO.NESTTOTALMNY);
    UFDouble feeMny =
        util.getHeadUFDoubleValueAt(e.getRow(), GoodsEstVO.NFEEMNY);
    UFDouble feeTaxMny =
        util.getHeadUFDoubleValueAt(e.getRow(), GoodsEstVO.NFEETAXMNY);
    util.setHeadValueAt(MathTool.add(estMny, feeMny), e.getRow(),
        GoodsEstVO.NTOTALMNY);
    util.setHeadValueAt(MathTool.add(estTotalMny, feeTaxMny), e.getRow(),
        GoodsEstVO.NTOTALTAXMNY);
  }

  @Override
  protected void realAfterEdit(ListHeadAfterEditEvent event, PricePriority pp,
      EstRelationCalcUtil calUtil, IDataSetForCal dataset) {
    calUtil.calcDataSet(dataset, event.getKey(), pp);
  }
}
