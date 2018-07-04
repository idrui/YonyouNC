/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-24 ����01:30:45
 */
package nc.vo.pu.est.rule;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pub.IAttributeMeta;
import nc.vo.pub.JavaType;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����ݹ�ʱ������
 * <li>��̨Ӧ�÷ŵ������ݹ����µ�ǰ������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-24 ����01:30:45
 */
public class GoodsEstNotNullChkRule {

  public void process(EstVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    this.checkHead(vos);
  }

  private void checkHead(EstVO[] vos) {
    IDataViewMeta meta = vos[0].getParentVO().getMetaData();
    StringBuilder error = new StringBuilder();
    for (int i = 0; i < vos.length; ++i) {
      GoodsEstVO head = vos[i].getParentVO();
      StringBuilder everyerror = new StringBuilder();
      for (String key : EstVOUtil.getGoodsEstNotNullKeys()) {
        IAttributeMeta ameta = meta.getAttribute(key);
        // IColumnMeta colmeta = ameta.getColumn();
        Object value = head.getAttributeValue(key);
        if (ObjectUtil.isEmptyWithTrim(value) && this.isCheck(key, value, head)) {
          everyerror.append("[");
          // everyerror.append(null == colmeta ? ameta.getName() : colmeta
          // .getLabel());
          everyerror.append(ameta.toString());
          everyerror.append("]");
          continue;
        }
        if (JavaType.UFDouble == ameta.getJavaType()
            && UFDouble.ZERO_DBL.equals(value)
            && this.isCheck(key, value, head)) {
          everyerror.append("[");
          // everyerror.append(null == colmeta ? ameta.getName() : colmeta
          // .getLabel());
          everyerror.append(ameta.toString());
          everyerror.append("]");
        }
      }
      if (0 < everyerror.length()) {
        if (0 != error.length()) {
          error.append(System.getProperty("line.separator"));
          error.append(System.getProperty("line.separator"));
        }
        int num = i + 1;
        error.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
            "04004060-0228", null, new String[] {
              String.valueOf(num)
            })/* ��{0}�е���ⵥ�����ݹ���ϢΪ�գ� */);
        error.append(System.getProperty("line.separator"));
        error.append(everyerror);
      }
    }
    if (0 < error.length()) {
      ExceptionUtils.wrappBusinessException(error.toString());
    }
  }

  private boolean isCheck(String key, Object value, GoodsEstVO head) {
    if (GoodsEstVO.NESTTAXRATE.equals(key)) {
      return false;
    }
    UFDouble taxrate = MathTool.nvl(head.getNesttaxrate());
    // ��˰����м��
    if (GoodsEstVO.NESTTAXMNY.equals(key)
        && (null == value || PubAppTool.isEqual(value, UFDouble.ZERO_DBL))) {
      // ˰��Ϊ0�����߿�˰��𲻼�˰��V61��ʼû�в���˰��ʱ�򲻼��˰��
      if (UFDouble.ZERO_DBL.equals(taxrate)) {
        return false;
      }
    }
    return true;
  }

}
