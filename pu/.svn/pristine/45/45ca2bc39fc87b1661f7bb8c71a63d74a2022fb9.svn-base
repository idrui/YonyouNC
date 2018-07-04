/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-6 上午10:10:20
 */
package nc.bs.pu.m20.pf.function;

import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.psn.PsndocPubService;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>计划员所属部门检查
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-6 上午10:10:20
 */
public class PsnDept {

  /**
   * 方法功能描述：判断请购单的计划员是否属于输入的计划部门 。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-7-6 上午11:18:02
   */
  public UFBoolean isPsnBelongDept(AggregatedValueObject vo)
      throws BusinessException {
    if (vo == null || vo.getParentVO() == null) {
      return UFBoolean.TRUE;
    }

    PraybillHeaderVO headVO = ((PraybillVO) vo).getHVO();
    String sDeptID = headVO.getPk_plandept();
    String sPsnID = headVO.getPk_planpsn();

    if (sDeptID != null && sDeptID.length() > 0 && sPsnID != null
        && sPsnID.length() > 0) {

      Map<String, List<String>> queryMap =
          PsndocPubService.queryDeptIDByPsndocIDs(new String[] {
            sPsnID
          });

      if (null != queryMap && null != queryMap.get(sPsnID)) {
        List<String> belongDepts = queryMap.get(sPsnID);
        for (String pk_dept : belongDepts) {
          if (sDeptID.equals(pk_dept)) {
            return UFBoolean.TRUE;
          }
        }
      }
    }
    else {
      return UFBoolean.TRUE;
    }

    return UFBoolean.FALSE;
  }
}
