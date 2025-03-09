package app.dbalava.klear.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "some_table")
class SomeEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}