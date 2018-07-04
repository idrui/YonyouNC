package nc.bs.pu.event;

import nc.bs.businessevent.AbstractBusinessEvent;
import nc.vo.pub.ValidationException;
import nc.vo.pub.ValueObject;

public class PUBusinessEvent extends AbstractBusinessEvent {

  public static class PUUserObj extends ValueObject {

    private static final long serialVersionUID = 4924657117559642920L;

    private Object newObjects;

    private Object oldObjects;

    private PUUserObj(Object newObjects, Object oldObjects) {
      this.newObjects = newObjects;
      this.oldObjects = oldObjects;
    }

    @Override
    public String getEntityName() {
      return this.getClass().getName();
    }

    public Object getNewObjects() {
      return this.newObjects;
    }

    public Object getOldObjects() {
      return this.oldObjects;
    }

    public void setNewObjects(Object newObjects) {
      this.newObjects = newObjects;
    }

    public void setOldObjects(Object oldObjects) {
      this.oldObjects = oldObjects;
    }

    @Override
    public void validate() throws ValidationException {

    }

  }

  private static final long serialVersionUID = 1407089011751467629L;

  private Object[] newObjs = null;

  private Object[] oldObjs = null;

  public PUBusinessEvent(String sourceID, String eventType) {
    super(sourceID, eventType);
  }

  public PUBusinessEvent(String sourceID, String eventType, Object... objs) {
    super(sourceID, eventType);
    this.setNewObjs(objs);
  }

  public PUBusinessEvent(String sourceID, String eventType, Object[] oldObjs,
      Object[] newObjs) {
    super(sourceID, eventType);
    this.setOldObjs(oldObjs);
    this.setNewObjs(newObjs);
  }

  public Object[] getNewObjs() {
    return this.newObjs;
  }

  public Object[] getObjs() {
    return this.newObjs;
  }

  public Object[] getOldObjs() {
    return this.oldObjs;
  }

  @SuppressWarnings("synthetic-access")
  @Override
  public ValueObject getUserObject() {
    return new PUUserObj(this.newObjs, this.oldObjs);
  }

  public void setNewObjs(Object[] newObjs) {
    this.newObjs = newObjs;
  }

  public void setObjs(Object[] newObjs) {
    this.newObjs = newObjs;
  }

  public void setOldObjs(Object[] oldObjs) {
    this.oldObjs = oldObjs;
  }

}
