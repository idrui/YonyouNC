package nc.ui.pu.pub.view;

import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator;

/**
 * 适用范围：卡片界面且非编辑态且需要编辑且需要注入物料辅助属性的场景。
 * 由于平台在编辑前判断如果是非编辑态则返回false，导致业务组自己的编辑前返回true失效。
 * 平台拒绝修改，于是采购提出自己的处理类。
 * 
 * @since 6.3
 * @version 2012-12-19 下午02:00:47
 * @author lixyp
 */
public class PUMarAsstPreparator extends MarAsstPreparator {

  @Override
  protected void beforEdit(CardBodyBeforeEditEvent e) {
    boolean editable =
        this.getMarAsstEditDelegate().beforEdit(
            e.getBillCardPanel().getBillModel(), e.getRow(), e.getKey(),
            e.getReturnValue());
    e.setReturnValue(Boolean.valueOf(editable));
    return;
  }

}
