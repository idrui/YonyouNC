package nc.vo.pu.m27.util;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.costfactor.IFactorOrdByOrgMaterial;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>费用第一次分摊的工具类（前台UI、后台都会继承此类）
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-9-2 上午11:34:24
 */
public abstract class AbstrictFirstFeeDistribute {

  public void distribute() {

    // 分摊劳务类
    this.distributeFee();

    // 分摊折扣类
    this.distributeDiscount();
  }

  abstract public void distributeDiscount();

  abstract public void distributeFee();

  /**
   * 方法功能描述：根据成本要素的主键获得其要素索引号(用于设置对应结算单行的要素值)
   * <p>
   * <b>参数说明</b>
   *
   * @param pk_factor
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-9-2 上午11:52:12
   */
  public int getFactorIndex(CostfactorViewVO factorvo) {
    // 对应的要素值ITEMKEY
    if (factorvo == null || factorvo.getIfactororder() == null) {
      String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0169")/*@res "成本要素的成本要素顺序字段为空，请检查成本要素定义！"*/;
      ExceptionUtils.wrappBusinessException(msg);
      return -1;
    }

    int ishoworder = factorvo.getIfactororder().intValue();// 要素中记录了顺序号
    // 要素索引值必须是1--8之间的整数值
    if (ishoworder < 1 || ishoworder > CostfactorVO.MAX_NUM) {
      String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0170")/*@res "成本要素的成本要素顺序字段必须在1--8之间，当前要素顺序值为："*/ + ishoworder;
      ExceptionUtils.wrappBusinessException(msg);
    }
    return ishoworder;
  }

  /**
   * 方法功能描述：查询成本要素视图VO
   * <p>
   * <b>参数说明</b>
   *
   * @param pk_fiorg
   * @param pk_mrls
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-9-16 下午03:18:49
   */
  public CostfactorViewVO[] queryCostFactor(String pk_fiorg, String[] pk_mrls) {
    CostfactorViewVO[] totalFactors = null;
    try {
      IFactorOrdByOrgMaterial cfSrv =
          NCLocator.getInstance().lookup(IFactorOrdByOrgMaterial.class);
      totalFactors = cfSrv.queryFactorViewVOByOrgMaterial(pk_fiorg, pk_mrls);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return totalFactors;
  }
}