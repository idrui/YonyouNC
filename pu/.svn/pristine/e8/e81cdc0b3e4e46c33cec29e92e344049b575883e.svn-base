package nc.bs.pu.m21.state;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;

/**
 * 
 * @description
 * 采购订单状态的抽象实现类
 * @scene
 * 
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-10-17 下午4:09:38
 * @author luojw
 */
public abstract class AbstractOrderBillState<E extends IBill> extends AbstractOrderState<E> {

  /**
   * 单据状态构造函数
   *
   * @param stateKey 状态字段
   * @param stateValue 状态值
   */
  public AbstractOrderBillState(String stateKey, Object stateValue) {
    super(stateKey, stateValue);
  }

  @Override
  public boolean isThisState(E vo) {
    Object value = vo.getParent().getAttributeValue(this.stateKey);
    if (value == null) {
      return false;
    }
    return this.stateValue.equals(value);
  }

  @Override
  public void setState(E[] bills) {
    List<ISuperVO> list = new ArrayList<ISuperVO>();
    for (E bill : bills) {
      if (this.isThisState(bill)) {
        String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("pubapp_0","0pubapp-0178")/*@res "当前单据不能重复设置相同的状态"*/;
        ExceptionUtils.wrappBusinessException(message);
      }
      else if (!this.isPrevStateValid(bill)) {
        String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("pubapp_0","0pubapp-0179")/*@res "当前单据所属的状态不能设置当前状态"*/;
        ExceptionUtils.wrappBusinessException(message);
      }
      ISuperVO head = bill.getParent();
      list.add(head);
      head.setAttributeValue(this.stateKey, this.stateValue);
    }
    this.updateStateKey(list);
  }

  private void updateStateKey(List<ISuperVO> list) {
    ListToArrayTool<ISuperVO> tool = new ListToArrayTool<ISuperVO>();
    ISuperVO[] vos = tool.convertToArray(list);
    String[] names = new String[] {
      this.stateKey
    };
    VOUpdate<ISuperVO> bo = new VOUpdate<ISuperVO>();
    bo.update(vos, names);
  }


}