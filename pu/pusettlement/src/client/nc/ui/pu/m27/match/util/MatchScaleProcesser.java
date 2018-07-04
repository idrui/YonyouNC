package nc.ui.pu.m27.match.util;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.ClientContext;
import nc.ui.pubapp.scale.BillModelScaleProcessor;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.MatchMaterialVO;

/**
 * ����ĵ�2���棨ƥ�䣩�ľ��ȴ�����
 * 
 * @since 6.0
 * @version 2011-1-24 ����09:34:18
 * @author zhyhang
 */

public class MatchScaleProcesser {

  private BillListPanel blp;

  public MatchScaleProcesser(BillListPanel blp) {
    this.blp = blp;
  }

  /**
   * ��ʼ�����ȴ���
   */
  public void initScale() {
    String pk_group = ClientContext.getInstance().getPk_group();
    // ��ͷƥ����澫�ȴ���
    BillModelScaleProcessor bmsp =
        new BillModelScaleProcessor(pk_group, this.blp.getHeadBillModel());
    bmsp.setMnyCtlInfo(new String[] {
      MatchMaterialVO.NCOSTFACTOR1, MatchMaterialVO.NCOSTFACTOR2,
      MatchMaterialVO.NCOSTFACTOR3, MatchMaterialVO.NCOSTFACTOR4,
      MatchMaterialVO.NCOSTFACTOR5, MatchMaterialVO.NCOSTFACTOR6,
      MatchMaterialVO.NCOSTFACTOR7, MatchMaterialVO.NCOSTFACTOR8,
      MatchMaterialVO.NDISCOUNT, MatchMaterialVO.NESTMNY,
      MatchMaterialVO.NCANSETTLEMNY, MatchMaterialVO.NCURINVOICESETTLEMNY,
      MatchMaterialVO.NCURSEETLEMNY, MatchMaterialVO.NNOESTSETTLEMNY,
      MatchMaterialVO.NACCUMESTMNY
    }, MatchMaterialVO.CCURRENCYID);
    bmsp.setNumCtlInfo(new String[] {
      MatchMaterialVO.NCANSETTLENUM, MatchMaterialVO.NSTOCKINNUM,
      MatchMaterialVO.NNOESTSETTLENUM, MatchMaterialVO.NREASONWASTENUM,
      MatchMaterialVO.NCURSTOCKSETTLENUM, MatchMaterialVO.NESTNUM,
      MatchMaterialVO.NCURINVOICESETTLENUM
    }, MatchMaterialVO.CUNITID);
    bmsp.setPriceCtlInfo(new String[] {
      MatchMaterialVO.NPRICE
    }, InvoiceSettleVO.CCURRENCYID);
    bmsp.setPriceCtlInfo(new String[] {
      MatchMaterialVO.NESTPRICE, MatchMaterialVO.NSETTLEAVGPRICE
    }, InvoiceSettleVO.CORIGCURRENCYID);
    bmsp.process();
    // ������÷�Ʊ���ȴ���
    bmsp = new BillModelScaleProcessor(pk_group, this.blp.getBodyBillModel());
    bmsp.setMnyCtlInfo(new String[] {
      InvoiceSettleVO.NCANSETTLEMNY, InvoiceSettleVO.NMNY,
      InvoiceSettleVO.NCURRENTINVOICESETTLEMNY
    }, InvoiceSettleVO.CCURRENCYID);
    bmsp.process();
  }
}
