package nc.ui.pu.pub.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.pubapp.pub.task.IMultiReturnObjProcessor;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.ui.pubapp.pub.task.MultiBillTaskRunner;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.components.progress.TPAProgressUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;
import nc.vo.pubapp.pattern.pub.Constructor;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ŵ��ݽ����������Ķ���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-6-21 ����02:07:52
 */
public abstract class MultiBillAction extends NCAction implements
    IMultiReturnObjProcessor {
  private static final long serialVersionUID = 4168679103149866741L;

  private BillManageModel model;

  private MultiBillTaskRunner multiBillTaskRunner;

  private TPAProgressUtil tpaProgressUtil;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    Object[] oldVos = this.getModel().getSelectedOperaDatas();

    if (oldVos != null && this.getSingleBillService() != null) {
      oldVos = this.getRealArray(oldVos);
      Object[] vos = this.produceLightVO((IBill[]) oldVos);
      if (vos.length > 1) {
        if (this.getTpaProgressUtil() == null) {
          TPAProgressUtil tpaProgressUtil = new TPAProgressUtil();
          tpaProgressUtil.setContext(this.getModel().getContext());
          this.setTpaProgressUtil(tpaProgressUtil);
        }

        this.getMultiBillTaskRunner().setOperateObjs(vos);
        this.getMultiBillTaskRunner().setMultiReturnObjProcessor(this);
        this.getMultiBillTaskRunner().setTpaProgressUtil(
            this.getTpaProgressUtil());
        // this.getMultiBillTaskRunner().setParent(
        // WorkbenchEnvironment.getInstance().getWorkbench());
        this.getMultiBillTaskRunner().runTask();
        // TaskRunClient.getInstance(
        // WorkbenchEnvironment.getInstance().getWorkbench(), this.getTitle(),
        // this.getMultiBillTaskRunner(), this).startTask();
        // IBill[] returnVos =
        // (IBill[]) this.getMultiBillTaskRunner().getReturnSuccessfulObjs();
        // this.processReturnObjs(returnVos, (IBill[]) oldVos);

      }
      else if (vos.length == 1) {
        IBill returnVo =
            (IBill) this.getSingleBillService().operateBill(vos[0]);
        this.processReturnObjs(new IBill[] {
          returnVo
        }, (IBill[]) oldVos);
        this.showSuccessInfo();
      }
    }
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public TPAProgressUtil getTpaProgressUtil() {
    return this.tpaProgressUtil;
  }

  @Override
  public void processReturnObjs(Object[] returnObj) {
    this.processReturnObjs((IBill[]) returnObj,
        (IBill[]) this.getRealArray(this.getModel().getSelectedOperaDatas()));
    if (this.getMultiBillTaskRunner().isTaskSuccessful()) {
      this.showSuccessInfo();
    }
    else {
      this.showFailedInfo();
    }
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  public void setTpaProgressUtil(TPAProgressUtil tpaProgressUtil) {
    this.tpaProgressUtil = tpaProgressUtil;
  }

  private Map<String, IBill> createBillMap(IBill[] bills) {
    Map<String, IBill> vmaps = new HashMap<String, IBill>();
    for (IBill bill : bills) {
      vmaps.put(bill.getPrimaryKey(), bill);
    }
    return vmaps;
  }

  private MultiBillTaskRunner getMultiBillTaskRunner() {
    if (this.multiBillTaskRunner == null) {
      this.multiBillTaskRunner =
          new MultiBillTaskRunner(this.getSingleBillService());
    }
    return this.multiBillTaskRunner;
  }

  private Object[] getRealArray(Object[] vos) {
    if (null == vos || 0 == vos.length) {
      return vos;
    }
    Object[] nvos = Constructor.declareArray(vos[0].getClass(), vos.length);
    System.arraycopy(vos, 0, nvos, 0, vos.length);
    return nvos;
  }

  private void processReturnObjs(IBill[] vos, IBill[] oldVos) {
    try {
      Map<String, IBill> vmaps = this.createBillMap(vos);
      List<IBill> ovos = new ArrayList<IBill>();
      for (IBill oldVo : oldVos) {
        if (vmaps.containsKey(oldVo.getPrimaryKey())) {
          ovos.add(oldVo);
        }
      }
      IBill[] useoldVos = ovos.toArray(new IBill[ovos.size()]);
      new ClientBillCombinServer<IBill>().combine(useoldVos, vos);
      this.getModel().directlyUpdate(useoldVos);
    }
    catch (Exception e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
  }

  private IBill[] produceLightVO(IBill[] vos) {
    ClientBillToServer<IBill> tool = new ClientBillToServer<IBill>();
    IBill[] lightVOs = tool.construct(vos, vos);
    return lightVOs;
  }

  private void showFailedInfo() {
    ShowStatusBarMsgUtil.showStatusBarMsg(this.getMultiBillTaskRunner()
        .getFailedMessage(), this.getModel().getContext());
  }

  private void showSuccessInfo() {
    ShowStatusBarMsgUtil.showStatusBarMsg(this.getSuccessInfo(), this
        .getModel().getContext());
  }

  /**
   * �����������������ִ����������Ҫ���õķ������
   * <p>
   * <b>����˵��</b>
   * 
   * @return ִ����������Ҫ���õķ���
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-6-21 ����02:15:33
   */
  protected abstract ISingleBillService<Object> getSingleBillService();

  /**
   * �����������������ִ�гɹ������ʾ��Ϣ
   * <p>
   * <b>����˵��</b>
   * 
   * @return ִ�гɹ������ʾ��Ϣ
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-6-21 ����02:15:06
   */
  protected abstract String getSuccessInfo();

  /**
   * ����������������ý��ȶԻ���ı���
   * <p>
   * <b>����˵��</b>
   * 
   * @return ���ȶԻ���ı���
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-6-21 ����02:14:36
   */
  protected abstract String getTitle();
}
