/**
 * $文件说明$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-19 上午09:03:51
 */
package nc.bs.pu.est.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.itf.scmpub.reference.uap.bd.accesor.MaterialAccessor;
import nc.vo.bd.accessor.IBDData;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>检查是否存在已经暂估的过费用项
 * <li>只有纯费用暂估才使用此规则
 * <li>货物和费用一起暂估的不用加此规则校验
 * <li>因为：可以货物暂估，说明未结算完毕，也未暂估完毕，在此条件下不可能做过费用暂估
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-6-19 上午09:03:51
 */
public class EstimatedFeeChkRule {

  public void process(EstVO[] vos, EstVO[] originVOs) {
    StringBuilder msg = new StringBuilder();
    for (int i = 0; i < vos.length; i++) {
      this.check(vos[i], originVOs[i], msg);
    }
    if (0 < msg.length()) {
      ExceptionUtils.wrappBusinessException(msg.toString());
    }
  }

  private void check(EstVO vo, EstVO orgVo, StringBuilder msg) {
    if (ArrayUtils.isEmpty(vo.getChildrenVO())
        || ArrayUtils.isEmpty(orgVo.getChildrenVO())) {
      return;
    }
    Set<String> materials =
        CirVOUtil.getDistinctFieldSet(orgVo.getChildrenVO(),
            FeeEstVO.PK_SRCFEEMATERIAL);
    List<String> estimated = new ArrayList<String>();
    for (FeeEstVO fee : vo.getChildrenVO()) {
      String material = fee.getPk_srcfeematerial();
      if (materials.contains(material)) {
        estimated.add(fee.getPk_feematerial());
      }
    }
    if (0 < estimated.size()) {
      String billcode = vo.getParentVO().getVbillcode();
      String rowno = vo.getParentVO().getCrowno();
      if (0 < msg.length()) {
        msg.append(System.getProperty("line.separator"));
      }
      msg.append(NCLangResOnserver.getInstance().getStrByID("4004060_0", "04004060-0209", null, new String[]{billcode,rowno})/*单据号：{0}行号：{1}以下费用项已经进行过暂估，不可再暂估：*/);
      msg.append(System.getProperty("line.separator"));
      Map<String, String> marMap =
          this.getMaterialInfo(estimated.toArray(new String[estimated.size()]));
      for (String mpk : estimated) {
        msg.append("[");
        msg.append(marMap.get(mpk));
        msg.append("]");
      }
    }
  }

  private Map<String, String> getMaterialInfo(String[] vids) {
    IBDData[] mdatas = MaterialAccessor.getDocbyPks(vids);
    Map<String, String> retMap = new HashMap<String, String>(mdatas.length);
    for (IBDData data : mdatas) {
      retMap.put(data.getPk(), data.getCode());
    }
    return retMap;
  }

}
