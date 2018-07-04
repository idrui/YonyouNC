/**
 * $�ļ�˵��$
 *
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-4 ����07:36:40
 */
package nc.ui.pu.m20.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m20.IPraybillMaintain;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.pub.power.PowerCheckUtils;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.editor.BillListView;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.enumeration.EnumBillStatue;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;
import nc.vo.pubapp.pub.power.PowerActionEnum;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�йر�Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-4 ����07:36:40
 */
public class PraybillCloseLineAction extends NCAction {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 6144093435749100115L;

  private BillForm billForm;

  private BillListView list;

  private AbstractAppModel model;

  private int[] selectIndex = null;

  public PraybillCloseLineAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_LINECLOSE);
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.getSelectIndex();

    this.doBeforeAction();

    PraybillVO vo = this.getSelectedVO();

    PraybillVO[] vos = new PraybillVO[] {
      vo
    };
    PowerCheckUtils.checkHasPermission(vos, POBillType.PrayBill.getCode(),
        PowerActionEnum.CLOSE.getActioncode(), PraybillHeaderVO.VBILLCODE);

    if (null == vo) {
      return;
    }

    PraybillVO oldVO = this.getOldVO();

    // ����VO����
    ClientBillToServer<PraybillVO> tool = new ClientBillToServer<PraybillVO>();
    PraybillVO[] lightVOs = tool.construct(new PraybillVO[] {
      vo
    }, new PraybillVO[] {
      vo
    });

    // ִ��Զ�̵��ã������йر�
    IPraybillMaintain PraybillCloseService =
        NCLocator.getInstance().lookup(IPraybillMaintain.class);
    PraybillVO[] returnVos = PraybillCloseService.closeBillRow(lightVOs);

    new ClientBillCombinServer<PraybillVO>().combine(new PraybillVO[] {
      oldVO
    }, returnVos);
    
    ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
			.getStrByID("4004000_0", "04004000-0163", null,
					new String[] {this.selectIndex.length + ""}), /*
                     											   * @res
                     											   * "�йرղ����ɹ������رգ����У�"
                     											   */
             this.billForm.getModel().getContext());

    this.getAppModel().directlyUpdate(oldVO);
  }

  /**
   * @param billForm
   *          Ҫ���õ� billForm
   */
  public void setBillForm(BillForm billForm) {
    this.billForm = billForm;
  }

  /**
   * @param list
   *          Ҫ���õ� list
   */
  public void setList(BillListView list) {
    this.list = list;
  }

  /**
   * @param model
   *          Ҫ���õ� model
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  /**
   * ���������������ж���û��ѡ����
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-10 ����10:31:29
   */
  private void doBeforeAction() {
    if (ArrayUtils.isEmpty(this.selectIndex)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004020_0", "04004020-0007")/*
                                                                   * @res
                                                                   * "����ѡ����"
                                                                   */);
    }
  }

  /**
   * ���������������õ�model
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-6 ����04:16:31
   */
  private AbstractAppModel getAppModel() {
    return this.model;
  }

  /**
   * ������������������ǿ�Ƭ���棬�ӿ�Ƭ��ȡ��������
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-4 ����11:34:18
   */
  private PraybillVO getCloseVOFromBillForm() {
    if (ArrayUtils.isEmpty(this.selectIndex)) {
      return null;
    }

    // ��þ�VO��Ȼ�󽫱�����Ϊѡ���VO���õ�ѡ������VO
    PraybillVO oldVO = this.getOldVO();
    if (null == oldVO) {
      return null;
    }
    PraybillVO vo = (PraybillVO) oldVO.clone();
    // Map<String, PraybillItemVO> itemMap =
    // AggVOUtil.createItemMap(new PraybillVO[] {
    // vo
    // });
    BillIndex index = new BillIndex(new PraybillVO[] {
      vo
    });
    IVOMeta meta = vo.getMetaData().getVOMeta(PraybillItemVO.class);
    PraybillItemVO[] itemVOs = new PraybillItemVO[this.selectIndex.length];
    for (int i = 0; i < this.selectIndex.length; ++i) {
      String pk_Praybill_b =
          (String) this.billForm.getBillCardPanel().getBodyValueAt(
              this.selectIndex[i], PraybillItemVO.PK_PRAYBILL_B);
      // PraybillItemVO itemVO = itemMap.get(pk_Praybill_b);
      PraybillItemVO itemVO = (PraybillItemVO) index.get(meta, pk_Praybill_b);
      if (null == itemVO) {
        return null;
      }
      itemVOs[i] = itemVO;
    }
    vo.setBVO(itemVOs);

    return vo;
  }

  /**
   * ��������������������б���棬���б��ȡ��������
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-4 ����12:13:40
   */
  private PraybillVO getCloseVOFromListView() {
    if (ArrayUtils.isEmpty(this.selectIndex)) {
      return null;
    }

    // ��þ�VO��Ȼ�󽫱�����Ϊѡ���VO���õ�ѡ�����ݵ�VO
    PraybillVO oldVO = this.getOldVO();
    if (null == oldVO) {
      return null;
    }
    PraybillVO vo = (PraybillVO) oldVO.clone();
    // Map<String, PraybillItemVO> itemMap =
    // AggVOUtil.createItemMap(new PraybillVO[] {
    // vo
    // });
    BillIndex index = new BillIndex(new PraybillVO[] {
      vo
    });
    IVOMeta meta = vo.getMetaData().getVOMeta(PraybillItemVO.class);
    PraybillItemVO[] itemVOs = new PraybillItemVO[this.selectIndex.length];
    for (int i = 0; i < this.selectIndex.length; ++i) {
      String pk_Praybill_b =
          (String) this.list.getBillListPanel().getBodyBillModel()
              .getValueAt(this.selectIndex[i], PraybillItemVO.PK_PRAYBILL_B);
      // PraybillItemVO itemVO = itemMap.get(pk_Praybill_b);
      PraybillItemVO itemVO = (PraybillItemVO) index.get(meta, pk_Praybill_b);
      if (null == itemVO) {
        return null;
      }
      itemVOs[i] = itemVO;
    }
    vo.setBVO(itemVOs);

    return vo;
  }

  /**
   * ���������������õ�model�еľ�VO
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-6 ����04:28:59
   */
  private PraybillVO getOldVO() {
    PraybillVO vo = (PraybillVO) this.getAppModel().getSelectedData();
    return vo;
  }

  /**
   * ���������������õ�Panel
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-10 ����10:21:05
   */
  private UIPanel getPanel() {
    UIPanel panel = null;

    // ��Ƭ����
    if (((ShowUpableBillForm) this.billForm).isComponentVisible()) {
      panel = this.billForm.getBillCardPanel();
    }
    else {// �б����
      panel = this.list.getBillListPanel();
    }
    return panel;
  }

  /**
   * ���������������õ���ǰ������ѡ�������VO
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-4 ����01:55:55
   */
  private PraybillVO getSelectedVO() {
    PraybillVO vo = null;

    // ��Ƭ����
    if (((ShowUpableBillForm) this.billForm).isComponentVisible()) {
      vo = this.getCloseVOFromBillForm();
    }
    else {// �б����
      vo = this.getCloseVOFromListView();
    }

    return vo;
  }

  /**
   * ���������������õ�ѡ�����
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author GGR
   * @time 2010-4-10 ����10:23:23
   */
  private void getSelectIndex() {

    // ��Ƭ����
    if (((ShowUpableBillForm) this.billForm).isComponentVisible()) {
      BillCardPanel panel = this.billForm.getBillCardPanel();
      this.selectIndex = panel.getBodyPanel().getTable().getSelectedRows();
    }
    else {// �б����
      BillListPanel panel = this.list.getBillListPanel();
      this.selectIndex = panel.getBodyTable().getSelectedRows();
    }
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    this.getSelectIndex();

    if (ArrayUtils.isEmpty(this.selectIndex)) {
      return false;
    }

    PraybillVO vo = this.getSelectedVO();

    // ������̬�Ĳ����йر�
    if (null != vo
        && !(POEnumBillStatus.APPROVE.toInt() == vo.getHVO().getFbillstatus()
            .intValue())) {
      return false;
    }

    // ������չرգ�����һ���ر�
    if (null == vo
        || EnumBillStatue.CLOSE.toInt() == vo.getHVO().getFbillstatus()
            .intValue()) {
      return false;
    }

    // ���ѡ�������һ��Ϊ�ر�״̬���򷵻�false��������ر�
    for (PraybillItemVO itemVO : vo.getBVO()) {
      if (itemVO != null && UFBoolean.TRUE.equals(itemVO.getBrowclose())) {
        return false;
      }
    }

    return true;
  }
}
