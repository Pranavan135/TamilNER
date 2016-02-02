
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

import nlp.tamil.IOLayer;
import nlp.tamil.TamilFontEntity;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author University of Moratuwa, Computer Science and Engineering, Group-12 CSE'11
 *
 * This class is used to detect the plural word and get the stem out of the plural word
 */
public class PluralStemDetectionLayer {
    private static final Logger log = Logger.getLogger(PluralStemDetectionLayer.class.getName());

    public static List<TamilFontEntity>  extractStemFromPlural(String word) {
        List<TamilFontEntity> tamilWord = IOLayer.getTamil(word);

        if(tamilWord.size() >2) {
            List<TamilFontEntity> lastPart = tamilWord.subList(tamilWord.size() - 2, tamilWord.size());
            List<TamilFontEntity> stem = tamilWord.subList(0, tamilWord.size()-2);

            if(lastPart.equals(IOLayer.getTamil("கள்"))) {

                if(tamilWord.size() > 3) {
                    TamilFontEntity f = tamilWord.get(tamilWord.size()-3);
                            if(f.equals(IOLayer.getTamil("ர்").get(0))&& !(tamilWord.get(tamilWord.size()-4).equals(IOLayer.getTamil("மா").get(0)))&& tamilWord.get(tamilWord.size()-4).getyLocation() == 1){
                                return null;
                            }
                            else if(f.equals(IOLayer.getTamil("ங்").get(0))) {
                                stem = tamilWord.subList(0, tamilWord.size()-3);
                                stem.add(IOLayer.getTamil("ம்").get(0));
                                return stem;
                            }
                            else if(f.equals(IOLayer.getTamil("க்").get(0))) {
                                stem = tamilWord.subList(0, tamilWord.size()-3);
                                return stem;
                            }
                            else  if(f.equals(IOLayer.getTamil("ர்").get(0))&& tamilWord.get(tamilWord.size()-4).equals(IOLayer.getTamil("மா").get(0))){
                                stem = tamilWord.subList(0, tamilWord.size()-2);
                                return stem;
                            }
                            else if(f.equals(IOLayer.getTamil("ர்").get(0))) {
                                stem = tamilWord.subList(0, tamilWord.size()-3);
                                stem.add(IOLayer.getTamil("ன்").get(0));
                                return stem;
                            }

                    return stem;
                }
            }
        }
        return null;

    }



}
