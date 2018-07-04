package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * 备注：物资需求申请的收回 单据动作执行中的动态执行类的动态执行类。 创建日期：(2010-10-12)
 * 
 * @author 平台脚本生成
 */
public class N_422X_UNSAVEBILL extends AbstractCompiler2 {

  /**
   * N_422X_UNSAVEBILL 构造子注解。
   */
  public N_422X_UNSAVEBILL() {
    super();
  }

  /*
   * 备注：平台编写原始脚本
   */
  @Override
  public String getCodeRemark() {
    return "	Object retValue = null;\nnc.vo.pu.m422x.entity.StoreReqAppVO[] vos = (nc.vo.pu.m422x.entity.StoreReqAppVO[])getVos();\nretValue = nc.bs.framework.common.NCLocator.getInstance().lookup(nc.itf.pu.m422x.IStoreReqAppApprove.class).unSendapprove(vos,this);\nreturn retValue;\n";
  }

  /*
   * 备注：平台编写规则类 接口执行类
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;
      Object retValue = null;
      nc.vo.pu.m422x.entity.StoreReqAppVO[] vos =
          (nc.vo.pu.m422x.entity.StoreReqAppVO[]) this.getVos();
      retValue =
          nc.bs.framework.common.NCLocator.getInstance()
              .lookup(nc.itf.pu.m422x.IStoreReqAppApprove.class)
              .unSendapprove(vos, this);
      return retValue;
    }
    catch (Exception ex) {
      if (ex instanceof BusinessException) {
        throw (BusinessException) ex;
      }

      throw new PFBusinessException(ex.getMessage(), ex);

    }
  }

}
