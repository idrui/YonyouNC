package nc.vo.pu.pub.rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.scmpub.reference.am.AMServiceForPU;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.constant.ForeignForPUConstant;
import nc.vo.pu.pub.enumeration.PuRefBillTypeIdEnum;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.bill.BillRowCompare;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * @description
 *            回写资产配置申请单，是否生成请购单的标志位
 * @scene
 *      回写资产配置申请单
 * @param
 * 
 *
 * @since 6.5
 * @version 2014-3-7 下午03:26:58
 * @author fanly3
 */
public class WriteBackM4A08Rule implements ICompareRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] vos, PraybillVO[] originVOs) {
    if (!SysInitGroupQuery.isAIMEnabled()) {
      return;
    }
    if (ArrayUtils.isEmpty(originVOs)) {
      // 新增
      this.writeBack(vos, UFBoolean.TRUE);
    }
    else if (ArrayUtils.isEmpty(vos)
        || VOStatus.DELETED == vos[0].getParent().getStatus()) {
      // 删除
      // 请购单vos不是空，所以只能根据状态判断
      this.writeBack(vos, UFBoolean.FALSE);
    }
    else {
      // 修改,该方法在资产配置申请取消审批时，如果下游请购单为自由态，并且该请购单有新增行时，
      // 按照需求会删除新增行之外的物料行（删除来源于资产配置申请的物料行）
      this.writeBackWhenM4A08UnApprove(vos, originVOs, UFBoolean.FALSE);
    }
  }

  /**
   * 回写资产配置申请的标志位
   * 
   * @param vos 请购单聚合VO
   * @param flag 标志位值
   */
  private void writeBack(PraybillVO[] vos, UFBoolean flag) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
    String csourceid = null;
    for (PraybillVO vo : vos) {
      PraybillItemVO[] itemVos = vo.getBVO();
      for (PraybillItemVO itemVo : itemVos) {
        if (!StringUtils.isEmpty(itemVo.getCsourceid())
            && PuRefBillTypeIdEnum.M4A08.getBillTypeId().equals(
                itemVo.getCsourcetypecode())) {
          csourceid = itemVo.getCsourceid();
          map.put(itemVo.getCsourcebid(), flag);
        }
      }
    }
    if (map.isEmpty()) {
      return;
    }
    AMServiceForPU.writebackM4A08(POBillType.PrayBill.getCode(),
        ForeignForPUConstant.PURCHASEPLAN, map, csourceid);
  }

  /**
   * 回写资产配置申请的标志位
   * 
   * @param vos 请购单聚合VO
   * @param flag 标志位值
   */
  private void writeBackWhenM4A08UnApprove(PraybillVO[] vos,
      PraybillVO[] originVOs, UFBoolean flag) {
    BillRowCompare tool = new BillRowCompare(vos, originVOs);
    List<ISuperVO> deleteList = tool.getDeleteList();
    if (deleteList.size() == 0) {
      return;
    }
    Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
    String csourceid = null;
    if (deleteList.size() > 0) {
      for (ISuperVO vo : deleteList) {
        PraybillItemVO itemVo = (PraybillItemVO) vo;
        if (!StringUtils.isEmpty(itemVo.getCsourceid())
            && PuRefBillTypeIdEnum.M4A08.getBillTypeId().equals(
                itemVo.getCsourcetypecode())) {
          csourceid = itemVo.getCsourceid();
          map.put(itemVo.getCsourcebid(), flag);
        }
      }
    }
    if (map.isEmpty()) {
      return;
    }
    AMServiceForPU.writebackM4A08(POBillType.PrayBill.getCode(),
        ForeignForPUConstant.PURCHASEPLAN, map, csourceid);
  }
}
