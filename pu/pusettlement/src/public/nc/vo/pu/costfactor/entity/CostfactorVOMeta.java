package nc.vo.pu.costfactor.entity;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

/**
 * 成本定义要素单据VO的元数据
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>功能条目1
 * <li>功能条目2
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author zhaoyha
 * @time 2009-6-1 上午09:51:27
 */
public class CostfactorVOMeta extends AbstractBillMeta {

  /**
   * CostfactorVOMeta 的构造子
   */
  public CostfactorVOMeta() {
    this.init();
  }

  private void init() {
    this.setParent(CostfactorHeaderVO.class);
    this.addChildren(CostfactorItemVO.class);
  }

}
