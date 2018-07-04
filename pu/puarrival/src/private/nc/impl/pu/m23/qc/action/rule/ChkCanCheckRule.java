package nc.impl.pu.m23.qc.action.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.qc.pub.util.QCSysParamUtil;

/**
 * @description
 *              ����Ƿ���Խ��б���
 * @scene
 *        �������ʼ�
 * @param ��
 * @since 6.3
 * @version 2010-6-1 ����10:05:56
 * @author hanbin
 */

public class ChkCanCheckRule implements IRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] vos) {
    if (vos == null || vos.length == 0) {
      return;
    }

    for (ArriveVO vo : vos) {
      // ������������Ƿ�����
      boolean isQcEnabled =
          SysInitGroupQuery.isQCEnabled()
              && UFBoolean.TRUE.equals(ValueUtils.getUFBoolean(QCSysParamUtil
                  .getINI01(vo.getHVO().getPk_org())));
      // ����Ƿ���������ʼ�
      this.chkOneVOEnable(vo, isQcEnabled);
    }
  }

  private boolean chkOneVOEnable(ArriveVO vo, boolean isQcEnabled) {
    if (!POEnumBillStatus.APPROVE.value().equals(vo.getHVO().getFbillstatus())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0133")/*
                                                                   * @res
                                                                   * "ֻ�����״̬�ĵ��ݲſ��Ա��죡"
                                                                   */);
    }

    if (vo.getHVO().getBisback().booleanValue()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0134")/*
                                                                   * @res
                                                                   * "�˻����ݲ�������Ա��죡"
                                                                   */);
    }

    ArriveItemVO[] items = vo.getBVO();
    // Map<String, MaterialStockVO> bidMrlMap =
    // ArrivePublicUtil.queryMaterialStockInfo(items);
    for (ArriveItemVO item : items) {
      if (isQcEnabled
          && MathTool.nvl(item.getNaccumstorenum()).doubleValue() > 0) {
        // String pk_arriveorder_b = item.getPk_arriveorder_b();
        // MaterialStockVO materialvo = bidMrlMap.get(pk_arriveorder_b);
        // boolean bstockbycheck =
        // ValueUtils.getBoolean(materialvo.getStockbycheck());
        // boolean chkfreeflag =
        // ValueUtils.getBoolean(materialvo.getChkfreeflag());
        // if (bstockbycheck && !chkfreeflag) {
        // UFDouble nelignum = item.getNelignum();
        // UFDouble nnotelignum = item.getNnotelignum();
        // UFDouble naccumletgoinnum = item.getNaccumletgoinnum();
        // UFDouble naccumstorenum = item.getNaccumstorenum();
        // String crowno = item.getCrowno();
        // if (MathTool.compareTo(MathTool.add(nelignum, nnotelignum),
        // UFDouble.ZERO_DBL) > 0
        // && MathTool.compareTo(
        // MathTool.sub(naccumstorenum, naccumletgoinnum),
        // UFDouble.ZERO_DBL) > 0) {
        //
        // ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
        // .getNCLangRes().getStrByID("4004040_0", "04004040-0035", null,
        // new String[] {
        // crowno
        // })/*
        // * @res
        // * "��{0}���Ѹ����ʼ챨������⣬��������飡"
        // */);
        // }
        // }
      }
      if (item.getBfaflag().booleanValue()) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004040_0", "04004040-0136")/*
                                                                     * @res
                                                                     * "�������ʲ���Ƭ���������죡"
                                                                     */);
      }

      UFDouble remainnum =
          MathTool.sub(item.getNnum(), item.getNaccumbacknum());
      if (MathTool.isZero(remainnum)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004040_0", "04004040-0199")/*
                                                                     * @res
                                                                     * "��ȫ�����ڵ������˻����޷����죡"
                                                                     */);
      }

      UFDouble nunchecknum =
          MathTool.sub(MathTool.sub(remainnum, item.getNaccumchecknum()),
              item.getNchecknum());
      if (MathTool.lessThan(nunchecknum, UFDouble.ZERO_DBL)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004040_0", "04004040-0203")/*
                                                                     * @res
                                                                     * "���������������˻���ʣ��������С���ۼƱ��������������α��죩"
                                                                     */);
      }
    }
    return true;
  }
}
