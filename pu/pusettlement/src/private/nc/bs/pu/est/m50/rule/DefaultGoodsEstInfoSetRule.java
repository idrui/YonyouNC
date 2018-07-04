/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-18 上午10:36:16
 */
package nc.bs.pu.est.m50.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.est.entity.m50.VmiEstHeaderVO;
import nc.vo.pu.est.entity.m50.VmiEstVO;
import nc.vo.pu.est.rule.GoodsEstOpptaxflagSetter;
import nc.vo.pu.est.rule.VmiGoodsCountrySetter;
import nc.vo.pu.est.rule.VmiGoodsVatValue;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 查询暂估后设置暂估数量单价金额等默认信息
 * @scene
 * 前台货物(费用同时)暂估查询暂估
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-23 上午9:59:09
 * @author zhangshqb
 */
public class DefaultGoodsEstInfoSetRule implements IRule<VmiEstVO> {

  @Override
  public void process(VmiEstVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    // 去掉，取税码档案上的税率
    // Map<String, UFDouble> marTaxrateMap = this.getMaterialTaxRate(vos);
    for (VmiEstVO vo : vos) {
      this.setInfo(vo);
    }

    String pk_financeorg = vos[0].getParentVO().getPk_financeorg();
    new VmiGoodsCountrySetter().setCountry(vos, pk_financeorg);
    new VmiGoodsVatValue().setVatValue(vos);
    // wuxla v61 设置逆向征税表记
    new GoodsEstOpptaxflagSetter().setOpptaxFlag(vos, pk_financeorg);
  }

  /**
   * 方法功能描述：得到物料的税率。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return map 不为空
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-12 下午02:29:08
   */
  // private Map<String, UFDouble> getMaterialTaxRate(VmiEstVO[] vos) {
  // String[] mvids =
  // (String[]) AggVOUtil.getDistinctHeadFieldArray(vos,
  // GoodsEstVO.PK_MATERIAL, String.class);
  // Map<String, UFDouble> retMap = null;
  // retMap = MaterialTaxRateService.queryTaxRate(mvids);
  // return retMap;
  // }

  private void setInfo(VmiEstVO vo) {
    VmiEstHeaderVO head = vo.getParentVO();
    UFDouble innum = head.getNinnum();
    UFDouble settlenum = head.getNaccumsettlenum();
    head.setNestexhgrate(UFDouble.ONE_DBL);// 折本汇率(消耗汇总不直接外币)
    // wuxla v61 使用税码设置
    // head.setFesttaxtype((Integer) EnumDiscounttaxtype.TAXOUT.value()); //
    // 扣税类别
    head.setPk_estcurrency(head.getCorigcurrencyid());
    // String pk_material = head.getPk_material();
    // head.setNesttaxrate(marTaxrateMap.get(pk_material));
    head.setDtocostapdate(AppContext.getInstance().getBusiDate());
    if (UFDouble.ZERO_DBL.equals(MathTool.nvl(settlenum))) {
      head.setNestnum(innum);
    }
    else {
      head.setNestnum(MathTool.sub(innum, settlenum));
    }
  }

}
