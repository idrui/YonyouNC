package nc.vo.pu.m20.entity;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-26 下午06:56:57
 */

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-1-26 下午06:56:57
 */
public class PraybillVOMeta extends AbstractBillMeta {

  public PraybillVOMeta() {
    this.init();
  }

  private void init() {
    this.setParent(PraybillHeaderVO.class);
    this.addChildren(PraybillItemVO.class);
  }
}
