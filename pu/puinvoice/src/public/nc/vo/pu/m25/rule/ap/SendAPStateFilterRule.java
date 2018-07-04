/**
 *
 */
package nc.vo.pu.m25.rule.ap;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * ��Ʊ״̬���˹����˵��Ѿ���Ӧ�����ڳ�������ȷ�Ʊ
 * ����Ǳ�־�Ǽ�飬�����쳣�������߹���
 * @scene
 * ��Ӧ��,����(ƥ��)���Զ���Ӧ��
 * @param
 * sCheck ����Ǳ�־��true�������쳣�������߹���
 *
 * @since 6.3
 * @version 2014-10-22 ����3:39:57
 * @author zhangshqb
 */
public class SendAPStateFilterRule implements IFilterRule<InvoiceVO> {

  private boolean isCheck = false;

  /**
   * SendAPStateFilterRule �Ĺ�����
   *
   * @param isCheck
   *          ����Ǳ�־��true�������쳣�������߹���
   */
  public SendAPStateFilterRule(boolean isCheck) {
    this.isCheck = isCheck;
  }

  @Override
  public InvoiceVO[] process(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return vos;
    }
    List<InvoiceVO> filterVos = new ArrayList<InvoiceVO>();
    for (InvoiceVO vo : vos) {
      int state = vo.getParentVO().getFbillstatus().intValue();
      if (POEnumBillStatus.APPROVE.toInt() != state) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0046")/*@res "δ����ͨ���ķ�Ʊ����ȷ��Ӧ����"*/);
      }
      UFBoolean sendflag = vo.getParentVO().getBapflag();
      if (this.isCheck && ValueUtils.getBoolean(sendflag)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0047")/*@res "�Ѿ�ȷ�Ϲ�Ӧ�������ظ�ȷ��!"*/);
      }
      else if (ValueUtils.getBoolean(sendflag)) {
        continue;
      }
      UFBoolean initFlag = vo.getParentVO().getBinitial();
      if (this.isCheck && ValueUtils.getBoolean(initFlag)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0048")/*@res "�ڳ���Ʊ���ܴ�Ӧ��!"*/);
      }
      else if (ValueUtils.getBoolean(initFlag)) {
        continue;
      }
      if (this.isCheck && this.isPostiveFrozen(vo)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0049")/*@res "���������Ʊ����ȷ��Ӧ����"*/);
      }
      else if (this.isPostiveFrozen(vo)) {
        continue;
      }
      filterVos.add(vo);// ���м�鶼ͨ����ɴ�Ӧ��
    }
    return filterVos.toArray(new InvoiceVO[filterVos.size()]);
  }

  /** �жϷ�Ʊ�Ƿ��Ѷ��������Ʊ **/
  private boolean isPostiveFrozen(InvoiceVO vo) {
    boolean frozen = ValueUtils.getBoolean(vo.getParentVO().getBfrozen());
    if (!frozen) {
      return false;
    }
    for (InvoiceItemVO item : vo.getChildrenVO()) {
      UFDouble mny = item.getNorigmny();
      if (MathTool.compareTo(mny, UFDouble.ZERO_DBL) > 0) {
        return true;
      }
    }
    return false;
  }

}