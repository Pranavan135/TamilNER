
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
 
package nlp.main;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.ling.Word;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nlp.gazetteer.RuleBaseTagger;
import nlp.morph.noun.MainNounDetetectionLayer;
import nlp.orthographic.OrthographicFeatureCreation;
import nlp.prefix.PrefixFeatureCreation;

/**
 * @author University of Moratuwa, Computer Science and Engineering, Group-12 CSE'11
 *
 * This is the main execution class of NER. It integrates all the related
 * features to build the model
 */
public class NER_Main {

    public static volatile Double progress = 0.0;
    private static final PropertyChangeSupport changes = new PropertyChangeSupport(NER_Main.class);

    public static void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public static void setProgress(double pro) {
        progress += pro;
        System.out.println(progress);
        changes.fireIndexedPropertyChange("progess", 0, progress - 1, progress + 1);

    }

    public static ArrayList createTestData(String data) {
        try {
            File file_word_2POS = new File("resources/POS.txt");
            File onlyWords = new File("resources/words.txt");
            PrintWriter pw = new PrintWriter(file_word_2POS);
            PrintWriter pw1 = new PrintWriter(onlyWords);
            ArrayList<String> testData = new ArrayList<>();
            MaxentTagger tagger;
            tagger = new MaxentTagger("Models/tamil.tagger");
            List<List<String[]>> morphed = MainNounDetetectionLayer.getMorph(data);
            Double p = (1.0 / morphed.size()) * 25;

            List<List<HasWord>> sentences = new ArrayList<>();

            morphed.stream().map((List<String[]> sent) -> {
                List<HasWord> newList = new ArrayList<>();
                sent.stream().forEach((s) -> {
                    newList.add(new Word(s[0]));
                });
                return newList;
            }).map((newList) -> {
                sentences.add(newList);
                return newList;
            }).forEach((_item) -> {

            });

            boolean last = false;
            int j = 0;

            for (List<HasWord> sentence : sentences) {
                boolean skip = false;
                int size = sentence.size();
                int index = 0;
                List<TaggedWord> tSentence = tagger.tagSentence(sentence);
                for (TaggedWord word : tSentence) {
                    String s = String.valueOf(word);
                    String[] y = s.split("/");
                    s = s.replace("/", "\t");
                    pw.write(s + "\t" + morphed.get(j).get(index)[1] + "\n");
                    pw1.write(y[0] + "\n");
                    index++;
                }
                pw.write("\n");
                pw1.write("\n");
                setProgress(p);
                j++;

            }
            pw1.close();
            pw.close();
            RuleBaseTagger.getTestData("resources/words.txt");

            Process proc = Runtime.getRuntime().exec("scripts/./test.sh");

            // Read the output
            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            String line = "";
            PrintWriter testDataSet = new PrintWriter(new File("resources/test_data.txt"));
            while ((line = reader.readLine()) != null) {
                testData.add(line);
                testDataSet.write(line + "\n");
            }
            testDataSet.close();
            int waitFor = proc.waitFor();
            
            return testData;

        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(NER_Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

     public static ArrayList createTestDataWithPrefix(String data, int i) {
        try {
            File file_word_2POS = new File("resources/POS.txt");
            File onlyWords = new File("resources/words.txt");
            PrintWriter pw = new PrintWriter(file_word_2POS);
            PrintWriter pw1 = new PrintWriter(onlyWords);
            ArrayList<String> testData = new ArrayList<>();
            MaxentTagger tagger;
            tagger = new MaxentTagger("Models/tamil.tagger");
            List<List<String[]>> morphed = MainNounDetetectionLayer.getMorph(data);
            Double p = (1.0 / morphed.size()) * 25;

            List<List<HasWord>> sentences = new ArrayList<>();

            morphed.stream().map((List<String[]> sent) -> {
                List<HasWord> newList = new ArrayList<>();
                sent.stream().forEach((s) -> {
                    newList.add(new Word(s[0]));
                });
                return newList;
            }).map((newList) -> {
                sentences.add(newList);
                return newList;
            }).forEach((_item) -> {

            });

            boolean last = false;
            int j = 0;

            for (List<HasWord> sentence : sentences) {
                boolean skip = false;
                int size = sentence.size();
                int index = 0;
                List<TaggedWord> tSentence = tagger.tagSentence(sentence);
                for (TaggedWord word : tSentence) {
                    String s = String.valueOf(word);
                    String[] y = s.split("/");
                    s = s.replace("/", "\t");
                    pw.write(s + "\t" + morphed.get(j).get(index)[1] + "\n");
                    pw1.write(y[0] + "\n");
                    index++;
                }
                pw.write("\n");
                pw1.write("\n");
                setProgress(p);
                j++;

            }
            pw1.close();
            pw.close();
            RuleBaseTagger.getTestData("resources/words.txt");
            if(i == 0)
                PrefixFeatureCreation.prefixGeneration("resources/words.txt", 4);
            else
                PrefixFeatureCreation.prefixGeneration("resources/words.txt", 4);

            Process proc = Runtime.getRuntime().exec("scripts/./test_with_prefix.sh");

            // Read the output
            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            String line = "";
            PrintWriter testDataSet = new PrintWriter(new File("resources/test_data.txt"));
            while ((line = reader.readLine()) != null) {
                testData.add(line);
                testDataSet.write(line + "\n");
            }
            testDataSet.close();
            int waitFor = proc.waitFor();
            
            return testData;

        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(NER_Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

     public static ArrayList createTestDataWithPrefixandOrthograph(String data, int index) {
        try {
            File file_word_2POS = new File("resources/POS.txt");
            File onlyWords = new File("resources/words.txt");
            PrintWriter pw = new PrintWriter(file_word_2POS);
            PrintWriter pw1 = new PrintWriter(onlyWords);
            ArrayList<String> testData = new ArrayList<>();
           
            List<List<String[]>> morphed = MainNounDetetectionLayer.getMorph(data);
            Double p = (1.0 / morphed.size()) * 25;
       
            boolean last = false;
            int j = 0;

            for(List<String[]> m : morphed){
                for(String[] s  : m){
                    pw.write(s[0] + "\t" + s[1] + "\n");
                    System.out.println(s[0] + "\t" + s[1] + "\n");
                    pw1.write(s[0] + "\n");
                }
                setProgress(p);
                pw.write("\n");;
                pw1.write("\n");
            }
         
            pw1.close();
            pw.close();
            RuleBaseTagger.getTestData("resources/words.txt");
            PrefixFeatureCreation.prefixGeneration("resources/words.txt", 4);
            OrthographicFeatureCreation.createFeature("resources/words.txt");
            Process proc = null;
            if(index == 0 || index == 2)
                proc = Runtime.getRuntime().exec("scripts/./test_with_prefix_OurPOS_ortho.sh");
            else    
                proc = Runtime.getRuntime().exec("scripts/./test_with_prefix_OurPOS_ortho2.sh");
           

            // Read the output
            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            String line = "";
            PrintWriter testDataSet = new PrintWriter(new File("resources/test_data.txt"));
            while ((line = reader.readLine()) != null) {
                testData.add(line);
                testDataSet.write(line + "\n");
            }
            testDataSet.close();
            int waitFor = proc.waitFor();
            
            return testData;

        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(NER_Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     
    /**
     * To get the output by giving the preprocessed test data
     *
     * @param data Data to be tagged
     * @param index Selection of classifier
     * @return the tagged data
     */
    public static ArrayList<String[]> getOutput(String data, int index) {
        try {
            setProgress(0);
            if(index < 2)
                createTestData(data);
            else if(index < 4)
                createTestDataWithPrefix(data, index-2);
            else
                createTestDataWithPrefixandOrthograph(data ,index - 4);

            Process proc = null;

            if (index == 0) {
                proc = Runtime.getRuntime().exec("scripts/./mira.sh");
            } else  if (index == 1){
                proc = Runtime.getRuntime().exec("scripts/./crf.sh");
            }
            else  if (index == 2){
                proc = Runtime.getRuntime().exec("scripts/./mira_with_prefix.sh");
            }else  if (index == 3){
                proc = Runtime.getRuntime().exec("scripts/./crf_with_prefix.sh");
            }
            else  if (index == 4){
                proc = Runtime.getRuntime().exec("scripts/./mira_with_prefix_OurPOS.sh");
            }
            else if (index == 5){
                proc = Runtime.getRuntime().exec("scripts/./mira_with_prefix_OurPOS2.sh");
            }
            else    if(index == 6)
                proc = Runtime.getRuntime().exec("scripts/./crf_with_prefix_OurPOS.sh");
            else
                proc = Runtime.getRuntime().exec("scripts/./mira_with_prefix_OurPOS2.sh");

  

            // Read the output
            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            String line = "";
            ArrayList<String[]> output = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
              
                String[] split = line.split("\\s+");
                output.add(split);
            }
            
            return output;
        } catch (IOException ex) {
            Logger.getLogger(NER_Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        createTestData("கோத்ரா தொடருந்து எரிப்பு இந்திய மாநிலம் குசராத்தில் கோத்ரா ஊரில் 2002ஆம் ஆண்டு தொடருந்து நிலையத்தில் நின்றிருந்த பயணிகள் தொடருந்து வண்டியின் பயணர்பெட்டி ஒன்று இசுமாலிய[சான்று தேவை] கலகக்காரக் கூட்டத்தால் தீயிடப்பட்ட நிகழ்வைக் குறிப்பதாகும். அயோத்திவிலிருந்து திரும்பிக் கொண்டிருந்த 59 இந்து பயணிகள் இறந்த இந்நிகழ்வு 790 இசுலாமியரும் 254 இந்துக்களும் பரந்தளவில் கொல்லப்பட்ட குசராத் வன்முறைக்குத் தூண்டுதலாக அமைந்தது. துவக்கத்தில் பதியப்பட்ட முதல் தகவல் அறிக்கையில் திட்டமிடப்படாத கூட்ட வன்முறை என்று விவரிக்கப்பட்டிருந்தது. ஆயினும் குசராத் காவல்படையின் சிறப்பு புலனாய்வுக் குழுவால் இது இசுலாமிய குழுவொன்று 140 லிட்டர் பெட்ரோலை நிகழ்நாளுக்கு முந்தைய நாளே சேகரித்து வைத்து, \"கரசேவகர்களை\" கொல்லத் திட்டமிட்ட சதி எனக் கண்டறிந்து வழக்காடியது.");

        Process proc = Runtime.getRuntime().exec("scripts/./mira.sh");

        // Read the output
        BufferedReader reader
                = new BufferedReader(new InputStreamReader(proc.getInputStream()));

        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.print(line + "\n");
        }

        proc.waitFor();
    }

}
