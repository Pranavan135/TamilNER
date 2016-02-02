
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

package nlp.systeminit;

import nlp.main.NER_Main;
import nlp.morph.database.NounListCheck;
import nlp.morph.noun.BigramDetector;
import nlp.morph.noun.NounEndingPatternRules;
import nlp.morph.noun.NumberDetectorLayer;
import nlp.morph.noun.PronounAndArticleDetector;
import nlp.morph.noun.stemExtraction.ExtractNounStemMLayer;
import nlp.tamil.TamilLayout;

/**
 * @author University of Moratuwa, Computer Science and Engineering, Group-12 CSE'11
 *
 * This class is used to initialize the system at the startup. Initialization of
 * static classes happens here
 */
public class InitSystem {

    public static void init() {
        TamilLayout.init();
        NounEndingPatternRules.init();
        PronounAndArticleDetector.init();
        NumberDetectorLayer.init();
        BigramDetector.init();
        ExtractNounStemMLayer.init();
        NounListCheck.init();
    }
}
