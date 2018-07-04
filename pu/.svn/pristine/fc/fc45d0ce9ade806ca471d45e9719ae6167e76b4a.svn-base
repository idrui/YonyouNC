/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-4 上午11:35:50
 */
package nc.vo.pu.m20.entity.writeback;

import nc.vo.pu.m20.enumeration.EnumOperate;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b> 价格审批、寻报价单、采购合同回写生成次数基类
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-2-4 上午11:35:50
 */
public class GenNumWriteBackVO extends BaseWriteBackVO {

  private static final long serialVersionUID = -2628939182581274025L;

  // 操作标识：增、改、删
  private EnumOperate operateFlag;

  // 是否是总括订单
  private UFBoolean isSaOrder;

  // 总括订单行行号
  private String crowno;

  /**
   * 总括订单行行号
   * 
   * @return
   */
  public String getCrowno() {
    return this.crowno;
  }

  /**
   * 总括订单行行号
   * 
   * @param crowno
   */
  public void setCrowno(String crowno) {
    this.crowno = crowno;
  }

  /**
   * 是否是总括订单
   * 
   * @return
   */
  public UFBoolean getIsSaOrder() {
    return this.isSaOrder;
  }

  /**
   * 是否是总括订单
   * 
   * @param isSaOrder
   */
  public void setIsSaOrder(UFBoolean isSaOrder) {
    this.isSaOrder = isSaOrder;
  }

  /**
   * 操作标识：增、改、删
   * 
   * @return opeFlag
   */
  public EnumOperate getOperateFlag() {
    return this.operateFlag;
  }

  /**
   * @param opeFlag
   *          操作标识：增、改、删
   */
  public void setOperateFlag(EnumOperate opeFlag) {
    this.operateFlag = opeFlag;
  }

}
