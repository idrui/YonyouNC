package nc.ui.pu.m23.billref.ic.m47;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m23.ic.m47.IQuery23For47;
import nc.ui.pu.pub.billref.PuRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 到货单提供给委托加工入库单的转单查询服务
 * 
 * @since 6.0
 * @version 2010-11-12 下午03:46:16
 * @author hanbin
 */
public class RefQueryServiceFor47 extends PuRefQueryService {

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) {
    this.checkQueryCond(queryScheme);
    ArriveVO[] rets = null;
    IQuery23For47 service = NCLocator.getInstance().lookup(IQuery23For47.class);
    try {
      rets = service.queryArrive(queryScheme);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return rets;
  }

  @Override
  protected String getRefOrgFieldCode() {
    return ArriveHeaderVO.PK_ORG;
  }
}
