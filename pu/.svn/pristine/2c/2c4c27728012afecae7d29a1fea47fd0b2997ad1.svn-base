/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-5 下午06:16:34
 */
package nc.bs.pu.m20.pf.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>物料分类编码包含
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-5 下午06:16:34
 */
public class MaterialCode {
  /**
   * 方法功能描述：对应物料编码，支持like,<br>
   * 即表体所有行物料编码等于或者包含该设置物料编码即可。
   * 单据表体只有一种分类。查询该分类的code，以支持 like等 <li>如果请购单单据所有表体均属于同一个存货分类，则返回这个分类名称，否则返回空</li>
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-7-5 下午06:19:35
   */
  public String getMaterialCode(AggregatedValueObject vo)
      throws BusinessException {
    List<String> pks = FunctionUtil.getPkMaterial((PraybillVO) vo);
    if (null == pks) {
      return null;
    }

    // 查询物料编码
    Map<String, MaterialVO> materialvos =
        MaterialPubService.queryMaterialBaseInfo(
            pks.toArray(new String[pks.size()]), new String[] {
              MaterialVO.CODE
            });
    if (materialvos == null || materialvos.size() == 0) {
      return null;
    }

    // 收集物料编码集合。
    List<String> materialCodeList = new ArrayList<String>();
    for (Map.Entry<String, MaterialVO> entry : materialvos.entrySet()) {
      MaterialVO materialVo = entry.getValue();
      materialCodeList.add(materialVo.getCode());
    }

    // 返回物料编码公共子串（左包含）。
    return this.getSameCodeByLeftLike(materialCodeList
        .toArray(new String[materialCodeList.size()]));
  }

  /**
   * 获取物料编码左包含的前缀。
   * 
   * @param strs 物料编码数组。
   * @return 左包含前缀
   */
  private String getSameCodeByLeftLike(String[] strs) {
    if (strs == null || strs.length == 0) {
      return null;
    }
    else if (strs.length == 1) {
      return strs[0];
    }
    else {
      // 将第一个字符串打散成字符数组，循环所有字符串，找到左包含的公共子串。
      // 第一个字符未必是最短的，但目前认为应该不会引起效率问题。故使用第一个元素。
      char[] str1chars = strs[0].toCharArray();
      StringBuffer buffer = new StringBuffer();
      for (int i = 0; i < str1chars.length; i++) {
        buffer.append(str1chars[i]);

        for (int j = 1; j < strs.length; j++) {
          // 发现不匹配。
          if (!strs[j].startsWith(buffer.toString())) {
            // 最新压入buffer的字符是无效的，所以需要删除。
            buffer.deleteCharAt(buffer.length() - 1);
            return buffer.toString();
          }
        }
      }
      return buffer.toString();
    }
  }
}
