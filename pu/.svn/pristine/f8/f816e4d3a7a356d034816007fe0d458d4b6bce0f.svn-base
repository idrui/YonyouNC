/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-18 上午10:36:16
 */
package nc.bs.pu.est.m45.rule;

import nc.bs.pubapp.AppBsContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.est.entity.m45.PurchaseInEstHeaderVO;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pu.est.util.EstCurrencyUtil;
import nc.vo.pu.pub.enumeration.EnumDiscounttaxtype;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 查询暂估后设置暂估数量单价金额等默认信息
 * @scene
 * 自动暂估,前台货物(费用同时)暂估查询暂估
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-23 上午9:15:14
 * @author zhangshqb
 */
public class DefaultGoodsEstInfoSetRule implements IRule<PurchaseInEstVO> {

  @Override
  public void process(PurchaseInEstVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (PurchaseInEstVO vo : vos) {
      this.setInfo(vo);
    }
    // wuxla v61 设置逆向征税表记
    // new GoodsEstOpptaxflagSetter().setOpptaxFlag(vos, vos[0].getParentVO()
    // .getPk_financeorg());
  }

  private UFDouble getExchgrate(PurchaseInEstHeaderVO head) {
    String pk_fiorg = head.getPk_financeorg();
    String pk_currency = head.getCorigcurrencyid();
    String pk_loccurr = head.getCcurrencyid();
    UFDate date = AppContext.getInstance().getBusiDate();
    return EstCurrencyUtil
        .getExchgrate(pk_fiorg, pk_currency, pk_loccurr, date);
  }

  private void setInfo(PurchaseInEstVO vo) {
    PurchaseInEstHeaderVO head = vo.getParentVO();
    UFDouble innum = head.getNinnum();
    UFDouble settlenum = head.getNaccumsettlenum();
    String pk_origcurr = head.getCorigcurrencyid();
    String pk_curr = head.getCcurrencyid();
    UFDouble erate = head.getNchangestdrate();
    head.setDtocostapdate(AppBsContext.getInstance().getBusiDate());
    if (PubAppTool.isEqual(pk_origcurr, pk_curr)) {
      erate = UFDouble.ONE_DBL;
    }
    else {
      erate = this.getExchgrate(head);
    }
    head.setNestexhgrate(erate);// 折本汇率
    head.setFesttaxtype(head.getFtaxtypeflag()); // 扣税类别
    if (null == head.getFesttaxtype()) {
      head.setFesttaxtype((Integer) EnumDiscounttaxtype.TAXIN.value());
    }
    head.setPk_estcurrency(head.getCorigcurrencyid());
    head.setNesttaxrate(head.getNtaxrate());
    if (UFDouble.ZERO_DBL.equals(MathTool.nvl(settlenum))) {
      head.setNestnum(innum);
    }
    else {
      head.setNestnum(MathTool.sub(innum, settlenum));
    }

    // wuxla V61 VAT
    // 税码
    head.setCesttaxcodeid(head.getCtaxcodeid());
    // 不可抵扣税率
    head.setNestnosubtaxrate(head.getNnosubtaxrate());
    // 税率
    head.setNesttaxrate(head.getNtaxrate());
    // 逆向征税直接用采购入库单的逆向征税标记
  }

}
