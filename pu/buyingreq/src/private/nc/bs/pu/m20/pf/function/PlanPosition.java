/**
 * $文件说明$
 *
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-6 上午11:24:11
 */
package nc.bs.pu.m20.pf.function;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.env.BSContext;
import nc.itf.scmpub.reference.uap.rbac.UserManageQuery;
import nc.pubitf.pu.position.IQueryPosition;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>计划岗设置检查
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-6 上午11:24:11
 */
public class PlanPosition {

  /**
   * 方法功能描述：根据当前操作员匹配计划岗设置<br>
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-7-5 下午06:19:35
   */
  public UFBoolean isMatchPlanPosition(AggregatedValueObject vo)
      throws BusinessException {
    if (vo == null || vo.getParentVO() == null || vo.getChildrenVO() == null
        || vo.getChildrenVO().length <= 0) {
      return UFBoolean.FALSE;
    }

    PraybillHeaderVO headVO = ((PraybillVO) vo).getHVO();
    String sPsnID = headVO.getPk_planpsn();
    if (StringUtil.isEmptyWithTrim(sPsnID)) {
      return UFBoolean.FALSE;
    }
    String userid = BSContext.getInstance().getUserID();
    String pk_psn = UserManageQuery.queryPsndocByUserid(userid);
    if (!sPsnID.equals(pk_psn)) {
      return UFBoolean.FALSE;
    }
    PraybillItemVO[] children = (PraybillItemVO[]) vo.getChildrenVO();
    ArrayList<String> pks = new ArrayList<String>();
    for (PraybillItemVO child : children) {
      pks.add(child.getPk_material());
    }
    if (pks.size() > 0) {
      String pk_org = headVO.getPk_org();
      List<String> materials = this.getMaterial(pk_org, pk_psn, pks);
      if (null == materials || pks.size() != materials.size()) {
        return UFBoolean.FALSE;
      }
    }
    return UFBoolean.TRUE;
  }

  private List<String> getMaterial(String pk_org, String pk_operator,
      ArrayList<String> pks) {

    IQueryPosition service =
        NCLocator.getInstance().lookup(IQueryPosition.class);
    List<String> materils = null;
    try {
      materils = service.filterMaterialByPlanner(pk_org, pk_operator, pks);
    }
    catch (BusinessException exception) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004020_0", "04004020-0048")/*
                                                                   * @res
                                                                   * "查询计划岗出错！"
                                                                   */);
    }
    return materils;

  }

}
