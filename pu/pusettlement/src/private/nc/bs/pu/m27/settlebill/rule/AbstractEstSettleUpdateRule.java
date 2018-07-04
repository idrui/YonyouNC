package nc.bs.pu.m27.settlebill.rule;

import java.util.List;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.tool.ViewConcurrentTool;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * ���Ļ��ܡ��ɹ����ڲɹ��ݹ��Ľ�����д����
 * 
 * @since 6.0
 * @version 2011-2-11 ����11:13:08
 * @author zhaoyha
 */
public abstract class AbstractEstSettleUpdateRule<E extends GoodsEstVO> extends
    AbstractSettleUpdateRule {
  private Class<E> clazz;

  protected WritebackPoint wbp;

  public AbstractEstSettleUpdateRule(WritebackPoint wbp, Class<E> goodsEstClazz) {
    this.wbp = wbp;
    this.clazz = goodsEstClazz;
  }

  /**
   * ��VO�����������ִ����Ϣ��ļ��<br>
   * ������ɶ��ۼƽ��㡢�ݹ��س����Ϣ�ļ��
   * 
   * @param gevo
   */
  protected void check(E gevo) {
    UFDouble naccwashmny = gevo.getNaccestcostwashmny();
    // UFDouble nestmny = gevo.getNestmny();
    // wuxla V61 ʹ�üǳɱ����
    UFDouble nestcalcostmny = gevo.getNestcalcostmny();
    UFDouble naccstlnum = gevo.getNaccumsettlenum();
    UFDouble ninnum = gevo.getNinnum();
    UFDouble estnum = gevo.getNestnum();
    UFDouble acceststlnum = gevo.getNaccestcoststtlnum();
    String code = gevo.getVbillcode();
    if (MathTool.absCompareTo(naccstlnum, ninnum) > 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004060_0", "04004060-0210", null, new String[] {
            code
          })/* [{0}]�ۼƽ���������������⣯������������ */);
    }
    if (MathTool.isDiffSign(naccstlnum, ninnum)) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004060_0", "04004060-0211", null, new String[] {
            code
          })/* [{0}]�ۼƽ�����������������⣯������������һ�£� */);
    }
    // if (UFBoolean.TRUE.equals(gevo.getBsettlefinish())
    // && (!MathTool.equals(naccwashmny, nestmny) || !MathTool.equals(
    // acceststlnum, estnum))) {
    // ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
    // .getStrByID("4004060_0", "04004060-0212", null, new String[] {
    // code
    // })/* [{0}]�Ѿ�������ϣ��ݹ���������δȫ�������� */);
    // }
    // wuxla v61 �ǳɱ����
    if (UFBoolean.TRUE.equals(gevo.getBsettlefinish())
        && (!MathTool.equals(naccwashmny, nestcalcostmny) || !MathTool.equals(
            acceststlnum, estnum))) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004060_0", "04004060-0212", null, new String[] {
            code
          })/* [{0}]�Ѿ�������ϣ��ݹ���������δȫ�������� */);
    }
    if (MathTool.isZero(naccstlnum)
        && (!MathTool.isZero(naccwashmny) || !MathTool.isZero(acceststlnum))) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004060_0", "04004060-0213", null, new String[] {
            code
          })/* [{0}]�����Ѿ�ȫ��ȡ���������г����ݹ���������δȡ���� */);
    }
    // if (MathTool.absCompareTo(naccwashmny, nestmny) > 0
    // || MathTool.absCompareTo(acceststlnum, estnum) > 0) {
    // ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
    // .getStrByID("4004060_0", "04004060-0214", null, new String[] {
    // code
    // })/* [{0}]�ۼƻس��ݹ������ͽ��ܴ����ݹ���������� */);
    // }
    // wuxla v61 �ǳɱ����
    if (MathTool.absCompareTo(naccwashmny, nestcalcostmny) > 0
        || MathTool.absCompareTo(acceststlnum, estnum) > 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004060_0", "04004060-0214", null, new String[] {
            code
          })/* [{0}]�ۼƻس��ݹ������ͽ��ܴ����ݹ���������� */);
    }
  }

  /**
   * ɾ�����㵥��д�ļ��
   * 
   * @param gevo
   * @param list
   */
  protected void checkDel(GoodsEstVO gevo, List<SettleBillItemVO> list) {

    if (MathTool.isZero(gevo.getNestnum())) {
      return;
    }
    String code = gevo.getVbillcode();
    for (SettleBillItemVO sbitem : list) {
      if (UFBoolean.FALSE.equals(sbitem.getBwashest())
          && !EnumMatchRowType.StockFeeSettle.toInteger().equals(// �����ý��㲻У��
              sbitem.getFrowtype())) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4004060_0", "04004060-0215", null, new String[] {
              code
            })/* [{0}]���������ݹ����ݣ�����ɾ���˽��㵥�� */);
      }
    }
  }

  /**
   * ����VO��һЩ������Ϣ <br>
   * ������ɶ����ۼƽ��㡢�ݹ��س���Ϣ�Ĵ���
   * 
   * @param gevo
   * @param list
   */
  protected void setSettleInfo(E gevo, List<SettleBillItemVO> list) {
    UFDouble accstlnum = gevo.getNaccumsettlenum();
    UFDouble accwashmny = gevo.getNaccestcostwashmny();
    UFDouble acceststlnum = gevo.getNaccestcoststtlnum();
    UFDouble accpreeststlmny = gevo.getNaccpreeststtlmny();
    UFDouble estnum = gevo.getNestnum();
    UFDouble accgoodssettlemny = gevo.getNaccgoodssettlemny();
    UFDouble accsettlemny = gevo.getNaccsettlemny();
    UFDouble accfeesettlemny = gevo.getNaccfeesettlemny();
    // mengjian �ۼƵ������������
    UFDouble accadjustmny = gevo.getNaccadjustmny();

    for (SettleBillItemVO sbitem : list) {
      UFDouble stlnum =
          WritebackPoint.SAVE == this.wbp ? sbitem.getNsettlenum() : MathTool
              .oppose(sbitem.getNsettlenum());
      UFDouble washmny =
          WritebackPoint.SAVE == this.wbp ? sbitem.getNclashestmoney()
              : MathTool.oppose(sbitem.getNclashestmoney());
      UFDouble stlmny =
          WritebackPoint.SAVE == this.wbp ? sbitem.getNgoodsmoney() : MathTool
              .oppose(sbitem.getNgoodsmoney());
      UFDouble goodssettlemny =
          WritebackPoint.SAVE == this.wbp ? sbitem.getNgoodsmoney() : MathTool
              .oppose(sbitem.getNgoodsmoney());
      UFDouble settlemny =
          WritebackPoint.SAVE == this.wbp ? sbitem.getNmoney() : MathTool
              .oppose(sbitem.getNmoney());
      UFDouble feesettlemny =
          WritebackPoint.SAVE == this.wbp ? this.getTotalFeemny(sbitem)
              : MathTool.oppose(this.getTotalFeemny(sbitem));
      UFDouble adjustsettlemny =
          WritebackPoint.SAVE == this.wbp ? sbitem.getNadjustmny() : MathTool
              .oppose(sbitem.getNadjustmny());
      accstlnum = MathTool.add(accstlnum, stlnum);
      accwashmny = MathTool.add(accwashmny, washmny);
      accgoodssettlemny = MathTool.add(accgoodssettlemny, goodssettlemny);
      accsettlemny = MathTool.add(accsettlemny, settlemny);
      accfeesettlemny = MathTool.add(accfeesettlemny, feesettlemny);
      accadjustmny = MathTool.add(accadjustmny, adjustsettlemny);
      if (MathTool.isZero(estnum)) {
        accpreeststlmny = MathTool.add(accpreeststlmny, stlmny);
      }
      else {
        acceststlnum = MathTool.add(acceststlnum, stlnum);
      }
    }
    gevo.setNaccumsettlenum(accstlnum);
    gevo.setNaccestcostwashmny(accwashmny);
    gevo.setNaccestcoststtlnum(acceststlnum);
    gevo.setNaccpreeststtlmny(accpreeststlmny);
    gevo.setNaccfeesettlemny(accfeesettlemny);
    gevo.setNaccgoodssettlemny(accgoodssettlemny);
    gevo.setNaccsettlemny(accsettlemny);
    gevo.setNaccadjustmny(accadjustmny);
    if (MathTool.compareTo(accstlnum, gevo.getNinnum()) == 0) {
      gevo.setBsettlefinish(UFBoolean.TRUE);
    }
    else {
      gevo.setBsettlefinish(UFBoolean.FALSE);
    }
  }

  /**
   * ִ�����ݿ�ĸ���
   * 
   * @param gevos
   */
  protected abstract void updateDB(E[] gevos);

  @Override
  protected void updateSettleInfo(String[] stockBIDs,
      MapList<String, SettleBillItemVO> sbidStlMap) {

    ViewQuery<E> vq = new ViewQuery<E>(this.clazz);
    E[] gevos = vq.query(stockBIDs);

    // ����ܶ�ط����ã����õĵط�û�м��������ֲ�����
    // ����ɾ�����㵥�ͷ��ý��㡣
    new ViewConcurrentTool().lock(gevos);

    for (E gevo : gevos) {
      // ��ɾ�����������飬���ݹ�����ɾ����ǰ���㵥
      if (WritebackPoint.DELETE == this.wbp) {
        this.checkDel(gevo, sbidStlMap.get(gevo.getPk_stockps_b()));
      }
      this.setSettleInfo(gevo, sbidStlMap.get(gevo.getPk_stockps_b()));// ����ÿһ�����������Ϣ
      this.check(gevo);// ����д�Ľ�����Ϣ�Ƿ���ȷ
    }
    this.updateDB(gevos);// �־û������ݿ�
  }

}
