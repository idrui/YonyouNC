/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2009-12-29 ����04:57:45
 */
package nc.bs.pu.m21.maintain.rule.save;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              �����ɼ��Լ��
 * @scene
 *        �ɹ����������޸�
 * @param ��
 * @since 6.3
 * @version 2014-10-22 ����2:58:05
 * @author luojw
 */
public class DocVisibilityChkRule implements IRule<OrderVO> {

  // private void chkMaterialApOrg(OrderVO[] vos, StringBuilder errMsg) {
  //
  // }
  //
  // private void chkMaterialPurOrg(OrderVO[] vos, StringBuilder errMsg) {
  //
  // }
  //
  // private void chkMaterialStlOrg(OrderVO[] vos, StringBuilder errMsg) {
  //
  // }
  //
  // private void chkSupplierApOrg(OrderVO[] vos, StringBuilder errMsg) {
  //
  // }
  //
  // private void chkSupplierPurOrg(OrderVO[] vos, StringBuilder errMsg) {
  //
  // }
  //
  // private void chkSupplierStlOrg(OrderVO[] vos, StringBuilder errMsg) {
  //
  // }

  @Override
  public void process(OrderVO[] vos) {
    // �����������󣬲���鵵���ɿ���
    StringBuilder errMsg = new StringBuilder();
    // this.chkSupplierPurOrg(vos, errMsg);
    // this.chkSupplierApOrg(vos, errMsg);
    // this.chkSupplierStlOrg(vos, errMsg);
    // this.chkMaterialPurOrg(vos, errMsg);
    // this.chkMaterialApOrg(vos, errMsg);
    // this.chkMaterialStlOrg(vos, errMsg);
    if (0 < errMsg.length()) {
      ExceptionUtils.wrappBusinessException(errMsg.toString());
    }
  }

}
