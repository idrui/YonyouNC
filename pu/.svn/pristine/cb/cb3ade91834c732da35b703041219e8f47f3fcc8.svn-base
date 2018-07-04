/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-25 下午02:22:54
 */
package nc.pubimpl.pu.m4202;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubitf.pu.m4202.IVMIEstInfoQuery;
import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.pu.m4202.entity.outsrv.VMIEstInfoVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>为库存消耗汇总的暂估信息查询服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-9-25 下午02:22:54
 */
public class VMIEstInfoQueryImpl implements IVMIEstInfoQuery {

  @Override
  public Map<String, VMIEstInfoVO> queryEstInfo(String[] vmiIDs)
      throws BusinessException {
    try {
      Map<String, VMIEstInfoVO> retMap = new HashMap<String, VMIEstInfoVO>();
      VOQuery<VMIEstInfoVO> queryTool =
          new VOQuery<VMIEstInfoVO>(VMIEstInfoVO.class, new String[] {
            VmiFIHeaderVO.PK_STOCKPS_B, VmiFIHeaderVO.NESTPRICE,
            VmiFIHeaderVO.NESTNUM, VmiFIHeaderVO.NESTMNY,
            VmiFIHeaderVO.NACCUMSETTLENUM, VmiFIHeaderVO.NACCESTCOSTWASHMNY,
            VmiFIHeaderVO.BSETTLEFINISH, VmiFIHeaderVO.DTOCOSTAPDATE
          });
      VMIEstInfoVO[] vos = queryTool.query(vmiIDs);
      if (!ArrayUtils.isEmpty(vos)) {
        retMap = CirVOUtil.createKeyVOMap(vos);
      }
      return retMap;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
      return null;
    }
  }
}
