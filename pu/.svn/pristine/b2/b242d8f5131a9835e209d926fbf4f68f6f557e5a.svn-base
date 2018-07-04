package nc.vo.pu.m23.rule.transfer;

import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.rule.ChkEmptyWhenTransfer;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>从采购订单转单后，对于交换后VO进行后续处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-6-19 上午11:03:44
 */
public class AfterTransferUtil {

  // 是否执行联动计算，在基于到货单单退货场景，由于无法执行尾差倒挤，一次拉单数量被反算了。
  // 只能特殊处理，不走通用的联动计算，而是在copy processor中单独进行。
  private boolean canRelationCalculate = true;

  private ArriveVO[] retVOArray;

  public AfterTransferUtil(ArriveVO[] retVOArray) {
    this.retVOArray = retVOArray;
  }

  public AfterTransferUtil(ArriveVO[] retVOArray, boolean canRelationCalculate) {
    this.retVOArray = retVOArray;
    this.canRelationCalculate = canRelationCalculate;
  }

  public ArriveVO[] process() {
    if (this.canRelationCalculate) {
      // 设置到货数量、单价
      new NumAndPrice(this.retVOArray).dealNumAndPrice();
    }
    // 如果上游单据是赠品，填补赠品数量、赠品主数量
    new FillPresentNum(this.retVOArray).fill();
    // 检查转单后的非空项
    new ChkEmptyWhenTransfer(this.retVOArray).checkEmpty();
    // 处理换算率、是否换算率
    new DealUnitAndChangeRate(this.retVOArray).deal();
    // 对于保质期管理的物料行，计算保质期天数
    this.retVOArray = new CalcValidDay(this.retVOArray).fillVaildDay();

    UFDate busidate = AppContext.getInstance().getBusiDate();
    for (ArriveVO vo : this.retVOArray) {
      vo.getHVO().setDbilldate(busidate);

      // 联动计算后，将数量设置到应到数量上，在退货场景下，主数量取反，联动数量，应到应与数量保持一致。
      // 订单退货与原到货退货都需要此步骤。
      for (ArriveItemVO itemVo : vo.getBVO()) {
        itemVo.setNplanastnum(itemVo.getNastnum());
      }
    }

    return this.retVOArray;
  }
}
