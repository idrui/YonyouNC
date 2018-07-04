package nc.pubitf.pu.m20.invp;

import nc.pubitf.invp.plan.ISupplyResultForInvp;
import nc.vo.pub.BusinessException;

/**
 * �빺����Ϊ���ƻ��Ĺ������ṩ�Ĳ�ѯ����
 * 
 * @since 6.0
 * @version 2010-12-14 ����06:18:00
 * @author duy
 */
public interface IBuyingreqQueryForInvp {

  /**
   * �����빺���Ĳ�ѯSQL��װ����
   * 
   * @param pk_org �����֯OID
   * @param tempTable
   *          ��ʱ��ı��������������ֶΣ�pk_material-�����ظ�������VID��dstart-��ʼʱ�䣬dend-����ʱ�䣩
   * @return ��ѯSQL��װ����
   * @throws BusinessException
   */
  ISupplyResultForInvp getSupply(String pk_org, String tempTable)
      throws BusinessException;
}
