package nc.vo.pu.m20.pf;

import nc.bs.pub.pf.IPrintDataGetter;
import nc.ui.pub.print.IDataSource;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.rule.PrayBillScaleRule;
import nc.vo.pu.pf.PrintDataSourceForMailAudit;
import nc.vo.pu.pf.PrintDataSourceForMailAudit.IMailAuditDataProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.scale.BillVOScaleProcessor;
import nc.vo.pubapp.scale.TotalValueVOScaleProcessor;

public class M20MobileService implements IPrintDataGetter {
  /**
   * 数据处理器
   * 
   * @since 6.0
   * @version 2012-5-13 下午03:36:56
   * @author tianft
   */
  public static class AuditDataProcessor implements IMailAuditDataProcessor {

    private static final long serialVersionUID = -2275800161145754342L;

    @Override
    public IBill[] processData(IBill[] datas) {
      PraybillVO[] vos = (PraybillVO[]) datas;
      String pk_group = vos[0].getHVO().getPk_group();
      BillVOScaleProcessor scale = new BillVOScaleProcessor(pk_group, vos);
      TotalValueVOScaleProcessor totalScale =
          new TotalValueVOScaleProcessor(vos);
      new PrayBillScaleRule().setScale(scale, totalScale);
      return vos;
    }
  }

  @Override
  public IDataSource getPrintDs(String billId, String billtype, String checkman)
      throws BusinessException {
    PrintDataSourceForMailAudit pds =
        new PrintDataSourceForMailAudit(billtype, billId);
    pds.setDataProcessor(new AuditDataProcessor());// 数据处理器
    return pds;
  }

}
