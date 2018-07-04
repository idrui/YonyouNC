package nc.ui.pu.m23.action.maintain;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.pubapp.pub.smart.IBillQueryService;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.uif2app.actions.RefreshSingleAction;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.utils.BatchSynchronizerM23;
import nc.vo.pu.m23.utils.FillBillInfoFor23;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

@SuppressWarnings("restriction")
public class ArriveRefreshCardAction extends RefreshSingleAction {

  private static final long serialVersionUID = 2010090214460001L;

  /**
   * RefreshCardAction �Ĺ�����
   */
  public ArriveRefreshCardAction() {
    super();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    Object obj = this.model.getSelectedData();
    if (obj != null) {
      ArriveVO oldVO = (ArriveVO) obj;
      String pk = oldVO.getParentVO().getPrimaryKey();
      IBillQueryService billQuery =
          NCLocator.getInstance().lookup(IBillQueryService.class);
      AggregatedValueObject newVO =
          billQuery.querySingleBillByPk(oldVO.getClass(), pk);
      // ���ݱ�ɾ��֮��Ӧ�ûص��б������ˢ��
      if (newVO == null) {
        // �����Ѿ���ɾ��
        throw new BusinessException(NCLangRes.getInstance().getStrByID("uif2",
            "RefreshSingleAction-000000")/* �����Ѿ���ɾ�����뷵���б���棡 */);
      }
      ArriveVO[] newbills = new ArriveVO[] {
        (ArriveVO) newVO
      };
      // ͬ��������Ϣ
      BatchSynchronizerM23.synchBatchCodeData(newbills);
      // ��������������
      FillBillInfoFor23.fillBillInfo(newbills);
      this.model.directlyUpdate(newVO);

    }

    this.showQueryInfo();
  }

}
