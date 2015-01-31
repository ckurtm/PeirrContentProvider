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

package com.peirr.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.peirr.droidprovider.sqlite.BaseDataStore;
import com.peirr.droidprovider.sqlite.annotations.ObjectRow;

import java.util.ArrayList;
import java.util.List;

import test.test.providersample.MyPojo;

/**
 *
 * Created by kurt on 31 01 2015 .
 *
 */
public class DemoDataStore extends BaseDataStore {

    public DemoDataStore(Context context, String dbfile) {
        super(context, dbfile);
    }

    @Override
    public List<Class<? extends ObjectRow>> getDefinedClasses() {
        List<Class<? extends ObjectRow>> list = new ArrayList<>();
        list.add(MyPojo.class);
        return list;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
