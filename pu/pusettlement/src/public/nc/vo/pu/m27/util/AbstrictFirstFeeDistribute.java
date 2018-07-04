package nc.vo.pu.m27.util;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.costfactor.IFactorOrdByOrgMaterial;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���õ�һ�η�̯�Ĺ����ࣨǰ̨UI����̨����̳д��ࣩ
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-9-2 ����11:34:24
 */
public abstract class AbstrictFirstFeeDistribute {

  public void distribute() {

    // ��̯������
    this.distributeFee();

    // ��̯�ۿ���
    this.distributeDiscount();
  }

  abstract public void distributeDiscount();

  abstract public void distributeFee();

  /**
   * �����������������ݳɱ�Ҫ�ص����������Ҫ��������(�������ö�Ӧ���㵥�е�Ҫ��ֵ)
   * <p>
   * <b>����˵��</b>
   *
   * @param pk_factor
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-9-2 ����11:52:12
   */
  public int getFactorIndex(CostfactorViewVO factorvo) {
    // ��Ӧ��Ҫ��ֵITEMKEY
    if (factorvo == null || factorvo.getIfactororder() == null) {
      String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0169")/*@res "�ɱ�Ҫ�صĳɱ�Ҫ��˳���ֶ�Ϊ�գ�����ɱ�Ҫ�ض��壡"*/;
      ExceptionUtils.wrappBusinessException(msg);
      return -1;
    }

    int ishoworder = factorvo.getIfactororder().intValue();// Ҫ���м�¼��˳���
    // Ҫ������ֵ������1--8֮�������ֵ
    if (ishoworder < 1 || ishoworder > CostfactorVO.MAX_NUM) {
      String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0170")/*@res "�ɱ�Ҫ�صĳɱ�Ҫ��˳���ֶα�����1--8֮�䣬��ǰҪ��˳��ֵΪ��"*/ + ishoworder;
      ExceptionUtils.wrappBusinessException(msg);
    }
    return ishoworder;
  }

  /**
   * ����������������ѯ�ɱ�Ҫ����ͼVO
   * <p>
   * <b>����˵��</b>
   *
   * @param pk_fiorg
   * @param pk_mrls
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-9-16 ����03:18:49
   */
  public CostfactorViewVO[] queryCostFactor(String pk_fiorg, String[] pk_mrls) {
    CostfactorViewVO[] totalFactors = null;
    try {
      IFactorOrdByOrgMaterial cfSrv =
          NCLocator.getInstance().lookup(IFactorOrdByOrgMaterial.class);
      totalFactors = cfSrv.queryFactorViewVOByOrgMaterial(pk_fiorg, pk_mrls);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return totalFactors;
  }
}