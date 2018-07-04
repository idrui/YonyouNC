package nc.vo.pu.pf;

import nc.bs.pub.pf.IPrintDataGetter;
import nc.ui.pub.print.IDataSource;
import nc.vo.pub.BusinessException;

public class PUMobileService implements IPrintDataGetter {

  @Override
  public IDataSource getPrintDs(String billId, String billtype, String checkman)
      throws BusinessException {
    return new PrintDataSourceForMailAudit(billtype, billId);
  }

}
