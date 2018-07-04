package nc.ui.pu.m23.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m23.fa.IArriveForFA;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.ui.pu.m23.view.ArriveCardForm;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.editor.BillListView;
import nc.vo.bd.material.IMaterialEnumConst;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.utils.ArrivePublicUtil;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单 生成资产卡片 按钮处理类Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-12 下午02:15:12
 */
public class CrtFACardUIAction extends NCAction {

  private static final long serialVersionUID = -2162719255555892367L;

  private ArriveCardForm billForm;

  private BillListView list;

  private BillManageModel model;

  public CrtFACardUIAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_GENASSETCARD);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (!SysInitGroupQuery.isAIMEnabled()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0182")/*
                                                                   * @res
                                                                   * "资产信息管理模块未启用,无法生成设备卡片!"
                                                                   */);
    }
    ArriveVO oldvo = (ArriveVO) this.model.getSelectedData();
    ArriveItemVO[] bvos = this.getBVOs(oldvo);
    if (bvos.length == 0 || bvos[0] == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0005")/*
                                                                   * @res
                                                                   * "请选中表体行"
                                                                   */);
    }
    ArriveVO vo = (ArriveVO) oldvo.clone();
    vo.setBVO(bvos);
    ArriveVO[] vos = new ArriveVO[] {
      vo
    };
    ClientBillToServer<ArriveVO> tool = new ClientBillToServer<ArriveVO>();
    ArriveVO[] lightVOs = tool.construct(vos, vos);

    lightVOs =
        NCLocator.getInstance().lookup(IArriveForFA.class)
            .createFACard(lightVOs);

    new ClientBillCombinServer<ArriveVO>().combine(new ArriveVO[] {
      oldvo
    }, lightVOs);

    this.model.directlyUpdate(oldvo);
    ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl
        .getNCLangRes().getStrByID("4004040_0", "04004040-0006")/*
                                                                 * @res
                                                                 * "成功生成设备卡片"
                                                                 */, this.model
        .getContext());
  }

  public ArriveCardForm getBillForm() {
    return this.billForm;
  }

  public BillListView getList() {
    return this.list;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public void setBillForm(ArriveCardForm billForm) {
    this.billForm = billForm;
    this.billForm.getModel().addAppEventListener(this);
  }

  public void setList(BillListView list) {
    this.list = list;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  private ArriveItemVO[] getBVOs(ArriveVO vo) {
    if (!POEnumBillStatus.APPROVE.value().equals(vo.getHVO().getFbillstatus())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0007")/*
                                                                   * @res
                                                                   * "到货单必须审核"
                                                                   */);
    }
    int[] rows = null;
    // 卡片界面
    if (((ShowUpableBillForm) this.billForm).isComponentVisible()) {
      BillCardPanel panel = this.billForm.getBillCardPanel();
      rows = panel.getBodyPanel().getTable().getSelectedRows();
    }
    else {
      // 列表界面
      BillListPanel panel = this.list.getBillListPanel();
      rows = panel.getBodyTable().getSelectedRows();
    }
    if (rows == null || rows.length == 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0008")/*
                                                                   * @res
                                                                   * "请选择到货单行数据"
                                                                   */);
    }
    BillIndex index = new BillIndex(new ArriveVO[] {
      vo
    });
    IVOMeta meta = vo.getMetaData().getVOMeta(ArriveItemVO.class);
    Map<String, UFBoolean> matmap = this.getEquipMap(vo);
    List<ArriveItemVO> arriveitemlist = new ArrayList<ArriveItemVO>();
    for (int row : rows) {
      String pk_arriveorder_b = null;
      if (((ShowUpableBillForm) this.billForm).isComponentVisible()) {
        pk_arriveorder_b =
            (String) this.getBillForm().getBillCardPanel()
                .getBodyValueAt(row, ArriveItemVO.PK_ARRIVEORDER_B);
      }
      else {
        pk_arriveorder_b =
            (String) this.list.getBillListPanel().getBodyBillModel()
                .getValueAt(row, ArriveItemVO.PK_ARRIVEORDER_B);
      }
      ArriveItemVO itemVO = (ArriveItemVO) index.get(meta, pk_arriveorder_b);
      if (itemVO != null
          && UFBoolean.TRUE.equals(matmap.get(itemVO.getPk_material()))
          && !UFBoolean.TRUE.equals(itemVO.getBfaflag())
          && !UFBoolean.TRUE.equals(itemVO.getBtransasset())
          && MathTool.nvl(itemVO.getNaccumstorenum()).doubleValue() <= 0) {
        UFDouble nastnum = itemVO.getNastnum();
        if (nastnum != null) {
          if (nastnum.compareTo(new UFDouble(5000)) > 0) {
            ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4004040_0", "04004040-0009")/*
                                                                         * @res
                                                                         * "到货单行"
                                                                         */
                + itemVO.getCrowno()
                + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                    "4004040_0", "04004040-0010")/*
                                                  * @res
                                                  * "为设备物料,数量最大为5000，请进行分批到货"
                                                  */);
          }
          UFDouble mod = nastnum.mod(new UFDouble(2));
//          double d = nastnum.getDouble();
//          UFDouble mod = new UFDouble(d % 2);
          if (!mod.equals(UFDouble.ZERO_DBL) && !mod.equals(UFDouble.ONE_DBL)) {
            ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4004040_0", "04004040-0009")/*
                                                                         * @res
                                                                         * "到货单行"
                                                                         */
                + itemVO.getCrowno()
                + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                    "4004040_0", "04004040-0011")/*
                                                  * @res
                                                  * "为设备物料,数量必须为正整数，请先检验"
                                                  */);
          }
        }
        MaterialStockVO marvo =
            ArrivePublicUtil.queryMaterialStockInfo(new ArriveItemVO[] {
              itemVO
            }).get(pk_arriveorder_b);
        if (marvo == null) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4004040_0", "04004040-0009")/*
                                                                       * @res
                                                                       * "到货单行"
                                                                       */
              + itemVO.getCrowno()
              + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4004040_0", "04004040-0012")/*
                                                * @res
                                                * "查询物料的库存页签属性时，找不到对应的物料信息"
                                                */);
        }
        else if (UFBoolean.FALSE.equals(marvo.getChkfreeflag())
            && UFBoolean.TRUE.equals(marvo.getStockbycheck())) {
          if (MathTool.compareTo(
              MathTool.add(itemVO.getNelignum(), itemVO.getNnotelignum()),
              UFDouble.ZERO_DBL) == 0) {
            ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4004040_0", "04004040-0009")/*
                                                                         * @res
                                                                         * "到货单行"
                                                                         */
                + itemVO.getCrowno()
                + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                    "4004040_0", "04004040-0013")/* @res "物料非免检，请先检验" */);
          }
        }
        arriveitemlist.add(itemVO);
      }
    }

    if (0 == arriveitemlist.size()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0014")/*
                                                                   * @res
                                                                   * "无符合条件可生成设备卡片的到货单行记录"
                                                                   */);
    }

    ArriveItemVO[] itemVOs =
        arriveitemlist.toArray(new ArriveItemVO[arriveitemlist.size()]);
    return itemVOs;
  }

  private Map<String, UFBoolean> getEquipMap(ArriveVO vo) {
    Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
    if (ArrayUtils.isEmpty(vo.getBVO())) {
      return map;
    }
    Set<String> materialSet = new HashSet<String>();
    for (ArriveItemVO item : vo.getBVO()) {
      materialSet.add(item.getPk_material());
    }
    Map<String, MaterialVO> result =
        MaterialPubService.queryMaterialBaseInfo(
            materialSet.toArray(new String[materialSet.size()]), new String[] {
              MaterialVO.MATERIALMGT
            });
    if (result == null) {
      return map;
    }
    for (Map.Entry<String, MaterialVO> ite : result.entrySet()) {
      UFBoolean isEquip =
          ite.getValue().getMaterialmgt() == null ? UFBoolean.FALSE
              : UFBoolean
                  .valueOf(ite.getValue().getMaterialmgt().intValue() == IMaterialEnumConst.MATERIALMGT_EQUIP);
      map.put(ite.getKey(), isEquip);
    }
    return map;
  }

  @Override
  protected boolean isActionEnable() {
    if (this.model.getAppUiState() == AppUiState.EDIT
        || this.model.getSelectedData() == null) {
      return false;
    }
    ArriveVO vo = (ArriveVO) this.model.getSelectedData();

    if (!POEnumBillStatus.APPROVE.value().equals(vo.getHVO().getFbillstatus())) {
      return false;
    }
    return true;
  }

}
