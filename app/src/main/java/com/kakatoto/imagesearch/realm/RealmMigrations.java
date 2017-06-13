package com.kakatoto.imagesearch.realm;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

/**
 * Created by ohyowan on 2017. 5. 13..
 */

public class RealmMigrations implements RealmMigration{

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        final RealmSchema schema = realm.getSchema();

        if (oldVersion == 1) {
            final RealmObjectSchema userSchema = schema.get("ScrapBean");
            userSchema.addField("mKey", long.class);
        }
    }
}
