package nc.ui.pu.m21.view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.WindowConstants;

import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.ClientContext;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.vo.pu.m21.entity.ApDataVO;
import nc.vo.pu.pub.enumeration.PuNodeKey;
import nc.vo.pubapp.scale.PosEnum;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>应付帐款查询
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-3 下午03:15:41
 */
public class ApQueryDlg extends UIDialog implements ActionListener {

  private static final long serialVersionUID = -9156545023856849984L;

  public ApQueryDlg(Container parent, ApDataVO[] vos) {
    super(parent);
    this.init(vos);
  }
  public ApQueryDlg(Container parent, ApDataVO[] vos,boolean reset) {
	    super(parent,reset);
	    this.init(vos);
	  }
  @Override
  public void actionPerformed(ActionEvent e) {
    return;
  }

  private void init(ApDataVO[] vos) {
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.setSize(800, 600);
    this.setTitle(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004030_0", "04004030-0089")/* @res "应付款" */);
    BillCardPanel apPanel = new BillCardPanel();
    apPanel.loadTemplet(PuNodeKey.n40040400AP.code());
    apPanel.setEnabled(false);
    this.add(apPanel);
    this.initScale(apPanel);
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    apPanel.getBillData().setBodyValueVO(vos);
    // for (int i = 0; i < vos.length; ++i) {
    // apPanel.getBillModel().execEditFormulas(i);
    apPanel.getBillModel().loadLoadRelationItemValue();
    // }
  }

  private void initScale(BillCardPanel apPanel) {
    String pk_group = ClientContext.getInstance().getPk_group();
    CardPaneScaleProcessor processer =
        new CardPaneScaleProcessor(pk_group, apPanel);
    processer.setMnyCtlInfo(new String[] {
      ApDataVO.NFINANCEAP, ApDataVO.NOPERATIONAP, ApDataVO.NORDERAP,
      ApDataVO.NORDERPAYMNY, ApDataVO.NUNVERIFYMNY
    }, PosEnum.body, null, ApDataVO.CCURRENCYID, PosEnum.body, null);
    processer.process();
  }

}
