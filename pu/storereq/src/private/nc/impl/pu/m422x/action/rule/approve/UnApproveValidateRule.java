/**
 * $�ļ�˵��$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-26 ����07:17:36
 */
package nc.impl.pu.m422x.action.rule.approve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.m422x.enumeration.EnumBillStatus;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 
 * @description
 *            �����������뵥ȡ������ʱ������״̬���
 * @scene
 *      �����������뵥ȡ������
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-7-26 ����07:17:36
 * @author wuxla
 */
public class UnApproveValidateRule implements IRule<StoreReqAppVO> {

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(StoreReqAppVO[] vos) {
    this.statusCheck(vos);
  }

  private void statusCheck(StoreReqAppVO[] vos) {
    for (StoreReqAppVO vo : vos) {
      // ״̬������̬�ĵ��ݲ���ȡ������Ҳ�����ջ�
      if (vo.getHVO().getFbillstatus().equals(POEnumBillStatus.FREE.value())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004010_0", "04004010-0010")/*
                                                                     * @res
                                                                     * "����״̬����ȷ�����ܲ�����"
                                                                     */);
      }

      // �����ر���������
      if (vo.getHVO().getFbillstatus().equals(EnumBillStatus.CLOSE.toInteger())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0158")/*
                                                                     * @res
                                                                     * "���������չرգ����ܲ�����\n"
                                                                     */);
      }

      // �ֹ�ȡ��ʱ��Դ��ά�޼ƻ��ĵ��ݲ���ȡ������
      // ά�޼ƻ��������ʱ�����ƴ�У��
//      if (!ForeignForPUConstant.M4B32.equals(StorereqContext.getInstance()
//          .getSession("SrcAction"))) {
//        StoreReqAppItemVO[] items = vo.getBVO();
//        if (items.length > 0
//            && items[0].getCsourcetypecode() != null
//            && items[0].getCsourcetypecode().equalsIgnoreCase(
//                PuRefBillTypeIdEnum.M4B32.getBillTypeId())) {
//          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
//              .getNCLangRes().getStrByID("4004010_0", "04004010-0030")/*
//                                                                       * @res
//                                                                       * "��Դά�޼ƻ��������������뵥����������\n"
//                                                                       */);
//        }
//      }
//      else {
//        StorereqContext.getInstance().setSession("SrcAction", null);
//      }

      for (StoreReqAppItemVO itemVo : vo.getBVO()) {
        // ��ƽ��ĵ��ݲ���ȡ������
        if(itemVo.getBendgather().booleanValue()){
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4004010_0", "04004010-0036")/*
                                                                       * @res
                                                                       * "��ƽ��ĵ��ݲ���ȡ��������"
                                                                       */);
        }
        if (MathTool.greaterThan(itemVo.getNaccumbuyreqnum(), UFDouble.ZERO_DBL)
            || MathTool.greaterThan(itemVo.getNaccuoutnum(), UFDouble.ZERO_DBL)
            || MathTool.greaterThan(itemVo.getNaccuoutreqnum(),
                UFDouble.ZERO_DBL)
            || MathTool.greaterThan(itemVo.getNaccumbuyreqnum(),
                UFDouble.ZERO_DBL)
            || MathTool.greaterThan(itemVo.getNnetnum(), UFDouble.ZERO_DBL)) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4004010_0", "04004010-0025")/*
                                                                       * @res
                                                                       * "���������ε��ݣ����ܲ�����"
                                                                       */);
          }
        // ��һ�йر���������
        if (itemVo.getBclose().booleanValue()) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4004030_0", "04004030-0157")/*
                                                                       * @res
                                                                       * "���ݴ��ڹرյ��У����ܲ�����\n"
                                                                       */);
        }

      }
    }
  }
}
