package nc.pubitf.pu.m20.pim;

import nc.pubitf.scmpub.pim.fetch.IFetchDataForPim;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.scmpub.mm.LinkQueryParamVOForPM;

/**
 * 为项目管理提供的查询服务
 * 
 * @since 6.1
 * @version 2012-3-6 下午01:30:40
 * @author yangtian
 */
public interface IBuyingReqQueryForPIM {

  /**
   * 为项目管理联查提供的查询服务。
   * 
   * @param pvo 查询条件vo
   * @return 符合条件的订单聚合vo
   * @throws BusinessException
   */
  public PraybillVO[] queryBuyingReqForLinkQuery(LinkQueryParamVOForPM pvo)
      throws BusinessException;

  /**
   * 发布物资及服务需求单时, 根据项目和“请购日期”所处期间查询请购单（保存态、审批中、审批通过、审批未通过）
   * 
   * @param cprojectids 项目主键数组
   * @param beginDateTime 抓取开始日期
   * @param endDateTime 抓取结束日期
   * @return 汇总的物资需求申请数量
   * @throws BusinessException
   */
  public IFetchDataForPim[] queryBuyingReqForPIM(String[] cprojectids,
      UFDateTime beginDateTime, UFDateTime endDateTime)
      throws BusinessException;
}
