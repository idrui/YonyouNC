/**
 * $�ļ�˵��$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-8 ����08:41:17
 */
package nc.bs.pu.m4201.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>ȡ��ǩ��ʱ����Ƿ��ݹ���
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-8 ����08:41:17
 */
public class UnSignEstCheckRule implements IRule<PurchaseinFIVO> {

  @Override
  public void process(PurchaseinFIVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (PurchaseinFIVO vo : vos) {
      PurchaseinFIHeaderVO head = vo.getParentVO();
      UFBoolean autoFi = head.getBautotofi();
      // �Զ��ݹ�����Ҫ�Զ�ȡ�������ﲻ�ü��
      if (ValueUtils.getBoolean(autoFi)) {
        continue;
      }
      this.check(vo.getChildrenVO());
    }

  }

  private void check(PurchaseinFIItemVO[] items) {
    for (PurchaseinFIItemVO item : items) {
      UFDouble estnum = item.getNestnum();
      if (0 != MathTool.compareTo(estnum, UFDouble.ZERO_DBL)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0082")/*@res "��ⵥ�Ѿ��ݹ����ɱ���Ӧ��������ȡ��ǩ��!"*/);
      }
    }
  }

}