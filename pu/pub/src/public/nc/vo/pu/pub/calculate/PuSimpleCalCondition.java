package nc.vo.pu.pub.calculate;

/**
 * ����������ļ�������
 * 
 * @since 6.1
 * @version 2012-8-7 ����11:17:14
 * @author tianft
 */
public class PuSimpleCalCondition {

  /**
   * �Ƿ�̶�������
   */
  private boolean isfixedChangeRate = true;

  public PuSimpleCalCondition() {
    //
  }

  public boolean isIsfixedChangeRate() {
    return this.isfixedChangeRate;
  }

  public void setIsfixedChangeRate(boolean isfixedChangeRate) {
    this.isfixedChangeRate = isfixedChangeRate;
  }

}
