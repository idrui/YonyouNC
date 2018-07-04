package nc.bs.pu.est.m45.rule.settle;

import java.util.List;

import nc.bs.ml.NCLangResOnserver;
import nc.bs.pu.m27.settlebill.rule.AbstractEstSettleUpdateRule;
import nc.bs.pu.m27.settlebill.rule.WritebackPoint;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.vo.pu.est.entity.m45.PurchaseInEstHeaderVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4201.enumeration.EnumToAPFlag;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 
 * @description
 * ����ʱ��д�����²ɹ�����������Ϣ-����
 * @scene
 * ɾ�����㵥,���ý���,������ϱ�����㵥
 * @param
 * wbp  ��дʱ���ö����
 *
 * @since 6.3
 * @version 2014-10-22 ����4:06:57
 * @author zhangshqb
 */
public class PurchsInSettleUpdateRule extends
    AbstractEstSettleUpdateRule<PurchaseInEstHeaderVO> {

  public PurchsInSettleUpdateRule(WritebackPoint wbp) {
    super(wbp, PurchaseInEstHeaderVO.class);
  }

  private void checkConfirm(PurchaseInEstHeaderVO gevo) {
    UFDouble accadjmny = gevo.getNacctocostadjstmny();
    UFDouble accadjapmny = gevo.getNacctoapadjstotmny();
    // wuxla v61
    UFDouble ncalcostmny = gevo.getNcalcostmny();
    // UFDouble mny = gevo.getNmny();
    UFDouble origtaxmny = gevo.getNorigtaxmny();
    UFDouble naccstlnum = gevo.getNaccumsettlenum();
    String code = gevo.getVbillcode();
    // ������ϣ�ȷ�ϳɱ��Ƚ�ȷ�Ͻ�ȷ��Ӧ���Ƚ�Ӧ����˰�ϼ�
    if (UFBoolean.TRUE.equals(gevo.getBsettlefinish())
        && (EnumToIAFlag.ConfirmToIA.toInteger().equals(gevo.getFtoiaflag())
            && !MathTool.equals(accadjmny, ncalcostmny) || EnumToAPFlag.ConfirmToAP
            .toInteger().equals(gevo.getFtoapflag())
            && !MathTool.equals(accadjapmny, origtaxmny))) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004060_0", "04004060-0206", null, new String[] {
            code
          })/* [{0}]�Ѿ�������ϣ�ȷ�ϳɱ���Ӧ����˰�ϼ�δȫ�������� */);
    }
    if (MathTool.isZero(naccstlnum)
        && (!MathTool.isZero(accadjmny) || !MathTool.isZero(accadjapmny))) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004060_0", "04004060-0207", null, new String[] {
            code
          })/* [{0}]�����Ѿ�ȫ��ȡ���������е���ȷ�ϳɱ���Ӧ����˰�ϼ�δȡ���� */);
    }
    if (MathTool.absCompareTo(accadjmny, ncalcostmny) > 0
        || MathTool.absCompareTo(accadjapmny, origtaxmny) > 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004060_0", "04004060-0208", null, new String[] {
            code
          })/* [{0}]�ۼƵ���ȷ�ϳɱ��Ľ�Ӧ����˰�ϼƲ��ܴ���ȷ�ϳɱ��Ľ�Ӧ����˰�ϼƣ� */);
    }

  }

  /** ����ȷ��Ӧ��ԭ�Ҽ�˰�ϼ� **/
  private UFDouble getSettleAdjApMny(SettleBillItemVO item) {
    return WritebackPoint.SAVE == this.wbp ? item.getNoppoconfmapmny()
        : MathTool.oppose(item.getNoppoconfmapmny());
  }

  /** ����ȷ�Ͻ�� **/
  private UFDouble getSettleAdjustMny(SettleBillItemVO item) {
    return WritebackPoint.SAVE == this.wbp ? item.getNoppoconfmmoney()
        : MathTool.oppose(item.getNoppoconfmmoney());
  }

  @Override
  protected void check(PurchaseInEstHeaderVO gevo) {
    super.check(gevo);
    // ִ�вɹ�������д��ȷ�ϳɱ���Ӧ�����
    if (EnumToIAFlag.ConfirmToIA.value().equals(gevo.getFtoiaflag())
        || EnumToAPFlag.ConfirmToAP.value().equals(gevo.getFtoapflag())) {
      this.checkConfirm(gevo);
    }

  }

  @Override
  protected String getStockBIDField() {
    return SettleBillItemVO.PK_PURCHASEIN_B;
  }

  @Override
  protected void setSettleInfo(PurchaseInEstHeaderVO gevo,
      List<SettleBillItemVO> list) {
    super.setSettleInfo(gevo, list);
    for (SettleBillItemVO item : list) {
      UFDouble adjustmny = this.getSettleAdjustMny(item);
      UFDouble accajst = gevo.getNacctocostadjstmny();
      UFDouble adjustApMny = this.getSettleAdjApMny(item);
      UFDouble accAjsApMny = gevo.getNacctoapadjstotmny();
      // �ۼƵ���ȷ�Ͻ��
      gevo.setNacctocostadjstmny(MathTool.add(accajst, adjustmny));
      // �ۼƵ���ȷ��Ӧ��ԭ�Ҽ�˰�ϼ�
      gevo.setNacctoapadjstotmny(MathTool.add(accAjsApMny, adjustApMny));
    }
  }

  @Override
  protected void updateDB(PurchaseInEstHeaderVO[] vos) {

    ViewUpdate<PurchaseInEstHeaderVO> vupdate =
        new ViewUpdate<PurchaseInEstHeaderVO>();
    String[] names =
        new String[] {
          PurchaseinFIItemVO.BSETTLEFINISH, PurchaseinFIItemVO.NACCUMSETTLENUM,
          PurchaseinFIItemVO.NACCESTCOSTSTTLNUM,
          PurchaseinFIItemVO.NACCPREESTSTTLMNY,
          PurchaseinFIItemVO.NACCESTCOSTWASHMNY,
          PurchaseinFIItemVO.NACCTOCOSTADJSTMNY,
          PurchaseinFIItemVO.NACCTOAPADJSTOTMNY,
          PurchaseinFIItemVO.NACCSETTLEMNY,
          PurchaseinFIItemVO.NACCGOODSSETTLEMNY,
          PurchaseinFIItemVO.NACCFEESETTLEMNY, PurchaseinFIItemVO.NACCADJUSTMNY
        };
    vupdate.update(vos, PurchaseinFIItemVO.class, names);
  }

}
