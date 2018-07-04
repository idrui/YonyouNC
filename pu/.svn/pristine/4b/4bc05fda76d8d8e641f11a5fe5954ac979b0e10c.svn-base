package nc.bs.pu.m23.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * @description
 *              到货单的BP二次开发插入点定义
 * @scene
 * @param 无
 * @since 6.3
 * @version 2010-1-13 上午10:26:37
 * @author hanbin
 */
public enum ArriveBPPlugInPoint implements IPluginPoint {

  /**
   * 删除
   */
  ArriveDeleteBP,

  /**
   * 新增
   */
  ArriveInsertBP,
  
  /**
   * API导入
   */
  ArriveInsertAPIBP,

  /**
   * 修改
   */
  ArriveUpdateBP,

  /**
   * 生成资产卡片
   */
  CreateFACardBP,

  /**
   * 删除资产卡片
   */
  DeleteFACardBP,

  /**
   * 删除转固单
   */
  DeleteTransAsset,

  /**
   * 周转材直领
   */
  MaterialAssign,
  /**
   * 生成转固单
   */
  TransAsset,

  /**
   * 采购订单补货后回写
   */
  Writeback23For21BP,

  /**
   * 采购入库单回写
   */
  Writeback23For45BP,

  /**
   * 委托入库单回写
   */
  Writeback23For47BP;

  @Override
  public String getComponent() {
    return POBillType.Arrive.getCode();
  }

  @Override
  public String getModule() {
    return NCModule.PO.getSystemCode();
  }

  @Override
  public String getPoint() {
    return this.name();
  }
}
