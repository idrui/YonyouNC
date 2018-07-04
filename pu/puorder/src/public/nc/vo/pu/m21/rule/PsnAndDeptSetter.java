package nc.vo.pu.m21.rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.psn.PsndocPubService;
import nc.pubitf.pu.position.IQueryPosition;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since 6.0
 * @version 2011-4-15 上午09:54:27
 * @author wuxla
 */

public class PsnAndDeptSetter implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    this.setDefaultPsnAndDept(vos);
  }

  /**
   * 设置采购员和采购部门
   * 
   * @param vos
   */
  private void setDefaultPsnAndDept(OrderVO[] vos) {
    List<String> orgList = new ArrayList<String>();
    List<String> matList = new ArrayList<String>();
    List<String> arriveOrg = new ArrayList<String>();

    for (OrderVO vo : vos) {
      orgList.add(vo.getHVO().getPk_org());
      for (OrderItemVO itemVO : vo.getBVO()) {
        matList.add(itemVO.getPk_material());
        arriveOrg.add(itemVO.getPk_arrvstoorg());
      }
    }

    Map<String, String> poMap = null;
    IQueryPosition service =
        NCLocator.getInstance().lookup(IQueryPosition.class);
    try {
      poMap =
          service.getPurchaser(orgList.toArray(new String[orgList.size()]),
              arriveOrg.toArray(new String[arriveOrg.size()]),
              matList.toArray(new String[matList.size()]));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    if (null == poMap) {
      return;
    }

    Collection<String> psnSet = poMap.values();
    Map<String, List<String>> deptMap = null;
    deptMap =
        PsndocPubService.queryDeptIDByPsndocIDs(psnSet
            .toArray(new String[psnSet.size()]));

    if (null == deptMap) {
      return;
    }
    for (OrderVO vo : vos) {
      String pk_org = vo.getHVO().getPk_org();
      String pk_material = vo.getBVO()[0].getPk_material();
      String pk_psn = poMap.get(pk_org + pk_material);
      if (null == pk_psn) {
        ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004030_0", "04004030-0325")/* 请设置采购岗 */);
      }
      vo.getHVO().setCemployeeid(pk_psn);
      List<String> dept = deptMap.get(pk_psn);
      if (null == dept) {
        ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004030_0", "04004030-0326")/* 请设置人员对于部门 */);
      }
      vo.getHVO().setPk_dept(dept.get(0));
      vo.getHVO().setPk_dept_v(dept.get(0));
    }

  }
}
