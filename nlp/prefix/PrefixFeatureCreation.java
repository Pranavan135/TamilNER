
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                             * 
 * Named Entity Recognition system is used to identify the names of person,    *
 * names of location, names of organization, names of times and numeric        *
 * expression in Tamil text.                                                   *
 *                                                                             *
 *  Copyright (C) 2015-2016  University of Moratuwa                            *
 *                                                                             *
 * This program is free software: you can redistribute it and/or modify        *
 * it under the terms of the GNU General Public License as published by        *
 * the Free Software Foundation, either version 3 of the License, or           *
 * (at your option) any later version.                                         *
 *                                                                             *
 *                                                                             *
 * This program is free software: you can redistribute it and/or modify        *
 * it under the terms of the GNU General Public License as published by        *
 * the Free Software Foundation, either version 3 of the License, or           *
 * (at your option) any later version.                                         *
 *                                                                             * 
 *                                                                             *
 * This program is distributed in the hope that it will be useful,             *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of              *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the               *
 * GNU General Public License for more details.                                *
 *                                                                             *
 * You should have received a copy of the GNU General Public License           *
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.       *
 *                                                                             * 
 *                                                                             * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package nlp.prefix;

import nlp.tamil.*;

import java.io.*;
import java.util.List;

/**
 * @author University of Moratuwa, Computer Science and Engineering, Group-12 CSE'11
 *
 * This class is used to create a feature file
 * with prefix for training and testing
 */
public class PrefixFeatureCreation {

    /**
     * Method to create a prefix feature for the given data in the file
     *
     * @param fileName Name of the file that contains the data
     * @param prefixLength Length of the feature to be extracted
     */
    public static void prefixGeneration(String fileName, int prefixLength) {
        String line;

        try {
            /* FileReader reads text files in the default encoding. */
            FileReader fileReader
                    = new FileReader(fileName);
            FileWriter fileWriter
                    = new FileWriter("resources/prefixed.txt");

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader
                    = new BufferedReader(fileReader);
            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter
                    = new BufferedWriter(fileWriter);

            while ((line = bufferedReader.readLine()) != null) {
                if (line.trim().length() != 0) {
                    List<TamilFontEntity> list = IOLayer.getTamil(line);
                    if (list.size() > prefixLength) {
                        list = list.subList(0, prefixLength);

                    }
                    String prefix = IOLayer.getText(list).toString();
                    
                    bufferedWriter.write(prefix + "\n");
                    
                    
                   
                } else {
                    bufferedWriter.write("\n");
                }

            }

            // Always close files.
            bufferedReader.close();

            // Always close files.
            bufferedWriter.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '"
                    + fileName + "'");
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) {
        TamilLayout.init();
        prefixGeneration("sf.txt", 2);
    }
}
