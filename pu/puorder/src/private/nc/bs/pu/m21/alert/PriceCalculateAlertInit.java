package nc.bs.pu.m21.alert;

import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.res.NCModule;
import nc.vo.sm.createcorp.CreateOrgInfo;
import nc.vo.sm.createcorp.CreatecorpVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * �ɹ�Ԥ�����ݼ�����(����ҵ��ʼ����ʱ���������ѯ�ۼ���Ԥ����Ŀ����Ϣ�������ݿ�)
 * 
 * @since 6.1
 * @version 2011-11-29 ����11:59:19
 * @author yangtian
 */
public class PriceCalculateAlertInit implements IBusinessListener {

  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
    BusinessEvent busi = (BusinessEvent) event;
    // �ж��Ƿ�Ϊ���ų�ʼ���¼�
    if (!busi.getSourceID().equals("createorg")) {
      return;
    }
    // ���ų�ʼ���¼���userobjectΪCreateOrgInfo�����а����ͽ�����ص���Ϣ
    CreateOrgInfo info = (CreateOrgInfo) busi.getObject();
    // ���ż����ݣ������Ƿ�������ֻҪqc���ڵ㱻��ʼ����ִ��Ԥ������
    // if (info.isAppend()) {
    // dosomething();
    // }
    // ��ñ��γ�ʼ����ģ��
    CreatecorpVO[] ccvos = info.getCreatecorpVOs();
    if (ArrayUtils.isEmpty(ccvos)) {
      return;
    }
    for (CreatecorpVO vo : ccvos) {
      if (NCModule.PO.getCode().equals(vo.getFunccode())) {

        PriceCalculateTaskReg priceCalculateTaskReg =
            new PriceCalculateTaskReg(vo.getPk_org());

        priceCalculateTaskReg.regMonthlyAlert();
        priceCalculateTaskReg.regDailyAlert();
      }
    }

  }

}
