/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-9 下午01:35:00
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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>消耗汇总财务(副本)维护服务实现
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-9 下午01:35:00
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
