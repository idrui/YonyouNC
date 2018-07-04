/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-26 ����11:03:04
 */
package nc.vo.pu.est.entity.m45;

import nc.vo.pu.est.entity.FeeEstDistVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ����������ݹ���̯��ϸ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-26 ����11:03:04
 */
public class PurchaseinFIFDVO extends FeeEstDistVO {

  /**
   * 
   */
  private static final long serialVersionUID = 1993587181475834581L;

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(PUEntity.PurchaseinFI_FD);
    return meta;
  }

}
