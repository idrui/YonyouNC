/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-4 下午07:47:28
 */
package nc.ui.pu.m20.action;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pu.pub.action.PuQueryAtpAction;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.IBillItem;
import nc.vo.pu.atp.ATPParamVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pub.lang.UFDate;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>存量查询Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-4 下午07:47:28
 */
public class PraybillATPAction extends PuQueryAtpAction {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 6144093435749100115L;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.action.PuQueryAtpAction#getParamByCardRow(nc.ui.pub.bill.BillCardPanel,
   *      int)
   */
  @Override
  public ATPParamVO[] getParamByCardRow(BillCardPanel panel, int[] rows) {
    List<ATPParamVO> vos = new ArrayList<>();
    for(int i = 0; i< rows.length; i++ ){
      String pk_material = (String) panel.getBodyValueAt(rows[i],
          PraybillItemVO.PK_SRCMATERIAL);
      if(pk_material == null){
        continue;
      }
      ATPParamVO vo  = new ATPParamVO();
      vos.add(vo);
      String pk_org = this.getAppModel().getContext().getPk_org();
      vo.setPk_stockorgs(new String[] {
        pk_org
      });
      vo.setCrowno((String)panel.getBodyValueAt(rows[i],
          PraybillItemVO.CROWNO));
      vo.setCunitid((String)panel.getBodyValueAt(rows[i],
          PraybillItemVO.CUNITID));
      vo.setPk_material((String) panel.getBodyValueAt(rows[i],
          PraybillItemVO.PK_SRCMATERIAL));
      vo.setMaterialCode((String) panel.getBillModel().getValueAt(rows[i],
          PraybillItemVO.PK_MATERIAL));
      vo.setMaterialName(panel.getBodyValueAt(rows[i], 
          PraybillItemVO.PK_MATERIAL + ".name"));
      vo.setEnd_date((UFDate) panel.getBodyValueAt(rows[i],
          PraybillItemVO.DREQDATE));
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
    for(int i = 0; i< rows.length; i++ ){
      ATPParamVO vo  = new ATPParamVO();
      vos.add(vo);
      String pk_org = this.getAppModel().getContext().getPk_org();
      vo.setPk_stockorgs(new String[] {
        pk_org
      });
      vo.setCrowno((String)panel.getBodyBillModel().getValueAt(rows[i],
          PraybillItemVO.CROWNO));
      vo.setCunitid((String)panel.getBodyBillModel().getValueAt(rows[i],
          PraybillItemVO.CUNITID));
      vo.setPk_material((String) panel.getBodyBillModel().getValueAt(rows[i],
          PraybillItemVO.PK_SRCMATERIAL + IBillItem.ID_SUFFIX));
      vo.setMaterialCode((String) panel.getBodyBillModel().getValueAt(rows[i],
          PraybillItemVO.PK_MATERIAL));
      vo.setMaterialName(panel.getBodyBillModel().getValueAt(rows[i], 
          PraybillItemVO.PK_MATERIAL + ".name"));
      vo.setEnd_date((UFDate) panel.getBodyBillModel().getValueAt(rows[i],
          PraybillItemVO.DREQDATE));
    }
    return vos.toArray(new ATPParamVO[0]);
  }
}
