package org.py.tool;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.DateFormatUtils;

public class FileUtils
{
  public static void restore(File originalFile, String fileName, String restoreDir, Boolean deleteOriginal)
    throws IOException
  {
    restore(originalFile, fileName, restoreDir, deleteOriginal, Boolean.valueOf(false), null, null, 0);
  }

  public static synchronized void restore(File originalFile, String originalFileName, String restoreDir, Boolean deleteOriginal, Boolean timePlusRandom, String timePattern, String decollator, int randomDigit)
    throws IOException
  {
    Validate.notNull(originalFile);
    Validate.notEmpty(originalFileName);
    Validate.notEmpty(restoreDir);
    if (timePlusRandom.booleanValue()) {
      Validate.notEmpty(timePattern);
      Validate.notEmpty(decollator);
      Validate.isTrue(randomDigit > 0);
    }

    String fname = originalFileName;
    if (timePlusRandom.booleanValue())
      fname = DateFormatUtils.format(new Date(), timePattern) + decollator + 
        RandomStringUtils.randomNumeric(randomDigit) + decollator + originalFileName;

    File restoreDirectory = new File(restoreDir);
    if (!(restoreDirectory.exists())) {
      restoreDirectory.mkdir();
    }

    org.apache.commons.io.FileUtils.copyFile(originalFile, new File(restoreDir + File.separator + fname));
    if (deleteOriginal.booleanValue())
      originalFile.delete();
  }

  public static String timeRandomFileName(String originalFileName, String timePattern, String decollator, int randomDigit)
  {
    Validate.notEmpty(originalFileName);
    Validate.notEmpty(timePattern);
    Validate.notEmpty(decollator);
    Validate.isTrue(randomDigit > 0);

    String fname = DateFormatUtils.format(new Date(), timePattern) + decollator + 
      RandomStringUtils.randomNumeric(randomDigit) + decollator + originalFileName;
    return fname;
  }
}