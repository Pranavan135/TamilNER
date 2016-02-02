
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
import nlp.tamil.IOLayer;
import nlp.morph.noun.NounEndingPatternRules;

/**
 * @author University of Moratuwa, Computer Science and Engineering, Group-12 CSE'11
 *
 * Class to check the ending patterns of the noun
 */
public class NounEndingPatternRulesTest {

    public static void main(String[] args) {
        InitSystem.init();

        System.out.println(NounEndingPatternRules.isNoun(IOLayer.getTamil("முதலாளி")));
        System.out.println(NounEndingPatternRules.isNoun(IOLayer.getTamil("பொருளியல்")));
        System.out.println(NounEndingPatternRules.isNoun(IOLayer.getTamil("கடைக்காரன்")));
        System.out.println(NounEndingPatternRules.isNoun(IOLayer.getTamil("தனம்")));
        System.out.println(NounEndingPatternRules.isNoun(IOLayer.getTamil("சமத்துவம்")));
        System.out.println(NounEndingPatternRules.isNoun(IOLayer.getTamil("எழுத்தாளன்")));
        System.out.println(NounEndingPatternRules.isNoun(IOLayer.getTamil("ஆட்சி")));
    }
}
