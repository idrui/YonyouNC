package nc.itf.pu.m23.fa;

import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>到货单的针对资产的接口定义，本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单生成资产卡片
 * <li>到货单删除资产卡片
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-11 下午01:39:45
 */
public interface IArriveForFA {

  /**
   * 方法功能描述：到货单生成资产卡片
   * <p>
   * <b>参数说明</b>
   * 
   * @param aggVO
   * @param bid_snVOArray_map
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-5-10 上午09:06:00
   */
  ArriveVO[] createFACard(ArriveVO[] aggVO) throws BusinessException;

  /**
   * 方法功能描述：到货单删除资产卡片
   * <p>
   * <b>参数说明</b>
   * 
   * @param aggVO
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-13 上午09:38:43
   */
  ArriveVO[] deleteFACard(ArriveVO[] aggVO) throws BusinessException;

  /**
   * 删除到货单生成的转固单
   * 
   * @param vos 删除转固单的到货单
   * @return 到货单
   * @throws BusinessException
   */
  ArriveVO[] deleteTransAsset(ArriveVO[] vos) throws BusinessException;

  /**
   * 周转材直领查询服务
   * 
   * @param vos 到货单VO，只包含主键和ts
   * @return
   * @throws BusinessException
   */
  ArriveVO[] queryArriveFor4A60(ArriveVO[] vos) throws BusinessException;

  /**
   * 到货单生成转固单
   * 
   * @param vos 生成转固单的到货单
   * @return 到货单
   * @throws BusinessException
   */
  ArriveVO[] transAsset(ArriveVO[] vos) throws BusinessException;
}
