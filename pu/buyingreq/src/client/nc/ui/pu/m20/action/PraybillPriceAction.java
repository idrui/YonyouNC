/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-4 下午07:53:10
 */
package nc.ui.pu.m20.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m20.IPraybillPriceInfo;
import nc.ui.pu.m20.view.PriceInfoDlg;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.ClientContext;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.vo.pu.m20.entity.PrayPriceInfoVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.uif2.LoginContext;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>价格论证表Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-4 下午07:53:10
 */
public class PraybillPriceAction extends NCAction {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 6144093435749100115L;

  /** 上下文 **/
  private LoginContext context = null;

  private String nodeKey = null;

  private PriceInfoDlg priceInfoDlg = null;

  private IPraybillPriceInfo priceSrv = null;

  private QueryConditionDLG queryDlg = null;

  protected BillManageModel model;

  public PraybillPriceAction() {
    super();
    SCMActionInitializer.initializeAction(this,
        SCMActionCode.PU_PRICEDISCUSSTABLE);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {

    if (this.getDlg().showModal() == UIDialog.ID_OK) {
      String strWhere = this.getDlg().getWhereSQL();
      PrayPriceInfoVO[] priceInfoVOs = this.constructQueryParaVO();
      priceInfoVOs = this.getPriceSrv().queryPriceInfo(priceInfoVOs, strWhere);
      this.getPriceInfoDlg(priceInfoVOs).showModal();

    }
  }

  public LoginContext getContext() {
    return this.context;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public String getNodeKey() {
    return this.nodeKey;
  }

  public void setContext(LoginContext context) {
    this.context = context;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
  }

  public void setNodeKey(String nodeKey) {
    this.nodeKey = nodeKey;
  }

  /**
   * 构造价格信息vo
   * 
   * @return
   */
  private PrayPriceInfoVO[] constructQueryParaVO() {
    PraybillVO vo = (PraybillVO) this.getModel().getSelectedData();
    if (vo == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0029")/*
                                                                   * @res
                                                                   * "请先选择行"
                                                                   */);
    }
    PrayPriceInfoVO[] paraVOs = new PrayPriceInfoVO[vo.getBVO().length];
    UFDate date = ClientContext.getInstance().getBusiDate();
    for (int i = 0; i < paraVOs.length; i++) {
      paraVOs[i] = new PrayPriceInfoVO();
      paraVOs[i].setPk_srcmaterial(vo.getBVO()[i].getPk_srcmaterial());// 物料oid
      paraVOs[i].setPk_material(vo.getBVO()[i].getPk_material());// 物料vid
      paraVOs[i].setPk_org(vo.getBVO()[i].getPk_purchaseorg());// 采购组织
      paraVOs[i].setDcurdate(date);// 当前日期
    }
    return paraVOs;
  }

  /**
   * 查询对话框
   * 
   * @return
   */
  private QueryConditionDLG getDlg() {
    if (this.queryDlg == null) {
      TemplateInfo tempinfo = new TemplateInfo();
      tempinfo.setPk_Org(this.getContext().getPk_group());
      tempinfo.setFunNode(this.getContext().getNodeCode());
      tempinfo.setUserid(this.getContext().getPk_loginUser());
      tempinfo.setNodekey(this.getNodeKey());
      tempinfo.setTemplateId("1002Z810000000004H9V");
      this.queryDlg =
          new QueryConditionDLG(this.getContext().getEntranceUI(), tempinfo);

    }
    return this.queryDlg;
  }

  /**
   * 价格论证表ui
   * 
   * @param priceInfoVOs
   * @return
   */
  private PriceInfoDlg getPriceInfoDlg(PrayPriceInfoVO[] priceInfoVOs) {
    if (this.priceInfoDlg == null) {
      this.priceInfoDlg =
          new PriceInfoDlg(this.getContext().getEntranceUI(), priceInfoVOs,
              this.context);
    }
    else {
      this.priceInfoDlg.setPriceInfoVos(priceInfoVOs);
      this.priceInfoDlg.refreshData();
    }
    return this.priceInfoDlg;
  }

  /**
   * 询价服务
   * 
   * @return
   */
  private IPraybillPriceInfo getPriceSrv() {
    if (this.priceSrv == null) {
      this.priceSrv = NCLocator.getInstance().lookup(IPraybillPriceInfo.class);
    }
    return this.priceSrv;
  }

}
