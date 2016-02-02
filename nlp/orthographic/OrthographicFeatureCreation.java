
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                             * 
 * Named Entity Recognition system is used to identify the names of person,    *
 * names of location, names of organization, time expressions and numeric      *
 * expression in Tamil text.                                                   *
 *                                                                             *
 *  Copyright (C) 2015-2016  University of Moratuwa                            *
 *                                                                             *
 * This program is free software: you can redistribute it and/or modify        *
 * it under the terms of the GNU General Public License as published by        *
 * the Free Software Foundation, either version 3 of the License, or           *
 * (at your option) any later version.                                         *
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

package nlp.orthographic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nlp.systeminit.InitSystem;
import nlp.tamil.IOLayer;
import nlp.tamil.TamilFontEntity;

/**
 * @author University of Moratuwa, Computer Science and Engineering, Group-12 CSE'11
 *
 * This class is used to create the orthographic features for the training and
 * testing file
 */
public class OrthographicFeatureCreation {

    /**
     * Create the orthographic features from the given file
     *
     * @param file File to be tag orthographic features
     */
    public static void createFeature(String file) {
        BufferedReader bufferedReader = null;
        boolean possibleDate = false;
        boolean possibleCount = false;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(file)));
            PrintWriter pw = new PrintWriter(new File("resources/orthographicFeatures.txt"));
            PrintWriter pw1 = new PrintWriter(new File("resources/ortho.txt"));
            
            String line = bufferedReader.readLine();

            while (line != null) {
                line = line.trim();
                String pattern = "";
                if (line.length() == 0) {
                    pattern = " ";
                } else if (line.matches("^[0-9\\.-]+")) {
                    possibleDate = false;
                    int count = 0;
                    pattern = "";
                    for (char c : line.toCharArray()) {
                        if (Character.isDigit(c)) {
                            pattern += "N";
                        } else {
                            pattern += c;
                            count++;
                        }
                    }
                    if(count > 1)
                        possibleDate = true;

                } else {
                    List<TamilFontEntity> tamilWord = IOLayer.getTamil(line);
                    pattern = "";
                    int count = 0;
                    for (TamilFontEntity t : tamilWord) {
                        String regex = "a-z-~@#$%^&*:;<>.,/}{+()'\"?`";
                        List<TamilFontEntity> tx = new ArrayList<>();
                        tx.add(t);
                        
                        if (t.getxLocation() != 0 || t.getyLocation() != 0) {
                            pattern += "X";
                        } else if (!IOLayer.getText(tx).toString().matches(("[" + regex + "]+")) && !Character.isDigit(IOLayer.getText(tx).toString().toCharArray()[0])) {
                            pattern += "X";
                        } else {
                            if (Character.isDigit(IOLayer.getText(tx).toString().toCharArray()[0])) {
                                pattern += "N";
                            } else {
                                pattern += IOLayer.getText(tx).toString();
                                if(!t.equals(tamilWord.get(0)) && !t.equals(tamilWord.get(tamilWord.size()-1)) )
                                count++;
                            }
                        }
                    }
                    if(count == 2)
                        possibleDate = true;

                }
                
                if(pattern.equals("NNNNX") || pattern.equals("NNNNXX") || pattern.equals("NNNN") || pattern.equals("NN.NN") || pattern.equals("N.NN") || pattern.equals("NN.N"))
                    possibleDate = true;
                else if(pattern.matches("[N]+X") || pattern.matches("[N]+XX") || pattern.matches("[N]+")){
                    possibleCount = true;
                }
           
                if(possibleDate)
                    pw.write(pattern +"\t" + "1"+ "\n");
                else if(possibleCount)
                    pw.write(pattern +"\t" + "2"+ "\n");
                else if(!pattern.trim().equals(""))
                    pw.write(pattern +"\t" + "0"+ "\n");
                else
                    pw.write("\n");
                
                pw1.write(pattern + "\n");
               
                line = bufferedReader.readLine();
                possibleDate = false;
                possibleCount = false;
            }
            pw.close();
            pw1.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OrthographicFeatureCreation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OrthographicFeatureCreation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ex) {
                Logger.getLogger(OrthographicFeatureCreation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        InitSystem.init();
        createFeature("resources/word.txt");
    }
}
