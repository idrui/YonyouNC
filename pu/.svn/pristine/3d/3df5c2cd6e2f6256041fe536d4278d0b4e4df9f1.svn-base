package nc.pubitf.pu.m422x.invp.inv9;

import java.util.Map;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m422x.entity.StoreReqAppViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

/**
 * 物资需求申请单作为库存计划的需求而提供的查询服务
 * 
 * @since 6.0
 * @version 2010-12-14 下午06:18:00
 * @author duy
 */
public interface IStorereqQueryForInv9 {

  /**
   * 物资需求平衡查询物资需求申请单
   * @param conMap<Map<String key,UFDouble value>>
   * @return StoreReqAppViewVO[]
   */
  StoreReqAppViewVO[] getReq(IQueryScheme queryScheme) throws BusinessException;

  /**
   * 查询物资需求申请单预计出
   * @param pk_req
   * @return Map<String, UFDouble>  
   *         Key:库存组织+物料
   *         Value: 物资需求申请预计出（本次平衡库存组织所有已平衡、
   *         未关闭的物资需求申请单的（主数量－累计消减主数量－累计出库数量）合计）
   * @throws BusinessException
   */
  Map<String, UFDouble> getPlanOut(String[] pk_StoreOrgs, String[] pk_materials)
      throws BusinessException;

  /**
   * 查询物资需求申请单是否已审批
   * @param pk_req
   * @return Map<String, UFBoolean>  Key:表头PK ;Value:是否已审批，true为已审批
   * @throws BusinessException
   */
  Map<String, UFBoolean> getIsApproved(String[] pk_req)
      throws BusinessException;

}
