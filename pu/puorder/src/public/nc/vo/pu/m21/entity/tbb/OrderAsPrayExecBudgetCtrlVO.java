package nc.vo.pu.m21.entity.tbb;

import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.pubitf.uapbd.CurrencyRateUtil;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillViewVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.tbb.DocConst;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.tbb.TbbTempTableSqlBuilder;
import nc.vo.scmpub.util.TimeUtils;

import org.apache.commons.lang.StringUtils;

/**
 * @since 6.0
 * @version 2011-3-16 ����04:12:03
 * @author yinfy
 */

public class OrderAsPrayExecBudgetCtrlVO extends OrderExecBudgetCtrlVO {
  private static final long serialVersionUID = -2623857233931113480L;

  /** ��Ӧ��Դ�빺��������ͼ */
  PraybillViewVO praybillview;

  /**
   * @param head
   * @param item
   * @param opstatus
   * @param execBilltype
   */
  public OrderAsPrayExecBudgetCtrlVO(OrderHeaderVO head, OrderItemVO item,
      int opstatus, String execBilltype, PraybillViewVO praybillview) {
    super(head, item, opstatus, execBilltype);
    this.praybillview = praybillview;
  }

  @Override
  public String getAttributesValue(String attr) {
    if (DocConst.CORPSTOCKORG.equals(attr)) {
      return StockOrgPubService.queryCorpIDByStockOrgID(this.praybillview
          .getItem().getPk_org());
    }
    else if (DocConst.PURCHASEORG.equals(attr)) {
      // �ɹ���֯
      return this.praybillview.getItem().getPk_purchaseorg();
    }
    else if (DocConst.STOCKORG.equals(attr)) {
      // �����֯
      return this.praybillview.getItem().getPk_org();
    }
    else if (DocConst.BDDEPT.equals(attr)) {
      // ����
      return this.praybillview.getItem().getPk_reqdept();
    }
    else if (DocConst.BDPROJECT.equals(attr)) {
      // ��Ŀ
      return this.praybillview.getItem().getCprojectid();
    }
    else if (DocConst.MATCLASS.equals(attr)) {
      // ���Ϸ���
      Map<String, String> matbasemap =
          MaterialPubService.queryMaterialBaseClassPk(new String[] {
            this.praybillview.getItem().getPk_material()
          });
      return matbasemap.get(this.praybillview.getItem().getPk_material());
    }
    else if (DocConst.MATERIAL.equals(attr)) {
      // ����
      return this.praybillview.getItem().getPk_material();
    }
    else if (DocConst.MATERIALOID.equals(attr)) {
      // ����oid
      return this.praybillview.getItem().getPk_srcmaterial();
    }
    else if (PraybillHeaderVO.TAUDITTIME.equals(attr)) {
      if (null == this.praybillview.getHead().getAttributeValue(attr)) {
        // Ӧ��ʹ��ҵ�����ڣ�����ʱ���õ�Ҳ��ҵ�����ڡ�
        return AppContext.getInstance().getBusiDate().toString();
      }
      return this.praybillview.getHead().getAttributeValue(attr).toString();
    }
    else if (TbbTempTableSqlBuilder.CURRENCY.equals(attr)) {
      return this.praybillview.getCcurrencyid();
    }
    return null;
  }

  @Override
  public String getBillType() {
    return this.getExecBilltype() + "-" + POBillType.PrayBill.getCode();
  }

  @Override
  public String getBusiDate() {
    return this.praybillview.getHead().getTaudittime().toString();
  }

  @Override
  public String getCurrency() {
    // ȡ����
    return this.praybillview.getHead().getCcurrencyid();
  }

  private UFDouble toPrayBillMny(UFDouble data) {
    UFDouble result = null;
    try {
      Map<String, String> finance =
          StockOrgPubService.queryFinanceOrgIDByStockOrgID(new String[] {
            this.praybillview.getItem().getPk_org()
          });
      // δ�ҵ�����������֯���޷�ת��Ϊ��Ӧ�ı��ֽ��
      if (null == finance
          || StringUtils.isEmpty(finance.get(this.praybillview.getItem()
              .getPk_org()))) {
        return null;
      }
      // ����Ϊ�빺������Ӧ�ı���
      CurrencyRateUtil util =
          CurrencyRateUtil.getInstanceByOrg(finance.get(this.praybillview
              .getItem().getPk_org()));
      result =
          MathTool.nvl(util.getAmountByOpp(this.getHead().getCorigcurrencyid(),
              this.praybillview.getHead().getCcurrencyid(), data, null,
              TimeUtils.getsrvBaseDate()));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return result;
  }

  @Override
  protected UFDouble getMnyByBilltype() {
    UFDouble data = super.getMnyByBilltype();
    return this.toPrayBillMny(data);
  }

  // @Override
  // protected UFDouble getReadyMnyByOp() {
  // UFDouble data = super.getMnyByBilltype();
  // return this.toPrayBillMny(data);
  // }
}
