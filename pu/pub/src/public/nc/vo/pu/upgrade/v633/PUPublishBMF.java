package nc.vo.pu.upgrade.v633;

import java.util.ArrayList;
import java.util.List;

import nc.scmmm.upgrade.scmpub.bmfpublish.AbstractSCMPublishBMF;
import nc.scmmm.upgrade.scmpub.bmfpublish.BmfFilePath;

public class PUPublishBMF extends AbstractSCMPublishBMF {

  @Override
  protected BmfFilePath[] getBmfFilePath() {
    List<BmfFilePath> paths = new ArrayList<BmfFilePath>();
    paths.add(new BmfFilePath("", "po_req_v60.bmf"));
    paths.add(new BmfFilePath("", "po_invoice_v60.bmf"));
    paths.add(new BmfFilePath("", "po_initialest.bmf"));
    paths.add(new BmfFilePath("", "po_settlebill.bmf"));
    paths.add(new BmfFilePath("", "po_stockps.bmf"));
    paths.add(new BmfFilePath("", "po_pray.bmf"));
    paths.add(new BmfFilePath("", "po_order_v60.bmf"));
    paths.add(new BmfFilePath("", "poinvoice_transtype.bmf"));
    paths.add(new BmfFilePath("", "invoicestockoptionable.bmf"));
    paths.add(new BmfFilePath("", "po_arrive.bmf"));
    return paths.toArray(new BmfFilePath[0]);
  }

  @Override
  protected String getModuleDir() {
    return "pu";
  }

}
