/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-19 下午01:15:03
 */
package nc.vo.pu.est.util;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pu.est.entity.m50.VmiEstHeaderVO;
import nc.vo.pu.est.entity.m50.VmiEstVO;
import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.pu.m4202.entity.VmiFIVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>消耗汇总暂估VO的工具类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-19 下午01:15:03
 */
public class VMIEstVOUtil {

  /**
   * 方法功能描述： 由VMI暂估头VO生成VMI财务聚合VO（中含表头，无表体）
   * <p>
   * <b>参数说明</b>
   * 
   * @param gevos 请保证暂估表头VO(视图VO信息的完成)，如不完整，则返回的聚合VO表头信息也不完整
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-19 下午01:21:55
   */
  public static VmiFIVO[] getVmiFIVO(VmiEstHeaderVO[] gevos) {
    if (ArrayUtils.isEmpty(gevos)) {
      return null;
    }
    List<VmiFIVO> fivos = new ArrayList<VmiFIVO>();
    for (int i = 0; i < gevos.length; i++) {
      VmiFIHeaderVO head = (VmiFIHeaderVO) gevos[i].getVO(VmiFIHeaderVO.class);
      if (null == head) {
        continue;
      }
      VmiFIVO fivo = new VmiFIVO();
      fivo.setParentVO(head);
      fivos.add(fivo);
    }
    if (fivos.size() == 0) {
      return null;
    }
    return fivos.toArray(new VmiFIVO[fivos.size()]);
  }

  /**
   * 由VMI暂估头VO生成VMI财务聚合VO
   * 
   * @param estVos
   * @return
   */
  public static VmiFIVO[] getVmiFIVO(VmiEstVO[] estVos) {
    if (ArrayUtils.isEmpty(estVos)) {
      return null;
    }
    List<VmiFIVO> fivos = new ArrayList<VmiFIVO>();
    for (int i = 0; i < estVos.length; i++) {
      VmiEstHeaderVO gevo = estVos[i].getParentVO();
      VmiFIHeaderVO head = (VmiFIHeaderVO) gevo.getVO(VmiFIHeaderVO.class);
      if (null == head) {
        continue;
      }
      VmiFIVO fivo = new VmiFIVO();
      fivo.setParentVO(head);
      fivo.setChildrenVO(estVos[i].getChildrenVO());
      fivos.add(fivo);
    }
    if (fivos.size() == 0) {
      return null;
    }
    return fivos.toArray(new VmiFIVO[fivos.size()]);
  }

}
