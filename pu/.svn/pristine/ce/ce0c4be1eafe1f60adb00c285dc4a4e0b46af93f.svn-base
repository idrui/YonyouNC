/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-28 上午11:00:36
 */
package nc.bs.pu.m20.maintain;

import nc.bs.pu.m20.maintain.rule.ATPAfterUpdateRule;
import nc.bs.pu.m20.maintain.rule.ATPBeforeUpdateRule;
import nc.bs.pu.m20.maintain.rule.ReviseBudgetCtrlRule;
import nc.bs.pu.m20.maintain.rule.pub.BillCodeCheckUniqueRule;
import nc.bs.pu.m20.maintain.rule.pub.BodyEmptyRule;
import nc.bs.pu.m20.maintain.rule.pub.ChkLenRule;
import nc.bs.pu.m20.maintain.rule.pub.DirecttransitAndSctype;
import nc.bs.pu.m20.maintain.rule.pub.FillDownNumRule;
import nc.bs.pu.m20.maintain.rule.pub.NotNullRule;
import nc.bs.pu.m20.maintain.rule.pub.NumAndOrigmnySumRule;
import nc.bs.pu.m20.maintain.rule.pub.NumCheckRule;
import nc.bs.pu.m20.maintain.rule.pub.RownoCheckRule;
import nc.bs.pu.m20.maintain.rule.pub.TranstypeCheck;
import nc.bs.pu.m20.maintain.rule.update.BillCodeUpdateRule;
import nc.bs.pu.m20.maintain.rule.update.FillUpdatedPraybillVos;
import nc.bs.pu.m20.maintain.rule.update.RwritegfjktUpdatedataRule;
import nc.bs.pu.m20.maintain.rule.update.RwriteksjktUpdatedataRule;
import nc.bs.pu.m20.maintain.rule.update.RwritepgjktUpdatedataRule;
import nc.bs.pu.m20.maintain.rule.update.UpdateAfterEventRule;
import nc.bs.pu.m20.maintain.rule.update.UpdateBeforeEventRule;
import nc.bs.pu.m20.plugin.PraybillPluginPoint;
import nc.impl.pubapp.bd.userdef.UserDefSaveRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.enumeration.EnumOperate;
import nc.vo.pu.pub.rule.NewestOrgVersionFillRule;
import nc.vo.pu.pub.rule.TrantypeNotNullCheckRule;
import nc.vo.pu.pub.rule.WriteBack422xRule;
import nc.vo.pu.pub.rule.WriteBackM4A08Rule;
import nc.vo.pu.pub.rule.WriteBackPIMRule;
import nc.vo.pu.pub.rule.pf.NoPassUpdateRule;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单修改保存
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-1-28 上午11:00:36
 */
public class PraybillUpdateBP {

  /**
   * 方法功能描述：请购单修改保存。
   * <p>
   * <b>参数说明</b>
   * 
   * @param updateVos 需要更新的请购单
   * @param originalVos 旧请购单
   * @param isRevise 是否是修订
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-23 下午02:20:16
   */
  public PraybillVO[] update(PraybillVO[] updateVos, PraybillVO[] originalVos,
      boolean isRevise) {
    CompareAroundProcesser<PraybillVO> processer =
        new CompareAroundProcesser<PraybillVO>(PraybillPluginPoint.UPDATE);
    this.addBeforeRule(processer, isRevise);
    this.addAfterRule(processer, isRevise);

    PraybillVO[] upVos = updateVos;
    processer.before(upVos, originalVos);
    BillUpdate<PraybillVO> bo = new BillUpdate<PraybillVO>();
    upVos = bo.update(upVos, originalVos);
    processer.after(upVos, originalVos);

    return upVos;
  }

  private void addAfterRule(CompareAroundProcesser<PraybillVO> processer,
      boolean isRevise) {
    if (!isRevise) {
      // 单据号唯一性检查
      processer.addAfterRule(new BillCodeCheckUniqueRule());
    }

    // 回写采购计划
    processer.addAfterRule(new ReviseBudgetCtrlRule());

    // 写业务日志
    // processer.addAfterRule(new WriteBusiLogRule<PraybillVO>(
    // PuBusiLogActionCode.save));
    // 回写项目
    processer.addAfterRule(new WriteBackPIMRule<PraybillVO>(POBillType.PrayBill
        .getCode()));
    // 回写上游
    processer.addAfterRule(new PraybillWriteBackSourceRule(EnumOperate.MODIFY));
    // 保存后可用量处理
    processer.addAfterRule(new ATPAfterUpdateRule());
    // 回写资产配置申请标志位（该操作并不是请购单界面修改保存触发，具体是资产配置申请时，取消审批时触发）
    processer.addAfterRule(new WriteBackM4A08Rule());
    processer.addAfterRule(new WriteBack422xRule<PraybillVO>(
        POBillType.PrayBill.getCode()));
    // 触发更新后事件
    processer.addAfterRule(new UpdateAfterEventRule());

  }

  private void addBeforeRule(CompareAroundProcesser<PraybillVO> processer,
      boolean isRevise) {

    if (!isRevise) {
      // 单据号处理
      processer.addBeforeRule(new BillCodeUpdateRule());
    }
    // FillUpdatedPraybillVos
    processer.addBeforeRule(new FillUpdatedPraybillVos());
    // 表体非空检查
    processer.addBeforeRule(new BodyEmptyRule());
    // 非空项检查
    processer.addBeforeFinalRule(new NotNullRule());
    // 检查交易类型是否为空
    processer.addBeforeFinalRule(new TrantypeNotNullCheckRule<PraybillVO>());
    // 计算主组织最新版
    processer.addBeforeFinalRule(new NewestOrgVersionFillRule<PraybillVO>(
        PraybillHeaderVO.PK_PRAYBILL));
    // 审批不通过时修改后的状态及审批人审批日期等处理
    processer.addBeforeFinalRule(new NoPassUpdateRule<PraybillVO>());
    // 设置下游回写数量等为0
    processer.addBeforeRule(new FillDownNumRule());
    // 长度检查
    processer.addBeforeFinalRule(new ChkLenRule());
    // 数量检查
    processer.addBeforeRule(new NumCheckRule());

    // 该校验改在前台，询问的方式来处理 - lixyp 2012.5.9
    // 请购日期、需求日期、建议订货日期
    // processer.addBeforeFinalRule(new ChkDateRule());

    // 行号检查
    processer.addBeforeRule(new RownoCheckRule());
    // 直运和委外互斥检查
    processer.addBeforeFinalRule(new DirecttransitAndSctype());
    // 请购单如果是直运业务，校验表体订单类型是否存在非直运订单类型
    processer.addBeforeFinalRule(new TranstypeCheck());
    // // 物料是否分配到库存组织
    // processer.addBeforeFinalRule(new MaterialInStorckOrgRule());
    // // 物料是否是请购单表头对应的库存组织可见
    // processer.addBeforeFinalRule(new ChkMrlInStockOrg());
    // // 物料档案是否表体采购组织可见
    // processer.addBeforeFinalRule(new ChkMrlInPurchaseOrg());
    // 计算表头总数量和价税合计
    processer.addBeforeFinalRule(new NumAndOrigmnySumRule());
    // 自定义项检查
    processer.addBeforeRule(new UserDefSaveRule<PraybillVO>(new Class[] {
      PraybillHeaderVO.class, PraybillItemVO.class
    }));
    // 自由辅助属性校验
    // MarAssistantSaveRule<PraybillVO> marRule =
    // new MarAssistantSaveRule<PraybillVO>();
    // marRule.setNotNullValidate(PraybillItemVO.PK_MATERIAL);
    // processer.addBeforeRule(marRule);
    // 保存前可用量处理
    processer.addBeforeRule(new ATPBeforeUpdateRule());
    // 触发更新前事件
    processer.addBeforeRule(new UpdateBeforeEventRule());
    
    //根据来表头来源单据主键更新攀钢采购计划接口中间表
    processer.addBeforeRule(new RwritepgjktUpdatedataRule());
    
    //根据来表头来源单据主键更新股份采购计划接口中间表
    processer.addBeforeRule(new RwritegfjktUpdatedataRule());
    
    //根据来表头来源单据主键更新矿山采购计划接口中间表
     processer.addBeforeRule(new RwriteksjktUpdatedataRule());

  }
}
