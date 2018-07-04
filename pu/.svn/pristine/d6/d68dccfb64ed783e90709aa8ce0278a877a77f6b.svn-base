/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-26 上午10:46:09
 */
package nc.bs.pu.m4t.maintain;

import nc.bs.pu.est.plugin.InitialEstPluginPoint;
import nc.bs.pu.m4t.maintain.rule.WriteBackSourceRule;
import nc.bs.pu.m4t.maintain.rule.maintain.InitialEstBillStatusChkRule;
import nc.bs.pu.m4t.maintain.rule.maintain.InitialEstCalCostMnyPriceRule;
import nc.bs.pu.m4t.maintain.rule.maintain.InitialEstCodeProcRule;
import nc.bs.pu.m4t.maintain.rule.maintain.InitialEstCodeUniqueRule;
import nc.bs.pu.m4t.maintain.rule.maintain.InitialEstConfirmBusitypeRule;
import nc.bs.pu.m4t.maintain.rule.maintain.InitialEstTailItemFillRule;
import nc.bs.pu.m4t.maintain.rule.maintain.InitialEstTotalValueCalcRule;
import nc.impl.pubapp.bd.userdef.UserDefSaveRule;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.m4t.entity.InitialEstContext;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.m4t.rule.InitialEstBillDateChkRule;
import nc.vo.pu.m4t.rule.InitialEstNotNullChkRule;
import nc.vo.pu.m4t.rule.InitialEstNumValueChkRule;
import nc.vo.pu.m4t.rule.InitialEstNumValueLmtChkRule;
import nc.vo.pu.m4t.rule.InitialEstRowNoChkRule;
import nc.vo.pu.m4t.rule.InitialEstScaleCheckRule;
import nc.vo.pu.m4t.rule.InitialEstSourceInfoChkRule;
import nc.vo.pu.pub.rule.ItemDupHeaderInfoRule;
import nc.vo.pu.pub.rule.NewestOrgVersionFillRule;
import nc.vo.pu.pub.rule.TrantypeNotNullCheckRule;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.scmpub.rule.FinanceOrgEnableCheckRule;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>期初暂估单保存BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-26 上午10:46:09
 */
public class InitialEstSaveBP {
  private InitialEstContext context;

  public InitialEstSaveBP(InitialEstContext context) {
    this.context = context;
  }

  public InitialEstVO[] save(InitialEstVO[] insertVos,
      InitialEstVO[] updateVos, InitialEstVO[] updateOrgVos) {
    CompareAroundProcesser<InitialEstVO> processer =
        new CompareAroundProcesser<InitialEstVO>(InitialEstPluginPoint.SAVE_BP);
    this.addRule(processer);
    InitialEstVO[] insertedVos = null;
    if (!ArrayUtils.isEmpty(insertVos)) {
      processer.before(insertVos, null);
      insertedVos = new BillInsert<InitialEstVO>().insert(insertVos);
      processer.after(insertedVos, null);
    }
    if (!ArrayUtils.isEmpty(updateVos)) {
      processer.before(updateVos, updateOrgVos);
      InitialEstVO[] updatedVos =
          new BillUpdate<InitialEstVO>().update(updateVos, updateOrgVos);
      processer.after(updatedVos, updateOrgVos);
    }
    InitialEstVO[] savedVos = ArrayUtil.combinArrays(insertedVos, updateVos);
    return savedVos;
  }

  /**
   * @param processer
   */
  private void addRule(CompareAroundProcesser<InitialEstVO> processer) {
    // 计算主组织最新版
    processer.addBeforeFinalRule(new NewestOrgVersionFillRule<InitialEstVO>(
        InitialEstHeaderVO.PK_INITIALEST));
    // 主组织停用检查
    processer.addBeforeRule(new FinanceOrgEnableCheckRule<InitialEstVO>());
    // 检查交易类型是否为空
    processer.addBeforeFinalRule(new TrantypeNotNullCheckRule<InitialEstVO>());
    // 表体冗余表头信息
    processer.addBeforeFinalRule(new ItemDupHeaderInfoRule<InitialEstVO>());
    // 非空检查
    processer.addBeforeRule(new InitialEstNotNullChkRule());
    // 数值型属性检查
    processer.addBeforeRule(new InitialEstNumValueChkRule());
    // 数值型属性极限值检查
    processer.addBeforeRule(new InitialEstNumValueLmtChkRule());
    // 单据日期检查
    processer.addBeforeRule(new InitialEstBillDateChkRule());
    // 行号检查
    processer.addBeforeRule(new InitialEstRowNoChkRule());
    // 精度检查
    processer.addBeforeRule(new InitialEstScaleCheckRule());
    // 发票来源信息检查
    processer.addBeforeRule(new InitialEstSourceInfoChkRule());
    // 发票状态检查
    processer.addBeforeRule(new InitialEstBillStatusChkRule());
    // 表尾审计 填充
    processer.addBeforeRule(new InitialEstTailItemFillRule());
    processer.addBeforeRule(new InitialEstCalCostMnyPriceRule());
    // 表头整单合计重算
    processer.addBeforeRule(new InitialEstTotalValueCalcRule());
    // 确定业务流程
    processer.addBeforeRule(new InitialEstConfirmBusitypeRule());
    // 自由项检查
    processer.addBeforeRule(new UserDefSaveRule<InitialEstVO>(new Class[] {
      InitialEstHeaderVO.class, InitialEstItemVO.class
    }));
    // 单据号处理
    processer.addBeforeRule(new InitialEstCodeProcRule());
    // 单据号重复检查
    processer.addAfterRule(new InitialEstCodeUniqueRule());
    // 自由辅助属性校验
    // MarAssistantSaveRule<InitialEstVO> marRule =
    // new MarAssistantSaveRule<InitialEstVO>();
    // marRule.setNotNullValidate(InitialEstItemVO.PK_MATERIAL);
    // processer.addBeforeRule(marRule);
    // 回写上游单据
    processer.addAfterRule(new WriteBackSourceRule(this.context));
  }
}
