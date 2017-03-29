/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitao.outils;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author jonathan.capitao
 */
public class ExtensionFileFilter extends FileFilter {
    String description;

    String extensions[];

    // Constructeur
    public ExtensionFileFilter(String description, String extension) {
        this(description, new String[] { extension });
    } // ExtensionFileFilter

    // Constructeur
    public ExtensionFileFilter(String description, String extensions[]) {
        if (description == null) {
            this.description = extensions[0];
        } else {
            this.description = description;
        }
        this.extensions = (String[]) extensions.clone();
        toLower(this.extensions);
    } // ExtensionFileFilter

    // On passe le contenu d'un tableau en minuscule
    private void toLower(String array[]) {
        for (int i = 0, n = array.length; i < n; i++) {
            array[i] = array[i].toLowerCase();
        }
    } // toLower

    // Récupère la description du filtre
    public String getDescription() {
        return description;
    } // getDescription

    // On vérifie si le fichier est valide par rapport au existension disponible.
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        } else {
            String path = file.getAbsolutePath().toLowerCase();
            for (int i = 0, n = extensions.length; i < n; i++) {
                String extension = extensions[i];
                if ((path.endsWith(extension) && (path.charAt(path.length() - extension.length() - 1)) == '.')) {
                    return true;
                }
            }
        }
        return false;
    } // accept
} // ExtensionFileFilter

