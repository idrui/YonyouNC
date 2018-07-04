package nc.pubimpl.pu.m422x.ic.m4d;

import nc.bs.pu.m422x.rewrite.ReWrite422xFor4dBP;
import nc.pubitf.pu.m422x.ic.m4d.IReWrite422xFor4d;
import nc.vo.pu.m422x.entity.WriteBack422XVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>物资需求申请提供给材料出库单的回写服务实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-9-25 下午01:58:08
 */
public class ReWrite422xFor4dImpl implements IReWrite422xFor4d {

  @Override
  public void backWriteNum(WriteBack422XVO[] paraVos) throws BusinessException {
    try {
      new ReWrite422xFor4dBP().backWriteNum(paraVos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

  }

}
