package nc.vo.pu.pub.rule.upgrade;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.pub.enumeration.EnumDiscounttaxtype;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

import org.apache.commons.lang.ArrayUtils;

/**
 * VAT���˰������
 * ֻ�����ڵ��ӱ��÷����������TaxtypeFlagUpgradeRule֮��
 * ��˰��� ����������=���ڲɹ��ķ�ʽ������
 * ���ɵֿ�˰��
 * ���ɵֿ�˰��
 * �Ƴɱ����
 * �ɹ����� �ɹ���Ʊ �ڳ��ݹ���
 * �ݹ�����--���Ｐ�����ݹ�
 * ���Ļ����ݹ�--���Ｐ�����ݹ�
 * ���ɵֿ�˰�ʡ����ɵֿ�˰�������Ϊ0��
 * �Ƴɱ�������Ϊ������˰��
 * 
 * @since 6.0
 * @version 2012-3-3 ����10:45:54
 * @author wuxla
 */
public class VATValueUpgrade<E extends IBill> implements IRule<E> {

  @Override
  public void process(E[] vos) {
    for (E vo : vos) {
      IBillMeta billMeta = vo.getMetaData();
      IVOMeta childMeta = billMeta.getChildren()[0];
      ISuperVO[] childvos = vo.getChildren(childMeta);
      if (ArrayUtils.isEmpty(childvos)) {
        continue;
      }
      for (ISuperVO childvo : childvos) {
        if (childvo == null) {
          continue;
        }
        this.setNnosubtaxrate(childvo);
        this.setNnosubtax(childvo);
        this.setNcalcostmny(childvo);
        this.setNcaltaxmny(childvo);
      }
    }
  }

  /**
   * �Ƴɱ����:����Ϊ������˰��
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>
   * </ul>
   */
  private void setNcalcostmny(ISuperVO childvo) {
    childvo.setAttributeValue(PuAttrNameEnum.ncalcostmny.name(),
        childvo.getAttributeValue(PuAttrNameEnum.nmny.name()));
  }

  /**
   * ��˰���:����������=���ڲɹ��ķ�ʽ������
   * Ĭ��ֵ����
   * �����˰���=Ӧ˰�ں����ӱ��Ҽ�˰�ϼ�Я��Ĭ��ֵ��
   * �����˰���=Ӧ˰��ӣ��ӱ�����˰���Я��Ĭ��ֵ��
   * �������ɹ����㷨�����
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>
   * </ul>
   * 
   * @param childvo
   */
  private void setNcaltaxmny(ISuperVO childvo) {
    Integer ftaxtypeflag =
        (Integer) childvo.getAttributeValue(PuAttrNameEnum.ftaxtypeflag.name());
    if (null == ftaxtypeflag) {
      return;
    }
    if (ftaxtypeflag.intValue() == EnumDiscounttaxtype.TAXOUT.toInt()) {
      childvo.setAttributeValue(PuAttrNameEnum.ncaltaxmny.name(),
          childvo.getAttributeValue(PuAttrNameEnum.nmny.name()));
      return;
    }
    childvo.setAttributeValue(PuAttrNameEnum.ncaltaxmny.name(),
        childvo.getAttributeValue(PuAttrNameEnum.ntaxmny.name()));
  }

  /**
   * <p>
   * ʹ�ó��������ɵֿ�˰��
   * <ul>
   * <li>
   * </ul>
   * 
   * @param childvo
   */
  private void setNnosubtax(ISuperVO childvo) {
    childvo.setAttributeValue(PuAttrNameEnum.nnosubtax.name(),
        UFDouble.ZERO_DBL);
  }

  /**
   * ���ɵֿ�˰��
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>
   * </ul>
   */
  private void setNnosubtaxrate(ISuperVO childvo) {
    childvo.setAttributeValue(PuAttrNameEnum.nnosubtaxrate.name(),
        UFDouble.ZERO_DBL);
  }

}
