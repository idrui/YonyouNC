package nc.pubitf.pu.m23.msg;

import nc.buzimsg.vo.BuziMsgSendingContext;
import nc.pubitf.bdlayer.msg.BuziMsgMetaDataSource;
import nc.pubitf.bdlayer.msg.BuziMsgSender;
import nc.pubitf.bdlayer.msg.OrgMsgVarCalculater;
import nc.pubitf.bdlayer.msg.ScmSendBuziMsgPara;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

public class PuBuziMsgSender extends BuziMsgSender {

  @Override
  protected BuziMsgSendingContext getContext(AbstractBill vo, String[] orgs,
      ScmSendBuziMsgPara param) {
    BuziMsgSendingContext context = super.getContext(vo, orgs, param);

    // ������Ϣ����������Դ��
    context.setDatasource(new BuziMsgMetaDataSource(new AbstractBill[] {
        vo
      }));
    // ������֯�����Ľ�������
    context.setCalculater(new OrgMsgVarCalculater(orgs[0]));
    return context;
  }

}
