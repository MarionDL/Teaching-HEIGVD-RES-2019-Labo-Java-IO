package ch.heigvd.res.labio.impl.explorers;

import ch.heigvd.res.labio.interfaces.IFileExplorer;
import ch.heigvd.res.labio.interfaces.IFileVisitor;
import java.io.File;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor visitor) {
    // throw new UnsupportedOperationException("The student has not implemented this method yet.");

    // Appel de la methode visit en passant en argument le noeud qu'on a rencontré
    visitor.visit(rootDirectory);

    // Extraction de tous les sous-repertoires et fichiers du root directory
    File[] filesList = rootDirectory.listFiles();

    // Parcours du tableau : si c'est un fichier, on le visite, si c'est un dossier, on l'explore
    // recursivement
    if (filesList != null) {
      for (File file : filesList) {
        if (file.isDirectory()) {
          explore(file, visitor);
        } else {
          visitor.visit(file);
        }
      }
    }
  }

}
