
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
import nlp.morph.noun.stemExtraction.ExtractNounStemInIlLayer;

/**
 * @author University of Moratuwa, Computer Science and Engineering, Group-12 CSE'11
 *
 * To test the functionality of the class ExtractNounStemInIlLayer class
 */
public class ExtractNounStemInILayerTest {
    public static void main(String[] args) {
        InitSystem.init();
        System.out.println(ExtractNounStemInIlLayer.extractNounStemInIl("மரத்தில்"));
        System.out.println(ExtractNounStemInIlLayer.extractNounStemInIl("விண்ணில்"));
        System.out.println(ExtractNounStemInIlLayer.extractNounStemInIl("அவனில்"));
        System.out.println(ExtractNounStemInIlLayer.extractNounStemInIl("நாயில்"));
        System.out.println(ExtractNounStemInIlLayer.extractNounStemInIl("நெய்யில்"));
        System.out.println(ExtractNounStemInIlLayer.extractNounStemInIl("கமலியில்"));
        System.out.println(ExtractNounStemInIlLayer.extractNounStemInIl("பலாவில்"));
        System.out.println(ExtractNounStemInIlLayer.extractNounStemInIl("மரத்தின்"));
        System.out.println(ExtractNounStemInIlLayer.extractNounStemInIl("விண்ணின்"));
        System.out.println(ExtractNounStemInIlLayer.extractNounStemInIl("அவளின்"));
        System.out.println(ExtractNounStemInIlLayer.extractNounStemInIl("நாயின்"));
        System.out.println(ExtractNounStemInIlLayer.extractNounStemInIl("நெய்யின்"));
        System.out.println(ExtractNounStemInIlLayer.extractNounStemInIl("கமலியின்"));
        System.out.println(ExtractNounStemInIlLayer.extractNounStemInIl("பலாவின்"));
        System.out.println(ExtractNounStemInIlLayer.extractNounStemInIl("பட்டாசின்"));
        System.out.println(ExtractNounStemInIlLayer.extractNounStemInIl("மாட்டின்"));

    }
}
