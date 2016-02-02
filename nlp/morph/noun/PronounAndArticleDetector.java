
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

package nlp.morph.noun;

import nlp.tamil.IOLayer;
import nlp.tamil.TamilFontEntity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author University of Moratuwa, Computer Science and Engineering, Group-12 CSE'11
 *
 * This class is used to identify the articles and pronouns in a given text
 */
public class PronounAndArticleDetector {
    private static List<List<TamilFontEntity>> pronounList;
    private static List<List<TamilFontEntity>> articleList;
    private static final Logger log = Logger.getLogger(PronounAndArticleDetector.class.getName());

    /**
     * This method is used to initialize the necessary variables and add a list of pronouns and articles
     */
    public static void init() {
        try {
            pronounList = new ArrayList<>();
            articleList = new ArrayList<>();
            Scanner inFile = new Scanner(new File(new File("resources/pronouns.txt").getCanonicalPath()));
            inFile.nextLine();
            while(inFile.hasNext()) {
                pronounList.add(IOLayer.getTamil(inFile.next()));
            }

            inFile = new Scanner(new File(new File("resources/articles.txt").getCanonicalPath()));
            inFile.nextLine();
            while(inFile.hasNext()) {
                articleList.add(IOLayer.getTamil(inFile.next()));
            }

        }
        catch(IOException e){
            log.log(Level.WARNING, "EEROR : {0}", e.getMessage());
        }

    }

    /**
     * To check whether the given word is a pronoun
     * @param word The tamil word to be checked
     * @return true, if the given word is pronoun, false if the given word is not pronoun
     */
    public static boolean isProNoun(String word) {
        return pronounList.stream().anyMatch((proNoun) -> (IOLayer.getText(proNoun).toString().equals(word)));
    }

    /**
     * To check whether the given word is an article
     * @param word The tamil word to be checked
     * @return true, if the given word is article, false if the given word is not article
     */
    public static boolean isArticle(String word) {
        return articleList.stream().anyMatch((article) -> (IOLayer.getText(article).toString().equals(word)));
    }
}
