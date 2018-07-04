package nc.vo.pu.m20.vochange;

import nc.vo.pf.change.ChangeVOAdjustContext;
import nc.vo.pf.change.IChangeVOAdjust;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.rule.CalculateDateRule;
import nc.vo.pu.m20.rule.SetChangeRateRule;
import nc.vo.pu.m20.rule.SetEmployeeRule;
import nc.vo.pu.m20.rule.SetNnumRule;
import nc.vo.pu.m20.rule.SetOrdertrantypeRule;
import nc.vo.pu.m20.rule.SetOrgCurrRule;
import nc.vo.pu.m20.rule.SetPriceRule;
import nc.vo.pu.m20.rule.SetPurchaseorgRule;
import nc.vo.pu.m20.rule.SetRownoRule;
import nc.vo.pu.m20.rule.SetUnitRule;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

/**
 * �ʲ��������뵽�빺����vo����������
 * 
 * @since 6.5
 * @version 2014-1-20 ����09:02:50
 * @author fanly3
 */
public class ChangeVOAdjust4A08To20 implements IChangeVOAdjust {

  @Override
  public AggregatedValueObject adjustBeforeChange(AggregatedValueObject srcVO,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    // ������������
    return this.batchAdjustBeforeChange(new AggregatedValueObject[] {
      srcVO
    }, adjustContext)[0];
  }

  @Override
  public AggregatedValueObject adjustAfterChange(AggregatedValueObject srcVO,
      AggregatedValueObject destVO, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    // ������������
    return this.batchAdjustAfterChange(new AggregatedValueObject[] {
      srcVO
    }, new AggregatedValueObject[] {
      destVO
    }, adjustContext)[0];
  }

  @Override
  public AggregatedValueObject[] batchAdjustBeforeChange(
      AggregatedValueObject[] srcVOs, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    return srcVOs;
  }

  @Override
  public AggregatedValueObject[] batchAdjustAfterChange(
      AggregatedValueObject[] srcVOs, AggregatedValueObject[] destVOs,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    PraybillVO[] vos = ArrayUtil.convertArrayType(destVOs);
    // ��ȫ��Ϣ
    this.fillInfo(vos);
    return vos;
  }

  /**
   * ��ȫ��Ϣ
   * 
   * @param vos
   */
  private void fillInfo(PraybillVO[] vos) {
    // ��ȫ�к�
    new SetRownoRule().process(vos);
    // ��ȫ���ұ���
    new SetOrgCurrRule().process(vos);
    // ��ȫ�ɹ���֯
    new SetPurchaseorgRule().process(vos);
    // ��ȫ�ɹ�Ա
    new SetEmployeeRule().process(vos);
    // ��ȫ��������
    new SetOrdertrantypeRule().process(vos);
    // ��������λ��VO������û�ж�����λ������λȡ�����ϵ�����λ
    new SetUnitRule().process(vos);
    // ���û����ʺ��Ƿ�̶�������
    new SetChangeRateRule().process(vos);
    // ���������ͻ����ʼ���������
    new SetNnumRule().process(vos);
    // ��ȫ�������ںͽ��鶩������
    new CalculateDateRule().process(vos);
    // ��ȫ��˰���ۺͼ�˰�ϼ�
    new SetPriceRule().process(vos);
  }
}
