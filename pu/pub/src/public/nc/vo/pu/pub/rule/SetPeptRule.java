package nc.vo.pu.pub.rule;

import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.psn.PsndocPubService;
import nc.itf.scmpub.reference.uap.org.DeptPubService;
import nc.itf.scmpub.reference.uap.rbac.UserManageQuery;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.org.DeptVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.uif2.LoginContext;

import org.apache.commons.lang.StringUtils;

/**
 * 设置部门以及vid
 * 
 * @since 6.0
 * @version 2011-5-26 下午08:27:54
 * @author liuchx
 */
public class SetPeptRule {

  private IKeyValue keyValue;

  // 部门最新版本字段值
  private String pk_dept_vKey;

  // 部门字段值
  private String pk_deptKey;

  private String pk_login_user;

  private String pk_org;

  // 人员字段值
  private String pk_psndocKey;

  /**
   * @param keyvalue
   * @param ctx
   * @param pk_psndocKey // 人员字段值
   * @param pk_deptKey // 部门字段值
   * @param pk_dept_vKey // 部门最新版本字段值
   */
  public SetPeptRule(IKeyValue keyvalue, LoginContext ctx, String pk_psndocKey,
      String pk_deptKey, String pk_dept_vKey) {
    this(keyvalue, pk_psndocKey, pk_deptKey, pk_dept_vKey, ctx
        .getPk_loginUser(), ctx.getPk_org());
  }

  public SetPeptRule(IKeyValue keyvalue, String pk_psndocKey,
      String pk_deptKey, String pk_dept_vKey, String pk_user, String pk_org) {
    this.keyValue = keyvalue;
    this.pk_psndocKey = pk_psndocKey;
    this.pk_deptKey = pk_deptKey;
    this.pk_dept_vKey = pk_dept_vKey;
    this.pk_org = pk_org;
    this.pk_login_user = pk_user;
  }

  public void setPsnAndDept() {
    if (keyValue.getHeadValue(pk_psndocKey) == null) {
      String psnid = UserManageQuery.queryPsndocByUserid(this.pk_login_user);
      this.keyValue.setHeadValue(this.pk_psndocKey, psnid);
    }

    String psn = (String) keyValue.getHeadValue(pk_psndocKey);
    if (StringUtil.isEmptyWithTrim(psn)) {
      return;
    }
    if (StringUtils.isBlank(this.pk_org)) {
      // ExceptionUtils.wrappBusinessException("主组织为空，无法取当前用户默认身份和部门！");
      return;
    }
    MapList<String, String> dept =
        PsndocPubService.queryDeptIDByPsndocIDs(new String[] {
          psn
        }, this.pk_org);

    // Log.error("liuchx :" + SystemUtils.LINE_SEPARATOR);
    // MapUtils.debugPrint(System.out, "dept", dept);
    // Log.error("null != dept:" + (null != dept));
    // Log.error("null != dept.get(psn):" + (null != dept.get(psn)));
    // Log.error("dept size:" + dept.size());

    if (null != dept && null != dept.get(psn) && dept.get(psn).size() == 1) {
      if (this.canSetDept(dept.get(psn).get(0))) {
        this.keyValue.setHeadValue(this.pk_deptKey, dept.get(psn).get(0));
        String dept_v = this.getLastdept_v(dept.get(psn).get(0));
        this.keyValue.setHeadValue(this.pk_dept_vKey, dept_v);
      }

    }
    else {
      this.keyValue.setHeadValue(this.pk_psndocKey, null);
    }

  }

  /**
   * 部门是否组织看见
   * 
   * @param pk_dept
   * @param pk_org
   * @return
   */
  private boolean canSetDept(String pk_dept) {
    DeptVO[] retDept = DeptPubService.queryDeptVOsByPKS(new String[] {
      pk_dept
    });
    return StringUtils.equals(retDept[0].getPk_org(), this.pk_org);

  }

  private String getLastdept_v(String dept) {
    Map<String, String> retMap =
        DeptPubService.getLastVIDSByDeptIDS(new String[] {
          dept
        });
    return retMap.get(dept);
  }

}
