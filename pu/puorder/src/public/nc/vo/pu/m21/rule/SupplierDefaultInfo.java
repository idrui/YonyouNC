package nc.vo.pu.m21.rule;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.itf.scmpub.bd.ISupplerDefaultInfoQuery;
import nc.vo.bd.supplier.supaddress.SupAddressVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.util.remotecallcombination.RemoteCallCombinatorEx;
import nc.vo.util.remotecallcombination.Token;

import org.apache.commons.lang.StringUtils;

public class SupplierDefaultInfo implements IPURemoteCallCombinator {
  private IKeyValue helper;

  private int[] rows;

  private Token token = null;

  public SupplierDefaultInfo(IKeyValue helper, int[] rows) {
    this.helper = helper;
    this.rows = rows;
  }

  @Override
  public void prepare() {
    this.registerCombineRemoteCall();
  }

  @Override
  public void process() {
    this.setSupplierDefaultInfo();
  }

  public void setSupplierDefaultInfo() {
    SupAddressVO supaddressvo = null;
    try {
      if (null != this.token) {
        supaddressvo =
            (SupAddressVO) RemoteCallCombinatorEx.getInstance().getResult(
                this.token);
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    if (supaddressvo == null) {
      return;
    }
    for (int i = 0; i < this.rows.length; i++) {
      this.helper.setBodyValue(this.rows[i], OrderItemVO.CVENDDEVAREAID,
          supaddressvo.getPk_areacl());
      this.helper.setBodyValue(this.rows[i], OrderItemVO.CVENDDEVADDRID,
          supaddressvo.getPk_addressdoc());
    }

  }

  private boolean doInvoke(RemoteCallCombinatorEx rcc) {
    ISupplerDefaultInfoQuery supplierService =
        rcc.getService(ISupplerDefaultInfoQuery.class);
    String pk_org = (String) this.helper.getHeadValue(OrderHeaderVO.PK_ORG);
    String vendorid =
        (String) this.helper.getHeadValue(OrderHeaderVO.PK_SUPPLIER);
    if (StringUtils.isBlank(pk_org) || StringUtils.isBlank(vendorid)) {
      return false;
    }
    try {
      supplierService.getDefaultArea(vendorid, pk_org);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return true;
  }

  private void registerCombineRemoteCall() {
    RemoteCallCombinatorEx rcc = RemoteCallCombinatorEx.getInstance();
    if (this.token != null) {
      rcc.update(this.token);
      this.doInvoke(rcc);
    }
    else {
      if (this.doInvoke(rcc)) {
        this.token = rcc.getToken();
      }
    }
  }

}
