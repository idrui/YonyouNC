package nc.bs.pu.m24.maintain;

import nc.bs.pu.m24.maintain.rewrite.Write45WhenInsertRule;
import nc.bs.pu.m24.maintain.rule.BillCodeCheckUniqueRule;
import nc.bs.pu.m24.maintain.rule.BillCodeRule;
import nc.bs.pu.m24.maintain.rule.BodyEmptyRule;
import nc.bs.pu.m24.maintain.rule.FillInsertPricestlVos;
import nc.bs.pu.m24.plugin.PricestlPluginPoint;
import nc.impl.pubapp.bd.userdef.UserDefSaveRule;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m24.entity.PricestlHeaderVO;
import nc.vo.pu.m24.entity.PricestlItemVO;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pu.pub.rule.NewestOrgVersionFillRule;
import nc.vo.scmpub.rule.PurchaseOrgEnableCheckRule;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>价格结算单插入BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-7-30 下午02:36:43
 */
public class PricestlInsertBP {

  public PricestlVO[] insert(PricestlVO[] aggVO) {

    AroundProcesser<PricestlVO> processer =
        new AroundProcesser<PricestlVO>(PricestlPluginPoint.INSERT);

    // 添加BP规则
    this.addBeforeRule(processer);
    this.addAfterRule(processer);

    processer.before(aggVO);

    BillInsert<PricestlVO> bo = new BillInsert<PricestlVO>();
    PricestlVO[] vos = bo.insert(aggVO);

    processer.after(aggVO);

    return vos;

  }

  private void addAfterRule(AroundProcesser<PricestlVO> processer) {

    // 单据号唯一性检查
    processer.addAfterRule(new BillCodeCheckUniqueRule());
    // 回写上游
    processer.addAfterRule(new Write45WhenInsertRule());

  }

  private void addBeforeRule(AroundProcesser<PricestlVO> processer) {

    // 表体非空检查
    processer.addBeforeRule(new BodyEmptyRule());
    // // 非空项检查
    // processer.addBeforeFinalRule(new NotNullRule());
    // 计算主组织最新版
    processer.addBeforeFinalRule(new NewestOrgVersionFillRule<PricestlVO>(
        PricestlHeaderVO.PK_PRICESETTLE));
    // 主组织停用检查
    processer.addBeforeRule(new PurchaseOrgEnableCheckRule<PricestlVO>());
    // 补全数据
    processer.addBeforeRule(new FillInsertPricestlVos());

    // // 长度检查
    // processer.addBeforeFinalRule(new ChkLenRule());

    // 单据号处理
    processer.addBeforeRule(new BillCodeRule());
    processer.addBeforeRule(new UserDefSaveRule<PricestlVO>(new Class[] {
      PricestlHeaderVO.class, PricestlItemVO.class
    }));
    // // 行号检查
    // processer.addBeforeRule(new RownoCheckRule());

  }
}
