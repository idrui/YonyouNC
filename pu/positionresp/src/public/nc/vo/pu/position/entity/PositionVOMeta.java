/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-6-1 上午09:51:27
 */
package nc.vo.pu.position.entity;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

/**
 * 计划岗（采购岗）物料设置单据VO的元数据
 * 
 * @author GGR
 * @time 2009-12-22 下午04:33:56
 * @since 6.0
 */
public class PositionVOMeta extends AbstractBillMeta {

  /**
   * PositioninvVOMeta 的构造子
   */
  public PositionVOMeta() {
    this.init();
  }

  private void init() {
    this.setParent(PositionHeaderVO.class);
    this.addChildren(PositionItemVO.class);
  }

}
