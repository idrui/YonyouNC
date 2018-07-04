package nc.pubitf.pu.m20.invp.inv9;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * 请购单作为库存计划的供给而提供的查询服务
 * 
 * @since 6.0
 * @version 2010-12-14 下午06:18:00
 * @author duy
 */
public interface IBuyingreqQueryForInv9 {

  /**
   * 查询请购单是否已审批
   * @param pk_req
   * @return Map<String, UFBoolean>  Key:表头PK ;Value:是否已审批，true为已审批
   * @throws BusinessException
   */
  Map<String, UFBoolean> getIsApprovedOfBuyingreq(String[] pk_praybill)
      throws BusinessException;
}
