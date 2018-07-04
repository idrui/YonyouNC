/**
 * $�ļ�˵��$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-27 ����09:54:26
 */
package nc.vo.pu.m20.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.mmpps.mps0202.PoTypeEnum;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.enumeration.EnumPraySource;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.scmpub.res.billtype.MMBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.res.billtype.TOBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              �빺������Ĭ��ֵ
 * @scene
 *        �ƻ�������ʽ�����빺��������������ʽ�����빺��
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����1:50:41
 * @author yanxm5
 */
public class SetDefaultValueRule implements IRule<PraybillVO> {
  private String workOrder = "1001Z900000000002215";

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(PraybillVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    this.setDefaultValue(vos);
  }

  private void setDefaultValue(PraybillVO[] vos) {
    UFDate prayDate = AppContext.getInstance().getBusiDate();

    for (PraybillVO vo : vos) {
      if (null != vo.getBVO() && vo.getBVO().length > 0) {
        // ������Դ�������������빺��Դ
        String sourceType = vo.getBVO()[0].getCsourcetypecode();
        // vo����
        if (this.workOrder.equals(sourceType)) {
          vo.getHVO().setFpraysource(EnumPraySource.M4B36.toInteger());
        }
        // �ɹ�����
        else if (SOBillType.Order.getCode().equals(sourceType)) {
          vo.getHVO().setFpraysource(EnumPraySource.SO.toInteger());
        }
        // ��������
        else if (TOBillType.TransOrder.getCode().equals(sourceType)) {
          vo.getHVO().setFpraysource(EnumPraySource.M5X.toInteger());
        }
        // ��������
        else if (MMBillType.ProduceOrder.getCode().equals(sourceType)) {
          vo.getHVO().setFpraysource(EnumPraySource.MO.toInteger());
        }
      }
      // ���ݼƻ����������빺��Դ
      if (null != vo.getHVO().getFpotype()) {
        // MPS�������ƻ�
        if (PoTypeEnum.MPS.equals(vo.getHVO().getFpotype())) {
          vo.getHVO().setFpraysource(EnumPraySource.MPS.toInteger());
        }
        // INVP���ƻ�
        else if (PoTypeEnum.INVP.equals(vo.getHVO().getFpotype())) {
          vo.getHVO().setFpraysource(EnumPraySource.ICPO.toInteger());
        }
        // MRP��������ƻ�
        else if (PoTypeEnum.MRP.equals(vo.getHVO().getFpotype())) {
          vo.getHVO().setFpraysource(EnumPraySource.MPO.toInteger());
        }
      }

      // �빺����
      if (null == vo.getHVO().getDbilldate()) {
        vo.getHVO().setDbilldate(prayDate);
      }

      // ����״̬
      if (null == vo.getHVO().getFbillstatus()) {
        vo.getHVO().setFbillstatus(POEnumBillStatus.FREE.toInteger());
      }

      // �汾��
      if (null == vo.getHVO().getNversion()) {
        vo.getHVO().setNversion(Integer.valueOf(1));
      }

      // ���°汾
      vo.getHVO().setBislatest(UFBoolean.TRUE);

      PraybillItemVO[] items = vo.getBVO();
      if (ArrayUtils.isEmpty(items)) {
        continue;
      }

      for (PraybillItemVO item : items) {
        // �ɹ���֯�ɱ༭
        if (null == item.getBcanpurchaseorgedit()) {
          item.setBcanpurchaseorgedit(UFBoolean.TRUE);
        }
        // ��������������
        if (null == item.getBpublishtoec()) {
          item.setBpublishtoec(UFBoolean.FALSE);
        }
        // �йر�
        if (null == item.getBrowclose()) {
          item.setBrowclose(UFBoolean.FALSE);
        }
        // if (null == item.getDbilldate()) {
        // item.setDbilldate(prayDate);
        // }
      }
    }
  }
}
