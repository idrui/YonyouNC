package nc.vo.pu.m27.entity;

import nc.vo.pu.m25.settle.ICostfactorDiscount;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.pubapp.pattern.pub.MathTool;

public class SettleBillItemVO extends SuperVO implements ICostfactorDiscount {
  /** �Ƿ�����ݹ���־ */
  public static final String BWASHEST = "bwashest";

  /** �к� */
  public static final String CROWNO = "crowno";

  /** ����λ */
  public static final String CUNITID = "cunitid";

  /** dr */
  public static final String DR = "dr";

  /** ������ */
  public static final String FROWTYPE = "frowtype";

  /** ��Ʊ���� */
  public static final String INVOICEBILLDATE = "invoicebilldate";

  /** ���������� */
  public static final String NADJUSTMNY = "nadjustmny";

  /** �����ݹ���� */
  public static final String NCLASHESTMONEY = "nclashestmoney";

  /** ���ҳɱ�Ҫ��1 */
  public static final String NCOSTFACTOR1 = "ncostfactor1";

  /** ���ҳɱ�Ҫ��2 */
  public static final String NCOSTFACTOR2 = "ncostfactor2";

  /** ���ҳɱ�Ҫ��3 */
  public static final String NCOSTFACTOR3 = "ncostfactor3";

  /** ���ҳɱ�Ҫ��4 */
  public static final String NCOSTFACTOR4 = "ncostfactor4";

  /** ���ҳɱ�Ҫ��5 */
  public static final String NCOSTFACTOR5 = "ncostfactor5";

  /** ���ҳɱ�Ҫ��6 */
  public static final String NCOSTFACTOR6 = "ncostfactor6";

  /** ���ҳɱ�Ҫ��7 */
  public static final String NCOSTFACTOR7 = "ncostfactor7";

  /** ���ҳɱ�Ҫ��8 */
  public static final String NCOSTFACTOR8 = "ncostfactor8";

  /** �ۿ� */
  public static final String NDISCOUNT = "ndiscount";

  /** ��������� */
  public static final String NGOODSMONEY = "ngoodsmoney";

  /** ������㵥�� */
  public static final String NGOODSPRICE = "ngoodsprice";

  /** ��� */
  public static final String NMONEY = "nmoney";

  /** ��Ӧ��ȷ��Ӧ��ԭ�Ҽ�˰�ϼ� */
  public static final String NOPPOCONFMAPMNY = "noppoconfmapmny";

  /** ��Ӧ��ȷ�ϳɱ� */
  public static final String NOPPOCONFMMONEY = "noppoconfmmoney";

  /** ���� */
  public static final String NPRICE = "nprice";

  /** ������Ľ�� */
  public static final String NREASONALWASTMNY = "nreasonalwastmny";

  /** ����������� */
  public static final String NREASONALWASTNUM = "nreasonalwastnum";

  /** ������ĵ��� */
  public static final String NREASONALWASTPRICE = "nreasonalwastprice";

  /** �������� */
  public static final String NSETTLENUM = "nsettlenum";

  /** �ջ������֯ */
  public static final String PK_ARRSTOCKORG = "pk_arrstockorg";

  /** �ջ������֯�汾 */
  public static final String PK_ARRSTOCKORG_V = "pk_arrstockorg_v";

  /** �ɱ��� */
  public static final String PK_COSTREGION = "pk_costregion";

  /** �ɹ�����ԭʼ��Ϣ */
  public static final String PK_DEPT = "pk_dept";

  /** �ɹ����� */
  public static final String PK_DEPT_V = "pk_dept_v";

  /** ��Ӧ��ֱ�����۶����� */
  public static final String PK_DTRANSALEBID = "pk_dtransalebid";

  /** ��Ӧ��ֱ�����۶��� */
  public static final String PK_DTRANSALEID = "pk_dtransaleid";

  /** �������ͷID */
  public static final String PK_GENERALIN = "pk_generalin";

  /** ���������ID */
  public static final String PK_GENERALIN_B = "pk_generalin_b";

  /** �������� */
  public static final String PK_GROUP = "pk_group";

  /** �ڳ��ݹ���ͷID */
  public static final String PK_INITIALEST = "pk_initialest";

  /** �ڳ��ݹ�����ID */
  public static final String PK_INITIALEST_B = "pk_initialest_b";

  /** ��Ʊ */
  public static final String PK_INVOICE = "pk_invoice";

  /** ��Ʊ�� */
  public static final String PK_INVOICE_B = "pk_invoice_b";

  /** ��Ʊ�Ķ���ͷ */
  public static final String PK_INVOICEORDER = "pk_invoiceorder";

  /** ��Ʊ�Ķ����� */
  public static final String PK_INVOICEORDER_B = "pk_invoiceorder_b";

  /** ���ϰ汾 */
  public static final String PK_MATERIAL = "pk_material";

  /** ������֯ */
  public static final String PK_ORG = "pk_org";

  /** ������֯�汾 */
  public static final String PK_ORG_V = "pk_org_v";

  /** �ɹ�Ա */
  public static final String PK_PSNDOC = "pk_psndoc";

  /** �ɹ����ͷID */
  public static final String PK_PURCHASEIN = "pk_purchasein";

  /** �ɹ������ID */
  public static final String PK_PURCHASEIN_B = "pk_purchasein_b";

  /** �Գ�ķ�Ʊ */
  public static final String PK_RUSHINVOICE = "pk_rushinvoice";

  /** �Գ�ķ�Ʊ�� */
  public static final String PK_RUSHINVOICE_B = "pk_rushinvoice_b";

  /** �Գ�Ŀ�浥�� */
  public static final String PK_RUSHSTOCK = "pk_rushstock";

  /** �Գ�Ŀ�浥���� */
  public static final String PK_RUSHSTOCK_B = "pk_rushstock_b";

  /** ���÷�̯��ϸ���� */
  public static final String PK_SETTLE_FEEALLOT = "pk_settle_feeallot";

  /** ���㵥��ϸ */
  public static final String PK_SETTLEBILL = "pk_settlebill";

  /** ������ϸ */
  public static final String PK_SETTLEBILL_B = "pk_settlebill_b";

  /** ���� */
  public static final String PK_SRCMATERIAL = "pk_srcmaterial";

  /** ��浥�� */
  public static final String PK_STOCK = "pk_stock";

  /** ��浥���� */
  public static final String PK_STOCK_B = "pk_stock_b";

  /** ��浥�ݵĶ���ͷ */
  public static final String PK_STOCKORDER = "pk_stockorder";

  /** ��浥�ݵĶ����� */
  public static final String PK_STOCKORDER_B = "pk_stockorder_b";

  /** �ֿ� */
  public static final String PK_STORDOC = "pk_stordoc";

  /** ί�����ͷID */
  public static final String PK_SUBCONTRACT = "pk_subcontract";

  /** ί�������ID */
  public static final String PK_SUBCONTRACT_B = "pk_subcontract_b";

  /** ��Ӧ�� */
  public static final String PK_SUPPLIER = "pk_supplier";

  /** �������ͷID */
  public static final String PK_TRANSIN = "pk_transin";

  /** ���������ID */
  public static final String PK_TRANSIN_B = "pk_transin_b";

  /** ���Ļ��ܱ�ͷID */
  public static final String PK_VOICONSUME = "pk_voiconsume";

  /** ���Ļ��ܱ���ID */
  public static final String PK_VOICONSUME_B = "pk_voiconsume_b";

  /** ������� */
  public static final String STOCKBILLDATE = "stockbilldate";

  /** ts */
  public static final String TS = "ts";

  /** ��Ʊ�� */
  public static final String VINVOICECODE = "vinvoicecode";

  /** ��Ʊ�������� */
  public static final String VINVOICETRANTYPE = "vinvoicetrantype";

  /** ��浥������ */
  public static final String VSTOCKBILLTYPE = "vstockbilltype";

  /** ��浥�ݺ� */
  public static final String VSTOCKCODE = "vstockcode";

  /** ��潻������ */
  public static final String VSTOCKTRANTYPE = "vstocktrantype";
  
  /** ���ں�ͬ�� */
  public static final String VITCTCODE = "vitctcode";

  private static final long serialVersionUID = -1914800029666610204L;

  @Override
  public void addtoCurrenttotalsettlemoney(UFDouble dValue) {
    this.setNmoney(MathTool.add(this.getNmoney(), dValue));
  }

  /** �Ƿ�����ݹ���־ getter ���� */
  public UFBoolean getBwashest() {
    return (UFBoolean) this.getAttributeValue(SettleBillItemVO.BWASHEST);
  }

  /** �к� getter ���� */
  public String getCrowno() {
    return (String) this.getAttributeValue(SettleBillItemVO.CROWNO);
  }

  /** ����λ getter ���� */
  public String getCunitid() {
    return (String) this.getAttributeValue(SettleBillItemVO.CUNITID);
  }

  /** dr getter ���� */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(SettleBillItemVO.DR);
  }

  /** ������ getter ���� */
  public Integer getFrowtype() {
    return (Integer) this.getAttributeValue(SettleBillItemVO.FROWTYPE);
  }

  /** ��Ʊ���� **/
  public UFDate getInvoicebilldate() {
    return (UFDate) this.getAttributeValue(SettleBillItemVO.INVOICEBILLDATE);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta(PUEntity.SettleBill_B);
    return meta;
  }

  /** ���������� getter ���� */
  @Override
  public UFDouble getNadjustmny() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NADJUSTMNY);
  }

  @Override
  public UFDouble getNallotmoney() {
    return null;
  }

  @Override
  public UFDouble getNallotnum() {
    return null;
  }

  /**
   * �����ݹ���� getter ����<br>
   * ��������ݹ��س�,ֻ�����س���Ϊ�㣨ȡ����ʱ���ܳ��ֵ�����������������ݹ��س�<br>
   * �����ж����ݹ�ǰ���㣬�����ݹ������
   */
  public UFDouble getNclashestmoney() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NCLASHESTMONEY);
  }

  /** ���ҳɱ�Ҫ��1 getter ���� */
  @Override
  public UFDouble getNcostfactor1() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NCOSTFACTOR1);
  }

  /** ���ҳɱ�Ҫ��2 getter ���� */
  @Override
  public UFDouble getNcostfactor2() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NCOSTFACTOR2);
  }

  /** ���ҳɱ�Ҫ��3 getter ���� */
  @Override
  public UFDouble getNcostfactor3() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NCOSTFACTOR3);
  }

  /** ���ҳɱ�Ҫ��4 getter ���� */
  @Override
  public UFDouble getNcostfactor4() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NCOSTFACTOR4);
  }

  /** ���ҳɱ�Ҫ��5 getter ���� */
  @Override
  public UFDouble getNcostfactor5() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NCOSTFACTOR5);
  }

  /** ���ҳɱ�Ҫ��6 getter ���� */
  @Override
  public UFDouble getNcostfactor6() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NCOSTFACTOR6);
  }

  /** ���ҳɱ�Ҫ��7 getter ���� */
  @Override
  public UFDouble getNcostfactor7() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NCOSTFACTOR7);
  }

  /** ���ҳɱ�Ҫ��8 getter ���� */
  @Override
  public UFDouble getNcostfactor8() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NCOSTFACTOR8);
  }

  /** �ۿ� getter ���� */
  @Override
  public UFDouble getNdiscount() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NDISCOUNT);
  }

  /** ��������� getter ���� */
  public UFDouble getNgoodsmoney() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NGOODSMONEY);
  }

  /** ������㵥�� getter ���� */
  public UFDouble getNgoodsprice() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NGOODSPRICE);
  }

  /** ��� getter ���� */
  public UFDouble getNmoney() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NMONEY);
  }

  /** ��Ӧ��ȷ��Ӧ��ԭ�Ҽ�˰�ϼ� getter ���� */
  public UFDouble getNoppoconfmapmny() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NOPPOCONFMAPMNY);
  }

  /** ��Ӧ��ȷ�ϳɱ� getter ���� */
  public UFDouble getNoppoconfmmoney() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NOPPOCONFMMONEY);
  }

  /** ���� getter ���� */
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NPRICE);
  }

  /** ������Ľ�� getter ���� */
  public UFDouble getNreasonalwastmny() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NREASONALWASTMNY);
  }

  /** ����������� getter ���� */
  public UFDouble getNreasonalwastnum() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NREASONALWASTNUM);
  }

  /** ������ĵ��� getter ���� */
  public UFDouble getNreasonalwastprice() {
    return (UFDouble) this
        .getAttributeValue(SettleBillItemVO.NREASONALWASTPRICE);
  }

  /** �������� getter ���� */
  public UFDouble getNsettlenum() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NSETTLENUM);
  }

  /** �ջ������֯ getter ���� */
  public String getPk_arrstockorg() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_ARRSTOCKORG);
  }

  /** �ջ������֯�汾 getter ���� */
  public String getPk_arrstockorg_v() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_ARRSTOCKORG_V);
  }

  /** �ɱ��� getter ���� */
  public String getPk_costregion() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_COSTREGION);
  }

  /** �ɹ�����ԭʼ��Ϣ getter ���� */
  public String getPk_dept() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_DEPT);
  }

  /** �ɹ����� getter ���� */
  public String getPk_dept_v() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_DEPT_V);
  }

  /** ��Ӧ��ֱ�����۶����� getter ���� */
  public String getPk_dtransalebid() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_DTRANSALEBID);
  }

  /** ��Ӧ��ֱ�����۶��� getter ���� */
  public String getPk_dtransaleid() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_DTRANSALEID);
  }

  /** �������ͷID getter ���� */
  public String getPk_generalin() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_GENERALIN);
  }

  /** ���������ID getter ���� */
  public String getPk_generalin_b() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_GENERALIN_B);
  }

  /** �������� getter ���� */
  public String getPk_group() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_GROUP);
  }

  /** �ڳ��ݹ���ͷID getter ���� */
  public String getPk_initialest() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_INITIALEST);
  }

  /** �ڳ��ݹ�����ID getter ���� */
  public String getPk_initialest_b() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_INITIALEST_B);
  }

  /** ��Ʊ getter ���� */
  public String getPk_invoice() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_INVOICE);
  }

  /** ��Ʊ�� getter ���� */
  public String getPk_invoice_b() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_INVOICE_B);
  }

  /** ��Ʊ�Ķ���ͷ getter ���� */
  public String getPk_invoiceorder() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_INVOICEORDER);
  }

  /** ��Ʊ�Ķ����� getter ���� */
  public String getPk_invoiceorder_b() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_INVOICEORDER_B);
  }

  /** ���ϰ汾 getter ���� */
  public String getPk_material() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_MATERIAL);
  }

  /** ������֯ getter ���� */
  public String getPk_org() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_ORG);
  }

  /** ������֯�汾 getter ���� */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_ORG_V);
  }

  /** �ɹ�Ա getter ���� */
  public String getPk_psndoc() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_PSNDOC);
  }

  /** �ɹ����ͷID getter ���� */
  public String getPk_purchasein() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_PURCHASEIN);
  }

  /** �ɹ������ID getter ���� */
  public String getPk_purchasein_b() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_PURCHASEIN_B);
  }

  /** �Գ�ķ�Ʊ getter ���� */
  public String getPk_rushinvoice() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_RUSHINVOICE);
  }

  /** �Գ�ķ�Ʊ�� getter ���� */
  public String getPk_rushinvoice_b() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_RUSHINVOICE_B);
  }

  /** �Գ�Ŀ�浥�� getter ���� */
  public String getPk_rushstock() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_RUSHSTOCK);
  }

  /** �Գ�Ŀ�浥���� getter ���� */
  public String getPk_rushstock_b() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_RUSHSTOCK_B);
  }

  /** ���÷�̯��ϸ���� getter ���� */
  public String getPk_settle_feeallot() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_SETTLE_FEEALLOT);
  }

  /** ���㵥��ϸ getter ���� */
  public String getPk_settlebill() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_SETTLEBILL);
  }

  /** ������ϸ getter ���� */
  public String getPk_settlebill_b() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_SETTLEBILL_B);
  }

  /** ���� getter ���� */
  @Override
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_SRCMATERIAL);
  }

  /** ��浥�� getter ���� */
  public String getPk_stock() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_STOCK);
  }

  /** ��浥���� getter ���� */
  public String getPk_stock_b() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_STOCK_B);
  }

  /** ��浥�ݵĶ���ͷ getter ���� */
  public String getPk_stockorder() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_STOCKORDER);
  }

  /** ��浥�ݵĶ����� getter ���� */
  public String getPk_stockorder_b() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_STOCKORDER_B);
  }

  /** �ֿ� getter ���� */
  public String getPk_stordoc() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_STORDOC);
  }

  /** ί�����ͷID getter ���� */
  public String getPk_subcontract() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_SUBCONTRACT);
  }

  /** ί�������ID getter ���� */
  public String getPk_subcontract_b() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_SUBCONTRACT_B);
  }

  /** ��Ӧ�� getter ���� */
  public String getPk_supplier() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_SUPPLIER);
  }

  /** �������ͷID getter ���� */
  public String getPk_transin() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_TRANSIN);
  }

  /** ���������ID getter ���� */
  public String getPk_transin_b() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_TRANSIN_B);
  }

  /** ���Ļ��ܱ�ͷID getter ���� */
  public String getPk_voiconsume() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_VOICONSUME);
  }

  /** ���Ļ��ܱ���ID getter ���� */
  public String getPk_voiconsume_b() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_VOICONSUME_B);
  }

  /** ������� **/
  public UFDate getStockbilldate() {
    return (UFDate) this.getAttributeValue(SettleBillItemVO.STOCKBILLDATE);
  }

  /** ts getter ���� */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SettleBillItemVO.TS);
  }

  /** ��Ʊ�� getter ���� */
  public String getVinvoicecode() {
    return (String) this.getAttributeValue(SettleBillItemVO.VINVOICECODE);
  }

  /** ��Ʊ�������� getter ���� */
  public String getVinvoicetrantype() {
    return (String) this.getAttributeValue(SettleBillItemVO.VINVOICETRANTYPE);
  }

  /** ��浥������ getter ���� */
  public String getVstockbilltype() {
    return (String) this.getAttributeValue(SettleBillItemVO.VSTOCKBILLTYPE);
  }

  /** ��浥�ݺ� getter ���� */
  public String getVstockcode() {
    return (String) this.getAttributeValue(SettleBillItemVO.VSTOCKCODE);
  }

  /** ��潻������ getter ���� */
  public String getVstocktrantype() {
    return (String) this.getAttributeValue(SettleBillItemVO.VSTOCKTRANTYPE);
  }
  
  /** ���ں�ͬ�� getter ���� */
  public String getVitctcode() {
	    return (String) this.getAttributeValue(SettleBillItemVO.VITCTCODE);
	  }
  
  

  /** �Ƿ�����ݹ���־ setter ���� */
  public void setBwashest(UFBoolean bwashest) {
    this.setAttributeValue(SettleBillItemVO.BWASHEST, bwashest);
  }

  /** �к� setter ���� */
  public void setCrowno(String crowno) {
    this.setAttributeValue(SettleBillItemVO.CROWNO, crowno);
  }

  /** ����λ setter ���� */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(SettleBillItemVO.CUNITID, cunitid);
  }

  /** dr setter ���� */
  public void setDr(Integer dr) {
    this.setAttributeValue(SettleBillItemVO.DR, dr);
  }

  /** ������ setter ���� */
  public void setFrowtype(Integer frowtype) {
    this.setAttributeValue(SettleBillItemVO.FROWTYPE, frowtype);
  }

  /** ��Ʊ���� **/
  public void setInvoicebilldate(UFDate invoicebilldate) {
    this.setAttributeValue(SettleBillItemVO.INVOICEBILLDATE, invoicebilldate);
  }

  /** ���������� setter ���� */
  @Override
  public void setNadjustmny(UFDouble nadjustmny) {
    this.setAttributeValue(SettleBillItemVO.NADJUSTMNY, nadjustmny);
  }

  /**
   * �����ݹ���� setter ���� <br>
   * ��������ݹ��س�,ֻ�����س���Ϊ�㣨ȡ����ʱ���ܳ��ֵ�����������������ݹ��س�<br>
   * �����ж����ݹ�ǰ���㣬�����ݹ������
   */
  public void setNclashestmoney(UFDouble nclashestmoney) {
    this.setAttributeValue(SettleBillItemVO.NCLASHESTMONEY, nclashestmoney);
  }

  /** ���ҳɱ�Ҫ��1 setter ���� */
  @Override
  public void setNcostfactor1(UFDouble ncostfactor1) {
    this.setAttributeValue(SettleBillItemVO.NCOSTFACTOR1, ncostfactor1);
  }

  /** ���ҳɱ�Ҫ��2 setter ���� */
  @Override
  public void setNcostfactor2(UFDouble ncostfactor2) {
    this.setAttributeValue(SettleBillItemVO.NCOSTFACTOR2, ncostfactor2);
  }

  /** ���ҳɱ�Ҫ��3 setter ���� */
  @Override
  public void setNcostfactor3(UFDouble ncostfactor3) {
    this.setAttributeValue(SettleBillItemVO.NCOSTFACTOR3, ncostfactor3);
  }

  /** ���ҳɱ�Ҫ��4 setter ���� */
  @Override
  public void setNcostfactor4(UFDouble ncostfactor4) {
    this.setAttributeValue(SettleBillItemVO.NCOSTFACTOR4, ncostfactor4);
  }

  /** ���ҳɱ�Ҫ��5 setter ���� */
  @Override
  public void setNcostfactor5(UFDouble ncostfactor5) {
    this.setAttributeValue(SettleBillItemVO.NCOSTFACTOR5, ncostfactor5);
  }

  /** ���ҳɱ�Ҫ��6 setter ���� */
  @Override
  public void setNcostfactor6(UFDouble ncostfactor6) {
    this.setAttributeValue(SettleBillItemVO.NCOSTFACTOR6, ncostfactor6);
  }

  /** ���ҳɱ�Ҫ��7 setter ���� */
  @Override
  public void setNcostfactor7(UFDouble ncostfactor7) {
    this.setAttributeValue(SettleBillItemVO.NCOSTFACTOR7, ncostfactor7);
  }

  /** ���ҳɱ�Ҫ��8 setter ���� */
  @Override
  public void setNcostfactor8(UFDouble ncostfactor8) {
    this.setAttributeValue(SettleBillItemVO.NCOSTFACTOR8, ncostfactor8);
  }

  /** �ۿ� setter ���� */
  @Override
  public void setNdiscount(UFDouble ndiscount) {
    this.setAttributeValue(SettleBillItemVO.NDISCOUNT, ndiscount);
  }

  /** ��������� setter ���� */
  public void setNgoodsmoney(UFDouble ngoodsmoney) {
    this.setAttributeValue(SettleBillItemVO.NGOODSMONEY, ngoodsmoney);
  }

  /** ������㵥�� setter ���� */
  public void setNgoodsprice(UFDouble ngoodsprice) {
    this.setAttributeValue(SettleBillItemVO.NGOODSPRICE, ngoodsprice);
  }

  /** ��� setter ���� */
  public void setNmoney(UFDouble nmoney) {
    this.setAttributeValue(SettleBillItemVO.NMONEY, nmoney);
  }

  /** ��Ӧ��ȷ��Ӧ��ԭ�Ҽ�˰�ϼ� setter ���� */
  public void setNoppoconfmapmny(UFDouble noppoconfmapmny) {
    this.setAttributeValue(SettleBillItemVO.NOPPOCONFMAPMNY, noppoconfmapmny);
  }

  /** ��Ӧ��ȷ�ϳɱ� setter ���� */
  public void setNoppoconfmmoney(UFDouble noppoconfmmoney) {
    this.setAttributeValue(SettleBillItemVO.NOPPOCONFMMONEY, noppoconfmmoney);
  }

  /** ���� setter ���� */
  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(SettleBillItemVO.NPRICE, nprice);
  }

  /** ������Ľ�� setter ���� */
  public void setNreasonalwastmny(UFDouble nreasonalwastmny) {
    this.setAttributeValue(SettleBillItemVO.NREASONALWASTMNY, nreasonalwastmny);
  }

  /** ����������� setter ���� */
  public void setNreasonalwastnum(UFDouble nreasonalwastnum) {
    this.setAttributeValue(SettleBillItemVO.NREASONALWASTNUM, nreasonalwastnum);
  }

  /** ������ĵ��� setter ���� */
  public void setNreasonalwastprice(UFDouble nreasonalwastprice) {
    this.setAttributeValue(SettleBillItemVO.NREASONALWASTPRICE,
        nreasonalwastprice);
  }

  /** �������� setter ���� */
  public void setNsettlenum(UFDouble nsettlenum) {
    this.setAttributeValue(SettleBillItemVO.NSETTLENUM, nsettlenum);
  }

  /** �ջ������֯ setter ���� */
  public void setPk_arrstockorg(String pk_arrstockorg) {
    this.setAttributeValue(SettleBillItemVO.PK_ARRSTOCKORG, pk_arrstockorg);
  }

  /** �ջ������֯�汾 setter ���� */
  public void setPk_arrstockorg_v(String pk_arrstockorg_v) {
    this.setAttributeValue(SettleBillItemVO.PK_ARRSTOCKORG_V, pk_arrstockorg_v);
  }

  /** �ɱ��� setter ���� */
  public void setPk_costregion(String pk_costregion) {
    this.setAttributeValue(SettleBillItemVO.PK_COSTREGION, pk_costregion);
  }

  /** �ɹ�����ԭʼ��Ϣ setter ���� */
  public void setPk_dept(String pk_dept) {
    this.setAttributeValue(SettleBillItemVO.PK_DEPT, pk_dept);
  }

  /** �ɹ����� setter ���� */
  public void setPk_dept_v(String pk_dept_v) {
    this.setAttributeValue(SettleBillItemVO.PK_DEPT_V, pk_dept_v);
  }

  /** ��Ӧ��ֱ�����۶����� setter ���� */
  public void setPk_dtransalebid(String pk_dtransalebid) {
    this.setAttributeValue(SettleBillItemVO.PK_DTRANSALEBID, pk_dtransalebid);
  }

  /** ��Ӧ��ֱ�����۶��� setter ���� */
  public void setPk_dtransaleid(String pk_dtransaleid) {
    this.setAttributeValue(SettleBillItemVO.PK_DTRANSALEID, pk_dtransaleid);
  }

  /** �������ͷID setter ���� */
  public void setPk_generalin(String pk_generalin) {
    this.setAttributeValue(SettleBillItemVO.PK_GENERALIN, pk_generalin);
  }

  /** ���������ID setter ���� */
  public void setPk_generalin_b(String pk_generalin_b) {
    this.setAttributeValue(SettleBillItemVO.PK_GENERALIN_B, pk_generalin_b);
  }

  /** �������� setter ���� */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(SettleBillItemVO.PK_GROUP, pk_group);
  }

  /** �ڳ��ݹ���ͷID setter ���� */
  public void setPk_initialest(String pk_initialest) {
    this.setAttributeValue(SettleBillItemVO.PK_INITIALEST, pk_initialest);
  }

  /** �ڳ��ݹ�����ID setter ���� */
  public void setPk_initialest_b(String pk_initialest_b) {
    this.setAttributeValue(SettleBillItemVO.PK_INITIALEST_B, pk_initialest_b);
  }

  /** ��Ʊ setter ���� */
  public void setPk_invoice(String pk_invoice) {
    this.setAttributeValue(SettleBillItemVO.PK_INVOICE, pk_invoice);
  }

  /** ��Ʊ�� setter ���� */
  public void setPk_invoice_b(String pk_invoice_b) {
    this.setAttributeValue(SettleBillItemVO.PK_INVOICE_B, pk_invoice_b);
  }

  /** ��Ʊ�Ķ���ͷ setter ���� */
  public void setPk_invoiceorder(String pk_invoiceorder) {
    this.setAttributeValue(SettleBillItemVO.PK_INVOICEORDER, pk_invoiceorder);
  }

  /** ��Ʊ�Ķ����� setter ���� */
  public void setPk_invoiceorder_b(String pk_invoiceorder_b) {
    this.setAttributeValue(SettleBillItemVO.PK_INVOICEORDER_B,
        pk_invoiceorder_b);
  }

  /** ���ϰ汾 setter ���� */
  public void setPk_material(String pk_material) {
    this.setAttributeValue(SettleBillItemVO.PK_MATERIAL, pk_material);
  }

  /** ������֯ setter ���� */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(SettleBillItemVO.PK_ORG, pk_org);
  }

  /** ������֯�汾 setter ���� */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(SettleBillItemVO.PK_ORG_V, pk_org_v);
  }

  /** �ɹ�Ա setter ���� */
  public void setPk_psndoc(String pk_psndoc) {
    this.setAttributeValue(SettleBillItemVO.PK_PSNDOC, pk_psndoc);
  }

  /** �ɹ����ͷID setter ���� */
  public void setPk_purchasein(String pk_purchasein) {
    this.setAttributeValue(SettleBillItemVO.PK_PURCHASEIN, pk_purchasein);
  }

  /** �ɹ������ID setter ���� */
  public void setPk_purchasein_b(String pk_purchasein_b) {
    this.setAttributeValue(SettleBillItemVO.PK_PURCHASEIN_B, pk_purchasein_b);
  }

  /** �Գ�ķ�Ʊ setter ���� */
  public void setPk_rushinvoice(String pk_rushinvoice) {
    this.setAttributeValue(SettleBillItemVO.PK_RUSHINVOICE, pk_rushinvoice);
  }

  /** �Գ�ķ�Ʊ�� setter ���� */
  public void setPk_rushinvoice_b(String pk_rushinvoice_b) {
    this.setAttributeValue(SettleBillItemVO.PK_RUSHINVOICE_B, pk_rushinvoice_b);
  }

  /** �Գ�Ŀ�浥�� setter ���� */
  public void setPk_rushstock(String pk_rushstock) {
    this.setAttributeValue(SettleBillItemVO.PK_RUSHSTOCK, pk_rushstock);
  }

  /** �Գ�Ŀ�浥���� setter ���� */
  public void setPk_rushstock_b(String pk_rushstock_b) {
    this.setAttributeValue(SettleBillItemVO.PK_RUSHSTOCK_B, pk_rushstock_b);
  }

  /** ���÷�̯��ϸ���� setter ���� */
  public void setPk_settle_feeallot(String pk_settle_feeallot) {
    this.setAttributeValue(SettleBillItemVO.PK_SETTLE_FEEALLOT,
        pk_settle_feeallot);
  }

  /** ���㵥��ϸ setter ���� */
  public void setPk_settlebill(String pk_settlebill) {
    this.setAttributeValue(SettleBillItemVO.PK_SETTLEBILL, pk_settlebill);
  }

  /** ������ϸ setter ���� */
  public void setPk_settlebill_b(String pk_settlebill_b) {
    this.setAttributeValue(SettleBillItemVO.PK_SETTLEBILL_B, pk_settlebill_b);
  }

  /** ���� setter ���� */
  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.setAttributeValue(SettleBillItemVO.PK_SRCMATERIAL, pk_srcmaterial);
  }

  /** ��浥�� setter ���� */
  public void setPk_stock(String pk_stock) {
    this.setAttributeValue(SettleBillItemVO.PK_STOCK, pk_stock);
  }

  /** ��浥���� setter ���� */
  public void setPk_stock_b(String pk_stock_b) {
    this.setAttributeValue(SettleBillItemVO.PK_STOCK_B, pk_stock_b);
  }

  /** ��浥�ݵĶ���ͷ setter ���� */
  public void setPk_stockorder(String pk_stockorder) {
    this.setAttributeValue(SettleBillItemVO.PK_STOCKORDER, pk_stockorder);
  }

  /** ��浥�ݵĶ����� setter ���� */
  public void setPk_stockorder_b(String pk_stockorder_b) {
    this.setAttributeValue(SettleBillItemVO.PK_STOCKORDER_B, pk_stockorder_b);
  }

  /** �ֿ� setter ���� */
  public void setPk_stordoc(String pk_stordoc) {
    this.setAttributeValue(SettleBillItemVO.PK_STORDOC, pk_stordoc);
  }

  /** ί�����ͷID setter ���� */
  public void setPk_subcontract(String pk_subcontract) {
    this.setAttributeValue(SettleBillItemVO.PK_SUBCONTRACT, pk_subcontract);
  }

  /** ί�������ID setter ���� */
  public void setPk_subcontract_b(String pk_subcontract_b) {
    this.setAttributeValue(SettleBillItemVO.PK_SUBCONTRACT_B, pk_subcontract_b);
  }

  /** ��Ӧ�� setter ���� */
  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(SettleBillItemVO.PK_SUPPLIER, pk_supplier);
  }

  /** �������ͷID setter ���� */
  public void setPk_transin(String pk_transin) {
    this.setAttributeValue(SettleBillItemVO.PK_TRANSIN, pk_transin);
  }

  /** ���������ID setter ���� */
  public void setPk_transin_b(String pk_transin_b) {
    this.setAttributeValue(SettleBillItemVO.PK_TRANSIN_B, pk_transin_b);
  }

  /** ���Ļ��ܱ�ͷID setter ���� */
  public void setPk_voiconsume(String pk_voiconsume) {
    this.setAttributeValue(SettleBillItemVO.PK_VOICONSUME, pk_voiconsume);
  }

  /** ���Ļ��ܱ���ID setter ���� */
  public void setPk_voiconsume_b(String pk_voiconsume_b) {
    this.setAttributeValue(SettleBillItemVO.PK_VOICONSUME_B, pk_voiconsume_b);
  }

  /** ������� **/
  public void setStockbilldate(UFDate stockbilldate) {
    this.setAttributeValue(SettleBillItemVO.STOCKBILLDATE, stockbilldate);
  }

  /** ts setter ���� */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SettleBillItemVO.TS, ts);
  }

  /** ��Ʊ�� setter ���� */
  public void setVinvoicecode(String vinvoicecode) {
    this.setAttributeValue(SettleBillItemVO.VINVOICECODE, vinvoicecode);
  }

  /** ��Ʊ�������� setter ���� */
  public void setVinvoicetrantype(String vinvoicetrantype) {
    this.setAttributeValue(SettleBillItemVO.VINVOICETRANTYPE, vinvoicetrantype);
  }

  /** ��浥������ setter ���� */
  public void setVstockbilltype(String vstockbilltype) {
    this.setAttributeValue(SettleBillItemVO.VSTOCKBILLTYPE, vstockbilltype);
  }

  /** ��浥�ݺ� setter ���� */
  public void setVstockcode(String vstockcode) {
    this.setAttributeValue(SettleBillItemVO.VSTOCKCODE, vstockcode);
  }

  /** ��潻������ setter ���� */
  public void setVstocktrantype(String vstocktrantype) {
    this.setAttributeValue(SettleBillItemVO.VSTOCKTRANTYPE, vstocktrantype);
  }
  
  /** ���ں�ͬ�� setter ���� */
  public void setVitctcode(String vitctcode) {
    this.setAttributeValue(SettleBillItemVO.VITCTCODE, vitctcode);
  }
  

}
