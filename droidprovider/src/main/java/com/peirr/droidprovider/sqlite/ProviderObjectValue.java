/*
 *
 *  * Copyright (c) 2015 Kurt Mbanje.
 *  *
 *  *   Licensed under the Apache License, Version 2.0 (the "License");
 *  *   you may not use this file except in compliance with the License.
 *  *   You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *   Unless required by applicable law or agreed to in writing, software
 *  *   distributed under the License is distributed on an "AS IS" BASIS,
 *  *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *   See the License for the specific language governing permissions and
 *  *   limitations under the License.
 *  *
 *  *   ckurtm at gmail dot com
 *  *   https://github.com/ckurtm/DroidProvider
 *
 */

package com.peirr.droidprovider.sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

/**
 * @author kurt
 */
public class ProviderObjectValue {
    public int MANY;
    public int ONE;
    public String TYPE;
    public String ITEM_TYPE;
    public String KEY;
    public String TABLE;
    public Uri URI;


    /**
     * gets the rows that were deleted
     *
     * @param uri       the table uri
     * @param type      the type
     * @param db        the db
     * @param selection selecting of columns
     * @param args      selection args
     * @return returns the count of deleted rows
     */
    public int getDeletedRows(Uri uri, int type, SQLiteDatabase db, String selection, String[] args) {
        int rowsAffected = 0;
        if (type == MANY) {
            rowsAffected = db.delete(TABLE, selection, args);
        } else if (type == ONE) {
            String id = uri.getLastPathSegment();
            if (TextUtils.isEmpty(selection)) {
                rowsAffected = db.delete(TABLE, KEY + "=" + id, null);
            } else {
                rowsAffected = db.delete(TABLE, selection + " and " + KEY + "=" + id, args);
            }
        }
        return rowsAffected;
    }

    /**
     * gets the rows that were updated
     *
     * @param uri       the table uri
     * @param values    content values
     * @param type      the type
     * @param db        the db
     * @param selection selecting of columns
     * @param args      selection args
     * @return returns the count of updated rows
     */
    public int getUpdatedRows(Uri uri, ContentValues values, int type, SQLiteDatabase db, String selection, String[] args) {
        int rowsAffected = 0;
        if (type == ONE) {
            String id = uri.getLastPathSegment();
            StringBuilder modSelection = new StringBuilder(KEY + "=" + id);
            if (!TextUtils.isEmpty(selection)) {
                modSelection.append(" AND ").append(selection);
            }
            rowsAffected = db.update(TABLE, values, modSelection.toString(), null);
        } else if (type == MANY) {
            rowsAffected = db.update(TABLE, values, selection, args);
        }
        return rowsAffected;
    }

    @Override
    public String toString() {
        return "ProviderObjectValue{" +
                "MANY=" + MANY +
                ", ONE=" + ONE +
                ", TYPE='" + TYPE + '\'' +
                ", ITEM_TYPE='" + ITEM_TYPE + '\'' +
                ", KEY='" + KEY + '\'' +
                ", BASE='" + TABLE + '\'' +
                ", URI=" + URI +
                '}';
    }
}
