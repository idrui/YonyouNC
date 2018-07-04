package nc.ui.pu.m23.billref.pu.m21;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m23.pu.m21.IQuery23For21;
import nc.ui.pu.pub.billref.PuRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class RefQueryServiceFor21 extends PuRefQueryService {

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) {
    this.checkQueryCond(queryScheme);
    ArriveVO[] rets = null;
    IQuery23For21 service = NCLocator.getInstance().lookup(IQuery23For21.class);
    try {
      rets = service.queryBackArrive(queryScheme);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return rets;
  }

  @Override
  protected String getRefOrgFieldCode() {
    return ArriveHeaderVO.PK_PURCHASEORG;
  }
}
