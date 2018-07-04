package nc.vo.pu.m24.rule;

import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.psn.PsndocPubService;
import nc.itf.scmpub.reference.uap.org.DeptPubService;
import nc.itf.scmpub.reference.uap.rbac.UserManageQuery;
import nc.vo.pu.m24.entity.PricestlHeaderVO;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�۸���㵥Ĭ��ֵ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-8-3 ����05:00:27
 */
public class PricestlSetDefaultRule {

  public PricestlVO[] setDefaultValue(PricestlVO[] vos, String pk_user) {
    if (ArrayUtils.isEmpty(vos)) {
      return vos;
    }

    String psn = UserManageQuery.queryPsndocByUserid(pk_user);

    String dept = null;
    Map<String, List<String>> depts =
        PsndocPubService.queryDeptIDByPsndocIDs(new String[] {
          psn
        });
    if (null != depts && null != depts.get(psn) && depts.get(psn).size() == 1) {
      dept = depts.get(psn).get(0);
    }

    String dept_v = this.getLastdept_v(dept);

    UFDate dbilldate = AppContext.getInstance().getBusiDate();
    for (PricestlVO vo : vos) {
      // ���ò��źͲɹ�Ա
      this.setPsnAndDept(psn, dept, dept_v, vo.getHVO());

      // ���õ�������
      vo.getHVO().setDbilldate(dbilldate);

      vo.getHVO().setStatus(VOStatus.NEW);
    }
    return vos;
  }

  private String getLastdept_v(String dept) {
    Map<String, String> retMap =
        DeptPubService.getLastVIDSByDeptIDS(new String[] {
          dept
        });
    return retMap.get(dept);
  }

  private void setPsnAndDept(String psn, String dept, String dept_v,
      PricestlHeaderVO head) {
    if (head.getPk_employee() == null) {
      head.setPk_employee(psn);
    }
    if (head.getPk_dept() == null) {
      head.setPk_dept(dept);
    }
    if (head.getPk_dept_v() == null) {
      head.setPk_dept_v(dept_v);
    }

  }
}
