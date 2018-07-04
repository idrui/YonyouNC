package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IOrderQuery;
import nc.itf.pu.m21.IOrderReceivePlan;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.dm.deliverystatus.IDeliveryStatus;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.vo.dm.deliverystatus.SourceBillDeliveryStatus;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-1-26 下午06:16:28
 * @author wuxla
 */

public class TransportStatusAction extends NCAction {

  private static final long serialVersionUID = 5720556150245367173L;

  private BillManageModel model;

  public TransportStatusAction() {
    SCMActionInitializer.initializeAction(this,
        SCMActionCode.PU_TRANSPORTSTATUS);
    // String str = "运输状态";
    // this.putValue(INCAction.CODE, str);
    // this.setBtnName(str);
    // this.putValue(Action.ACCELERATOR_KEY,
    // KeyStroke.getKeyStroke(KeyEvent.VK_R, Event.CTRL_MASK | Event.ALT_MASK));
    // this.putValue(Action.SHORT_DESCRIPTION, str + "(Ctrl+Alt+R)");
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {

    OrderVO orderBill = (OrderVO) this.getModel().getSelectedData();
    if (null == orderBill) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0036")/*
                                                                   * @res
                                                                   * "请选择数据"
                                                                   */);
    }
    OrderItemVO[] childern = orderBill.getBVO();
    if (!SysInitGroupQuery.isDMEnabled()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0069")/*
                                                                   * @res
                                                                   * "运输模块未启用！"
                                                                   */);
    }
    SourceBillDeliveryStatus[] arrs = this.convert(childern);
    IDeliveryStatus service =
        NCUILocator.getInstance().lookup(IDeliveryStatus.class, NCModule.DM);
    boolean isOK =
        service.show(this.getModel().getContext().getEntranceUI(), arrs);
    if (isOK) {
      String pk_order = orderBill.getHVO().getPk_order();
      IOrderQuery query = NCLocator.getInstance().lookup(IOrderQuery.class);
      OrderVO[] vos = query.queryOrderVOsByIds(new String[] {
        pk_order
      }, UFBoolean.FALSE);
      this.getModel().directlyUpdate(vos);
      this.getModel().setUiState(UIState.NOT_EDIT);
    }
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  private SourceBillDeliveryStatus[] convert(OrderItemVO[] bodys) {

    MapList<String, OrderReceivePlanVO> mapList = this.getRPMap(bodys);
    if (null == mapList) {
      return this.convertNoRP(bodys);
    }
    return this.convertRP(bodys, mapList);
  }

  private SourceBillDeliveryStatus[] convertNoRP(OrderItemVO[] bodys) {
    SourceBillDeliveryStatus[] arrs =
        new SourceBillDeliveryStatus[bodys.length];
    for (int i = 0; i < bodys.length; i++) {
      arrs[i] = this.getStatus(bodys[i]);
    }
    return arrs;
  }

  private SourceBillDeliveryStatus[] convertRP(OrderItemVO[] bodys,
      MapList<String, OrderReceivePlanVO> mapList) {
    List<SourceBillDeliveryStatus> list =
        new ArrayList<SourceBillDeliveryStatus>();
    for (OrderItemVO body : bodys) {
      String pk_order_b = body.getPk_order_b();
      List<OrderReceivePlanVO> rpList = mapList.get(pk_order_b);
      if (null == rpList) {
        list.add(this.getStatus(body));
      }
      else {
        list.addAll(this.getStatus(rpList, body));
      }
    }
    return list.toArray(new SourceBillDeliveryStatus[list.size()]);
  }

  private MapList<String, OrderReceivePlanVO> getRPMap(OrderItemVO[] bodys) {
    boolean breceiveplan = false;
    String hid = null;
    for (OrderItemVO body : bodys) {
      if (UFBoolean.TRUE.equals(body.getBreceiveplan())) {
        breceiveplan = true;
        hid = body.getPk_order();
        break;
      }
    }
    if (breceiveplan) {
      IOrderReceivePlan service =
          NCLocator.getInstance().lookup(IOrderReceivePlan.class);
      try {
        OrderReceivePlanVO[] rpVOs = service.queryPlanVOsByHId(hid);
        if (ArrayUtils.isEmpty(rpVOs)) {
          return null;
        }
        MapList<String, OrderReceivePlanVO> mapList =
            new MapList<String, OrderReceivePlanVO>();
        for (OrderReceivePlanVO rpVO : rpVOs) {
          mapList.put(rpVO.getPk_order_b(), rpVO);
        }
        return mapList;
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
    return null;
  }

  private List<SourceBillDeliveryStatus> getStatus(
      List<OrderReceivePlanVO> rpList, OrderItemVO body) {
    List<SourceBillDeliveryStatus> statusList =
        new ArrayList<SourceBillDeliveryStatus>();
    for (OrderReceivePlanVO rpVO : rpList) {
      SourceBillDeliveryStatus status = new SourceBillDeliveryStatus();
      status.setCbill_bid(rpVO.getPk_order_b());
      status.setCbill_id(rpVO.getPk_order());
      status.setCpuorder_bb1id(rpVO.getPk_order_bb1());
      status.setCbilltype(POBillType.Order.getCode());
      status.setCinventoryid(rpVO.getPk_srcmaterial());
      status.setCinventoryid_v(rpVO.getPk_material());
      status.setCrowno(body.getCrowno());
      status.setNnum(rpVO.getNnum());
      status.setNsendnum(rpVO.getNaccumdevnum());
      status
          .setNcansendnum(MathTool.sub(rpVO.getNnum(), rpVO.getNaccumdevnum()));
      status.setBsendendflag(rpVO.getBtransclosed());
      status.setPk_group(rpVO.getPk_group());
      status.setVpuplancode(rpVO.getVbillcode());
      status.setCunitid(rpVO.getCunitid());
      status.setBsendendflag(rpVO.getBtransclosed());
      statusList.add(status);
    }
    return statusList;
  }

  private SourceBillDeliveryStatus getStatus(OrderItemVO body) {
    SourceBillDeliveryStatus status = new SourceBillDeliveryStatus();
    status.setCbill_bid(body.getPk_order_b());
    status.setCbill_id(body.getPk_order());
    status.setCbilltype(POBillType.Order.getCode());
    status.setCinventoryid(body.getPk_srcmaterial());
    status.setCinventoryid_v(body.getPk_material());
    status.setCrowno(body.getCrowno());
    status.setNnum(body.getNnum());
    status.setNsendnum(body.getNaccumdevnum());
    status.setNcansendnum(MathTool.sub(body.getNnum(), body.getNaccumdevnum()));
    status.setBsendendflag(body.getBtransclosed());
    status.setPk_group(body.getPk_group());
    status.setVpuplancode(null);
    status.setCunitid(body.getCunitid());
    status.setBsendendflag(body.getBtransclosed());
    return status;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    if (this.getModel().getAppUiState() == AppUiState.ADD
        || this.getModel().getAppUiState() == AppUiState.EDIT) {
      return false;
    }
    Object[] objs = this.getModel().getSelectedOperaDatas();
    if (objs != null && objs.length > 1) {
      return false;
    }
    OrderVO orderBill = (OrderVO) this.getModel().getSelectedData();
    if (null == orderBill) {
      return false;
    }
    return POEnumBillStatus.APPROVE.toInteger().equals(
        orderBill.getHVO().getForderstatus());
  }
}
