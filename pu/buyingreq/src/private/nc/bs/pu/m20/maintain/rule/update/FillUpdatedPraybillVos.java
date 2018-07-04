/**
 * $�ļ�˵��$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-2 ����03:12:31
 */
package nc.bs.pu.m20.maintain.rule.update;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.util.SetUpdateAuditInfoRule;

/**
 * @description
 *              �빺���޸ı���ʱ���������Ϣ������
 * @scene
 *        �빺���޸�
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����10:42:30
 * @author yanxm5
 */
public class FillUpdatedPraybillVos implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] vos) {

    // ��������ʱ�������Ϣ
    new SetUpdateAuditInfoRule<PraybillVO>().process(vos);

    for (PraybillVO vo : vos) {
      PraybillHeaderVO head = vo.getHVO();
      // ��ȫ����״̬
      if (null == head.getFbillstatus()) {
        head.setFbillstatus(POEnumBillStatus.FREE.toInteger());
      }
      PraybillItemVO[] items = vo.getBVO();
      for (PraybillItemVO item : items) {
        if (null == item.getPk_group()) {
          item.setPk_group(head.getPk_group());
        }
        if (null == item.getPk_org()) {
          item.setPk_org(head.getPk_org());
        }
        if (null == item.getPk_org_v()) {
          item.setPk_org_v(head.getPk_org_v());
        }
        if (null == item.getDbilldate()) {
          item.setDbilldate(head.getDbilldate());
        }
      }
    }
  }
}
