package nc.ui.pu.pub.util;

import java.util.List;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pu.pub.rule.BusitypeFillRule;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.scmpub.res.billtype.IBillType;
import nc.vo.uif2.LoginContext;

import org.apache.commons.lang.StringUtils;

/**
 * 业务流程设置器，用于：主组织切换、新增按钮、复制时确定业务流程
 * 
 * @since 6.0
 * @version 2011-8-5 下午11:06:08
 * @author zhaoyha
 */
public class BusitypeSetter {

  private IKeyValue billHelper;

  private IBillType bt;

  private LoginContext context;

  /**
   * 
   * @param bt 单据类型
   * @param billHelper 单据的封装器
   * @param context LoginContex
   */
  public BusitypeSetter(IBillType bt, IKeyValue billHelper, LoginContext context) {
    super();
    this.bt = bt;
    this.billHelper = billHelper;
    this.context = context;
  }

  /**
   * 复制单据时处理
   * 
   * @param rccRuleLst
   */
  public void copySet(List<IPURemoteCallCombinator> rccRuleLst) {
    // 处理交易类型
    String transtype = TrantypeFuncUtils.getTrantype(this.context);
    // 只有交易类型发布的节点，才重新确定流程，因为此时可能不会触发类型编辑后，有可能确定不出流程
    if (StringUtils.isNotBlank(transtype)) {
      // 注册远程调用
      BusitypeFillRule bizRule =
          new BusitypeFillRule(this.billHelper, this.bt.getCode());
      bizRule.prepare();
      rccRuleLst.add(bizRule);
    }

  }

  /**
   * 点自制按钮时处理
   * 
   * @param rccRuleLst
   */
  public void manualAddSet(List<IPURemoteCallCombinator> rccRuleLst) {
    String transtype = TrantypeFuncUtils.getTrantype(this.context);
    String pk_org = this.context.getPk_org();
    String pk_busitype =
        (String) this.billHelper.getHeadValue(PUQueryConst.PK_BUSITYPE);
    // 只有交易类型发布的节点，才重新确定流程，因为此时可能不会触发类型编辑后，有可能确定不出流程
    // 此处可以判断流程字段是否已经有值，如果已经有了可以不再找，此种情况出现：
    // 设置的修改化中心，新增时已经触发了组织编辑事件，那里也有相同的规则
    if (StringUtils.isNotBlank(transtype) && StringUtils.isNotBlank(pk_org)
        && StringUtils.isBlank(pk_busitype)) {
      BusitypeFillRule biz =
          new BusitypeFillRule(this.billHelper, this.bt.getCode());
      biz.prepare();
      rccRuleLst.add(biz);
    }

  }

  /**
   * 主组织切换时处理
   * 
   * @param rccRuleLst
   */
  public void orgChgSet(List<IPURemoteCallCombinator> rccRuleLst) {
    String transtype = TrantypeFuncUtils.getTrantype(this.context);
    String pk_org = this.context.getPk_org();
    // 只有交易类型发布的节点，才重新确定流程，因为此时可能不会触发类型编辑后，有可能确定不出流程
    if (StringUtils.isNotBlank(transtype) && StringUtils.isNotBlank(pk_org)) {
      BusitypeFillRule biz =
          new BusitypeFillRule(this.billHelper, this.bt.getCode());
      biz.prepare();
      rccRuleLst.add(biz);
    }
  }
}
