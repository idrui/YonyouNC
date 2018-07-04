package nc.ui.pu.m27.settlebill.editor.org;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.costfactor.ICostFactorQueryService;
import nc.ui.pu.pub.editor.org.AbstractOrgChangedEventHandler;
import nc.ui.pu.pub.editor.org.IOrgChangedEventListener;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������֯�ı༭���¼�������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-2-25 ����09:21:24
 */
public class OrgChangedEventHandler extends AbstractOrgChangedEventHandler {
  /** ������֯�ĳɱ�Ҫ�� **/
  private Map<String, CostfactorVO[]> orgCFMap =
      new HashMap<String, CostfactorVO[]>();

  public CostfactorVO[] getCostfactor(String pk_org) {
    if (StringUtils.isBlank(pk_org)) {
      return null;
    }
    if (this.orgCFMap.containsKey(pk_org)) {
      return this.orgCFMap.get(pk_org);
    }
    try {
      CostfactorVO[] vos =
          NCLocator.getInstance().lookup(ICostFactorQueryService.class)
              .queryCostfacotorIn(pk_org);
      this.orgCFMap.put(pk_org, vos);
      return vos;
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  @Override
  public void registerEventListener(List<IOrgChangedEventListener> listenerList) {
    listenerList.add(new FinanceOrganization(this));
  }

}
