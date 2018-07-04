/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2009-12-30 上午08:59:06
 */
package nc.bs.pu.m21.maintain.rule.save;

import java.util.ArrayList;
import java.util.List;

import nc.bs.ml.NCLangResOnserver;
import nc.bs.pu.m21.maintain.rule.FreezeInfoSetter;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.context.OrderContext;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.exception.AskMaxPriceException;
import nc.vo.pu.m21.rule.MaxPrice;
import nc.vo.pu.pub.constant.PUParaValue;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * @description
 *              采购订单最高限价检查
 * @scene
 *        采购订单保存修改
 * @param OrderContext ctx 采购订单操作时前台到后台的环境信息
 * @since 6.3
 * @version 2014-10-22 下午3:00:50
 * @author luojw
 */

public class MaxPriceChkRule implements IRule<OrderVO> {
  private OrderContext ctx;

  public MaxPriceChkRule(OrderContext ctx) {
    this.ctx = ctx;
  }

  @Override
  public void process(OrderVO[] vos) {
    // 读取PO01最高限制控制参数值
    PUParaValue.ctrltype PO01 =
        PUSysParamUtil.getPO01(vos[0].getHVO().getPk_org());

    // 不控制
    if (PUParaValue.ctrltype.not_control.equals(PO01)) {
      return;
    }

    // 过滤掉已经冻结和退货订单的Vo
    OrderVO[] vosFiltered = this.filterFreezeAndReturnVos(vos);
    if (vosFiltered.length == 0) {
      return;
    }

    // 查询出最高限价，并放到VO中
    this.getMaxPrice(vosFiltered);

    StringBuilder errMsg = new StringBuilder();
    // 不保存
    if (PUParaValue.ctrltype.not_save.equals(PO01)) {
      this.chkAndNoSave(vosFiltered, errMsg);
      if (errMsg.length() > 0) {
        ExceptionUtils.wrappBusinessException(errMsg.toString());
      }
    }
    // 提示
    else if (PUParaValue.ctrltype.ask.equals(PO01)) {
      this.chkAndAsk(vosFiltered, errMsg);
      if (errMsg.length() > 0) {
        ExceptionUtils.wrappException(new AskMaxPriceException(errMsg
            .toString()));
      }
    }
    else if (PUParaValue.ctrltype.freeze.equals(PO01)) {
      this.chkAndFreeze(vosFiltered);
    }
  }

  private void chkAndAsk(OrderVO[] vos, StringBuilder errMsg) {
    // 如果用户已经确认过继续保存，则直接返回
    if (ValueUtils.getBoolean(this.ctx.getMaxPriceConfirm())) {
      return;
    }

    for (OrderVO vo : vos) {
      String vbillcode = vo.getHVO().getVbillcode();
      for (OrderItemVO item : vo.getBVO()) {
        if (this.isNetPriceLargeThanMaxPrice(item)) {
          errMsg.append(NCLangResOnserver.getInstance().getStrByID("4004030_0",
              "04004030-0235", null, new String[] {
                vbillcode, item.getCrowno()
              })/* 单据号：{0} 行号：{1}超过物料的最高限价，是否继续？ */);
        }
      }
    }
  }

  private void chkAndFreeze(OrderVO[] vos) {
    List<OrderVO> freezeVos = new ArrayList<OrderVO>();
    List<OrderHeaderVO> headVO = new ArrayList<OrderHeaderVO>();
    for (OrderVO vo : vos) {
      for (OrderItemVO item : vo.getBVO()) {
        if (this.isNetPriceLargeThanMaxPrice(item)) {
          freezeVos.add(vo);
          headVO.add(vo.getHVO());
          break;
        }
      }
    }

    if (freezeVos.size() > 0) {
      OrderVO[] orders = freezeVos.toArray(new OrderVO[freezeVos.size()]);
      String freezeReason =
          NCLangResOnserver.getInstance().getStrByID("4004030_0",
              "04004030-0236")/* 超最高限价则订单冻结 */;
      new FreezeInfoSetter(orders).setFreezeInfo(freezeReason);
      OrderHeaderVO[] headVOs =
          headVO.toArray(new OrderHeaderVO[headVO.size()]);
      VOUpdate<OrderHeaderVO> update = new VOUpdate<OrderHeaderVO>();
      update.update(headVOs, new String[] {
        OrderHeaderVO.BFROZEN, OrderHeaderVO.VFROZENREASON,
        OrderHeaderVO.PK_FREEZEPSNDOC, OrderHeaderVO.TFREEZETIME
      });
    }
  }

  private void chkAndNoSave(OrderVO[] vos, StringBuilder errMsg) {
    for (OrderVO vo : vos) {
      String vbillcode = vo.getHVO().getVbillcode();
      for (OrderItemVO item : vo.getBVO()) {
        if (this.isNetPriceLargeThanMaxPrice(item)) {
          errMsg.append(NCLangResOnserver.getInstance().getStrByID("4004030_0",
              "04004030-0237", null, new String[] {
                vbillcode, item.getCrowno()
              })/* 单据号：{0} 行号：{1}超过物料的最高限价！ */);
        }
      }
    }
  }

  private OrderVO[] filterFreezeAndReturnVos(OrderVO[] vos) {
    List<OrderVO> unFreezeVos = new ArrayList<OrderVO>();
    for (OrderVO vo : vos) {
      // 过滤掉冻结和退货的订单
      if (ValueUtils.getBoolean(vo.getHVO().getBfrozen())
          || ValueUtils.getBoolean(vo.getHVO().getBreturn())) {
        continue;
      }
      unFreezeVos.add(vo);
    }
    return unFreezeVos.toArray(new OrderVO[unFreezeVos.size()]);
  }

  private void getMaxPrice(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      BillHelper<OrderVO> helper = new BillHelper<OrderVO>(vo);
      int[] rows = new int[vo.getBVO().length];
      for (int i = 0; i < rows.length; i++) {
        rows[i] = i;
      }
      new MaxPrice(helper).setMaxPrice(rows);
    }
  }

  private boolean isNetPriceLargeThanMaxPrice(OrderItemVO item) {
    boolean flag = false;

    UFDouble nmaxprice = ValueUtils.getUFDouble(item.getNmaxprice());
    // 赠品不控制；如果最高限价未录入或者录入0也不控制
    if (ValueUtils.getBoolean(item.getBlargess())
        || MathTool.compareTo(nmaxprice, UFDouble.ZERO_DBL) == 0) {
      return flag;
    }

    if (MathTool.compareTo(item.getNnetprice(), item.getNmaxprice()) > 0) {
      flag = true;
    }
    return flag;
  }
}
