/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-23 下午04:31:23
 */
package nc.ui.pu.costfactor.view.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import nc.ui.pu.costfactor.view.ListShowPanel;
import nc.ui.pub.beans.UIList;
import nc.vo.pu.costfactor.entity.CostfactorItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>事件响应: 包括上移、下移、置顶、置底、显示和选择行
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-6-23 下午04:31:23
 */
public class ActionEventHandler implements ActionListener,
    ListSelectionListener {

  private ListShowPanel panel;

  public ActionEventHandler(ListShowPanel panel) {
    this.panel = panel;
  }

  /**
   * 父类方法重写
   * 
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == this.panel.getUpBt()) {
      int[] selectIndexs =
          ((UIList) this.panel.getShowList()).getSelectedIndices();
      this.panel.getChgItemOrder().forward(new CostfactorItemVO[] {
        this.panel.getSelectCostfactor(selectIndexs[0])
      });
      int[] indices =
          this.panel.getModel().forward(
              this.panel.getShowList().getSelectedValues());
      this.panel.getShowList().setSelectedIndices(indices);
    }
    else if (e.getSource() == this.panel.getDownBt()) {
      int[] selectIndexs =
          ((UIList) this.panel.getShowList()).getSelectedIndices();
      this.panel.getChgItemOrder().backward(new CostfactorItemVO[] {
        this.panel.getSelectCostfactor(selectIndexs[0])
      });
      int[] indices =
          this.panel.getModel().backward(
              this.panel.getShowList().getSelectedValues());
      this.panel.getShowList().setSelectedIndices(indices);
    }
    else if (e.getSource() == this.panel.getTopBt()) {
      int[] selectIndexs =
          ((UIList) this.panel.getShowList()).getSelectedIndices();
      this.panel.getChgItemOrder().top(new CostfactorItemVO[] {
        this.panel.getSelectCostfactor(selectIndexs[0])
      });
      int[] indices =
          this.panel.getModel().moveTop(
              this.panel.getShowList().getSelectedValues());
      this.panel.getShowList().setSelectedIndices(indices);
    }
    else if (e.getSource() == this.panel.getBottomBt()) {
      int[] selectIndexs =
          ((UIList) this.panel.getShowList()).getSelectedIndices();
      this.panel.getChgItemOrder().bottom(new CostfactorItemVO[] {
        this.panel.getSelectCostfactor(selectIndexs[0])
      });
      int[] indices =
          this.panel.getModel().moveBottom(
              this.panel.getShowList().getSelectedValues());
      this.panel.getShowList().setSelectedIndices(indices);
    }

    // 是否显示勾选框事件
    else if (e.getSource() == this.panel.getIsShowChk()) {
      int[] selectIndexs =
          ((UIList) this.panel.getShowList()).getSelectedIndices();
      if (selectIndexs.length > 0) {
        boolean b = this.panel.getIsShowChk().isSelected();
        CostfactorItemVO selectCosfacItemVOs =
            this.panel.getSelectCostfactor(selectIndexs[0]);
        selectCosfacItemVOs.setBshow(UFBoolean.valueOf(b));
      }
    }
    else if (e.getSource() == this.panel.getOkBt()) {
      this.panel.setShowOrder();
      this.panel.closeOK();
    }
    else if (e.getSource() == this.panel.getCancelBt()) {
      this.panel.closeCancel();
    }

  }

  /**
   * 父类方法重写
   * 
   * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
   */
  @Override
  public void valueChanged(ListSelectionEvent e) {
    int[] selectIndexs =
        ((UIList) this.panel.getShowList()).getSelectedIndices();
    if (selectIndexs.length > 0) {
      boolean b = true;
      CostfactorItemVO selectCosfacItemVO =
          this.panel.getSelectCostfactor(selectIndexs[0]);
      if (selectCosfacItemVO != null) {
        b = ValueUtils.getBoolean(selectCosfacItemVO.getBshow());
      }
      this.panel.getIsShowChk().setSelected(b);
    }
  }

}
