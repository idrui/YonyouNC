/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-27 ����06:19:19
 */
package nc.bs.pu.est.rule.pricequery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.rule.IEstPriceQueryInfoProvide;
import nc.vo.pu.est.util.EstRelationCalcItem;
import nc.vo.pu.est.util.EstRelationCalcUtil;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ���ⵥ��ѯ�۴�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-27 ����06:19:19
 */
public class EstPriceQryContext {
  private GoodsEstVO[] heads;

  private IEstPriceQueryInfoProvide[] pqinfo;

  private AbstractEstPriceQueryStrategy strategy;

  /**
   * PurchsInEstPriceQryProcesser �Ĺ�����
   * 
   * @param heads
   * @param pss
   */
  public EstPriceQryContext(GoodsEstVO[] heads) {
    this.heads = heads;
    this.pqinfo = heads;
  }

  /**
   * ���������������Ƿ��Ѿ�ѯ����ɡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-21 ����02:17:34
   */
  public boolean isComplete() {
    return ArrayUtils.isEmpty(this.pqinfo);
  }

  /**
   * �����������������ݹ���ʱ����Ĳ�������ѯ�۴���
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author zhaoyha
   * @time 2010-5-27 ����09:17:04
   */
  public void process() {
    Map<String, GoodsEstVO> voMap = CirVOUtil.createKeyVOMap(this.heads);
    if (null == this.strategy) {
      return;
    }
    EstPriceQryResltData[] qryReslt = this.strategy.queryPrice(this.pqinfo);
    this.setPrice(voMap, qryReslt);
    this.pqinfo = this.getNoPricePqInfo(this.pqinfo, qryReslt);
  }

  /**
   * ��������������������Ҫʹ�õ�ѯ�۲��ԡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param strategy
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-21 ����02:15:55
   */
  public void setStrategy(AbstractEstPriceQueryStrategy strategy) {
    this.strategy = strategy;
  }

  /**
   * ���������������õ���Ҫ����ѯ��(�ϴ�δѯ��)��ѯ����ϢVO��
   * <p>
   * <b>����˵��</b>
   * 
   * @param oldPqinfo
   *          �ϴ�ѯ����ϢVO
   * @param qryReslt
   *          �ϴ�ѯ�۽��VO
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-5-27 ����09:17:39
   */
  private IEstPriceQueryInfoProvide[] getNoPricePqInfo(
      IEstPriceQueryInfoProvide[] oldPqinfo, EstPriceQryResltData[] qryReslt) {
    List<IEstPriceQueryInfoProvide> newPqinfo =
        new ArrayList<IEstPriceQueryInfoProvide>();
    for (int i = 0; i < qryReslt.length; i++) {
      EstPriceQryResltData reslt = qryReslt[i];
      if (UFDouble.ZERO_DBL.equals(MathTool.nvl(reslt.getOrigPrice()))
          && UFDouble.ZERO_DBL.equals(MathTool.nvl(reslt.getOrigTaxPrice()))
          && UFDouble.ZERO_DBL.equals(MathTool.nvl(reslt.getPrice()))
          && UFDouble.ZERO_DBL.equals(MathTool.nvl(reslt.getTaxPrice()))) {
        newPqinfo.add(oldPqinfo[i]);
      }
    }
    IEstPriceQueryInfoProvide[] newPqinfoAry = null;
    if (0 < newPqinfo.size()) {
      newPqinfoAry =
          new ListToArrayTool<IEstPriceQueryInfoProvide>()
              .convertToArray(newPqinfo);
    }
    return newPqinfoAry;
  }

  /**
   * ��������������������˰�ۡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   *          �ݹ�VO
   * @param reslt
   *          ѯ�۽��
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-5-27 ����09:21:14
   */
  private void setNoTaxPrice(GoodsEstVO vo, EstPriceQryResltData reslt) {
    UFDouble origprice = MathTool.nvl(reslt.getOrigPrice());
    UFDouble price = MathTool.nvl(reslt.getPrice());
    String chgKey = null;
    if (!UFDouble.ZERO_DBL.equals(origprice)) {
      vo.setNestoprice(origprice);
      chgKey = GoodsEstVO.NESTOPRICE;
    }
    else {
      if (!UFDouble.ZERO_DBL.equals(price)) {
        vo.setNestprice(price);
        chgKey = GoodsEstVO.NESTPRICE;
      }
    }
    // ����˰�ʺͿ�˰���ѯ��Ӧ�̼�Ŀ��ʱ����
    if (reslt.getTaxRate() != null) {
      vo.setNesttaxrate(reslt.getTaxRate());
    }
    if (reslt.getTaxTypeFlag() != null) {
      vo.setFesttaxtype(reslt.getTaxTypeFlag());
    }
    // wuxla V61 ѯ��Ӧ�̼�Ŀ���õ��Ĳ��ɵֿ�˰�ʣ������ݹ��Ĳ��ɵֿ�˰��
    if (reslt.getNosubTaxRate() != null) {
      vo.setNestnosubtaxrate(reslt.getNosubTaxRate());
    }
    if (null == chgKey) {
      return;
    }
    EstRelationCalcUtil util =
        new EstRelationCalcUtil(vo.getPk_group(), new EstRelationCalcItem());
    util.calcVO(vo, chgKey, PricePriority.PRICE_PRIOR_TO_TAXPRICE);
  }

  /**
   * ����������������һ��ѯ��֮���Ѿ�ѯ���ļ۸����õ��ݹ�VO�С�
   * <p>
   * <b>����˵��</b>
   * 
   * @param voMap
   *          �ݹ�VO��map
   * @param qryReslt
   *          ��һ��ѯ�۵Ľ��
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-5-27 ����09:19:39
   */
  private void setPrice(Map<String, GoodsEstVO> voMap,
      EstPriceQryResltData[] qryReslt) {
    for (int i = 0; i < qryReslt.length; i++) {
      EstPriceQryResltData reslt = qryReslt[i];
      GoodsEstVO vo = voMap.get(reslt.getBID());
      // ȫ����Ϣȡ��Դ��������Ϣ���������¼���
      if (!reslt.isNeedCalc()) {
        vo.setNestprice(reslt.getPrice());
        vo.setNesttaxprice(reslt.getTaxPrice());
        vo.setNestmny(reslt.getMny());
        vo.setNesttaxmny(reslt.getTax());
        vo.setNesttotalmny(reslt.getTotalmny());
        vo.setNestoprice(reslt.getOrigPrice());
        vo.setNestotaxprice(reslt.getOrigTaxPrice());
        vo.setNestomny(reslt.getOrigmny());
        // wuxla V61 ȥ��ԭ��˰��
        // vo.setNestotaxmny(reslt.getOrigtax());
        vo.setNestototalmny(reslt.getOrigtotalmny());

        // wuxla V61 ��VAT��Ϣ������
        vo.setNestnosubtax(reslt.getNosubtax());
        vo.setNestcalcostmny(reslt.getCalcostmny());
        vo.setNestcaltaxmny(reslt.getCaltaxmny());
      }
      else {
        PricePriority pp = EstVOUtil.getPO28(vo.getPk_purchaseOrg());
        if (PricePriority.TAXPRICE_PRIOR_TO_PRICE == pp) {
          this.setTaxPrice(vo, reslt);
        }
        else {
          this.setNoTaxPrice(vo, reslt);
        }
      }
    }
  }

  /**
   * �����������������ú�˰�ۡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   *          �ݹ�VO
   * @param reslt
   *          ѯ�۽��
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-5-27 ����09:21:29
   */
  private void setTaxPrice(GoodsEstVO vo, EstPriceQryResltData reslt) {
    UFDouble origTaxprice = MathTool.nvl(reslt.getOrigTaxPrice());
    UFDouble taxprice = MathTool.nvl(reslt.getTaxPrice());
    UFDouble origprice = MathTool.nvl(reslt.getOrigPrice());
    UFDouble price = MathTool.nvl(reslt.getPrice());
    String chgKey = null;
    if (UFDouble.ZERO_DBL.equals(origTaxprice)) {
      if (UFDouble.ZERO_DBL.equals(taxprice)) {
        if (UFDouble.ZERO_DBL.equals(origprice)) {
          vo.setNestprice(price);
          chgKey = GoodsEstVO.NESTPRICE;
        }
        else {
          vo.setNestoprice(origprice);
          chgKey = GoodsEstVO.NESTOPRICE;
        }
      }
      else {
        vo.setNesttaxprice(taxprice);
        chgKey = GoodsEstVO.NESTTAXPRICE;
      }
    }
    else {
      vo.setNestotaxprice(origTaxprice);
      chgKey = GoodsEstVO.NESTOTAXPRICE;
    }
    // ����˰�ʺͿ�˰���ѯ��Ӧ�̼�Ŀ��ʱ����
    if (reslt.getTaxRate() != null) {
      vo.setNesttaxrate(reslt.getTaxRate());
    }
    if (reslt.getTaxTypeFlag() != null) {
      vo.setFesttaxtype(reslt.getTaxTypeFlag());
    }
    // wuxla V61 ѯ��Ӧ�̼�Ŀ���õ��Ĳ��ɵֿ�˰�ʣ������ݹ��Ĳ��ɵֿ�˰��
    if (reslt.getNosubTaxRate() != null) {
      vo.setNestnosubtaxrate(reslt.getNosubTaxRate());
    }
    if (null == chgKey) {
      return;
    }
    EstRelationCalcUtil util =
        new EstRelationCalcUtil(vo.getPk_group(), new EstRelationCalcItem());
    util.calcVO(vo, chgKey, PricePriority.TAXPRICE_PRIOR_TO_PRICE);
  }
}
