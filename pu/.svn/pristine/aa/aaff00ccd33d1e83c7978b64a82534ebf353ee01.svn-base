package nc.vo.pu.m21.pf;

import nc.bs.pub.pf.IPrintDataGetter;
import nc.ui.pub.print.IDataSource;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.OrderScaleRule;
import nc.vo.pu.pf.PrintDataSourceForMailAudit;
import nc.vo.pu.pf.PrintDataSourceForMailAudit.IMailAuditDataProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.scale.BillVOScaleProcessor;
import nc.vo.pubapp.scale.TotalValueVOScaleProcessor;

public class M21MobileService implements IPrintDataGetter {

  /**
   * 数据处理器
   * 
   * @since 6.0
   * @version 2012-5-13 下午03:36:56
   * @author tianft
   */
  public class AuditDataProcessor implements IMailAuditDataProcessor {

    private static final long serialVersionUID = -2426120358152870971L;

    @Override
    public IBill[] processData(IBill[] datas) {
      OrderVO[] vos = (OrderVO[]) datas;
      String pk_group = vos[0].getHVO().getPk_group();
      BillVOScaleProcessor scale = new BillVOScaleProcessor(pk_group, vos);
      TotalValueVOScaleProcessor totalScale =
          new TotalValueVOScaleProcessor(vos);
      new OrderScaleRule().setScale(scale, totalScale);
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
