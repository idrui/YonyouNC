package nc.ui.pu.m23.action;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pu.pub.action.PuQueryAtpAction;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.IBillItem;
import nc.vo.pu.atp.ATPParamVO;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.lang.UFDate;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>联查存量查询
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-3-4 下午02:27:22
 */
public class LinkATPUIAction extends PuQueryAtpAction {

  private static final long serialVersionUID = -8876691494402335939L;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.action.PuQueryAtpAction#getParamByCardRow(nc.ui.pub.bill.BillCardPanel,
   *      int)
   */
  @Override
  public ATPParamVO[] getParamByCardRow(BillCardPanel panel, int[] rows) {
    List<ATPParamVO> vos = new ArrayList<>();
    for(int i = 0; i < rows.length; i++){
      String pk_org = this.getAppModel().getContext().getPk_org();
      String pk_srcMaterial = (String) panel.getBodyValueAt(rows[i],
          ArriveItemVO.PK_SRCMATERIAL);
      if(pk_srcMaterial == null || pk_org == null){
        continue;
      }
      ATPParamVO vo  = new ATPParamVO();
      vos.add(vo);
      vo.setPk_stockorgs(new String[] {
        pk_org
      });
      vo.setCrowno((String)panel.getBodyValueAt(rows[i],
          ArriveItemVO.CROWNO));
      vo.setCunitid((String)panel.getBodyValueAt(rows[i],
          ArriveItemVO.CUNITID));
      vo.setPk_material((String) panel.getBodyValueAt(rows[i],
          ArriveItemVO.PK_SRCMATERIAL));
      vo.setMaterialCode((String) panel.getBillModel().getValueAt(rows[i],
          ArriveItemVO.PK_MATERIAL));
      vo.setMaterialName(panel.getBodyValueAt(rows[i], ArriveItemVO.PK_MATERIAL
          + ".name"));
      vo.setEnd_date((UFDate) panel.getHeadItem(ArriveHeaderVO.DBILLDATE)
          .getValueObject());
    }
    return vos.toArray(new ATPParamVO[0]);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.action.PuQueryAtpAction#getParamByListRow(nc.ui.pub.bill.BillListPanel,
   *      int)
   */
  @Override
  public ATPParamVO[] getParamByListRow(BillListPanel panel, int[] rows) {
    List<ATPParamVO> vos = new ArrayList<>();
    for(int i = 0; i < rows.length; i++){
      String pk_org = this.getAppModel().getContext().getPk_org();
      ATPParamVO vo  = new ATPParamVO();
      vos.add(vo);
      vo.setPk_stockorgs(new String[] {
        pk_org
      });
      vo.setCrowno((String)panel.getBodyBillModel().getValueAt(rows[i],
          ArriveItemVO.CROWNO));
      vo.setCunitid((String)panel.getBodyBillModel().getValueAt(rows[i],
          ArriveItemVO.CUNITID));
      vo.setPk_material((String) panel.getBodyBillModel().getValueAt(rows[i],
          ArriveItemVO.PK_SRCMATERIAL + IBillItem.ID_SUFFIX));
      vo.setMaterialCode((String) panel.getBodyBillModel().getValueAt(rows[i],
          ArriveItemVO.PK_MATERIAL));
      vo.setMaterialName(panel.getBodyBillModel().getValueAt(rows[i],
          ArriveItemVO.PK_MATERIAL + ".name"));
      vo.setEnd_date(((ArriveVO) this.getAppModel().getSelectedData()).getHVO()
          .getDbilldate());
    }
    return vos.toArray(new ATPParamVO[0]);
  }
}
