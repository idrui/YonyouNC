package nc.test.pu.m23;

import nc.bs.framework.test.AbstractTestCase;
import nc.vo.pu.m25trantype.entity.InvcTranTypeVO;
import nc.vo.pubapp.pattern.tool.generate.VOFieldGenerator;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����Ԫ���ݣ�����VO����������
 * <li>
 * <li>
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2009-12-30 ����03:29:34
 */
public class ArriveVOGenerator extends AbstractTestCase {

  public void testGenerateVO() {
    VOFieldGenerator bo = new VOFieldGenerator();
    String str = bo.generate(InvcTranTypeVO.class);
    // String str = bo.generate(ArriveItemVO.class);
    // String str = bo.generate(ArriveBbVO.class);
    System.out.println(str);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
  }

}
