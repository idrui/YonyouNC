package nc.ui.pu.m27.settlebill.action;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m27.ISettleBillMaintain;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.ui.pubapp.uif2app.actions.DeleteAction;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>ɾ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-6-3 ����10:40:36
 */
public class SettleBillDeleteAction extends DeleteAction {
  private static final long serialVersionUID = -7838656865093306531L;

  @Override
  public ISingleBillService<Object> getSingleBillService() {
    ISingleBillService<Object> service = new ISingleBillService<Object>() {
      @Override
      public SettleBillVO operateBill(Object bill) throws Exception {
        // ִ��Զ�̵��ã�����ɾ��
        ISettleBillMaintain manageService =
            NCLocator.getInstance().lookup(ISettleBillMaintain.class);
        manageService.deleteSettleBills(this.getLightVO(new SettleBillVO[] {
          (SettleBillVO) bill
        }));
        return (SettleBillVO) bill;
      }

      private SettleBillVO[] getLightVO(SettleBillVO[] vos) {
        ClientBillToServer<SettleBillVO> tool =
            new ClientBillToServer<SettleBillVO>();
        return tool.constructDelete(vos);
      }
    };
    return service;
  }

}
