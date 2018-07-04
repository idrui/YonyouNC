package nc.vo.pu.m25.vochange;

import java.util.ArrayList;
import java.util.List;

import nc.vo.ic.m45.entity.PurchaseInBodyVO;
import nc.vo.ic.m45.entity.PurchaseInVO;
import nc.vo.pf.change.ChangeVOAdjustContext;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pu.m25.rule.maintain.InvoiceOrgVatCodeFillRule;
import nc.vo.pu.m25.rule.maintain.InvoiceSupplierVatCodeFillRule;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * @since 6.0
 * @version 2011-4-18 ����05:10:42
 * @author �����
 */

public class ChangeVOAdjustor45To25 extends InvoiceChangeVOAdjustor {

  @Override
  public AggregatedValueObject[] batchAdjustBeforeChange(
      AggregatedValueObject[] srcVOs, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    // ����ǰ����
    PurchaseInVO[] sourceVOs =
        (PurchaseInVO[]) ArrayUtil.convertArrayType(srcVOs);
    for (PurchaseInVO vo : sourceVOs) {
      // ������ȷ����ʽ���漰������IC�Ѿ������˿ɿ�Ʊ����
      // �������������ǿ��ǲɹ�����ʱ���Լ�����vo���������
      for (PurchaseInBodyVO item : vo.getBodys()) {
        if (MathTool.isZero(item.getNinvoicenum())) {
          item.setNinvoicenum(MathTool.sub(item.getNnum(), item.getNsignnum()));
        }
      }
    }
    PurchaseInVO[] sourceVOss = this.filterFlargessRow(sourceVOs);
    if (sourceVOss == null || sourceVOss.length < 1) {
      ExceptionUtils.marsh(new BusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004050_0", "04004050-0134")/*
                                                                   * ȫ����Ʒ�еĵ��ݲ�֧��һ�Ƶ�������
                                                                   * �����飡
                                                                   */));
      return null;
    }
    return sourceVOss;
  }

  /**
   * ���˱�����Ʒ�С������ݱ���ȫΪ��Ʒ�У��򲻴���
   * 
   * @param signvos
   * @return
   */
  private PurchaseInVO[] filterFlargessRow(PurchaseInVO[] signvos) {
    if (signvos == null || signvos.length == 0) {
      return signvos;
    }
    List<PurchaseInVO> accBills = new ArrayList<PurchaseInVO>();
    for (PurchaseInVO bill : signvos) {

      PurchaseInBodyVO[] bodys = bill.getBodys();
      List<PurchaseInBodyVO> accBodys = new ArrayList<PurchaseInBodyVO>();
      for (PurchaseInBodyVO body : bodys) {
        if (UFBoolean.FALSE.equals(body.getFlargess())) {
          accBodys.add(body);
        }
      }
      if (accBodys.size() > 0) {
        if (accBodys.size() < bodys.length) {
          bill.setChildrenVO(accBodys.toArray(new PurchaseInBodyVO[accBodys
              .size()]));
        }
        accBills.add(bill);
      }
    }

    return accBills.toArray(new PurchaseInVO[accBills.size()]);
  }

  @Override
  protected void setDefaultVatInfo(InvoiceVO[] retvos) {
    IKeyValue[] bills = InvoiceVOUtil.getBillHelper(retvos);
    InvoiceSupplierVatCodeFillRule supvatrule =
        new InvoiceSupplierVatCodeFillRule(bills);
    InvoiceOrgVatCodeFillRule orgvatrule = new InvoiceOrgVatCodeFillRule(bills);
    supvatrule.prepare();
    orgvatrule.prepare();
    supvatrule.process();
    orgvatrule.process();
  }
}
