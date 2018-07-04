package nc.vo.pu.m21.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.pubitf.scmbd.vrm.vm.IVendorMaterialQuery;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmbd.vrm.vm.entity.VendorMaterBVO;
import nc.vo.util.remotecallcombination.RemoteCallCombinatorEx;
import nc.vo.util.remotecallcombination.Token;

import org.apache.commons.lang.StringUtils;

public class VendorMaterial implements IPURemoteCallCombinator {

  private IKeyValue helper;

  private int[] rows;

  private Token token = null;

  public VendorMaterial(IKeyValue helper, int[] rows) {
    this.helper = helper;
    this.rows = rows;
  }

  @Override
  public void prepare() {
    this.registerCombineRemoteCall();
  }

  @Override
  public void process() {
    this.setMaterialInfo();
  }

  public void setMaterialInfo() {
    VendorMaterBVO[] vmbvos = null;
    try {
      if (null != this.token) {
        vmbvos =
            (VendorMaterBVO[]) RemoteCallCombinatorEx.getInstance().getResult(
                this.token);
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    if (null == vmbvos || vmbvos.length == 0) {
      this.clearVendorMaterInfo();
      return;
    }
    Map<String, VendorMaterBVO> vmmap = new HashMap<String, VendorMaterBVO>();
    for (int i = 0; i < vmbvos.length; i++) {
      vmmap.put(vmbvos[i].getPk_material(), vmbvos[i]);
    }
    for (int i = 0; i < this.rows.length; i++) {
      String pk_material =
          (String) this.helper.getBodyValue(this.rows[i],
              OrderItemVO.PK_MATERIAL);
      VendorMaterBVO vmbvo = vmmap.get(pk_material);
      if (vmbvo != null) {
        this.helper.setBodyValue(this.rows[i], OrderItemVO.VVENDINVENTORYCODE,
            vmbvo.getVvendinventorycode());
        this.helper.setBodyValue(this.rows[i], OrderItemVO.VVENDINVENTORYNAME,
            vmbvo.getVvendinventoryname());
      }
      else {
        this.helper.setBodyValue(this.rows[i], OrderItemVO.VVENDINVENTORYCODE,
            null);
        this.helper.setBodyValue(this.rows[i], OrderItemVO.VVENDINVENTORYNAME,
            null);
      }
    }

  }

  private void clearVendorMaterInfo() {
    for (int i = 0; i < this.rows.length; i++) {
      this.helper.setBodyValue(this.rows[i], OrderItemVO.VVENDINVENTORYCODE,
          null);
      this.helper.setBodyValue(this.rows[i], OrderItemVO.VVENDINVENTORYNAME,
          null);
    }
  }

  private boolean doInvoke(RemoteCallCombinatorEx rcc) {
    IVendorMaterialQuery vrmQuery = rcc.getService(IVendorMaterialQuery.class);
    String pk_org = (String) this.helper.getHeadValue(OrderHeaderVO.PK_ORG);
    String vendorid =
        (String) this.helper.getHeadValue(OrderHeaderVO.PK_SUPPLIER);
    if (StringUtils.isBlank(pk_org) || StringUtils.isBlank(vendorid)) {
      return false;
    }
    List<String> marLst = new ArrayList<String>();
    for (int i = 0; i < this.rows.length; i++) {
      String pk_material =
          (String) this.helper.getBodyValue(this.rows[i],
              OrderItemVO.PK_MATERIAL);
      if (StringUtils.isBlank(pk_material)) {
        continue;
      }
      marLst.add(pk_material);
    }
    if (marLst.size() == 0) {
      return false;
    }
    try {
      vrmQuery.queryByVendorIds(new String[] {
        vendorid
      }, marLst.toArray(new String[marLst.size()]), new String[] {
        pk_org
      });
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return true;
  }

  private void registerCombineRemoteCall() {
    RemoteCallCombinatorEx rcc = RemoteCallCombinatorEx.getInstance();
    if (this.token != null) {
      rcc.update(this.token);
      this.doInvoke(rcc);
    }
    else {
      if (this.doInvoke(rcc)) {
        this.token = rcc.getToken();
      }
    }
  }

}
