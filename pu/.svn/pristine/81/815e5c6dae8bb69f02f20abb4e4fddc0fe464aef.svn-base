package nc.md.gen.pdm.index.test;

import nc.bs.framework.test.AbstractTestCase;
import nc.md.gen.pdm.index.IndexFileManager;
import nc.md.gen.pdm.index.TabIndex;

public class IndexFileLoadTest extends AbstractTestCase {

  // public void testGetFileName() throws Exception{
  // //IndexFileManager.loadIndexFile();
  // TabIndex obj=new TabIndex("po_order","i_21_1",new String[]{
  // "pk_org"},UFBoolean.TRUE);
  // TabIndex obj1=new TabIndex("po_order","i_21_1",new String[]{
  // "pk_org","pk_org_v"},UFBoolean.FALSE);
  // TabIndex[] objs=new TabIndex[]{obj,obj1};
  // URL url=this.getClass().getResource("");
  // String fileName=url.getFile()+"objtest.xml";
  // ObjectToXML.saveAsXmlFile(fileName, objs);
  // }
  // public void testLoadFromXml() throws Exception{
  // TabIndex[] indices=IndexFileManager.loadIndexFile();
  // System.out.println(Arrays.deepToString(indices));
  // }
  public void testSaveToXml() throws Exception {
    TabIndex[] indices = IndexFileManager.loadIndexFile();
    IndexFileManager.saveToIndexFile(indices);
  }

  // public void testPDMReader(){
  // PDMFileManager.readPDM(null);
  // }
}
