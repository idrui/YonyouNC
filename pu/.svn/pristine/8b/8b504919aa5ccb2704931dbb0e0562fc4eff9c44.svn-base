package nc.bs.pu.m4201.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.it.ITServices;
import nc.vo.ic.m45.entity.PurchaseInHeadVO;
import nc.vo.ic.m45.entity.PurchaseInVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pub.lang.UFBoolean;

/**
 * 进口入库签字保存时vo交换后处理规则
 * 
 * @since 6.31
 * @version 2013-10-11 上午09:31:49
 * @author mengjian
 */
public class ChangeDataForITAfterRule implements IRule<PurchaseinFIVO> {

  private PurchaseInVO[] vosFiltered;

  public ChangeDataForITAfterRule(PurchaseInVO[] vosFiltered) {
    this.vosFiltered = vosFiltered;
  }

  @Override
  public void process(PurchaseinFIVO[] vos) {
    if (null == vos || null == this.vosFiltered) {
      return;
    }
    PurchaseInHeadVO hvo = (PurchaseInHeadVO) this.vosFiltered[0].getParent();
    if (UFBoolean.FALSE.equals(hvo.getBitinbill())) {
      return;
    }

    ITServices.dealAttributeAfterVOChange(this.vosFiltered, vos);
  }

}
