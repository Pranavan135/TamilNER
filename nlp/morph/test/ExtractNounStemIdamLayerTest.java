
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

package nlp.morph.test;

import nlp.systeminit.InitSystem;
import nlp.morph.noun.stemExtraction.ExtractNounStemIdamLayer;

/**
 * @author University of Moratuwa, Computer Science and Engineering, Group-12 CSE'11
 */
public class ExtractNounStemIdamLayerTest {
    /**
     * Exract stem இடம் in Tamil
     * @param args
     */
    public static void main(String[] args) {
        InitSystem.init();
        System.out.println(ExtractNounStemIdamLayer.extractNounStemIdam("மரத்திடம்"));
        System.out.println(ExtractNounStemIdamLayer.extractNounStemIdam("விண்ணிடம்"));
        System.out.println(ExtractNounStemIdamLayer.extractNounStemIdam("அவனிடம்"));
        System.out.println(ExtractNounStemIdamLayer.extractNounStemIdam("நாயிடம்"));
        System.out.println(ExtractNounStemIdamLayer.extractNounStemIdam("நெய்யிடம்"));
        System.out.println(ExtractNounStemIdamLayer.extractNounStemIdam("கமலியிடம்"));
        System.out.println(ExtractNounStemIdamLayer.extractNounStemIdam("பலாவிடம்"));
        System.out.println(ExtractNounStemIdamLayer.extractNounStemIdam("படகிடம்"));
        System.out.println(ExtractNounStemIdamLayer.extractNounStemIdam("மாட்டிடம்"));


    }
}