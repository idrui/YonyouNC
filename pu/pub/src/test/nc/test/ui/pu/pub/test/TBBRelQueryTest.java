package nc.test.ui.pu.pub.test;

import java.util.Collection;

import nc.bs.framework.common.NCLocator;
import nc.bs.framework.test.AbstractTestCase;
import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.ui.scmf.pub.TBBRelQueryDialog;
import nc.ui.scmf.pub.TBBRelQueryDialog.TBBQueryData;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.entity.PraybillViewVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.OrderViewVO;

/**
 * 预算联查的打开对话框测试
 * 
 * @since 6.0
 * @version 2011-6-28 下午06:09:01
 * @author zhaoyha
 */

public class TBBRelQueryTest extends AbstractTestCase {

  public void testShow() throws MetaDataException {
    TBBQueryData[] tds = new TBBQueryData[2];
    tds[0] = this.get20();
    tds[1] = this.get21();
    new TBBRelQueryDialog(null, tds, NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0", "04004000-0112")/*联查采购数据*/).showModal();
  }

  private TBBQueryData get20() throws MetaDataException {
    String[] pks = {
      "1002G610000000000Z1P", "1002G610000000001JQC"
    };
    Collection<PraybillVO> vos =
        NCLocator.getInstance().lookup(IMDPersistenceQueryService.class)
            .queryBillOfVOByPKs(PraybillVO.class, pks, false);
    PraybillVO[] aggVos = vos.toArray(new PraybillVO[vos.size()]);
    PraybillViewVO[] views =
        new BillToViewConvertor<PraybillVO, PraybillViewVO>(
            PraybillViewVO.class).convert(aggVos);
    TBBQueryData td = new TBBQueryData("4004020022", views, NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0", "04004000-0113")/*请购单*/);
    return td;
  }

  private TBBQueryData get21() throws MetaDataException {
    String[] pks = {
      "1002D510000000004EZ3"
    };
    Collection<OrderVO> vos =
        NCLocator.getInstance().lookup(IMDPersistenceQueryService.class)
            .queryBillOfVOByPKs(OrderVO.class, pks, false);
    OrderVO[] aggVos = vos.toArray(new OrderVO[vos.size()]);
    OrderViewVO[] views =
        new BillToViewConvertor<OrderVO, OrderViewVO>(OrderViewVO.class)
            .convert(aggVos);
    TBBQueryData td = new TBBQueryData("40040400RL", views, NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0", "04004000-0114")/*订单*/);
    return td;
  }

}
