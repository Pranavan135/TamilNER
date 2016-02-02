
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
import nlp.morph.noun.stemExtraction.ExtractNounStemNathuLayer;

/**
 * @author University of Moratuwa, Computer Science and Engineering, Group-12 CSE'11
 *
 * ExtractNounNathuLayer
 */
public class ExtractNounStemNathuLayerTest {

    public static void main(String[] args) {
        InitSystem.init();
        System.out.println(ExtractNounStemNathuLayer.extractNounStemNathu("மரத்தது"));
        System.out.println(ExtractNounStemNathuLayer.extractNounStemNathu("விண்ணது"));
        System.out.println(ExtractNounStemNathuLayer.extractNounStemNathu("அவனது"));
        System.out.println(ExtractNounStemNathuLayer.extractNounStemNathu("நாயது"));
        System.out.println(ExtractNounStemNathuLayer.extractNounStemNathu("மாட்டது"));
        System.out.println(ExtractNounStemNathuLayer.extractNounStemNathu("நெய்யது"));
        System.out.println(ExtractNounStemNathuLayer.extractNounStemNathu("படகது"));

    }
}
