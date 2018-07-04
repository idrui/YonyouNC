package nc.bs.pu.m23.upgrade.v61;

import java.util.HashMap;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.scmpub.vatupdate.VatFieldEnum;
import nc.vo.scmpub.vatupdate.VatUpdateProcess;

/**
 * 到货单v61升级逻辑。
 * 
 * @since 6.0
 * @version 2012-3-28 上午09:02:51
 * @author lixyp
 */
public class M23UpgradeFor61 {

  public void process() {
    Map<String, String> moreExpress = new HashMap<String, String>();
    moreExpress.put(ArriveItemVO.CPROJECTTASKID, "'~'");
    moreExpress.put(ArriveItemVO.CASSCUSTID, "'~'");
    VatUpdateProcess process = new VatUpdateProcess();
    process.setExpressValue(VatFieldEnum.FBUYSELLFLAG,
        BuySellFlagEnum.NATIONAL_BUY.value().toString());
    // 升级VAT字段。
    process.processVatUpdate(PUEntity.M23_B_TABLE, new VatFieldEnum[] {
      VatFieldEnum.CSENDCOUNTRYID, VatFieldEnum.CRECECOUNTRYID,
      VatFieldEnum.CTAXCOUNTRYID, VatFieldEnum.FBUYSELLFLAG,
      VatFieldEnum.BTRIATRADEFLAG
    }, moreExpress);
  }

}
