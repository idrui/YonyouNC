/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-9 ����01:35:00
 */
package nc.pubimpl.pu.m4202;

import nc.bs.pu.m4202.VMIFICancelDupBP;
import nc.bs.pu.m4202.VMIFIDupBP;
import nc.pubitf.pu.m4202.IVMIFIMaintain;
import nc.vo.ic.m50.entity.VmiSumVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���Ļ��ܲ���(����)ά������ʵ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-9 ����01:35:00
 */
public class VMIFIMaintainImpl implements IVMIFIMaintain {

  @Override
  public void cancelDupVMI(String[] hids) throws BusinessException {
    try {
      new VMIFICancelDupBP().cancelDuplicate(hids);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

  }

  @Override
  public void duplicateVMI(VmiSumVO[] vos) throws BusinessException {
    try {
      new VMIFIDupBP().duplicate(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

}
