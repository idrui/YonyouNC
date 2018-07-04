package nc.bs.pu.event;

import nc.itf.scmpub.reference.uap.md.MDQueryFacade;
import nc.md.model.IBean;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

/**
 * @since 6.0
 * @version 2014-3-21 下午03:09:30
 * @author mengjian
 */
public class PUBusinessEventUtil {

  /**
   * 获得vo对应的元数据ID
   * 
   * @param vos
   * @return
   */
  public static String getSourceIDByBillVO(AbstractBill[] vos) {
    IVOMeta meta = ((SuperVO) vos[0].getParent()).getMetaData();
    String beanname = meta.getEntityName();
    IBean bean = MDQueryFacade.getBeanByFullName(beanname);
    String sourceID = bean.getID();
    return sourceID;
  }
}
