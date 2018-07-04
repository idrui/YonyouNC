package nc.vo.pu.pub.iadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.pm.PMServiceForSCM;
import nc.pubitf.pbm.material.scm.IMaterialForSCMService;
import nc.vo.pbm.materialstock.MaterialStockBodyFOrSCMVO;
import nc.vo.pbm.materialstocklog.MaterialNumCtlInfoMSG;
import nc.vo.pu.m422x.context.StorereqContext;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pu.pub.exception.PUBudgetControlCheckException;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.model.tool.VOTool;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @since 6.36
 * @version 2015-1-25 下午10:27:59
 * @author mengjian
 */
public class PimForPUInterfaceAdapter<E extends AbstractBill> {

  private String cprojectid = PuAttrNameEnum.cprojectid.name();

  private String cprojecttaskid = PuAttrNameEnum.cprojecttaskid.name();

  private String csourcebid = PuAttrNameEnum.csourcebid.name();

  private String csourceid = PuAttrNameEnum.csourceid.name();

  private String csourcetypecode = PuAttrNameEnum.csourcetypecode.name();

  private String ctrantypeid = PuAttrNameEnum.ctrantypeid.name();

  private String dbilldate = PuAttrNameEnum.dbilldate.name();

  private String nnum = PuAttrNameEnum.nnum.name();

  private String pk_billtype;

  private String pk_material = PuAttrNameEnum.pk_material.name();

  private String pk_src_material = PuAttrNameEnum.pk_srcmaterial.name();

  private String vsourcecode = PuAttrNameEnum.vsourcecode.name();

  private String vsourcetrantype = PuAttrNameEnum.vsourcetrantype.name();

  private String vtrantypecode = PuAttrNameEnum.vtrantypecode.name();

  public PimForPUInterfaceAdapter(String pk_billtype) {
    this.pk_billtype = pk_billtype;
  }

  public String getCprojectid() {
    return this.cprojectid;
  }

  public String getCprojecttaskid() {
    return this.cprojecttaskid;
  }

  public String getPk_material() {
    return this.pk_material;
  }

  public String getPk_src_material() {
    return this.pk_src_material;
  }

  public void setCprojectid(String cprojectid) {
    this.cprojectid = cprojectid;
  }

  public void setCprojecttaskid(String cprojecttaskid) {
    this.cprojecttaskid = cprojecttaskid;
  }

  public void setPk_material(String pk_material) {
    this.pk_material = pk_material;
  }

  public void setPk_src_material(String pk_src_material) {
    this.pk_src_material = pk_src_material;
  }

  public void writebackWhenDelete(E[] originVOs) {
    List<MaterialStockBodyFOrSCMVO> deleteList =
        new ArrayList<MaterialStockBodyFOrSCMVO>();
    IVOMeta meta = originVOs[0].getMetaData().getChildren()[0];
    for (E originVO : originVOs) {
      ISuperVO parentVO = originVO.getParent();
      ISuperVO[] itemVOs = originVO.getChildren(meta);
      if (ArrayUtils.isEmpty(itemVOs)) {
        continue;
      }
      for (ISuperVO itemVO : itemVOs) {
        this.processRow(deleteList, parentVO, itemVO);
      }
    }

    if (deleteList.size() > 0) {
      if (POBillType.MRBill.getCode().equals(this.pk_billtype)) {
        this.updatePMWhenDeleteFor422X(deleteList
            .toArray(new MaterialStockBodyFOrSCMVO[deleteList.size()]));
      }
      else {
        PMServiceForSCM.updatePMWhenDelete(deleteList
            .toArray(new MaterialStockBodyFOrSCMVO[deleteList.size()]));
      }
    }
  }

  public void writebackWhenInsert(E[] vos) {
    List<MaterialStockBodyFOrSCMVO> insertList =
        new ArrayList<MaterialStockBodyFOrSCMVO>();
    IVOMeta meta = vos[0].getMetaData().getChildren()[0];
    for (E vo : vos) {
      ISuperVO parentVO = vo.getParent();
      ISuperVO[] itemVOs = vo.getChildren(meta);
      if (ArrayUtils.isEmpty(itemVOs)) {
        continue;
      }
      for (ISuperVO itemVO : itemVOs) {
        this.processRow(insertList, parentVO, itemVO);
      }
    }

    if (insertList.size() > 0) {
      if (POBillType.MRBill.getCode().equals(this.pk_billtype)) {
        this.updatePMWhenInsertFor422X(insertList
            .toArray(new MaterialStockBodyFOrSCMVO[insertList.size()]));
      }
      else {
        PMServiceForSCM.updatePMWhenInsert(insertList
            .toArray(new MaterialStockBodyFOrSCMVO[insertList.size()]));
      }
    }
  }

  public void writebackWhenUpdate(E[] vos, E[] originVOs) {
    IVOMeta childMeta = vos[0].getMetaData().getChildren()[0];
    Map<String, Map<String, ISuperVO>> map =
        new HashMap<String, Map<String, ISuperVO>>();
    for (IBill bill : originVOs) {
      Map<String, ISuperVO> voIndex = new HashMap<String, ISuperVO>();
      ISuperVO parentVO = bill.getParent();
      ISuperVO[] itemVOs = bill.getChildren(childMeta);
      for (ISuperVO vo : itemVOs) {
        voIndex.put(vo.getPrimaryKey(), vo);
      }
      map.put(parentVO.getPrimaryKey(), voIndex);
    }

    VOTool tool = new VOTool();
    List<MaterialStockBodyFOrSCMVO> updateList =
        new ArrayList<MaterialStockBodyFOrSCMVO>();
    List<MaterialStockBodyFOrSCMVO> updateOriginList =
        new ArrayList<MaterialStockBodyFOrSCMVO>();
    for (IBill bill : vos) {
      ISuperVO parentVO = bill.getParent();
      String parentkey = parentVO.getPrimaryKey();
      Map<String, ISuperVO> voIndex = map.get(parentkey);
      ISuperVO[] itemVOs = bill.getChildren(childMeta);
      for (ISuperVO itemVO : itemVOs) {
        String key = itemVO.getPrimaryKey();
        // 新旧VO中都有当前行
        if (voIndex.containsKey(key)) {
          ISuperVO originVO = voIndex.get(key);
          voIndex.remove(key);
          // 两个行没有改变
          if (tool.getDifferentField(itemVO, originVO).size() == 0) {
            continue;
          }
          this.processRow(updateList, parentVO, itemVO);
          this.processRow(updateOriginList, parentVO, originVO);
        }
        else {
          this.processRow(updateList, parentVO, itemVO);
          updateOriginList.add(null);
        }
      }
      int deletesize = voIndex.values().size();
      if (deletesize > 0) {
        ISuperVO[] deleteVOs =
            voIndex.values().toArray(new ISuperVO[deletesize]);
        for (ISuperVO originVO : deleteVOs) {
          this.processRow(updateOriginList, parentVO, originVO);
          updateList.add(null);
        }
      }
    }

    if (updateList.size() > 0) {
      MaterialStockBodyFOrSCMVO[] updateBillVOs =
          updateList.toArray(new MaterialStockBodyFOrSCMVO[updateList.size()]);
      MaterialStockBodyFOrSCMVO[] updateOriginVOs =
          updateOriginList
              .toArray(new MaterialStockBodyFOrSCMVO[updateOriginList.size()]);
      if (POBillType.MRBill.getCode().equals(this.pk_billtype)) {
        this.updatePMWhenUpdateFor422X(updateBillVOs, updateOriginVOs);
      }
      else {
        PMServiceForSCM.updatePMWhenUpdate(updateBillVOs, updateOriginVOs);
      }
    }
  }

  private MaterialStockBodyFOrSCMVO getPara(ISuperVO parentVO, ISuperVO itemVO) {
    MaterialStockBodyFOrSCMVO vo = new MaterialStockBodyFOrSCMVO();
    vo.setSrc_bill_type(this.pk_billtype);
    vo.setSrc_pk_bill(parentVO.getPrimaryKey());
    vo.setSrc_pk_bill_b(itemVO.getPrimaryKey());
    vo.setSrc_pk_transitype((String) parentVO
        .getAttributeValue(this.ctrantypeid));
    vo.setSrc_transi_type((String) parentVO
        .getAttributeValue(this.vtrantypecode));
    vo.setPk_project((String) itemVO.getAttributeValue(this.cprojectid));
    vo.setPk_wbs((String) itemVO.getAttributeValue(this.cprojecttaskid));
    vo.setPk_material((String) itemVO.getAttributeValue(this.pk_src_material));
    vo.setPk_material_v((String) itemVO.getAttributeValue(this.pk_material));
    vo.setNnum((UFDouble) itemVO.getAttributeValue(this.nnum));
    vo.setDbusidate((UFDate) parentVO.getAttributeValue(this.dbilldate));

    // 来源单据主键
    vo.setOri_pk_bill((String) itemVO.getAttributeValue(this.csourceid));
    // 来源单据行主键
    vo.setOri_pk_bill_b((String) itemVO.getAttributeValue(this.csourcebid));
    // 来源单据类型
    vo.setOri_bill_type((String) itemVO.getAttributeValue(this.csourcetypecode));
    // 来源交易类型主键
    vo.setOri_pk_transitype((String) itemVO
        .getAttributeValue(this.vsourcetrantype));
    // 来源单据号
    vo.setBill_code((String) itemVO.getAttributeValue(this.vsourcecode));

    return vo;
  }

  private void processRow(List<MaterialStockBodyFOrSCMVO> list,
      ISuperVO parentVO, ISuperVO itemVO) {
    MaterialStockBodyFOrSCMVO vo = this.getPara(parentVO, itemVO);
    list.add(vo);
  }

  private void updatePMWhenDeleteFor422X(MaterialStockBodyFOrSCMVO[] array) {
    PMServiceForSCM.updatePMWhenDelete(array);
  }

  private void updatePMWhenInsertFor422X(MaterialStockBodyFOrSCMVO[] insVos) {
    try {
      StorereqContext context = StorereqContext.getInstance();
      IMaterialForSCMService service =
          NCLocator.getInstance().lookup(IMaterialForSCMService.class);
      if (!context.isbBudgetControlCheckFlag()) {
        MaterialNumCtlInfoMSG msg = service.updatePMWhenInsert(insVos);
        if (msg != null
            && (StringUtils.isNotEmpty(msg.getReturnMsg()) || StringUtils
                .isNotEmpty(msg.getBudgetCtrlMsg()))) {
          String returnmsg =
              msg.getReturnMsg() == null ? "" : msg.getReturnMsg();
          String budgetCtrlMsg =
              msg.getBudgetCtrlMsg() == null ? "" : msg.getBudgetCtrlMsg();
          String showmsg =
              returnmsg.length() <= 0 ? budgetCtrlMsg : returnmsg + "\n"
                  + budgetCtrlMsg;
          throw new PUBudgetControlCheckException(showmsg);
        }
      }
      else {
        service.updatePMWhenInsert(insVos);
        context.setbBudgetControlCheckFlag(false);
      }
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
  }

  private void updatePMWhenUpdateFor422X(
      MaterialStockBodyFOrSCMVO[] updateBillVOs,
      MaterialStockBodyFOrSCMVO[] updateOriginVOs) {
    try {
      StorereqContext context = StorereqContext.getInstance();
      IMaterialForSCMService service =
          NCLocator.getInstance().lookup(IMaterialForSCMService.class);
      if (!context.isbBudgetControlCheckFlag()) {
        MaterialNumCtlInfoMSG msg =
            service.updatePMWhenUpdate(updateBillVOs, updateOriginVOs);
        if (msg != null
            && (StringUtils.isNotEmpty(msg.getReturnMsg()) || StringUtils
                .isNotEmpty(msg.getBudgetCtrlMsg()))) {
          String returnmsg =
              msg.getReturnMsg() == null ? "" : msg.getReturnMsg();
          String budgetCtrlMsg =
              msg.getBudgetCtrlMsg() == null ? "" : msg.getBudgetCtrlMsg();
          String showmsg =
              returnmsg.length() <= 0 ? budgetCtrlMsg : returnmsg + "\n"
                  + budgetCtrlMsg;
          throw new PUBudgetControlCheckException(showmsg);
        }
      }
      else {
        service.updatePMWhenUpdate(updateBillVOs, updateOriginVOs);
        context.setbBudgetControlCheckFlag(false);
      }
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
  }

}
