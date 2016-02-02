
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

package nlp.tamil;

import java.util.List;

/**
 * @author University of Moratuwa, Computer Science and Engineering, Group-12 CSE'11
 */
public class TamilFontEntity {
    private List<String> unicodeList;
    private String charList;
    private int xLocation;
    private int yLocation;
    private boolean isTamilLetter;
    private boolean isLive;

    /**
     * constructor for basic tamil alphabets
     * @param unicodeList
     * @param xLocation
     * @param yLocation
     */
    public TamilFontEntity(List<String> unicodeList, int xLocation, int yLocation) {
        this.unicodeList = unicodeList;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        isTamilLetter = true;
    }

    /**
     * constructor for tamil body, live, and weapon alphabets
     * @param unicodeList
     * @param index
     * @param isLive
     */
    public TamilFontEntity(List<String> unicodeList, int index, boolean isLive) {
        this.unicodeList = unicodeList;
        isTamilLetter = true;
        this.isLive = isLive;

        if(!isLive) {
            this.xLocation = index;
            this.yLocation =  -1;
        } else {
            this.yLocation = index;
            this.xLocation = -1;
        }

    }

    public TamilFontEntity(String charList) {
        this.charList = charList;
        isTamilLetter = false;
    }

    public List<String> getUnicodeList() {
        return unicodeList;
    }

    public void setUnicodeList(List<String> unicodeList) {
        this.unicodeList = unicodeList;
    }

    public int getxLocation() {
        return xLocation;
    }

    public void setxLocation(int xLocation) {
        this.xLocation = xLocation;
    }

    public int getyLocation() {
        return yLocation;
    }

    public void setyLocation(int yLocation) {
        this.yLocation = yLocation;
    }

    @Override
    public String toString() {
        if (isTamilLetter) {
            String str = "";

            for (String s : unicodeList) {
                str = str + s;
            }
            return str;
        } else {
            return charList;
        }

    }

    public boolean isTamilLetter() {
        return isTamilLetter;
    }
}
