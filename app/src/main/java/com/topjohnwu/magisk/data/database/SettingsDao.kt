package com.topjohnwu.magisk.data.database

import com.topjohnwu.magisk.data.database.magiskdb.BaseDao
import com.topjohnwu.magisk.data.database.magiskdb.Delete
import com.topjohnwu.magisk.data.database.magiskdb.Replace
import com.topjohnwu.magisk.data.database.magiskdb.Select

class SettingsDao : BaseDao() {

    override val table = Table.SETTINGS

    fun delete(key: String) = query<Delete> {
        condition { equals("key", key) }
    }.ignoreElement()

    fun put(key: String, value: Int) = query<Replace> {
        values("key" to key, "value" to value)
    }.ignoreElement()

    fun fetch(key: String, default: Int = -1) = query<Select> {
        fields("value")
        condition { equals("key", key) }
    }.map { it.firstOrNull()?.values?.firstOrNull()?.toIntOrNull() ?: default }

}
