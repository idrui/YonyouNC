package nc.pubitf.pu.m21.invp;

import nc.pubitf.invp.plan.ISupplyResultForInvp;
import nc.vo.pub.BusinessException;

/**
 * �ɹ�������Ϊ���ƻ��Ĺ������ṩ�Ĳ�ѯ����
 * 
 * @since 6.0
 * @version 2010-12-14 ����11:42:15
 * @author duy
 */
public interface IOrderQueryForInvp {

  /**
   * ���زɹ������Ĳ�ѯSQL��װ����
   * 
   * @param pk_org �ջ������֯��OID
   * @param tempTable
   *          ��ʱ��ı��������������ֶΣ�pk_material-�����ظ�������VID��dstart-��ʼʱ�䣬dend-����ʱ�䣩
   * @param includeRed �Ƿ�������ֵ��ݣ����Ϊtrue������������򲻰���
   * @return ��ѯSQL��װ����
   * @throws BusinessException
   */
  public ISupplyResultForInvp getSupply(String pk_org, String tempTable,
      boolean includeRed) throws BusinessException;
}
