/**
 * 
 */
package nc.bs.pu.m25.ap.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.entity.m45.PurchaseInEstHeaderVO;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.enumeration.InvoiceRowType;
import nc.vo.pu.m25.pub.VORelationCalculate;
import nc.vo.pu.m27.query.SettleBillInfo;
import nc.vo.pu.m4201.enumeration.EnumToAPFlag;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.ICBillType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * ȷ��Ӧ���������
 * @scene
 * ��Ӧ��
 * @param
 * estVOs �ɹ���ⵥ�ݹ�VO 
 * sttlInfoMap ��Ʊ����Ľ�����ϸ 
 * @since 6.3
 * @version 2014-10-22 ����3:46:19
 * @author zhangshqb
 */
public class MakeDiffConfirmAPRule implements IRule<InvoiceVO> {
  private PurchaseInEstVO[] confirmVos;

  private MapList<String, SettleBillInfo> sttlInfoMap;

  public MakeDiffConfirmAPRule(PurchaseInEstVO[] estVOs,
      MapList<String, SettleBillInfo> sttlInfoMap) {
    this.sttlInfoMap = sttlInfoMap;
    this.initConfirmPurFIVO(estVOs);
  }

  @Override
  public void process(InvoiceVO[] vos) {
    // ����Ҫ�����ֱ�ӷ���
    if (ArrayUtils.isEmpty(vos) || ArrayUtils.isEmpty(this.confirmVos)
        || (null == this.sttlInfoMap) || (0 == this.sttlInfoMap.size())) {
      return;
    }
    for (InvoiceVO vo : vos) {
      this.makeDiff(vo);
    }
  }

  /** �õ������뷢Ʊ�н���Ķ�����ⵥ�е�ȷ��Ӧ����˰�ϼƵĺ� **/
  private UFDouble getNoSpltConfirmAPMny(List<SettleBillInfo> sttlInfoList) {
    if (CollectionUtils.isEmpty(sttlInfoList)) {
      return UFDouble.ZERO_DBL;
    }
    UFDouble apmny = UFDouble.ZERO_DBL;
    Map<String, PurchaseInEstHeaderVO> confirmVOMap =
        AggVOUtil.createHeadMap(this.confirmVos);
    for (SettleBillInfo info : sttlInfoList) {
      if (!this.isConfirmedAP(confirmVOMap, info)) {
        continue;
      }
      UFDouble stlAjsApMny = info.getNoppconfirmapmny();
      apmny = MathTool.add(apmny, stlAjsApMny);
    }
    return apmny;
  }

  /** �Ѿ���������ϸ��ֵ��У�ֻȡ������ϸ�ϵ���ⵥȷ��Ӧ��ֵ **/
  private UFDouble getSpltConfirmAPMny(String pk_stockps_b,
      String pk_invoice_b, List<SettleBillInfo> sttlInfoList) {
    Map<String, PurchaseInEstHeaderVO> confirmVOMap =
        AggVOUtil.createHeadMap(this.confirmVos);
    for (SettleBillInfo info : sttlInfoList) {
      if (!this.isConfirmedAP(confirmVOMap, info)) {
        continue;
      }
      String stlStockb = info.getPk_stock_b();
      String stlInvcb = info.getPk_invoice_b();
      if (!pk_stockps_b.equals(stlStockb) || !pk_invoice_b.equals(stlInvcb)) {
        continue;
      }
      return info.getNoppconfirmapmny();
    }
    return UFDouble.ZERO_DBL;
  }

  private void initConfirmPurFIVO(PurchaseInEstVO[] estVOs) {
    if (ArrayUtils.isEmpty(estVOs)) {
      return;
    }
    List<PurchaseInEstVO> confirmVOList = new ArrayList<PurchaseInEstVO>();
    for (PurchaseInEstVO vo : estVOs) {
      GoodsEstVO head = vo.getParentVO();
      if (EnumToAPFlag.ConfirmToAP.value().equals(head.getFtoapflag())) {
        confirmVOList.add(vo);
      }
    }
    int size = confirmVOList.size();
    this.confirmVos = confirmVOList.toArray(new PurchaseInEstVO[size]);
  }

  /** �жϴ���������Ϣ����ⵥ�Ƿ�����ȷ��Ӧ�� **/
  private boolean isConfirmedAP(
      Map<String, PurchaseInEstHeaderVO> confirmVOMap, SettleBillInfo info) {
    String billtype = info.getVstockbilltype();
    // �ǲɹ��룬V60��һ�治֧��������浥��ֱ��ȷ��Ӧ��
    if (!ICBillType.PurchaseIn.getCode().equals(billtype)) {
      return false;
    }
    String pk_stockps_b = info.getPk_stock_b();
    // ������Ʊ�Գ�Ĳ�����
    if (StringUtil.isEmptyWithTrim(pk_stockps_b)) {
      return false;
    }
    // ����������Ϣ����ⵥδȷ�Ϲ�Ӧ��
    if (!confirmVOMap.containsKey(pk_stockps_b)) {
      return false;
    }
    return true;
  }

  private void makeDiff(InvoiceVO vo) {
    for (InvoiceItemVO item : vo.getChildrenVO()) {
      int rowtype = item.getFrowtype().intValue();
      if (InvoiceRowType.GOODS_ROW != rowtype) {
        continue;// �����в�����
      }
      String pk_invoice_b = item.getPk_invoice_b();
      List<SettleBillInfo> stlInfo = this.sttlInfoMap.get(pk_invoice_b);
      String pk_stockps_b = item.getPk_stockps_b();
      UFDouble stlStockApMny = UFDouble.ZERO_DBL;
      if (StringUtil.isEmptyWithTrim(pk_stockps_b)) {
        // δ��������ϸ��ֵ��д���
        stlStockApMny = this.getNoSpltConfirmAPMny(stlInfo);
      }
      else {
        // �Ѿ���������ϸ��ֵ��д���
        stlStockApMny =
            this.getSpltConfirmAPMny(pk_stockps_b, pk_invoice_b, stlInfo);
      }
      if (UFDouble.ZERO_DBL.equals(stlStockApMny)) {
        continue;// δȷ�Ϲ���������
      }
      UFDouble invcMny = item.getNorigtaxmny();
      // ȷ���뷢Ʊ��ȣ�����Ϊ�㣬�����������˵�
      UFDouble diffMny = MathTool.sub(invcMny, stlStockApMny);
      this.updateInvcOrigTaxMny(vo.getParentVO(), item, diffMny);
    }
  }

  private void updateInvcOrigTaxMny(InvoiceHeaderVO head, InvoiceItemVO item,
      UFDouble newOrigTaxMny) {
    item.setNorigtaxmny(newOrigTaxMny);
    // TODO ������ ��������������͵��ۣ���Ҫ����ȷ�ϣ�
    item.setNnum(UFDouble.ZERO_DBL);
    item.setNastnum(UFDouble.ZERO_DBL);
    item.setNastorigprice(UFDouble.ZERO_DBL);
    item.setNastorigtaxprice(UFDouble.ZERO_DBL);
    item.setNorigprice(UFDouble.ZERO_DBL);
    item.setNorigtaxprice(UFDouble.ZERO_DBL);
    new VORelationCalculate().calculate(head, item, InvoiceItemVO.NORIGTAXMNY);
  }

}
