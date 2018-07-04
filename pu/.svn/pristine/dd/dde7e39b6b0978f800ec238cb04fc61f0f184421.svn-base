/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-5 下午02:46:48
 */
package nc.ui.pu.est.action.m50;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.est.m50.IVMIEstQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.model.ModelDataDescriptor;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.m50.VmiEstVO;
import nc.vo.pu.est.enumeration.QueryEstType;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.pu.pub.util.VOSortUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-7-5 下午02:46:48
 */
public class VMICancelEstQueryAction extends VMIEstQueryAction {

  /**
   * 
   */
  private static final long serialVersionUID = -3868591768862686610L;

  private void initAfter(EstVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    // 计算合计相关
    EstVOUtil.calculateTotal(vos);
  }

  private void initBefore() {
    // 取消暂估
    this.getContext().setEsttype(QueryEstType.UN_EST);
    // 初始化界面(编辑性及精度等)
    super.initUI();
  }

  @Override
  protected void executeQuery(IQueryScheme queryScheme) {
    this.queryScheme = queryScheme;

    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    this.initBefore();
    VmiEstVO[] vos = null;
    IVMIEstQuery srv = NCLocator.getInstance().lookup(IVMIEstQuery.class);
    try {
      vos = srv.cancelEstQuery(queryScheme, this.getFIOrg(qrySchemeProcessor));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    VOSortUtils.sortVOs(vos, VmiFIHeaderVO.VBILLCODE);
    this.initAfter(vos);

    String schemeName = queryScheme.getName();
    ModelDataDescriptor modelDataDescriptor = null;
    if (!StringUtil.isEmptyWithTrim(schemeName)) {
      modelDataDescriptor = new ModelDataDescriptor(schemeName);
    }
    else {
      modelDataDescriptor =
          new ModelDataDescriptor(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("pubapp_0", "0pubapp-0144")/*@res "查询结果"*/);
    }
    this.getModel().initModel(vos, modelDataDescriptor);
  }
}
