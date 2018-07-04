package nc.ui.pu.m4t.editor.body.after;

import nc.itf.scmpub.reference.uap.bd.vat.VATBDService;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoByTaxcodeQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoVO;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pub.lang.UFDate;

import org.apache.commons.lang.ArrayUtils;

public class Ctaxcodeid implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    int row = event.getRow();
    String ctaxcodeid =
        (String) panel.getBodyValueAt(row, InitialEstItemVO.CTAXCODEID);
    UFDate dbilldate =
        (UFDate) panel.getHeadItem(InitialEstHeaderVO.DBILLDATE)
            .getValueObject();
    if (null == ctaxcodeid || null == dbilldate) {
      return;
    }
    VATInfoByTaxcodeQueryVO[] queryvos = new VATInfoByTaxcodeQueryVO[] {
      new VATInfoByTaxcodeQueryVO(ctaxcodeid, dbilldate)
    };
    VATInfoVO[] vatinfos = VATBDService.queryVATInfo(queryvos);
    if (ArrayUtils.isEmpty(vatinfos) || vatinfos[0] == null) {
      return;
    }
    panel.setBodyValueAt(vatinfos[0].getFtaxtypeflag(), row,
        InitialEstItemVO.FTAXTYPEFLAG);
    panel.setBodyValueAt(vatinfos[0].getNnosubtaxrate(), row,
        InitialEstItemVO.NNOSUBTAXRATE);
    panel.setBodyValueAt(vatinfos[0].getNtaxrate(), row,
        InitialEstItemVO.NTAXRATE);

    RelationCalculate cal = new RelationCalculate();
    cal.calculate(panel, new int[] {
      row
    }, InitialEstItemVO.NTAXRATE);
  }
}
