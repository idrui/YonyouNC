package nc.pubitf.pu.position;

import java.util.List;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

/**
 * 采购岗/计划岗为分单函数提供服务：根据组织和物料匹配岗位，根据岗位分单
 * 
 * @since 6.0
 * @version 2010-11-9 下午06:36:28
 * @author wuxla
 */
public interface IPositionForSplit {

  /**
   * 当组织在表体时，根据表体采购组织和物料匹配采购岗/计划岗，然后根据采购岗/计划岗分单
   * 
   * @param vo 需要分单的来源单据VO，主要包括请购单、采购合同、价格审批单、库存借入转采购、退库单
   * @param keys 0：组织字段名称 1:物料VID字段名称 2:物料分类字段名称，如果没有则为null
   *          例如采购订单参照请购单时，keys为new String
   *          []{PraybillItemVO.PK_PURCHASEORG,PraybillItemVO
   *          .PK_MATERIAL,null}
   * @param positiontype 0：生成请购单（计划岗） 1:生成采购订单（采购岗）
   * @return 来源单据表体对应的岗位id，和表体一一对应
   * @throws BusinessException
   */
  public List<String> splitBorgByPosition(AggregatedValueObject vo,
      String[] keys, int positiontype) throws BusinessException;

  /**
   * 当组织在表头时，根据表头采购组织和物料匹配采购岗/计划岗，然后根据采购岗/计划岗分单
   * 
   * @param vo 需要分单的来源单据VO，主要包括退货单、销售订单、调拨订单
   * @param keys 0：组织字段名称 1:物料VID字段名称 2:物料分类字段名称，为null
   *          例如采购订单参照请购单时，keys为new String
   *          []{PraybillItemVO.PK_PURCHASEORG,PraybillItemVO
   *          .PK_MATERIAL,null}
   * @param positiontype 0：生成请购单（计划岗） 1:生成采购订单（采购岗）
   * @return
   * @throws BusinessException
   */
  public List<String> splitHorgByPosition(AggregatedValueObject vo,
      String[] keys, int positiontype) throws BusinessException;

  /**
   * 生产制造只有表头，根据表头组织和物料物料匹配采购岗/计划岗，然后根据采购岗/计划岗分单
   * 
   * @param vo 需要分单的来源单据VO，主要包括MPS/MRP（生产），生产订单，计划订单（库存计划）
   * @param keys 0：组织字段名称 1:物料VID字段名称 2:物料分类字段名称，则为null
   *          例如采购订单参照请购单时，keys为new String
   *          []{PraybillItemVO.PK_PURCHASEORG,PraybillItemVO
   *          .PK_MATERIAL,null}
   * @param positiontype 0：生成请购单（计划岗） 1:生成采购订单（采购岗）
   * @return
   * @throws BusinessException
   */
  public List<String> splitMMByPosition(AggregatedValueObject vo,
      String[] keys, int positiontype) throws BusinessException;
}
