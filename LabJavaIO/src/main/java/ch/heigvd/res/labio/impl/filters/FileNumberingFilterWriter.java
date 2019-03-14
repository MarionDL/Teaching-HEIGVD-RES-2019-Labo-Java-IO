package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a lineNb separator, it sends it to the decorated writer.
 * It then sends the lineNb number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\tHello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  // Numeros des lignes
  private int lineNb = 1;
  // Detection d'un '\r'
  private boolean detectR = false;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {

    // Appel de la methode definie au-dessous
    this.write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {

    for (int i = off; i < len + off; ++i) {
      // Appel de la methode definie au-dessous
      this.write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {

    // Cas particulier : premiere ligne
    if (lineNb == 1) {
      this.out.write("1\t");
      ++lineNb;
    }

    // Si un \r n'a pas ete detecte, on ecrit le char correctement (y compris le \n)
    if (!detectR) {
      this.out.write(c);
    } else {
      if (c != '\n') {
        this.out.write(lineNb++ + "\t" + (char) c);
        detectR = false;
      }
    }

    switch (c) {
      case '\n':
        // On ne l'ecrit que si le r a ete detecte
        if (detectR) {
          this.out.write("\n");
          detectR = false;
        }
        this.out.write(lineNb++ + "\t");
        break;
      case '\r':
        detectR = true;
        break;
      default:
    }
  }
}