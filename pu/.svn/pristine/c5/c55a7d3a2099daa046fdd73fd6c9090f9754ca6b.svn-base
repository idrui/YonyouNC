package nc.md.gen.pdm.index.test;

import java.io.File;

import nc.bs.framework.test.AbstractTestCase;
import nc.md.gen.pdm.index.PDMFileManager;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wangxhi
 * @time 2010-10-21 下午02:36:11
 */
public class BatchAddTest extends AbstractTestCase {

  /**
   * Test 的构造子
   */
  public BatchAddTest() {
    //
  }

  public static void index() {
    String dr = "d:\\temp\\pdmindex\\";
    File pdmFile = new File(dr);
    BatchAddTest.res(pdmFile);
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param args <p>
   * @since 6.0
   * @author wangxhi
   * @time 2010-10-21 下午02:36:11
   */
  public static void main(String[] args) {
    BatchAddTest.index();
  }

  public static void testAddIndex() {
    BatchAddTest.index();
  }

  private static void res(File file1) {
    if (file1.isFile()
        && (file1.getName().contains(".pdm") || file1.getName()
            .contains(".PDM"))) {
      try {
        PDMFileManager.addPDMIndexes(file1.getAbsolutePath());
      }
      catch (Exception e) {
        e.toString();
      }
    }
    else if (file1.isDirectory()) {
      File[] files = file1.listFiles();
      if (files == null || files.length == 0) {
        return;
      }
      for (File file : files) {
        BatchAddTest.res(file);
      }
    }
    else {
      return;
    }

  }

}
