package nc.bs.pu.it;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.md.model.impl.MDEnum;
import nc.vo.pu.costfactor.entity.CostfactorItemVO;
import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pu.costfactor.enumeration.ApportionMode;
import nc.vo.pu.est.entity.fee.FeeMnyDivideVO;
import nc.vo.pu.est.entity.fee.FeeMnyVO;
import nc.vo.pu.est.rule.feedivide.DivideByValueFactory;
import nc.vo.pu.est.rule.feedivide.DivideByValueFactory.AbstractByValueGen;
import nc.vo.pu.est.rule.feedivide.DivideByValueFactory.ByMnyGen;
import nc.vo.pu.est.rule.feedivide.DivideByValueFactory.ByNumGen;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pu.pub.util.ListUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����ڷ��á�������Ʊ��̯
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.31
 * @since 6.31
 * @author liangchen1
 * @time 2013-11-19 ����01:12:53
 */
public class FeeDivideRuleForIT {
  private CostfactorViewVO[] cfviews;

  private FeeMnyDivideVO[] divideVos;

  private String pk_group;

  /**
   * FeeDivideRule �Ĺ�����
   * 
   * @param pk_group ����
   * @param cfviews �ɱ�Ҫ����ͼ
   * @param divideVos ��̯���ݼ���̯���
   */
  public FeeDivideRuleForIT(String pk_group, CostfactorViewVO[] cfviews,
      FeeMnyDivideVO[] divideVos) {
    this.pk_group = pk_group;
    this.cfviews = cfviews;
    this.divideVos = divideVos;
  }

  /**
   * ����������������ʼ�Ը����ķ��ý��з�̯��
   * <p>
   * <b>����˵��</b>
   * 
   * @param feeMnyVos Ҫ��̯�ķ���VO
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-25 ����09:54:22
   */
  public FeeMnyDivideVO[] divide(FeeMnyVO[] feeMnyVos) {
    Map<String, ArrayList<CostfactorViewVO>> cmap = null;
    if (!ArrayUtils.isEmpty(this.cfviews)) {
      cmap =
          CirVOUtil.getFieldVOList(this.cfviews,
              CostfactorItemVO.PK_SRCMATERIAL);
    }
    for (FeeMnyVO fmvo : feeMnyVos) {
      this.genByValue(fmvo.getFeeoid(), cmap); // ����ÿ��VO�ķ�̯����ֵ
      this.divideOneFee(fmvo); // ��ʼ��̯
    }
    return this.divideVos;
  }

  /**
   * ������Ʊ���÷�̯
   * 
   * @param feeMnyVos
   * @return
   */

  public FeeMnyDivideVO[] divideForAjust(FeeMnyVO[] feeMnyVos) {
    for (FeeMnyVO fmvo : feeMnyVos) {
      // ����ÿ��VO�ķ�̯����ֵ
      new ByNumGen(this.divideVos, this.pk_group).genByValue();
      this.divideOneFee(fmvo); // ��ʼ��̯
    }
    return this.divideVos;
  }

  /**
   * ��������������ר���ṩ���ۿ��෢Ʊ���з�̯�����ս����з�̯��
   * <p>
   * <b>����˵��</b>
   * 
   * @param feeMnyVos
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-9-1 ����04:38:33
   */
  public FeeMnyDivideVO[] divideForDiscount(FeeMnyVO[] feeMnyVos) {
    for (FeeMnyVO fmvo : feeMnyVos) {
      // ����ÿ��VO�ķ�̯����ֵ
      new ByMnyGen(this.divideVos, this.pk_group).genByValue();
      this.divideOneFee(fmvo); // ��ʼ��̯
    }
    return this.divideVos;
  }

  private void divideOneFee(FeeMnyVO fmvo) {
    UFDouble feeMny = fmvo.getMny();
    if (0 == MathTool.compareTo(feeMny, UFDouble.ZERO_DBL)) {
      return;
    }
    /**** �õ��ܷ�̯������ֵ ****/
    UFDouble allByVal = UFDouble.ZERO_DBL;
    for (FeeMnyDivideVO divVo : this.divideVos) {
      allByVal = MathTool.add(allByVal, divVo.getByvalue());
    }
    if (0 == MathTool.compareTo(UFDouble.ZERO_DBL, allByVal)) {
      return;// ���ܵ���������ϱ�׼�����λ�ľ��Ⱥ������ϵĵ�λ������������Ȳ�һ��
    }
    /**** ���з�̯ ****/
    UFDouble dividedMny = UFDouble.ZERO_DBL;// �����ѷ�̯���
    for (int i = 0; i < this.divideVos.length; ++i) {
      FeeMnyDivideVO divVo = this.divideVos[i];
      UFDouble divVal = UFDouble.ZERO_DBL;
      if (i == this.divideVos.length - 1) {
        divVal = MathTool.sub(feeMny, dividedMny);
        divVo.setLastRow(true);// ��Ҫ����
      }
      else {
        UFDouble byVal = divVo.getByvalue();
        // ������ȡ��󾫶ȣ������������
        UFDouble ratio = byVal.div(allByVal, UFDouble.DEFAULT_POWER);
        divVal = ratio.multiply(feeMny, fmvo.getDigit());
        dividedMny = MathTool.add(dividedMny, divVal);
      }
      divVo.setDividedmny(MathTool.add(divVal, divVo.getDividedmny()));
      // ����ϼ���ص���Ϣ������������
      this.fillTotalValue(fmvo, divVo);

    }
  }

  /**
   * ����ϼ���ص���Ϣ������������
   * 
   * @param fmvo
   * @param feeMny
   * @param divVo
   */
  private void fillTotalValue(FeeMnyVO fmvo, FeeMnyDivideVO divVo) {
    // wuxla V61 �ǳɱ�����˰����Ҫ����
    divVo.setTotalCalcostMny(MathTool.add(fmvo.getEstCalcostmny(),
        divVo.getTotalCalcostMny()));
    divVo.setTotalCaltaxMny(MathTool.add(fmvo.getEstCaltaxmny(),
        divVo.getTotalCaltaxMny()));
    divVo.setTotalMny(MathTool.add(fmvo.getEstMny(), divVo.getTotalMny()));// �ܱ�����˰���
    divVo.setTotalTaxMny(MathTool.add(fmvo.getEstTaxmny(),
        divVo.getTotalTaxMny()));// �ܱ��Ҽ�˰�ϼ�
    divVo.setTotalOrigMny(MathTool.add(fmvo.getOrigEstMny(),
        divVo.getTotalOrigMny()));// ��ԭ����˰���
    divVo.setTotalOrigTaxMny(MathTool.add(fmvo.getMny(),
        divVo.getTotalOrigTaxMny()));// ��ԭ�Ҽ�˰�ϼ�
    // wuxla V61ȥ��
    // divVo.setTotalOrigTax(MathTool.add(fmvo.getOrigEstTax(),
    // divVo.getTotalOrigTax()));// ��ԭ��˰��
    divVo.setTotalTax(MathTool.add(fmvo.getEstTax(), divVo.getTotalTax()));// �ܱ���˰��
    divVo.setTotalNoSubTaxMny(MathTool.add(fmvo.getEstNoSubTaxmny(),
        divVo.getTotalNoSubTaxMny()));// ���ɵֿ�˰��
  }

  private void genByValue(String pk_srcmaterial,
      Map<String, ArrayList<CostfactorViewVO>> cmap) {
    if (MapUtils.isEmpty(cmap)) {
      return;
    }
    List<CostfactorViewVO> cvlist = cmap.get(pk_srcmaterial);
    if (ListUtil.isEmpty(cvlist)) {
      return;
    }
    Integer byModeint = cvlist.get(0).getFapportionmode();
    ApportionMode byMode = MDEnum.valueOf(ApportionMode.class, byModeint);
    AbstractByValueGen bvGen =
        DivideByValueFactory.getByValueGen(this.pk_group, this.divideVos,
            byMode);
    // ��̯��ֵ����
    bvGen.genByValue();
  }
}
