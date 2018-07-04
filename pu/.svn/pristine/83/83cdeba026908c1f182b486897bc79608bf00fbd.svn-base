package nc.ui.pu.m23.billref.ic.m45;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m23.ic.m45.IQuery23For45;
import nc.ui.pu.pub.billref.PuRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.util.VOSortUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

public class RefQueryServiceFor45 extends PuRefQueryService {

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) {
    this.checkQueryCond(queryScheme);
    ArriveVO[] rets = null;
    IQuery23For45 service = NCLocator.getInstance().lookup(IQuery23For45.class);
    try {
      rets = service.queryArrive(queryScheme);
      if (!ArrayUtils.isEmpty(rets)) {
        for (ArriveVO vo : rets) {
          ArriveItemVO[] itemVOs = vo.getBVO();
          VOSortUtils.ascSort(itemVOs, ArriveItemVO.CROWNO);
          vo.setBVO(itemVOs);
        }
      }
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
