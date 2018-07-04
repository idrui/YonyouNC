/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-14 上午08:21:27
 */
package nc.ui.pu.m21.editor.card.afteredit.body;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货计划：修改表体数量后的相应变化
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-14 上午08:21:27
 */
public class RPBodyNum implements ICardBodyAfterEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener#afterEdit(nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent)
   */
  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    int row = event.getRow();
    CardEditorHelper card = new CardEditorHelper(event.getBillCardPanel());

    UFDouble ufdOrderNum =
        (UFDouble) card.getBodyValue(row, OrderReceivePlanVO.NNUM);
    UFDouble ufdAccNum =
        (UFDouble) card.getBodyValue(row, OrderReceivePlanVO.NACCUMDEVNUM);
    if (MathTool.compareTo(ufdOrderNum, ufdAccNum) < 0) {
      card.setBodyValue(row, OrderReceivePlanVO.NNUM, event.getOldValue());
      // 到货计划已经执行运输，不能将到货计划数量修改的比已经运输数量小
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0210")/*
                                                                   * @res
                                                                   * "到货计划已经执行运输，不能将到货计划数量修改的比已经运输数量小"
                                                                   */);
    }
  }

}
