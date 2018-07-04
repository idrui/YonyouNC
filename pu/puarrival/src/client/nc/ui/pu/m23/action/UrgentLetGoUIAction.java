package nc.ui.pu.m23.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.pu.m23.pubquery.IArrivePubQuery;
import nc.ui.ml.NCLangRes;
import nc.ui.pu.m23.view.ArriveCardForm;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.utils.ArrivePublicUtil;
import nc.vo.pu.m23.utils.BatchSynchronizerM23;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.qc.pub.util.QCSysParamUtil;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.sm.funcreg.FuncRegisterVO;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������ �������� ��ť������Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-12 ����02:15:12
 */
public class UrgentLetGoUIAction extends NCAction {

  private static final long serialVersionUID = 6457241642041653036L;

  private ArriveCardForm form;

  private ShowUpableBillListView list;

  private BillManageModel model;

  public UrgentLetGoUIAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_URGENCYPASS);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    ArriveVO[] vos = new ArriveVO[1];
    vos[0] = this.getSelectedRowData();
    if (vos[0] == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0045")/*
                                                                   * @res
                                                                   * "��ѡ������У�"
                                                                   */);
      return;
    }
    String pk_org = vos[0].getHVO().getPk_org();
    if (!SysInitGroupQuery.isQCEnabled()
        || UFBoolean.FALSE.equals(ValueUtils.getUFBoolean(QCSysParamUtil
            .getINI01(pk_org)))) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0044")/*
                                                                   * @res
                                                                   * "�ʼ�ģ��δ����,����!"
                                                                   */);
    }

    // ����Ƿ���Խ��н�������
    this.checkCondition(vos);

    ArriveVO[] newvos = this.filterItemByStorecheck(vos);

    // �򿪽������е��ڵ�
    FuncletInitData initData = new FuncletInitData();
    initData.setInitType(Integer.valueOf(POBillType.Arrive.getCode())
        .intValue());
    initData.setInitData(newvos);
    WorkbenchEnvironment instance = WorkbenchEnvironment.getInstance();
    FuncRegisterVO funvo = instance.getFuncRegisterVO("C08005");// �ڵ����
    FuncletWindowLauncher.openFuncNodeDialog(this.form, funvo, initData, null,
        true, true);

    this.refreshModel(newvos);
  }

  public ArriveCardForm getForm() {
    return this.form;
  }

  public ShowUpableBillListView getList() {
    return this.list;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public int[] getSelectedRows() {
    if (this.getForm().isShowing()) {
      return this.getForm().getBillCardPanel().getBillTable().getSelectedRows();
    }
    return this.getList().getBillListPanel().getBodyTable().getSelectedRows();
  }

  public void setForm(ArriveCardForm form) {
    this.form = form;
  }

  public void setList(ShowUpableBillListView list) {
    this.list = list;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  private void checkCondition(ArriveVO[] vos) {
    if (vos == null || vos.length == 0) {
      return;
    }
    for (ArriveVO vo : vos) {
      String code = vo.getHVO().getVbillcode();
      boolean isback = ValueUtils.getBoolean(vo.getHVO().getBisback());
      if (isback) {
        // �˻������������ɽ������е�
        String msg =
            code
                + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                    "4004040_0", "04004040-0046")/* @res "�˻��������Խ��н������У�" */;
        ExceptionUtils.wrappBusinessException(msg);
      }
      Object approvecode = POEnumBillStatus.APPROVE.value();
      if (!approvecode.equals(vo.getHVO().getFbillstatus())) {
        // ������ͨ���ĵ��������Խ��н������е�!
        String msg =
            code
                + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                    "4004040_0", "04004040-0047")/* @res "������ͨ���ĵ����������Խ��н������е�!" */;
        ExceptionUtils.wrappBusinessException(msg);
      }

      for (ArriveItemVO item : vo.getBVO()) {
        String rowno = item.getCrowno();
        UFDouble naccumletgonum = MathTool.nvl(item.getNaccumletgonum());
        if (naccumletgonum.doubleValue() > 0
            || StringUtils.isNotEmpty(item.getPk_passbill())) {
          // ��������ֻ֧��һ�ν�������
          String msg =
              code
                  + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                      "4004040_0", "04004040-0009")/* @res "��������" */
                  + rowno
                  + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                      "4004040_0", "04004040-0048")/* @res "ֻ֧��һ�ν�������!" */;
          ExceptionUtils.wrappBusinessException(msg);
        }
        // �ϸ�����
        UFDouble nelignum = item.getNelignum();
        // ���ϸ�����
        UFDouble nnotelignum = item.getNnotelignum();
        if (MathTool.add(nelignum, nnotelignum).compareTo(UFDouble.ZERO_DBL) > 0) {
          // ����������Ѿ������ʼ쵥,���ܽ��н�������
          String msg =
              NCLangRes.getInstance().getStrByID("4004040_0", "04004040-0168",
                  null, new String[] {
                    code, rowno
                  })/* {0}��������{1}���ʼ����Ѿ����أ��޷����н�������! */;
          ExceptionUtils.wrappBusinessException(msg);
        }

        UFDouble narrnum = MathTool.nvl(item.getNnum());
        UFDouble naccumstorenum = MathTool.nvl(item.getNaccumstorenum());
        if (MathTool.compareTo(narrnum, naccumstorenum) == 0) {
          // ���ڿ��Խ��н������е�����(����������-�ۼ��������>0)
          String msg =
              code
                  + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                      "4004040_0", "04004040-0009")/* @res "��������" */
                  + rowno
                  + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                      "4004040_0", "04004040-0049")/* @res "�Ѿ�ȫ����⣬�������ٽ�������!" */;
          ExceptionUtils.wrappBusinessException(msg);
        }
      }
    }

  }

  private ArriveVO[] filterItemByStorecheck(ArriveVO[] vos) {
    if (vos == null || vos.length == 0) {
      return null;
    }
    // ������ѯ�������������������Ӧ�Ŀ����֯ҳǩ�����ԣ����ݼ�������⡢�Ƿ���죩
    ArriveItemVO[] items = AggVOUtil.getCombinItemVOs(vos);
    Map<String, MaterialStockVO> bidMrlMap = null;
    bidMrlMap = ArrivePublicUtil.queryMaterialStockInfo(items);
    List<ArriveVO> canLetgoVos = new ArrayList<ArriveVO>();
    for (ArriveVO vo : vos) {
      List<ArriveItemVO> canLetgoItems = new ArrayList<ArriveItemVO>();
      for (ArriveItemVO item : vo.getBVO()) {
        // ���ϵ����ԡ��Ƿ���ݼ��������=�ǡ������ϲſ������ɽ������е���
        boolean bstockbycheck = false;
        // �Ƿ����
        boolean chkfreeflag = false;
        MaterialStockVO mrlvo = bidMrlMap.get(item.getPk_arriveorder_b());
        if (mrlvo != null) {
          bstockbycheck = ValueUtils.getBoolean(mrlvo.getStockbycheck());
          chkfreeflag = ValueUtils.getBoolean(mrlvo.getChkfreeflag());
        }

        UFDouble nnum = MathTool.sub(item.getNnum(), item.getNaccumbacknum());
        // ����������Ⲣ�ҷ���죬��û����ȫ���ڵ������˻��ģ����Խ�������
        if (bstockbycheck && !chkfreeflag
            && MathTool.greaterThan(nnum, UFDouble.ZERO_DBL)) {
          canLetgoItems.add(item);

          // nnum�����item.getNnum()���ȣ�˵�����˻��������������Ҫ�������úͷ�������
          if (!MathTool.equals(nnum, item.getNnum())) {
            // ������ = ������ - �ۼ��˻�������
            item.setNnum(nnum);

            // ��������
            item.setNastnum(HslParseUtil.hslDivUFDouble(item.getVchangerate(),
                item.getNnum()));
          }
        }
      }
      if (canLetgoItems.size() == 0) {
        // ������ͨ���ĵ��������Խ��н������е�!
        String msg =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
                "04004040-0050")/*
                                 * @res
                                 * "���岻���ڸ��ݼ��������������У�����ȫ�����ڵ������˻����޷����н�������!"
                                 */;
        ExceptionUtils.wrappBusinessException(msg);
      }
      ArriveVO newvo = new ArriveVO();
      newvo.setHVO(vo.getHVO());
      newvo.setBVO(canLetgoItems.toArray(new ArriveItemVO[0]));
      canLetgoVos.add(newvo);
    }
    return canLetgoVos.toArray(new ArriveVO[0]);
  }

  /**
   * ��ȡѡ�н������е�������
   * 
   * @return
   */
  private ArriveVO getSelectedRowData() {
    Object oldData = this.getModel().getSelectedData();

    if (oldData == null) {
      return null;
    }
    int[] selectedRows = this.getSelectedRows();
    if (selectedRows.length == 0) {
      return null;
    }
    ArriveVO newVO = new ArriveVO();
    newVO.setHVO(((ArriveVO) oldData).getHVO());
    ArriveItemVO[] orgItems = ((ArriveVO) oldData).getBVO();
    ArriveItemVO[] items = new ArriveItemVO[selectedRows.length];
    for (int i = 0; i < items.length; i++) {
      items[i] = orgItems[selectedRows[i]];
    }
    newVO.setBVO(items);
    // data.setChildrenVO(items);
    // return data;
    return newVO;
  }

  private boolean isOneVOEnable(ArriveVO vo) {
    if (vo == null) {
      return false;
    }
    if (!POEnumBillStatus.APPROVE.value().equals(vo.getHVO().getFbillstatus())) {
      return false;
    }
    ArriveItemVO[] bvos = vo.getBVO();
    if (ArrayUtils.isEmpty(bvos)) {
      return false;
    }
    return true;
  }

  // ˢ��ģ�����ݣ���Ҫ��Ϊ�������л�д�������������󣬽���û��ˢ�£����½�������ֻ��һ�ε�У��ʧЧ��
  private void refreshModel(ArriveVO[] vos) throws BusinessException {
    List<String> arriveHIDs = new ArrayList<String>();
    String hid = "";
    for (ArriveVO vo : vos) {
      hid = vo.getHVO().getPk_arriveorder();
      arriveHIDs.add(hid);
    }

    IArrivePubQuery arrivePubQuery =
        NCLocator.getInstance().lookup(IArrivePubQuery.class);
    ArriveVO[] arriveVos =
        arrivePubQuery.queryAggVOByHids(arriveHIDs
            .toArray(new String[arriveHIDs.size()]));

    // ͬ��������Ϣ
    BatchSynchronizerM23.synchBatchCodeData(arriveVos);
    this.getModel().directlyUpdate(arriveVos);
  }

  @Override
  protected boolean isActionEnable() {
    BillManageModel model = (BillManageModel) this.form.getModel();
    if (model.getUiState() == UIState.EDIT || model.getUiState() == UIState.ADD
        || model.getSelectedData() == null) {
      return false;
    }

    Object[] objs = model.getSelectedOperaDatas();

    if (this.model.getSelectedData() != null && ArrayUtils.isEmpty(objs)) {
      return this.isOneVOEnable((ArriveVO) this.model.getSelectedData());
    }

    if (objs.length > 1) {
      return false;
    }
    return this.isOneVOEnable((ArriveVO) objs[0]);
  }
}
