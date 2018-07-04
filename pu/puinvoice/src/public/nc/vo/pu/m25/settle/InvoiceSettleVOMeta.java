package nc.vo.pu.m25.settle;

import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;

public class InvoiceSettleVOMeta extends DataViewMeta {
  public InvoiceSettleVOMeta() {
    this.init();
  }

  private void init() {
    Class<? extends ISuperVO> headClazz = InvoiceHeaderVO.class;
    Class<? extends ISuperVO> itemClazz = InvoiceItemVO.class;
    // 视图的主vo为子表vo
    this.add(itemClazz, new String[] {
      InvoiceItemVO.VFREE1, InvoiceItemVO.VFREE10, InvoiceItemVO.VFREE2,
      InvoiceItemVO.VFREE3, InvoiceItemVO.VFREE4, InvoiceItemVO.VFREE5,
      InvoiceItemVO.VFREE6, InvoiceItemVO.VFREE7, InvoiceItemVO.VFREE8,
      InvoiceItemVO.VFREE9, InvoiceItemVO.VBATCHCODE, InvoiceItemVO.CROWNO,
      InvoiceItemVO.PK_COSTSUBJ, InvoiceItemVO.CSOURCEBID,
      InvoiceItemVO.CSOURCETYPECODE, InvoiceItemVO.VSOURCECODE,
      InvoiceItemVO.VSOURCEROWNO, InvoiceItemVO.VSOURCETRANTYPE,
      InvoiceItemVO.CSOURCEID, InvoiceItemVO.VFIRSTROWNO,
      InvoiceItemVO.VFIRSTTRANTYPE, InvoiceItemVO.CFIRSTID,
      InvoiceItemVO.CFIRSTBID, InvoiceItemVO.CFIRSTTYPECODE,
      InvoiceItemVO.VFIRSTCODE, InvoiceItemVO.NACCUMSETTMNY,
      InvoiceItemVO.NACCUMSETTNUM, InvoiceItemVO.VCHANGERATE,
      InvoiceItemVO.NNUM, InvoiceItemVO.NMNY, InvoiceItemVO.NPRICE,
      InvoiceItemVO.NREASONWASTENUM, InvoiceItemVO.PK_INVOICE_B,
      InvoiceItemVO.PK_MATERIAL, InvoiceItemVO.PK_SRCMATERIAL,
      InvoiceItemVO.CUNITID,
      InvoiceItemVO.PK_ORDER,
      InvoiceItemVO.PK_ORDER_B,
      // added by wangzhqf 2013年10月14日13:25:16
      InvoiceItemVO.VORDERCODE, InvoiceItemVO.VCTCODE,
      InvoiceItemVO.CPRODUCTORID, InvoiceItemVO.CPROJECTID,
      InvoiceItemVO.PK_STORDOC, InvoiceItemVO.VMEMOB, InvoiceItemVO.NORIGPRICE,
      InvoiceItemVO.FROWTYPE, InvoiceItemVO.TS, InvoiceItemVO.NCALCOSTMNY
    });

    // 视图vo还要包含主表vo
    this.add(headClazz, new String[] {
      InvoiceHeaderVO.PK_BIZPSN, InvoiceHeaderVO.BAPFLAG,
      InvoiceHeaderVO.BVIRTUAL, InvoiceHeaderVO.DBILLDATE,
      InvoiceHeaderVO.PK_GROUP, InvoiceHeaderVO.PK_DEPT,
      InvoiceHeaderVO.PK_DEPT_V, InvoiceHeaderVO.BFEE, InvoiceHeaderVO.PK_ORG,
      InvoiceHeaderVO.PK_ORG_V, InvoiceHeaderVO.PK_INVOICE,
      InvoiceHeaderVO.PK_PARENTINVOICE, InvoiceHeaderVO.PK_PURCHASEORG,
      InvoiceHeaderVO.PK_STOCKORG, InvoiceHeaderVO.PK_STOCKORG_V,
      InvoiceHeaderVO.VBILLCODE, InvoiceHeaderVO.VTRANTYPECODE,
      InvoiceHeaderVO.CTRANTYPEID, InvoiceHeaderVO.CCURRENCYID,
      InvoiceHeaderVO.CORIGCURRENCYID, InvoiceHeaderVO.PK_SUPPLIER,
      InvoiceHeaderVO.TS, InvoiceHeaderVO.FINVOICETYPE

    });
    // 设置关联字段

    this.addRelation(itemClazz, InvoiceItemVO.PK_INVOICE, headClazz,
        InvoiceHeaderVO.PK_INVOICE);

    this.setAttributeAliasName(InvoiceHeaderVO.class, InvoiceHeaderVO.TS, "hts");
  }
}
