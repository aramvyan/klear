package app.dbalava.klear.data.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences

fun provideDataStore(context: Context): DataStore<Preferences> {
    return PreferenceDataStoreFactory.create {
        context.applicationContext.filesDir.resolve("terra_datastore.preferences_pb")
    }
}