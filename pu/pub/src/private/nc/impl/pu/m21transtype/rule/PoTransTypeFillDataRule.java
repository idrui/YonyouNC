/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-26 上午11:36:57
 */
package nc.impl.pu.m21transtype.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>设置交易类型发货、装运、报关、出关的前置状态
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-6-26 上午11:36:57
 */
public class PoTransTypeFillDataRule implements IRule<PoTransTypeVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(PoTransTypeVO[] vos) {

    for (PoTransTypeVO vo : vos) {
      // 设置后置状态
      this.processStatusAft(vo);
    }
  }

  /**
   * 方法功能描述：设置后置状态
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-12 下午04:26:11
   */
  private void processStatusAft(PoTransTypeVO vo) {

    // 审核
    if (vo.getBoutput().booleanValue()) {
      vo.setIapproveaft((Integer) OnwayStatus.STATUS_OUTPUT.value());
    }
    else if (vo.getBconfirm().booleanValue()) {
      vo.setIapproveaft((Integer) OnwayStatus.STATUS_CONFIRM.value());
    }
    else if (vo.getBconsign().booleanValue()) {
      vo.setIapproveaft((Integer) OnwayStatus.STATUS_SENDOUT.value());
    }
    else if (vo.getBload().booleanValue()) {
      vo.setIapproveaft((Integer) OnwayStatus.STATUS_SHIP.value());
    }
    else if (vo.getBcustom().booleanValue()) {
      vo.setIapproveaft((Integer) OnwayStatus.STATUS_COMEIN.value());
    }
    else if (vo.getBoutcustom().booleanValue()) {
      vo.setIapproveaft((Integer) OnwayStatus.STATUS_GETOUT.value());
    }
    else if (vo.getBarrive().booleanValue()) {
      vo.setIapproveaft((Integer) OnwayStatus.STATUS_ARRIVE.value());
    }
    // 默认入库为最后状态
    else if (vo.getBstore().booleanValue() || !vo.getBarrive().booleanValue()) {
      vo.setIapproveaft((Integer) OnwayStatus.STATUS_STORE.value());
    }

    // 输出
    if (vo.getBoutput().booleanValue()) {

      if (vo.getBconfirm().booleanValue()) {
        vo.setIoutputaft((Integer) OnwayStatus.STATUS_CONFIRM.value());
      }
      else if (vo.getBconsign().booleanValue()) {
        vo.setIoutputaft((Integer) OnwayStatus.STATUS_SENDOUT.value());
      }
      else if (vo.getBload().booleanValue()) {
        vo.setIoutputaft((Integer) OnwayStatus.STATUS_SHIP.value());
      }
      else if (vo.getBcustom().booleanValue()) {
        vo.setIoutputaft((Integer) OnwayStatus.STATUS_COMEIN.value());
      }
      else if (vo.getBoutcustom().booleanValue()) {
        vo.setIoutputaft((Integer) OnwayStatus.STATUS_GETOUT.value());
      }
      else if (vo.getBarrive().booleanValue()) {
        vo.setIoutputaft((Integer) OnwayStatus.STATUS_ARRIVE.value());
      }
      // 默认入库为最后状态
      else if (vo.getBstore().booleanValue() || !vo.getBarrive().booleanValue()) {
        vo.setIoutputaft((Integer) OnwayStatus.STATUS_STORE.value());
      }
    }

    // 对方确认
    if (vo.getBconfirm().booleanValue()) {

      if (vo.getBconsign().booleanValue()) {
        vo.setIconfirmaft((Integer) OnwayStatus.STATUS_SENDOUT.value());
      }
      else if (vo.getBload().booleanValue()) {
        vo.setIconfirmaft((Integer) OnwayStatus.STATUS_SHIP.value());
      }
      else if (vo.getBcustom().booleanValue()) {
        vo.setIconfirmaft((Integer) OnwayStatus.STATUS_COMEIN.value());
      }
      else if (vo.getBoutcustom().booleanValue()) {
        vo.setIconfirmaft((Integer) OnwayStatus.STATUS_GETOUT.value());
      }
      else if (vo.getBarrive().booleanValue()) {
        vo.setIconfirmaft((Integer) OnwayStatus.STATUS_ARRIVE.value());
      }
      // 默认入库为最后状态
      else if (vo.getBstore().booleanValue() || !vo.getBarrive().booleanValue()) {
        vo.setIconfirmaft((Integer) OnwayStatus.STATUS_STORE.value());
      }
    }

    // 发货
    if (vo.getBconsign().booleanValue()) {

      if (vo.getBload().booleanValue()) {
        vo.setIconsignaft((Integer) OnwayStatus.STATUS_SHIP.value());
      }
      else if (vo.getBcustom().booleanValue()) {
        vo.setIconsignaft((Integer) OnwayStatus.STATUS_COMEIN.value());
      }
      else if (vo.getBoutcustom().booleanValue()) {
        vo.setIconsignaft((Integer) OnwayStatus.STATUS_GETOUT.value());
      }
      else if (vo.getBarrive().booleanValue()) {
        vo.setIconsignaft((Integer) OnwayStatus.STATUS_ARRIVE.value());
      }
      // 默认入库为最后状态
      else if (vo.getBstore().booleanValue() || !vo.getBarrive().booleanValue()) {
        vo.setIconsignaft((Integer) OnwayStatus.STATUS_STORE.value());
      }
    }

    // 装运
    if (vo.getBload().booleanValue()) {

      if (vo.getBcustom().booleanValue()) {
        vo.setIloadaft((Integer) OnwayStatus.STATUS_COMEIN.value());
      }
      else if (vo.getBoutcustom().booleanValue()) {
        vo.setIloadaft((Integer) OnwayStatus.STATUS_GETOUT.value());
      }
      else if (vo.getBarrive().booleanValue()) {
        vo.setIloadaft((Integer) OnwayStatus.STATUS_ARRIVE.value());
      }
      // 默认入库为最后状态
      else if (vo.getBstore().booleanValue() || !vo.getBarrive().booleanValue()) {
        vo.setIloadaft((Integer) OnwayStatus.STATUS_STORE.value());
      }
    }

    // 报关
    if (vo.getBcustom().booleanValue()) {

      if (vo.getBoutcustom().booleanValue()) {
        vo.setIcustomaft((Integer) OnwayStatus.STATUS_GETOUT.value());
      }
      else if (vo.getBarrive().booleanValue()) {
        vo.setIcustomaft((Integer) OnwayStatus.STATUS_ARRIVE.value());
      }
      // 默认入库为最后状态
      else if (vo.getBstore().booleanValue() || !vo.getBarrive().booleanValue()) {
        vo.setIcustomaft((Integer) OnwayStatus.STATUS_STORE.value());
      }
    }

    // 出关
    if (vo.getBoutcustom().booleanValue()) {

      if (vo.getBarrive().booleanValue()) {
        vo.setIoutcustomaft((Integer) OnwayStatus.STATUS_ARRIVE.value());
      }
      // 默认入库为最后状态
      else if (vo.getBstore().booleanValue() || !vo.getBarrive().booleanValue()) {
        vo.setIoutcustomaft((Integer) OnwayStatus.STATUS_STORE.value());
      }
    }

    if (vo.getBarrive().booleanValue()) {
      // 不做处理
    }
    if (vo.getBstore().booleanValue()) {
      // 不做处理
    }
  }
}
