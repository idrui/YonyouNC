/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-5 下午02:46:48
 */
package nc.ui.pu.est.action.m45;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.est.m45.IPurchaseInEstQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.model.ModelDataDescriptor;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pu.est.enumeration.QueryEstType;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.pub.util.VOSortUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

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
@SuppressWarnings("serial")
public class PurchsInCancelEstQueryAction extends PurchaseInEstQueryAction {

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
    this.initBefore();
    PurchaseInEstVO[] vos = null;
    IPurchaseInEstQuery srv =
        NCLocator.getInstance().lookup(IPurchaseInEstQuery.class);
    try {
      vos = srv.unEstQuery(queryScheme);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    VOSortUtils.sortVOs(vos, PurchaseinFIHeaderVO.VBILLCODE);
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
