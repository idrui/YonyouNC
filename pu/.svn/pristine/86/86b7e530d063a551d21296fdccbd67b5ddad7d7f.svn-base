package nc.pubitf.pu.m422x.invp.inv9;

import java.util.Map;

import nc.vo.pu.m422x.entity.WriteBack422XForInv9ParamVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

/**
 * 物资需求申请单为 库存计划 提供的回写接口
 * 
 * @since 6.3
 * @version 2014-4-29 下午03:18:00
 * @author zhangyhh
 */
public interface IReWrite422xForInv9 {

  /**
   * 库存平衡后回写物资需求申请单接口
   * 
   * @param WriteBack422XVO[]
   * @throws BusinessException
   */
  void reWriteReq(WriteBack422XForInv9ParamVO[] param) throws BusinessException;

  /**
   * 取消库存平衡后回写物资需求申请单接口--清空下游信息
   * 
   * @param pk_reqline
   * @throws BusinessException
   */
  void reWriteReqForClear(WriteBack422XForInv9ParamVO[] vos)
      throws BusinessException;

  /**
   * 删除请购单回写相应物资需求申请单数量接口
   * 
   * @param pk_line
   * @param billtype 单据类型
   * @throws BusinessException
   */
  void reWriteReqForDelete(Map<String, UFDouble> returnNums, String billtype)
      throws BusinessException;

}
