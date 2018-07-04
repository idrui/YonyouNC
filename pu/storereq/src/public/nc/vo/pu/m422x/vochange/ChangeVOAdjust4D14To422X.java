package nc.vo.pu.m422x.vochange;

import nc.vo.pf.change.ChangeVOAdjustContext;
import nc.vo.pf.change.IChangeVOAdjust;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.m422x.rule.CurrencySetter;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

import org.apache.commons.lang.ArrayUtils;

/**
 * ���ʼ��������󵥵������������뵥VO����ǰ������
 * 
 * @since 6.3.1
 * @version 2013-8-8 ����07:12:46
 * @author fanly3
 */
public class ChangeVOAdjust4D14To422X implements IChangeVOAdjust {

  @Override
  public AggregatedValueObject adjustAfterChange(AggregatedValueObject srcVO,
      AggregatedValueObject destVO, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    if (destVO != null) {
      // ������������
      return this.batchAdjustAfterChange(new AggregatedValueObject[] {
        srcVO
      }, new AggregatedValueObject[] {
        destVO
      }, adjustContext)[0];
    }
    return null;
  }

  @Override
  public AggregatedValueObject adjustBeforeChange(AggregatedValueObject srcVO,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    if (srcVO != null) {
      // ������������
      return this.batchAdjustBeforeChange(new AggregatedValueObject[] {
        srcVO
      }, adjustContext)[0];
    }
    return null;
  }

  @Override
  public AggregatedValueObject[] batchAdjustAfterChange(
      AggregatedValueObject[] srcVOs, AggregatedValueObject[] destVOs,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    StoreReqAppVO[] vos = ArrayUtil.convertArrayType(destVOs);
    // ��ȫ�����������뵥��Ϣ
    this.fillInformation(vos);
    return vos;
  }

  @Override
  public AggregatedValueObject[] batchAdjustBeforeChange(
      AggregatedValueObject[] srcVOs, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    return srcVOs;
  }

  /**
   * ��ȫvo�����Ϣ
   * 
   * @param vos �����������뵥�ۺ�VO
   */
  private void fillInformation(StoreReqAppVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    for (StoreReqAppVO vo : vos) {
      StoreReqAppItemVO[] itemVOs = vo.getBVO();
      for (StoreReqAppItemVO item : itemVOs) {
        item.setCsourcetypecode("1001ZP1000000005GEZN");
      }

      int[] rows = new int[itemVOs.length];
      for (int i = 0; i < itemVOs.length; ++i) {
        rows[i] = i;
      }
      BillHelper<StoreReqAppVO> helper = new BillHelper<StoreReqAppVO>(vo);
      // ��������ȡ�ɹ�Ĭ�ϵ�λ��ֱ��VO���չ����������ͻ�����Ҳ��
      // new AssistUnit(helper).setAssistUnit(rows);
      // new UnitAndChangeRate(helper).setChangeRate(rows);
      // new NumValueSetter(helper).setNastnum(rows);
      // ���ñ���
      new CurrencySetter(helper).setCurrency(rows);
    }
  }
}
