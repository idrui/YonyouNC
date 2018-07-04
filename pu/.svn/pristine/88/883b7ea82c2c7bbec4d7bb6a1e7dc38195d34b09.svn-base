package nc.impl.pu.m23.approve;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.impl.pu.m23.approve.action.ArriveApproveAction;
import nc.impl.pu.m23.approve.action.ArriveSendAction;
import nc.impl.pu.m23.approve.action.ArriveUnApproveAction;
import nc.impl.pu.m23.approve.action.ArriveUnSendApproveAction;
import nc.itf.pu.m23.approve.IArriveApprove;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>到货单的审批操作接口实现类，本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单的审核
 * <li>到货单的弃审
 * <li>到货单的送审
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-13 上午09:34:54
 */
public class ArriveApproveImpl implements IArriveApprove {

  @Override
  public ArriveVO[] approveArrive(ArriveVO[] voArray, AbstractCompiler2 script)
      throws BusinessException {
    try {
      return new ArriveApproveAction().approveArrive(voArray, script);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public ArriveVO[] sendArrive(ArriveVO[] voArray, AbstractCompiler2 script)
      throws BusinessException {
    try {
      return new ArriveSendAction().sendArrive(voArray, script);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public ArriveVO[] unApproveArrive(ArriveVO[] voArray, AbstractCompiler2 script)
      throws BusinessException {
    try {
      return new ArriveUnApproveAction().unApproveArrive(voArray, script);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public ArriveVO[] unSendArrive(ArriveVO[] voArray, AbstractCompiler2 script)
      throws BusinessException {
    try {
      return new ArriveUnSendApproveAction().unSendArrive(voArray, script);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
