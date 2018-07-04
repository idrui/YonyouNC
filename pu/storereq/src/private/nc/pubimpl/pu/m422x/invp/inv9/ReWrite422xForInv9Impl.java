package nc.pubimpl.pu.m422x.invp.inv9;

import java.util.Map;

import nc.bs.pu.m422x.rewrite.ReWrite422XFor4410BP;
import nc.pubitf.pu.m422x.invp.inv9.IReWrite422xForInv9;
import nc.vo.pu.m422x.entity.WriteBack422XForInv9ParamVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 物资需求申请单为 库存计划 提供的回写实现
 * 
 * @since 6.3
 * @version 2014-4-29 下午03:18:00
 * @author zhangyhh
 */
public class ReWrite422xForInv9Impl implements IReWrite422xForInv9 {

  /**
   * 库存平衡后回写物资需求申请单实现
   * 
   * @param WriteBack422XVO[]
   * @throws BusinessException
   */
  @Override
  public void reWriteReq(WriteBack422XForInv9ParamVO[] vos)
      throws BusinessException {

    try {
      new ReWrite422XFor4410BP().backWriteFor4410(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

  }

  /**
   * 取消库存平衡后回写物资需求申请单实现--清空下游信息
   * 
   * @param pk_reqline
   * @throws BusinessException
   */
  @Override
  public void reWriteReqForClear(WriteBack422XForInv9ParamVO[] vos)
      throws BusinessException {
    try {
      new ReWrite422XFor4410BP().backWriteClearFor4410(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  /**
   * 删除下游单据回写物资需求申请单实现--清空下游信息 --暂时废弃
   * 
   * @param pks 要清空上游物资需求申请所对应的下游单据id
   * @throws BusinessException
   */
  @Override
  public void reWriteReqForDelete(Map<String, UFDouble> returnNums,
      String billtype) throws BusinessException {
    try {
      new ReWrite422XFor4410BP().backWriteFor4410(returnNums, billtype);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }
}
