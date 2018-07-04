package nc.pubimpl.pu.m25.arap.f1;

import java.util.Map;

import nc.bs.businessevent.BdUpdateEvent;
import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.bs.businessevent.IEventType;
import nc.bs.ml.NCLangResOnserver;
import nc.vo.arap.payable.AggPayableBillVO;
import nc.vo.arap.payable.PayableBillItemVO;
import nc.vo.arap.pub.BillEnumCollection.FromSystem;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * ��Ӧ��Ӧ�����޸�ʱ�ɹ���������
 * 
 * @since 6.0
 * @version 2011-8-8 ����04:07:45
 * @author �����
 */

public class PayablebillUpdateListener implements IBusinessListener {

  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
    try {
      if (IEventType.TYPE_UPDATE_AFTER.equals(event.getEventType())) {
        BdUpdateEvent e = (BdUpdateEvent) event;
        AggregatedValueObject[] oldVos =
            (AggregatedValueObject[]) e.getOldObject();
        AggregatedValueObject[] newVos =
            (AggregatedValueObject[]) e.getNewObject();
        if (ArrayUtils.isEmpty(oldVos) || ArrayUtils.isEmpty(newVos)) {
          return;
        }
        AggPayableBillVO newVo = (AggPayableBillVO) newVos[0];
        AggPayableBillVO oldVo = (AggPayableBillVO) oldVos[0];
        // ������Դ�ڲɹ���������
        if (!FromSystem.PO.VALUE.equals(newVo.getHeadVO().getSrc_syscode())) {
          return;
        }
        // �Ƿ����л���ɾ��
        this.checkAddOrDelRows(oldVo, newVo);
        // �ֶα༭����
        this.checkUpdateFields(oldVo, newVo);

      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  /**
   * @param oldVo
   * @param newVo
   */
  private void checkAddOrDelRows(AggPayableBillVO oldVo, AggPayableBillVO newVo) {
    // Ӧ�������Ƶ�vo״̬���������޷��ж��Ƿ����л���ɾ�У������·����ж�
    // 1.�������ݲ�һ��
    if (oldVo.getBodyVOs().length != newVo.getBodyVOs().length) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004050_0", "04004050-0037")/*
                                                                   * @res
                                                                   * "��Դ�ɹ�ϵͳ��Ӧ���������������л���ɾ�У�"
                                                                   */);
    }
    Map<String, PayableBillItemVO> oldPayItemVOMap =
        AggVOUtil.createItemMap(new AggPayableBillVO[] {
          oldVo
        });
    // 2.��vo����vo����������һ��
    for (PayableBillItemVO item : newVo.getBodyVOs()) {
      if (oldPayItemVOMap.get(item.getPk_payableitem()) == null) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004050_0", "04004050-0037")/*
                                                                     * @res
                                                                     * "��Դ�ɹ�ϵͳ��Ӧ���������������л���ɾ�У�"
                                                                     */);
      }
    }
  }

  private void checkUpdateFields(AggPayableBillVO oldVo, AggPayableBillVO newVo) {
    Map<String, PayableBillItemVO> oldPayItemVOMap =
        AggVOUtil.createItemMap(new AggPayableBillVO[] {
          oldVo
        });

    for (PayableBillItemVO item : newVo.getBodyVOs()) {
      for (int i = 0; i < this.getCheckFields().length; i++) {
        Object newValue = item.getAttributeValue(this.getCheckFields()[i][0]);
        Object oldValue =
            oldPayItemVOMap.get(item.getPk_payableitem()).getAttributeValue(
                this.getCheckFields()[i][0]);
        if (newValue == null && oldValue == null) {
          continue;
        }
        if (newValue != null && !newValue.equals(oldValue) || oldValue != null
            && !oldValue.equals(newValue)) {
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
              .getStrByID("4004050_0", "04004050-0107", null, new String[] {
                this.getCheckFields()[i][1]
              })/* ��Դ�ɹ�ϵͳ��Ӧ�������������޸ġ�{0}�� */);
        }
      }
    }
  }

  private String[][] getCheckFields() {

    String[][] checkFields =
        new String[][] {
          {
            PayableBillItemVO.PK_CURRTYPE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0001755")
          /* @res "����" */
          },// ����
          {
            PayableBillItemVO.RATE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0002837")
          /* @res "����" */
          },// ����
          {
            PayableBillItemVO.PRICE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0000741")
          /* @res "����" */
          },// ����
          {
            PayableBillItemVO.TAXPRICE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0001160")
          /* @res "��˰����" */
          },// ��˰����
          {
            PayableBillItemVO.SUPPLIER,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0000275")
          /* @res "��Ӧ��" */
          },// ��Ӧ��
          {
            PayableBillItemVO.MATERIAL,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
                "04004050-0038")
          /* @res "����" */
          },// ����
          {
            PayableBillItemVO.TAXRATE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0003078")
          /* @res "˰��" */
          },// ˰��
          {
            PayableBillItemVO.QUANTITY_CR,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0003856")
          /* @res "��������" */
          },// ��������
          {
            PayableBillItemVO.LOCAL_TAX_CR,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
                "04004050-0039")
          /* @res "��֯����˰��(����)" */
          },// ��֯����˰��(����)
          {
            PayableBillItemVO.NOTAX_CR,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
                "04004050-0040")
          /* @res "����ԭ����˰���" */
          },// ����ԭ����˰���
          // {
          // PayableBillItemVO.TAX_CR,
          // nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
          // "04004050-0041")
          // /* @res "����ԭ��˰��" */
          // },// ����ԭ��˰��
          {
            PayableBillItemVO.LOCAL_MONEY_CR,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
                "04004050-0042")
          /* @res "��֯���ҽ��(����)" */
          }
          // ��֯���ҽ��(����)
          ,
          {
            "taxcodeid",
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_2",
                "2400403002-0187")
          /*
           * @res
           * "˰��"
           */
          }
        };

    return checkFields;
  }
}
