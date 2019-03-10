package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    // throw new UnsupportedOperationException("The student has not implemented this method yet.");
    this.out.write(str.toUpperCase(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    // throw new UnsupportedOperationException("The student has not implemented this method yet.");
    char[] result = new char[cbuf.length];
    for (int i = 0; i < cbuf.length; ++i) {
      result[i] = Character.toUpperCase(cbuf[i]);
    }
    this.out.write(result, off, len);
  }

  @Override
  public void write(int c) throws IOException {
    // throw new UnsupportedOperationException("The student has not implemented this method yet.");
    this.out.write(Character.toUpperCase(c));
  }

}
