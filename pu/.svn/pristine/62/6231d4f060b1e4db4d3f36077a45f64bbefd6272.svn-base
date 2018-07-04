/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-28 上午10:17:15
 */
package nc.bs.pu.m20.maintain;

import nc.bs.pu.m20.maintain.rule.ATPAfterUpdateRule;
import nc.bs.pu.m20.maintain.rule.ATPBeforeUpdateRule;
import nc.bs.pu.m20.maintain.rule.insert.BillCodeRule;
import nc.bs.pu.m20.maintain.rule.insert.FillInsertPraybillVos;
import nc.bs.pu.m20.maintain.rule.insert.InsertAfterEventRule;
import nc.bs.pu.m20.maintain.rule.insert.InsertBeforeEventRule;
import nc.bs.pu.m20.maintain.rule.insert.RwritegfjktdataRule;
import nc.bs.pu.m20.maintain.rule.insert.RwriteksjktdataRule;
import nc.bs.pu.m20.maintain.rule.insert.RwritepgjktdataRule;
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
import nc.bs.pu.m20.plugin.PraybillPluginPoint;
import nc.impl.pubapp.bd.userdef.UserDefSaveRule;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.enumeration.EnumOperate;
import nc.vo.pu.pub.rule.NewestOrgVersionFillRule;
import nc.vo.pu.pub.rule.TrantypeNotNullCheckRule;
import nc.vo.pu.pub.rule.WriteBackM4A08Rule;
import nc.vo.pu.pub.rule.WriteBackPIMRule;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.rule.StockOrgEnableCheckRule;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单插入BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-1-28 上午10:17:15
 */
public class PraybillInsertBP {

  public PraybillVO[] insert(PraybillVO[] aggVO) {

    AroundProcesser<PraybillVO> processer =
        new AroundProcesser<PraybillVO>(PraybillPluginPoint.INSERT);

    // 添加BP规则
    this.addBeforeRule(processer);
    this.addAfterRule(processer);

    processer.before(aggVO);

    BillInsert<PraybillVO> bo = new BillInsert<PraybillVO>();
    PraybillVO[] vos = bo.insert(aggVO);

    processer.after(aggVO);
    // 回写上游(注意：放到更新可用量之前)
    new PraybillWriteBackSourceRule(EnumOperate.ADD).process(vos, null);
    // 可用量
    new ATPAfterUpdateRule().process(vos);

    // 回写项目
    new WriteBackPIMRule<PraybillVO>(POBillType.PrayBill.getCode()).process(
        vos, null);
    // 回写资产配置申请标志位
    new WriteBackM4A08Rule().process(vos, null);
    return vos;

  }

  private void addAfterRule(AroundProcesser<PraybillVO> processer) {

    // 单据号唯一性检查
    processer.addAfterRule(new BillCodeCheckUniqueRule());
    // 触发新增后事件
    processer.addAfterRule(new InsertAfterEventRule());
    // 写业务日志
    // processer.addAfterRule(new WriteBusiLogRule<PraybillVO>(
    // PuBusiLogActionCode.save));
  }

  private void addBeforeRule(AroundProcesser<PraybillVO> processer) {

    // 表体非空检查
    processer.addBeforeRule(new BodyEmptyRule());
    // 非空项检查
    processer.addBeforeFinalRule(new NotNullRule());
    // 检查交易类型是否为空
    processer.addBeforeFinalRule(new TrantypeNotNullCheckRule<PraybillVO>());
    // 计算主组织最新版
    processer.addBeforeFinalRule(new NewestOrgVersionFillRule<PraybillVO>(
        PraybillHeaderVO.PK_PRAYBILL));
    // 补全数据
    processer.addBeforeRule(new FillInsertPraybillVos());
    // 设置下游回写数量等为0
    processer.addBeforeRule(new FillDownNumRule());
    // 长度检查
    processer.addBeforeFinalRule(new ChkLenRule());
    // 数量检查
    processer.addBeforeRule(new NumCheckRule());
    // 单据号处理
//    processer.addBeforeRule(new BillCodeRule());
    
    //根据来表头来源单据主键更新攀钢采购计划接口中间表
    processer.addBeforeRule(new RwritepgjktdataRule());
    
    //根据来表头来源单据主键更新股份采购计划接口中间表
     processer.addBeforeRule(new RwritegfjktdataRule());
    
  //根据来表头来源单据主键更新矿山采购计划接口中间表
    processer.addBeforeRule(new RwriteksjktdataRule());
    // 该校验改在前台，询问的方式来处理 - lixyp 2012.5.9
    // // 请购日期、需求日期、建议订货日期
    // processer.addBeforeFinalRule(new ChkDateRule());

    // 行号检查
    processer.addBeforeRule(new RownoCheckRule());
    // 主组织停用检查
    processer.addBeforeRule(new StockOrgEnableCheckRule<PraybillVO>());
    // 直运和委外互斥检查
    processer.addBeforeFinalRule(new DirecttransitAndSctype());
    // 请购单如果是直运业务，校验表体订单类型是否存在非直运订单类型
    processer.addBeforeFinalRule(new TranstypeCheck());

    // 物料是否分配到库存组织检查
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
    // 可用量
    processer.addBeforeFinalRule(new ATPBeforeUpdateRule());
    // 触发新增前事件
    processer.addBeforeRule(new InsertBeforeEventRule());
    // 自由辅助属性校验
    // MarAssistantSaveRule<PraybillVO> marRule =
    // new MarAssistantSaveRule<PraybillVO>();
    // marRule.setNotNullValidate(PraybillItemVO.PK_MATERIAL);
    // processer.addBeforeRule(marRule);

  }
}
