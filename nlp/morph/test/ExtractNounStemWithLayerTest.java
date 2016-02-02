
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

package nlp.morph.test;

import nlp.systeminit.InitSystem;
import nlp.morph.noun.stemExtraction.ExtractNounStemWithLayer;

/**
 * @author University of Moratuwa, Computer Science and Engineering, Group-12 CSE'11
 *
 * Class for testing the each possibilities with (ஐ)
 */
public class ExtractNounStemWithLayerTest {

    /**
     * Testing the each possibilities with (ஐ)
     *
     * @param args
     */
    public static void main(String[] args) {
        InitSystem.init();
        System.out.println(ExtractNounStemWithLayer.ExtractNounStemWith("விண்ணோடு"));
        System.out.println(ExtractNounStemWithLayer.ExtractNounStemWith("அவனோடு"));
        System.out.println(ExtractNounStemWithLayer.ExtractNounStemWith("மரத்தோடு"));
        System.out.println(ExtractNounStemWithLayer.ExtractNounStemWith("கரங்களோடு"));
        System.out.println(ExtractNounStemWithLayer.ExtractNounStemWith("புடைவையோடு"));
        System.out.println(ExtractNounStemWithLayer.ExtractNounStemWith("பலாவோடு"));
        System.out.println(ExtractNounStemWithLayer.ExtractNounStemWith("நாயோடு"));
        System.out.println(ExtractNounStemWithLayer.ExtractNounStemWith("நெய்யோடு"));
        System.out.println(ExtractNounStemWithLayer.ExtractNounStemWith("ஆணோடு"));
        System.out.println(ExtractNounStemWithLayer.ExtractNounStemWith("குரங்கோடு"));
        System.out.println(ExtractNounStemWithLayer.ExtractNounStemWith("பாம்போடு"));
        System.out.println(ExtractNounStemWithLayer.ExtractNounStemWith("மாட்டோடு"));
        System.out.println(ExtractNounStemWithLayer.ExtractNounStemWith("காற்றோடு"));

    }
}
