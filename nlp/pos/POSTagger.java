
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

package nlp.pos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.List;

import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author University of Moratuwa, Computer Science and Engineering, Group-12 CSE'11
 *
 * Class to POS tag the given text
 */
public class POSTagger {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("usage: java TaggerDemo fileToTag");
        } else {
            postag(args[0]);
        }

    }

    public static void postag(String fileName) {

        try {
            MaxentTagger tagger;
            tagger = new MaxentTagger("Models/tamil.tagger");

            List<List<HasWord>> sentences = MaxentTagger.tokenizeText(new BufferedReader(new FileReader(fileName)));
            try (PrintWriter writer = new PrintWriter("POSTagged_" + fileName, "UTF-8")) {
                sentences.stream().map((sentence) -> tagger.tagSentence(sentence)).map((List<TaggedWord> tSentence) -> {
                    tSentence.stream().map((word) -> String.valueOf(word)).map((s) -> s.split("/")).map((y) -> {
                        writer.println(y[0] + "\t" + y[1]);
                        return y;
                    }).forEach((y) -> {
                        System.out.println(y[0] + "\t" + y[1]);
                    });
                    return tSentence;
                }).map((_item) -> {
                    System.out.println();
                    return _item;
                }).forEach((_item) -> {
                    writer.println();
                });
            }
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(POSTagger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
