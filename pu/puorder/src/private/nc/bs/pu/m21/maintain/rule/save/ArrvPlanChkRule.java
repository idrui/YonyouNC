/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-12-29 下午02:43:26
 */
package nc.bs.pu.m21.maintain.rule.save;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              采购订单对有到货计划的订单表体行进行检查
 * @scene
 *        采购订单保存修改
 * @param
 *        OrderVO[] orgVos 原始订单vo
 * @since 6.3
 * @version 2014-10-22 上午11:29:55
 * @author luojw
 */
public class ArrvPlanChkRule implements IRule<OrderVO> {

  private OrderVO[] orgVos = null;

  public ArrvPlanChkRule(OrderVO[] orgVos) {
    this.orgVos = orgVos;
  }

  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    StringBuilder errMsg = new StringBuilder();
    // Map<String, OrderItemVO> map = AggVOUtil.createItemMap(this.orgVos);
    BillIndex index = new BillIndex(this.orgVos);
    IVOMeta meta = this.orgVos[0].getMetaData().getVOMeta(OrderItemVO.class);
    for (OrderVO vo : vos) {
      if (VOStatus.NEW == vo.getHVO().getStatus()) {
        continue;
      }

      String vbillcode = vo.getHVO().getVbillcode();
      for (OrderItemVO itemVO : vo.getBVO()) {
        if (!ValueUtils.getUFBoolean(itemVO.getBreceiveplan()).booleanValue()) {
          continue;
        }

        if (VOStatus.DELETED == itemVO.getStatus()) {
          errMsg.append(NCLangResOnserver.getInstance().getStrByID("4004030_0",
              "04004030-0230", null, new String[] {
                vbillcode, itemVO.getCrowno()
              })/* 单据号：{0}行号：{1}存在到货计划不能删除！\n */);
        }
        else {
          // 主数量
          UFDouble nnum = itemVO.getNnum();
          // 累计到货计划数量
          UFDouble accArrvPlanNum = itemVO.getNaccumrpnum();

          // 订单数量 不能小于累计到货计划数量
          if (MathTool.compareTo(nnum, accArrvPlanNum) < 0) {
            errMsg.append(NCLangResOnserver.getInstance().getStrByID(
                "4004030_0", "04004030-0231", null, new String[] {
                  vbillcode, itemVO.getCrowno()
                })/* 单据号：{0}行号：{1}订单主数量小于累计所估到货计划数量!\n */);
          }

          // OrderItemVO orgItemVO = map.get(itemVO.getPk_order_b());
          OrderItemVO orgItemVO =
              (OrderItemVO) index.get(meta, itemVO.getPk_order_b());
          // 物料不能改变
          if (null != orgItemVO
              && !orgItemVO.getPk_material().equals(itemVO.getPk_material())) {
            errMsg.append(NCLangResOnserver.getInstance().getStrByID(
                "4004030_0", "04004030-0232", null, new String[] {
                  vbillcode, itemVO.getCrowno()
                })/* 单据号：{0}行号：{1}存在到货计划的订单行不能改变物料!\n */);
          }
          // 单位不能改变
          if (null != orgItemVO
              && !orgItemVO.getCastunitid().equals(itemVO.getCastunitid())) {
            errMsg.append(NCLangResOnserver.getInstance().getStrByID(
                "4004030_0", "04004030-0233", null, new String[] {
                  vbillcode, itemVO.getCrowno()
                })/* 单据号：{0}行号：{1}存在到货计划的订单行不能改变单位!\n */);
          }

        }

      }
    }

    if (errMsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(errMsg.toString());
    }
  }

}
