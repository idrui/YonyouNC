/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-15 上午09:50:26
 */
package nc.impl.pu.m21.action;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pu.m21.action.rule.rp.ATPBeforeUpdateRule;
import nc.impl.pu.m21.action.rule.rp.ATPUpdateRule;
import nc.impl.pu.m21.action.rule.rp.RPAfterDeleteEventRule;
import nc.impl.pu.m21.action.rule.rp.RPBatchCodeBeforeRule;
import nc.impl.pu.m21.action.rule.rp.RPBeforeDeleteEventRule;
import nc.impl.pu.m21.action.rule.rp.RPDPlanarrvdateRule;
import nc.impl.pu.m21.action.rule.rp.RPDeleteRule;
import nc.impl.pu.m21.action.rule.rp.RPNaccumrpnumRule;
import nc.impl.pu.m21.action.rule.rp.RPNumRule;
import nc.impl.pu.m21.action.rule.rp.StoreAndMaterialRule;
import nc.impl.pu.m21.action.rule.rp.WriteBackAccrmRPNum;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.data.vo.VODelete;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.data.vo.tool.VOConcurrentTool;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.RPItemNotNullCheckRule;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>保存到货计划
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-15 上午09:50:26
 */
public class OrderReceivePlanSaveAction {

  /**
   * 方法功能描述：保存
   * <p>
   * <b>参数说明</b>
   * 
   * @param batchVO
   * @param orderVO
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-19 上午11:34:17
   */
  public Object[] batchSave(BatchOperateVO batchVO, OrderVO orderVO,
      UFBoolean confirm) throws BusinessException {

    // 得到原批量VO，只有删除和更新的VO
    BatchOperateVO originVO = this.getOrigninVO(batchVO);

    // 对采购订单进行差异VO处理
    BillTransferTool<OrderVO> tool =
        new BillTransferTool<OrderVO>(new OrderVO[] {
          orderVO
        });
    OrderVO orderOrginVO = tool.getOriginBills()[0];

    // 规则
    CompareAroundProcesser<BatchOperateVO> processer =
        new CompareAroundProcesser<BatchOperateVO>(null);
    //
    this.addBeforeRule(processer, orderOrginVO);
    this.addAfterRule(processer, orderOrginVO, confirm);

    processer.before(new BatchOperateVO[] {
      batchVO
    }, new BatchOperateVO[] {
      originVO
    });

    // 保存
    BatchOperateVO saveVO = this.batchSave(batchVO, originVO);

    processer.after(new BatchOperateVO[] {
      saveVO
    }, new BatchOperateVO[] {
      originVO
    });

    // 保存后回写采购订单，查询新OrderVO
    BillQuery<OrderVO> query = new BillQuery<OrderVO>(OrderVO.class);
    OrderVO[] newOrderVO = query.query(new String[] {
      orderVO.getHVO().getPk_order()
    });

    // 钟鸣经理的差异VO暂时不能用
    OrderVO retOrderVO = tool.getBillForToClient(newOrderVO)[0];
    // 设置返回值
    Object[] retObject = new Object[2];
    // 暂时
    retObject[0] = saveVO;
    retObject[1] = retOrderVO;
    return retObject;
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-16 下午07:23:56
   */
  private void addAfterRule(CompareAroundProcesser<BatchOperateVO> processer,
      OrderVO orderVO, UFBoolean confirm) {
    // 回写订单子表的到货计划标志
    // 根据数量就可以判断，去掉
    // processer.addAfterRule(new WriteBackOrderItemBRP(orderVO));
    // 订单数量不能小于收货数量
    // 累计到货计划数量不能大于订单数量
    processer.addAfterRule(new RPNaccumrpnumRule(orderVO));
    processer.addAfterRule(new RPNumRule(confirm));
    // 回写订单子表的累计到货计划数量
    processer.addAfterFinalRule(new WriteBackAccrmRPNum(orderVO));
    // 判断某订单的到货计划的存货是否存在于库存组织中
    processer.addAfterRule(new StoreAndMaterialRule());
    // 可用量
    processer.addAfterRule(new ATPUpdateRule(orderVO));
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-16 下午07:25:29
   */
  private void addBeforeRule(CompareAroundProcesser<BatchOperateVO> processer,
      OrderVO orderVO) {
    processer.addBeforeRule(new RPItemNotNullCheckRule());
    // 已到货数量不能大于数量
    // processer.addBeforeRule(new RPRowNumRule());
    // 有后续单据或相关订单行已关闭，不能删除
    processer.addBeforeRule(new RPDeleteRule(orderVO));
    // 辅计量管理但未确认辅单位的物料
    // processer.addBeforeRule(new RPCastunitidRule(orderVO));
    // 计划到货日期不早于订单日期
    processer.addBeforeRule(new RPDPlanarrvdateRule(orderVO));

    // 影响批次号
    processer.addBeforeRule(new RPBatchCodeBeforeRule(orderVO));
    // 可用量
    processer.addBeforeRule(new ATPBeforeUpdateRule(orderVO));
  }

  /**
   * 方法功能描述：保存
   * <p>
   * <b>参数说明</b>
   * 
   * @param batchVO
   * @param originVO
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-19 下午07:34:34
   */
  private BatchOperateVO batchSave(BatchOperateVO batchVO,
      BatchOperateVO originVO) {
    // 删除
    Object[] delObjs = batchVO.getDelObjs();
    if (!ArrayUtils.isEmpty(delObjs)) {
      OrderReceivePlanVO[] delVOs = ArrayUtil.convertArrayType(delObjs);

      new RPBeforeDeleteEventRule().process(delVOs);
      VODelete<OrderReceivePlanVO> deleteAction =
          new VODelete<OrderReceivePlanVO>();
      deleteAction.delete(delVOs);
      new RPAfterDeleteEventRule().process(delVOs);
    }

    // 修改
    Object[] upObjs = batchVO.getUpdObjs();
    if (!ArrayUtils.isEmpty(upObjs)) {
      OrderReceivePlanVO[] upVOs = ArrayUtil.convertArrayType(upObjs);
      OrderReceivePlanVO[] upOriginVOs =
          ArrayUtil.convertArrayType(originVO.getUpdObjs());
      VOUpdate<OrderReceivePlanVO> updateAction =
          new VOUpdate<OrderReceivePlanVO>();
      upVOs = updateAction.update(upVOs, upOriginVOs);
      batchVO.setUpdObjs(upVOs);
    }

    // 添加
    Object[] addObjs = batchVO.getAddObjs();
    if (!ArrayUtils.isEmpty(addObjs)) {
      OrderReceivePlanVO[] addVOs = ArrayUtil.convertArrayType(addObjs);
      VOInsert<OrderReceivePlanVO> insertAction =
          new VOInsert<OrderReceivePlanVO>();
      addVOs = insertAction.insert(addVOs);
      batchVO.setAddObjs(addVOs);
    }

    return batchVO;
  }

  /**
   * 方法功能描述：检查TS
   * <p>
   * <b>参数说明</b>
   * 
   * @param rpVO
   * @param originRPVO <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-16 下午06:46:32
   */
  private void checkTs(OrderReceivePlanVO rpVO, OrderReceivePlanVO originRPVO) {
    boolean flag = true;
    // 新VO的Ts
    UFDateTime rpTs = rpVO.getTs();
    // 新VO中保存的表头Ts
    // UFDateTime rpOrderTs = rpVO.getTorderts();
    // 新VO中保存的表体Ts
    // UFDateTime rpItemTs = rpVO.getTorderbts();
    // 旧VO的Ts
    UFDateTime originRPTs = originRPVO.getTs();
    // 表头的Ts
    // UFDateTime headerTs = orderVO.getHVO().getTs();
    // 表体的Ts
    // UFDateTime itemTs = itemMap.get(rpVO.getPk_order_b()).getTs();
    if (null == rpTs) {
      flag = false;
    }
    else if (!ObjectUtils.equals(rpTs, originRPTs)) {
      flag = false;
    }
    // else if (!ObjectUtils.equals(rpOrderTs, headerTs)) {
    // flag = false;
    // }
    // else if (!ObjectUtils.equals(rpItemTs, itemTs)) {
    // flag = false;
    // }

    if (!flag) {
      this.throwsBusinessException();
    }
  }

  /**
   * 方法功能描述：检查TS
   * <p>
   * <b>参数说明</b>
   * 
   * @param rpVOs <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-16 下午04:47:41
   */
  private void checkTs(OrderReceivePlanVO[] rpVOs,
      OrderReceivePlanVO[] originRPVOs) {
    if (rpVOs.length != originRPVOs.length) {
      this.throwsBusinessException();
    }

    Map<String, OrderReceivePlanVO> originRPMap =
        new HashMap<String, OrderReceivePlanVO>();
    for (OrderReceivePlanVO originRPVO : originRPVOs) {
      originRPMap.put(originRPVO.getPk_order_bb1(), originRPVO);
    }

    for (OrderReceivePlanVO rpVO : rpVOs) {
      String pkOrderBB1 = rpVO.getPk_order_bb1();
      if (null == pkOrderBB1) {
        continue;
      }

      OrderReceivePlanVO originRPVO = originRPMap.get(pkOrderBB1);
      if (null == originRPVO) {
        this.throwsBusinessException();
      }

      this.checkTs(rpVO, originRPVO);
    }
  }

  /**
   * 方法功能描述：获得数据库中的数据
   * <p>
   * <b>参数说明</b>
   * 
   * @param rpVOs
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-16 下午04:37:30
   */
  private OrderReceivePlanVO[] getOriginVOs(OrderReceivePlanVO[] rpVOs) {
    if (ArrayUtils.isEmpty(rpVOs)) {
      return null;
    }

    new VOConcurrentTool().lock(rpVOs);

    int length = rpVOs.length;
    String[] pkOrderBB1s = new String[length];
    for (int i = 0; i < length; ++i) {
      pkOrderBB1s[i] = rpVOs[i].getPk_order_bb1();
    }

    VOQuery<OrderReceivePlanVO> query =
        new VOQuery<OrderReceivePlanVO>(OrderReceivePlanVO.class);
    OrderReceivePlanVO[] originRPVOs = query.query(pkOrderBB1s);

    this.checkTs(rpVOs, originRPVOs);

    return originRPVOs;
  }

  /**
   * 方法功能描述：得到原批量VO
   * <p>
   * <b>参数说明</b>
   * 
   * @param batchVO
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-16 下午07:19:10
   */
  private BatchOperateVO getOrigninVO(BatchOperateVO batchVO) {

    BatchOperateVO originVO = new BatchOperateVO();

    // 更新的VO
    Object[] upVOs = batchVO.getUpdObjs();
    if (!ArrayUtils.isEmpty(upVOs)) {
      OrderReceivePlanVO[] upRPVOs = ArrayUtil.convertArrayType(upVOs);
      OrderReceivePlanVO[] upOriginVO = this.getOriginVOs(upRPVOs);
      originVO.setUpdObjs(upOriginVO);
    }

    // 删除的VO
    Object[] delVOs = batchVO.getDelObjs();
    if (!ArrayUtils.isEmpty(delVOs)) {
      OrderReceivePlanVO[] delRPVOs = ArrayUtil.convertArrayType(delVOs);
      OrderReceivePlanVO[] delOriginVO = this.getOriginVOs(delRPVOs);
      originVO.setDelObjs(delOriginVO);
    }

    return originVO;
  }

  private void throwsBusinessException() {
    ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
        .getNCLangRes().getStrByID("4004030_0", "04004030-0167")/*
                                                                 * @res
                                                                 * "出现并发，请重新查询"
                                                                 */);
  }

}
