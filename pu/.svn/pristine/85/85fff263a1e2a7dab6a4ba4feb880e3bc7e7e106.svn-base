package nc.ui.pu.position.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.position.IPosition;
import nc.ui.pu.position.view.PositionListEditor;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.actions.billlist.SaveAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.UIState;
import nc.vo.pu.position.entity.PositionItemVO;
import nc.vo.pu.position.entity.PositionVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.vo.PseudoColumnAttribute;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>保存Action
 * </ul>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2009-12-28 下午04:33:56
 */
public class PositionSaveAction extends SaveAction {
  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 7554992152712238699L;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    BillModel bm_body_kd =
        ((PositionListEditor) this.getEditor()).getBillListPanel()
            .getBodyBillModel();
    if (bm_body_kd.getRowCount() == 0) {
      throw new BusinessException(nc.ui.ml.NCLangRes.getInstance().getStrByID(
          "4004073005", "UPP4004073005-000002")/* @res "表体行不能为空" */);
    }
    PositionItemVO[] items =
        (PositionItemVO[]) bm_body_kd
            .getBodyValueChangeVOs(PositionItemVO.class.getName());
    for (PositionItemVO billItem : items) {
      PositionItemVO itemVO = billItem;
      if (itemVO.getPk_marbasclass() == null
          && itemVO.getPk_srcmaterial() == null
          && itemVO.getPk_marpuclass() == null) {
        throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004080_0", "04004080-0003")/* @res "表体行不可为空！" */);
      }
    }

    Object value = this.getEditor().getValue();
    this.getItem(value);

    this.setFakeRowNO(value);
    this.validate(value);

    PositionVO qlVO = (PositionVO) value;

    // 检查输入内容是否正确

    ClientBillToServer<PositionVO> tool = new ClientBillToServer<PositionVO>();

    PositionVO[] clientVOs = new PositionVO[] {
      qlVO
    };

    IPosition qlService = NCLocator.getInstance().lookup(IPosition.class);

    // 增加按钮
    if (UIState.ADD == this.getModel().getUiState()) {

      // 取得轻量级VO
      PositionVO[] lightVOs = tool.constructInsert(clientVOs);

      PositionVO afterUpdateVOs = qlService.insert(lightVOs)[0];

      // clientVOs为界面上的数据，afterUpdateVOs为后台返回的差异数据
      new ClientBillCombinServer<PositionVO>().combine(clientVOs,
          new PositionVO[] {
            afterUpdateVOs
          });

      this.getModel().setUiState(UIState.NOT_EDIT);

      this.getModel().directlyAdd(clientVOs[0]);
      ((BillManageModel) this.getModel()).setSelectedOperaRows(new int[] {
        ((BillManageModel) this.getModel()).getSelectedRow()
      });
    }
    // 修改按钮
    else {

      PositionVO[] oldVO = new PositionVO[] {
        (PositionVO) this.getModel().getSelectedData()
      };

      // 取得轻量级VO
      PositionVO[] lightVOs = tool.construct(oldVO, clientVOs);

      PositionVO afterUpdateVOs = qlService.update(lightVOs)[0];

      // clientVOs为界面上的数据，afterUpdateVOs为后台返回的差异数据
      new ClientBillCombinServer<PositionVO>().combine(clientVOs,
          new PositionVO[] {
            afterUpdateVOs
          });

      this.getModel().setUiState(UIState.NOT_EDIT);
      this.getModel().directlyUpdate(clientVOs[0]);
    }
    ((PositionListEditor) this.getEditor()).getBillListPanel()
        .setEnabled(false);
    ((PositionListEditor) this.getEditor()).setMultiSelectionEnable(true);
    ((PositionListEditor) this.getEditor()).getBillListPanel().getHeadTable()
        .setSortEnabled(true);
    ((PositionListEditor) this.getEditor()).getBillListPanel().updateUI();
    this.showSuccessInfo();
  }

  private void getItem(Object value) {
    PositionVO bill = (PositionVO) value;
    if (null == bill || ArrayUtils.isEmpty(bill.getBVO())) {
      return;
    }

    PositionItemVO[] items = bill.getBVO();
    ArrayList<PositionItemVO> ls = new ArrayList<PositionItemVO>();
    for (PositionItemVO item : items) {
      if (item.getStatus() != VOStatus.DELETED) {
        ls.add(item);
      }
    }

    items = ls.toArray(new PositionItemVO[ls.size()]);
    bill.setBVO(items);

  }

  private void setFakeRowNO(Object value) {
    if (value instanceof AggregatedValueObject) {
      if (value instanceof AbstractBill) {
        AbstractBill bill = (AbstractBill) value;
        IVOMeta[] voMetas = bill.getMetaData().getChildren();
        if (voMetas == null) {
          return;
        }
        // 处理多子表
        for (IVOMeta voMeta : voMetas) {
          ISuperVO[] vos = bill.getChildren(voMeta);
          if (vos != null) {
            for (int i = 0; i < vos.length; i++) {
              vos[i].setAttributeValue(PseudoColumnAttribute.PSEUDOCOLUMN,
                  Integer.valueOf(i));
            }
          }
        }
      }
      else {
        CircularlyAccessibleValueObject[] childrenVO =
            ((AggregatedValueObject) value).getChildrenVO();
        if (childrenVO != null) {
          for (int i = 0; i < childrenVO.length; i++) {
            childrenVO[i].setAttributeValue(PseudoColumnAttribute.PSEUDOCOLUMN,
                Integer.valueOf(i));
          }
        }
      }
    }

  }
}
