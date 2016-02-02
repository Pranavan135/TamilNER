
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

package nlp.gazetteer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author University of Moratuwa, Computer Science and Engineering, Group-12 CSE'11
 */
public class RuleBaseTagger {

    private static PersonSureFireRules personSureFireRules = PersonSureFireRules.getPersonSureFireRules();
    private static OrganizationSureFireRules organizationSureFireRules = OrganizationSureFireRules.getOrganizationSureFireRules();

    public static List<String> tagList(List<String> wordList) throws IOException {
        ExtractNames.extract();
        ExtractPlaces.extract();
        ExtractCount.extract();
        List<String> retList = new ArrayList<>();

        PrintWriter printWriter = new PrintWriter(new File("resources/gz_data.txt"));

        for (int i = 0; i < wordList.size(); i++) {

            if (wordList.get(i).length() == 0) {
                printWriter.println(" ");
                continue;
            }
            if (personSureFireRules.getMatch(wordList.get(i)) > 0) {
                if ((i + 1) < wordList.size()) {
                    printWriter.println("0");
                    printWriter.println("1");
                    i = i + 1;
                }
            } else if (ExtractNames.person(wordList.get(i))) {
                retList.add(wordList.get(i) + "\t1");
                printWriter.println("1");
            } else if (organizationSureFireRules.getMatch(wordList.get(i)) > 0) {
                printWriter.println("3");
            } else if (ExtractPlaces.place(wordList.get(i))) {
                printWriter.println("2");
            } else if (ExtractPlaces.place(wordList.get(i))) {
                printWriter.println("4");
            } else if (digit(wordList.get(i))) {
                if (wordList.get(i).length() == 4) {
                    printWriter.println("5");
                } else {
                    printWriter.println("4");
                }
            } else {
                retList.add(wordList.get(i) + "\t0");
                printWriter.println("0");
            }

        }

        printWriter.close();

        return retList;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("resources/word.txt")));
        List<String> wordList = new ArrayList<>();

        String line = bufferedReader.readLine();

        while (line != null) {
            wordList.add(line.trim());
            line = bufferedReader.readLine();
        }

        bufferedReader.close();

        System.out.println(wordList.size());

        List<String> tagged = tagList(wordList);

        System.out.println(tagged.size());

    }

    private static boolean digit(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (!Character.isDigit(word.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static void getTestData(String file) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(file)));
            List<String> wordList = new ArrayList<>();
            String line = bufferedReader.readLine();
            while (line != null) {
                wordList.add(line.trim());
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            List<String> tagged = tagList(wordList);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RuleBaseTagger.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RuleBaseTagger.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ex) {
                Logger.getLogger(RuleBaseTagger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
