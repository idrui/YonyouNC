package nc.ui.pu.pub.atp;

import java.util.ArrayList;
import java.util.HashMap;

import nc.itf.pu.reference.ic.AtpQueryServices;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillData;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.vo.ic.atp.entity.AtpVO;
import nc.vo.org.OrgVO;
import nc.vo.pu.atp.ATPForOneMaterialBillVO;
import nc.vo.pu.atp.ATPForOneMaterialItemVO;
import nc.vo.pu.atp.ATPParamVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.scale.PosEnum;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>存量查询显示对话框
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-9-27 下午03:22:34
 */
public class ATPForOneMaterialMulOrgUI extends nc.ui.pub.beans.UIDialog
    implements nc.ui.pub.pf.IinitData2 {

  private static final long serialVersionUID = 7686998814436197815L;

  private boolean[] bATPShow = {
    true, true, true, true, false, true, true, true
  };

  private boolean[] bATPTotal = {
    false, false, false, false, false, true, true, true
  };

  private BillCardPanel bcpATP = null;

  private BillItem[] biATPs = null;

  private BillItem[] bodyItems = null;

  private int[] iATPDataType = {
    0, 0, 0, 0, 0, 2, 2, 2
  };

  private int[] iATPPos = {
    1, 1, 1, 1, 1, 1, 1, 1
  };

  private String[] sATPKeys = {
    "crowno", "stockorgname", "materialcode", "materialname",
    "cunitid", "handnum", "usablenum", "usablenumbydate"
  };

  private String[][] sATPLoad = {
    null, null, null, null, null, null, null, null
  };

  private String[] sATPNames = {
      nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
          "04004000-0164")/* @res "行号" */,
      nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
          "UC000-0001825")/* @res "库存组织" */,
      nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
          "UC000-0002911")/* @res "物料编码" */,
      nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
          "UC000-0002908")/* @res "物料名称" */,
      nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
          "UC000-0002908")/* @res "物料名称" */,
      nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
          "UC000-0002918")/* @res "现存量" */,
      nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
          "UC000-0001084")/* @res "可用量" */,
      nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
          "04004000-0032")/* @res "计划到货日可用量" */
  };

  /**
   * ATPDialog 构造子注解。
   * 
   * @param parent
   *          java.awt.Container
   */
  public ATPForOneMaterialMulOrgUI(java.awt.Container parent) {
    super(parent, true);
    this.setResizable(true);
    this.initialize();
  }

  /**
   * ATPDialog 构造子注解。
   * 
   * @param parent
   *          java.awt.Container
   * @param title
   *          java.lang.String
   */
  public ATPForOneMaterialMulOrgUI(java.awt.Container parent, String title) {
    super(parent, title, true);
    this.setResizable(true);
    this.initialize();
  }

  /**
   * ATPDialog 构造子注解。
   * 
   * @param owner
   *          java.awt.Frame
   */
  public ATPForOneMaterialMulOrgUI(java.awt.Frame owner) {
    // super(owner);
    super(owner, true);
    this.setResizable(true);
    this.initialize();
  }

  /**
   * ATPDialog 构造子注解。
   * 
   * @param owner
   *          java.awt.Frame
   * @param title
   *          java.lang.String
   */
  public ATPForOneMaterialMulOrgUI(java.awt.Frame owner, String title) {
    super(owner, title, true);
    this.setResizable(true);
    this.initialize();
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-9-27 下午03:24:34
   */
  public BillCardPanel getATPPanel() {
    if (this.bcpATP == null) {
      this.bcpATP = new BillCardPanel();
      this.bcpATP.setName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004000_0", "04004000-0033")/* @res "可用量情况" */);
      this.initPanel();
      BillData billdata = new BillData();
      billdata.setBodyItems(this.bodyItems);
      this.bcpATP.setBillData(billdata);
      this.bcpATP.setBodyMenuShow(false);
      this.bcpATP.setTatolRowShow(true);
      this.bcpATP.setEnabled(false);
    }
    return this.bcpATP;
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-9-27 下午03:24:43
   */
  public UIPanel getContentPanel() {
    UIPanel contentPanel = new UIPanel();
    contentPanel.setLayout(new java.awt.BorderLayout());
    contentPanel.add(this.getATPPanel(), "Center");
    return contentPanel;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pub.pf.IinitData2#initData(java.lang.Object)
   */
  @Override
  public void initData(Object obj) {
    ATPParamVO[] vos = (ATPParamVO[]) obj;

    ATPForOneMaterialBillVO vaATP = this.getAtpInfo(vos);
    if (vaATP != null && vaATP.getChildrenVO() != null) {
      this.getATPPanel().setBillValueVO(vaATP);
      this.getATPPanel().getBillModel().execLoadFormula();
    }
    else {
      this.getATPPanel().getBillModel().clearBodyData();
    }

    this.getATPPanel().updateUI();

  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author gaogr
   * @time 2010-9-27 下午03:29:01
   */
  public void initialize() {
    this.setName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004000_0", "04004000-0035")/* @res "现存量可用量情况" */);
    this.setTitle(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004000_0", "04004000-0035")/* @res "现存量可用量情况" */);
    this.setSize(840, 600);
    this.setContentPane(this.getContentPanel());
    // 设置建议请购量可编辑
    this.getATPPanel().addLine();
    this.getATPPanel()
        .getTotalTableModel()
        .setValueAt(
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
                "04004000-0036")/* @res "     库存组织合计" */, 0, 0);
    this.processScale();
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author gaogr
   * @time 2010-9-27 下午03:28:53
   */
  public void initPanel() {

    this.biATPs = new BillItem[this.sATPNames.length];
    ArrayList<BillItem> bodyItemVector = new ArrayList<BillItem>();
    for (int i = 0; i < this.sATPNames.length; i++) {
      this.biATPs[i] = new BillItem();
      this.biATPs[i].setName(this.sATPNames[i]);
      this.biATPs[i].setKey(this.sATPKeys[i]);
      this.biATPs[i].setPos(this.iATPPos[i]);
      this.biATPs[i].setShow(this.bATPShow[i]);
      this.biATPs[i].setDataType(this.iATPDataType[i]);
      this.biATPs[i].setRefType("");
      this.biATPs[i].setEdit(false);
      this.biATPs[i].setLoadFormula(this.sATPLoad[i]);
      this.biATPs[i].setTatol(this.bATPTotal[i]);
      this.biATPs[i].setWidth(110);
      bodyItemVector.add(this.biATPs[i]);
    }

    this.bodyItems =
        bodyItemVector.toArray(new BillItem[bodyItemVector.size()]);

  }

  /**
   * 方法功能描述：取得显示信息
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   *          <p>
   * @since 6.0
   * @author gaogr
   * @throws BusinessException
   * @time 2010-10-23 下午02:29:45
   */
  private ATPForOneMaterialBillVO getAtpInfo(ATPParamVO[] vos) {
    ATPForOneMaterialBillVO vaATP = null;

    ATPForOneMaterialItemVO[] items = this.getItems(vos);
    if (!ArrayUtils.isEmpty(items)) {
      vaATP = new ATPForOneMaterialBillVO();
      vaATP.setChildrenVO(items);
    }

    return vaATP;
  }

  /**
   * 方法功能描述：取得表体信息(现存量和可用量)
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   *          <p>
   * @since 6.0
   * @author gaogr
   * @throws BusinessException
   * @time 2010-10-23 下午03:12:46
   */
  private ATPForOneMaterialItemVO[] getItems(ATPParamVO[] vos) {

    int len = vos.length;
    String[] pk_orgs = new String[len];
    String[] pk_materials = new String[len];
    UFDate[] denddates = new UFDate[len];
    UFDate[] ddates = new UFDate[len];
    for (int i = 0; i < len; i++) {
      pk_orgs[i] = vos[i].getPk_stockorgs()[0];
      pk_materials[i] = vos[i].getPk_material();
      denddates[i] = vos[i].getEnd_date();
      ddates[i] = null;
    }
    HashMap<String, String> orgname = this.getOrgName(pk_orgs);

    // 是否有展望日期
//    boolean hasEndDate = null != vos[0].getEnd_date();
    AtpVO[] atpvos = null;
    try {
      // 取得现存量和可用量
      atpvos = AtpQueryServices.queryAtpVOs(pk_orgs, pk_materials, ddates);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    ATPForOneMaterialItemVO[] items = new ATPForOneMaterialItemVO[len];
    if(atpvos != null){
      for(int i = 0; i < len ; i++){
        items[i] = new ATPForOneMaterialItemVO();
        items[i].setCrowno(vos[i].getCrowno());
        items[i].setStockorgname(orgname.get(atpvos[i].getPk_org()));
        items[i].setMaterialcode(vos[i].getMaterialCode());
        items[i].setMaterialname(vos[i].getMaterialName());
        items[i].setCunitid(vos[i].getCunitid());
        items[i].setHandnum(atpvos[i].getNonhandnum());
        items[i].setUsablenum(atpvos[i].getNatpnum());
        items[i].setUsablenumbydate(atpvos[i].getNatpnum());
      }
    }

//    // 取得展望量
//    if (hasEndDate) {
//      AtpVO[] atpvoends =
//          AtpQueryServices.queryAtpVOs(pk_orgs, pk_materials, ddates);
//      for (int i = 0; i < len; i++) {
//        items[i].setUsablenumbydate(atpvoends[i].getNatpnum());
//      }
//    }

    return items;
  }

  /**
   * 方法功能描述：取得库存组织名称
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_orgs
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-10-23 下午03:26:48
   */
  private HashMap<String, String> getOrgName(String[] pk_orgs) {
    HashMap<String, String> orgname = new HashMap<String, String>();
    OrgVO[] orgvos = OrgUnitPubService.getOrgsByPks(pk_orgs, new String[] {
      OrgVO.PK_ORG, OrgVO.NAME
    });
    for (int i = 0, len = orgvos.length; i < len; i++) {
      orgname.put(orgvos[i].getPk_org(), orgvos[i].getName());
    }

    return orgname;
  }

  private void processScale() {
    CardPaneScaleProcessor scale = 
        new CardPaneScaleProcessor(AppContext.getInstance().getPkGroup(),
            this.getATPPanel());
    // 数量
    String[] numkeys = new String[] {
        "handnum", "usablenum", "usablenumbydate"
        };

    // 主单位数量精度
    scale.setNumCtlInfo(numkeys, PosEnum.body, null, OrderItemVO.CUNITID, PosEnum.body,
        null);

    scale.process();
  }

}
