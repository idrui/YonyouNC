package nc.ui.pu.m21.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>根据计量单位控制换算率的可编辑性
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-4-29 下午06:35:13
 */
public class EditableByUnit {
  private BillCardPanel panel;

  public EditableByUnit(BillCardPanel panel) {
    this.panel = panel;
  }

  public void setEditable(int[] rows) {
  	/*
  	 * change by wandl
  	 * 批量根据是否固定换算率、主辅计量单位是否有值，处理换算率和报价换算率的可编辑性
  	 * Start
  	 */
  	Map<String,UFBoolean> map = new HashMap<String,UFBoolean>();
  	List<String> pk_materials = new ArrayList<String>();
    //List<String> mainunits = new ArrayList<String>();
    List<String> otherunits = new ArrayList<String>();
  	for(int row : rows) {
  		String material =
          (String) panel.getBodyValueAt(row, OrderItemVO.PK_MATERIAL);
      //String cunitid = (String) panel.getBodyValueAt(row, OrderItemVO.CUNITID);
      String castunitid =
          (String) panel.getBodyValueAt(row, OrderItemVO.CASTUNITID);
      String cqtunitid =
          (String) panel.getBodyValueAt(row, OrderItemVO.CQTUNITID);
      pk_materials.add(material);
      otherunits.add(castunitid);
      //加载俩次物料是为了保证传入接口的物料和单位，报价单位参数一一对应
			if (castunitid != null && cqtunitid != null
					&& !castunitid.equals(cqtunitid)) {
				pk_materials.add(material);
				otherunits.add(cqtunitid);
			}
  	}
  	String[] materials = pk_materials.toArray(new String[0]); 
  	String[] otherunitids = otherunits.toArray(new String[0]);
  	map = MaterialPubService.queryIsFixedRateByMaterialAndMeasdocs(materials, 
  					otherunitids);
  	/*
  	 * end
  	 */
    for (int row : rows) {
      this.setEditable(row,map);
    }
  }

  private void setEditable(int row,Map<String,UFBoolean> map) {
    Object cunitid = this.panel.getBodyValueAt(row, OrderItemVO.CUNITID);
    if (cunitid == null) {
      return;
    }
    String castunitid = (String) this.panel.getBodyValueAt(row, OrderItemVO.CASTUNITID);
		String cqtunitid = (String) this.panel.getBodyValueAt(row, OrderItemVO.CQTUNITID);
    String material = (String) this.panel.getBodyValueAt(row, OrderItemVO.PK_MATERIAL);
    //key：物料+辅单位
    String castKey = material + castunitid;
    //key:物料+报价单位
    String cqKey = material + cqtunitid;
    // 主辅计量相同，不允许修改
    if (cunitid.equals(castunitid)) {
      this.panel.setCellEditable(row, OrderItemVO.VCHANGERATE, false);
    }
    // 主辅计量不同，根据是否固定换算率决定其编辑性
    else if (castunitid != null && material != null) {
      //根据物料+辅单位获取固定换算率
    	UFBoolean fixedRate = map.get(castKey);
      this.panel.setCellEditable(row, OrderItemVO.VCHANGERATE, !fixedRate.booleanValue());
    }
    else {
      this.panel.setCellEditable(row, OrderItemVO.VCHANGERATE, true);
    }

    // 主辅计量相同，不允许修改
    if (cunitid.equals(cqtunitid)) {
      this.panel.setCellEditable(row, OrderItemVO.VQTUNITRATE, false);
    }
    // 主辅计量不同，根据是否固定换算率决定其编辑性
    else if (cqtunitid != null) {
    	////根据物料+报价单位获取固定换算率
      UFBoolean fixedRate = map.get(cqKey);
      this.panel.setCellEditable(row, OrderItemVO.VQTUNITRATE, !fixedRate.booleanValue());
    }
    else {
      this.panel.setCellEditable(row, OrderItemVO.VQTUNITRATE, true);
    }
  }

}
