package app.dbalava.klear.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import app.dbalava.klear.data.room.dao.SomeDao
import app.dbalava.klear.data.room.entities.SomeEntity

@Database(entities = [SomeEntity::class], version = 1)
abstract class KlearDB: RoomDatabase() {
    abstract fun someDao(): SomeDao

}