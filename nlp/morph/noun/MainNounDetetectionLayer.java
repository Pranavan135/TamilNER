
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

package nlp.morph.noun;

import nlp.morph.database.NounListCheck;
import nlp.tamil.IOLayer;
import nlp.morph.noun.stemExtraction.*;
import nlp.tamil.TamilFontEntity;

import java.io.*;
import java.util.List;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import nlp.systeminit.InitSystem;

/**
 * @author University of Moratuwa, Computer Science and Engineering, Group-12 CSE'11
 *
 * This is the main noun detection layer which integrates all the layers to
 * detect the noun.
 */
public class MainNounDetetectionLayer {

    public static void main(String[] args) throws Exception {

        InitSystem.init();
        if (args.length == 0) {
         System.out.println("Usage : java <Input File>");
         System.exit(0);
         }

        List<List<HasWord>> sentences = MaxentTagger.tokenizeText(new BufferedReader(new FileReader(args[0])));

        PrintWriter writer;
        try {
            writer = new PrintWriter("output.txt", "UTF-8");

            sentences.stream().map((input) -> {
                String[] words = new String[input.size()];
                int i = 0;
                for (HasWord l : input) {
                    words[i] = l.word();
                    i++;
                }

                return words;
            }).map((String[] words) -> {
                String wordBefore = "";
                for (int index = 0; index < words.length; index++) {

                    String temp = words[index].replaceAll("[.,'?/\"%]", "");
                    String outputSentence = "";
                    String stemSentence = "";
                    if (isNumeric(temp)) {
                        outputSentence += words[index] + "\t<NUM> ";
                        stemSentence += words[index] + "\t<NUM> ";
                    } else if (NounListCheck.isExist(words[index])) {

                        outputSentence += words[index] + "\t<NOUN> ";

                        stemSentence += words[index] + "\t<NOUN> ";
                    } else {
                        List<TamilFontEntity> tamilWord = IOLayer.getTamil(temp);

                        List<TamilFontEntity> cleanWord = tamilWord;

                        if (!tamilWord.isEmpty()) {
                            if (index != words.length - 1 && !words[index].contains(".") && tamilWord.get(tamilWord.size() - 1).getxLocation() != 9 && tamilWord.get(tamilWord.size() - 1).getyLocation() == -1 && IOLayer.getTamil(words[index + 1]).get(0).getxLocation() == tamilWord.get(tamilWord.size() - 1).getxLocation()) {
                                cleanWord = tamilWord.subList(0, tamilWord.size() - 1);

                            }
                        }

                        String tempWord = IOLayer.getText(cleanWord).toString();

                        if (tempWord.length() > 0) {
                            if (index == words.length - 2) {
                                if (cleanWord.size() > 2) {
                                    outputSentence += words[index] + "\t<VERB>";
                                    stemSentence += words[index] + "\t<VERB>";
                                } else {
                                    outputSentence += words[index] + "\t<NOTDEFINED>";
                                    stemSentence += words[index] + "\t<NOTDEFINED>";
                                }
                            } else {
                                List<TamilFontEntity> stem = detectStems(tempWord, wordBefore);
                                if (NumberDetectorLayer.isNumber(cleanWord)) {
                                    outputSentence += words[index] + "\t<NUM> ";
                                    stemSentence += words[index] + "\t<NUM> ";
                                } else if (PronounAndArticleDetector.isArticle(tempWord)) {
                                    outputSentence += words[index] + "\t<ATL> ";
                                    stemSentence += words[index] + "\t<ATL> ";
                                } else if (PronounAndArticleDetector.isProNoun(tempWord)) {
                                    outputSentence += words[index] + "\t<PNN> ";
                                    stemSentence += words[index] + "\t<PNN> ";
                                } else if (stem != null) {
                                    outputSentence += words[index] + "\t<NOUN> ";
                                    stemSentence += IOLayer.getText(stem) + "\t<NOUN> ";
                                } else {
                                    stem = getNounStem(tempWord);

                                    if (stem != null) {

                                        outputSentence += words[index] + "\t<NOUN> ";
                                        stemSentence += IOLayer.getText(stem) + "\t<NOUN> ";

                                    } else if (ExtractNounStemMLayer.extractStemNounM(tempWord) != null) {
                                        List<TamilFontEntity> stemWithoutM = ExtractNounStemMLayer.extractStemNounM(tempWord);

                                        stemWithoutM = extractPluralStem(stemWithoutM);

                                        if (stemWithoutM.equals(IOLayer.getTamil(tempWord))) {
                                            outputSentence += IOLayer.getText(stemWithoutM) + "\t<JOIN> ";
                                            stemSentence += IOLayer.getText(stemWithoutM) + "\t<JOIN> ";
                                        } else {
                                            outputSentence += IOLayer.getText(stemWithoutM) + "\t<NOUN> ";
                                            stemSentence += IOLayer.getText(stemWithoutM) + "\t<NOUN> ";
                                        }
                                    } else {
                                        outputSentence += IOLayer.getText(cleanWord) + "\t<NOTDEFINED> ";
                                        stemSentence += IOLayer.getText(cleanWord) + "\t<NOTDEFINED> ";
                                    }
                                }
                            }
                        }
                    }

                    if (temp.trim().equals("")) {
                        writer.println(words[index] + "\t<SYM>");

                    } else {
                        writer.println(stemSentence);
                        wordBefore = words[index];
                    }
                }
                return wordBefore;
            }).map((wordBefore) -> IOLayer.getTamil(wordBefore)).filter((tamilWord) -> (tamilWord.size() > 2)).forEach((_item) -> {
                writer.println("");
            });
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
        }
    }

    public static List<List<String[]>> getMorph(String data) {
     
        PrintWriter pw = null;
        try {
            List<List<HasWord>> sentences = MaxentTagger.tokenizeText(new StringReader(data));
            int count = sentences.size();
            List<List<String[]>> morphOutput = new ArrayList<>();
            ArrayList<String[]> list = new ArrayList<>();
            boolean checkSentence = true;
         
            pw = new PrintWriter(new File("resources/morphed_stem.txt"));
            for (List<HasWord> input : sentences) {
                String[] words = new String[input.size()];

                int i = 0;
                for (HasWord l : input) {
                    words[i] = l.word();

                    i++;
                }

                String wordBefore = "";

                if (checkSentence) {
                    if (list.size() > 0) {
                        morphOutput.add(list);
                    }
                    list = new ArrayList<>();
                    checkSentence = false;
                    pw.println();
                }

                for (int index = 0; index < words.length; index++) {

                    String temp = words[index].replaceAll("[.,/?\"'`#$%]", "");
                    String outputSentence = "";
                    String stemSentence = "";
                    if (isNumeric(temp)) {
                        outputSentence = "NUM";
                        stemSentence = temp;

                    } else if (NounListCheck.isExist(words[index])) {

                        outputSentence = "NOUN";
                        stemSentence = words[index];

                    } else {
                        List<TamilFontEntity> tamilWord = IOLayer.getTamil(temp);

                        List<TamilFontEntity> cleanWord = tamilWord;

                        if (!tamilWord.isEmpty()) {
                            if (index != words.length - 1 && !words[index].contains(".") && tamilWord.get(tamilWord.size() - 1).getxLocation() != 9 && tamilWord.get(tamilWord.size() - 1).getyLocation() == -1 && IOLayer.getTamil(words[index + 1]).get(0).getxLocation() == tamilWord.get(tamilWord.size() - 1).getxLocation()) {
                                cleanWord = tamilWord.subList(0, tamilWord.size() - 1);

                            }
                        }

                        String tempWord = IOLayer.getText(cleanWord).toString();

                        if (tempWord.length() > 0) {
                            if (index == words.length - 2) {
                                if (cleanWord.size() > 2) {

                                    outputSentence = "VERB";
                                    stemSentence = words[index];

                                } else {
                                    outputSentence = "NOTDEFINED";
                                    stemSentence = IOLayer.getText(cleanWord).toString();
                                }

                            } else {
                                List<TamilFontEntity> stem = detectStems(tempWord, wordBefore);

                                if (NumberDetectorLayer.isNumber(cleanWord)) {
                                    outputSentence = "NUM";
                                    stemSentence = IOLayer.getText(cleanWord).toString();

                                } else if (PronounAndArticleDetector.isArticle(tempWord)) {
                                    outputSentence = "ATL";
                                    stemSentence = tempWord;

                                } else if (PronounAndArticleDetector.isProNoun(tempWord)) {
                                    outputSentence = "PNN";
                                    stemSentence = tempWord;

                                } else if (stem != null) {
                                    outputSentence = "NOUN";
                                    stemSentence = tempWord;

                                } else {
                                    stem = getNounStem(tempWord);

                                    if (stem != null) {

                                        outputSentence = "NOUN";
                                        stemSentence = IOLayer.getText(stem).toString();

                                    } else if (ExtractNounStemMLayer.extractStemNounM(tempWord) != null) {
                                        List<TamilFontEntity> stemWithoutM = ExtractNounStemMLayer.extractStemNounM(tempWord);

                                        stemWithoutM = extractPluralStem(stemWithoutM);
                                        stemSentence = IOLayer.getText(stemWithoutM).toString();
                                        if (stemWithoutM.equals(IOLayer.getTamil(tempWord))) {
                                            outputSentence = "JOIN";

                                        } else {
                                            outputSentence = "NOUN";
                                            stemSentence = words[index];
                                        }
                                    } else {
                                        outputSentence = "NOTDEFINED";

                                    }
                                }
                            }
                        }
                    }

                    String[] word = new String[2];

                    if (words[index].startsWith("்") || words[index].startsWith("ு") || words[index].startsWith("ி")) {
                        String[] w = list.remove(list.size()-1);
                        w[0] = w[0]+words[index];
                        list.add(w);
                    } else if (temp.trim().equals("")) {
                        word[0] = words[index];
                        word[1] = "SYM";

                        pw.println(word[0]);
                        list.add(word);

                    } else {
                        word[0] = words[index];
                        if (word[0].contains(".") && !word[0].matches(".*\\d.*")) {
                            String[] wo = word[0].split("\\.");
                            int j = 0;
                            for (int k = 0; k < words[index].length();) {
                                if (String.valueOf(words[index].charAt(k)).contains(".")) {
                                    word = new String[2];
                                    word[0] = ".";
                                    word[1] = "SYM";
                                    pw.println(word[0]);

                                    list.add(word);
                                    List<TamilFontEntity> tamilWord = IOLayer.getTamil(wordBefore);
                                    if (tamilWord.size() > 2 && !wordBefore.contains("எஸ்") && !wordBefore.contains("திரு") && !wordBefore.contains("திரு") && !wordBefore.contains("டபிள்") && !isNumeric(wordBefore)) {
                                        if (list.size() > 0) {
                                            morphOutput.add(list);
                                        }
                                        pw.println();
                                        list = new ArrayList<>();

                                    }

                                    list.add(word);
                                    k++;
                                } else {
                                    word = new String[2];
                                    word[0] = wo[j];
                                    pw.println(wo[j]);
                                    if (isNumeric(word[0])) {
                                        word[1] = "NUM";
                                    } else {
                                        word[1] = "NOTDEFINED";
                                    }
                                   
                                    list.add(word);

                                    k = k + wo[j].length();
                                    wordBefore = wo[j];
                                    j++;
                                }
                            }

                        } else {
                            pw.println(stemSentence);
                            word[0] = words[index];
                            pw.println(word[0]);
                            word[1] = outputSentence;
                            list.add(word);
                            wordBefore = word[0];
                        }

                    }
                    
                }
                List<TamilFontEntity> tamilWord = IOLayer.getTamil(wordBefore);
               

                if (tamilWord.size() > 2 && !wordBefore.contains("எஸ்") && !wordBefore.contains("திருமதி") && !wordBefore.contains("டபிள்") && !isNumeric(wordBefore)) {
                    checkSentence = true;
                
                    pw.println();
                }

            }
            if (list.size() > 0) {
                morphOutput.add(list);
            }
            return morphOutput;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainNounDetetectionLayer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
        return null;

    }

    /**
     * To get the stem by removing morph part
     *
     * @param tempWord Word with the morph part
     * @return the noun stem in a word, null if it is not the word
     */
    public static List<TamilFontEntity> getNounStem(String tempWord) {
        if (tempWord.trim().length() > 1) {
            /**
             * Go through each possible methods of extracting the stem
             */
            if (ExtractNounStemAlLayer.extractStemNounAl(tempWord) != null) {
                List<TamilFontEntity> stemWithoutAl = ExtractNounStemAlLayer.extractStemNounAl(tempWord);
                return extractPluralStem(stemWithoutAl);
            } else if (ExtractNounStemBelongLayer.extractNounStemBelong(tempWord) != null) {
                List<TamilFontEntity> stemWithoutBelong = ExtractNounStemBelongLayer.extractNounStemBelong(tempWord);
                return extractPluralStem(stemWithoutBelong);
            } else if (ExtractNounStemIdamLayer.extractNounStemIdam(tempWord) != null) {
                List<TamilFontEntity> stemWithoutIdam = ExtractNounStemIdamLayer.extractNounStemIdam(tempWord);
                return extractPluralStem(stemWithoutIdam);
            } else if (ExtractNounStemILayer.extractNounStemI(tempWord) != null) {
                List<TamilFontEntity> stemWithoutI = ExtractNounStemILayer.extractNounStemI(tempWord);
                return extractPluralStem(stemWithoutI);
            } else if (ExtractNounStemInFromLayer.extractStemNounInFrom(tempWord) != null) {
                List<TamilFontEntity> stemWithoutInFrom = ExtractNounStemInFromLayer.extractStemNounInFrom(tempWord);
                return extractPluralStem(stemWithoutInFrom);
            } else if (ExtractNounStemInIlLayer.extractNounStemInIl(tempWord) != null) {
                List<TamilFontEntity> stemWithoutInI1 = ExtractNounStemInIlLayer.extractNounStemInIl(tempWord);
                return extractPluralStem(stemWithoutInI1);
            } else if (ExtractNounStemIrunthuLayer.extractStemNounIrunthu(tempWord) != null) {
                List<TamilFontEntity> stemWithoutIrunthu = ExtractNounStemIrunthuLayer.extractStemNounIrunthu(tempWord);
                return extractPluralStem(stemWithoutIrunthu);
            } else if (ExtractNounStemNathuLayer.extractNounStemNathu(tempWord) != null) {
                List<TamilFontEntity> stemWithoutNathu = ExtractNounStemNathuLayer.extractNounStemNathu(tempWord);
                return extractPluralStem(stemWithoutNathu);
            } else if (ExtractNounStemUdanLayer.ExtractNounStemUdan(tempWord) != null) {
                List<TamilFontEntity> stemWithoutUdan = ExtractNounStemUdanLayer.ExtractNounStemUdan(tempWord);
                return extractPluralStem(stemWithoutUdan);
            } else if (ExtractNounStemWithLayer.ExtractNounStemWith(tempWord) != null) {
                List<TamilFontEntity> stemWithoutWith = ExtractNounStemWithLayer.ExtractNounStemWith(tempWord);
                return extractPluralStem(stemWithoutWith);
            } else if (ExtractNounStemUdaiyaLayer.ExtractNounStemUdaiya(tempWord) != null) {
                List<TamilFontEntity> stemWithoutUdaiya = ExtractNounStemUdaiyaLayer.ExtractNounStemUdaiya(tempWord);
                return extractPluralStem(stemWithoutUdaiya);
            } else if (ExtractStemNounKuLayer.extractStemNounKu(tempWord) != null) {
                List<TamilFontEntity> stemWithoutKu = ExtractStemNounKuLayer.extractStemNounKu(tempWord);
                return extractPluralStem(stemWithoutKu);
            }
        }

        return null;
    }

    /**
     * To find out the stems
     *
     * @param tempWord
     * @param beforeWord
     * @return stem if it is noun, null if it is not noun
     */
    public static List<TamilFontEntity> detectStems(String tempWord, String beforeWord) {
        List<TamilFontEntity> stemWithoutPlural = extractPluralStem(IOLayer.getTamil(tempWord));

        if (tempWord.trim().length() > 1) {
            if (ExtractNounStemMaarLayer.extractNounStemMaar(IOLayer.getText(stemWithoutPlural).toString()) != null) {
                return ExtractNounStemMaarLayer.extractNounStemMaar(IOLayer.getText(stemWithoutPlural).toString());
            } else if (BigramDetector.isNoun(beforeWord)) {
                return extractPluralStem(IOLayer.getTamil(tempWord));
            } else if (NounEndingPatternRules.isNoun(IOLayer.getTamil(tempWord))) {
                return extractPluralStem(IOLayer.getTamil(tempWord));
            } else if (PluralStemDetectionLayer.extractStemFromPlural(tempWord) != null) {
                return PluralStemDetectionLayer.extractStemFromPlural(tempWord);
            } else if (SpecialFinishLayer.getOriginalWord(tempWord) != null) {
                return IOLayer.getTamil(SpecialFinishLayer.getOriginalWord(tempWord));
            }
        }
        return null;
    }

    /**
     * To extract the stem from plural noun
     *
     * @param word
     * @return the stem of the pural word
     */
    public static List<TamilFontEntity> extractPluralStem(List<TamilFontEntity> word) {
        List<TamilFontEntity> value = PluralStemDetectionLayer.extractStemFromPlural(IOLayer.getText(word).toString());
        List<TamilFontEntity> stemWithoutPlural = (value == null) ? word : value;
        return stemWithoutPlural;
    }

    /**
     * To check whether the given string is a number
     *
     * @param str
     * @return true if the given string is a number, false if not
     */
    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
