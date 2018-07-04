package nc.test.pu.pub.uitextfield;

import java.awt.BorderLayout;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import nc.bs.framework.test.AbstractTestCase;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UITextField;

/**
 * @since 6.0
 * @version 2012-1-7 下午11:14:21
 * @author zhaoyha
 */

public class UITextFieldColorTest extends AbstractTestCase {

  public void testBillItemTxtColor() throws Exception {
    UIDialog dlg = new UIDialog();
    final UITextField uitf = new UITextField();
    uitf.setForeground(Color.RED);
    uitf.addPropertyChangeListener(new PropertyChangeListener() {

      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        if (!"foreground".equals(evt.getPropertyName())
            || evt.getOldValue().equals(evt.getNewValue())) {
          return;
        }
        uitf.setForeground(Color.RED);

      }
    });
    uitf.setText("测试卡片字段的前景颜色变化");/* -=notranslate=- */
    uitf.setEditable(false);
    dlg.add(uitf, BorderLayout.NORTH);
    dlg.showModal();
  }

}
