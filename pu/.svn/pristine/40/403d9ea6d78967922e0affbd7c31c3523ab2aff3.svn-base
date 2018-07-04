package nc.bs.pu.m25.ap;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m4201.pu.m25.IPurchaseinFIQueryFor25;
import nc.pubitf.pu.m4203.pu.m25.ISubcontinFIQueryFor25;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.ICBillType;

/**
 * 入库物料质量合格相关的工具类
 * 
 * @since 6.0
 * @version 2011-4-8 下午12:09:44
 * @author 田锋涛
 */

public class StockMaterialQualityUtil {

  // /**
  // * 检查采购入库的物料是否质量合格
  // * <ul>
  // * <li>只要有一行不合格，则视为够不合格；
  // * <li>免检的物料或者非批次管理的物料视为合格；
  // * <li>物料批次对应的批次档案的质量等级必须为合格
  // * </ul>
  // *
  // * @param itemsIDs 入库行id
  // * @return true 合格；false 不合格
  // */
  // public boolean checkMQualityFor45(String[] itemsIDs) {
  // return true;
  // }
  //
  // /**
  // * 检查委托加工入库的物料是否质量合格
  // * <ul>
  // * <li>只要有一行不合格，则视为够不合格；
  // * <li>免检的物料或者非批次管理的物料视为合格；
  // * <li>物料批次对应的批次档案的质量等级必须为合格
  // * </ul>
  // *
  // * @param itemsIDs 入库行id
  // * @return true 合格；false 不合格
  // */
  // public boolean checkMQualityFor47(String[] itemsIDs) {
  //
  // return true;
  // }

  /**
   * 根据单据类型和入库行id查询对应的批次管理的非免检的物料，返回批次主键
   * 
   * @param itemIds
   * @param billType
   * @return
   */
  public Map<String, String> queryQualityMaterial(String[] itemIds,
      String billType) {
    if (ICBillType.PurchaseIn.getCode().equals(billType)) {
      return this.queryQualityMaterialFor45(itemIds);
    }
    else if (ICBillType.SubContinIn.getCode().equals(billType)) {
      return this.queryQualityMaterialFor47(itemIds);
    }
    return null;
  }

  /**
   * 根据入库行id查询对应的批次管理的非免检的物料，返回批次主键
   * 
   * @param itemsIDs 入库行id
   * @return Map<String, String>， key=行id，value=符合条件的物料的批次主键
   */
  public Map<String, String> queryQualityMaterialFor45(String[] itemIds) {
    try {
      return NCLocator.getInstance().lookup(IPurchaseinFIQueryFor25.class)
          .queryQualityMaterial(itemIds);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  /**
   * 根据入库行id查询对应的批次管理的非免检的物料，返回批次主键
   * 
   * @param itemsIDs 入库行id
   * @return Map<String, String>， key=行id，value=符合条件的物料的批次主键
   */
  public Map<String, String> queryQualityMaterialFor47(String[] itemIds) {
    try {
      return NCLocator.getInstance().lookup(ISubcontinFIQueryFor25.class)
          .queryQualityMaterial(itemIds);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }
}
