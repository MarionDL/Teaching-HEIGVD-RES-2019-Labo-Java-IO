package ch.heigvd.res.labio.impl.explorers;

import ch.heigvd.res.labio.interfaces.IFileExplorer;
import ch.heigvd.res.labio.interfaces.IFileVisitor;
import java.io.File;
import java.util.Arrays;
import java.util.Objects;

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

    // Appel de la methode visit en passant en argument le noeud qu'on a rencontré
    visitor.visit(rootDirectory);

    // On s'assure que le rootDirectory est bien un repertoire
    if (rootDirectory.isDirectory()) {
      // Extraction de tous les sous-repertoires et fichiers du root directory
      File[] filesList = Objects.requireNonNull(rootDirectory.listFiles());
      // Tri selon l'ordre alphabetique
      Arrays.sort(filesList);

    /*
    Parcours du tableau : si c'est un fichier, on le visite, si c'est un dossier, on l'explore
    recursivement
    */
      for (File file : filesList) {
        explore(file, visitor);
      }
    }
  }
}